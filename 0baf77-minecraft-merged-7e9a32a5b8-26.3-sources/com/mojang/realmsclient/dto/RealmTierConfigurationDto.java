package com.mojang.realmsclient.dto;

import com.google.gson.annotations.SerializedName;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jspecify.annotations.Nullable;

@Environment(EnvType.CLIENT)
public record RealmTierConfigurationDto(
	@SerializedName("renderDistance") RealmTierConfigurationDto.RealmTierRangeDto renderDistance,
	@SerializedName("simDistance") RealmTierConfigurationDto.RealmTierRangeDto simDistance
) implements ReflectionBasedSerialization {
	@Environment(EnvType.CLIENT)
	public record RealmTierRangeDto(
		@SerializedName("min") int min,
		@SerializedName("max") int max,
		@SerializedName("defaultValue") int defaultValue,
		@SerializedName("current") @Nullable Integer current
	) implements ReflectionBasedSerialization {
	}
}
