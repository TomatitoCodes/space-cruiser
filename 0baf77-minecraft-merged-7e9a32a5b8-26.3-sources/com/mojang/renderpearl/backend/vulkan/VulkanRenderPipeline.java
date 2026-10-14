package com.mojang.renderpearl.backend.vulkan;

import com.mojang.renderpearl.api.pipeline.BindGroupLayout;
import com.mojang.renderpearl.api.pipeline.BlendFunction;
import com.mojang.renderpearl.api.pipeline.ColorTargetState;
import com.mojang.renderpearl.api.pipeline.DepthStencilState;
import com.mojang.renderpearl.backend.api.BackendRenderPipeline;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongList;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VK12;
import org.lwjgl.vulkan.VkDescriptorSetLayoutBinding;
import org.lwjgl.vulkan.VkDescriptorSetLayoutCreateInfo;
import org.lwjgl.vulkan.VkGraphicsPipelineCreateInfo;
import org.lwjgl.vulkan.VkPipelineColorBlendAttachmentState;
import org.lwjgl.vulkan.VkPipelineColorBlendStateCreateInfo;
import org.lwjgl.vulkan.VkPipelineDepthStencilStateCreateInfo;
import org.lwjgl.vulkan.VkPipelineDynamicStateCreateInfo;
import org.lwjgl.vulkan.VkPipelineInputAssemblyStateCreateInfo;
import org.lwjgl.vulkan.VkPipelineLayoutCreateInfo;
import org.lwjgl.vulkan.VkPipelineMultisampleStateCreateInfo;
import org.lwjgl.vulkan.VkPipelineRasterizationStateCreateInfo;
import org.lwjgl.vulkan.VkPipelineRenderingCreateInfoKHR;
import org.lwjgl.vulkan.VkPipelineShaderStageCreateInfo;
import org.lwjgl.vulkan.VkPipelineVertexInputDivisorStateCreateInfoEXT;
import org.lwjgl.vulkan.VkPipelineVertexInputStateCreateInfo;
import org.lwjgl.vulkan.VkPipelineViewportStateCreateInfo;
import org.lwjgl.vulkan.VkPushConstantRange;
import org.lwjgl.vulkan.VkShaderModuleCreateInfo;
import org.lwjgl.vulkan.VkVertexInputAttributeDescription;
import org.lwjgl.vulkan.VkVertexInputBindingDescription;
import org.lwjgl.vulkan.VkVertexInputBindingDivisorDescriptionEXT;
import org.lwjgl.vulkan.VkDescriptorSetLayoutBinding.Buffer;

@Environment(EnvType.CLIENT)
public final class VulkanRenderPipeline implements BackendRenderPipeline, Destroyable {
	private final VulkanDevice device;
	private final long withDepthPipeline;
	private final long withoutDepthPipeline;
	private final long pipelineLayout;
	private final long descriptorSetLayout;
	private final LongList shaderModules;
	private final List<BindGroupLayout.UniformDescription> uniforms;
	private boolean closed;

	public VulkanRenderPipeline(
		final VulkanDevice device,
		final long withDepthPipeline,
		final long withoutDepthPipeline,
		final long pipelineLayout,
		final long descriptorSetLayout,
		final LongList shaderModules,
		final List<BindGroupLayout.UniformDescription> uniforms
	) {
		this.device = device;
		this.withDepthPipeline = withDepthPipeline;
		this.withoutDepthPipeline = withoutDepthPipeline;
		this.pipelineLayout = pipelineLayout;
		this.descriptorSetLayout = descriptorSetLayout;
		this.shaderModules = shaderModules;
		this.uniforms = uniforms;
	}

	@Override
	public boolean isClosed() {
		return this.closed;
	}

	@Override
	public void close() {
		if (!this.closed) {
			this.closed = true;
			this.device.createCommandEncoder().queueForDestroy(this);
		}
	}

