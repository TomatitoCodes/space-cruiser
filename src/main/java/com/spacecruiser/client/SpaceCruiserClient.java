package com.spacecruiser.client;

import com.spacecruiser.ModEntities;
import com.spacecruiser.SpaceCruiserMod;
import com.spacecruiser.entity.SpaceCruiserEntity;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.Perspective;
import net.minecraft.text.Text;
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

		// Cámara de cabina: al subir a la nave se pasa a primera persona (desde dentro se alcanza la consola)
		// y al bajar se recupera la que tenías.
		final Perspective[] saved = {null};
		ClientTickEvents.END_CLIENT_TICK.register(mc -> {
			boolean riding = mc.player != null && mc.player.getVehicle() instanceof SpaceCruiserEntity;
			if (riding && saved[0] == null) {
				saved[0] = mc.options.getPerspective();
				mc.options.setPerspective(Perspective.FIRST_PERSON);
			} else if (!riding && saved[0] != null) {
				mc.options.setPerspective(saved[0]);
				saved[0] = null;
			}
		});

		// Aviso junto a la mirilla cuando apuntas a la pantalla del GPS.
		HudRenderCallback.EVENT.register((ctx, tickCounter) -> {
			if (client.player != null && client.currentScreen == null
					&& client.player.getVehicle() instanceof SpaceCruiserEntity ship
					&& client.options.getPerspective().isFirstPerson() && ship.isLookingAtScreen(client.player)) {
				ctx.drawCenteredTextWithShadow(client.textRenderer, Text.translatable("hud.spacecruiser.gps_hint"),
						ctx.getScaledWindowWidth() / 2, ctx.getScaledWindowHeight() / 2 + 12, 0xFF6EE6FF);
			}
		});
		SpaceCruiserEntity.ascendInput = () -> client.options.jumpKey.isPressed();
		SpaceCruiserEntity.descendInput = descendKey::isPressed;
		SpaceCruiserEntity.cockpitView = () -> client.options.getPerspective().isFirstPerson();
	}
}
