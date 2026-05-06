package net.damqn4etobg.endlessexpansion.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public interface IMultiblock {
    MultiblockShape getShape();
    BlockPos getOrigin();
    Direction getFacing();

    default boolean matches(Level level) {
        if (level == null) return false;

        for (var entry : getShape().getBlocks().entrySet()) {
            BlockPos offset = rotate(entry.getKey(), getFacing());
            BlockPos worldPos = getOrigin().offset(offset);

            Block actual = level.getBlockState(worldPos).getBlock();

            if (actual != entry.getValue()) {
                return false;
            }
        }

        return true;
    }

    private static BlockPos rotate(BlockPos pos, Direction facing) {
        return switch (facing) {
            case NORTH -> pos;
            case SOUTH -> new BlockPos(-pos.getX(), pos.getY(), -pos.getZ());
            case WEST  -> new BlockPos(pos.getZ(), pos.getY(), -pos.getX());
            case EAST  -> new BlockPos(-pos.getZ(), pos.getY(), pos.getX());
            default -> pos;
        };
    }
}
