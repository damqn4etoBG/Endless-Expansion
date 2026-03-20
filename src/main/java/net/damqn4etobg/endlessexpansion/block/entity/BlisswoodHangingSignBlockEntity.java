package net.damqn4etobg.endlessexpansion.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlisswoodHangingSignBlockEntity extends HangingSignBlockEntity {
    public BlisswoodHangingSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(pPos, pBlockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.BLISSWOOD_HANGING_SIGN.get();
    }
}
