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
		c(body, "nucleo_inf_0", 1, 177, -18.0f, -4.0f, -7.4558f, 36.0f, 4.0f, 14.9117f, 0.0f, -8.0f, 0.0f, 0.0f, 0.0f);
		c(body, "nucleo_inf_1", 333, 155, -18.0f, -3.98f, -7.4558f, 36.0f, 3.96f, 14.9117f, 0.0f, -8.0f, 0.0f, 0.0f, -45.0f);
		c(body, "nucleo_inf_2", 229, 155, -18.0f, -3.96f, -7.4558f, 36.0f, 3.92f, 14.9117f, 0.0f, -8.0f, 0.0f, 0.0f, -90.0f);
		c(body, "nucleo_inf_3", 125, 155, -18.0f, -3.94f, -7.4558f, 36.0f, 3.88f, 14.9117f, 0.0f, -8.0f, 0.0f, 0.0f, -135.0f);
		c(body, "borde_0", 207, 38, -36.0f, -5.0f, -14.9117f, 72.0f, 5.0f, 29.8234f, 0.0f, -12.0f, 0.0f, 0.0f, 0.0f);
		c(body, "borde_1", 1, 38, -36.0f, -4.98f, -14.9117f, 72.0f, 4.96f, 29.8234f, 0.0f, -12.0f, 0.0f, 0.0f, -45.0f);
		c(body, "borde_2", 207, 1, -36.0f, -4.96f, -14.9117f, 72.0f, 4.92f, 29.8234f, 0.0f, -12.0f, 0.0f, 0.0f, -90.0f);
		c(body, "borde_3", 1, 1, -36.0f, -4.94f, -14.9117f, 72.0f, 4.88f, 29.8234f, 0.0f, -12.0f, 0.0f, 0.0f, -135.0f);
		c(body, "nucleo_sup_0", 145, 104, -25.0f, -2.5f, -10.3553f, 50.0f, 2.5f, 20.7107f, 0.0f, -17.0f, 0.0f, 0.0f, 0.0f);
		c(body, "nucleo_sup_1", 1, 104, -25.0f, -2.48f, -10.3553f, 50.0f, 2.46f, 20.7107f, 0.0f, -17.0f, 0.0f, 0.0f, -45.0f);
		c(body, "nucleo_sup_2", 227, 75, -25.0f, -2.46f, -10.3553f, 50.0f, 2.42f, 20.7107f, 0.0f, -17.0f, 0.0f, 0.0f, -90.0f);
		c(body, "nucleo_sup_3", 83, 75, -25.0f, -2.44f, -10.3553f, 50.0f, 2.38f, 20.7107f, 0.0f, -17.0f, 0.0f, 0.0f, -135.0f);
		c(body, "base_cupula_0", 243, 130, -21.0f, -5.0f, -8.6985f, 42.0f, 5.0f, 17.397f, 0.0f, -22.0f, 0.0f, 0.0f, 0.0f);
		c(body, "base_cupula_1", 122, 130, -21.0f, -4.98f, -8.6985f, 42.0f, 4.96f, 17.397f, 0.0f, -22.0f, 0.0f, 0.0f, -45.0f);
		c(body, "base_cupula_2", 1, 130, -21.0f, -4.96f, -8.6985f, 42.0f, 4.92f, 17.397f, 0.0f, -22.0f, 0.0f, 0.0f, -90.0f);
		c(body, "base_cupula_3", 289, 104, -21.0f, -4.94f, -8.6985f, 42.0f, 4.88f, 17.397f, 0.0f, -22.0f, 0.0f, 0.0f, -135.0f);
		c(body, "asiento_base", 19, 198, -6.0f, -31.0f, -8.0f, 12.0f, 4.0f, 12.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "asiento_respaldo", 149, 248, -7.0f, -40.0f, -9.0f, 14.0f, 9.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "consola", 233, 289, -9.0f, -31.0f, 10.0f, 18.0f, 4.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "columna_volante", 383, 309, -1.5f, -32.0f, 12.0f, 3.0f, 1.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "volante", 55, 309, -5.0f, -33.5f, 11.0f, 10.0f, 1.5f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_arriba_izq", 63, 155, -36.0f, -37.0f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_abajo_izq", 1, 155, -36.0f, -26.5f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_interior_izq", 42, 75, -25.5f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_exterior_izq", 1, 75, -36.0f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tapa_delantera_izq", 103, 198, -37.0f, -38.0f, 9.0f, 14.0f, 14.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "asa_delantera_izq", 371, 309, -32.0f, -32.0f, 11.0f, 4.0f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_arriba_izq", 326, 309, -37.0f, -38.0f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_abajo_izq", 293, 309, -37.0f, -25.5f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_interior_izq", 141, 248, -24.5f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_exterior_izq", 133, 248, -37.0f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "soporte_izq", 207, 248, -24.0f, -34.0f, -3.0f, 4.0f, 6.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_arriba_der", 426, 130, 24.0f, -37.0f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_abajo_der", 364, 130, 24.0f, -26.5f, -9.0f, 12.0f, 1.5f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_interior_der", 454, 38, 24.0f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tubo_exterior_der", 413, 38, 34.5f, -35.5f, -9.0f, 1.5f, 9.0f, 18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "tapa_delantera_der", 69, 198, 23.0f, -38.0f, 9.0f, 14.0f, 14.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "asa_delantera_der", 359, 309, 28.0f, -32.0f, 11.0f, 4.0f, 2.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_arriba_der", 260, 309, 23.0f, -38.0f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_abajo_der", 227, 309, 23.0f, -25.5f, -10.5f, 14.0f, 1.5f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_interior_der", 125, 248, 23.0f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "labio_exterior_der", 117, 248, 35.5f, -36.5f, -10.5f, 1.5f, 11.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "soporte_der", 185, 248, 20.0f, -34.0f, -3.0f, 4.0f, 6.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_0", 1, 217, -17.0f, -10.0f, 11.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_1", 485, 198, 11.0f, -10.0f, 11.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_2", 459, 198, -17.0f, -10.0f, -17.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(body, "pata_3", 433, 198, 11.0f, -10.0f, -17.0f, 6.0f, 8.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData body_lado0 = body.addChild("lado_0", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(0), 0));
		c(body_lado0, "panel_sup_A_0", 289, 263, -13.3584f, 0.0f, -7.9057f, 26.7168f, 3.0f, 7.9057f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		c(body_lado0, "panel_sup_B_0", 296, 276, -10.2518f, 0.0f, -7.9057f, 20.5036f, 3.0f, 7.9057f, 0.0f, -19.5f, 28.5f, -18.4349f, 0.0f);
		c(body_lado0, "panel_inf_A_0", 74, 233, -13.0477f, -3.0f, -9.2195f, 26.0955f, 3.0f, 9.2195f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado0, "panel_inf_B_0", 59, 248, -9.3198f, -3.0f, -9.2195f, 18.6396f, 3.0f, 9.2195f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		ModelPartData body_lado1 = body.addChild("lado_1", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-45), 0));
		c(body_lado1, "panel_sup_A_1", 217, 263, -13.3584f, 0.0f, -7.9057f, 26.7168f, 3.0f, 7.9057f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		c(body_lado1, "panel_sup_B_1", 237, 276, -10.2518f, 0.0f, -7.9057f, 20.5036f, 3.0f, 7.9057f, 0.0f, -19.5f, 28.5f, -18.4349f, 0.0f);
		c(body_lado1, "panel_inf_A_1", 1, 233, -13.0477f, -3.0f, -9.2195f, 26.0955f, 3.0f, 9.2195f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado1, "panel_inf_B_1", 1, 248, -9.3198f, -3.0f, -9.2195f, 18.6396f, 3.0f, 9.2195f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		ModelPartData body_lado2 = body.addChild("lado_2", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-90), 0));
		c(body_lado2, "panel_sup_A_2", 145, 263, -13.3584f, 0.0f, -7.9057f, 26.7168f, 3.0f, 7.9057f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		c(body_lado2, "panel_sup_B_2", 178, 276, -10.2518f, 0.0f, -7.9057f, 20.5036f, 3.0f, 7.9057f, 0.0f, -19.5f, 28.5f, -18.4349f, 0.0f);
		c(body_lado2, "panel_inf_A_2", 392, 217, -13.0477f, -3.0f, -9.2195f, 26.0955f, 3.0f, 9.2195f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado2, "panel_inf_B_2", 437, 233, -9.3198f, -3.0f, -9.2195f, 18.6396f, 3.0f, 9.2195f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		ModelPartData body_lado3 = body.addChild("lado_3", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-135), 0));
		c(body_lado3, "panel_sup_A_3", 73, 263, -13.3584f, 0.0f, -7.9057f, 26.7168f, 3.0f, 7.9057f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		c(body_lado3, "panel_sup_B_3", 119, 276, -10.2518f, 0.0f, -7.9057f, 20.5036f, 3.0f, 7.9057f, 0.0f, -19.5f, 28.5f, -18.4349f, 0.0f);
		c(body_lado3, "panel_inf_A_3", 319, 217, -13.0477f, -3.0f, -9.2195f, 26.0955f, 3.0f, 9.2195f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado3, "panel_inf_B_3", 379, 233, -9.3198f, -3.0f, -9.2195f, 18.6396f, 3.0f, 9.2195f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		ModelPartData body_lado4 = body.addChild("lado_4", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-180), 0));
		c(body_lado4, "panel_sup_A_4", 1, 263, -13.3584f, 0.0f, -7.9057f, 26.7168f, 3.0f, 7.9057f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		c(body_lado4, "panel_sup_B_4", 60, 276, -10.2518f, 0.0f, -7.9057f, 20.5036f, 3.0f, 7.9057f, 0.0f, -19.5f, 28.5f, -18.4349f, 0.0f);
		c(body_lado4, "panel_inf_A_4", 246, 217, -13.0477f, -3.0f, -9.2195f, 26.0955f, 3.0f, 9.2195f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado4, "panel_inf_B_4", 321, 233, -9.3198f, -3.0f, -9.2195f, 18.6396f, 3.0f, 9.2195f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		ModelPartData body_lado5 = body.addChild("lado_5", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-225), 0));
		c(body_lado5, "panel_sup_A_5", 373, 248, -13.3584f, 0.0f, -7.9057f, 26.7168f, 3.0f, 7.9057f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		c(body_lado5, "panel_sup_B_5", 1, 276, -10.2518f, 0.0f, -7.9057f, 20.5036f, 3.0f, 7.9057f, 0.0f, -19.5f, 28.5f, -18.4349f, 0.0f);
		c(body_lado5, "panel_inf_A_5", 173, 217, -13.0477f, -3.0f, -9.2195f, 26.0955f, 3.0f, 9.2195f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado5, "panel_inf_B_5", 263, 233, -9.3198f, -3.0f, -9.2195f, 18.6396f, 3.0f, 9.2195f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		ModelPartData body_lado6 = body.addChild("lado_6", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-270), 0));
		c(body_lado6, "panel_sup_A_6", 301, 248, -13.3584f, 0.0f, -7.9057f, 26.7168f, 3.0f, 7.9057f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		c(body_lado6, "panel_sup_B_6", 420, 263, -10.2518f, 0.0f, -7.9057f, 20.5036f, 3.0f, 7.9057f, 0.0f, -19.5f, 28.5f, -18.4349f, 0.0f);
		c(body_lado6, "panel_inf_A_6", 100, 217, -13.0477f, -3.0f, -9.2195f, 26.0955f, 3.0f, 9.2195f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado6, "panel_inf_B_6", 205, 233, -9.3198f, -3.0f, -9.2195f, 18.6396f, 3.0f, 9.2195f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);
		ModelPartData body_lado7 = body.addChild("lado_7", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-315), 0));
		c(body_lado7, "panel_sup_A_7", 229, 248, -13.3584f, 0.0f, -7.9057f, 26.7168f, 3.0f, 7.9057f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		c(body_lado7, "panel_sup_B_7", 361, 263, -10.2518f, 0.0f, -7.9057f, 20.5036f, 3.0f, 7.9057f, 0.0f, -19.5f, 28.5f, -18.4349f, 0.0f);
		c(body_lado7, "panel_inf_A_7", 27, 217, -13.0477f, -3.0f, -9.2195f, 26.0955f, 3.0f, 9.2195f, 0.0f, -12.0f, 36.0f, 12.5288f, 0.0f);
		c(body_lado7, "panel_inf_B_7", 147, 233, -9.3198f, -3.0f, -9.2195f, 18.6396f, 3.0f, 9.2195f, 0.0f, -10.0f, 27.0f, 12.5288f, 0.0f);

		// --- glow
		c(glow, "fondo_brasa_izq", 378, 276, -34.5f, -35.5f, -7.5f, 9.0f, 9.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_1_izq", 449, 276, -29.5f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_2_izq", 433, 276, -33.0f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fondo_brasa_der", 355, 276, 25.5f, -35.5f, -7.5f, 9.0f, 9.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_1_der", 417, 276, 27.0f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow, "fuego_2_der", 401, 276, 30.5f, -34.0f, -12.0f, 2.5f, 6.0f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		ModelPartData glow_lado0 = glow.addChild("lado_0", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(0), 0));
		c(glow_lado0, "franja_borde_0", 209, 309, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado0, "franja_pendiente_0", 413, 177, -2.5f, -0.6f, -15.8114f, 5.0f, 0.6f, 15.8114f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		ModelPartData glow_lado1 = glow.addChild("lado_1", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-45), 0));
		c(glow_lado1, "franja_borde_1", 191, 309, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado1, "franja_pendiente_1", 369, 177, -2.5f, -0.6f, -15.8114f, 5.0f, 0.6f, 15.8114f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		ModelPartData glow_lado2 = glow.addChild("lado_2", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-90), 0));
		c(glow_lado2, "franja_borde_2", 173, 309, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado2, "franja_pendiente_2", 325, 177, -2.5f, -0.6f, -15.8114f, 5.0f, 0.6f, 15.8114f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		ModelPartData glow_lado3 = glow.addChild("lado_3", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-135), 0));
		c(glow_lado3, "franja_borde_3", 155, 309, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado3, "franja_pendiente_3", 281, 177, -2.5f, -0.6f, -15.8114f, 5.0f, 0.6f, 15.8114f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		ModelPartData glow_lado4 = glow.addChild("lado_4", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-180), 0));
		c(glow_lado4, "franja_borde_4", 137, 309, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado4, "franja_pendiente_4", 237, 177, -2.5f, -0.6f, -15.8114f, 5.0f, 0.6f, 15.8114f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		ModelPartData glow_lado5 = glow.addChild("lado_5", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-225), 0));
		c(glow_lado5, "franja_borde_5", 119, 309, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado5, "franja_pendiente_5", 193, 177, -2.5f, -0.6f, -15.8114f, 5.0f, 0.6f, 15.8114f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		ModelPartData glow_lado6 = glow.addChild("lado_6", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-270), 0));
		c(glow_lado6, "franja_borde_6", 101, 309, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado6, "franja_pendiente_6", 149, 177, -2.5f, -0.6f, -15.8114f, 5.0f, 0.6f, 15.8114f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);
		ModelPartData glow_lado7 = glow.addChild("lado_7", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-315), 0));
		c(glow_lado7, "franja_borde_7", 83, 309, -3.5f, -16.0f, 36.0f, 7.0f, 3.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(glow_lado7, "franja_pendiente_7", 105, 177, -2.5f, -0.6f, -15.8114f, 5.0f, 0.6f, 15.8114f, 0.0f, -17.0f, 36.0f, -18.4349f, 0.0f);

		// --- frame
		c(frame, "tapa_0", 175, 289, -9.8f, -1.5f, -4.0593f, 19.6f, 1.5f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, 0.0f);
		c(frame, "tapa_1", 117, 289, -9.8f, -1.48f, -4.0593f, 19.6f, 1.46f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, -45.0f);
		c(frame, "tapa_2", 59, 289, -9.8f, -1.46f, -4.0593f, 19.6f, 1.42f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, -90.0f);
		c(frame, "tapa_3", 1, 289, -9.8f, -1.44f, -4.0593f, 19.6f, 1.38f, 8.1186f, 0.0f, -48.5f, 0.0f, 0.0f, -135.0f);
		c(frame, "poste_0", 10, 198, 7.4843f, -41.5f, 19.2f, 1.6f, 14.5f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_1", 1, 198, 19.2f, -41.5f, 7.4843f, 1.6f, 14.5f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_2", 502, 177, 19.2f, -41.5f, -9.0843f, 1.6f, 14.5f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_3", 493, 177, 7.4843f, -41.5f, -20.8f, 1.6f, 14.5f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_4", 484, 177, -9.0843f, -41.5f, -20.8f, 1.6f, 14.5f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_5", 475, 177, -20.8f, -41.5f, -9.0843f, 1.6f, 14.5f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_6", 466, 177, -20.8f, -41.5f, 7.4843f, 1.6f, 14.5f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(frame, "poste_7", 457, 177, -9.0843f, -41.5f, 19.2f, 1.6f, 14.5f, 1.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);

		// --- dome
		ModelPartData dome_lado0 = dome.addChild("lado_0", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(0), 0));
		c(dome_lado0, "cristal_pared_0", 396, 198, -8.1f, -41.0f, 19.5f, 16.2f, 14.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado0, "cristal_techo_0_0", 38, 301, -6.6f, 0.0f, -4.2687f, 13.2f, 1.5f, 4.2687f, 0.0f, -41.0f, 20.0f, -38.6598f, 0.0f);
		c(dome_lado0, "cristal_techo_1_0", 299, 301, -5.2f, 0.0f, -4.2687f, 10.4f, 1.5f, 4.2687f, 0.0f, -43.6667f, 16.6667f, -38.6598f, 0.0f);
		c(dome_lado0, "cristal_techo_2_0", 28, 309, -3.9f, 0.0f, -4.2687f, 7.8f, 1.5f, 4.2687f, 0.0f, -46.3333f, 13.3333f, -38.6598f, 0.0f);
		ModelPartData dome_lado1 = dome.addChild("lado_1", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-45), 0));
		c(dome_lado1, "cristal_pared_1", 359, 198, -8.1f, -41.0f, 19.5f, 16.2f, 14.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado1, "cristal_techo_0_1", 1, 301, -6.6f, 0.0f, -4.2687f, 13.2f, 1.5f, 4.2687f, 0.0f, -41.0f, 20.0f, -38.6598f, 0.0f);
		c(dome_lado1, "cristal_techo_1_1", 267, 301, -5.2f, 0.0f, -4.2687f, 10.4f, 1.5f, 4.2687f, 0.0f, -43.6667f, 16.6667f, -38.6598f, 0.0f);
		c(dome_lado1, "cristal_techo_2_1", 1, 309, -3.9f, 0.0f, -4.2687f, 7.8f, 1.5f, 4.2687f, 0.0f, -46.3333f, 13.3333f, -38.6598f, 0.0f);
		ModelPartData dome_lado2 = dome.addChild("lado_2", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-90), 0));
		c(dome_lado2, "cristal_pared_2", 322, 198, -8.1f, -41.0f, 19.5f, 16.2f, 14.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado2, "cristal_techo_0_2", 468, 289, -6.6f, 0.0f, -4.2687f, 13.2f, 1.5f, 4.2687f, 0.0f, -41.0f, 20.0f, -38.6598f, 0.0f);
		c(dome_lado2, "cristal_techo_1_2", 235, 301, -5.2f, 0.0f, -4.2687f, 10.4f, 1.5f, 4.2687f, 0.0f, -43.6667f, 16.6667f, -38.6598f, 0.0f);
		c(dome_lado2, "cristal_techo_2_2", 466, 301, -3.9f, 0.0f, -4.2687f, 7.8f, 1.5f, 4.2687f, 0.0f, -46.3333f, 13.3333f, -38.6598f, 0.0f);
		ModelPartData dome_lado3 = dome.addChild("lado_3", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-135), 0));
		c(dome_lado3, "cristal_pared_3", 285, 198, -8.1f, -41.0f, 19.5f, 16.2f, 14.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado3, "cristal_techo_0_3", 431, 289, -6.6f, 0.0f, -4.2687f, 13.2f, 1.5f, 4.2687f, 0.0f, -41.0f, 20.0f, -38.6598f, 0.0f);
		c(dome_lado3, "cristal_techo_1_3", 203, 301, -5.2f, 0.0f, -4.2687f, 10.4f, 1.5f, 4.2687f, 0.0f, -43.6667f, 16.6667f, -38.6598f, 0.0f);
		c(dome_lado3, "cristal_techo_2_3", 439, 301, -3.9f, 0.0f, -4.2687f, 7.8f, 1.5f, 4.2687f, 0.0f, -46.3333f, 13.3333f, -38.6598f, 0.0f);
		ModelPartData dome_lado4 = dome.addChild("lado_4", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-180), 0));
		c(dome_lado4, "cristal_pared_4", 248, 198, -8.1f, -41.0f, 19.5f, 16.2f, 14.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado4, "cristal_techo_0_4", 394, 289, -6.6f, 0.0f, -4.2687f, 13.2f, 1.5f, 4.2687f, 0.0f, -41.0f, 20.0f, -38.6598f, 0.0f);
		c(dome_lado4, "cristal_techo_1_4", 171, 301, -5.2f, 0.0f, -4.2687f, 10.4f, 1.5f, 4.2687f, 0.0f, -43.6667f, 16.6667f, -38.6598f, 0.0f);
		c(dome_lado4, "cristal_techo_2_4", 412, 301, -3.9f, 0.0f, -4.2687f, 7.8f, 1.5f, 4.2687f, 0.0f, -46.3333f, 13.3333f, -38.6598f, 0.0f);
		ModelPartData dome_lado5 = dome.addChild("lado_5", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-225), 0));
		c(dome_lado5, "cristal_pared_5", 211, 198, -8.1f, -41.0f, 19.5f, 16.2f, 14.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado5, "cristal_techo_0_5", 357, 289, -6.6f, 0.0f, -4.2687f, 13.2f, 1.5f, 4.2687f, 0.0f, -41.0f, 20.0f, -38.6598f, 0.0f);
		c(dome_lado5, "cristal_techo_1_5", 139, 301, -5.2f, 0.0f, -4.2687f, 10.4f, 1.5f, 4.2687f, 0.0f, -43.6667f, 16.6667f, -38.6598f, 0.0f);
		c(dome_lado5, "cristal_techo_2_5", 385, 301, -3.9f, 0.0f, -4.2687f, 7.8f, 1.5f, 4.2687f, 0.0f, -46.3333f, 13.3333f, -38.6598f, 0.0f);
		ModelPartData dome_lado6 = dome.addChild("lado_6", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-270), 0));
		c(dome_lado6, "cristal_pared_6", 174, 198, -8.1f, -41.0f, 19.5f, 16.2f, 14.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado6, "cristal_techo_0_6", 320, 289, -6.6f, 0.0f, -4.2687f, 13.2f, 1.5f, 4.2687f, 0.0f, -41.0f, 20.0f, -38.6598f, 0.0f);
		c(dome_lado6, "cristal_techo_1_6", 107, 301, -5.2f, 0.0f, -4.2687f, 10.4f, 1.5f, 4.2687f, 0.0f, -43.6667f, 16.6667f, -38.6598f, 0.0f);
		c(dome_lado6, "cristal_techo_2_6", 358, 301, -3.9f, 0.0f, -4.2687f, 7.8f, 1.5f, 4.2687f, 0.0f, -46.3333f, 13.3333f, -38.6598f, 0.0f);
		ModelPartData dome_lado7 = dome.addChild("lado_7", ModelPartBuilder.create(), ModelTransform.of(0, 0, 0, 0, (float) Math.toRadians(-315), 0));
		c(dome_lado7, "cristal_pared_7", 137, 198, -8.1f, -41.0f, 19.5f, 16.2f, 14.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		c(dome_lado7, "cristal_techo_0_7", 283, 289, -6.6f, 0.0f, -4.2687f, 13.2f, 1.5f, 4.2687f, 0.0f, -41.0f, 20.0f, -38.6598f, 0.0f);
		c(dome_lado7, "cristal_techo_1_7", 75, 301, -5.2f, 0.0f, -4.2687f, 10.4f, 1.5f, 4.2687f, 0.0f, -43.6667f, 16.6667f, -38.6598f, 0.0f);
		c(dome_lado7, "cristal_techo_2_7", 331, 301, -3.9f, 0.0f, -4.2687f, 7.8f, 1.5f, 4.2687f, 0.0f, -46.3333f, 13.3333f, -38.6598f, 0.0f);

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
