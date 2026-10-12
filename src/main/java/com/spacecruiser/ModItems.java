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

	// Piezas para fabricar la nave (receta de endgame)
	public static final Item REACTOR = part("reactor");
	public static final Item HULL = part("hull");
	public static final Item COCKPIT = part("cockpit");
	public static final Item NAVIGATION_CONSOLE = part("navigation_console");

	private ModItems() {
	}

	private static Item register(String id, Supplier<EntityType<SpaceCruiserEntity>> entityType) {
		return Registry.register(
				Registries.ITEM,
				Identifier.of(SpaceCruiserMod.MOD_ID, id),
				new SpaceCruiserItem(new Item.Settings().maxCount(1), entityType)
		);
	}

	private static Item part(String id) {
		return Registry.register(Registries.ITEM, Identifier.of(SpaceCruiserMod.MOD_ID, id), new Item(new Item.Settings()));
	}

	/** Solo sirve para forzar la carga de la clase (y con ella el registro). */
	public static void register() {
	}
}
