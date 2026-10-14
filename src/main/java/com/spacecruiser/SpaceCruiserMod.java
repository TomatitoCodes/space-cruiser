package com.spacecruiser;

import com.spacecruiser.entity.SpaceCruiserEntity;
import com.spacecruiser.network.GpsNetworking;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.Level;

public class SpaceCruiserMod implements ModInitializer {
	public static final String MOD_ID = "spacecruiser";

	@Override
	public void onInitialize() {
		ModEntities.register();
		ModItems.register();
		GpsNetworking.register();

		// Clic derecho del piloto mirando a la pantalla del GPS: se abre el GPS y el clic no hace nada más
		// (ni colocar bloques en el suelo que queda tras la nave, ni usar el objeto de la mano).
		UseBlockCallback.EVENT.register((player, level, hand, hit) -> gpsClick(player, level));
		UseEntityCallback.EVENT.register((player, level, hand, entity, hit) -> gpsClick(player, level));
		UseItemCallback.EVENT.register((player, level, hand) -> gpsClick(player, level));

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
				.register(output -> {
					output.accept(ModItems.SPACE_CRUISER);
					output.accept(ModItems.REACTOR);
					output.accept(ModItems.HULL);
					output.accept(ModItems.COCKPIT);
					output.accept(ModItems.NAVIGATION_CONSOLE);
				});
	}

	private static InteractionResult gpsClick(Player player, Level level) {
		if (player.getVehicle() instanceof SpaceCruiserEntity ship && ship.isLookingAtScreen(player)) {
			if (level.isClientSide()) {
				SpaceCruiserEntity.gpsClick.accept(ship, player);
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}
}
