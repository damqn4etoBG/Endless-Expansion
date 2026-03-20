package net.damqn4etobg.endlessexpansion.capability.wand;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EvolutionDataProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final Capability<EvolutionData> EVOLUTION_DATA = CapabilityManager.get(new CapabilityToken<>(){});
    private EvolutionData data =  null;
    private final LazyOptional<EvolutionData> optional = LazyOptional.of(this::createData);

    private EvolutionData createData() {
        if(this.data == null) {
            this.data = new EvolutionData();
        }
        return this.data;
    }

    @Override
    public CompoundTag serializeNBT() {
        return createData().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createData().deserializeNBT(nbt);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
        if(capability == EVOLUTION_DATA) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }
}