	public static VulkanRenderPipeline compile(final VulkanDevice device, final BackendRenderPipeline.CreateInfo pipelineCreateInfo) {
		long descriptorSetLayout;
		try (MemoryStack stack = MemoryStack.stackPush()) {
			int descriptorCount = pipelineCreateInfo.uniforms().size();
			Buffer bindings = VkDescriptorSetLayoutBinding.calloc(descriptorCount, stack);

			for (int i = 0; i < descriptorCount; i++) {
				VkDescriptorSetLayoutBinding binding = VkDescriptorSetLayoutBinding.calloc(stack);

				binding.descriptorType(switch (pipelineCreateInfo.uniforms().get(i).type()) {
					case UNIFORM_BUFFER -> 6;
					case COMBINED_IMAGE_SAMPLER -> 1;
					case TEXEL_BUFFER -> 4;
				});
				binding.descriptorCount(1);
				binding.binding(i);
				binding.stageFlags(17);
				bindings.put(binding);
			}

			bindings.flip();
			VkDescriptorSetLayoutCreateInfo setCreateInfo = VkDescriptorSetLayoutCreateInfo.calloc(stack).sType$Default();
			setCreateInfo.flags(1);
			setCreateInfo.pBindings(bindings);
			LongBuffer pointer = stack.callocLong(1);
			VulkanUtils.crashIfFailure(
				device,
				VK12.vkCreateDescriptorSetLayout(device.vkDevice(), setCreateInfo, null, pointer),
				"Can't create descriptor set layout for " + pipelineCreateInfo.name()
			);
			descriptorSetLayout = pointer.get(0);
		}

		long pipelineLayout;
		try (MemoryStack stack = MemoryStack.stackPush()) {
			VkPipelineLayoutCreateInfo createInfo = VkPipelineLayoutCreateInfo.calloc(stack).sType$Default();
			if (pipelineCreateInfo.pushConstantsSize() != 0) {
				org.lwjgl.vulkan.VkPushConstantRange.Buffer range = VkPushConstantRange.calloc(1, stack);
				range.stageFlags(Integer.MAX_VALUE);
				range.offset(0);
				range.size(pipelineCreateInfo.pushConstantsSize());
				createInfo.pPushConstantRanges(range);
			}

			createInfo.pSetLayouts(stack.longs(descriptorSetLayout));
			LongBuffer pointer = stack.callocLong(1);
			VulkanUtils.crashIfFailure(
				device, VK12.vkCreatePipelineLayout(device.vkDevice(), createInfo, null, pointer), "Can't create pipeline for " + pipelineCreateInfo.name()
			);
			pipelineLayout = pointer.get(0);
			device.instance().debug().setObjectName(device.vkDevice(), 17, pipelineLayout, () -> "Pipeline layout for " + pipelineCreateInfo.name());
		}

		LongList compiledShaderModules = new LongArrayList();

		try (MemoryStack stack = MemoryStack.stackPush()) {
			org.lwjgl.vulkan.VkPipelineShaderStageCreateInfo.Buffer shaderStages = VkPipelineShaderStageCreateInfo.calloc(pipelineCreateInfo.shaders().size(), stack);

			for (BackendRenderPipeline.CreateInfo.Shader shader : pipelineCreateInfo.shaders()) {
				long module;
				try (MemoryStack vertexInputDivisorState = stack.push()) {
					VkShaderModuleCreateInfo info = VkShaderModuleCreateInfo.calloc(stack).sType$Default().pCode(shader.module().spv());
					LongBuffer pointer = stack.callocLong(1);
					VulkanUtils.crashIfFailure(
						device,
						VK12.vkCreateShaderModule(device.vkDevice(), info, null, pointer),
						"Can't compile " + shader.name() + " (" + shader.module().type() + ") for pipeline " + pipelineCreateInfo.name()
					);
					device.instance().debug().setObjectName(device.vkDevice(), 15, pointer.get(0), pipelineCreateInfo::name);
					module = pointer.get(0);
					compiledShaderModules.add(module);
				}

				ByteBuffer entryPoint = stack.UTF8(shader.entryPoint());
				VkPipelineShaderStageCreateInfo stage = VkPipelineShaderStageCreateInfo.calloc(stack)
					.sType$Default()
					.stage(VulkanConst.toVk(shader.module().type()))
					.module(module)
					.pName(entryPoint);
				shaderStages.put(stage);
			}

			shaderStages.flip();
			List<BackendRenderPipeline.CreateInfo.VertexBuffer> vertexBindings = pipelineCreateInfo.vertexBuffers();
			org.lwjgl.vulkan.VkVertexInputBindingDescription.Buffer vertexBindingDescriptions = VkVertexInputBindingDescription.calloc(vertexBindings.size(), stack);
			org.lwjgl.vulkan.VkVertexInputBindingDivisorDescriptionEXT.Buffer vertexBindingDivisorDescriptions = VkVertexInputBindingDivisorDescriptionEXT.calloc(
				vertexBindings.size(), stack
			);

			for (BackendRenderPipeline.CreateInfo.VertexBuffer vertexBinding : vertexBindings) {
				VkVertexInputBindingDescription bindingDescription = VkVertexInputBindingDescription.calloc(stack)
					.binding(vertexBinding.bufferSlot())
					.stride(vertexBinding.stride())
					.inputRate(vertexBinding.stepRate() > 0 ? 1 : 0);
				vertexBindingDescriptions.put(bindingDescription);
				if (vertexBinding.stepRate() > 0) {
					VkVertexInputBindingDivisorDescriptionEXT divisorBinding = VkVertexInputBindingDivisorDescriptionEXT.calloc(stack)
						.binding(vertexBinding.bufferSlot())
						.divisor(vertexBinding.stepRate());
					vertexBindingDivisorDescriptions.put(divisorBinding);
				}
			}

			vertexBindingDescriptions.flip();
			vertexBindingDivisorDescriptions.flip();
			org.lwjgl.vulkan.VkVertexInputAttributeDescription.Buffer vertexAttributeDescriptions = VkVertexInputAttributeDescription.calloc(
				pipelineCreateInfo.attribBindings().size(), stack
			);

			for (BackendRenderPipeline.CreateInfo.AttribBinding attribBinding : pipelineCreateInfo.attribBindings()) {
				VkVertexInputAttributeDescription attributeDescription = VkVertexInputAttributeDescription.calloc(stack)
					.location(attribBinding.location())
					.binding(attribBinding.bufferSlot())
					.offset(attribBinding.offset())
					.format(VulkanConst.toVk(attribBinding.format()));
				vertexAttributeDescriptions.put(attributeDescription);
			}

			vertexAttributeDescriptions.flip();
			VkPipelineVertexInputDivisorStateCreateInfoEXT vertexInputDivisorState = VkPipelineVertexInputDivisorStateCreateInfoEXT.calloc(stack)
				.sType$Default()
				.pVertexBindingDivisors(vertexBindingDivisorDescriptions);
			VkPipelineVertexInputStateCreateInfo vertexInputState = VkPipelineVertexInputStateCreateInfo.calloc(stack)
				.sType$Default()
				.pVertexAttributeDescriptions(vertexAttributeDescriptions)
				.pVertexBindingDescriptions(vertexBindingDescriptions);
			if (vertexInputDivisorState.vertexBindingDivisorCount() > 0) {
				vertexInputState.pNext(vertexInputDivisorState);
			}

			VkPipelineInputAssemblyStateCreateInfo inputAssemblyState = VkPipelineInputAssemblyStateCreateInfo.calloc(stack)
				.sType$Default()
				.topology(VulkanConst.toVk(pipelineCreateInfo.primitiveTopology()));
			VkPipelineRasterizationStateCreateInfo rasterizationState = VkPipelineRasterizationStateCreateInfo.calloc(stack)
				.sType$Default()
				.polygonMode(VulkanConst.toVk(pipelineCreateInfo.polygonMode()))
				.cullMode(pipelineCreateInfo.cull() ? 2 : 0)
				.frontFace(1)
				.lineWidth(1.0F);
			VkPipelineDepthStencilStateCreateInfo vkDepthStencilState = VkPipelineDepthStencilStateCreateInfo.calloc(stack).sType$Default();
			DepthStencilState depthStencilState = pipelineCreateInfo.depthStencilState();
			if (depthStencilState != null) {
				rasterizationState.depthBiasEnable(depthStencilState.depthBiasConstant() != 0.0F || depthStencilState.depthBiasScaleFactor() != 0.0F);
				rasterizationState.depthBiasConstantFactor(depthStencilState.depthBiasConstant());
				rasterizationState.depthBiasSlopeFactor(depthStencilState.depthBiasScaleFactor());
				vkDepthStencilState.depthTestEnable(true);
				vkDepthStencilState.depthWriteEnable(depthStencilState.writeDepth());
				vkDepthStencilState.depthCompareOp(VulkanConst.toVk(depthStencilState.depthTest()));
			}

			List<ColorTargetState> colorTargetStates = pipelineCreateInfo.colorTargetStates();
			org.lwjgl.vulkan.VkPipelineColorBlendAttachmentState.Buffer blendAttachments = VkPipelineColorBlendAttachmentState.calloc(colorTargetStates.size(), stack);

			for (ColorTargetState colorTargetState : colorTargetStates) {
				blendAttachments.colorWriteMask(colorTargetState != null ? VulkanConst.toVk(colorTargetState) : 0);
				if (colorTargetState != null && colorTargetState.blendFunction().isPresent()) {
					applyBlendInformation(blendAttachments, colorTargetState.blendFunction().get());
				}

				blendAttachments.position(blendAttachments.position() + 1);
			}

			blendAttachments.position(0);
			VkPipelineColorBlendStateCreateInfo colorBlendState = VkPipelineColorBlendStateCreateInfo.calloc(stack).sType$Default().pAttachments(blendAttachments);
			VkPipelineViewportStateCreateInfo viewportState = VkPipelineViewportStateCreateInfo.calloc(stack).sType$Default().scissorCount(1).viewportCount(1);
			VkPipelineMultisampleStateCreateInfo multisampleState = VkPipelineMultisampleStateCreateInfo.calloc(stack)
				.sType$Default()
				.rasterizationSamples(1)
				.sampleShadingEnable(false);
			VkPipelineDynamicStateCreateInfo dynamicStateInfo = VkPipelineDynamicStateCreateInfo.calloc(stack).sType$Default().pDynamicStates(stack.ints(1, 0));
			VkPipelineRenderingCreateInfoKHR renderingInfo = VkPipelineRenderingCreateInfoKHR.calloc(stack).sType$Default();
			IntBuffer colorAttachmentFormats = stack.mallocInt(colorTargetStates.size());

			for (int i = 0; i < colorTargetStates.size(); i++) {
				ColorTargetState colorTargetState = colorTargetStates.get(i);
				colorAttachmentFormats.put(i, colorTargetState != null ? VulkanConst.toVk(colorTargetState.format()) : 0);
			}

			renderingInfo.pColorAttachmentFormats(colorAttachmentFormats);
			renderingInfo.depthAttachmentFormat(126);
			org.lwjgl.vulkan.VkGraphicsPipelineCreateInfo.Buffer createInfo = VkGraphicsPipelineCreateInfo.calloc(1, stack)
				.sType$Default()
				.flags(0)
				.pStages(shaderStages)
				.pVertexInputState(vertexInputState)
				.pInputAssemblyState(inputAssemblyState)
				.pRasterizationState(rasterizationState)
				.pDepthStencilState(vkDepthStencilState)
				.pColorBlendState(colorBlendState)
				.pViewportState(viewportState)
				.pMultisampleState(multisampleState)
				.pDynamicState(dynamicStateInfo)
				.layout(pipelineLayout)
				.pNext(renderingInfo);
			LongBuffer pointer = stack.callocLong(1);
			VulkanUtils.crashIfFailure(
				device, VK12.vkCreateGraphicsPipelines(device.vkDevice(), 0L, createInfo, null, pointer), "Can't compile pipeline " + pipelineCreateInfo.name()
			);
			long withDepthPipeline = pointer.get(0);
			device.instance().debug().setObjectName(device.vkDevice(), 19, withDepthPipeline, () -> "Pipeline " + pipelineCreateInfo.name());
			long withoutDepthPipeline;
			if (depthStencilState == null) {
				renderingInfo.depthAttachmentFormat(0);
				VulkanUtils.crashIfFailure(
					device, VK12.vkCreateGraphicsPipelines(device.vkDevice(), 0L, createInfo, null, pointer), "Can't compile pipeline " + pipelineCreateInfo.name()
				);
				withoutDepthPipeline = pointer.get(0);
				device.instance().debug().setObjectName(device.vkDevice(), 19, withoutDepthPipeline, () -> "Pipeline " + pipelineCreateInfo.name());
			} else {
				withoutDepthPipeline = 0L;
			}

			return new VulkanRenderPipeline(
				device, withDepthPipeline, withoutDepthPipeline, pipelineLayout, descriptorSetLayout, compiledShaderModules, pipelineCreateInfo.uniforms()
			);
		}
	}

