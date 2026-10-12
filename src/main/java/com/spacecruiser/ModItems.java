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
