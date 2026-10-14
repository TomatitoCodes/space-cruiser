package com.spacecruiser.item;

import com.spacecruiser.entity.SpaceCruiserEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class SpaceCruiserItem extends Item {
	private final Supplier<EntityType<SpaceCruiserEntity>> entityType;

	public SpaceCruiserItem(Properties properties, Supplier<EntityType<SpaceCruiserEntity>> entityType) {
		super(properties);
		this.entityType = entityType;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		if (!(level instanceof ServerLevel serverLevel)) {
			return InteractionResult.SUCCESS;
		}

		BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
		SpaceCruiserEntity ship = this.entityType.get().create(serverLevel, EntitySpawnReason.SPAWN_ITEM_USE);
		if (ship == null) {
			return InteractionResult.FAIL;
		}

		ship.snapTo(pos.getX() + 0.5, pos.getY() + 0.1, pos.getZ() + 0.5, context.getRotation(), 0.0f);
		if (!level.noCollision(ship)) {
			return InteractionResult.FAIL;
		}

		serverLevel.addFreshEntity(ship);
		context.getItemInHand().consume(1, context.getPlayer());
		return InteractionResult.CONSUME;
	}
}
