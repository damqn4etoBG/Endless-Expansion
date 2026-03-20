package net.damqn4etobg.endlessexpansion.capability.wand;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.Map;

public class EvolutionData implements INBTSerializable<CompoundTag> {
    private final Map<String, String> highestEvolutions = new HashMap<>();

    public Map<String, String> getHighestEvolutions() {
        return highestEvolutions;
    }

    public void setHighestEvolution(String wand, String evoID) {
        highestEvolutions.put(wand, evoID);
    }

    public String getHighestEvolution(String wand) {
        return highestEvolutions.getOrDefault(wand, "");
    }

    public void copyFrom(EvolutionData data) {
        highestEvolutions.clear();
        highestEvolutions.putAll(data.getHighestEvolutions());
    }

    public void clearHighestEvolutions() {
        highestEvolutions.clear();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        highestEvolutions.forEach(tag::putString);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        highestEvolutions.clear();
        for(String key : nbt.getAllKeys()) {
            highestEvolutions.put(key, nbt.getString(key));
        }
    }
}
