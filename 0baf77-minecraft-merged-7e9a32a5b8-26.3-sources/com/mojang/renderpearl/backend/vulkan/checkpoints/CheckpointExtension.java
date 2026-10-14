package com.mojang.renderpearl.backend.vulkan.checkpoints;

import com.mojang.renderpearl.backend.vulkan.VulkanDevice;
import com.mojang.renderpearl.backend.vulkan.VulkanQueue;
import com.mojang.renderpearl.util.UncheckedAutoCloseable;
import java.util.List;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.lwjgl.vulkan.VkCommandBuffer;

@Environment(EnvType.CLIENT)
public interface CheckpointExtension extends UncheckedAutoCloseable {
	CheckpointExtension.CheckpointStorage createStorage(VulkanDevice device, VulkanQueue queue, int maxFramesInFlight);

	List<CheckpointExtension.QueueCheckpoints> retrieveCheckpoints(boolean isDeviceLost);

	@Environment(EnvType.CLIENT)
	interface CheckpointStorage {
		void rotate();

		void recordCheckpoint(VkCommandBuffer commandBuffer, CheckpointExtension.CheckpointType type, Supplier<String> label);
	}

	@Environment(EnvType.CLIENT)
	enum CheckpointType {
		BEGIN_RENDER_PASS,
		END_RENDER_PASS;
	}

	@Environment(EnvType.CLIENT)
	record QueueCheckpoints(long queue, List<CheckpointExtension.StageCheckpoint> checkpoints) {
	}

	@Environment(EnvType.CLIENT)
	record StageCheckpoint(long stage, CheckpointExtension.CheckpointType type, String label) {
	}
}
