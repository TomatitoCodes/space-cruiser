package com.spacecruiser.item;

import com.spacecruiser.ModEntities;
import com.spacecruiser.entity.SpaceCruiserEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpaceCruiserItem extends Item {
	public SpaceCruiserItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		if (!(world instanceof ServerWorld serverWorld)) {
			return ActionResult.SUCCESS;
		}

		BlockPos pos = context.getBlockPos().offset(context.getSide());
		SpaceCruiserEntity ship = ModEntities.SPACE_CRUISER.create(serverWorld);
		if (ship == null) {
			return ActionResult.FAIL;
		}

		ship.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY() + 0.1, pos.getZ() + 0.5, context.getPlayerYaw(), 0.0f);
		if (!world.isSpaceEmpty(ship)) {
			return ActionResult.FAIL;
		}

		serverWorld.spawnEntity(ship);
		context.getStack().decrementUnlessCreative(1, context.getPlayer());
		return ActionResult.CONSUME;
	}
}
