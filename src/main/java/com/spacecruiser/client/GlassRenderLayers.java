package com.spacecruiser.client;

import com.mojang.renderpearl.api.pipeline.BindGroupLayout;
import com.mojang.renderpearl.api.pipeline.BlendFunction;
import com.mojang.renderpearl.api.pipeline.ColorTargetState;
import com.mojang.renderpearl.api.pipeline.CompareOp;
import com.mojang.renderpearl.api.pipeline.DepthStencilState;
import com.mojang.renderpearl.api.pipeline.RenderPipeline;
import com.spacecruiser.SpaceCruiserMod;
import net.minecraft.client.renderer.BindGroupLayouts;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;

import java.util.function.Function;

/**
 * Capa translúcida para la cúpula SIN escritura de profundidad.
 * Con la capa normal (entityTranslucent), si la nave se dibuja antes que el piloto (el orden depende de cuándo
 * entró cada entidad en el mundo), el cristal tapa al piloto en el buffer de profundidad y no se le ve.
 * Aquí el cristal solo pinta color, así que lo que hay detrás siempre se ve.
 */
public final class GlassRenderLayers {
	private GlassRenderLayers() {
	}

	/** Igual que la pipeline de entidades translúcidas de vanilla, pero sin escribir en el buffer de profundidad. */
	private static final RenderPipeline NO_DEPTH_GLASS_PIPELINE = RenderPipelines.register(
			RenderPipeline.builder(RenderPipelines.ENTITY_SNIPPET)
					.withLocation(Identifier.fromNamespaceAndPath(SpaceCruiserMod.MOD_ID, "pipeline/glass"))
					.withShaderDefine("ALPHA_CUTOUT", 0.1F)
					.withShaderDefine("PER_FACE_LIGHTING")
					.withBindGroupLayout(BindGroupLayouts.SAMPLER1)
					.withColorTargetState(new ColorTargetState(BlendFunction.TRANSLUCENT))
					.withCull(false)
					.withDepthStencilState(new DepthStencilState(CompareOp.GREATER_THAN_OR_EQUAL, false))
					.build());

	private static final Function<Identifier, RenderType> NO_DEPTH_GLASS = Util.memoize(texture ->
			RenderType.create("spacecruiser_glass",
					RenderSetup.builder(NO_DEPTH_GLASS_PIPELINE)
							.withTexture("Sampler0", texture)
							.useLightmap()
							.useOverlay()
							.sortOnUpload()
							.createRenderSetup()));

	public static RenderType glass(Identifier texture) {
		return NO_DEPTH_GLASS.apply(texture);
	}

	/** Fuerza la carga de la clase (y con ella el registro de la pipeline) antes de que arranque el render. */
	public static void init() {
	}
}
