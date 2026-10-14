package com.spacecruiser;

import com.spacecruiser.entity.SpaceCruiserEntity;
import com.spacecruiser.item.SpaceCruiserItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

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

	private static ResourceKey<Item> key(String id) {
		return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(SpaceCruiserMod.MOD_ID, id));
	}

	private static Item register(String id, Supplier<EntityType<SpaceCruiserEntity>> entityType) {
		ResourceKey<Item> key = key(id);
		return Registry.register(
				BuiltInRegistries.ITEM,
				key,
				new SpaceCruiserItem(new Item.Properties().stacksTo(1).setId(key), entityType)
		);
	}

	private static Item part(String id) {
		ResourceKey<Item> key = key(id);
		return Registry.register(BuiltInRegistries.ITEM, key, new Item(new Item.Properties().setId(key)));
	}

	/** Solo sirve para forzar la carga de la clase (y con ella el registro). */
	public static void register() {
	}
}
