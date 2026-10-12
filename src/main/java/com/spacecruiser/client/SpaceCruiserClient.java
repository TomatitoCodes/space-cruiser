package com.spacecruiser.client;

import com.spacecruiser.ModEntities;
import com.spacecruiser.SpaceCruiserMod;
import com.spacecruiser.entity.SpaceCruiserEntity;
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

		MinecraftClient client = MinecraftClient.getInstance();
		SpaceCruiserEntity.ascendInput = () -> client.options.jumpKey.isPressed();
		SpaceCruiserEntity.descendInput = descendKey::isPressed;
	}
}
