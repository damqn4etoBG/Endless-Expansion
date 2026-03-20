package net.damqn4etobg.endlessexpansion.event.server;

import net.damqn4etobg.endlessexpansion.event.client.bossbar.ModBossbarInstance;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

import java.util.UUID;

public class ModBossbarSavedEntry {
    public String id;
    public UUID entityUUID;
    public Component displayName;
    public int colorFrom;
    public int colorTo;
    public boolean forcedVisible;
    public boolean forcedVisibleValue;

    public ModBossbarSavedEntry() {
    }

    public ModBossbarSavedEntry(ModBossbarInstance bar) {
        this.id = bar.getId();
        this.entityUUID = bar.getEntity().getUUID();
        this.displayName = bar.getDisplayName();
        this.colorFrom = bar.getColorFrom();
        this.colorTo = bar.getColorTo();
        this.forcedVisible = bar.getForcedVisible();
        this.forcedVisibleValue = bar.getForcedVisibleValue();
    }

    public static ModBossbarSavedEntry fromBar(ModBossbarInstance bar) {
        ModBossbarSavedEntry entry = new ModBossbarSavedEntry(bar);
        entry.id = bar.getId();
        entry.entityUUID = bar.getEntity().getUUID();
        entry.displayName = bar.getDisplayName();
        entry.colorFrom = bar.getColorFrom();
        entry.colorTo = bar.getColorTo();
        entry.forcedVisible = bar.getForcedVisible();
        entry.forcedVisibleValue = bar.getForcedVisibleValue();
        return entry;
    }

    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("Id", id);
        tag.putUUID("EntityUUID", entityUUID);
        tag.putString("DisplayName", Component.Serializer.toJson(displayName));
        tag.putInt("ColorFrom", colorFrom);
        tag.putInt("ColorTo", colorTo);
        tag.putBoolean("ForcedVisible", forcedVisible);
        tag.putBoolean("ForcedVisibleValue", forcedVisibleValue);
        return tag;
    }

    public static ModBossbarSavedEntry fromNBT(CompoundTag tag) {
        ModBossbarSavedEntry entry = new ModBossbarSavedEntry();
        entry.id = tag.getString("Id");
        entry.entityUUID = tag.getUUID("EntityUUID");
        entry.displayName = Component.Serializer.fromJson(tag.getString("DisplayName"));
        entry.colorFrom = tag.getInt("ColorFrom");
        entry.colorTo = tag.getInt("ColorTo");
        entry.forcedVisible = tag.getBoolean("ForcedVisible");
        entry.forcedVisibleValue = tag.getBoolean("ForcedVisibleValue");
        return entry;
    }
}
