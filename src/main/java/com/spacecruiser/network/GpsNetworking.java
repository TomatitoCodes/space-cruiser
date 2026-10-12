package com.spacecruiser.network;

import com.spacecruiser.entity.SpaceCruiserEntity;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.block.BlockState;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

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
		PayloadTypeRegistry.playC2S().register(GpsActionPayload.ID, GpsActionPayload.CODEC);
		ServerPlayNetworking.registerGlobalReceiver(GpsActionPayload.ID,
				(payload, context) -> handle(context.player(), payload.action()));
	}

	private static void handle(ServerPlayerEntity player, int action) {
		if (!(player.getVehicle() instanceof SpaceCruiserEntity ship)) {
			return;
		}
		switch (action) {
			case GpsActionPayload.RECHARGE -> recharge(player, ship);
			case GpsActionPayload.TRAVEL_OVERWORLD -> travel(player, ship, World.OVERWORLD);
			case GpsActionPayload.TRAVEL_NETHER -> travel(player, ship, World.NETHER);
			case GpsActionPayload.TRAVEL_END -> travel(player, ship, World.END);
			default -> {
			}
		}
	}

	private static void recharge(ServerPlayerEntity player, SpaceCruiserEntity ship) {
		float battery = ship.getBattery();
		if (battery >= BATTERY_MAX - 0.01f) {
			player.sendMessage(Text.translatable("message.spacecruiser.gps.full"), true);
			return;
		}
		if (player.isCreative()) {
			ship.setBattery(BATTERY_MAX);
			player.sendMessage(Text.translatable("message.spacecruiser.gps.recharged", 0), true);
			return;
		}
		int used = 0;
		PlayerInventory inventory = player.getInventory();
		for (int i = 0; i < inventory.size() && battery < BATTERY_MAX; i++) {
			ItemStack stack = inventory.getStack(i);
			while (stack.isOf(Items.FIRE_CHARGE) && battery < BATTERY_MAX) {
				stack.decrement(1);
				battery = Math.min(BATTERY_MAX, battery + 1.0f);
				used++;
			}
		}
		if (used == 0) {
			player.sendMessage(Text.translatable("message.spacecruiser.gps.no_fire_charges"), true);
			return;
		}
		ship.setBattery(battery);
		player.sendMessage(Text.translatable("message.spacecruiser.gps.recharged", used), true);
	}

	private static void travel(ServerPlayerEntity player, SpaceCruiserEntity ship, RegistryKey<World> targetKey) {
		ServerWorld from = player.getServerWorld();
		if (from.getRegistryKey() == targetKey) {
			player.sendMessage(Text.translatable("message.spacecruiser.gps.same_dimension"), true);
			return;
		}
		boolean free = player.isCreative();
		if (!free && ship.getBattery() < TRAVEL_COST) {
			player.sendMessage(Text.translatable("message.spacecruiser.gps.low_battery"), true);
			return;
		}
		MinecraftServer server = player.getServer();
		ServerWorld to = server == null ? null : server.getWorld(targetKey);
		if (to == null) {
			return;
		}

		// Mismas coordenadas con la conversión 1:8 del Nether (el End cuenta como Overworld).
		double factor = 1.0;
		if (from.getRegistryKey() == World.NETHER) {
			factor *= 8.0;
		}
		if (targetKey == World.NETHER) {
			factor /= 8.0;
		}
		double tx = ship.getX() * factor;
		double tz = ship.getZ() * factor;

		EntityDimensions dims = ship.getType().getDimensions();
		Vec3d spot = findSafeSpot(to, tx, ship.getY(), tz, dims);
		if (spot == null) {
			spot = findSafeSpot(to, 0.0, ship.getY(), 0.0, dims);
		}
		if (spot == null) {
			player.sendMessage(Text.translatable("message.spacecruiser.gps.no_spot"), false);
			return;
		}

		float battery = free ? ship.getBattery() : ship.getBattery() - TRAVEL_COST;
		float yaw = ship.getYaw();

		from.playSound(null, ship.getX(), ship.getY(), ship.getZ(),
				SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0f, 0.6f);

		// Espiral verde de salida (la de llegada la anima la nueva nave durante unos ticks).
		for (int i = 0; i <= 20; i++) {
			SpaceCruiserEntity.spawnPortalSpiral(from, ship.getX(), ship.getY(), ship.getZ(), i / 20.0, ship.modelScale);
		}

		// La nave no cambia de dimensión: se crea una igual en destino, con la misma batería, y el piloto se sube a ella.
		SpaceCruiserEntity newShip = (SpaceCruiserEntity) ship.getType().create(to);
		if (newShip == null) {
			return;
		}
		newShip.refreshPositionAndAngles(spot.x, spot.y, spot.z, yaw, 0.0f);
		newShip.setBattery(battery);
		newShip.portalTicks = SpaceCruiserEntity.PORTAL_TICKS;

		player.stopRiding();
		ship.discard();
		to.spawnEntity(newShip);
		player.teleport(to, spot.x, spot.y, spot.z, yaw, player.getPitch());
		player.startRiding(newShip, true);

		to.playSound(null, spot.x, spot.y, spot.z,
				SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0f, 1.2f);
		player.sendMessage(Text.translatable("message.spacecruiser.gps.arrived"), true);
	}

	/** Busca, alrededor de (x, z), un hueco libre sobre suelo firme y sin líquidos donde quepa la nave. */
	private static Vec3d findSafeSpot(ServerWorld world, double x, double y, double z, EntityDimensions dims) {
		int bottom = world.getBottomY() + 1;
		int top = world.getTopY() - 4;
		if (world.getDimension().hasCeiling()) {
			top = Math.min(top, 120);
		}
		// Altura de partida: la de la nave si cabe en la dimensión de destino; si no (p. ej. un mundo plano a y=-58
		// al ir al Nether), una altura típica de esa dimensión.
		int guess = (int) Math.ceil(y) + 8;
		int startY;
		if (guess > bottom + 8 && guess <= top) {
			startY = guess;
		} else {
			int typical = world.getRegistryKey() == World.NETHER ? 64 : world.getRegistryKey() == World.END ? 70 : 80;
			startY = MathHelper.clamp(typical, bottom + 1, top);
		}
		int cx = MathHelper.floor(x);
		int cz = MathHelper.floor(z);

		for (int r = 0; r <= SEARCH_RADIUS; r += 2) {
			for (int dx = -r; dx <= r; dx += 2) {
				for (int dz = -r; dz <= r; dz += 2) {
					if (Math.max(Math.abs(dx), Math.abs(dz)) != r) {
						continue;
					}
					Vec3d spot = groundSpot(world, cx + dx + 0.5, cz + dz + 0.5, startY, bottom, dims);
					if (spot != null) {
						return spot;
					}
				}
			}
		}
		return null;
	}

	private static Vec3d groundSpot(ServerWorld world, double x, double z, int startY, int bottom, EntityDimensions dims) {
		BlockPos.Mutable pos = new BlockPos.Mutable();
		for (int gy = startY; gy > bottom; gy--) {
			pos.set(MathHelper.floor(x), gy - 1, MathHelper.floor(z));
			BlockState floor = world.getBlockState(pos);
			if (floor.getCollisionShape(world, pos).isEmpty()) {
				continue;
			}
			// Suelo firme en gy - 1: la nave flota 1 bloque por encima.
			double spotY = gy + 1.0;
			Box box = dims.getBoxAt(x, spotY, z);
			if (world.isSpaceEmpty(box) && !touchesFluid(world, box)) {
				return new Vec3d(x, spotY, z);
			}
		}
		return null;
	}

	private static boolean touchesFluid(ServerWorld world, Box box) {
		BlockPos min = BlockPos.ofFloored(box.minX, box.minY, box.minZ);
		BlockPos max = BlockPos.ofFloored(box.maxX, box.maxY, box.maxZ);
		for (BlockPos p : BlockPos.iterate(min, max)) {
			if (!world.getFluidState(p).isEmpty()) {
				return true;
			}
		}
		return false;
	}
}
