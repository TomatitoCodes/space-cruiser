package com.spacecruiser.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.function.Function;

/**
 * Capa translúcida para la cúpula SIN escritura de profundidad.
 * Con la capa normal (entityTranslucent), si la nave se dibuja antes que el piloto (el orden depende de cuándo
 * entró cada entidad en el mundo), el cristal tapa al piloto en el buffer de profundidad y no se le ve.
 * Aquí el cristal solo pinta color, así que lo que hay detrás siempre se ve.
 */
public final class GlassRenderLayers extends RenderLayer {
	private GlassRenderLayers() {
		super("unused", VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.QUADS, 256, false, false, () -> {}, () -> {});
		throw new UnsupportedOperationException();
	}

	private static final Function<Identifier, RenderLayer> NO_DEPTH_GLASS = Util.memoize(texture ->
			RenderLayer.of("spacecruiser_glass",
					VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL,
					VertexFormat.DrawMode.QUADS,
					1536,
					RenderLayer.MultiPhaseParameters.builder()
							.program(ENTITY_TRANSLUCENT_PROGRAM)
							.texture(new RenderPhase.Texture(texture, false, false))
							.transparency(TRANSLUCENT_TRANSPARENCY)
							.cull(DISABLE_CULLING)
							.lightmap(ENABLE_LIGHTMAP)
							.overlay(ENABLE_OVERLAY_COLOR)
							.writeMaskState(COLOR_MASK)
							.build(true)));

	public static RenderLayer glass(Identifier texture) {
		return NO_DEPTH_GLASS.apply(texture);
	}
}
