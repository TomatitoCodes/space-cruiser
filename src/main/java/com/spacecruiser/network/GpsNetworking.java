package com.spacecruiser.network;

import com.spacecruiser.entity.SpaceCruiserEntity;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Set;

/** Lado servidor del GPS: recarga de la batería y viaje entre dimensiones. */
public final class GpsNetworking {
	/** Capacidad de la batería, en fire charges. */
	public static final float BATTERY_MAX = 10.0f;
	/** Fire charges que gasta cada viaje (el 35 % de la barra). */
	public static final float TRAVEL_COST = 3.5f;

	private static final int SEARCH_RADIUS = 32;

	private GpsNetworking() {
	}

	public static void register() {
		PayloadTypeRegistry.serverboundPlay().register(GpsActionPayload.TYPE, GpsActionPayload.CODEC);
		ServerPlayNetworking.registerGlobalReceiver(GpsActionPayload.TYPE,
				(payload, context) -> handle(context.player(), payload.action()));
	}

	private static void handle(ServerPlayer player, int action) {
		if (!(player.getVehicle() instanceof SpaceCruiserEntity ship)) {
			return;
		}
		switch (action) {
			case GpsActionPayload.RECHARGE -> recharge(player, ship);
			case GpsActionPayload.TRAVEL_OVERWORLD -> travel(player, ship, Level.OVERWORLD);
			case GpsActionPayload.TRAVEL_NETHER -> travel(player, ship, Level.NETHER);
			case GpsActionPayload.TRAVEL_END -> travel(player, ship, Level.END);
			default -> {
			}
		}
	}

	private static void recharge(ServerPlayer player, SpaceCruiserEntity ship) {
		float battery = ship.getBattery();
		if (battery >= BATTERY_MAX - 0.01f) {
			player.sendSystemMessage(Component.translatable("message.spacecruiser.gps.full"), true);
			return;
		}
		if (player.isCreative()) {
			ship.setBattery(BATTERY_MAX);
			player.sendSystemMessage(Component.translatable("message.spacecruiser.gps.recharged", 0), true);
			return;
		}
		int used = 0;
		Inventory inventory = player.getInventory();
		for (int i = 0; i < inventory.getContainerSize() && battery < BATTERY_MAX; i++) {
			ItemStack stack = inventory.getItem(i);
			while (stack.is(Items.FIRE_CHARGE) && battery < BATTERY_MAX) {
				stack.shrink(1);
				battery = Math.min(BATTERY_MAX, battery + 1.0f);
				used++;
			}
		}
		if (used == 0) {
			player.sendSystemMessage(Component.translatable("message.spacecruiser.gps.no_fire_charges"), true);
			return;
		}
		ship.setBattery(battery);
		player.sendSystemMessage(Component.translatable("message.spacecruiser.gps.recharged", used), true);
	}

