package com.spacecruiser.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spacecruiser.SpaceCruiserMod;
import com.spacecruiser.entity.SpaceCruiserEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.util.Mth;
import com.mojang.math.Axis;

public class SpaceCruiserRenderer extends EntityRenderer<SpaceCruiserEntity, SpaceCruiserRenderState> {
	private static final Identifier TEXTURE =
			Identifier.fromNamespaceAndPath(SpaceCruiserMod.MOD_ID, "textures/entity/space_cruiser.png");
	private static final Identifier GLASS_TEXTURE =
			Identifier.fromNamespaceAndPath(SpaceCruiserMod.MOD_ID, "textures/entity/space_cruiser_glass.png");
	private static final Identifier BOTTLE_TEXTURE =
			Identifier.fromNamespaceAndPath(SpaceCruiserMod.MOD_ID, "textures/entity/space_cruiser_bottle.png");

	private final ModelPart body;
	private final ModelPart glow;
	private final ModelPart frame;
	private final ModelPart dome;
	private final ModelPart[] battery = new ModelPart[10];
	private final ModelPart bottle;

	public SpaceCruiserRenderer(EntityRendererProvider.Context context, float scale) {
		super(context);
		ModelPart root = context.bakeLayer(SpaceCruiserClient.MODEL_LAYER);
		this.body = root.getChild("body");
		this.glow = root.getChild("glow");
		this.frame = root.getChild("frame");
		this.dome = root.getChild("dome");
		for (int i = 0; i < 10; i++) {
			this.battery[i] = root.getChild("bat_" + i);
		}
		this.bottle = root.getChild("botella");
		this.shadowRadius = 2.3f * scale;
	}

	@Override
	public SpaceCruiserRenderState createRenderState() {
		return new SpaceCruiserRenderState();
	}

	@Override
	public void extractRenderState(SpaceCruiserEntity entity, SpaceCruiserRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		Minecraft client = Minecraft.getInstance();
		state.yRot = entity.getYRot(partialTick);
		state.leanPitch = Mth.lerp(partialTick, entity.prevLeanPitch, entity.leanPitch);
		state.leanRoll = Mth.lerp(partialTick, entity.prevLeanRoll, entity.leanRoll);
		state.modelScale = entity.modelScale;
		state.batteryLit = (int) Math.ceil(entity.getBattery() - 0.001f);
		state.hideDome = client.player != null && entity.hasPassenger(client.player)
				&& client.options.getCameraType().isFirstPerson();
	}

	@Override
	public void submit(SpaceCruiserRenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState camera) {
		super.submit(state, poseStack, collector, camera);

		poseStack.pushPose();

		// Flotación suave.
		float bob = Mth.sin(state.ageInTicks * 0.12f) * 0.03f;
		poseStack.translate(0.0, 0.06 + bob, 0.0);

		// Escala de la versión (se aplica desde el suelo, bajo el centro de la nave).
		poseStack.scale(state.modelScale, state.modelScale, state.modelScale);

		// Orientación: tras esta rotación, +Z local es el morro de la nave.
		poseStack.mulPose(Axis.YP.rotationDegrees(-state.yRot));

		// Inclinación según el movimiento (morro abajo al avanzar, alabeo al desplazarse).
		poseStack.translate(0.0, 1.2, 0.0);
		poseStack.mulPose(Axis.XP.rotationDegrees(state.leanPitch));
		poseStack.mulPose(Axis.ZP.rotationDegrees(state.leanRoll));
		poseStack.translate(0.0, -1.2, 0.0);

		// Convención de modelos de entidad: eje Y invertido y origen a 24 px del suelo.
		poseStack.scale(-1.0f, -1.0f, 1.0f);
		poseStack.translate(0.0, -1.5, 0.0);

		int light = state.lightCoords;
		int overlay = OverlayTexture.NO_OVERLAY;
		RenderType solid = RenderTypes.entityCutout(TEXTURE);

		collector.submitModelPart(body, poseStack, solid, light, overlay, null);

		// Luces: siempre a brillo máximo, como si emitieran luz.
		collector.submitModelPart(glow, poseStack, solid, LightCoordsUtil.FULL_BRIGHT, overlay, null);

		// Batería del reactor: se encienden tantos pilotos como carga quede (redondeando hacia arriba).
		for (int i = 0; i < state.batteryLit && i < 10; i++) {
			collector.submitModelPart(this.battery[i], poseStack, solid, LightCoordsUtil.FULL_BRIGHT, overlay, null);
		}

		// Botella decorativa del suelo: cristal verdoso casi opaco, visible también desde dentro.
		collector.submitModelPart(bottle, poseStack, RenderTypes.entityTranslucent(BOTTLE_TEXTURE), light, overlay, null);

		if (!state.hideDome) {
			collector.submitModelPart(frame, poseStack, solid, light, overlay, null);
			collector.submitModelPart(dome, poseStack, GlassRenderLayers.glass(GLASS_TEXTURE), light, overlay, null);
		}

		poseStack.popPose();
	}
}
