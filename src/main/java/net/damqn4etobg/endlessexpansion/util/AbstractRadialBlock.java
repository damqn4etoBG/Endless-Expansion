package net.damqn4etobg.endlessexpansion.util;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractRadialBlock extends Block implements TickableBlockEntity {

    protected AbstractRadialBlock(Properties pProperties) {
        super(pProperties);
    }

    public void CheckRadius(Level level, Player player, BlockPos pos) {
        int radius = getRadius(); // Get the radius from a method in your subclass
        int radiusSquared = radius * radius;

        if (player.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) <= radiusSquared) {
            // Player is within the specified radius of the block's center
            // You can perform actions or checks here
            // For example:
            player.sendSystemMessage(Component.literal("You are close to the block!"));
        }
    }

    // Abstract method that subclasses should implement to provide the radius
    protected abstract int getRadius();

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
}
