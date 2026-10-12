package com.spacecruiser.client;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;

/**
 * Modelo construido por código (sin archivos .json/.bbmodel).
 *
 * Unidades en píxeles (16 = 1 bloque). Las alturas se expresan como "distancia sobre el suelo"
 * y los helpers se encargan del eje Y invertido del sistema de modelos.
 *
 * Grupos (los usa SpaceCruiserRenderer):
 *  - body:  casco, propulsores, salpicadero (opaco)
 *  - glow:  luces ámbar/amarillas y botones (se renderizan a brillo máximo)
 *  - frame: marco de la cúpula (opaco, se oculta en primera persona)
 *  - dome:  cristal de la cúpula (translúcido, se oculta en primera persona)
 *
 * Textura "space_cruiser.png": 128x256, 8 franjas de 32 px de alto (una por color).
 * Cada pieza apunta a su franja con uv(0, franja * 32).
 */
public final class SpaceCruiserModel {
	public static final int TEXTURE_WIDTH = 128;
	public static final int TEXTURE_HEIGHT = 256;

	private static final int SLOT_HEIGHT = 32;
	private static final int HULL = 0;
	private static final int DARK = 1;
	private static final int YELLOW = 2;
	private static final int ORANGE = 3;
	private static final int POD = 4;
	private static final int FRAME = 5;
	private static final int GREEN = 6;
	private static final int RED = 7;

