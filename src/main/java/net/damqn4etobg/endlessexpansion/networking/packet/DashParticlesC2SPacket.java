package net.damqn4etobg.endlessexpansion.networking.packet;

import net.damqn4etobg.endlessexpansion.capability.dash.PlayerDashProvider;
import net.damqn4etobg.endlessexpansion.effect.ModMobEffects;
import net.damqn4etobg.endlessexpansion.particle.ModParticles;
import net.damqn4etobg.endlessexpansion.util.EndlessUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DashParticlesC2SPacket {

    public DashParticlesC2SPacket() {

    }

    public DashParticlesC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender(); // Server-side player

            if (player != null && player.hasEffect(ModMobEffects.SHADOW_STATE.get())) {
                player.getCapability(PlayerDashProvider.PLAYER_DASH).ifPresent(dash -> {
                    RandomSource random = player.getRandom();
                    Level world = player.level();

                    // shadow smoke
                    if (dash.canDash()) {
                        for (int i = 0; i < 10; i++) {
                            double x = player.getX() + random.nextGaussian() * 0.15;
                            double y = player.getY() + random.nextGaussian() * 0.15;
                            double z = player.getZ() + random.nextGaussian() * 0.15;

                            EndlessUtils.addParticle(world, ModParticles.SHADOW_SMOKE.get(), 1, x, y, z, random.nextGaussian() * 0.05, random.nextGaussian() * 0.05, random.nextGaussian() * 0.05, 0.05D);
                        }
                    }
                });
            }
        });
        return true;
    }
}
