package com.spacecruiser.client;

import com.spacecruiser.SpaceCruiserMod;
import com.spacecruiser.entity.SpaceCruiserEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class SpaceCruiserRenderer extends EntityRenderer<SpaceCruiserEntity> {
	private static final Identifier TEXTURE =
			Identifier.of(SpaceCruiserMod.MOD_ID, "textures/entity/space_cruiser.png");
	private static final Identifier GLASS_TEXTURE =
			Identifier.of(SpaceCruiserMod.MOD_ID, "textures/entity/space_cruiser_glass.png");

	private final ModelPart body;
	private final ModelPart glow;
	private final ModelPart frame;
	private final ModelPart dome;
	private final ModelPart battery;
	private final ModelPart bottle;

	public SpaceCruiserRenderer(EntityRendererFactory.Context context, float scale) {
		super(context);
		ModelPart root = context.getPart(SpaceCruiserClient.MODEL_LAYER);
		this.body = root.getChild("body");
		this.glow = root.getChild("glow");
		this.frame = root.getChild("frame");
		this.dome = root.getChild("dome");
		this.battery = root.getChild("bateria");
		this.bottle = root.getChild("botella");
		this.shadowRadius = 2.3f * scale;
	}

	@Override
	public Identifier getTexture(SpaceCruiserEntity entity) {
		return TEXTURE;
	}

	@Override
	public void render(SpaceCruiserEntity entity, float yaw, float tickDelta, MatrixStack matrices,
					   VertexConsumerProvider vertexConsumers, int light) {
		super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);

		MinecraftClient client = MinecraftClient.getInstance();
		boolean hideDome = entity.hasPassenger(client.player) && client.options.getPerspective().isFirstPerson();

		matrices.push();

		// Flotación suave.
		float bob = MathHelper.sin((entity.age + tickDelta) * 0.12f) * 0.03f;
		matrices.translate(0.0, 0.06 + bob, 0.0);

		// Escala de la versión (se aplica desde el suelo, bajo el centro de la nave).
		matrices.scale(entity.modelScale, entity.modelScale, entity.modelScale);

		// Orientación: tras esta rotación, +Z local es el morro de la nave.
		matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-yaw));

		// Inclinación según el movimiento (morro abajo al avanzar, alabeo al desplazarse).
		float pitch = MathHelper.lerp(tickDelta, entity.prevLeanPitch, entity.leanPitch);
		float roll = MathHelper.lerp(tickDelta, entity.prevLeanRoll, entity.leanRoll);
		matrices.translate(0.0, 1.2, 0.0);
		matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(pitch));
		matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(roll));
		matrices.translate(0.0, -1.2, 0.0);

		// Convención de modelos de entidad: eje Y invertido y origen a 24 px del suelo.
		matrices.scale(-1.0f, -1.0f, 1.0f);
		matrices.translate(0.0, -1.5, 0.0);

		VertexConsumer solid = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(TEXTURE));
		body.render(matrices, solid, light, OverlayTexture.DEFAULT_UV);

		// Luces: siempre a brillo máximo, como si emitieran luz.
		glow.render(matrices, solid, LightmapTextureManager.MAX_LIGHT_COORDINATE, OverlayTexture.DEFAULT_UV);

		// Batería del reactor: se encienden tantos pilotos como carga quede (redondeando hacia arriba).
		int lit = (int) Math.ceil(entity.getBattery() - 0.001f);
		for (int i = 0; i < 10; i++) {
			battery.getChild("bat_" + i).visible = i < lit;
		}
		battery.render(matrices, solid, LightmapTextureManager.MAX_LIGHT_COORDINATE, OverlayTexture.DEFAULT_UV);

		// Botella decorativa del suelo: cristal translúcido, visible también desde dentro.
		bottle.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(GLASS_TEXTURE)), light,
				OverlayTexture.DEFAULT_UV);

		if (!hideDome) {
			frame.render(matrices, solid, light, OverlayTexture.DEFAULT_UV);
			VertexConsumer glass = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(GLASS_TEXTURE));
			dome.render(matrices, glass, light, OverlayTexture.DEFAULT_UV);
		}

		matrices.pop();
	}
}
