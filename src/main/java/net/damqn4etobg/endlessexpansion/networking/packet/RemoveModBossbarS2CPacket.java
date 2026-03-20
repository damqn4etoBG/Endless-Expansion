package net.damqn4etobg.endlessexpansion.networking.packet;

import net.damqn4etobg.endlessexpansion.event.client.bossbar.ModBossbarHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RemoveModBossbarS2CPacket {
    private final String id;

    public RemoveModBossbarS2CPacket(String id) {
        this.id = id;
    }

    public RemoveModBossbarS2CPacket(FriendlyByteBuf buf) {
        this.id = buf.readUtf();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(id);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ModBossbarHandler.clientRemove(id);
        });
        return true;
    }
}
