package net.damqn4etobg.endlessexpansion.networking.packet;

import net.damqn4etobg.endlessexpansion.event.client.ClientFreezeData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FreezeDataSyncS2CPacket {
    private final int freeze;

    public FreezeDataSyncS2CPacket(int thirst) {
        this.freeze = thirst;
    }

    public FreezeDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.freeze = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(freeze);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            ClientFreezeData.set(freeze);
        });
        return true;
    }
}
