package com.spacecruiser;

import com.spacecruiser.entity.SpaceCruiserEntity;
import com.spacecruiser.item.SpaceCruiserItem;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public final class ModItems {
	public static final Item SPACE_CRUISER = register("space_cruiser", () -> ModEntities.SPACE_CRUISER);
	// Variantes de menor tamaño: sin receta, se sacan de la pestaña Herramientas o con /give.
	public static final Item SPACE_CRUISER_MINUS_1 = register("space_cruiser_minus1", () -> ModEntities.SPACE_CRUISER_MINUS_1);
	public static final Item SPACE_CRUISER_MINUS_2 = register("space_cruiser_minus2", () -> ModEntities.SPACE_CRUISER_MINUS_2);

	private ModItems() {
	}

	private static Item register(String id, Supplier<EntityType<SpaceCruiserEntity>> entityType) {
		return Registry.register(
				Registries.ITEM,
				Identifier.of(SpaceCruiserMod.MOD_ID, id),
				new SpaceCruiserItem(new Item.Settings().maxCount(1), entityType)
		);
	}

	/** Solo sirve para forzar la carga de la clase (y con ella el registro). */
	public static void register() {
	}
}
