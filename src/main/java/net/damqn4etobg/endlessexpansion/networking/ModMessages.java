package net.damqn4etobg.endlessexpansion.networking;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.networking.packet.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(EnergySyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(EnergySyncS2CPacket::new)
                .encoder(EnergySyncS2CPacket::toBytes)
                .consumerMainThread(EnergySyncS2CPacket::handle)
                .add();

        net.messageBuilder(FluidSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FluidSyncS2CPacket::new)
                .encoder(FluidSyncS2CPacket::toBytes)
                .consumerMainThread(FluidSyncS2CPacket::handle)
                .add();

        net.messageBuilder(FluidWasteSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FluidWasteSyncS2CPacket::new)
                .encoder(FluidWasteSyncS2CPacket::toBytes)
                .consumerMainThread(FluidWasteSyncS2CPacket::handle)
                .add();

        net.messageBuilder(TemperatureSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(TemperatureSyncS2CPacket::new)
                .encoder(TemperatureSyncS2CPacket::toBytes)
                .consumerMainThread(TemperatureSyncS2CPacket::handle)
                .add();

        net.messageBuilder(FreezeDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FreezeDataSyncS2CPacket::new)
                .encoder(FreezeDataSyncS2CPacket::toBytes)
                .consumerMainThread(FreezeDataSyncS2CPacket::handle)
                .add();

        net.messageBuilder(FreezeC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(FreezeC2SPacket::new)
                .encoder(FreezeC2SPacket::toBytes)
                .consumerMainThread(FreezeC2SPacket::handle)
                .add();

        net.messageBuilder(ProgressSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ProgressSyncS2CPacket::new)
                .encoder(ProgressSyncS2CPacket::toBytes)
                .consumerMainThread(ProgressSyncS2CPacket::handle)
                .add();

        net.messageBuilder(DashC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(DashC2SPacket::new)
                .encoder(DashC2SPacket::toBytes)
                .consumerMainThread(DashC2SPacket::handle)
                .add();

        net.messageBuilder(DashParticlesC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(DashParticlesC2SPacket::new)
                .encoder(DashParticlesC2SPacket::toBytes)
                .consumerMainThread(DashParticlesC2SPacket::handle)
                .add();

        net.messageBuilder(EffectParticlesC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(EffectParticlesC2SPacket::new)
                .encoder(EffectParticlesC2SPacket::toBytes)
                .consumerMainThread(EffectParticlesC2SPacket::handle)
                .add();

        net.messageBuilder(AddModBossbarS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(AddModBossbarS2CPacket::new)
                .encoder(AddModBossbarS2CPacket::toBytes)
                .consumerMainThread(AddModBossbarS2CPacket::handle)
                .add();

        net.messageBuilder(RemoveModBossbarS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(RemoveModBossbarS2CPacket::new)
                .encoder(RemoveModBossbarS2CPacket::toBytes)
                .consumerMainThread(RemoveModBossbarS2CPacket::handle)
                .add();

        net.messageBuilder(UpdateModBossbarS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(UpdateModBossbarS2CPacket::new)
                .encoder(UpdateModBossbarS2CPacket::toBytes)
                .consumerMainThread(UpdateModBossbarS2CPacket::handle)
                .add();

        net.messageBuilder(SyncAllModBossbarsS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SyncAllModBossbarsS2CPacket::new)
                .encoder(SyncAllModBossbarsS2CPacket::toBytes)
                .consumerMainThread(SyncAllModBossbarsS2CPacket::handle)
                .add();

        net.messageBuilder(EvolutionTableC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(EvolutionTableC2SPacket::new)
                .encoder(EvolutionTableC2SPacket::toBytes)
                .consumerMainThread(EvolutionTableC2SPacket::handle)
                .add();

        net.messageBuilder(EvolutionDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(EvolutionDataSyncS2CPacket::new)
                .encoder(EvolutionDataSyncS2CPacket::toBytes)
                .consumerMainThread(EvolutionDataSyncS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
