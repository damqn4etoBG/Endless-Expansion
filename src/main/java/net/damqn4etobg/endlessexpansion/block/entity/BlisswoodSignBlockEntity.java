package net.damqn4etobg.endlessexpansion.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlisswoodSignBlockEntity extends SignBlockEntity {
    public BlisswoodSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.BLISSWOOD_SIGN.get(), pPos, pBlockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.BLISSWOOD_SIGN.get();
    }
}
