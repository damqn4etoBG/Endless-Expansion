package net.damqn4etobg.endlessexpansion.networking.packet;

import net.damqn4etobg.endlessexpansion.block.entity.InfuserBlockEntity;
import net.damqn4etobg.endlessexpansion.block.entity.RadioactiveGeneratorBlockEntity;
import net.damqn4etobg.endlessexpansion.screen.InfuserMenu;
import net.damqn4etobg.endlessexpansion.screen.RadioactiveGeneratorMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EssenceSyncS2CPacket {
    private final int essence;
    private final BlockPos pos;

    public EssenceSyncS2CPacket(int essence, BlockPos pos) {
        this.essence = essence;
        this.pos = pos;
    }

    public EssenceSyncS2CPacket(FriendlyByteBuf buf) {
        this.essence = buf.readInt();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(essence);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof InfuserBlockEntity blockEntity) {
                blockEntity.setEssenceLevel(essence);

                if(Minecraft.getInstance().player.containerMenu instanceof InfuserMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    blockEntity.setEssenceLevel(essence);
                }
            }
        });
        return true;
    }
}