	private SpaceCruiserModel() {
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData data = new ModelData();
		ModelPartData root = data.getRoot();

		ModelPartData body = root.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0, 24, 0));
		ModelPartData glow = root.addChild("glow", ModelPartBuilder.create(), ModelTransform.pivot(0, 24, 0));
		ModelPartData frame = root.addChild("frame", ModelPartBuilder.create(), ModelTransform.pivot(0, 24, 0));
		ModelPartData dome = root.addChild("dome", ModelPartBuilder.create(), ModelTransform.pivot(0, 24, 0));

		// --- Casco: discos octogonales apilados (anchura, altura sobre el suelo, grosor)
		octagon(body, "pad", 14, 1, 3, DARK);
		octagon(body, "lower", 26, 3, 3, DARK);
		octagon(body, "rim", 38, 5, 3, HULL);
		octagon(body, "deck1", 32, 8, 3, HULL);
		octagon(body, "deck2", 26, 11, 3, HULL);
		octagon(body, "deck3", 22, 14, 1, DARK);
		octagon(body, "dome_base", 22.6f, 15, 1, DARK);

		// --- Franjas luminosas en 8 direcciones, siguiendo los escalones del casco
		for (int k = 0; k < 8; k++) {
			int deg = k * 45;
			strip(glow, "s1_" + k, deg, 16.0f, 19.1f, 3, 7.6f, 1);
			strip(glow, "s2_" + k, deg, 13.0f, 16.1f, 3, 10.6f, 1);
			strip(glow, "s3_" + k, deg, 11.0f, 13.1f, 3, 13.6f, 1);
			strip(glow, "rim_light_" + k, deg, 18.5f, 19.6f, 5, 5.5f, 2);
		}

		// --- Propulsores laterales con luces ámbar "II"
		for (int side = -1; side <= 1; side += 2) {
			String n = side < 0 ? "l" : "r";
			box(body, "pod_" + n, 21.5f * side, 0, 6, 9, 11, 10, POD);
			box(glow, "pod_out_a_" + n, 26.3f * side, -2.4f, 8, 0.8f, 7, 2, ORANGE);
			box(glow, "pod_out_b_" + n, 26.3f * side, 2.4f, 8, 0.8f, 7, 2, ORANGE);
			box(glow, "pod_back_a_" + n, (21.5f - 2.4f) * side, -5.3f, 8, 2.2f, 7, 0.8f, ORANGE);
			box(glow, "pod_back_b_" + n, (21.5f + 2.4f) * side, -5.3f, 8, 2.2f, 7, 0.8f, ORANGE);
		}

		// --- Salpicadero con dos botones
		box(body, "dash", 0, 6.5f, 16, 12, 3, 3, DARK);
		box(glow, "btn_green", -3.5f, 6.5f, 19, 2, 0.8f, 2, GREEN);
		box(glow, "btn_red", 3.5f, 6.5f, 19, 2, 0.8f, 2, RED);

		// --- Cúpula: cuatro capas de cristal que se estrechan, con postes y aros de marco
		glassLayer(dome, frame, "l1", 20, 16, 4);
		glassLayer(dome, frame, "l2", 18, 20, 6);
		glassLayer(dome, frame, "l3", 14, 26, 5);
		glassLayer(dome, frame, "l4", 9, 31, 3);

		return TexturedModelData.of(data, TEXTURE_WIDTH, TEXTURE_HEIGHT);
	}

	/**
	 * Disco octogonal regular: cuatro barras que se cruzan a 0, 45, 90 y 135 grados.
	 * Cada barra mide width x lado, con lado = width / (1 + raiz(2)). Las alturas se escalonan
	 * unas centésimas para evitar parpadeo entre caras coplanares.
	 */
	private static void octagon(ModelPartData parent, String name, float width, float bottom, float height, int slot) {
		float side = width / (1.0f + (float) Math.sqrt(2.0));
		for (int i = 0; i < 4; i++) {
			float h = height - i * 0.06f;
			parent.addChild(
					name + "_" + i,
					ModelPartBuilder.create()
							.uv(0, slot * SLOT_HEIGHT)
							.cuboid(-width / 2f, -h / 2f, -side / 2f, width, h, side),
					ModelTransform.of(0, -(bottom + height / 2f), 0, 0, (float) Math.toRadians(i * 45), 0)
			);
		}
	}

	/** Barra radial orientada hacia el ángulo dado (0 = delante), de r0 a r1 desde el centro. */
	private static void strip(ModelPartData parent, String name, int degrees, float r0, float r1,
							  float width, float bottom, float height) {
		float rad = (float) Math.toRadians(degrees);
		float mid = (r0 + r1) / 2f;
		float length = r1 - r0;
		parent.addChild(
				name,
				ModelPartBuilder.create()
						.uv(0, YELLOW * SLOT_HEIGHT)
						.cuboid(-width / 2f, -height / 2f, -length / 2f, width, height, length),
				ModelTransform.of((float) Math.sin(rad) * mid, -(bottom + height / 2f), (float) Math.cos(rad) * mid, 0, rad, 0)
		);
	}

	/** Caja centrada en (cx, cz), con su cara inferior a "bottom" píxeles del suelo. */
	private static void box(ModelPartData parent, String name, float cx, float cz, float bottom,
							float sx, float sy, float sz, int slot) {
		parent.addChild(
				name,
				ModelPartBuilder.create()
						.uv(0, slot * SLOT_HEIGHT)
						.cuboid(-sx / 2f, -sy / 2f, -sz / 2f, sx, sy, sz),
				ModelTransform.pivot(cx, -(bottom + sy / 2f), cz)
		);
	}

	private static void glassLayer(ModelPartData dome, ModelPartData frame, String name,
								   float size, float bottom, float height) {
		// Cristal (la textura de cristal es lisa, así que todas usan la franja 0).
		box(dome, "glass_" + name, 0, 0, bottom, size, height, size, 0);

		// Postes en las cuatro esquinas.
		float c = size / 2f;
		box(frame, "post_" + name + "_a", c, c, bottom, 1.2f, height, 1.2f, FRAME);
		box(frame, "post_" + name + "_b", -c, c, bottom, 1.2f, height, 1.2f, FRAME);
		box(frame, "post_" + name + "_c", c, -c, bottom, 1.2f, height, 1.2f, FRAME);
		box(frame, "post_" + name + "_d", -c, -c, bottom, 1.2f, height, 1.2f, FRAME);

		// Aro superior de la capa.
		box(frame, "ring_" + name, 0, 0, bottom + height - 0.35f, size + 0.6f, 0.7f, size + 0.6f, FRAME);
	}
}
