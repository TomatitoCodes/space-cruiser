package com.spacecruiser.entity;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.InterpolationHandler;
import net.minecraft.world.entity.LinearInterpolationHandler;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

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
	public static BiConsumer<SpaceCruiserEntity, Player> gpsClick = (ship, player) -> {
	};

	/** Carga de la batería del reactor, en fire charges (0 a 10). */
	private static final EntityDataAccessor<Float> BATTERY =
			SynchedEntityData.defineId(SpaceCruiserEntity.class, EntityDataSerializers.FLOAT);

	// Posición de la pantalla del GPS respecto al centro de la nave (bloques a escala 1).
	private static final double GPS_FORWARD = 0.64;
	private static final double GPS_LEFT = 0.64;
	private static final double GPS_UP = 1.29;
	private static final double GPS_RADIUS = 0.4;

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

	/** Escala con la que se dibuja la nave (1 = tamaño original). La usa el renderer y las llamas. */
	public final float modelScale;
	private final Supplier<Item> item;

	public SpaceCruiserEntity(EntityType<?> type, Level level, float modelScale, Supplier<Item> item) {
		super(type, level);
		this.modelScale = modelScale;
		this.item = item;
		this.setNoGravity(true);
	}

	/** Para quien ve la nave pilotada por otro jugador: vanilla interpola posición y giro. */
	@Override
	protected InterpolationHandler createInterpolationHandler() {
		return LinearInterpolationHandler.create(this, 3);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(BATTERY, 0.0f);
	}

	public float getBattery() {
		return this.entityData.get(BATTERY);
	}

	public void setBattery(float value) {
		this.entityData.set(BATTERY, Mth.clamp(value, 0.0f, 10.0f));
	}

	/** El piloto va 2 px más atrás, sentado: las caderas a la altura del asiento (los pies a ras del suelo de la nave). */
	@Override
	protected void positionRider(Entity passenger, Entity.MoveFunction moveFunction) {
		if (!this.hasPassenger(passenger)) {
			return;
		}
		double s = this.modelScale;
		double yawRad = Math.toRadians(this.getYRot());
		double back = (2.0 / 16.0) * s;
		moveFunction.accept(passenger,
				this.getX() + Math.sin(yawRad) * back,
				this.getY() + 0.05 * s,
				this.getZ() - Math.cos(yawRad) * back);
	}

	/** ¿Está el piloto mirando hacia la pantalla del GPS? (distancia del rayo de su mirada al centro de la pantalla) */
	public boolean isLookingAtScreen(Player player) {
		double s = this.modelScale;
		double yawRad = Math.toRadians(this.getYRot());
		Vec3 forward = new Vec3(-Math.sin(yawRad), 0.0, Math.cos(yawRad));
		Vec3 left = new Vec3(Math.cos(yawRad), 0.0, Math.sin(yawRad));
		Vec3 center = this.position().add(forward.scale(GPS_FORWARD * s)).add(left.scale(GPS_LEFT * s))
				.add(0.0, GPS_UP * s + 0.06, 0.0);
		Vec3 eye = player.getEyePosition();
		Vec3 look = player.getViewVector(1.0f);
		Vec3 toCenter = center.subtract(eye);
		double t = toCenter.dot(look);
		if (t < 0.2) {
			return false;
		}
		return toCenter.subtract(look.scale(t)).length() < GPS_RADIUS * s;
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		this.setBattery(input.getFloatOr("Battery", 0.0f));
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		output.putFloat("Battery", this.getBattery());
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
	public Vec3 getDismountLocationForPassenger(LivingEntity passenger) {
		double distance = this.getBbWidth() / 2.0 + passenger.getBbWidth() / 2.0 + 0.6;
		double yawRad = Math.toRadians(this.getYRot());
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
				Vec3 target = new Vec3(x, this.getY() + dy, z);
				AABB box = passenger.getDimensions(Pose.STANDING).makeBoundingBox(target);
				if (this.level().noCollision(passenger, box)) {
					return target;
				}
			}
		}
		return new Vec3(this.getX(), this.getBoundingBox().maxY, this.getZ());
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand, Vec3 location) {
		// El piloto toca la nave desde dentro: si mira a la pantalla, se abre el GPS (solo en el cliente).
		if (this.hasPassenger(player)) {
			if (this.level().isClientSide()) {
				gpsClick.accept(this, player);
				return InteractionResult.SUCCESS;
			}
			return InteractionResult.PASS;
		}
		if (player.isSecondaryUseActive()) {
			return InteractionResult.PASS;
		}
		if (!this.level().isClientSide()) {
			return player.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public boolean isPickable() {
		return true;
	}

	@Override
	public ItemStack getPickResult() {
		return new ItemStack(this.item.get());
	}

	/** En vista de cabina el piloto puede mirar 90 grados a cada lado de la proa (180 en total) sin que la nave gire. */
	@Override
	public void onPassengerTurned(Entity passenger) {
		if (!cockpitView.getAsBoolean()) {
			return;
		}
		float relative = Mth.wrapDegrees(passenger.getYRot() - this.getYRot());
		float clamped = Mth.clamp(relative, -90.0f, 90.0f);
		passenger.yRotO += clamped - relative;
		passenger.setYRot(passenger.getYRot() + clamped - relative);
		passenger.setYHeadRot(passenger.getYRot());
	}

	/** Volar bajo no debe hacer daño por caída ni a la nave ni al piloto. */
	@Override
	public boolean causeFallDamage(double fallDistance, float damageModifier, DamageSource damageSource) {
		return false;
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
		if (this.isInvulnerableToBase(source)) {
			return false;
		}
		if (this.isRemoved()) {
			return true;
		}

		boolean creativeAttacker = source.getEntity() instanceof Player player && player.isCreative();
		if (!creativeAttacker && level.getGameRules().get(GameRules.ENTITY_DROPS)) {
			this.spawnAtLocation(level, new ItemStack(this.item.get()));
		}
		this.discard();
		return true;
	}

	/** Duración (ticks) del espiral verde de llegada tras un viaje entre dimensiones. */
	public static final int PORTAL_TICKS = 30;
	private static final DustParticleOptions PORTAL_DUST = new DustParticleOptions(0x40FF59, 1.3f);
	public int portalTicks = 0;

	/** Un paso del espiral verde (tipo portal): tres brazos que giran y suben alrededor de la nave. progress 0..1. */
	public static void spawnPortalSpiral(ServerLevel level, double x, double y, double z, double progress, float scale) {
		double radius = (2.6 - 1.4 * progress) * scale;
		double height = (0.2 + 2.4 * progress) * scale;
		for (int arm = 0; arm < 3; arm++) {
			double angle = progress * Math.PI * 6.0 + arm * (Math.PI * 2.0 / 3.0);
			level.sendParticles(PORTAL_DUST, x + Math.cos(angle) * radius, y + height, z + Math.sin(angle) * radius,
					2, 0.05, 0.05, 0.05, 0.0);
		}
	}

	@Override
	public void tick() {
		super.tick();

		Level level = this.level();
		LivingEntity pilot = this.getControllingPassenger();

		if (this.isLocalInstanceAuthoritative()) {
			this.flyTick(pilot);
		}

		// Sin daño por caída para nadie que vaya a bordo.
		this.resetFallDistance();
		for (Entity passenger : this.getPassengers()) {
			passenger.resetFallDistance();
		}

		if (!level.isClientSide() && this.portalTicks > 0) {
			this.portalTicks--;
			spawnPortalSpiral((ServerLevel) level, this.getX(), this.getY(), this.getZ(), (PORTAL_TICKS - this.portalTicks) / (double) PORTAL_TICKS, this.modelScale);
		}

		if (level.isClientSide()) {
			this.updateLean();
			this.spawnThrusterParticles(pilot);
		}
	}

	private void flyTick(LivingEntity pilot) {
		Vec3 velocity = this.getDeltaMovement();
		double vx = velocity.x;
		double vy = velocity.y;
		double vz = velocity.z;

		if (pilot != null) {
			double forward = pilot.zza;
			double side = pilot.xxa; // positivo = izquierda
			double sin;
			double cos;
			if (cockpitView.getAsBoolean()) {
				// Vista de cabina: A/D giran la nave y la mirada del piloto queda libre; el piloto gira con la cabina,
				// así que lo que ve no cambia al virar.
				float targetTurn = (float) (-side * TURN_SPEED);
				this.turnVelocity += (targetTurn - this.turnVelocity) * 0.3f;
				if (Math.abs(this.turnVelocity) > 0.01f) {
					this.setYRot(this.getYRot() + this.turnVelocity);
					pilot.setYRot(pilot.getYRot() + this.turnVelocity);
					pilot.yRotO += this.turnVelocity;
				}
				double yawRad = Math.toRadians(this.getYRot());
				sin = Math.sin(yawRad);
				cos = Math.cos(yawRad);
				vx += -sin * forward * ACCELERATION;
				vz += cos * forward * ACCELERATION;
			} else {
				// Tercera persona (como siempre): la nave gira suavemente hacia donde mira el piloto y A/D desplazan de lado.
				this.turnVelocity = 0.0f;
				float diff = Mth.wrapDegrees(pilot.getYRot() - this.getYRot());
				this.setYRot(this.getYRot() + Mth.clamp(diff, -FOLLOW_TURN_SPEED, FOLLOW_TURN_SPEED));
				double yawRad = Math.toRadians(this.getYRot());
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
			vy = Mth.clamp(vy * 0.8 - 0.02, -0.08, 0.1);
		}

		this.setDeltaMovement(vx, vy, vz);
		this.move(MoverType.SELF, this.getDeltaMovement());
	}

	/** Calcula cuánto se inclina la nave según su movimiento (positivo = morro abajo). */
	private void updateLean() {
		double dx = this.getX() - this.xo;
		double dz = this.getZ() - this.zo;
		double yawRad = Math.toRadians(this.getYRot());
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
		double dx = this.getX() - this.xo;
		double dz = this.getZ() - this.zo;
		double speed = Math.hypot(dx, dz);
		if (speed < 0.08 || this.random.nextInt(2) != 0) {
			return;
		}

		double yawRad = Math.toRadians(this.getYRot());
		double sin = Math.sin(yawRad);
		double cos = Math.cos(yawRad);
		for (int sign = -1; sign <= 1; sign += 2) {
			// Boca trasera de los cubos de basura: a ~1,9 bloques del centro, ~0,75 por detrás y a ~1,8 de altura.
			double s = this.modelScale;
			double px = this.getX() + cos * 1.9 * s * sign + (-sin) * -0.8 * s;
			double pz = this.getZ() + sin * 1.9 * s * sign + cos * -0.8 * s;
			this.level().addParticle(ParticleTypes.FLAME, px, this.getY() + 1.8 * s, pz, -dx * 0.3, 0.0, -dz * 0.3);
		}
	}
}
