package net.damqn4etobg.endlessexpansion.util;

import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public class LuminiteEssence implements ILuminiteEssence, INBTSerializable<Tag> {
    protected int essence;
    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;

    public LuminiteEssence(int capacity) {
        this(capacity, capacity, capacity, 0);
    }

    public LuminiteEssence(int capacity, int maxTransfer) {
        this(capacity, maxTransfer, maxTransfer, 0);
    }

    public LuminiteEssence(int capacity, int maxReceive, int maxExtract)
    {
        this(capacity, maxReceive, maxExtract, 0);
    }

    public LuminiteEssence(int capacity, int maxReceive, int maxExtract, int essence)
    {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.essence = Math.max(0 , Math.min(capacity, essence));
    }

    @Override
    public int receiveEssence(int maxReceive, boolean simulate) {
        if (!canReceive())
            return 0;

        int essenceRecieved = Math.min(capacity - essence, Math.min(this.maxReceive, maxReceive));
        if (!simulate)
            essence += essenceRecieved;
        return essenceRecieved;
    }

    @Override
    public int extractEssence(int maxExtract, boolean simulate) {
        if (!canExtract())
            return 0;

        int essenceExtracted = Math.min(essence, Math.min(this.maxExtract, maxExtract));
        if (!simulate)
            essence -= essenceExtracted;
        return essenceExtracted;
    }

    @Override
    public int getEssence() {
        return essence;
    }

    @Override
    public int getMaxEssence() {
        return capacity;
    }

    @Override
    public boolean canExtract() {
        return this.maxExtract > 0;
    }

    @Override
    public boolean canReceive() {
        return this.maxReceive > 0;
    }

    @Override
    public Tag serializeNBT() {
        return IntTag.valueOf(this.getEssence());
    }

    @Override
    public void deserializeNBT(Tag nbt) {
        if (!(nbt instanceof IntTag intNbt))
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        this.essence = intNbt.getAsInt();
    }
}
