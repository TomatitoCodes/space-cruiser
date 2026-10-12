package com.spacecruiser;

import com.spacecruiser.item.SpaceCruiserItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModItems {
	public static final Item SPACE_CRUISER = Registry.register(
			Registries.ITEM,
			Identifier.of(SpaceCruiserMod.MOD_ID, "space_cruiser"),
			new SpaceCruiserItem(new Item.Settings().maxCount(1))
	);

	private ModItems() {
	}

	/** Solo sirve para forzar la carga de la clase (y con ella el registro). */
	public static void register() {
	}
}
