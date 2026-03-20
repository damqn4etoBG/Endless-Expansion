package net.damqn4etobg.endlessexpansion.util;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface IClientLevelHelper {
    @Nullable
    static LivingEntity findEntityByUUID(ClientLevel level, UUID uuid) {
        for (Entity entity : level.entitiesForRendering()) {
            if (entity.getUUID().equals(uuid) && entity instanceof LivingEntity living) {
                return living;
            }
        }
        return null;
    }
}
