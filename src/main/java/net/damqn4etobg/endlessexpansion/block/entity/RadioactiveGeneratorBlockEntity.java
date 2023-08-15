package net.damqn4etobg.endlessexpansion.block.entity;

import net.damqn4etobg.endlessexpansion.util.TickableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class RadioactiveGeneratorBlockEntity extends BlockEntity implements TickableBlockEntity {
    private int counter;
    public RadioactiveGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RADIOACTIVE_GENERATOR.get(), pos, state);
    }

    @Override
    public void tick() {
        System.out.println(getLevel().isClientSide());
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);

        this.counter = nbt.getInt("Counter");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putInt("Counter", this.counter);
    }

    public int incrementCounter() {
        this.counter++;
        setChanged();
        return this.counter;
    }

    public int getCounter() {
        return this.counter;
    }
}
