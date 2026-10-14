package com.mojang.renderpearl.backend.api;

import com.mojang.renderpearl.api.GpuFormat;
import com.mojang.renderpearl.api.pipeline.BindGroupLayout;
import com.mojang.renderpearl.api.pipeline.ColorTargetState;
import com.mojang.renderpearl.api.pipeline.DepthStencilState;
import com.mojang.renderpearl.api.pipeline.PolygonMode;
import com.mojang.renderpearl.api.pipeline.PrimitiveTopology;
import com.mojang.renderpearl.util.UncheckedAutoCloseable;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jspecify.annotations.Nullable;

@Environment(EnvType.CLIENT)
public interface BackendRenderPipeline extends UncheckedAutoCloseable {
	boolean isClosed();

	@Environment(EnvType.CLIENT)
	record CreateInfo(
		String name,
		List<BackendRenderPipeline.CreateInfo.Shader> shaders,
		List<BackendRenderPipeline.CreateInfo.VertexBuffer> vertexBuffers,
		List<BackendRenderPipeline.CreateInfo.AttribBinding> attribBindings,
		List<BindGroupLayout.UniformDescription> uniforms,
		int pushConstantsSize,
		@Nullable DepthStencilState depthStencilState,
		PolygonMode polygonMode,
		boolean cull,
		List<@Nullable ColorTargetState> colorTargetStates,
		PrimitiveTopology primitiveTopology
	) {
		@Environment(EnvType.CLIENT)
		public record AttribBinding(int bufferSlot, int location, int offset, GpuFormat format) {
		}

		@Environment(EnvType.CLIENT)
		public record Shader(String name, String entryPoint, SpvModule module) {
		}

		@Environment(EnvType.CLIENT)
		public record VertexBuffer(int bufferSlot, int stride, int stepRate) {
		}
	}

	@FunctionalInterface
	@Environment(EnvType.CLIENT)
	interface Pending {
		BackendRenderPipeline.Pending NULL = () -> null;

		@Nullable BackendRenderPipeline finishCompile();
	}
}
