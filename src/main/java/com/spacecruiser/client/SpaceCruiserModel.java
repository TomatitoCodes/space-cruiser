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
		c(body, "nucleo_inf_0", 385, 229, -18.0f, -4.0f, -7.4558f, 36.0f, 4.0f, 14.9117f, 0.0f, -8.0f, 0.0f, 0.0f, 0.0f);
		c(body, "nucleo_inf_1", 281, 229, -18.0f, -3.98f, -7.4558f, 36.0f, 3.96f, 14.9117f, 0.0f, -8.0f, 0.0f, 0.0f, -45.0f);
		c(body, "nucleo_inf_2", 177, 229, -18.0f, -3.96f, -7.4558f, 36.0f, 3.92f, 14.9117f, 0.0f, -8.0f, 0.0f, 0.0f, -90.0f);
		c(body, "nucleo_inf_3", 73, 229, -18.0f, -3.94f, -7.4558f, 36.0f, 3.88f, 14.9117f, 0.0f, -8.0f, 0.0f, 0.0f, -135.0f);
		c(body, "borde_0", 207, 38, -36.0f, -5.0f, -14.9117f, 72.0f, 5.0f, 29.8234f, 0.0f, -12.0f, 0.0f, 0.0f, 0.0f);
		c(body, "borde_1", 1, 38, -36.0f, -4.98f, -14.9117f, 72.0f, 4.96f, 29.8234f, 0.0f, -12.0f, 0.0f, 0.0f, -45.0f);
		c(body, "borde_2", 207, 1, -36.0f, -4.96f, -14.9117f, 72.0f, 4.92f, 29.8234f, 0.0f, -12.0f, 0.0f, 0.0f, -90.0f);
		c(body, "borde_3", 1, 1, -36.0f, -4.94f, -14.9117f, 72.0f, 4.88f, 29.8234f, 0.0f, -12.0f, 0.0f, 0.0f, -135.0f);
		c(body, "nucleo_sup_0", 289, 130, -25.0f, -2.5f, -10.3553f, 50.0f, 2.5f, 20.7107f, 0.0f, -17.0f, 0.0f, 0.0f, 0.0f);
		c(body, "nucleo_sup_1", 145, 130, -25.0f, -2.48f, -10.3553f, 50.0f, 2.46f, 20.7107f, 0.0f, -17.0f, 0.0f, 0.0f, -45.0f);
		c(body, "nucleo_sup_2", 1, 130, -25.0f, -2.46f, -10.3553f, 50.0f, 2.42f, 20.7107f, 0.0f, -17.0f, 0.0f, 0.0f, -90.0f);
		c(body, "nucleo_sup_3", 323, 104, -25.0f, -2.44f, -10.3553f, 50.0f, 2.38f, 20.7107f, 0.0f, -17.0f, 0.0f, 0.0f, -135.0f);
		c(body, "relleno_sup_3_0", 162, 104, -28.0f, -0.3546f, -11.598f, 56.0f, 0.3546f, 23.196f, 0.0f, -17.0f, 0.0f, 0.0f, 0.0f);
		c(body, "relleno_sup_3_1", 1, 104, -28.0f, -0.3346f, -11.598f, 56.0f, 0.3146f, 23.196f, 0.0f, -17.0f, 0.0f, 0.0f, -45.0f);
		c(body, "relleno_sup_3_2", 244, 75, -28.0f, -0.3146f, -11.598f, 56.0f, 0.2746f, 23.196f, 0.0f, -17.0f, 0.0f, 0.0f, -90.0f);
		c(body, "relleno_sup_3_3", 83, 75, -28.0f, -0.2946f, -11.598f, 56.0f, 0.2346f, 23.196f, 0.0f, -17.0f, 0.0f, 0.0f, -135.0f);
		c(body, "relleno_sup_4_0", 1, 181, -25.3333f, -1.188f, -10.4934f, 50.6667f, 1.188f, 20.9868f, 0.0f, -17.0f, 0.0f, 0.0f, 0.0f);
		c(body, "relleno_sup_4_1", 293, 156, -25.3333f, -1.168f, -10.4934f, 50.6667f, 1.148f, 20.9868f, 0.0f, -17.0f, 0.0f, 0.0f, -45.0f);
		c(body, "relleno_sup_4_2", 147, 156, -25.3333f, -1.148f, -10.4934f, 50.6667f, 1.108f, 20.9868f, 0.0f, -17.0f, 0.0f, 0.0f, -90.0f);
		c(body, "relleno_sup_4_3", 1, 156, -25.3333f, -1.128f, -10.4934f, 50.6667f, 1.068f, 20.9868f, 0.0f, -17.0f, 0.0f, 0.0f, -135.0f);
		c(body, "relleno_sup_5_0", 132, 206, -22.6667f, -2.0213f, -9.3888f, 45.3333f, 2.0213f, 18.7777f, 0.0f, -17.0f, 0.0f, 0.0f, 0.0f);
		c(body, "relleno_sup_5_1", 1, 206, -22.6667f, -2.0013f, -9.3888f, 45.3333f, 1.9813f, 18.7777f, 0.0f, -17.0f, 0.0f, 0.0f, -45.0f);
		c(body, "relleno_sup_5_2", 278, 181, -22.6667f, -1.9813f, -9.3888f, 45.3333f, 1.9413f, 18.7777f, 0.0f, -17.0f, 0.0f, 0.0f, -90.0f);
		c(body, "relleno_sup_5_3", 147, 181, -22.6667f, -1.9613f, -9.3888f, 45.3333f, 1.9013f, 18.7777f, 0.0f, -17.0f, 0.0f, 0.0f, -135.0f);
		c(body, "asiento_base", 297, 292, -5.0f, -23.0f, -5.0f, 10.0f, 3.5f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "asiento_respaldo", 207, 272, -5.0f, -36.0f, -5.0f, 10.0f, 13.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "consola", 337, 272, -9.0f, -26.0f, 12.0f, 18.0f, 6.5f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "columna_volante", 109, 396, -1.5f, -28.0f, 15.0f, 3.0f, 2.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "volante", 331, 292, -5.0f, -29.5f, 13.0f, 10.0f, 1.5f, 5.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_arriba_izq", 449, 206, -36.0f, -37.0f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_abajo_izq", 387, 206, -36.0f, -26.5f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_interior_izq", 42, 75, -25.5f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_exterior_izq", 1, 75, -36.0f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tapa_delantera_izq", 173, 272, -37.0f, -38.0f, 9.0f, 14.0f, 14.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "asa_delantera_izq", 265, 396, -32.0f, -32.0f, 11.0f, 4.0f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_arriba_izq", 220, 396, -37.0f, -38.0f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_abajo_izq", 187, 396, -37.0f, -25.5f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_interior_izq", 411, 272, -24.5f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_exterior_izq", 403, 272, -37.0f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "soporte_izq", 441, 272, -24.0f, -34.0f, -3.0f, 4.0f, 6.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_arriba_der", 325, 206, 24.0f, -37.0f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_abajo_der", 263, 206, 24.0f, -26.5f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_interior_der", 454, 38, 24.0f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_exterior_der", 413, 38, 34.5f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tapa_delantera_der", 139, 272, 23.0f, -38.0f, 9.0f, 14.0f, 14.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "asa_delantera_der", 253, 396, 28.0f, -32.0f, 11.0f, 4.0f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_arriba_der", 154, 396, 23.0f, -38.0f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_abajo_der", 121, 396, 23.0f, -25.5f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_interior_der", 395, 272, 23.0f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_exterior_der", 387, 272, 35.5f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "soporte_der", 419, 272, 20.0f, -34.0f, -3.0f, 4.0f, 6.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_0", 311, 272, -17.0f, -10.0f, 11.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_1", 285, 272, 11.0f, -10.0f, 11.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_2", 259, 272, -17.0f, -10.0f, -17.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_3", 233, 272, 11.0f, -10.0f, -17.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado0 = body.addChild("lado_0", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(0), 0));
		c(body_lado0, "pend_sup_0_0", 316, 361, -13.7571f, 0.0f, -2.7938f, 27.5142f, 2.0f, 2.7938f, 0.0f, -17.0f, 36.0f, -17.354f, 0.0f);
		c(body_lado0, "pend_sup_1_0", 296, 368, -12.6525f, 0.0f, -2.7938f, 25.3051f, 2.0f, 2.7938f, 0.0f, -17.8333f, 33.3333f, -17.354f, 0.0f);
		c(body_lado0, "pend_sup_2_0", 271, 375, -11.548f, 0.0f, -2.7938f, 23.096f, 2.0f, 2.7938f, 0.0f, -18.6667f, 30.6667f, -17.354f, 0.0f);
		c(body_lado0, "pend_sup_3_0", 201, 382, -10.4434f, 0.0f, -2.7938f, 20.8868f, 2.0f, 2.7938f, 0.0f, -19.5f, 28.0f, -17.354f, 0.0f);
		c(body_lado0, "pend_sup_4_0", 91, 389, -9.3388f, 0.0f, -2.7938f, 18.6777f, 2.0f, 2.7938f, 0.0f, -20.3333f, 25.3333f, -17.354f, 0.0f);
		c(body_lado0, "pend_sup_5_0", 423, 389, -8.2343f, 0.0f, -2.7938f, 16.4685f, 2.0f, 2.7938f, 0.0f, -21.1667f, 22.6667f, -17.354f, 0.0f);
		c(body_lado0, "pend_inf_0_0", 316, 305, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_1_0", 291, 313, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_2_0", 213, 321, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_3_0", 97, 329, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_4_0", 446, 329, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado0, "pend_inf_5_0", 267, 337, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado0, "borde_cupula_0", 75, 345, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado1 = body.addChild("lado_1", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-45), 0));
		c(body_lado1, "pend_sup_0_1", 253, 361, -13.7571f, 0.0f, -2.7938f, 27.5142f, 2.0f, 2.7938f, 0.0f, -17.0f, 36.0f, -17.354f, 0.0f);
		c(body_lado1, "pend_sup_1_1", 237, 368, -12.6525f, 0.0f, -2.7938f, 25.3051f, 2.0f, 2.7938f, 0.0f, -17.8333f, 33.3333f, -17.354f, 0.0f);
		c(body_lado1, "pend_sup_2_1", 217, 375, -11.548f, 0.0f, -2.7938f, 23.096f, 2.0f, 2.7938f, 0.0f, -18.6667f, 30.6667f, -17.354f, 0.0f);
		c(body_lado1, "pend_sup_3_1", 151, 382, -10.4434f, 0.0f, -2.7938f, 20.8868f, 2.0f, 2.7938f, 0.0f, -19.5f, 28.0f, -17.354f, 0.0f);
		c(body_lado1, "pend_sup_4_1", 46, 389, -9.3388f, 0.0f, -2.7938f, 18.6777f, 2.0f, 2.7938f, 0.0f, -20.3333f, 25.3333f, -17.354f, 0.0f);
		c(body_lado1, "pend_sup_5_1", 382, 389, -8.2343f, 0.0f, -2.7938f, 16.4685f, 2.0f, 2.7938f, 0.0f, -21.1667f, 22.6667f, -17.354f, 0.0f);
		c(body_lado1, "pend_inf_0_1", 253, 305, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_1_1", 233, 313, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_2_1", 160, 321, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_3_1", 49, 329, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_4_1", 403, 329, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado1, "pend_inf_5_1", 229, 337, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado1, "borde_cupula_1", 38, 345, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado2 = body.addChild("lado_2", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-90), 0));
		c(body_lado2, "pend_sup_0_2", 190, 361, -13.7571f, 0.0f, -2.7938f, 27.5142f, 2.0f, 2.7938f, 0.0f, -17.0f, 36.0f, -17.354f, 0.0f);
		c(body_lado2, "pend_sup_1_2", 178, 368, -12.6525f, 0.0f, -2.7938f, 25.3051f, 2.0f, 2.7938f, 0.0f, -17.8333f, 33.3333f, -17.354f, 0.0f);
		c(body_lado2, "pend_sup_2_2", 163, 375, -11.548f, 0.0f, -2.7938f, 23.096f, 2.0f, 2.7938f, 0.0f, -18.6667f, 30.6667f, -17.354f, 0.0f);
		c(body_lado2, "pend_sup_3_2", 101, 382, -10.4434f, 0.0f, -2.7938f, 20.8868f, 2.0f, 2.7938f, 0.0f, -19.5f, 28.0f, -17.354f, 0.0f);
		c(body_lado2, "pend_sup_4_2", 1, 389, -9.3388f, 0.0f, -2.7938f, 18.6777f, 2.0f, 2.7938f, 0.0f, -20.3333f, 25.3333f, -17.354f, 0.0f);
		c(body_lado2, "pend_sup_5_2", 341, 389, -8.2343f, 0.0f, -2.7938f, 16.4685f, 2.0f, 2.7938f, 0.0f, -21.1667f, 22.6667f, -17.354f, 0.0f);
		c(body_lado2, "pend_inf_0_2", 190, 305, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_1_2", 175, 313, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_2_2", 107, 321, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_3_2", 1, 329, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_4_2", 360, 329, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado2, "pend_inf_5_2", 191, 337, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado2, "borde_cupula_2", 1, 345, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado3 = body.addChild("lado_3", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-135), 0));
		c(body_lado3, "pend_sup_0_3", 127, 361, -13.7571f, 0.0f, -2.7938f, 27.5142f, 2.0f, 2.7938f, 0.0f, -17.0f, 36.0f, -17.354f, 0.0f);
		c(body_lado3, "pend_sup_1_3", 119, 368, -12.6525f, 0.0f, -2.7938f, 25.3051f, 2.0f, 2.7938f, 0.0f, -17.8333f, 33.3333f, -17.354f, 0.0f);
		c(body_lado3, "pend_sup_2_3", 109, 375, -11.548f, 0.0f, -2.7938f, 23.096f, 2.0f, 2.7938f, 0.0f, -18.6667f, 30.6667f, -17.354f, 0.0f);
		c(body_lado3, "pend_sup_3_3", 51, 382, -10.4434f, 0.0f, -2.7938f, 20.8868f, 2.0f, 2.7938f, 0.0f, -19.5f, 28.0f, -17.354f, 0.0f);
		c(body_lado3, "pend_sup_4_3", 431, 382, -9.3388f, 0.0f, -2.7938f, 18.6777f, 2.0f, 2.7938f, 0.0f, -20.3333f, 25.3333f, -17.354f, 0.0f);
		c(body_lado3, "pend_sup_5_3", 300, 389, -8.2343f, 0.0f, -2.7938f, 16.4685f, 2.0f, 2.7938f, 0.0f, -21.1667f, 22.6667f, -17.354f, 0.0f);
		c(body_lado3, "pend_inf_0_3", 127, 305, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_1_3", 117, 313, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_2_3", 54, 321, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_3_3", 458, 321, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_4_3", 317, 329, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado3, "pend_inf_5_3", 153, 337, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado3, "borde_cupula_3", 453, 337, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado4 = body.addChild("lado_4", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-180), 0));
		c(body_lado4, "pend_sup_0_4", 64, 361, -13.7571f, 0.0f, -2.7938f, 27.5142f, 2.0f, 2.7938f, 0.0f, -17.0f, 36.0f, -17.354f, 0.0f);
		c(body_lado4, "pend_sup_1_4", 60, 368, -12.6525f, 0.0f, -2.7938f, 25.3051f, 2.0f, 2.7938f, 0.0f, -17.8333f, 33.3333f, -17.354f, 0.0f);
		c(body_lado4, "pend_sup_2_4", 55, 375, -11.548f, 0.0f, -2.7938f, 23.096f, 2.0f, 2.7938f, 0.0f, -18.6667f, 30.6667f, -17.354f, 0.0f);
		c(body_lado4, "pend_sup_3_4", 1, 382, -10.4434f, 0.0f, -2.7938f, 20.8868f, 2.0f, 2.7938f, 0.0f, -19.5f, 28.0f, -17.354f, 0.0f);
		c(body_lado4, "pend_sup_4_4", 386, 382, -9.3388f, 0.0f, -2.7938f, 18.6777f, 2.0f, 2.7938f, 0.0f, -20.3333f, 25.3333f, -17.354f, 0.0f);
		c(body_lado4, "pend_sup_5_4", 259, 389, -8.2343f, 0.0f, -2.7938f, 16.4685f, 2.0f, 2.7938f, 0.0f, -21.1667f, 22.6667f, -17.354f, 0.0f);
		c(body_lado4, "pend_inf_0_4", 64, 305, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_1_4", 59, 313, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_2_4", 1, 321, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_3_4", 410, 321, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_4_4", 274, 329, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado4, "pend_inf_5_4", 115, 337, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado4, "borde_cupula_4", 416, 337, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado5 = body.addChild("lado_5", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-225), 0));
		c(body_lado5, "pend_sup_0_5", 1, 361, -13.7571f, 0.0f, -2.7938f, 27.5142f, 2.0f, 2.7938f, 0.0f, -17.0f, 36.0f, -17.354f, 0.0f);
		c(body_lado5, "pend_sup_1_5", 1, 368, -12.6525f, 0.0f, -2.7938f, 25.3051f, 2.0f, 2.7938f, 0.0f, -17.8333f, 33.3333f, -17.354f, 0.0f);
		c(body_lado5, "pend_sup_2_5", 1, 375, -11.548f, 0.0f, -2.7938f, 23.096f, 2.0f, 2.7938f, 0.0f, -18.6667f, 30.6667f, -17.354f, 0.0f);
		c(body_lado5, "pend_sup_3_5", 425, 375, -10.4434f, 0.0f, -2.7938f, 20.8868f, 2.0f, 2.7938f, 0.0f, -19.5f, 28.0f, -17.354f, 0.0f);
		c(body_lado5, "pend_sup_4_5", 341, 382, -9.3388f, 0.0f, -2.7938f, 18.6777f, 2.0f, 2.7938f, 0.0f, -20.3333f, 25.3333f, -17.354f, 0.0f);
		c(body_lado5, "pend_sup_5_5", 218, 389, -8.2343f, 0.0f, -2.7938f, 16.4685f, 2.0f, 2.7938f, 0.0f, -21.1667f, 22.6667f, -17.354f, 0.0f);
		c(body_lado5, "pend_inf_0_5", 1, 305, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_1_5", 1, 313, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_2_5", 455, 313, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_3_5", 362, 321, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_4_5", 231, 329, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado5, "pend_inf_5_5", 77, 337, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado5, "borde_cupula_5", 379, 337, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado6 = body.addChild("lado_6", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-270), 0));
		c(body_lado6, "pend_sup_0_6", 427, 353, -13.7571f, 0.0f, -2.7938f, 27.5142f, 2.0f, 2.7938f, 0.0f, -17.0f, 36.0f, -17.354f, 0.0f);
		c(body_lado6, "pend_sup_1_6", 438, 361, -12.6525f, 0.0f, -2.7938f, 25.3051f, 2.0f, 2.7938f, 0.0f, -17.8333f, 33.3333f, -17.354f, 0.0f);
		c(body_lado6, "pend_sup_2_6", 409, 368, -11.548f, 0.0f, -2.7938f, 23.096f, 2.0f, 2.7938f, 0.0f, -18.6667f, 30.6667f, -17.354f, 0.0f);
		c(body_lado6, "pend_sup_3_6", 375, 375, -10.4434f, 0.0f, -2.7938f, 20.8868f, 2.0f, 2.7938f, 0.0f, -19.5f, 28.0f, -17.354f, 0.0f);
		c(body_lado6, "pend_sup_4_6", 296, 382, -9.3388f, 0.0f, -2.7938f, 18.6777f, 2.0f, 2.7938f, 0.0f, -20.3333f, 25.3333f, -17.354f, 0.0f);
		c(body_lado6, "pend_sup_5_6", 177, 389, -8.2343f, 0.0f, -2.7938f, 16.4685f, 2.0f, 2.7938f, 0.0f, -21.1667f, 22.6667f, -17.354f, 0.0f);
		c(body_lado6, "pend_inf_0_6", 426, 292, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_1_6", 437, 305, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_2_6", 402, 313, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_3_6", 314, 321, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_4_6", 188, 329, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado6, "pend_inf_5_6", 39, 337, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado6, "borde_cupula_6", 342, 337, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado7 = body.addChild("lado_7", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-315), 0));
		c(body_lado7, "pend_sup_0_7", 364, 353, -13.7571f, 0.0f, -2.7938f, 27.5142f, 2.0f, 2.7938f, 0.0f, -17.0f, 36.0f, -17.354f, 0.0f);
		c(body_lado7, "pend_sup_1_7", 379, 361, -12.6525f, 0.0f, -2.7938f, 25.3051f, 2.0f, 2.7938f, 0.0f, -17.8333f, 33.3333f, -17.354f, 0.0f);
		c(body_lado7, "pend_sup_2_7", 355, 368, -11.548f, 0.0f, -2.7938f, 23.096f, 2.0f, 2.7938f, 0.0f, -18.6667f, 30.6667f, -17.354f, 0.0f);
		c(body_lado7, "pend_sup_3_7", 325, 375, -10.4434f, 0.0f, -2.7938f, 20.8868f, 2.0f, 2.7938f, 0.0f, -19.5f, 28.0f, -17.354f, 0.0f);
		c(body_lado7, "pend_sup_4_7", 251, 382, -9.3388f, 0.0f, -2.7938f, 18.6777f, 2.0f, 2.7938f, 0.0f, -20.3333f, 25.3333f, -17.354f, 0.0f);
		c(body_lado7, "pend_sup_5_7", 136, 389, -8.2343f, 0.0f, -2.7938f, 16.4685f, 2.0f, 2.7938f, 0.0f, -21.1667f, 22.6667f, -17.354f, 0.0f);
		c(body_lado7, "pend_inf_0_7", 363, 292, -13.619f, -2.0f, -3.0732f, 27.2381f, 2.0f, 3.0732f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_1_7", 379, 305, -12.3764f, -2.0f, -3.0732f, 24.7528f, 2.0f, 3.0732f, 0.0f, -11.3333f, 33.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_2_7", 349, 313, -11.1338f, -2.0f, -3.0732f, 22.2675f, 2.0f, 3.0732f, 0.0f, -10.6667f, 30.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_3_7", 266, 321, -9.8911f, -2.0f, -3.0732f, 19.7823f, 2.0f, 3.0732f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_4_7", 145, 329, -8.6485f, -2.0f, -3.0732f, 17.297f, 2.0f, 3.0732f, 0.0f, -9.3333f, 24.0f, 12.5288f, 0.0f);
		c(body_lado7, "pend_inf_5_7", 1, 337, -7.4058f, -2.0f, -3.0732f, 14.8117f, 2.0f, 3.0732f, 0.0f, -8.6667f, 21.0f, 12.5288f, 0.0f);
		c(body_lado7, "borde_cupula_7", 305, 337, -7.9736f, -23.5f, 18.5f, 15.9472f, 4.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);

		// --- glow
		c(glow, "fondo_brasa_izq", 486, 272, -34.5f, -35.5f, -7.5f, 9.0f, 9.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_1_izq", 49, 292, -29.5f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_2_izq", 33, 292, -33.0f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fondo_brasa_der", 463, 272, 25.5f, -35.5f, -7.5f, 9.0f, 9.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_1_der", 17, 292, 27.0f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_2_der", 1, 292, 30.5f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData glow_lado0 = glow.addChild("lado_0", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(0), 0));
		c(glow_lado0, "franja_borde_0", 91, 396, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado0, "franja_pendiente_0", 93, 272, -2.5f, -0.6f, -16.7631f, 5.0f, 0.6f, 16.7631f, 0.0f, -17.0f, 36.0f, -17.354f, 0.0f);
		ModelPartData glow_lado1 = glow.addChild("lado_1", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-45), 0));
		c(glow_lado1, "franja_borde_1", 73, 396, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado1, "franja_pendiente_1", 47, 272, -2.5f, -0.6f, -16.7631f, 5.0f, 0.6f, 16.7631f, 0.0f, -17.0f, 36.0f, -17.354f, 0.0f);
		ModelPartData glow_lado2 = glow.addChild("lado_2", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-90), 0));
		c(glow_lado2, "franja_borde_2", 55, 396, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado2, "franja_pendiente_2", 1, 272, -2.5f, -0.6f, -16.7631f, 5.0f, 0.6f, 16.7631f, 0.0f, -17.0f, 36.0f, -17.354f, 0.0f);
		ModelPartData glow_lado3 = glow.addChild("lado_3", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-135), 0));
		c(glow_lado3, "franja_borde_3", 37, 396, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado3, "franja_pendiente_3", 465, 251, -2.5f, -0.6f, -16.7631f, 5.0f, 0.6f, 16.7631f, 0.0f, -17.0f, 36.0f, -17.354f, 0.0f);
		ModelPartData glow_lado4 = glow.addChild("lado_4", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-180), 0));
		c(glow_lado4, "franja_borde_4", 19, 396, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado4, "franja_pendiente_4", 419, 251, -2.5f, -0.6f, -16.7631f, 5.0f, 0.6f, 16.7631f, 0.0f, -17.0f, 36.0f, -17.354f, 0.0f);
		ModelPartData glow_lado5 = glow.addChild("lado_5", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-225), 0));
		c(glow_lado5, "franja_borde_5", 1, 396, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado5, "franja_pendiente_5", 373, 251, -2.5f, -0.6f, -16.7631f, 5.0f, 0.6f, 16.7631f, 0.0f, -17.0f, 36.0f, -17.354f, 0.0f);
		ModelPartData glow_lado6 = glow.addChild("lado_6", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-270), 0));
		c(glow_lado6, "franja_borde_6", 482, 389, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado6, "franja_pendiente_6", 327, 251, -2.5f, -0.6f, -16.7631f, 5.0f, 0.6f, 16.7631f, 0.0f, -17.0f, 36.0f, -17.354f, 0.0f);
		ModelPartData glow_lado7 = glow.addChild("lado_7", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-315), 0));
		c(glow_lado7, "franja_borde_7", 464, 389, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado7, "franja_pendiente_7", 281, 251, -2.5f, -0.6f, -16.7631f, 5.0f, 0.6f, 16.7631f, 0.0f, -17.0f, 36.0f, -17.354f, 0.0f);

		// --- frame
		c(frame, "tapa_0", 239, 292, -9.8f, -1.5f, -4.0593f, 19.6f, 1.5f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, 0.0f);
		c(frame, "tapa_1", 181, 292, -9.8f, -1.48f, -4.0593f, 19.6f, 1.46f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, -45.0f);
		c(frame, "tapa_2", 123, 292, -9.8f, -1.46f, -4.0593f, 19.6f, 1.42f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, -90.0f);
		c(frame, "tapa_3", 65, 292, -9.8f, -1.44f, -4.0593f, 19.6f, 1.38f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, -135.0f);
		c(frame, "poste_0", 64, 229, 7.0701f, -41.5f, 18.2f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_1", 55, 229, 18.2f, -41.5f, 7.0701f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_2", 46, 229, 18.2f, -41.5f, -8.6701f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_3", 37, 229, 7.0701f, -41.5f, -19.8f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_4", 28, 229, -8.6701f, -41.5f, -19.8f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_5", 19, 229, -19.8f, -41.5f, -8.6701f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_6", 10, 229, -19.8f, -41.5f, 7.0701f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_7", 1, 229, -8.6701f, -41.5f, 18.2f, 1.6f, 18.0f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);

		// --- dome
		ModelPartData dome_lado0 = dome.addChild("lado_0", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(0), 0));
		c(dome_lado0, "cristal_pared_0", 246, 251, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado0, "cristal_techo_0_0", 364, 345, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado0, "cristal_techo_1_0", 125, 353, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado0, "cristal_techo_2_0", 338, 353, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado1 = dome.addChild("lado_1", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-45), 0));
		c(dome_lado1, "cristal_pared_1", 211, 251, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado1, "cristal_techo_0_1", 328, 345, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado1, "cristal_techo_1_1", 94, 353, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado1, "cristal_techo_2_1", 312, 353, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado2 = dome.addChild("lado_2", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-90), 0));
		c(dome_lado2, "cristal_pared_2", 176, 251, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado2, "cristal_techo_0_2", 292, 345, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado2, "cristal_techo_1_2", 63, 353, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado2, "cristal_techo_2_2", 286, 353, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado3 = dome.addChild("lado_3", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-135), 0));
		c(dome_lado3, "cristal_pared_3", 141, 251, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado3, "cristal_techo_0_3", 256, 345, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado3, "cristal_techo_1_3", 32, 353, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado3, "cristal_techo_2_3", 260, 353, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado4 = dome.addChild("lado_4", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-180), 0));
		c(dome_lado4, "cristal_pared_4", 106, 251, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado4, "cristal_techo_0_4", 220, 345, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado4, "cristal_techo_1_4", 1, 353, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado4, "cristal_techo_2_4", 234, 353, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado5 = dome.addChild("lado_5", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-225), 0));
		c(dome_lado5, "cristal_pared_5", 71, 251, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado5, "cristal_techo_0_5", 184, 345, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado5, "cristal_techo_1_5", 462, 345, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado5, "cristal_techo_2_5", 208, 353, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado6 = dome.addChild("lado_6", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-270), 0));
		c(dome_lado6, "cristal_pared_6", 36, 251, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado6, "cristal_techo_0_6", 148, 345, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado6, "cristal_techo_1_6", 431, 345, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado6, "cristal_techo_2_6", 182, 353, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);
		ModelPartData dome_lado7 = dome.addChild("lado_7", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-315), 0));
		c(dome_lado7, "cristal_pared_7", 1, 251, -7.6701f, -41.0f, 18.5f, 15.3401f, 17.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado7, "cristal_techo_0_7", 112, 345, -6.3774f, 0.0f, -4.0139f, 12.7548f, 1.5f, 4.0139f, 0.0f, -41.0f, 19.0f, -41.6335f, 0.0f);
		c(dome_lado7, "cristal_techo_1_7", 400, 345, -5.1348f, 0.0f, -4.0139f, 10.2696f, 1.5f, 4.0139f, 0.0f, -43.6667f, 16.0f, -41.6335f, 0.0f);
		c(dome_lado7, "cristal_techo_2_7", 156, 353, -3.8921f, 0.0f, -4.0139f, 7.7843f, 1.5f, 4.0139f, 0.0f, -46.3333f, 13.0f, -41.6335f, 0.0f);

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
