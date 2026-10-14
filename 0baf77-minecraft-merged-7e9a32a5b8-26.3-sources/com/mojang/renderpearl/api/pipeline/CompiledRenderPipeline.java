package com.mojang.renderpearl.api.pipeline;

import com.mojang.renderpearl.util.UncheckedAutoCloseable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jspecify.annotations.Nullable;

@Environment(EnvType.CLIENT)
public interface CompiledRenderPipeline extends UncheckedAutoCloseable {
	boolean isClosed();

	@FunctionalInterface
	@Environment(EnvType.CLIENT)
	interface Pending {
		CompiledRenderPipeline.Pending NULL = () -> null;

		@Nullable CompiledRenderPipeline finishCompile();
	}
}
