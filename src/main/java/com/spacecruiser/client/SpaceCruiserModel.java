package com.spacecruiser.client;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;

/**
 * GENERADO por tools/generate_model.py a partir del modelo de Blockbench. No editar a mano:
 * cambia el script (o el modelo) y vuelve a generarlo.
 *
 * Unidades en píxeles (16 = 1 bloque). Origen en el suelo, centro de la nave en X/Z, morro en +Z.
 *
 * Grupos (los usa SpaceCruiserRenderer):
 *  - body:  casco, cabina, propulsores (opaco)
 *  - glow:  franjas luminosas y fuego de los propulsores (brillo máximo)
 *  - frame: postes y tapa de la cúpula (opaco, se oculta en primera persona)
 *  - dome:  cristal de la cúpula (translúcido, se oculta en primera persona)
 *
 * Textura "space_cruiser.png": atlas de 512x512 con un rectángulo plano de color por cubo.
 */
public final class SpaceCruiserModel {
	public static final int TEXTURE_WIDTH = 512;
	public static final int TEXTURE_HEIGHT = 512;

	private SpaceCruiserModel() {
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData data = new ModelData();
		ModelPartData root = data.getRoot();

		ModelPartData body = root.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0, 24, 0));
		ModelPartData glow = root.addChild("glow", ModelPartBuilder.create(), ModelTransform.pivot(0, 24, 0));
		ModelPartData frame = root.addChild("frame", ModelPartBuilder.create(), ModelTransform.pivot(0, 24, 0));
		ModelPartData dome = root.addChild("dome", ModelPartBuilder.create(), ModelTransform.pivot(0, 24, 0));

		// --- body
		c(body, "fondo_0", 1, 30, -19.5f, -5.5f, -8.0772f, 39.0f, 5.5f, 16.1543f, 0.0f, -8.5f, 0.0f, 0.0f, 0.0f);
		c(body, "fondo_1", 391, 1, -19.5f, -5.48f, -8.0772f, 39.0f, 5.46f, 16.1543f, 0.0f, -8.5f, 0.0f, 0.0f, -45.0f);
		c(body, "fondo_2", 278, 1, -19.5f, -5.46f, -8.0772f, 39.0f, 5.42f, 16.1543f, 0.0f, -8.5f, 0.0f, 0.0f, -90.0f);
		c(body, "fondo_3", 165, 1, -19.5f, -5.44f, -8.0772f, 39.0f, 5.38f, 16.1543f, 0.0f, -8.5f, 0.0f, 0.0f, -135.0f);
		c(body, "asiento_base", 419, 93, -5.0f, -16.0f, -4.0f, 10.0f, 2.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_arriba_izq", 300, 30, -36.0f, -37.0f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_abajo_izq", 238, 30, -36.0f, -26.5f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_interior_izq", 124, 1, -25.5f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_exterior_izq", 83, 1, -36.0f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tapa_delantera_izq", 77, 75, -37.0f, -38.0f, 9.0f, 14.0f, 14.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "asa_delantera_izq", 335, 280, -32.0f, -32.0f, 11.0f, 4.0f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_arriba_izq", 290, 280, -37.0f, -38.0f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_abajo_izq", 257, 280, -37.0f, -25.5f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_interior_izq", 25, 93, -24.5f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_exterior_izq", 17, 93, -37.0f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "soporte_izq", 55, 93, -24.0f, -34.0f, -3.0f, 4.0f, 6.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_arriba_der", 176, 30, 24.0f, -37.0f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_abajo_der", 114, 30, 24.0f, -26.5f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_interior_der", 42, 1, 24.0f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_exterior_der", 1, 1, 34.5f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tapa_delantera_der", 43, 75, 23.0f, -38.0f, 9.0f, 14.0f, 14.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "asa_delantera_der", 323, 280, 28.0f, -32.0f, 11.0f, 4.0f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_arriba_der", 224, 280, 23.0f, -38.0f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_abajo_der", 191, 280, 23.0f, -25.5f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_interior_der", 9, 93, 23.0f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_exterior_der", 1, 93, 35.5f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "soporte_der", 33, 93, 20.0f, -34.0f, -3.0f, 4.0f, 6.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_0", 189, 75, -17.0f, -10.0f, 11.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_1", 163, 75, 11.0f, -10.0f, 11.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_2", 137, 75, -17.0f, -10.0f, -17.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_3", 111, 75, 11.0f, -10.0f, -17.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado0 = body.addChild("lado_0", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(0), 0));
		c(body_lado0, "pend_sup_0_0", 190, 173, -13.8952f, 0.0f, -2.4777f, 27.7904f, 2.0f, 2.4777f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		c(body_lado0, "pend_sup_1_0", 178, 180, -12.9287f, 0.0f, -2.4777f, 25.8574f, 2.0f, 2.4777f, 0.0f, -17.8333f, 33.6667f, -19.6538f, 0.0f);
		c(body_lado0, "pend_sup_2_0", 111, 187, -11.9622f, 0.0f, -2.4777f, 23.9244f, 2.0f, 2.4777f, 0.0f, -18.6667f, 31.3333f, -19.6538f, 0.0f);
		c(body_lado0, "pend_sup_3_0", 52, 194, -10.9957f, 0.0f, -2.4777f, 21.9914f, 2.0f, 2.4777f, 0.0f, -19.5f, 29.0f, -19.6538f, 0.0f);
		c(body_lado0, "pend_sup_4_0", 439, 194, -10.0292f, 0.0f, -2.4777f, 20.0584f, 2.0f, 2.4777f, 0.0f, -20.3333f, 26.6667f, -19.6538f, 0.0f);
		c(body_lado0, "pend_sup_5_0", 309, 201, -9.0627f, 0.0f, -2.4777f, 18.1254f, 2.0f, 2.4777f, 0.0f, -21.1667f, 24.3333f, -19.6538f, 0.0f);
		c(body_lado0, "pend_inf_0_0", 442, 117, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_1_0", 407, 125, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_2_0", 372, 133, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_3_0", 289, 141, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_4_0", 130, 149, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_5_0", 439, 149, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado0, "soporte_sup_0_0", 403, 214, -14.9117f, -14.7263f, 33.6667f, 29.8234f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado0, "soporte_sup_1_0", 442, 226, -13.9452f, -15.5596f, 31.3333f, 27.8904f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado0, "soporte_sup_2_0", 414, 232, -12.9787f, -16.3929f, 29.0f, 25.9574f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado0, "soporte_sup_3_0", 386, 244, -12.0122f, -17.2263f, 26.6667f, 24.0244f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado0, "soporte_sup_4_0", 256, 256, -11.0457f, -18.0596f, 24.3333f, 22.0914f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado0, "soporte_sup_5_0", 48, 268, -10.0792f, -18.8929f, 22.0f, 20.1584f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado0, "soporte_inf_0_0", 341, 208, -14.9117f, -15.1988f, 33.0f, 29.8234f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado0, "soporte_inf_1_0", 379, 226, -13.669f, -14.5321f, 30.0f, 27.3381f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado0, "soporte_inf_2_0", 407, 238, -12.4264f, -13.8655f, 27.0f, 24.8528f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado0, "soporte_inf_3_0", 319, 250, -11.1838f, -13.1988f, 24.0f, 22.3675f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado0, "soporte_inf_4_0", 145, 262, -9.9411f, -12.5321f, 21.0f, 19.8823f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado0, "soporte_inf_5_0", 396, 268, -8.6985f, -11.8655f, 18.0f, 17.397f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado0, "borde_0", 449, 108, -14.4975f, -17.0f, 34.0f, 28.9949f, 5.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado0, "anillo_plano_0_0", 353, 274, -8.9056f, -22.0f, 21.0f, 17.8112f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado0, "anillo_plano_1_0", 153, 280, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado0, "borde_cupula_0", 474, 75, -7.9736f, -23.5f, 18.5f, 15.9472f, 11.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado1 = body.addChild("lado_1", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-45), 0));
		c(body_lado1, "pend_sup_0_1", 127, 173, -13.8952f, 0.0f, -2.4777f, 27.7904f, 2.0f, 2.4777f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		c(body_lado1, "pend_sup_1_1", 119, 180, -12.9287f, 0.0f, -2.4777f, 25.8574f, 2.0f, 2.4777f, 0.0f, -17.8333f, 33.6667f, -19.6538f, 0.0f);
		c(body_lado1, "pend_sup_2_1", 56, 187, -11.9622f, 0.0f, -2.4777f, 23.9244f, 2.0f, 2.4777f, 0.0f, -18.6667f, 31.3333f, -19.6538f, 0.0f);
		c(body_lado1, "pend_sup_3_1", 1, 194, -10.9957f, 0.0f, -2.4777f, 21.9914f, 2.0f, 2.4777f, 0.0f, -19.5f, 29.0f, -19.6538f, 0.0f);
		c(body_lado1, "pend_sup_4_1", 391, 194, -10.0292f, 0.0f, -2.4777f, 20.0584f, 2.0f, 2.4777f, 0.0f, -20.3333f, 26.6667f, -19.6538f, 0.0f);
		c(body_lado1, "pend_sup_5_1", 265, 201, -9.0627f, 0.0f, -2.4777f, 18.1254f, 2.0f, 2.4777f, 0.0f, -21.1667f, 24.3333f, -19.6538f, 0.0f);
		c(body_lado1, "pend_inf_0_1", 379, 117, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_1_1", 349, 125, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_2_1", 319, 133, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_3_1", 241, 141, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_4_1", 87, 149, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_5_1", 401, 149, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado1, "soporte_sup_0_1", 336, 214, -14.9117f, -14.7263f, 33.6667f, 29.8234f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado1, "soporte_sup_1_1", 316, 226, -13.9452f, -15.5596f, 31.3333f, 27.8904f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado1, "soporte_sup_2_1", 355, 232, -12.9787f, -16.3929f, 29.0f, 25.9574f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado1, "soporte_sup_3_1", 331, 244, -12.0122f, -17.2263f, 26.6667f, 24.0244f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado1, "soporte_sup_4_1", 205, 256, -11.0457f, -18.0596f, 24.3333f, 22.0914f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado1, "soporte_sup_5_1", 1, 268, -10.0792f, -18.8929f, 22.0f, 20.1584f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado1, "soporte_inf_0_1", 273, 208, -14.9117f, -15.1988f, 33.0f, 29.8234f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado1, "soporte_inf_1_1", 253, 226, -13.669f, -14.5321f, 30.0f, 27.3381f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado1, "soporte_inf_2_1", 349, 238, -12.4264f, -13.8655f, 27.0f, 24.8528f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado1, "soporte_inf_3_1", 266, 250, -11.1838f, -13.1988f, 24.0f, 22.3675f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado1, "soporte_inf_4_1", 97, 262, -9.9411f, -12.5321f, 21.0f, 19.8823f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado1, "soporte_inf_5_1", 353, 268, -8.6985f, -11.8655f, 18.0f, 17.397f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado1, "borde_1", 385, 108, -14.4975f, -17.0f, 34.0f, 28.9949f, 5.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado1, "anillo_plano_0_1", 313, 274, -8.9056f, -22.0f, 21.0f, 17.8112f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado1, "anillo_plano_1_1", 115, 280, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado1, "borde_cupula_1", 437, 75, -7.9736f, -23.5f, 18.5f, 15.9472f, 11.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado2 = body.addChild("lado_2", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-90), 0));
		c(body_lado2, "pend_sup_0_2", 64, 173, -13.8952f, 0.0f, -2.4777f, 27.7904f, 2.0f, 2.4777f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		c(body_lado2, "pend_sup_1_2", 60, 180, -12.9287f, 0.0f, -2.4777f, 25.8574f, 2.0f, 2.4777f, 0.0f, -17.8333f, 33.6667f, -19.6538f, 0.0f);
		c(body_lado2, "pend_sup_2_2", 1, 187, -11.9622f, 0.0f, -2.4777f, 23.9244f, 2.0f, 2.4777f, 0.0f, -18.6667f, 31.3333f, -19.6538f, 0.0f);
		c(body_lado2, "pend_sup_3_2", 421, 187, -10.9957f, 0.0f, -2.4777f, 21.9914f, 2.0f, 2.4777f, 0.0f, -19.5f, 29.0f, -19.6538f, 0.0f);
		c(body_lado2, "pend_sup_4_2", 343, 194, -10.0292f, 0.0f, -2.4777f, 20.0584f, 2.0f, 2.4777f, 0.0f, -20.3333f, 26.6667f, -19.6538f, 0.0f);
		c(body_lado2, "pend_sup_5_2", 221, 201, -9.0627f, 0.0f, -2.4777f, 18.1254f, 2.0f, 2.4777f, 0.0f, -21.1667f, 24.3333f, -19.6538f, 0.0f);
		c(body_lado2, "pend_inf_0_2", 316, 117, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_1_2", 291, 125, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_2_2", 266, 133, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_3_2", 193, 141, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_4_2", 44, 149, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_5_2", 363, 149, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado2, "soporte_sup_0_2", 269, 214, -14.9117f, -14.7263f, 33.6667f, 29.8234f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado2, "soporte_sup_1_2", 190, 226, -13.9452f, -15.5596f, 31.3333f, 27.8904f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado2, "soporte_sup_2_2", 296, 232, -12.9787f, -16.3929f, 29.0f, 25.9574f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado2, "soporte_sup_3_2", 276, 244, -12.0122f, -17.2263f, 26.6667f, 24.0244f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado2, "soporte_sup_4_2", 154, 256, -11.0457f, -18.0596f, 24.3333f, 22.0914f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado2, "soporte_sup_5_2", 428, 262, -10.0792f, -18.8929f, 22.0f, 20.1584f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado2, "soporte_inf_0_2", 205, 208, -14.9117f, -15.1988f, 33.0f, 29.8234f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado2, "soporte_inf_1_2", 127, 226, -13.669f, -14.5321f, 30.0f, 27.3381f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado2, "soporte_inf_2_2", 291, 238, -12.4264f, -13.8655f, 27.0f, 24.8528f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado2, "soporte_inf_3_2", 213, 250, -11.1838f, -13.1988f, 24.0f, 22.3675f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado2, "soporte_inf_4_2", 49, 262, -9.9411f, -12.5321f, 21.0f, 19.8823f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado2, "soporte_inf_5_2", 310, 268, -8.6985f, -11.8655f, 18.0f, 17.397f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado2, "borde_2", 321, 108, -14.4975f, -17.0f, 34.0f, 28.9949f, 5.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado2, "anillo_plano_0_2", 273, 274, -8.9056f, -22.0f, 21.0f, 17.8112f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado2, "anillo_plano_1_2", 77, 280, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado2, "borde_cupula_2", 400, 75, -7.9736f, -23.5f, 18.5f, 15.9472f, 11.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado3 = body.addChild("lado_3", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-135), 0));
		c(body_lado3, "pend_sup_0_3", 1, 173, -13.8952f, 0.0f, -2.4777f, 27.7904f, 2.0f, 2.4777f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		c(body_lado3, "pend_sup_1_3", 1, 180, -12.9287f, 0.0f, -2.4777f, 25.8574f, 2.0f, 2.4777f, 0.0f, -17.8333f, 33.6667f, -19.6538f, 0.0f);
		c(body_lado3, "pend_sup_2_3", 457, 180, -11.9622f, 0.0f, -2.4777f, 23.9244f, 2.0f, 2.4777f, 0.0f, -18.6667f, 31.3333f, -19.6538f, 0.0f);
		c(body_lado3, "pend_sup_3_3", 370, 187, -10.9957f, 0.0f, -2.4777f, 21.9914f, 2.0f, 2.4777f, 0.0f, -19.5f, 29.0f, -19.6538f, 0.0f);
		c(body_lado3, "pend_sup_4_3", 295, 194, -10.0292f, 0.0f, -2.4777f, 20.0584f, 2.0f, 2.4777f, 0.0f, -20.3333f, 26.6667f, -19.6538f, 0.0f);
		c(body_lado3, "pend_sup_5_3", 177, 201, -9.0627f, 0.0f, -2.4777f, 18.1254f, 2.0f, 2.4777f, 0.0f, -21.1667f, 24.3333f, -19.6538f, 0.0f);
		c(body_lado3, "pend_inf_0_3", 253, 117, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_1_3", 233, 125, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_2_3", 213, 133, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_3_3", 145, 141, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_4_3", 1, 149, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_5_3", 325, 149, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado3, "soporte_sup_0_3", 202, 214, -14.9117f, -14.7263f, 33.6667f, 29.8234f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado3, "soporte_sup_1_3", 64, 226, -13.9452f, -15.5596f, 31.3333f, 27.8904f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado3, "soporte_sup_2_3", 237, 232, -12.9787f, -16.3929f, 29.0f, 25.9574f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado3, "soporte_sup_3_3", 221, 244, -12.0122f, -17.2263f, 26.6667f, 24.0244f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado3, "soporte_sup_4_3", 103, 256, -11.0457f, -18.0596f, 24.3333f, 22.0914f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado3, "soporte_sup_5_3", 381, 262, -10.0792f, -18.8929f, 22.0f, 20.1584f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado3, "soporte_inf_0_3", 137, 208, -14.9117f, -15.1988f, 33.0f, 29.8234f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado3, "soporte_inf_1_3", 1, 226, -13.669f, -14.5321f, 30.0f, 27.3381f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado3, "soporte_inf_2_3", 233, 238, -12.4264f, -13.8655f, 27.0f, 24.8528f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado3, "soporte_inf_3_3", 160, 250, -11.1838f, -13.1988f, 24.0f, 22.3675f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado3, "soporte_inf_4_3", 1, 262, -9.9411f, -12.5321f, 21.0f, 19.8823f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado3, "soporte_inf_5_3", 267, 268, -8.6985f, -11.8655f, 18.0f, 17.397f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado3, "borde_3", 257, 108, -14.4975f, -17.0f, 34.0f, 28.9949f, 5.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado3, "anillo_plano_0_3", 233, 274, -8.9056f, -22.0f, 21.0f, 17.8112f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado3, "anillo_plano_1_3", 39, 280, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado3, "borde_cupula_3", 363, 75, -7.9736f, -23.5f, 18.5f, 15.9472f, 11.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado4 = body.addChild("lado_4", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-180), 0));
		c(body_lado4, "pend_sup_0_4", 398, 165, -13.8952f, 0.0f, -2.4777f, 27.7904f, 2.0f, 2.4777f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		c(body_lado4, "pend_sup_1_4", 430, 173, -12.9287f, 0.0f, -2.4777f, 25.8574f, 2.0f, 2.4777f, 0.0f, -17.8333f, 33.6667f, -19.6538f, 0.0f);
		c(body_lado4, "pend_sup_2_4", 402, 180, -11.9622f, 0.0f, -2.4777f, 23.9244f, 2.0f, 2.4777f, 0.0f, -18.6667f, 31.3333f, -19.6538f, 0.0f);
		c(body_lado4, "pend_sup_3_4", 319, 187, -10.9957f, 0.0f, -2.4777f, 21.9914f, 2.0f, 2.4777f, 0.0f, -19.5f, 29.0f, -19.6538f, 0.0f);
		c(body_lado4, "pend_sup_4_4", 247, 194, -10.0292f, 0.0f, -2.4777f, 20.0584f, 2.0f, 2.4777f, 0.0f, -20.3333f, 26.6667f, -19.6538f, 0.0f);
		c(body_lado4, "pend_sup_5_4", 133, 201, -9.0627f, 0.0f, -2.4777f, 18.1254f, 2.0f, 2.4777f, 0.0f, -21.1667f, 24.3333f, -19.6538f, 0.0f);
		c(body_lado4, "pend_inf_0_4", 190, 117, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_1_4", 175, 125, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_2_4", 160, 133, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_3_4", 97, 141, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_4_4", 466, 141, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_5_4", 287, 149, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado4, "soporte_sup_0_4", 135, 214, -14.9117f, -14.7263f, 33.6667f, 29.8234f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado4, "soporte_sup_1_4", 442, 220, -13.9452f, -15.5596f, 31.3333f, 27.8904f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado4, "soporte_sup_2_4", 178, 232, -12.9787f, -16.3929f, 29.0f, 25.9574f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado4, "soporte_sup_3_4", 166, 244, -12.0122f, -17.2263f, 26.6667f, 24.0244f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado4, "soporte_sup_4_4", 52, 256, -11.0457f, -18.0596f, 24.3333f, 22.0914f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado4, "soporte_sup_5_4", 334, 262, -10.0792f, -18.8929f, 22.0f, 20.1584f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado4, "soporte_inf_0_4", 69, 208, -14.9117f, -15.1988f, 33.0f, 29.8234f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado4, "soporte_inf_1_4", 379, 220, -13.669f, -14.5321f, 30.0f, 27.3381f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado4, "soporte_inf_2_4", 175, 238, -12.4264f, -13.8655f, 27.0f, 24.8528f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado4, "soporte_inf_3_4", 107, 250, -11.1838f, -13.1988f, 24.0f, 22.3675f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado4, "soporte_inf_4_4", 451, 256, -9.9411f, -12.5321f, 21.0f, 19.8823f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado4, "soporte_inf_5_4", 224, 268, -8.6985f, -11.8655f, 18.0f, 17.397f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado4, "borde_4", 193, 108, -14.4975f, -17.0f, 34.0f, 28.9949f, 5.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado4, "anillo_plano_0_4", 193, 274, -8.9056f, -22.0f, 21.0f, 17.8112f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado4, "anillo_plano_1_4", 1, 280, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado4, "borde_cupula_4", 326, 75, -7.9736f, -23.5f, 18.5f, 15.9472f, 11.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado5 = body.addChild("lado_5", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-225), 0));
		c(body_lado5, "pend_sup_0_5", 335, 165, -13.8952f, 0.0f, -2.4777f, 27.7904f, 2.0f, 2.4777f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		c(body_lado5, "pend_sup_1_5", 371, 173, -12.9287f, 0.0f, -2.4777f, 25.8574f, 2.0f, 2.4777f, 0.0f, -17.8333f, 33.6667f, -19.6538f, 0.0f);
		c(body_lado5, "pend_sup_2_5", 347, 180, -11.9622f, 0.0f, -2.4777f, 23.9244f, 2.0f, 2.4777f, 0.0f, -18.6667f, 31.3333f, -19.6538f, 0.0f);
		c(body_lado5, "pend_sup_3_5", 268, 187, -10.9957f, 0.0f, -2.4777f, 21.9914f, 2.0f, 2.4777f, 0.0f, -19.5f, 29.0f, -19.6538f, 0.0f);
		c(body_lado5, "pend_sup_4_5", 199, 194, -10.0292f, 0.0f, -2.4777f, 20.0584f, 2.0f, 2.4777f, 0.0f, -20.3333f, 26.6667f, -19.6538f, 0.0f);
		c(body_lado5, "pend_sup_5_5", 89, 201, -9.0627f, 0.0f, -2.4777f, 18.1254f, 2.0f, 2.4777f, 0.0f, -21.1667f, 24.3333f, -19.6538f, 0.0f);
		c(body_lado5, "pend_inf_0_5", 127, 117, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_1_5", 117, 125, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_2_5", 107, 133, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_3_5", 49, 141, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_4_5", 423, 141, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_5_5", 249, 149, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado5, "soporte_sup_0_5", 68, 214, -14.9117f, -14.7263f, 33.6667f, 29.8234f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado5, "soporte_sup_1_5", 316, 220, -13.9452f, -15.5596f, 31.3333f, 27.8904f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado5, "soporte_sup_2_5", 119, 232, -12.9787f, -16.3929f, 29.0f, 25.9574f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado5, "soporte_sup_3_5", 111, 244, -12.0122f, -17.2263f, 26.6667f, 24.0244f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado5, "soporte_sup_4_5", 1, 256, -11.0457f, -18.0596f, 24.3333f, 22.0914f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado5, "soporte_sup_5_5", 287, 262, -10.0792f, -18.8929f, 22.0f, 20.1584f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado5, "soporte_inf_0_5", 1, 208, -14.9117f, -15.1988f, 33.0f, 29.8234f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado5, "soporte_inf_1_5", 253, 220, -13.669f, -14.5321f, 30.0f, 27.3381f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado5, "soporte_inf_2_5", 117, 238, -12.4264f, -13.8655f, 27.0f, 24.8528f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado5, "soporte_inf_3_5", 54, 250, -11.1838f, -13.1988f, 24.0f, 22.3675f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado5, "soporte_inf_4_5", 403, 256, -9.9411f, -12.5321f, 21.0f, 19.8823f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado5, "soporte_inf_5_5", 181, 268, -8.6985f, -11.8655f, 18.0f, 17.397f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado5, "borde_5", 129, 108, -14.4975f, -17.0f, 34.0f, 28.9949f, 5.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado5, "anillo_plano_0_5", 153, 274, -8.9056f, -22.0f, 21.0f, 17.8112f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado5, "anillo_plano_1_5", 469, 274, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado5, "borde_cupula_5", 289, 75, -7.9736f, -23.5f, 18.5f, 15.9472f, 11.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado6 = body.addChild("lado_6", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-270), 0));
		c(body_lado6, "pend_sup_0_6", 272, 165, -13.8952f, 0.0f, -2.4777f, 27.7904f, 2.0f, 2.4777f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		c(body_lado6, "pend_sup_1_6", 312, 173, -12.9287f, 0.0f, -2.4777f, 25.8574f, 2.0f, 2.4777f, 0.0f, -17.8333f, 33.6667f, -19.6538f, 0.0f);
		c(body_lado6, "pend_sup_2_6", 292, 180, -11.9622f, 0.0f, -2.4777f, 23.9244f, 2.0f, 2.4777f, 0.0f, -18.6667f, 31.3333f, -19.6538f, 0.0f);
		c(body_lado6, "pend_sup_3_6", 217, 187, -10.9957f, 0.0f, -2.4777f, 21.9914f, 2.0f, 2.4777f, 0.0f, -19.5f, 29.0f, -19.6538f, 0.0f);
		c(body_lado6, "pend_sup_4_6", 151, 194, -10.0292f, 0.0f, -2.4777f, 20.0584f, 2.0f, 2.4777f, 0.0f, -20.3333f, 26.6667f, -19.6538f, 0.0f);
		c(body_lado6, "pend_sup_5_6", 45, 201, -9.0627f, 0.0f, -2.4777f, 18.1254f, 2.0f, 2.4777f, 0.0f, -21.1667f, 24.3333f, -19.6538f, 0.0f);
		c(body_lado6, "pend_inf_0_6", 64, 117, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_1_6", 59, 125, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_2_6", 54, 133, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_3_6", 1, 141, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_4_6", 380, 141, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_5_6", 211, 149, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado6, "soporte_sup_0_6", 1, 214, -14.9117f, -14.7263f, 33.6667f, 29.8234f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado6, "soporte_sup_1_6", 190, 220, -13.9452f, -15.5596f, 31.3333f, 27.8904f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado6, "soporte_sup_2_6", 60, 232, -12.9787f, -16.3929f, 29.0f, 25.9574f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado6, "soporte_sup_3_6", 56, 244, -12.0122f, -17.2263f, 26.6667f, 24.0244f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado6, "soporte_sup_4_6", 423, 250, -11.0457f, -18.0596f, 24.3333f, 22.0914f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado6, "soporte_sup_5_6", 240, 262, -10.0792f, -18.8929f, 22.0f, 20.1584f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado6, "soporte_inf_0_6", 421, 201, -14.9117f, -15.1988f, 33.0f, 29.8234f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado6, "soporte_inf_1_6", 127, 220, -13.669f, -14.5321f, 30.0f, 27.3381f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado6, "soporte_inf_2_6", 59, 238, -12.4264f, -13.8655f, 27.0f, 24.8528f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado6, "soporte_inf_3_6", 1, 250, -11.1838f, -13.1988f, 24.0f, 22.3675f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado6, "soporte_inf_4_6", 355, 256, -9.9411f, -12.5321f, 21.0f, 19.8823f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado6, "soporte_inf_5_6", 138, 268, -8.6985f, -11.8655f, 18.0f, 17.397f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado6, "borde_6", 65, 108, -14.4975f, -17.0f, 34.0f, 28.9949f, 5.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado6, "anillo_plano_0_6", 113, 274, -8.9056f, -22.0f, 21.0f, 17.8112f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado6, "anillo_plano_1_6", 431, 274, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado6, "borde_cupula_6", 252, 75, -7.9736f, -23.5f, 18.5f, 15.9472f, 11.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado7 = body.addChild("lado_7", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-315), 0));
		c(body_lado7, "pend_sup_0_7", 209, 165, -13.8952f, 0.0f, -2.4777f, 27.7904f, 2.0f, 2.4777f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		c(body_lado7, "pend_sup_1_7", 253, 173, -12.9287f, 0.0f, -2.4777f, 25.8574f, 2.0f, 2.4777f, 0.0f, -17.8333f, 33.6667f, -19.6538f, 0.0f);
		c(body_lado7, "pend_sup_2_7", 237, 180, -11.9622f, 0.0f, -2.4777f, 23.9244f, 2.0f, 2.4777f, 0.0f, -18.6667f, 31.3333f, -19.6538f, 0.0f);
		c(body_lado7, "pend_sup_3_7", 166, 187, -10.9957f, 0.0f, -2.4777f, 21.9914f, 2.0f, 2.4777f, 0.0f, -19.5f, 29.0f, -19.6538f, 0.0f);
		c(body_lado7, "pend_sup_4_7", 103, 194, -10.0292f, 0.0f, -2.4777f, 20.0584f, 2.0f, 2.4777f, 0.0f, -20.3333f, 26.6667f, -19.6538f, 0.0f);
		c(body_lado7, "pend_sup_5_7", 1, 201, -9.0627f, 0.0f, -2.4777f, 18.1254f, 2.0f, 2.4777f, 0.0f, -21.1667f, 24.3333f, -19.6538f, 0.0f);
		c(body_lado7, "pend_inf_0_7", 1, 117, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_1_7", 1, 125, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_2_7", 1, 133, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_3_7", 425, 133, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_4_7", 337, 141, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_5_7", 173, 149, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado7, "soporte_sup_0_7", 409, 208, -14.9117f, -14.7263f, 33.6667f, 29.8234f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado7, "soporte_sup_1_7", 64, 220, -13.9452f, -15.5596f, 31.3333f, 27.8904f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado7, "soporte_sup_2_7", 1, 232, -12.9787f, -16.3929f, 29.0f, 25.9574f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado7, "soporte_sup_3_7", 1, 244, -12.0122f, -17.2263f, 26.6667f, 24.0244f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado7, "soporte_sup_4_7", 372, 250, -11.0457f, -18.0596f, 24.3333f, 22.0914f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado7, "soporte_sup_5_7", 193, 262, -10.0792f, -18.8929f, 22.0f, 20.1584f, 1.0f, 2.3333f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado7, "soporte_inf_0_7", 353, 201, -14.9117f, -15.1988f, 33.0f, 29.8234f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado7, "soporte_inf_1_7", 1, 220, -13.669f, -14.5321f, 30.0f, 27.3381f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado7, "soporte_inf_2_7", 1, 238, -12.4264f, -13.8655f, 27.0f, 24.8528f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado7, "soporte_inf_3_7", 441, 244, -11.1838f, -13.1988f, 24.0f, 22.3675f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado7, "soporte_inf_4_7", 307, 256, -9.9411f, -12.5321f, 21.0f, 19.8823f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado7, "soporte_inf_5_7", 95, 268, -8.6985f, -11.8655f, 18.0f, 17.397f, 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado7, "borde_7", 1, 108, -14.4975f, -17.0f, 34.0f, 28.9949f, 5.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado7, "anillo_plano_0_7", 73, 274, -8.9056f, -22.0f, 21.0f, 17.8112f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado7, "anillo_plano_1_7", 393, 274, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado7, "borde_cupula_7", 215, 75, -7.9736f, -23.5f, 18.5f, 15.9472f, 11.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);

		// --- glow
		c(glow, "fondo_brasa_izq", 100, 93, -34.5f, -35.5f, -7.5f, 9.0f, 9.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_1_izq", 171, 93, -29.5f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_2_izq", 155, 93, -33.0f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fondo_brasa_der", 77, 93, 25.5f, -35.5f, -7.5f, 9.0f, 9.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_1_der", 139, 93, 27.0f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_2_der", 123, 93, 30.5f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData glow_lado0 = glow.addChild("lado_0", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(0), 0));
		c(glow_lado0, "franja_borde_0", 55, 274, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado0, "franja_pendiente_0", 1, 75, -2.5f, -0.6f, -14.8661f, 5.0f, 0.6f, 14.8661f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		ModelPartData glow_lado1 = glow.addChild("lado_1", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-45), 0));
		c(glow_lado1, "franja_borde_1", 37, 274, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado1, "franja_pendiente_1", 463, 54, -2.5f, -0.6f, -14.8661f, 5.0f, 0.6f, 14.8661f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		ModelPartData glow_lado2 = glow.addChild("lado_2", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-90), 0));
		c(glow_lado2, "franja_borde_2", 19, 274, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado2, "franja_pendiente_2", 421, 54, -2.5f, -0.6f, -14.8661f, 5.0f, 0.6f, 14.8661f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		ModelPartData glow_lado3 = glow.addChild("lado_3", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-135), 0));
		c(glow_lado3, "franja_borde_3", 1, 274, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado3, "franja_pendiente_3", 379, 54, -2.5f, -0.6f, -14.8661f, 5.0f, 0.6f, 14.8661f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		ModelPartData glow_lado4 = glow.addChild("lado_4", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-180), 0));
		c(glow_lado4, "franja_borde_4", 493, 268, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado4, "franja_pendiente_4", 337, 54, -2.5f, -0.6f, -14.8661f, 5.0f, 0.6f, 14.8661f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		ModelPartData glow_lado5 = glow.addChild("lado_5", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-225), 0));
		c(glow_lado5, "franja_borde_5", 475, 268, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado5, "franja_pendiente_5", 295, 54, -2.5f, -0.6f, -14.8661f, 5.0f, 0.6f, 14.8661f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		ModelPartData glow_lado6 = glow.addChild("lado_6", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-270), 0));
		c(glow_lado6, "franja_borde_6", 457, 268, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado6, "franja_pendiente_6", 253, 54, -2.5f, -0.6f, -14.8661f, 5.0f, 0.6f, 14.8661f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		ModelPartData glow_lado7 = glow.addChild("lado_7", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-315), 0));
		c(glow_lado7, "franja_borde_7", 439, 268, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado7, "franja_pendiente_7", 211, 54, -2.5f, -0.6f, -14.8661f, 5.0f, 0.6f, 14.8661f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);

		// --- frame
		c(frame, "tapa_0", 361, 93, -9.8f, -1.5f, -4.0593f, 19.6f, 1.5f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, 0.0f);
		c(frame, "tapa_1", 303, 93, -9.8f, -1.48f, -4.0593f, 19.6f, 1.46f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, -45.0f);
		c(frame, "tapa_2", 245, 93, -9.8f, -1.46f, -4.0593f, 19.6f, 1.42f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, -90.0f);
		c(frame, "tapa_3", 187, 93, -9.8f, -1.44f, -4.0593f, 19.6f, 1.38f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, -135.0f);
		c(frame, "poste_0", 425, 30, 7.0701f, -41.5f, 18.2f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_1", 416, 30, 18.2f, -41.5f, 7.0701f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_2", 407, 30, 18.2f, -41.5f, -8.6701f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_3", 398, 30, 7.0701f, -41.5f, -19.8f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_4", 389, 30, -8.6701f, -41.5f, -19.8f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_5", 380, 30, -19.8f, -41.5f, -8.6701f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_6", 371, 30, -19.8f, -41.5f, 7.0701f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_7", 362, 30, -8.6701f, -41.5f, 18.2f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);

		// --- dome
		ModelPartData dome_lado0 = dome.addChild("lado_0", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(0), 0));
		c(dome_lado0, "cristal_pared_0", 176, 54, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado0, "cristal_techo_0_0", 217, 157, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado0, "cristal_techo_1_0", 470, 157, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado0, "cristal_techo_2_0", 183, 165, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado1 = dome.addChild("lado_1", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-45), 0));
		c(dome_lado1, "cristal_pared_1", 141, 54, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado1, "cristal_techo_0_1", 181, 157, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado1, "cristal_techo_1_1", 439, 157, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado1, "cristal_techo_2_1", 157, 165, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado2 = dome.addChild("lado_2", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-90), 0));
		c(dome_lado2, "cristal_pared_2", 106, 54, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado2, "cristal_techo_0_2", 145, 157, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado2, "cristal_techo_1_2", 408, 157, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado2, "cristal_techo_2_2", 131, 165, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado3 = dome.addChild("lado_3", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-135), 0));
		c(dome_lado3, "cristal_pared_3", 71, 54, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado3, "cristal_techo_0_3", 109, 157, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado3, "cristal_techo_1_3", 377, 157, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado3, "cristal_techo_2_3", 105, 165, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado4 = dome.addChild("lado_4", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-180), 0));
		c(dome_lado4, "cristal_pared_4", 36, 54, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado4, "cristal_techo_0_4", 73, 157, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado4, "cristal_techo_1_4", 346, 157, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado4, "cristal_techo_2_4", 79, 165, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado5 = dome.addChild("lado_5", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-225), 0));
		c(dome_lado5, "cristal_pared_5", 1, 54, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado5, "cristal_techo_0_5", 37, 157, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado5, "cristal_techo_1_5", 315, 157, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado5, "cristal_techo_2_5", 53, 165, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado6 = dome.addChild("lado_6", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-270), 0));
		c(dome_lado6, "cristal_pared_6", 469, 30, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado6, "cristal_techo_0_6", 1, 157, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado6, "cristal_techo_1_6", 284, 157, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado6, "cristal_techo_2_6", 27, 165, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado7 = dome.addChild("lado_7", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-315), 0));
		c(dome_lado7, "cristal_pared_7", 434, 30, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado7, "cristal_techo_0_7", 477, 149, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado7, "cristal_techo_1_7", 253, 157, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado7, "cristal_techo_2_7", 1, 165, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);

		return TexturedModelData.of(data, TEXTURE_WIDTH, TEXTURE_HEIGHT);
	}

	/** Cubo con pivote propio: posición del cubo relativa al pivote, giros en grados (X = pitch, Y = yaw). */
	private static void c(ModelPartData parent, String name, int u, int v,
						  float x, float y, float z, float sx, float sy, float sz,
						  float px, float py, float pz, float pitchDeg, float yawDeg) {
		parent.addChild(
				name,
				ModelPartBuilder.create().uv(u, v).cuboid(x, y, z, sx, sy, sz),
				ModelTransform.of(px, py, pz, (float) Math.toRadians(pitchDeg), (float) Math.toRadians(yawDeg), 0.0f)
		);
	}
}