	private static void travel(ServerPlayer player, SpaceCruiserEntity ship, ResourceKey<Level> targetKey) {
		ServerLevel from = player.level();
		if (from.dimension() == targetKey) {
			player.sendSystemMessage(Component.translatable("message.spacecruiser.gps.same_dimension"), true);
			return;
		}
		boolean free = player.isCreative();
		if (!free && ship.getBattery() < TRAVEL_COST) {
			player.sendSystemMessage(Component.translatable("message.spacecruiser.gps.low_battery"), true);
			return;
		}
		MinecraftServer server = from.getServer();
		ServerLevel to = server == null ? null : server.getLevel(targetKey);
		if (to == null) {
			return;
		}

		// Mismas coordenadas con la conversión 1:8 del Nether (el End cuenta como Overworld).
		double factor = 1.0;
		if (from.dimension() == Level.NETHER) {
			factor *= 8.0;
		}
		if (targetKey == Level.NETHER) {
			factor /= 8.0;
		}
		double tx = ship.getX() * factor;
		double tz = ship.getZ() * factor;

		EntityDimensions dims = ship.getType().getDimensions();
		Vec3 spot = findSafeSpot(to, tx, ship.getY(), tz, dims);
		if (spot == null) {
			spot = findSafeSpot(to, 0.0, ship.getY(), 0.0, dims);
		}
		if (spot == null) {
			player.sendSystemMessage(Component.translatable("message.spacecruiser.gps.no_spot"), false);
			return;
		}

		float battery = free ? ship.getBattery() : ship.getBattery() - TRAVEL_COST;
		float yaw = ship.getYRot();

		from.playSound(null, ship.getX(), ship.getY(), ship.getZ(),
				SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0f, 0.6f);

		// Espiral verde de salida (la de llegada la anima la nueva nave durante unos ticks).
		for (int i = 0; i <= 20; i++) {
			SpaceCruiserEntity.spawnPortalSpiral(from, ship.getX(), ship.getY(), ship.getZ(), i / 20.0, ship.modelScale);
		}

		// La nave no cambia de dimensión: se crea una igual en destino, con la misma batería, y el piloto se sube a ella.
		SpaceCruiserEntity newShip = (SpaceCruiserEntity) ship.getType().create(to, net.minecraft.world.entity.EntitySpawnReason.TRIGGERED);
		if (newShip == null) {
			return;
		}
		newShip.snapTo(spot.x, spot.y, spot.z, yaw, 0.0f);
		newShip.setBattery(battery);
		newShip.portalTicks = SpaceCruiserEntity.PORTAL_TICKS;

		player.stopRiding();
		ship.discard();
		to.addFreshEntity(newShip);
		player.teleportTo(to, spot.x, spot.y, spot.z, Set.of(), yaw, player.getXRot(), true);
		player.startRiding(newShip, true, true);

		to.playSound(null, spot.x, spot.y, spot.z,
				SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0f, 1.2f);
		player.sendSystemMessage(Component.translatable("message.spacecruiser.gps.arrived"), true);
	}

	/** Busca, alrededor de (x, z), un hueco libre sobre suelo firme y sin líquidos donde quepa la nave. */
	private static Vec3 findSafeSpot(ServerLevel level, double x, double y, double z, EntityDimensions dims) {
		int bottom = level.getMinY() + 1;
		int top = level.getMaxY() + 1 - 4;
		if (level.dimensionType().hasCeiling()) {
			top = Math.min(top, 120);
		}
		// Altura de partida: la de la nave si cabe en la dimensión de destino; si no (p. ej. un mundo plano a y=-58
		// al ir al Nether), una altura típica de esa dimensión.
		int guess = (int) Math.ceil(y) + 8;
		int startY;
		if (guess > bottom + 8 && guess <= top) {
			startY = guess;
		} else {
			int typical = level.dimension() == Level.NETHER ? 64 : level.dimension() == Level.END ? 70 : 80;
			startY = Mth.clamp(typical, bottom + 1, top);
		}
		int cx = Mth.floor(x);
		int cz = Mth.floor(z);

		for (int r = 0; r <= SEARCH_RADIUS; r += 2) {
			for (int dx = -r; dx <= r; dx += 2) {
				for (int dz = -r; dz <= r; dz += 2) {
					if (Math.max(Math.abs(dx), Math.abs(dz)) != r) {
						continue;
					}
					Vec3 spot = groundSpot(level, cx + dx + 0.5, cz + dz + 0.5, startY, bottom, dims);
					if (spot != null) {
						return spot;
					}
				}
			}
		}
		return null;
	}

	private static Vec3 groundSpot(ServerLevel level, double x, double z, int startY, int bottom, EntityDimensions dims) {
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
		for (int gy = startY; gy > bottom; gy--) {
			pos.set(Mth.floor(x), gy - 1, Mth.floor(z));
			BlockState floor = level.getBlockState(pos);
			if (floor.getCollisionShape(level, pos).isEmpty()) {
				continue;
			}
			// Suelo firme en gy - 1: la nave flota 1 bloque por encima.
			double spotY = gy + 1.0;
			AABB box = dims.makeBoundingBox(x, spotY, z);
			if (level.noCollision(box) && !touchesFluid(level, box)) {
				return new Vec3(x, spotY, z);
			}
		}
		return null;
	}

	private static boolean touchesFluid(ServerLevel level, AABB box) {
		BlockPos min = BlockPos.containing(box.minX, box.minY, box.minZ);
		BlockPos max = BlockPos.containing(box.maxX, box.maxY, box.maxZ);
		for (BlockPos p : BlockPos.betweenClosed(min, max)) {
			if (!level.getFluidState(p).isEmpty()) {
				return true;
			}
		}
		return false;
	}
}
