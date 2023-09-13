package net.damqn4etobg.endlessexpansion.event;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.command.WorldBeyondTP;
import net.damqn4etobg.endlessexpansion.freeze.PlayerFreezeProvider;
import net.damqn4etobg.endlessexpansion.networking.ModMessages;
import net.damqn4etobg.endlessexpansion.networking.packet.FreezeDataSyncS2CPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = EndlessExpansion.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    protected Minecraft minecraft;

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new WorldBeyondTP(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerFreezeProvider.PLAYER_FREEZE).ifPresent(freeze -> {
                    ModMessages.sendToPlayer(new FreezeDataSyncS2CPacket(freeze.getFreeze()), player);
                });
            }
        }
    }
}
