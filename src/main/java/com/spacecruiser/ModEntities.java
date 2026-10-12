package com.spacecruiser;

import com.spacecruiser.entity.SpaceCruiserEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModEntities {
	public static final EntityType<SpaceCruiserEntity> SPACE_CRUISER = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(SpaceCruiserMod.MOD_ID, "space_cruiser"),
			EntityType.Builder.create(SpaceCruiserEntity::new, SpawnGroup.MISC)
					.dimensions(3.4f, 2.2f)
					.passengerAttachments(0.75f)
					.maxTrackingRange(10)
					.build("space_cruiser")
	);

	private ModEntities() {
	}

	/** Solo sirve para forzar la carga de la clase (y con ella el registro). */
	public static void register() {
	}
}
