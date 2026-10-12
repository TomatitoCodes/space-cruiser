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
		c(body, "nucleo_inf_0", 1, 252, -18.0f, -4.0f, -7.4558f, 36.0f, 4.0f, 14.9117f, 0.0f, -8.0f, 0.0f, 0.0f, 0.0f);
		c(body, "nucleo_inf_1", 343, 230, -18.0f, -3.98f, -7.4558f, 36.0f, 3.96f, 14.9117f, 0.0f, -8.0f, 0.0f, 0.0f, -45.0f);
		c(body, "nucleo_inf_2", 239, 230, -18.0f, -3.96f, -7.4558f, 36.0f, 3.92f, 14.9117f, 0.0f, -8.0f, 0.0f, 0.0f, -90.0f);
		c(body, "nucleo_inf_3", 135, 230, -18.0f, -3.94f, -7.4558f, 36.0f, 3.88f, 14.9117f, 0.0f, -8.0f, 0.0f, 0.0f, -135.0f);
		c(body, "borde_0", 207, 38, -36.0f, -5.0f, -14.9117f, 72.0f, 5.0f, 29.8234f, 0.0f, -12.0f, 0.0f, 0.0f, 0.0f);
		c(body, "borde_1", 1, 38, -36.0f, -4.98f, -14.9117f, 72.0f, 4.96f, 29.8234f, 0.0f, -12.0f, 0.0f, 0.0f, -45.0f);
		c(body, "borde_2", 207, 1, -36.0f, -4.96f, -14.9117f, 72.0f, 4.92f, 29.8234f, 0.0f, -12.0f, 0.0f, 0.0f, -90.0f);
		c(body, "borde_3", 1, 1, -36.0f, -4.94f, -14.9117f, 72.0f, 4.88f, 29.8234f, 0.0f, -12.0f, 0.0f, 0.0f, -135.0f);
		c(body, "nucleo_sup_0", 289, 130, -25.0f, -2.5f, -10.3553f, 50.0f, 2.5f, 20.7107f, 0.0f, -17.0f, 0.0f, 0.0f, 0.0f);
		c(body, "nucleo_sup_1", 145, 130, -25.0f, -2.48f, -10.3553f, 50.0f, 2.46f, 20.7107f, 0.0f, -17.0f, 0.0f, 0.0f, -45.0f);
		c(body, "nucleo_sup_2", 1, 130, -25.0f, -2.46f, -10.3553f, 50.0f, 2.42f, 20.7107f, 0.0f, -17.0f, 0.0f, 0.0f, -90.0f);
		c(body, "nucleo_sup_3", 329, 104, -25.0f, -2.44f, -10.3553f, 50.0f, 2.38f, 20.7107f, 0.0f, -17.0f, 0.0f, 0.0f, -135.0f);
		c(body, "relleno_sup_3_0", 165, 104, -28.5f, -0.3418f, -11.8051f, 57.0f, 0.3418f, 23.6102f, 0.0f, -17.0f, 0.0f, 0.0f, 0.0f);
		c(body, "relleno_sup_3_1", 1, 104, -28.5f, -0.3218f, -11.8051f, 57.0f, 0.3018f, 23.6102f, 0.0f, -17.0f, 0.0f, 0.0f, -45.0f);
		c(body, "relleno_sup_3_2", 247, 75, -28.5f, -0.3018f, -11.8051f, 57.0f, 0.2618f, 23.6102f, 0.0f, -17.0f, 0.0f, 0.0f, -90.0f);
		c(body, "relleno_sup_3_3", 83, 75, -28.5f, -0.2818f, -11.8051f, 57.0f, 0.2218f, 23.6102f, 0.0f, -17.0f, 0.0f, 0.0f, -135.0f);
		c(body, "relleno_sup_4_0", 1, 181, -26.0f, -1.1751f, -10.7696f, 52.0f, 1.1751f, 21.5391f, 0.0f, -17.0f, 0.0f, 0.0f, 0.0f);
		c(body, "relleno_sup_4_1", 301, 156, -26.0f, -1.1551f, -10.7696f, 52.0f, 1.1351f, 21.5391f, 0.0f, -17.0f, 0.0f, 0.0f, -45.0f);
		c(body, "relleno_sup_4_2", 151, 156, -26.0f, -1.1351f, -10.7696f, 52.0f, 1.0951f, 21.5391f, 0.0f, -17.0f, 0.0f, 0.0f, -90.0f);
		c(body, "relleno_sup_4_3", 1, 156, -26.0f, -1.1151f, -10.7696f, 52.0f, 1.0551f, 21.5391f, 0.0f, -17.0f, 0.0f, 0.0f, -135.0f);
		c(body, "relleno_sup_5_0", 136, 206, -23.5f, -2.0085f, -9.734f, 47.0f, 2.0085f, 19.468f, 0.0f, -17.0f, 0.0f, 0.0f, 0.0f);
		c(body, "relleno_sup_5_1", 1, 206, -23.5f, -1.9885f, -9.734f, 47.0f, 1.9685f, 19.468f, 0.0f, -17.0f, 0.0f, 0.0f, -45.0f);
		c(body, "relleno_sup_5_2", 286, 181, -23.5f, -1.9685f, -9.734f, 47.0f, 1.9285f, 19.468f, 0.0f, -17.0f, 0.0f, 0.0f, -90.0f);
		c(body, "relleno_sup_5_3", 151, 181, -23.5f, -1.9485f, -9.734f, 47.0f, 1.8885f, 19.468f, 0.0f, -17.0f, 0.0f, 0.0f, -135.0f);
		c(body, "asiento_base", 419, 292, -5.0f, -23.0f, -5.0f, 10.0f, 3.5f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "asiento_respaldo", 333, 273, -5.0f, -36.0f, -5.0f, 10.0f, 13.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "consola", 463, 273, -9.0f, -26.0f, 12.0f, 18.0f, 6.5f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "columna_volante", 73, 406, -1.5f, -28.0f, 15.0f, 3.0f, 2.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "volante", 453, 292, -5.0f, -29.5f, 13.0f, 10.0f, 1.5f, 5.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_arriba_izq", 1, 230, -36.0f, -37.0f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_abajo_izq", 395, 206, -36.0f, -26.5f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_interior_izq", 42, 75, -25.5f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_exterior_izq", 1, 75, -36.0f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tapa_delantera_izq", 299, 273, -37.0f, -38.0f, 9.0f, 14.0f, 14.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "asa_delantera_izq", 229, 406, -32.0f, -32.0f, 11.0f, 4.0f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_arriba_izq", 184, 406, -37.0f, -38.0f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_abajo_izq", 151, 406, -37.0f, -25.5f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_interior_izq", 25, 292, -24.5f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_exterior_izq", 17, 292, -37.0f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "soporte_izq", 55, 292, -24.0f, -34.0f, -3.0f, 4.0f, 6.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_arriba_der", 333, 206, 24.0f, -37.0f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_abajo_der", 271, 206, 24.0f, -26.5f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_interior_der", 454, 38, 24.0f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_exterior_der", 413, 38, 34.5f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tapa_delantera_der", 265, 273, 23.0f, -38.0f, 9.0f, 14.0f, 14.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "asa_delantera_der", 217, 406, 28.0f, -32.0f, 11.0f, 4.0f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_arriba_der", 118, 406, 23.0f, -38.0f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_abajo_der", 85, 406, 23.0f, -25.5f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_interior_der", 9, 292, 23.0f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_exterior_der", 1, 292, 35.5f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "soporte_der", 33, 292, 20.0f, -34.0f, -3.0f, 4.0f, 6.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_0", 437, 273, -17.0f, -10.0f, 11.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_1", 411, 273, 11.0f, -10.0f, 11.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_2", 385, 273, -17.0f, -10.0f, -17.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_3", 359, 273, 11.0f, -10.0f, -17.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado0 = body.addChild("lado_0", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(0), 0));
		c(body_lado0, "pend_sup_0_0", 1, 371, -13.8262f, 0.0f, -2.6352f, 27.6523f, 2.0f, 2.6352f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		c(body_lado0, "pend_sup_1_0", 1, 378, -12.7906f, 0.0f, -2.6352f, 25.5812f, 2.0f, 2.6352f, 0.0f, -17.8333f, 33.5f, -18.4349f, 0.0f);
		c(body_lado0, "pend_sup_2_0", 445, 378, -11.7551f, 0.0f, -2.6352f, 23.5102f, 2.0f, 2.6352f, 0.0f, -18.6667f, 31.0f, -18.4349f, 0.0f);
		c(body_lado0, "pend_sup_3_0", 358, 385, -10.7196f, 0.0f, -2.6352f, 21.4391f, 2.0f, 2.6352f, 0.0f, -19.5f, 28.5f, -18.4349f, 0.0f);
		c(body_lado0, "pend_sup_4_0", 236, 392, -9.684f, 0.0f, -2.6352f, 19.368f, 2.0f, 2.6352f, 0.0f, -20.3333f, 26.0f, -18.4349f, 0.0f);
		c(body_lado0, "pend_sup_5_0", 85, 399, -8.6485f, 0.0f, -2.6352f, 17.297f, 2.0f, 2.6352f, 0.0f, -21.1667f, 23.5f, -18.4349f, 0.0f);
		c(body_lado0, "pend_inf_0_0", 442, 307, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_1_0", 407, 315, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_2_0", 372, 323, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_3_0", 289, 331, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_4_0", 130, 339, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_5_0", 439, 339, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado0, "anillo_plano_0", 393, 399, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado0, "borde_cupula_0", 260, 347, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado1 = body.addChild("lado_1", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-45), 0));
		c(body_lado1, "pend_sup_0_1", 405, 363, -13.8262f, 0.0f, -2.6352f, 27.6523f, 2.0f, 2.6352f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		c(body_lado1, "pend_sup_1_1", 418, 371, -12.7906f, 0.0f, -2.6352f, 25.5812f, 2.0f, 2.6352f, 0.0f, -17.8333f, 33.5f, -18.4349f, 0.0f);
		c(body_lado1, "pend_sup_2_1", 390, 378, -11.7551f, 0.0f, -2.6352f, 23.5102f, 2.0f, 2.6352f, 0.0f, -18.6667f, 31.0f, -18.4349f, 0.0f);
		c(body_lado1, "pend_sup_3_1", 307, 385, -10.7196f, 0.0f, -2.6352f, 21.4391f, 2.0f, 2.6352f, 0.0f, -19.5f, 28.5f, -18.4349f, 0.0f);
		c(body_lado1, "pend_sup_4_1", 189, 392, -9.684f, 0.0f, -2.6352f, 19.368f, 2.0f, 2.6352f, 0.0f, -20.3333f, 26.0f, -18.4349f, 0.0f);
		c(body_lado1, "pend_sup_5_1", 43, 399, -8.6485f, 0.0f, -2.6352f, 17.297f, 2.0f, 2.6352f, 0.0f, -21.1667f, 23.5f, -18.4349f, 0.0f);
		c(body_lado1, "pend_inf_0_1", 379, 307, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_1_1", 349, 315, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_2_1", 319, 323, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_3_1", 241, 331, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_4_1", 87, 339, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_5_1", 401, 339, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado1, "anillo_plano_1", 355, 399, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado1, "borde_cupula_1", 223, 347, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado2 = body.addChild("lado_2", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-90), 0));
		c(body_lado2, "pend_sup_0_2", 342, 363, -13.8262f, 0.0f, -2.6352f, 27.6523f, 2.0f, 2.6352f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		c(body_lado2, "pend_sup_1_2", 359, 371, -12.7906f, 0.0f, -2.6352f, 25.5812f, 2.0f, 2.6352f, 0.0f, -17.8333f, 33.5f, -18.4349f, 0.0f);
		c(body_lado2, "pend_sup_2_2", 335, 378, -11.7551f, 0.0f, -2.6352f, 23.5102f, 2.0f, 2.6352f, 0.0f, -18.6667f, 31.0f, -18.4349f, 0.0f);
		c(body_lado2, "pend_sup_3_2", 256, 385, -10.7196f, 0.0f, -2.6352f, 21.4391f, 2.0f, 2.6352f, 0.0f, -19.5f, 28.5f, -18.4349f, 0.0f);
		c(body_lado2, "pend_sup_4_2", 142, 392, -9.684f, 0.0f, -2.6352f, 19.368f, 2.0f, 2.6352f, 0.0f, -20.3333f, 26.0f, -18.4349f, 0.0f);
		c(body_lado2, "pend_sup_5_2", 1, 399, -8.6485f, 0.0f, -2.6352f, 17.297f, 2.0f, 2.6352f, 0.0f, -21.1667f, 23.5f, -18.4349f, 0.0f);
		c(body_lado2, "pend_inf_0_2", 316, 307, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_1_2", 291, 315, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_2_2", 266, 323, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_3_2", 193, 331, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_4_2", 44, 339, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_5_2", 363, 339, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado2, "anillo_plano_2", 317, 399, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado2, "borde_cupula_2", 186, 347, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado3 = body.addChild("lado_3", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-135), 0));
		c(body_lado3, "pend_sup_0_3", 279, 363, -13.8262f, 0.0f, -2.6352f, 27.6523f, 2.0f, 2.6352f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		c(body_lado3, "pend_sup_1_3", 300, 371, -12.7906f, 0.0f, -2.6352f, 25.5812f, 2.0f, 2.6352f, 0.0f, -17.8333f, 33.5f, -18.4349f, 0.0f);
		c(body_lado3, "pend_sup_2_3", 280, 378, -11.7551f, 0.0f, -2.6352f, 23.5102f, 2.0f, 2.6352f, 0.0f, -18.6667f, 31.0f, -18.4349f, 0.0f);
		c(body_lado3, "pend_sup_3_3", 205, 385, -10.7196f, 0.0f, -2.6352f, 21.4391f, 2.0f, 2.6352f, 0.0f, -19.5f, 28.5f, -18.4349f, 0.0f);
		c(body_lado3, "pend_sup_4_3", 95, 392, -9.684f, 0.0f, -2.6352f, 19.368f, 2.0f, 2.6352f, 0.0f, -20.3333f, 26.0f, -18.4349f, 0.0f);
		c(body_lado3, "pend_sup_5_3", 451, 392, -8.6485f, 0.0f, -2.6352f, 17.297f, 2.0f, 2.6352f, 0.0f, -21.1667f, 23.5f, -18.4349f, 0.0f);
		c(body_lado3, "pend_inf_0_3", 253, 307, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_1_3", 233, 315, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_2_3", 213, 323, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_3_3", 145, 331, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_4_3", 1, 339, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_5_3", 325, 339, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado3, "anillo_plano_3", 279, 399, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado3, "borde_cupula_3", 149, 347, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado4 = body.addChild("lado_4", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-180), 0));
		c(body_lado4, "pend_sup_0_4", 216, 363, -13.8262f, 0.0f, -2.6352f, 27.6523f, 2.0f, 2.6352f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		c(body_lado4, "pend_sup_1_4", 241, 371, -12.7906f, 0.0f, -2.6352f, 25.5812f, 2.0f, 2.6352f, 0.0f, -17.8333f, 33.5f, -18.4349f, 0.0f);
		c(body_lado4, "pend_sup_2_4", 225, 378, -11.7551f, 0.0f, -2.6352f, 23.5102f, 2.0f, 2.6352f, 0.0f, -18.6667f, 31.0f, -18.4349f, 0.0f);
		c(body_lado4, "pend_sup_3_4", 154, 385, -10.7196f, 0.0f, -2.6352f, 21.4391f, 2.0f, 2.6352f, 0.0f, -19.5f, 28.5f, -18.4349f, 0.0f);
		c(body_lado4, "pend_sup_4_4", 48, 392, -9.684f, 0.0f, -2.6352f, 19.368f, 2.0f, 2.6352f, 0.0f, -20.3333f, 26.0f, -18.4349f, 0.0f);
		c(body_lado4, "pend_sup_5_4", 409, 392, -8.6485f, 0.0f, -2.6352f, 17.297f, 2.0f, 2.6352f, 0.0f, -21.1667f, 23.5f, -18.4349f, 0.0f);
		c(body_lado4, "pend_inf_0_4", 190, 307, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_1_4", 175, 315, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_2_4", 160, 323, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_3_4", 97, 331, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_4_4", 466, 331, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_5_4", 287, 339, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado4, "anillo_plano_4", 241, 399, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado4, "borde_cupula_4", 112, 347, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado5 = body.addChild("lado_5", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-225), 0));
		c(body_lado5, "pend_sup_0_5", 153, 363, -13.8262f, 0.0f, -2.6352f, 27.6523f, 2.0f, 2.6352f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		c(body_lado5, "pend_sup_1_5", 182, 371, -12.7906f, 0.0f, -2.6352f, 25.5812f, 2.0f, 2.6352f, 0.0f, -17.8333f, 33.5f, -18.4349f, 0.0f);
		c(body_lado5, "pend_sup_2_5", 170, 378, -11.7551f, 0.0f, -2.6352f, 23.5102f, 2.0f, 2.6352f, 0.0f, -18.6667f, 31.0f, -18.4349f, 0.0f);
		c(body_lado5, "pend_sup_3_5", 103, 385, -10.7196f, 0.0f, -2.6352f, 21.4391f, 2.0f, 2.6352f, 0.0f, -19.5f, 28.5f, -18.4349f, 0.0f);
		c(body_lado5, "pend_sup_4_5", 1, 392, -9.684f, 0.0f, -2.6352f, 19.368f, 2.0f, 2.6352f, 0.0f, -20.3333f, 26.0f, -18.4349f, 0.0f);
		c(body_lado5, "pend_sup_5_5", 367, 392, -8.6485f, 0.0f, -2.6352f, 17.297f, 2.0f, 2.6352f, 0.0f, -21.1667f, 23.5f, -18.4349f, 0.0f);
		c(body_lado5, "pend_inf_0_5", 127, 307, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_1_5", 117, 315, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_2_5", 107, 323, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_3_5", 49, 331, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_4_5", 423, 331, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_5_5", 249, 339, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado5, "anillo_plano_5", 203, 399, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado5, "borde_cupula_5", 75, 347, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado6 = body.addChild("lado_6", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-270), 0));
		c(body_lado6, "pend_sup_0_6", 90, 363, -13.8262f, 0.0f, -2.6352f, 27.6523f, 2.0f, 2.6352f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		c(body_lado6, "pend_sup_1_6", 123, 371, -12.7906f, 0.0f, -2.6352f, 25.5812f, 2.0f, 2.6352f, 0.0f, -17.8333f, 33.5f, -18.4349f, 0.0f);
		c(body_lado6, "pend_sup_2_6", 115, 378, -11.7551f, 0.0f, -2.6352f, 23.5102f, 2.0f, 2.6352f, 0.0f, -18.6667f, 31.0f, -18.4349f, 0.0f);
		c(body_lado6, "pend_sup_3_6", 52, 385, -10.7196f, 0.0f, -2.6352f, 21.4391f, 2.0f, 2.6352f, 0.0f, -19.5f, 28.5f, -18.4349f, 0.0f);
		c(body_lado6, "pend_sup_4_6", 456, 385, -9.684f, 0.0f, -2.6352f, 19.368f, 2.0f, 2.6352f, 0.0f, -20.3333f, 26.0f, -18.4349f, 0.0f);
		c(body_lado6, "pend_sup_5_6", 325, 392, -8.6485f, 0.0f, -2.6352f, 17.297f, 2.0f, 2.6352f, 0.0f, -21.1667f, 23.5f, -18.4349f, 0.0f);
		c(body_lado6, "pend_inf_0_6", 64, 307, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_1_6", 59, 315, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_2_6", 54, 323, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_3_6", 1, 331, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_4_6", 380, 331, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_5_6", 211, 339, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado6, "anillo_plano_6", 165, 399, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado6, "borde_cupula_6", 38, 347, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado7 = body.addChild("lado_7", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-315), 0));
		c(body_lado7, "pend_sup_0_7", 27, 363, -13.8262f, 0.0f, -2.6352f, 27.6523f, 2.0f, 2.6352f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		c(body_lado7, "pend_sup_1_7", 64, 371, -12.7906f, 0.0f, -2.6352f, 25.5812f, 2.0f, 2.6352f, 0.0f, -17.8333f, 33.5f, -18.4349f, 0.0f);
		c(body_lado7, "pend_sup_2_7", 60, 378, -11.7551f, 0.0f, -2.6352f, 23.5102f, 2.0f, 2.6352f, 0.0f, -18.6667f, 31.0f, -18.4349f, 0.0f);
		c(body_lado7, "pend_sup_3_7", 1, 385, -10.7196f, 0.0f, -2.6352f, 21.4391f, 2.0f, 2.6352f, 0.0f, -19.5f, 28.5f, -18.4349f, 0.0f);
		c(body_lado7, "pend_sup_4_7", 409, 385, -9.684f, 0.0f, -2.6352f, 19.368f, 2.0f, 2.6352f, 0.0f, -20.3333f, 26.0f, -18.4349f, 0.0f);
		c(body_lado7, "pend_sup_5_7", 283, 392, -8.6485f, 0.0f, -2.6352f, 17.297f, 2.0f, 2.6352f, 0.0f, -21.1667f, 23.5f, -18.4349f, 0.0f);
		c(body_lado7, "pend_inf_0_7", 1, 307, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_1_7", 1, 315, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_2_7", 1, 323, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_3_7", 425, 323, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_4_7", 337, 331, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_5_7", 173, 339, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado7, "anillo_plano_7", 127, 399, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado7, "borde_cupula_7", 1, 347, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);

		// --- glow
		c(glow, "fondo_brasa_izq", 100, 292, -34.5f, -35.5f, -7.5f, 9.0f, 9.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_1_izq", 171, 292, -29.5f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_2_izq", 155, 292, -33.0f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fondo_brasa_der", 77, 292, 25.5f, -35.5f, -7.5f, 9.0f, 9.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_1_der", 139, 292, 27.0f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_2_der", 123, 292, 30.5f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData glow_lado0 = glow.addChild("lado_0", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(0), 0));
		c(glow_lado0, "franja_borde_0", 55, 406, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado0, "franja_pendiente_0", 221, 273, -2.5f, -0.6f, -15.8114f, 5.0f, 0.6f, 15.8114f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		ModelPartData glow_lado1 = glow.addChild("lado_1", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-45), 0));
		c(glow_lado1, "franja_borde_1", 37, 406, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado1, "franja_pendiente_1", 177, 273, -2.5f, -0.6f, -15.8114f, 5.0f, 0.6f, 15.8114f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		ModelPartData glow_lado2 = glow.addChild("lado_2", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-90), 0));
		c(glow_lado2, "franja_borde_2", 19, 406, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado2, "franja_pendiente_2", 133, 273, -2.5f, -0.6f, -15.8114f, 5.0f, 0.6f, 15.8114f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		ModelPartData glow_lado3 = glow.addChild("lado_3", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-135), 0));
		c(glow_lado3, "franja_borde_3", 1, 406, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado3, "franja_pendiente_3", 89, 273, -2.5f, -0.6f, -15.8114f, 5.0f, 0.6f, 15.8114f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		ModelPartData glow_lado4 = glow.addChild("lado_4", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-180), 0));
		c(glow_lado4, "franja_borde_4", 485, 399, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado4, "franja_pendiente_4", 45, 273, -2.5f, -0.6f, -15.8114f, 5.0f, 0.6f, 15.8114f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		ModelPartData glow_lado5 = glow.addChild("lado_5", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-225), 0));
		c(glow_lado5, "franja_borde_5", 467, 399, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado5, "franja_pendiente_5", 1, 273, -2.5f, -0.6f, -15.8114f, 5.0f, 0.6f, 15.8114f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		ModelPartData glow_lado6 = glow.addChild("lado_6", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-270), 0));
		c(glow_lado6, "franja_borde_6", 449, 399, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado6, "franja_pendiente_6", 429, 252, -2.5f, -0.6f, -15.8114f, 5.0f, 0.6f, 15.8114f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		ModelPartData glow_lado7 = glow.addChild("lado_7", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-315), 0));
		c(glow_lado7, "franja_borde_7", 431, 399, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado7, "franja_pendiente_7", 385, 252, -2.5f, -0.6f, -15.8114f, 5.0f, 0.6f, 15.8114f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);

		// --- frame
		c(frame, "tapa_0", 361, 292, -9.8f, -1.5f, -4.0593f, 19.6f, 1.5f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, 0.0f);
		c(frame, "tapa_1", 303, 292, -9.8f, -1.48f, -4.0593f, 19.6f, 1.46f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, -45.0f);
		c(frame, "tapa_2", 245, 292, -9.8f, -1.46f, -4.0593f, 19.6f, 1.42f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, -90.0f);
		c(frame, "tapa_3", 187, 292, -9.8f, -1.44f, -4.0593f, 19.6f, 1.38f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, -135.0f);
		c(frame, "poste_0", 126, 230, 7.0701f, -41.5f, 18.2f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_1", 117, 230, 18.2f, -41.5f, 7.0701f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_2", 108, 230, 18.2f, -41.5f, -8.6701f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_3", 99, 230, 7.0701f, -41.5f, -19.8f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_4", 90, 230, -8.6701f, -41.5f, -19.8f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_5", 81, 230, -19.8f, -41.5f, -8.6701f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_6", 72, 230, -19.8f, -41.5f, 7.0701f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_7", 63, 230, -8.6701f, -41.5f, 18.2f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);

		// --- dome
		ModelPartData dome_lado0 = dome.addChild("lado_0", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(0), 0));
		c(dome_lado0, "cristal_pared_0", 350, 252, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado0, "cristal_techo_0_0", 37, 355, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado0, "cristal_techo_1_0", 290, 355, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado0, "cristal_techo_2_0", 1, 363, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado1 = dome.addChild("lado_1", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-45), 0));
		c(dome_lado1, "cristal_pared_1", 315, 252, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado1, "cristal_techo_0_1", 1, 355, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado1, "cristal_techo_1_1", 259, 355, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado1, "cristal_techo_2_1", 477, 355, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado2 = dome.addChild("lado_2", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-90), 0));
		c(dome_lado2, "cristal_pared_2", 280, 252, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado2, "cristal_techo_0_2", 477, 347, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado2, "cristal_techo_1_2", 228, 355, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado2, "cristal_techo_2_2", 451, 355, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado3 = dome.addChild("lado_3", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-135), 0));
		c(dome_lado3, "cristal_pared_3", 245, 252, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado3, "cristal_techo_0_3", 441, 347, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado3, "cristal_techo_1_3", 197, 355, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado3, "cristal_techo_2_3", 425, 355, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado4 = dome.addChild("lado_4", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-180), 0));
		c(dome_lado4, "cristal_pared_4", 210, 252, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado4, "cristal_techo_0_4", 405, 347, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado4, "cristal_techo_1_4", 166, 355, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado4, "cristal_techo_2_4", 399, 355, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado5 = dome.addChild("lado_5", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-225), 0));
		c(dome_lado5, "cristal_pared_5", 175, 252, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado5, "cristal_techo_0_5", 369, 347, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado5, "cristal_techo_1_5", 135, 355, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado5, "cristal_techo_2_5", 373, 355, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado6 = dome.addChild("lado_6", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-270), 0));
		c(dome_lado6, "cristal_pared_6", 140, 252, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado6, "cristal_techo_0_6", 333, 347, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado6, "cristal_techo_1_6", 104, 355, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado6, "cristal_techo_2_6", 347, 355, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado7 = dome.addChild("lado_7", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-315), 0));
		c(dome_lado7, "cristal_pared_7", 105, 252, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado7, "cristal_techo_0_7", 297, 347, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado7, "cristal_techo_1_7", 73, 355, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado7, "cristal_techo_2_7", 321, 355, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);

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
