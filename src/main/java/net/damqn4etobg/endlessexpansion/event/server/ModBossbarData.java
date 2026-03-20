package net.damqn4etobg.endlessexpansion.event.server;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ModBossbarData extends SavedData {
    private final Map<String, ModBossbarSavedEntry> savedBossbars = new HashMap<>();

    public ModBossbarData() {
    }

    public static ModBossbarData load(CompoundTag tag) {
        ModBossbarData data = new ModBossbarData();

        ListTag bossbarList = tag.getList("Bossbars", Tag.TAG_COMPOUND);
        for (Tag bossbarTag : bossbarList) {
            if (bossbarTag instanceof CompoundTag bossbarCompound) {
                ModBossbarSavedEntry entry = ModBossbarSavedEntry.fromNBT(bossbarCompound);
                if (entry != null) {
                    data.savedBossbars.put(entry.id, entry);
                }
            }
        }
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag bossbarList = new ListTag();
        for (ModBossbarSavedEntry entry : savedBossbars.values()) {
            bossbarList.add(entry.toNBT());
        }
        tag.put("Bossbars", bossbarList);
        return tag;
    }

    public Collection<ModBossbarSavedEntry> getAll() {
        return savedBossbars.values();
    }

    public void add(ModBossbarSavedEntry entry) {
        savedBossbars.put(entry.id, entry);
        setDirty();
    }

    public void remove(String id) {
        savedBossbars.remove(id);
        setDirty();
    }

    public void clear() {
        savedBossbars.clear();
        setDirty();
    }

    public Map<String, ModBossbarSavedEntry> getSavedBossbars() {
        return savedBossbars;
    }
}
