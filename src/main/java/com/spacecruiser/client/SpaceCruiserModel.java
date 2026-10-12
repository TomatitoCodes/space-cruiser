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
		c(body, "nucleo_inf_0", 1, 256, -18.0f, -4.0f, -7.4558f, 36.0f, 4.0f, 14.9117f, 0.0f, -8.0f, 0.0f, 0.0f, 0.0f);
		c(body, "nucleo_inf_1", 343, 234, -18.0f, -3.98f, -7.4558f, 36.0f, 3.96f, 14.9117f, 0.0f, -8.0f, 0.0f, 0.0f, -45.0f);
		c(body, "nucleo_inf_2", 239, 234, -18.0f, -3.96f, -7.4558f, 36.0f, 3.92f, 14.9117f, 0.0f, -8.0f, 0.0f, 0.0f, -90.0f);
		c(body, "nucleo_inf_3", 135, 234, -18.0f, -3.94f, -7.4558f, 36.0f, 3.88f, 14.9117f, 0.0f, -8.0f, 0.0f, 0.0f, -135.0f);
		c(body, "borde_0", 207, 38, -36.0f, -5.0f, -14.9117f, 72.0f, 5.0f, 29.8234f, 0.0f, -12.0f, 0.0f, 0.0f, 0.0f);
		c(body, "borde_1", 1, 38, -36.0f, -4.98f, -14.9117f, 72.0f, 4.96f, 29.8234f, 0.0f, -12.0f, 0.0f, 0.0f, -45.0f);
		c(body, "borde_2", 207, 1, -36.0f, -4.96f, -14.9117f, 72.0f, 4.92f, 29.8234f, 0.0f, -12.0f, 0.0f, 0.0f, -90.0f);
		c(body, "borde_3", 1, 1, -36.0f, -4.94f, -14.9117f, 72.0f, 4.88f, 29.8234f, 0.0f, -12.0f, 0.0f, 0.0f, -135.0f);
		c(body, "nucleo_sup_0", 1, 183, -25.0f, -2.5f, -10.3553f, 50.0f, 2.5f, 20.7107f, 0.0f, -17.0f, 0.0f, 0.0f, 0.0f);
		c(body, "nucleo_sup_1", 289, 157, -25.0f, -2.48f, -10.3553f, 50.0f, 2.46f, 20.7107f, 0.0f, -17.0f, 0.0f, 0.0f, -45.0f);
		c(body, "nucleo_sup_2", 145, 157, -25.0f, -2.46f, -10.3553f, 50.0f, 2.42f, 20.7107f, 0.0f, -17.0f, 0.0f, 0.0f, -90.0f);
		c(body, "nucleo_sup_3", 1, 157, -25.0f, -2.44f, -10.3553f, 50.0f, 2.38f, 20.7107f, 0.0f, -17.0f, 0.0f, 0.0f, -135.0f);
		c(body, "relleno_sup_3_0", 168, 104, -29.0f, -0.3263f, -12.0122f, 58.0f, 0.3263f, 24.0244f, 0.0f, -17.0f, 0.0f, 0.0f, 0.0f);
		c(body, "relleno_sup_3_1", 1, 104, -29.0f, -0.3063f, -12.0122f, 58.0f, 0.2863f, 24.0244f, 0.0f, -17.0f, 0.0f, 0.0f, -45.0f);
		c(body, "relleno_sup_3_2", 250, 75, -29.0f, -0.2863f, -12.0122f, 58.0f, 0.2463f, 24.0244f, 0.0f, -17.0f, 0.0f, 0.0f, -90.0f);
		c(body, "relleno_sup_3_3", 83, 75, -29.0f, -0.2663f, -12.0122f, 58.0f, 0.2063f, 24.0244f, 0.0f, -17.0f, 0.0f, 0.0f, -135.0f);
		c(body, "relleno_sup_4_0", 307, 131, -26.6667f, -1.1596f, -11.0457f, 53.3333f, 1.1596f, 22.0914f, 0.0f, -17.0f, 0.0f, 0.0f, 0.0f);
		c(body, "relleno_sup_4_1", 154, 131, -26.6667f, -1.1396f, -11.0457f, 53.3333f, 1.1196f, 22.0914f, 0.0f, -17.0f, 0.0f, 0.0f, -45.0f);
		c(body, "relleno_sup_4_2", 1, 131, -26.6667f, -1.1196f, -11.0457f, 53.3333f, 1.0796f, 22.0914f, 0.0f, -17.0f, 0.0f, 0.0f, -90.0f);
		c(body, "relleno_sup_4_3", 335, 104, -26.6667f, -1.0996f, -11.0457f, 53.3333f, 1.0396f, 22.0914f, 0.0f, -17.0f, 0.0f, 0.0f, -135.0f);
		c(body, "relleno_sup_5_0", 141, 209, -24.3333f, -1.9929f, -10.0792f, 48.6667f, 1.9929f, 20.1584f, 0.0f, -17.0f, 0.0f, 0.0f, 0.0f);
		c(body, "relleno_sup_5_1", 1, 209, -24.3333f, -1.9729f, -10.0792f, 48.6667f, 1.9529f, 20.1584f, 0.0f, -17.0f, 0.0f, 0.0f, -45.0f);
		c(body, "relleno_sup_5_2", 285, 183, -24.3333f, -1.9529f, -10.0792f, 48.6667f, 1.9129f, 20.1584f, 0.0f, -17.0f, 0.0f, 0.0f, -90.0f);
		c(body, "relleno_sup_5_3", 145, 183, -24.3333f, -1.9329f, -10.0792f, 48.6667f, 1.8729f, 20.1584f, 0.0f, -17.0f, 0.0f, 0.0f, -135.0f);
		c(body, "asiento_base", 365, 295, -5.0f, -23.0f, -5.0f, 10.0f, 3.5f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "asiento_respaldo", 279, 277, -5.0f, -36.0f, -5.0f, 10.0f, 13.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "consola", 409, 277, -9.0f, -26.0f, 12.0f, 18.0f, 6.5f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "columna_volante", 335, 407, -1.5f, -28.0f, 15.0f, 3.0f, 2.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "volante", 399, 295, -5.0f, -29.5f, 13.0f, 10.0f, 1.5f, 5.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_arriba_izq", 1, 234, -36.0f, -37.0f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_abajo_izq", 405, 209, -36.0f, -26.5f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_interior_izq", 42, 75, -25.5f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_exterior_izq", 1, 75, -36.0f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tapa_delantera_izq", 245, 277, -37.0f, -38.0f, 9.0f, 14.0f, 14.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "asa_delantera_izq", 491, 407, -32.0f, -32.0f, 11.0f, 4.0f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_arriba_izq", 446, 407, -37.0f, -38.0f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_abajo_izq", 413, 407, -37.0f, -25.5f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_interior_izq", 483, 277, -24.5f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_exterior_izq", 475, 277, -37.0f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "soporte_izq", 1, 295, -24.0f, -34.0f, -3.0f, 4.0f, 6.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_arriba_der", 343, 209, 24.0f, -37.0f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_abajo_der", 281, 209, 24.0f, -26.5f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_interior_der", 454, 38, 24.0f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_exterior_der", 413, 38, 34.5f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tapa_delantera_der", 211, 277, 23.0f, -38.0f, 9.0f, 14.0f, 14.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "asa_delantera_der", 479, 407, 28.0f, -32.0f, 11.0f, 4.0f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_arriba_der", 380, 407, 23.0f, -38.0f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_abajo_der", 347, 407, 23.0f, -25.5f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_interior_der", 467, 277, 23.0f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_exterior_der", 459, 277, 35.5f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "soporte_der", 491, 277, 20.0f, -34.0f, -3.0f, 4.0f, 6.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_0", 383, 277, -17.0f, -10.0f, 11.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_1", 357, 277, 11.0f, -10.0f, 11.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_2", 331, 277, -17.0f, -10.0f, -17.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_3", 305, 277, 11.0f, -10.0f, -17.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado0 = body.addChild("lado_0", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(0), 0));
		c(body_lado0, "pend_sup_0_0", 379, 365, -13.8952f, 0.0f, -2.4777f, 27.7904f, 2.0f, 2.4777f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		c(body_lado0, "pend_sup_1_0", 355, 372, -12.9287f, 0.0f, -2.4777f, 25.8574f, 2.0f, 2.4777f, 0.0f, -17.8333f, 33.6667f, -19.6538f, 0.0f);
		c(body_lado0, "pend_sup_2_0", 331, 379, -11.9622f, 0.0f, -2.4777f, 23.9244f, 2.0f, 2.4777f, 0.0f, -18.6667f, 31.3333f, -19.6538f, 0.0f);
		c(body_lado0, "pend_sup_3_0", 256, 386, -10.9957f, 0.0f, -2.4777f, 21.9914f, 2.0f, 2.4777f, 0.0f, -19.5f, 29.0f, -19.6538f, 0.0f);
		c(body_lado0, "pend_sup_4_0", 145, 393, -10.0292f, 0.0f, -2.4777f, 20.0584f, 2.0f, 2.4777f, 0.0f, -20.3333f, 26.6667f, -19.6538f, 0.0f);
		c(body_lado0, "pend_sup_5_0", 1, 400, -9.0627f, 0.0f, -2.4777f, 18.1254f, 2.0f, 2.4777f, 0.0f, -21.1667f, 24.3333f, -19.6538f, 0.0f);
		c(body_lado0, "pend_inf_0_0", 379, 309, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_1_0", 349, 317, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_2_0", 266, 325, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_3_0", 145, 333, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_4_0", 1, 341, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_5_0", 310, 341, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado0, "anillo_plano_0_0", 325, 400, -8.9056f, -22.0f, 21.0f, 17.8112f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado0, "anillo_plano_1_0", 153, 407, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado0, "borde_cupula_0", 112, 349, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado1 = body.addChild("lado_1", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-45), 0));
		c(body_lado1, "pend_sup_0_1", 316, 365, -13.8952f, 0.0f, -2.4777f, 27.7904f, 2.0f, 2.4777f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		c(body_lado1, "pend_sup_1_1", 296, 372, -12.9287f, 0.0f, -2.4777f, 25.8574f, 2.0f, 2.4777f, 0.0f, -17.8333f, 33.6667f, -19.6538f, 0.0f);
		c(body_lado1, "pend_sup_2_1", 276, 379, -11.9622f, 0.0f, -2.4777f, 23.9244f, 2.0f, 2.4777f, 0.0f, -18.6667f, 31.3333f, -19.6538f, 0.0f);
		c(body_lado1, "pend_sup_3_1", 205, 386, -10.9957f, 0.0f, -2.4777f, 21.9914f, 2.0f, 2.4777f, 0.0f, -19.5f, 29.0f, -19.6538f, 0.0f);
		c(body_lado1, "pend_sup_4_1", 97, 393, -10.0292f, 0.0f, -2.4777f, 20.0584f, 2.0f, 2.4777f, 0.0f, -20.3333f, 26.6667f, -19.6538f, 0.0f);
		c(body_lado1, "pend_sup_5_1", 457, 393, -9.0627f, 0.0f, -2.4777f, 18.1254f, 2.0f, 2.4777f, 0.0f, -21.1667f, 24.3333f, -19.6538f, 0.0f);
		c(body_lado1, "pend_inf_0_1", 316, 309, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_1_1", 291, 317, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_2_1", 213, 325, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_3_1", 97, 333, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_4_1", 451, 333, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_5_1", 272, 341, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado1, "anillo_plano_0_1", 285, 400, -8.9056f, -22.0f, 21.0f, 17.8112f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado1, "anillo_plano_1_1", 115, 407, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado1, "borde_cupula_1", 75, 349, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado2 = body.addChild("lado_2", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-90), 0));
		c(body_lado2, "pend_sup_0_2", 253, 365, -13.8952f, 0.0f, -2.4777f, 27.7904f, 2.0f, 2.4777f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		c(body_lado2, "pend_sup_1_2", 237, 372, -12.9287f, 0.0f, -2.4777f, 25.8574f, 2.0f, 2.4777f, 0.0f, -17.8333f, 33.6667f, -19.6538f, 0.0f);
		c(body_lado2, "pend_sup_2_2", 221, 379, -11.9622f, 0.0f, -2.4777f, 23.9244f, 2.0f, 2.4777f, 0.0f, -18.6667f, 31.3333f, -19.6538f, 0.0f);
		c(body_lado2, "pend_sup_3_2", 154, 386, -10.9957f, 0.0f, -2.4777f, 21.9914f, 2.0f, 2.4777f, 0.0f, -19.5f, 29.0f, -19.6538f, 0.0f);
		c(body_lado2, "pend_sup_4_2", 49, 393, -10.0292f, 0.0f, -2.4777f, 20.0584f, 2.0f, 2.4777f, 0.0f, -20.3333f, 26.6667f, -19.6538f, 0.0f);
		c(body_lado2, "pend_sup_5_2", 413, 393, -9.0627f, 0.0f, -2.4777f, 18.1254f, 2.0f, 2.4777f, 0.0f, -21.1667f, 24.3333f, -19.6538f, 0.0f);
		c(body_lado2, "pend_inf_0_2", 253, 309, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_1_2", 233, 317, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_2_2", 160, 325, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_3_2", 49, 333, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_4_2", 408, 333, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_5_2", 234, 341, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado2, "anillo_plano_0_2", 245, 400, -8.9056f, -22.0f, 21.0f, 17.8112f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado2, "anillo_plano_1_2", 77, 407, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado2, "borde_cupula_2", 38, 349, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado3 = body.addChild("lado_3", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-135), 0));
		c(body_lado3, "pend_sup_0_3", 190, 365, -13.8952f, 0.0f, -2.4777f, 27.7904f, 2.0f, 2.4777f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		c(body_lado3, "pend_sup_1_3", 178, 372, -12.9287f, 0.0f, -2.4777f, 25.8574f, 2.0f, 2.4777f, 0.0f, -17.8333f, 33.6667f, -19.6538f, 0.0f);
		c(body_lado3, "pend_sup_2_3", 166, 379, -11.9622f, 0.0f, -2.4777f, 23.9244f, 2.0f, 2.4777f, 0.0f, -18.6667f, 31.3333f, -19.6538f, 0.0f);
		c(body_lado3, "pend_sup_3_3", 103, 386, -10.9957f, 0.0f, -2.4777f, 21.9914f, 2.0f, 2.4777f, 0.0f, -19.5f, 29.0f, -19.6538f, 0.0f);
		c(body_lado3, "pend_sup_4_3", 1, 393, -10.0292f, 0.0f, -2.4777f, 20.0584f, 2.0f, 2.4777f, 0.0f, -20.3333f, 26.6667f, -19.6538f, 0.0f);
		c(body_lado3, "pend_sup_5_3", 369, 393, -9.0627f, 0.0f, -2.4777f, 18.1254f, 2.0f, 2.4777f, 0.0f, -21.1667f, 24.3333f, -19.6538f, 0.0f);
		c(body_lado3, "pend_inf_0_3", 190, 309, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_1_3", 175, 317, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_2_3", 107, 325, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_3_3", 1, 333, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_4_3", 365, 333, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_5_3", 196, 341, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado3, "anillo_plano_0_3", 205, 400, -8.9056f, -22.0f, 21.0f, 17.8112f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado3, "anillo_plano_1_3", 39, 407, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado3, "borde_cupula_3", 1, 349, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado4 = body.addChild("lado_4", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-180), 0));
		c(body_lado4, "pend_sup_0_4", 127, 365, -13.8952f, 0.0f, -2.4777f, 27.7904f, 2.0f, 2.4777f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		c(body_lado4, "pend_sup_1_4", 119, 372, -12.9287f, 0.0f, -2.4777f, 25.8574f, 2.0f, 2.4777f, 0.0f, -17.8333f, 33.6667f, -19.6538f, 0.0f);
		c(body_lado4, "pend_sup_2_4", 111, 379, -11.9622f, 0.0f, -2.4777f, 23.9244f, 2.0f, 2.4777f, 0.0f, -18.6667f, 31.3333f, -19.6538f, 0.0f);
		c(body_lado4, "pend_sup_3_4", 52, 386, -10.9957f, 0.0f, -2.4777f, 21.9914f, 2.0f, 2.4777f, 0.0f, -19.5f, 29.0f, -19.6538f, 0.0f);
		c(body_lado4, "pend_sup_4_4", 451, 386, -10.0292f, 0.0f, -2.4777f, 20.0584f, 2.0f, 2.4777f, 0.0f, -20.3333f, 26.6667f, -19.6538f, 0.0f);
		c(body_lado4, "pend_sup_5_4", 325, 393, -9.0627f, 0.0f, -2.4777f, 18.1254f, 2.0f, 2.4777f, 0.0f, -21.1667f, 24.3333f, -19.6538f, 0.0f);
		c(body_lado4, "pend_inf_0_4", 127, 309, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_1_4", 117, 317, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_2_4", 54, 325, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_3_4", 463, 325, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_4_4", 322, 333, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_5_4", 158, 341, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado4, "anillo_plano_0_4", 165, 400, -8.9056f, -22.0f, 21.0f, 17.8112f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado4, "anillo_plano_1_4", 1, 407, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado4, "borde_cupula_4", 459, 341, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado5 = body.addChild("lado_5", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-225), 0));
		c(body_lado5, "pend_sup_0_5", 64, 365, -13.8952f, 0.0f, -2.4777f, 27.7904f, 2.0f, 2.4777f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		c(body_lado5, "pend_sup_1_5", 60, 372, -12.9287f, 0.0f, -2.4777f, 25.8574f, 2.0f, 2.4777f, 0.0f, -17.8333f, 33.6667f, -19.6538f, 0.0f);
		c(body_lado5, "pend_sup_2_5", 56, 379, -11.9622f, 0.0f, -2.4777f, 23.9244f, 2.0f, 2.4777f, 0.0f, -18.6667f, 31.3333f, -19.6538f, 0.0f);
		c(body_lado5, "pend_sup_3_5", 1, 386, -10.9957f, 0.0f, -2.4777f, 21.9914f, 2.0f, 2.4777f, 0.0f, -19.5f, 29.0f, -19.6538f, 0.0f);
		c(body_lado5, "pend_sup_4_5", 403, 386, -10.0292f, 0.0f, -2.4777f, 20.0584f, 2.0f, 2.4777f, 0.0f, -20.3333f, 26.6667f, -19.6538f, 0.0f);
		c(body_lado5, "pend_sup_5_5", 281, 393, -9.0627f, 0.0f, -2.4777f, 18.1254f, 2.0f, 2.4777f, 0.0f, -21.1667f, 24.3333f, -19.6538f, 0.0f);
		c(body_lado5, "pend_inf_0_5", 64, 309, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_1_5", 59, 317, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_2_5", 1, 325, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_3_5", 415, 325, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_4_5", 279, 333, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_5_5", 120, 341, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado5, "anillo_plano_0_5", 125, 400, -8.9056f, -22.0f, 21.0f, 17.8112f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado5, "anillo_plano_1_5", 441, 400, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado5, "borde_cupula_5", 422, 341, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado6 = body.addChild("lado_6", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-270), 0));
		c(body_lado6, "pend_sup_0_6", 1, 365, -13.8952f, 0.0f, -2.4777f, 27.7904f, 2.0f, 2.4777f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		c(body_lado6, "pend_sup_1_6", 1, 372, -12.9287f, 0.0f, -2.4777f, 25.8574f, 2.0f, 2.4777f, 0.0f, -17.8333f, 33.6667f, -19.6538f, 0.0f);
		c(body_lado6, "pend_sup_2_6", 1, 379, -11.9622f, 0.0f, -2.4777f, 23.9244f, 2.0f, 2.4777f, 0.0f, -18.6667f, 31.3333f, -19.6538f, 0.0f);
		c(body_lado6, "pend_sup_3_6", 437, 379, -10.9957f, 0.0f, -2.4777f, 21.9914f, 2.0f, 2.4777f, 0.0f, -19.5f, 29.0f, -19.6538f, 0.0f);
		c(body_lado6, "pend_sup_4_6", 355, 386, -10.0292f, 0.0f, -2.4777f, 20.0584f, 2.0f, 2.4777f, 0.0f, -20.3333f, 26.6667f, -19.6538f, 0.0f);
		c(body_lado6, "pend_sup_5_6", 237, 393, -9.0627f, 0.0f, -2.4777f, 18.1254f, 2.0f, 2.4777f, 0.0f, -21.1667f, 24.3333f, -19.6538f, 0.0f);
		c(body_lado6, "pend_inf_0_6", 1, 309, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_1_6", 1, 317, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_2_6", 460, 317, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_3_6", 367, 325, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_4_6", 236, 333, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_5_6", 82, 341, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado6, "anillo_plano_0_6", 85, 400, -8.9056f, -22.0f, 21.0f, 17.8112f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado6, "anillo_plano_1_6", 403, 400, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado6, "borde_cupula_6", 385, 341, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado7 = body.addChild("lado_7", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-315), 0));
		c(body_lado7, "pend_sup_0_7", 395, 357, -13.8952f, 0.0f, -2.4777f, 27.7904f, 2.0f, 2.4777f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		c(body_lado7, "pend_sup_1_7", 442, 365, -12.9287f, 0.0f, -2.4777f, 25.8574f, 2.0f, 2.4777f, 0.0f, -17.8333f, 33.6667f, -19.6538f, 0.0f);
		c(body_lado7, "pend_sup_2_7", 414, 372, -11.9622f, 0.0f, -2.4777f, 23.9244f, 2.0f, 2.4777f, 0.0f, -18.6667f, 31.3333f, -19.6538f, 0.0f);
		c(body_lado7, "pend_sup_3_7", 386, 379, -10.9957f, 0.0f, -2.4777f, 21.9914f, 2.0f, 2.4777f, 0.0f, -19.5f, 29.0f, -19.6538f, 0.0f);
		c(body_lado7, "pend_sup_4_7", 307, 386, -10.0292f, 0.0f, -2.4777f, 20.0584f, 2.0f, 2.4777f, 0.0f, -20.3333f, 26.6667f, -19.6538f, 0.0f);
		c(body_lado7, "pend_sup_5_7", 193, 393, -9.0627f, 0.0f, -2.4777f, 18.1254f, 2.0f, 2.4777f, 0.0f, -21.1667f, 24.3333f, -19.6538f, 0.0f);
		c(body_lado7, "pend_inf_0_7", 431, 295, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_1_7", 442, 309, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_2_7", 407, 317, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_3_7", 319, 325, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_4_7", 193, 333, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_5_7", 44, 341, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado7, "anillo_plano_0_7", 45, 400, -8.9056f, -22.0f, 21.0f, 17.8112f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado7, "anillo_plano_1_7", 365, 400, -8.4914f, -22.0f, 20.0f, 16.9828f, 2.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body_lado7, "borde_cupula_7", 348, 341, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);

		// --- glow
		c(glow, "fondo_brasa_izq", 46, 295, -34.5f, -35.5f, -7.5f, 9.0f, 9.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_1_izq", 117, 295, -29.5f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_2_izq", 101, 295, -33.0f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fondo_brasa_der", 23, 295, 25.5f, -35.5f, -7.5f, 9.0f, 9.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_1_der", 85, 295, 27.0f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_2_der", 69, 295, 30.5f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData glow_lado0 = glow.addChild("lado_0", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(0), 0));
		c(glow_lado0, "franja_borde_0", 317, 407, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado0, "franja_pendiente_0", 169, 277, -2.5f, -0.6f, -14.8661f, 5.0f, 0.6f, 14.8661f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		ModelPartData glow_lado1 = glow.addChild("lado_1", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-45), 0));
		c(glow_lado1, "franja_borde_1", 299, 407, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado1, "franja_pendiente_1", 127, 277, -2.5f, -0.6f, -14.8661f, 5.0f, 0.6f, 14.8661f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		ModelPartData glow_lado2 = glow.addChild("lado_2", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-90), 0));
		c(glow_lado2, "franja_borde_2", 281, 407, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado2, "franja_pendiente_2", 85, 277, -2.5f, -0.6f, -14.8661f, 5.0f, 0.6f, 14.8661f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		ModelPartData glow_lado3 = glow.addChild("lado_3", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-135), 0));
		c(glow_lado3, "franja_borde_3", 263, 407, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado3, "franja_pendiente_3", 43, 277, -2.5f, -0.6f, -14.8661f, 5.0f, 0.6f, 14.8661f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		ModelPartData glow_lado4 = glow.addChild("lado_4", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-180), 0));
		c(glow_lado4, "franja_borde_4", 245, 407, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado4, "franja_pendiente_4", 1, 277, -2.5f, -0.6f, -14.8661f, 5.0f, 0.6f, 14.8661f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		ModelPartData glow_lado5 = glow.addChild("lado_5", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-225), 0));
		c(glow_lado5, "franja_borde_5", 227, 407, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado5, "franja_pendiente_5", 469, 256, -2.5f, -0.6f, -14.8661f, 5.0f, 0.6f, 14.8661f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		ModelPartData glow_lado6 = glow.addChild("lado_6", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-270), 0));
		c(glow_lado6, "franja_borde_6", 209, 407, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado6, "franja_pendiente_6", 427, 256, -2.5f, -0.6f, -14.8661f, 5.0f, 0.6f, 14.8661f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);
		ModelPartData glow_lado7 = glow.addChild("lado_7", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-315), 0));
		c(glow_lado7, "franja_borde_7", 191, 407, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado7, "franja_pendiente_7", 385, 256, -2.5f, -0.6f, -14.8661f, 5.0f, 0.6f, 14.8661f, 0.0f, -17.0f, 36.0f, -19.6538f, 0.0f);

		// --- frame
		c(frame, "tapa_0", 307, 295, -9.8f, -1.5f, -4.0593f, 19.6f, 1.5f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, 0.0f);
		c(frame, "tapa_1", 249, 295, -9.8f, -1.48f, -4.0593f, 19.6f, 1.46f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, -45.0f);
		c(frame, "tapa_2", 191, 295, -9.8f, -1.46f, -4.0593f, 19.6f, 1.42f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, -90.0f);
		c(frame, "tapa_3", 133, 295, -9.8f, -1.44f, -4.0593f, 19.6f, 1.38f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, -135.0f);
		c(frame, "poste_0", 126, 234, 7.0701f, -41.5f, 18.2f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_1", 117, 234, 18.2f, -41.5f, 7.0701f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_2", 108, 234, 18.2f, -41.5f, -8.6701f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_3", 99, 234, 7.0701f, -41.5f, -19.8f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_4", 90, 234, -8.6701f, -41.5f, -19.8f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_5", 81, 234, -19.8f, -41.5f, -8.6701f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_6", 72, 234, -19.8f, -41.5f, 7.0701f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_7", 63, 234, -8.6701f, -41.5f, 18.2f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);

		// --- dome
		ModelPartData dome_lado0 = dome.addChild("lado_0", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(0), 0));
		c(dome_lado0, "cristal_pared_0", 350, 256, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado0, "cristal_techo_0_0", 401, 349, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado0, "cristal_techo_1_0", 156, 357, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado0, "cristal_techo_2_0", 369, 357, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado1 = dome.addChild("lado_1", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-45), 0));
		c(dome_lado1, "cristal_pared_1", 315, 256, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado1, "cristal_techo_0_1", 365, 349, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado1, "cristal_techo_1_1", 125, 357, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado1, "cristal_techo_2_1", 343, 357, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado2 = dome.addChild("lado_2", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-90), 0));
		c(dome_lado2, "cristal_pared_2", 280, 256, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado2, "cristal_techo_0_2", 329, 349, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado2, "cristal_techo_1_2", 94, 357, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado2, "cristal_techo_2_2", 317, 357, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado3 = dome.addChild("lado_3", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-135), 0));
		c(dome_lado3, "cristal_pared_3", 245, 256, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado3, "cristal_techo_0_3", 293, 349, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado3, "cristal_techo_1_3", 63, 357, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado3, "cristal_techo_2_3", 291, 357, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado4 = dome.addChild("lado_4", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-180), 0));
		c(dome_lado4, "cristal_pared_4", 210, 256, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado4, "cristal_techo_0_4", 257, 349, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado4, "cristal_techo_1_4", 32, 357, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado4, "cristal_techo_2_4", 265, 357, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado5 = dome.addChild("lado_5", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-225), 0));
		c(dome_lado5, "cristal_pared_5", 175, 256, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado5, "cristal_techo_0_5", 221, 349, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado5, "cristal_techo_1_5", 1, 357, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado5, "cristal_techo_2_5", 239, 357, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado6 = dome.addChild("lado_6", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-270), 0));
		c(dome_lado6, "cristal_pared_6", 140, 256, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado6, "cristal_techo_0_6", 185, 349, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado6, "cristal_techo_1_6", 468, 349, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado6, "cristal_techo_2_6", 213, 357, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado7 = dome.addChild("lado_7", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-315), 0));
		c(dome_lado7, "cristal_pared_7", 105, 256, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado7, "cristal_techo_0_7", 149, 349, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado7, "cristal_techo_1_7", 437, 349, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado7, "cristal_techo_2_7", 187, 357, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);

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
