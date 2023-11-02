package net.damqn4etobg.endlessexpansion.util;

public abstract class ModLuminiteEssence extends LuminiteEssence {
    public ModLuminiteEssence(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    @Override
    public int extractEssence(int maxExtract, boolean simulate) {
        int extractedEssence = super.extractEssence(maxExtract, simulate);
        if(extractedEssence != 0) {
            onEssenceChanged();
        }

        return extractedEssence;
    }

    @Override
    public int receiveEssence(int maxReceive, boolean simulate) {
        int receiveEssence = super.receiveEssence(maxReceive, simulate);
        if(receiveEssence != 0) {
            onEssenceChanged();
        }

        return receiveEssence;
    }

    public int setEssence(int essence) {
        this.essence = essence;
        return essence;
    }

    public abstract void onEssenceChanged();
}
