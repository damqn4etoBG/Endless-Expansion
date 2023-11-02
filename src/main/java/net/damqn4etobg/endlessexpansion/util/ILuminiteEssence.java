package net.damqn4etobg.endlessexpansion.util;

public interface ILuminiteEssence {
    int receiveEssence(int maxReceive, boolean simulate);

    int extractEssence(int maxExtract, boolean simulate);

    int getEssence();

    int getMaxEssence();

    boolean canExtract();

    boolean canReceive();
}
