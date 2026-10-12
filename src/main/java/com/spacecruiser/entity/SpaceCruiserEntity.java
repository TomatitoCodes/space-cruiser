package com.spacecruiser.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * Nave pilotable. Funciona como un barco: el cliente del piloto calcula el movimiento
 * y el servidor lo valida mediante los paquetes de vehículo de vanilla.
 *
 * Controles: W/S/A/D mueven la nave, Espacio sube, Ctrl izquierdo baja (configurable),
 * la nave gira hacia donde mira el piloto, Shift para bajarse.
 */
public class SpaceCruiserEntity extends Entity {
	// Los rellena el cliente (SpaceCruiserClient); en el servidor se quedan a false.
	public static BooleanSupplier ascendInput = () -> false;
	public static BooleanSupplier descendInput = () -> false;

	private static final double ACCELERATION = 0.06;
	private static final double VERTICAL_ACCELERATION = 0.04;
	private static final double HORIZONTAL_DRAG = 0.92;
	private static final double VERTICAL_DRAG = 0.88;
	private static final float TURN_SPEED = 10.0f;

	// Inclinación visual (solo cliente), la lee el renderer.
	public float leanPitch;
	public float leanRoll;
	public float prevLeanPitch;
	public float prevLeanRoll;

	// Interpolación para quien ve la nave pilotada por otro jugador.
	private int lerpSteps;
	private double lerpX;
	private double lerpY;
	private double lerpZ;
	private float lerpYaw;

	/** Escala con la que se dibuja la nave (1 = tamaño original). La usa el renderer y las llamas. */
	public final float modelScale;
	private final Supplier<Item> item;

	public SpaceCruiserEntity(EntityType<?> type, World world, float modelScale, Supplier<Item> item) {
		super(type, world);
		this.modelScale = modelScale;
		this.item = item;
		this.setNoGravity(true);
	}

	@Override
	protected void initDataTracker(DataTracker.Builder builder) {
	}

	@Override
	protected void readCustomDataFromNbt(NbtCompound nbt) {
	}

	@Override
	protected void writeCustomDataToNbt(NbtCompound nbt) {
	}

	@Override
	public LivingEntity getControllingPassenger() {
		if (this.getFirstPassenger() instanceof LivingEntity living) {
			return living;
		}
		return super.getControllingPassenger();
	}

	@Override
	public ActionResult interact(PlayerEntity player, Hand hand) {
		if (player.shouldCancelInteraction()) {
			return ActionResult.PASS;
		}
		if (!this.getWorld().isClient) {
			return player.startRiding(this) ? ActionResult.CONSUME : ActionResult.PASS;
		}
		return ActionResult.SUCCESS;
	}

	@Override
	public boolean canHit() {
		return true;
	}

	@Override
	public ItemStack getPickBlockStack() {
		return new ItemStack(this.item.get());
	}

