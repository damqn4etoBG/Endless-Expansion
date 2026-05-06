package net.damqn4etobg.endlessexpansion.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class MultiblockShape {
    private final Map<BlockPos, Block> blocks = new HashMap<>();

    public MultiblockShape add(BlockPos offset, Block block) {
        blocks.put(offset, block);
        return this;
    }

    public Map<BlockPos, Block> getBlocks() {
        return blocks;
    }
}
