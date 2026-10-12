package com.spacecruiser;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;

public class SpaceCruiserMod implements ModInitializer {
	public static final String MOD_ID = "spacecruiser";

	@Override
	public void onInitialize() {
		ModEntities.register();
		ModItems.register();

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
				.register(entries -> entries.add(ModItems.SPACE_CRUISER));
	}
}