	/** Volar bajo no debe hacer daño por caída ni a la nave ni al piloto. */
	@Override
	public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
		return false;
	}

	@Override
	public boolean damage(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		}
		if (this.getWorld().isClient || this.isRemoved()) {
			return true;
		}

		boolean creativeAttacker = source.getAttacker() instanceof PlayerEntity player && player.isCreative();
		if (!creativeAttacker && this.getWorld().getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
			this.dropStack(new ItemStack(this.item.get()));
		}
		this.discard();
		return true;
	}

	@Override
	public void updateTrackedPositionAndAngles(double x, double y, double z, float yaw, float pitch, int interpolationSteps) {
		this.lerpX = x;
		this.lerpY = y;
		this.lerpZ = z;
		this.lerpYaw = yaw;
		this.lerpSteps = Math.max(interpolationSteps, 1);
	}

	@Override
	public void tick() {
		super.tick();

		World world = this.getWorld();
		LivingEntity pilot = this.getControllingPassenger();

		if (this.isLogicalSideForUpdatingMovement()) {
			this.flyTick(pilot);
		} else if (world.isClient && this.lerpSteps > 0) {
			double t = 1.0 / this.lerpSteps;
			this.setPosition(
					MathHelper.lerp(t, this.getX(), this.lerpX),
					MathHelper.lerp(t, this.getY(), this.lerpY),
					MathHelper.lerp(t, this.getZ(), this.lerpZ)
			);
			this.setRotation(
					this.getYaw() + (float) (MathHelper.wrapDegrees(this.lerpYaw - this.getYaw()) * t),
					this.getPitch()
			);
			this.lerpSteps--;
		}

		// Sin daño por caída para nadie que vaya a bordo.
		this.onLanding();
		for (Entity passenger : this.getPassengerList()) {
			passenger.onLanding();
		}

		if (world.isClient) {
			this.updateLean();
			this.spawnThrusterParticles(pilot);
		}
	}

	private void flyTick(LivingEntity pilot) {
		Vec3d velocity = this.getVelocity();
		double vx = velocity.x;
		double vy = velocity.y;
		double vz = velocity.z;

		if (pilot != null) {
			// La nave gira suavemente hacia donde mira el piloto.
			float diff = MathHelper.wrapDegrees(pilot.getYaw() - this.getYaw());
			this.setYaw(this.getYaw() + MathHelper.clamp(diff, -TURN_SPEED, TURN_SPEED));

			double forward = pilot.forwardSpeed;
			double side = pilot.sidewaysSpeed; // positivo = izquierda
			double yawRad = Math.toRadians(this.getYaw());
			double sin = Math.sin(yawRad);
			double cos = Math.cos(yawRad);

			vx += (-sin * forward + cos * side) * ACCELERATION;
			vz += (cos * forward + sin * side) * ACCELERATION;
			if (ascendInput.getAsBoolean()) {
				vy += VERTICAL_ACCELERATION;
			}
			if (descendInput.getAsBoolean()) {
				vy -= VERTICAL_ACCELERATION;
			}

			vx *= HORIZONTAL_DRAG;
			vz *= HORIZONTAL_DRAG;
			vy *= VERTICAL_DRAG;
		} else {
			// Sin piloto: se frena y desciende despacio hasta posarse.
			vx *= 0.8;
			vz *= 0.8;
			vy = MathHelper.clamp(vy * 0.8 - 0.02, -0.08, 0.1);
		}

		this.setVelocity(vx, vy, vz);
		this.move(MovementType.SELF, this.getVelocity());
	}

	/** Calcula cuánto se inclina la nave según su movimiento (positivo = morro abajo). */
	private void updateLean() {
		double dx = this.getX() - this.prevX;
		double dz = this.getZ() - this.prevZ;
		double yawRad = Math.toRadians(this.getYaw());
		double forwardMove = -Math.sin(yawRad) * dx + Math.cos(yawRad) * dz;
		double sideMove = Math.cos(yawRad) * dx + Math.sin(yawRad) * dz; // positivo = izquierda

		this.prevLeanPitch = this.leanPitch;
		this.prevLeanRoll = this.leanRoll;
		this.leanPitch += ((float) (forwardMove * 16.0) - this.leanPitch) * 0.25f;
		this.leanRoll += ((float) (-sideMove * 16.0) - this.leanRoll) * 0.25f;
	}

	private void spawnThrusterParticles(LivingEntity pilot) {
		if (pilot == null) {
			return;
		}
		double dx = this.getX() - this.prevX;
		double dz = this.getZ() - this.prevZ;
		double speed = Math.hypot(dx, dz);
		if (speed < 0.08 || this.random.nextInt(2) != 0) {
			return;
		}

		double yawRad = Math.toRadians(this.getYaw());
		double sin = Math.sin(yawRad);
		double cos = Math.cos(yawRad);
		for (int sign = -1; sign <= 1; sign += 2) {
			// Boca trasera de los cubos de basura: a ~1,9 bloques del centro, ~0,75 por detrás y a ~1,8 de altura.
			double s = this.modelScale;
			double px = this.getX() + cos * 1.9 * s * sign + (-sin) * -0.8 * s;
			double pz = this.getZ() + sin * 1.9 * s * sign + cos * -0.8 * s;
			this.getWorld().addParticle(ParticleTypes.FLAME, px, this.getY() + 1.8 * s, pz, -dx * 0.3, 0.0, -dz * 0.3);
		}
	}
}
