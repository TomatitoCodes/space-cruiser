package com.spacecruiser.client;

import com.spacecruiser.ModEntities;
import com.spacecruiser.SpaceCruiserMod;
import com.spacecruiser.entity.SpaceCruiserEntity;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.CameraType;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class SpaceCruiserClient implements ClientModInitializer {
	public static final ModelLayerLocation MODEL_LAYER =
			new ModelLayerLocation(Identifier.fromNamespaceAndPath(SpaceCruiserMod.MOD_ID, "space_cruiser"), "main");

	@Override
	public void onInitializeClient() {
		GlassRenderLayers.init();
		ModelLayerRegistry.registerModelLayer(MODEL_LAYER, SpaceCruiserModel::createLayer);
		EntityRendererRegistry.register(ModEntities.SPACE_CRUISER,
				ctx -> new SpaceCruiserRenderer(ctx, ModEntities.SCALE));

		KeyMapping descendKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
				"key.spacecruiser.descend",
				InputConstants.KEY_LCONTROL,
				KeyMapping.Category.MOVEMENT
		));

		KeyMapping gpsKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
				"key.spacecruiser.gps",
				InputConstants.KEY_G,
				KeyMapping.Category.MOVEMENT
		));

		Minecraft client = Minecraft.getInstance();

		// Tecla G: abre el GPS estando a bordo.
		ClientTickEvents.END_CLIENT_TICK.register(mc -> {
			while (gpsKey.consumeClick()) {
				if (mc.player != null && mc.gui.screen() == null && mc.player.getVehicle() instanceof SpaceCruiserEntity) {
					mc.gui.setScreen(new GpsScreen());
				}
			}
		});

		// Clic derecho mirando a la pantalla de la consola.
		SpaceCruiserEntity.gpsClick = (ship, player) -> {
			if (client.gui.screen() == null && ship.isLookingAtScreen(player)) {
				client.gui.setScreen(new GpsScreen());
			}
		};

		// Cámara de cabina: al subir a la nave se pasa a primera persona (desde dentro se alcanza la consola)
		// y al bajar se recupera la que tenías.
		final CameraType[] saved = {null};
		ClientTickEvents.END_CLIENT_TICK.register(mc -> {
			boolean riding = mc.player != null && mc.player.getVehicle() instanceof SpaceCruiserEntity;
			if (riding && saved[0] == null) {
				saved[0] = mc.options.getCameraType();
				mc.options.setCameraType(CameraType.FIRST_PERSON);
			} else if (!riding && saved[0] != null) {
				mc.options.setCameraType(saved[0]);
				saved[0] = null;
			}
		});

		// Aviso junto a la mirilla cuando apuntas a la pantalla del GPS.
		HudElementRegistry.addLast(Identifier.fromNamespaceAndPath(SpaceCruiserMod.MOD_ID, "gps_hint"), (graphics, deltaTracker) -> {
			if (client.player != null && client.gui.screen() == null
					&& client.player.getVehicle() instanceof SpaceCruiserEntity ship
					&& client.options.getCameraType().isFirstPerson() && ship.isLookingAtScreen(client.player)) {
				graphics.centeredText(client.font, Component.translatable("hud.spacecruiser.gps_hint"),
						graphics.guiWidth() / 2, graphics.guiHeight() / 2 + 12, 0xFF6EE6FF);
			}
		});
		SpaceCruiserEntity.ascendInput = () -> client.options.keyJump.isDown();
		SpaceCruiserEntity.descendInput = descendKey::isDown;
		SpaceCruiserEntity.cockpitView = () -> client.options.getCameraType().isFirstPerson();
	}
}
