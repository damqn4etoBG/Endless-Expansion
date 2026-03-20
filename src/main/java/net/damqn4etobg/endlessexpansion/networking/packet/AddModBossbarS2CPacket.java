package net.damqn4etobg.endlessexpansion.networking.packet;

import net.damqn4etobg.endlessexpansion.event.client.bossbar.ModBossbarHandler;
import net.damqn4etobg.endlessexpansion.event.client.bossbar.ModBossbarInstance;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AddModBossbarS2CPacket {
    private final ModBossbarInstance bossbar;

    public AddModBossbarS2CPacket(ModBossbarInstance bossbar) {
        this.bossbar = bossbar;
    }

    public AddModBossbarS2CPacket(FriendlyByteBuf buf) {
        this.bossbar = ModBossbarInstance.fromNetwork(buf);
    }

    public void toBytes(FriendlyByteBuf buf) {
        bossbar.toNetwork(buf);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ModBossbarHandler.clientAdd(bossbar);
        });
        return true;
    }
}

