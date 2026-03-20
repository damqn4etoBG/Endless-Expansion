package net.damqn4etobg.endlessexpansion.event.server;

import net.damqn4etobg.endlessexpansion.event.client.bossbar.ModBossbarHandler;
import net.damqn4etobg.endlessexpansion.event.client.bossbar.ModBossbarInstance;
import net.damqn4etobg.endlessexpansion.networking.ModMessages;
import net.damqn4etobg.endlessexpansion.networking.packet.UpdateModBossbarS2CPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class ModServerBossEvent {
    public static void addBossbar(LivingEntity entity, Component displayName) {
        ModBossbarInstance bossbar = new ModBossbarInstance(entity, displayName);
        ModBossbarHandler.serverAdd(bossbar); // save server copy
        ModBossbarHandler.syncToAll();
    }

    public static void addBossbarWithID(String id, LivingEntity entity, Component displayName) {
        ModBossbarInstance bossbar = new ModBossbarInstance(id, entity, displayName);
        ModBossbarHandler.serverAdd(bossbar);
        ModBossbarHandler.syncToAll();
    }

    public static void addBoss(Level level, LivingEntity boss) {
        if (!level.isClientSide() && !ModBossbarHandler.hasBossbarForEntity(boss.getUUID())) {
            addBossbar(boss, boss.getDisplayName());
        }
    }

    public static void removeBossbar(String id) {
        ModBossbarHandler.serverRemove(id);
        ModBossbarHandler.syncToAll();
    }

    public static void updateColor(String id, int colorFrom, int colorTo) {
        ModBossbarInstance bar = ModBossbarHandler.getServerBossbar(id);
        if(bar != null) {
            bar.setBarColor(colorFrom, colorTo);
            ModMessages.sendToClients(new UpdateModBossbarS2CPacket(id, SetMode.COLOR, null, colorFrom, colorTo, null, null));
            ModBossbarHandler.syncToAll();
        }
    }

    public static void updateText(String id, Component text) {
        ModBossbarInstance bar = ModBossbarHandler.getServerBossbar(id);
        if(bar != null) {
            bar.setDisplayName(text);
            ModMessages.sendToClients(new UpdateModBossbarS2CPacket(id, SetMode.TEXT, text, null, null, null, null));
            ModBossbarHandler.syncToAll();
        }
    }

    public static void updateVisibility(String id, boolean forcedVisible, boolean visible) {
        ModBossbarInstance bar = ModBossbarHandler.getServerBossbar(id);
        if(bar != null) {
            bar.setForcedVisible(forcedVisible);
            bar.setForcedVisibilityValue(visible);
            ModMessages.sendToClients(new UpdateModBossbarS2CPacket(id, SetMode.VISIBILITY, null, null, null, forcedVisible, visible));
            ModBossbarHandler.syncToAll();
        }
    }

    public static void syncProperties(String id, Component text, int barColorFrom, int barColorTo, boolean visible) {
        updateText(id, text);
        updateColor(id, barColorFrom, barColorTo);
        ModMessages.sendToClients(new UpdateModBossbarS2CPacket(id, SetMode.VISIBILITY, null, null, null, false, visible));
        //updateVisibility(id, visible);
    }

    public enum SetMode {
        COLOR,
        TEXT,
        VISIBILITY,
        ENTITY
    }
}
