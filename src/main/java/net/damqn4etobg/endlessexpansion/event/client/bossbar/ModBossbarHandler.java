package net.damqn4etobg.endlessexpansion.event.client.bossbar;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.event.server.ModBossbarData;
import net.damqn4etobg.endlessexpansion.event.server.ModBossbarSavedEntry;
import net.damqn4etobg.endlessexpansion.networking.ModMessages;
import net.damqn4etobg.endlessexpansion.networking.packet.AddModBossbarS2CPacket;
import net.damqn4etobg.endlessexpansion.networking.packet.RemoveModBossbarS2CPacket;
import net.damqn4etobg.endlessexpansion.networking.packet.SyncAllModBossbarsS2CPacket;
import net.damqn4etobg.endlessexpansion.util.IClientLevelHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

import java.util.*;

public class ModBossbarHandler {
    private static final Map<String, ModBossbarInstance> SERVER_BOSSBARS = new HashMap<>();
    private static final Map<String, ModBossbarInstance> CLIENT_BOSSBARS = new HashMap<>();
    private static final Map<UUID, String> PENDING_BOSSBARS = new HashMap<>(); // uuid -> id

    public static void serverAdd(ModBossbarInstance bar) {
        SERVER_BOSSBARS.put(bar.getId(), bar);
        ModMessages.sendToClients(new AddModBossbarS2CPacket(bar));
    }

    public static void serverRemove(String id) {
        SERVER_BOSSBARS.remove(id);
        ModMessages.sendToClients(new RemoveModBossbarS2CPacket(id));
    }

    public static Collection<ModBossbarInstance> getServerBars() {
        return SERVER_BOSSBARS.values();
    }

    public static Collection<ModBossbarInstance> getClientBars() {
        return CLIENT_BOSSBARS.values();
    }

    public static ModBossbarInstance getServerBossbar(String id) {
        return ModBossbarHandler.getServerBars().stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }

    public static ModBossbarInstance getClientBossbar(String id) {
        return ModBossbarHandler.getClientBars().stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }

    public static Collection<String> getServerBossbarIDs() {
        Collection<String> ids = new ArrayList<>();
        for(ModBossbarInstance bar : getServerBars()) {
            ids.add(bar.getId());
        }
        return ids;
    }

    public static void clientAdd(ModBossbarInstance bar) {
        CLIENT_BOSSBARS.put(bar.getId(), bar);
    }

    public static void clientRemove(String id) {
        CLIENT_BOSSBARS.remove(id);
    }

    public static void serverClear() {
        SERVER_BOSSBARS.clear();
    }

    public static void clientSyncAll(Collection<ModBossbarInstance> bars) {
        CLIENT_BOSSBARS.clear();

        for (ModBossbarInstance bar : bars) {
            CLIENT_BOSSBARS.put(bar.getId(), bar);

            // try to attach the entity immediately if level is ready
            ClientLevel level = Minecraft.getInstance().level;
            if (level != null && bar.getEntity() == null) {
                LivingEntity entity = IClientLevelHelper.findEntityByUUID(level, bar.getEntityUUID());
                if (entity != null) {
                    bar.attachEntity(entity);
                }
            }
        }
    }

    public static void syncToAll() {
        ModMessages.sendToClients(new SyncAllModBossbarsS2CPacket(getServerBars()));
    }

    public static void clientTick() {
        ClientLevel level = Minecraft.getInstance().level;
        LocalPlayer player = Minecraft.getInstance().player;
        if (level == null) return;
        Iterator<Map.Entry<String, ModBossbarInstance>> iterator = CLIENT_BOSSBARS.entrySet().iterator();
        while (iterator.hasNext()) {
            ModBossbarInstance bar = iterator.next().getValue();
            bar.tickClient(level);
            if(bar.getEntity() == null || !bar.isAlive()) {
                iterator.remove();
                continue;
            }

            if(player != null && bar.getEntity() != null && bar.isPlayerInRange(player, 32D)) {
                bar.addVisiblePlayer(player.getUUID());
            } else if(player != null && bar.getEntity() != null && !bar.isPlayerInRange(player, 32D)) {
                bar.removeVisiblePlayer(player.getUUID());
            }
        }
    }

    public static void serverTick() {
        Iterator<Map.Entry<String, ModBossbarInstance>> iterator = SERVER_BOSSBARS.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, ModBossbarInstance> entry = iterator.next();
            ModBossbarInstance bar = entry.getValue();

            if (bar.getEntity() == null || !bar.getEntity().isAlive()) {
                iterator.remove();
                ModMessages.sendToClients(new RemoveModBossbarS2CPacket(bar.getId())); // notify all clients
            }
        }
    }

    public static void syncToPlayer(ServerPlayer player) {
        ModMessages.sendToPlayer(new SyncAllModBossbarsS2CPacket(getServerBars()), player);
    }

    public static List<ModBossbarInstance> getVisibleBarsForPlayer() {
        return CLIENT_BOSSBARS.values().stream().filter(ModBossbarInstance::isVisible).toList();
    }

    public static ModBossbarData getSavedData(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(ModBossbarData::load, ModBossbarData::new, "endexp_bossbars");
    }

    // Saving and Loading from disk
    public static void loadBossbars(ServerLevel level) {
        SERVER_BOSSBARS.clear();
        PENDING_BOSSBARS.clear();

        ModBossbarData savedData = getSavedData(level);

        for (ModBossbarSavedEntry entry : savedData.getAll()) {
            PENDING_BOSSBARS.put(entry.entityUUID, entry.id);
        }
    }

    public static void saveBossbars(ServerLevel level) {
        ModBossbarData data = getSavedData(level);
        data.clear();

        for (ModBossbarInstance bar : SERVER_BOSSBARS.values()) {
            data.add(new ModBossbarSavedEntry(bar));
        }

        data.setDirty();
        EndlessExpansion.LOGGER.info("Saved {} bossbars to disk", SERVER_BOSSBARS.size());
    }

    public static boolean hasPendingBossbar(UUID entityUUID) {
        return PENDING_BOSSBARS.containsKey(entityUUID);
    }

    public static String consumePendingBossbar(UUID entityUUID) {
        return PENDING_BOSSBARS.remove(entityUUID);
    }

    public static boolean hasBossbarForEntity(UUID entityUUID) {
        return SERVER_BOSSBARS.values().stream().anyMatch(bar -> bar.getEntityUUID().equals(entityUUID));
    }
}