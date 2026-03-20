package net.damqn4etobg.endlessexpansion.event.client.bossbar;

import net.damqn4etobg.endlessexpansion.util.IClientLevelHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ModBossbarInstance {
    private final String id;
    private final UUID entityUUID;
    private LivingEntity bossEntity;
    private Component displayName;
    private int maxHealth;
    private int currentHealth;
    private int colorFrom;
    private int colorTo;
    private boolean visible;
    private float alpha;
    private final Set<UUID> visiblePlayers = new HashSet<>();
    private boolean forcedVisible = false;
    private boolean forceVisibilityValue = false;
    private int displayHealth = -1;

    public ModBossbarInstance(LivingEntity bossEntity, Component displayName) {
        this.id = "bar_" + UUID.randomUUID().toString().substring(0, 8);
        this.bossEntity = bossEntity;
        this.entityUUID = bossEntity.getUUID();
        this.displayName = displayName;
        this.maxHealth = (int) bossEntity.getMaxHealth();
        this.currentHealth = (int) bossEntity.getHealth();
        this.colorFrom = 0xFFb50000;
        this.colorTo = 0xFFFF0000;
        this.visible = true;
    }

    public ModBossbarInstance(String id, LivingEntity entity, Component displayName) {
        this.id = id;
        this.bossEntity = entity;
        this.displayName = displayName;
        this.entityUUID = entity.getUUID();
        this.maxHealth = (int) entity.getMaxHealth();
        this.currentHealth = (int) entity.getHealth();
        this.colorFrom = 0xFFb50000;
        this.colorTo = 0xFFFF0000;
        this.visible = true;
        this.alpha = 1.0f;
    }

    public ModBossbarInstance(String id, LivingEntity bossEntity, Component displayName, int colorFrom, int colorTo, boolean forcedVisible, boolean forceVisibilityValue) {
        this.id = id;
        this.bossEntity = bossEntity;
        this.entityUUID = bossEntity != null ? bossEntity.getUUID() : UUID.randomUUID();
        this.displayName = displayName;
        this.maxHealth = bossEntity != null ? (int) bossEntity.getMaxHealth() : 1;
        this.currentHealth = bossEntity != null ? (int) bossEntity.getHealth() : 1;
        this.colorFrom = colorFrom;
        this.colorTo = colorTo;
        this.forcedVisible = forcedVisible;
        this.forceVisibilityValue = forceVisibilityValue;
    }

    public ModBossbarInstance(String id, UUID entityUUID, Component displayName, int colorFrom, int colorTo, boolean forcedVisible, boolean forceVisibilityValue) {
        this.id = id;
        this.entityUUID = entityUUID;
        this.displayName = displayName;
        this.colorFrom = colorFrom;
        this.colorTo = colorTo;
        this.forcedVisible = forcedVisible;
        this.forceVisibilityValue = forceVisibilityValue;
        this.alpha = forceVisibilityValue ? 1.0f : 0.0f;
        this.bossEntity = null;
    }

    public void tickClient(ClientLevel level) {
        if (bossEntity == null && entityUUID != null) {
            LivingEntity living = IClientLevelHelper.findEntityByUUID(level, entityUUID);

            if (living != null) {
                this.attachEntity(living);
            }
        }

        if (bossEntity != null && bossEntity.isAlive()) {
            this.currentHealth = (int) bossEntity.getHealth();
        }

        if(bossEntity != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            if(player != null) {
                boolean inRange = bossEntity.distanceToSqr(player) <= (32D * 32D);

                if (forcedVisible) {
                    if (forceVisibilityValue) {
                        alpha = Math.min(1f, alpha + 0.1f); // Fade in
                    } else {
                        alpha = Math.max(0f, alpha - 0.1f); // Fade out
                    }
                } else { // normal
                    if (inRange) {
                        alpha = Math.min(1f, alpha + 0.1f);
                    } else {
                        alpha = Math.max(0f, alpha - 0.1f);
                    }
                }

                visible = alpha > 0.01f;
            }
        }
    }

    public void setVisibilityForced(boolean forced, boolean visibleValue) {
        this.forcedVisible = forced;
        this.forceVisibilityValue = visibleValue;

        if (!forced) { // unforcing check
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null && bossEntity != null) {
                if (isPlayerInRange(player, 32D)) {
                    this.alpha = 1.0f;
                } else {
                    this.alpha = 0.0f;
                }
            }
        }
    }

    public void attachEntity(LivingEntity boss) {
        this.bossEntity = boss;
        this.maxHealth = (int) boss.getMaxHealth();
        this.currentHealth = (int) boss.getHealth();
    }

    public float getHealthPercent() {
        return maxHealth == 0 ? 0 : (float) currentHealth / maxHealth;
    }

    public boolean isAlive() {
        return bossEntity != null && bossEntity.isAlive();
    }

    public void updateDisplayHealth() {
        if (displayHealth < 0) displayHealth = getHealth(); // Init
        displayHealth = Mth.lerpInt(0.1f, displayHealth, getHealth());
    }

    public float getDisplayHealthPercent() {
        return getMaxHealth() == 0 ? 0 : displayHealth / (float) getMaxHealth();
    }

    public int getDisplayHealthPercentInt() {
        return Math.round(getDisplayHealthPercent() * 100f);
    }

    // serialization
    public void toNetwork(FriendlyByteBuf buf) {
        buf.writeUtf(id);
        buf.writeUUID(entityUUID);
        buf.writeComponent(displayName);
        buf.writeInt(colorFrom);
        buf.writeInt(colorTo);
        buf.writeBoolean(forcedVisible);
        buf.writeBoolean(forceVisibilityValue);
    }

    public static ModBossbarInstance fromNetwork(FriendlyByteBuf buf) {
        String id = buf.readUtf();
        UUID entityUUID = buf.readUUID();
        Component displayName = buf.readComponent();
        int colorFrom = buf.readInt();
        int colorTo = buf.readInt();
        boolean forcedVisible = buf.readBoolean();
        boolean forcedVisibilityValue = buf.readBoolean();

        ModBossbarInstance instance = new ModBossbarInstance(id, entityUUID, displayName, colorFrom, colorTo, forcedVisible, forcedVisibilityValue);

        // attach entity if it already exists in the world
        ClientLevel level = Minecraft.getInstance().level;
        if (level != null) {
            LivingEntity entity = IClientLevelHelper.findEntityByUUID(level, entityUUID);
            if (entity != null) {
                instance.attachEntity(entity);
            }
        }
        return instance;
    }

    public boolean isPlayerInRange(Player player, double radius) {
        return bossEntity.distanceToSqr(player) <= radius * radius;
    }

    public String getId() { return id; }
    public UUID getEntityUUID() { return entityUUID; }
    public boolean isVisible() { return alpha > 0.01f; }
    public float getAlpha() { return alpha; }
    public Component getDisplayName() { return displayName; }
    public int getColorFrom() { return colorFrom; }
    public int getColorTo() { return colorTo; }
    public int getHealth() { return currentHealth; }
    public int getMaxHealth() { return maxHealth; }
    public boolean getVisible() { return visible; }
    public boolean getForcedVisible() { return forcedVisible; }
    public boolean getForcedVisibleValue() { return forceVisibilityValue; }
    public LivingEntity getEntity() { return bossEntity; }
    public void setDisplayName(Component text) { this.displayName = text; }
    public void setBarColor(int colorFrom, int colorTo) { this.colorFrom = colorFrom; this.colorTo = colorTo; }
    public void setVisible(boolean visible) { this.visible = visible; }
    public void setForcedVisible(boolean visible) { this.forcedVisible = visible; }
    public void setForcedVisibilityValue(boolean value) { this.forceVisibilityValue = value; }
    public void addVisiblePlayer(UUID playerId) { visiblePlayers.add(playerId); }
    public void removeVisiblePlayer(UUID playerId) { visiblePlayers.remove(playerId); }
    public Set<UUID> getVisiblePlayers() { return visiblePlayers; }
}
