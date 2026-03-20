package net.damqn4etobg.endlessexpansion.networking.packet;

import net.damqn4etobg.endlessexpansion.event.client.bossbar.ModBossbarHandler;
import net.damqn4etobg.endlessexpansion.event.client.bossbar.ModBossbarInstance;
import net.damqn4etobg.endlessexpansion.event.server.ModServerBossEvent;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class UpdateModBossbarS2CPacket {
    private final String id;
    private final ModServerBossEvent.SetMode mode;
    private final Component newText;
    private final Integer newColorFrom;
    private final Integer newColorTo;
    private Boolean newVisible;
    private Boolean forced;

    public UpdateModBossbarS2CPacket(String id, ModServerBossEvent.SetMode mode, @Nullable Component text, @Nullable Integer colorFrom, @Nullable Integer colorTo, @Nullable Boolean forced, @Nullable Boolean newVisible) {
        this.id = id;
        this.mode = mode;
        this.newText = text;
        this.newColorFrom = colorFrom;
        this.newColorTo = colorTo;
        this.forced = forced != null && forced;
        this.newVisible = newVisible != null && newVisible;
    }

    public UpdateModBossbarS2CPacket(FriendlyByteBuf buf) {
        this.id = buf.readUtf();
        this.mode = buf.readEnum(ModServerBossEvent.SetMode.class);

        Component text = null;
        Integer from = null, to = null;
        Boolean vis = null;
        Boolean forced = null;

        switch (mode) {
            case TEXT -> text = buf.readComponent();
            case COLOR -> {
                from = buf.readInt();
                to = buf.readInt();
            }
            case VISIBILITY -> {
                forced = buf.readBoolean();
                vis = buf.readBoolean();
            }
        }
        this.newText = text;
        this.newColorFrom = from;
        this.newColorTo = to;
        this.forced = forced;
        this.newVisible = vis;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(id);
        buf.writeEnum(mode);

        switch (mode) {
            case TEXT -> buf.writeComponent(newText);
            case COLOR -> {
                buf.writeInt(newColorFrom);
                buf.writeInt(newColorTo);
            }
            case VISIBILITY -> {
                buf.writeBoolean(forced);
                buf.writeBoolean(newVisible);
            }
        }
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ModBossbarInstance bar = ModBossbarHandler.getClientBossbar(id);

            if (bar != null) {
                switch (mode) {
                    case TEXT -> bar.setDisplayName(newText);
                    case COLOR -> bar.setBarColor(newColorFrom, newColorTo);
                    case VISIBILITY -> {
                        if (forced) {
                            bar.setForcedVisible(true);
                            bar.setForcedVisibilityValue(newVisible);
                        } else {
                            bar.setForcedVisible(false);
                            bar.setForcedVisibilityValue(false);
                        }
                    }
                }
            }
        });
        return true;
    }
}