	@Override
	public void destroy() {
		if (this.withDepthPipeline != 0L) {
			VK12.vkDestroyPipeline(this.device.vkDevice(), this.withoutDepthPipeline, null);
			VK12.vkDestroyPipeline(this.device.vkDevice(), this.withDepthPipeline, null);
			VK12.vkDestroyPipelineLayout(this.device.vkDevice(), this.pipelineLayout, null);
			VK12.vkDestroyDescriptorSetLayout(this.device.vkDevice(), this.descriptorSetLayout, null);

			for (int i = 0; i < this.shaderModules.size(); i++) {
				VK12.vkDestroyShaderModule(this.device.vkDevice(), this.shaderModules.getLong(i), null);
			}
		}
	}

	private static void applyBlendInformation(
		final org.lwjgl.vulkan.VkPipelineColorBlendAttachmentState.Buffer blendAttachments, final BlendFunction blendFunction
	) {
		blendAttachments.blendEnable(true)
			.colorBlendOp(VulkanConst.toVk(blendFunction.color().op()))
			.alphaBlendOp(VulkanConst.toVk(blendFunction.alpha().op()))
			.dstAlphaBlendFactor(VulkanConst.toVk(blendFunction.alpha().destFactor()))
			.dstColorBlendFactor(VulkanConst.toVk(blendFunction.color().destFactor()))
			.srcAlphaBlendFactor(VulkanConst.toVk(blendFunction.alpha().sourceFactor()))
			.srcColorBlendFactor(VulkanConst.toVk(blendFunction.color().sourceFactor()));
	}

	public VulkanDevice device() {
		return this.device;
	}

	public long withDepthPipeline() {
		return this.withDepthPipeline;
	}

	public long withoutDepthPipeline() {
		return this.withoutDepthPipeline;
	}

	public long pipelineLayout() {
		return this.pipelineLayout;
	}

	public List<BindGroupLayout.UniformDescription> uniforms() {
		return this.uniforms;
	}
}
