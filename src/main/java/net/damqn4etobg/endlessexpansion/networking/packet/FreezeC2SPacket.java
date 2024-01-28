package net.damqn4etobg.endlessexpansion.networking.packet;

import net.damqn4etobg.endlessexpansion.dimension.ModDimensions;
import net.damqn4etobg.endlessexpansion.event.client.ClientFreezeData;
import net.damqn4etobg.endlessexpansion.freeze.PlayerFreezeProvider;
import net.damqn4etobg.endlessexpansion.networking.ModMessages;
import net.damqn4etobg.endlessexpansion.tag.ModTags;
import net.damqn4etobg.endlessexpansion.worldgen.biome.ModBiomes;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FluidState;
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

            BlockPos playerPos = player.blockPosition();
            Holder<Biome> holder = player.level().getBiome(playerPos);

            boolean inSpecificBiome = holder.is(ModTags.Biomes.IS_FROZEN_WASTES);

            if(player.level().dimension() == ModDimensions.WORLD_BEYOND_LEVEL_KEY) {
                player.getCapability(PlayerFreezeProvider.PLAYER_FREEZE).ifPresent(freeze -> {
                    if(inSpecificBiome) {
                        freeze.addFreeze(1);
                        if(player.isInWater()) {
                            freeze.addFreeze(1);
                        }
                    } else {
                        freeze.subFreeze(1);
                    }
                    if (freeze.getFreeze() == 10) {
                        player.hurt(player.level().damageSources().freeze(), 2f);
                    }

                    if (hasFireAroundPlayer(player, level, 2)) {
                        freeze.subFreeze(2);
                    }

                    ModMessages.sendToPlayer(new FreezeDataSyncS2CPacket(freeze.getFreeze()), player);
                });
            } else {
                player.getCapability(PlayerFreezeProvider.PLAYER_FREEZE).ifPresent(freeze -> {
                    ModMessages.sendToPlayer(new FreezeDataSyncS2CPacket(freeze.getFreeze()), player);
                });
            }
        });
        return true;
    }
    private boolean hasFireAroundPlayer(ServerPlayer player, ServerLevel level, int size) {
        return level.getBlockStates(player.getBoundingBox().inflate(size))
                .filter(state -> state.is(Blocks.CAMPFIRE) || state.is(Blocks.FIRE)
                        || state.is(Blocks.SOUL_CAMPFIRE) || state.is(Blocks.SOUL_FIRE)).toArray().length > 0;
    }
}
