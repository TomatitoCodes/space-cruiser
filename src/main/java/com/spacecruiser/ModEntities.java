package com.spacecruiser;

import com.spacecruiser.entity.SpaceCruiserEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public final class ModEntities {
	/** Escala de la nave (16 píxeles = 1 bloque): 12/16. */
	public static final float SCALE = 12.0f / 16.0f;

	/** Medidas de la nave a escala 1: caja de colisión y altura a la que va el piloto. */
	private static final float WIDTH = 4.6f;
	private static final float HEIGHT = 3.2f;
	private static final float PILOT_HEIGHT = 1.0f;

	public static final EntityType<SpaceCruiserEntity> SPACE_CRUISER =
			register("space_cruiser", SCALE, () -> ModItems.SPACE_CRUISER);

	private ModEntities() {
	}

	private static EntityType<SpaceCruiserEntity> register(String id, float scale, Supplier<Item> item) {
		ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(SpaceCruiserMod.MOD_ID, id));
		return Registry.register(
				BuiltInRegistries.ENTITY_TYPE,
				key,
				EntityType.Builder.<SpaceCruiserEntity>of(
								(type, level) -> new SpaceCruiserEntity(type, level, scale, item), MobCategory.MISC)
						.sized(WIDTH * scale, HEIGHT * scale)
						.passengerAttachments(PILOT_HEIGHT * scale)
						.clientTrackingRange(10)
						.build(key)
		);
	}

	/** Solo sirve para forzar la carga de la clase (y con ella el registro). */
	public static void register() {
	}
}
