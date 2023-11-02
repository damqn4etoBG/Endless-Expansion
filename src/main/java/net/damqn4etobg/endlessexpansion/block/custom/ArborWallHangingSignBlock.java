package net.damqn4etobg.endlessexpansion.block.custom;

import net.damqn4etobg.endlessexpansion.block.entity.ArborHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ArborWallHangingSignBlock extends WallHangingSignBlock {
    public ArborWallHangingSignBlock(Properties pProperties, WoodType pType) {
        super(pProperties, pType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ArborHangingSignBlockEntity(pPos, pState);
    }
}
