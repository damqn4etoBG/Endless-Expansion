package net.damqn4etobg.endlessexpansion.event.common;

import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class PlayerShadowDashEvent extends PlayerEvent {
    public PlayerShadowDashEvent(Player player) {
        super(player);
    }
}
