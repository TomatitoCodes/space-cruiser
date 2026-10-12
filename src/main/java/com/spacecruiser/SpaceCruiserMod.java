package com.spacecruiser;

import com.spacecruiser.network.GpsNetworking;
import net.fabricmc.api.ModInitializer;
import com.spacecruiser.entity.SpaceCruiserEntity;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.item.ItemGroups;

public class SpaceCruiserMod implements ModInitializer {
	public static final String MOD_ID = "spacecruiser";

	@Override
	public void onInitialize() {
		ModEntities.register();
		ModItems.register();
		GpsNetworking.register();

		// Clic derecho del piloto mirando a la pantalla del GPS: se abre el GPS y el clic no hace nada más
		// (ni colocar bloques en el suelo que queda tras la nave, ni usar el objeto de la mano).
		UseBlockCallback.EVENT.register((player, world, hand, hit) -> gpsClick(player, world));
		UseEntityCallback.EVENT.register((player, world, hand, entity, hit) -> gpsClick(player, world));
		UseItemCallback.EVENT.register((player, world, hand) -> {
			ActionResult result = gpsClick(player, world);
			return result == ActionResult.PASS
					? TypedActionResult.pass(player.getStackInHand(hand))
					: TypedActionResult.success(player.getStackInHand(hand));
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
				.register(entries -> {
					entries.add(ModItems.SPACE_CRUISER);
					entries.add(ModItems.REACTOR);
					entries.add(ModItems.HULL);
					entries.add(ModItems.COCKPIT);
					entries.add(ModItems.NAVIGATION_CONSOLE);
				});
	}

	private static ActionResult gpsClick(PlayerEntity player, World world) {
		if (player.getVehicle() instanceof SpaceCruiserEntity ship && ship.isLookingAtScreen(player)) {
			if (world.isClient) {
				SpaceCruiserEntity.gpsClick.accept(ship, player);
			}
			return ActionResult.SUCCESS;
		}
		return ActionResult.PASS;
	}
}
