package com.spacecruiser;

import com.spacecruiser.entity.SpaceCruiserEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public final class ModEntities {
	/**
	 * Escalas de la nave. 16 píxeles = 1 bloque. La nave actual es 14/16; las variantes quitan 1 y 2 píxeles más
	 * (13/16 y 12/16). Para probar otro tamaño basta con cambiar estos números.
	 */
	public static final float SCALE = 14.0f / 16.0f;
	public static final float SCALE_MINUS_1 = 13.0f / 16.0f;
	public static final float SCALE_MINUS_2 = 12.0f / 16.0f;

	/** Medidas de la nave a escala 1: caja de colisión y altura a la que va el piloto. */
	private static final float WIDTH = 4.6f;
	private static final float HEIGHT = 3.2f;
	private static final float PILOT_HEIGHT = 1.0f;

	public static final EntityType<SpaceCruiserEntity> SPACE_CRUISER =
			register("space_cruiser", SCALE, () -> ModItems.SPACE_CRUISER);
	public static final EntityType<SpaceCruiserEntity> SPACE_CRUISER_MINUS_1 =
			register("space_cruiser_minus1", SCALE_MINUS_1, () -> ModItems.SPACE_CRUISER_MINUS_1);
	public static final EntityType<SpaceCruiserEntity> SPACE_CRUISER_MINUS_2 =
			register("space_cruiser_minus2", SCALE_MINUS_2, () -> ModItems.SPACE_CRUISER_MINUS_2);

	private ModEntities() {
	}

	private static EntityType<SpaceCruiserEntity> register(String id, float scale, Supplier<Item> item) {
		return Registry.register(
				Registries.ENTITY_TYPE,
				Identifier.of(SpaceCruiserMod.MOD_ID, id),
				EntityType.Builder.<SpaceCruiserEntity>create(
								(type, world) -> new SpaceCruiserEntity(type, world, scale, item), SpawnGroup.MISC)
						.dimensions(WIDTH * scale, HEIGHT * scale)
						.passengerAttachments(PILOT_HEIGHT * scale)
						.maxTrackingRange(10)
						.build(id)
		);
	}

	/** Solo sirve para forzar la carga de la clase (y con ella el registro). */
	public static void register() {
	}
}
