package net.damqn4etobg.endlessexpansion.networking.packet;

import net.damqn4etobg.endlessexpansion.block.entity.EvolutionTableBlockEntity;
import net.damqn4etobg.endlessexpansion.item.wand.evolution.WandEvolution;
import net.damqn4etobg.endlessexpansion.screen.menu.EvolutionTableMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class EvolutionTableC2SPacket {
    private final WandEvolution evolution;
    private final UUID playerUUID;
    private final int xpCost;

    public EvolutionTableC2SPacket(WandEvolution evolution, UUID playerUUID, int xpCost) {
        this.evolution = evolution;
        this.playerUUID = playerUUID;
        this.xpCost = xpCost;
    }

    public EvolutionTableC2SPacket(FriendlyByteBuf buf) {
        this.evolution = WandEvolution.fromNetwork(buf);
        this.playerUUID = buf.readUUID();
        this.xpCost = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        evolution.toNetwork(buf);
        buf.writeUUID(playerUUID);
        buf.writeInt(xpCost);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null && player.containerMenu instanceof EvolutionTableMenu menu) {
                BlockEntity entity = menu.getBlockEntity();
                if(entity instanceof EvolutionTableBlockEntity be) {
                    be.evolveWand(evolution, playerUUID, xpCost);
                }
            }
        });
        return true;
    }
}
