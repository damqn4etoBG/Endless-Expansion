package net.damqn4etobg.endlessexpansion.networking.packet;

import net.damqn4etobg.endlessexpansion.capability.wand.EvolutionDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EvolutionDataSyncS2CPacket {
    private final CompoundTag tag;

    public EvolutionDataSyncS2CPacket(CompoundTag tag) {
        this.tag = tag;
    }

    public EvolutionDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.tag = buf.readNbt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeNbt(tag);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                player.getCapability(EvolutionDataProvider.EVOLUTION_DATA).ifPresent(data -> {
                    data.deserializeNBT(tag);
                });
            }
        });
        return true;
    }
}
