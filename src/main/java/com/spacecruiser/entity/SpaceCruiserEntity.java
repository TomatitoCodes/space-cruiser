package com.spacecruiser.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import java.util.function.BiConsumer;
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
	/** Lo rellena el cliente: true si el piloto va en primera persona (vista de cabina). */
	public static BooleanSupplier cockpitView = () -> false;

	/** Lo rellena el cliente: abre el GPS cuando el piloto hace clic mirando a la pantalla. */
	public static BiConsumer<SpaceCruiserEntity, PlayerEntity> gpsClick = (ship, player) -> {
	};

	/** Carga de la batería del reactor, en fire charges (0 a 10). */
	private static final TrackedData<Float> BATTERY =
			DataTracker.registerData(SpaceCruiserEntity.class, TrackedDataHandlerRegistry.FLOAT);

	// Posición de la pantalla del GPS respecto al centro de la nave (bloques a escala 1).
	private static final double GPS_FORWARD = 1.03;
	private static final double GPS_UP = 1.27;
	private static final double GPS_RADIUS = 0.5;

	private static final double ACCELERATION = 0.06;
	private static final double VERTICAL_ACCELERATION = 0.04;
	private static final double HORIZONTAL_DRAG = 0.92;
	private static final double VERTICAL_DRAG = 0.88;
	/** Giro máximo con A/D en vista de cabina, en grados por tick. */
	private static final float TURN_SPEED = 3.5f;
	/** Giro máximo hacia donde mira el piloto en tercera persona, en grados por tick. */
	private static final float FOLLOW_TURN_SPEED = 10.0f;

	private float turnVelocity;

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
		builder.add(BATTERY, 0.0f);
	}

	public float getBattery() {
		return this.dataTracker.get(BATTERY);
	}

	public void setBattery(float value) {
		this.dataTracker.set(BATTERY, MathHelper.clamp(value, 0.0f, 10.0f));
	}

	/** ¿Está el piloto mirando hacia la pantalla del GPS? (distancia del rayo de su mirada al centro de la pantalla) */
	public boolean isLookingAtScreen(PlayerEntity player) {
		double s = this.modelScale;
		double yawRad = Math.toRadians(this.getYaw());
		Vec3d forward = new Vec3d(-Math.sin(yawRad), 0.0, Math.cos(yawRad));
		Vec3d center = this.getPos().add(forward.multiply(GPS_FORWARD * s)).add(0.0, GPS_UP * s + 0.06, 0.0);
		Vec3d eye = player.getEyePos();
		Vec3d look = player.getRotationVec(1.0f);
		Vec3d toCenter = center.subtract(eye);
		double t = toCenter.dotProduct(look);
		if (t < 0.2) {
			return false;
		}
		return toCenter.subtract(look.multiply(t)).length() < GPS_RADIUS * s;
	}

	@Override
	protected void readCustomDataFromNbt(NbtCompound nbt) {
		this.setBattery(nbt.getFloat("Battery"));
	}

	@Override
	protected void writeCustomDataToNbt(NbtCompound nbt) {
		nbt.putFloat("Battery", this.getBattery());
	}

	@Override
	public LivingEntity getControllingPassenger() {
		if (this.getFirstPassenger() instanceof LivingEntity living) {
			return living;
		}
		return super.getControllingPassenger();
	}

	/**
	 * Al bajarse, el jugador aparece junto a la nave (a su lado, sin chocar con nada) en vez de
	 * donde decida el valor por defecto de vanilla, que lo manda encima de la caja de colisión y
	 * hacía que saltara a un sitio raro y volviera.
	 */
	@Override
	public Vec3d updatePassengerForDismount(LivingEntity passenger) {
		double distance = this.getWidth() / 2.0 + passenger.getWidth() / 2.0 + 0.6;
		double yawRad = Math.toRadians(this.getYaw());
		double sin = Math.sin(yawRad);
		double cos = Math.cos(yawRad);
		// Direcciones respecto a la nave: derecha, izquierda, atrás, delante y diagonales.
		double[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-0.7, -0.7}, {0.7, -0.7}, {-0.7, 0.7}, {0.7, 0.7}};
		for (double[] d : dirs) {
			double side = d[0] * distance;
			double front = d[1] * distance;
			double x = this.getX() + cos * side - sin * front;
			double z = this.getZ() + sin * side + cos * front;
			for (double dy : new double[]{0.0, 1.0, -1.0}) {
				Vec3d target = new Vec3d(x, this.getY() + dy, z);
				Box box = passenger.getBoundingBox(EntityPose.STANDING).offset(target.subtract(passenger.getPos()));
				if (this.getWorld().isSpaceEmpty(passenger, box)) {
					return target;
				}
			}
		}
		return new Vec3d(this.getX(), this.getBoundingBox().maxY, this.getZ());
	}

	@Override
	public ActionResult interact(PlayerEntity player, Hand hand) {
		// El piloto toca la nave desde dentro: si mira a la pantalla, se abre el GPS (solo en el cliente).
		if (this.hasPassenger(player)) {
			if (this.getWorld().isClient) {
				gpsClick.accept(this, player);
				return ActionResult.SUCCESS;
			}
			return ActionResult.PASS;
		}
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

	/** En vista de cabina el piloto puede mirar 90 grados a cada lado de la proa (180 en total) sin que la nave gire. */
	@Override
	public void onPassengerLookAround(Entity passenger) {
		if (!cockpitView.getAsBoolean()) {
			return;
		}
		float relative = MathHelper.wrapDegrees(passenger.getYaw() - this.getYaw());
		float clamped = MathHelper.clamp(relative, -90.0f, 90.0f);
		passenger.prevYaw += clamped - relative;
		passenger.setYaw(passenger.getYaw() + clamped - relative);
		passenger.setHeadYaw(passenger.getYaw());
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
			double forward = pilot.forwardSpeed;
			double side = pilot.sidewaysSpeed; // positivo = izquierda
			double sin;
			double cos;
			if (cockpitView.getAsBoolean()) {
				// Vista de cabina: A/D giran la nave y la mirada del piloto queda libre; el piloto gira con la cabina,
				// así que lo que ve no cambia al virar.
				float targetTurn = (float) (-side * TURN_SPEED);
				this.turnVelocity += (targetTurn - this.turnVelocity) * 0.3f;
				if (Math.abs(this.turnVelocity) > 0.01f) {
					this.setYaw(this.getYaw() + this.turnVelocity);
					pilot.setYaw(pilot.getYaw() + this.turnVelocity);
					pilot.prevYaw += this.turnVelocity;
				}
				double yawRad = Math.toRadians(this.getYaw());
				sin = Math.sin(yawRad);
				cos = Math.cos(yawRad);
				vx += -sin * forward * ACCELERATION;
				vz += cos * forward * ACCELERATION;
			} else {
				// Tercera persona (como siempre): la nave gira suavemente hacia donde mira el piloto y A/D desplazan de lado.
				this.turnVelocity = 0.0f;
				float diff = MathHelper.wrapDegrees(pilot.getYaw() - this.getYaw());
				this.setYaw(this.getYaw() + MathHelper.clamp(diff, -FOLLOW_TURN_SPEED, FOLLOW_TURN_SPEED));
				double yawRad = Math.toRadians(this.getYaw());
				sin = Math.sin(yawRad);
				cos = Math.cos(yawRad);
				vx += (-sin * forward + cos * side) * ACCELERATION;
				vz += (cos * forward + sin * side) * ACCELERATION;
			}
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
