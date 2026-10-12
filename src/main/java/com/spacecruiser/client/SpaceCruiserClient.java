package com.spacecruiser.client;

import com.spacecruiser.ModEntities;
import com.spacecruiser.SpaceCruiserMod;
import com.spacecruiser.entity.SpaceCruiserEntity;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.util.TypedActionResult;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class SpaceCruiserClient implements ClientModInitializer {
	public static final EntityModelLayer MODEL_LAYER =
			new EntityModelLayer(Identifier.of(SpaceCruiserMod.MOD_ID, "space_cruiser"), "main");

	@Override
	public void onInitializeClient() {
		EntityModelLayerRegistry.registerModelLayer(MODEL_LAYER, SpaceCruiserModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.SPACE_CRUISER,
				ctx -> new SpaceCruiserRenderer(ctx, ModEntities.SCALE));

		KeyBinding descendKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.spacecruiser.descend",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_LEFT_CONTROL,
				"key.categories.movement"
		));

		KeyBinding gpsKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.spacecruiser.gps",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_G,
				"key.categories.movement"
		));

		MinecraftClient client = MinecraftClient.getInstance();

		// Tecla G: abre el GPS estando a bordo.
		ClientTickEvents.END_CLIENT_TICK.register(mc -> {
			while (gpsKey.wasPressed()) {
				if (mc.player != null && mc.currentScreen == null && mc.player.getVehicle() instanceof SpaceCruiserEntity) {
					mc.setScreen(new GpsScreen());
				}
			}
		});

		// Clic derecho mirando a la pantalla de la consola.
		SpaceCruiserEntity.gpsClick = (ship, player) -> {
			if (client.currentScreen == null && ship.isLookingAtScreen(player)) {
				client.setScreen(new GpsScreen());
			}
		};
		UseItemCallback.EVENT.register((player, world, hand) -> {
			if (world.isClient && client.currentScreen == null
					&& player.getVehicle() instanceof SpaceCruiserEntity ship && ship.isLookingAtScreen(player)) {
				client.setScreen(new GpsScreen());
				return TypedActionResult.success(player.getStackInHand(hand));
			}
			return TypedActionResult.pass(player.getStackInHand(hand));
		});
		SpaceCruiserEntity.ascendInput = () -> client.options.jumpKey.isPressed();
		SpaceCruiserEntity.descendInput = descendKey::isPressed;
	}
}
