package net.damqn4etobg.endlessexpansion.networking.packet;

import net.damqn4etobg.endlessexpansion.event.client.bossbar.ModBossbarHandler;
import net.damqn4etobg.endlessexpansion.event.client.bossbar.ModBossbarInstance;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class SyncAllModBossbarsS2CPacket {
    private final List<ModBossbarInstance> bars;

    public SyncAllModBossbarsS2CPacket(Collection<ModBossbarInstance> bars) {
        this.bars = new ArrayList<>(bars);
    }

    public SyncAllModBossbarsS2CPacket(FriendlyByteBuf buf) {
        int size = buf.readVarInt();
        bars = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            bars.add(ModBossbarInstance.fromNetwork(buf));
        }
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeVarInt(bars.size());
        for (ModBossbarInstance bar : bars) {
            bar.toNetwork(buf);
        }
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ModBossbarHandler.clientSyncAll(bars);
        });
        return true;
    }
}
