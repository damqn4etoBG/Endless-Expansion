package net.damqn4etobg.endlessexpansion.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ArborSignBlockEntity extends SignBlockEntity {
    public ArborSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ARBOR_SIGN.get(), pPos, pBlockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.ARBOR_SIGN.get();
    }
}
