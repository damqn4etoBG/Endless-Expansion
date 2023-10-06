package net.damqn4etobg.endlessexpansion.networking.packet;

import net.damqn4etobg.endlessexpansion.dimension.ModDimensions;
import net.damqn4etobg.endlessexpansion.freeze.PlayerFreezeProvider;
import net.damqn4etobg.endlessexpansion.networking.ModMessages;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FreezeC2SPacket {

    public FreezeC2SPacket() {

    }

    public FreezeC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel().getLevel();

            if(isInWater(player) && isInWorldBeyond(level)) {
                player.getCapability(PlayerFreezeProvider.PLAYER_FREEZE).ifPresent(freeze -> {
                    player.sendSystemMessage(Component.literal("Current Freeze " + freeze.getFreeze())
                            .withStyle(ChatFormatting.AQUA));
                    ModMessages.sendToPlayer(new FreezeDataSyncS2CPacket(freeze.getFreeze()), player);
                    player.isFreezing();
                });
            } else {
                // Notify the player that there is no water around!
                player.sendSystemMessage(Component.literal("asd").withStyle(ChatFormatting.RED));
                // Output the current thirst level
                player.getCapability(PlayerFreezeProvider.PLAYER_FREEZE).ifPresent(freeze -> {
                    player.sendSystemMessage(Component.literal("Current Freeze " + freeze.getFreeze())
                            .withStyle(ChatFormatting.AQUA));
                    ModMessages.sendToPlayer(new FreezeDataSyncS2CPacket(freeze.getFreeze()), player);
                });
            }
        });
        return true;
    }

    private boolean isInWater(ServerPlayer player) {
        return player.isInFluidType(Fluids.WATER.getFluidType());
    }

    private boolean isInWorldBeyond(ServerLevel level) {
        return level.dimension() == ModDimensions.WORLD_BEYOND_LEVEL_KEY;
    }
}
