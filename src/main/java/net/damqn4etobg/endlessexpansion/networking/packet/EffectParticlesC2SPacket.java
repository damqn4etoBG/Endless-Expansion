package net.damqn4etobg.endlessexpansion.networking.packet;

import net.damqn4etobg.endlessexpansion.effect.ModMobEffects;
import net.damqn4etobg.endlessexpansion.particle.ModParticles;
import net.damqn4etobg.endlessexpansion.util.EndlessUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EffectParticlesC2SPacket {

    public EffectParticlesC2SPacket() {

    }

    public EffectParticlesC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    // the Dist.CLIENT in ModClientEvents prevents us from directly using that method
    public void spawnFreezeParticles(Player player, Level world, RandomSource random) {
        if (random.nextFloat() < 0.25f) {
            double x = player.getX() + random.nextDouble() - 0.5;
            double y = player.getY() + random.nextDouble() + 0.5;
            double z = player.getZ() + random.nextDouble() - 0.5;
            EndlessUtils.addParticle(world, ModParticles.SNOWFLAKE.get(), 1, x, y, z, 0d, 0.025d, 0d, 0.025d);
        }
    }

    public void spawnShadowParticles(Player player, Level world, RandomSource random) {
        double x = player.getX() + random.nextDouble() - 0.5;
        double y = player.getY() + random.nextDouble() + 0.5;
        double z = player.getZ() + random.nextDouble() - 0.5;

        if (random.nextFloat() < 0.125f) {
            EndlessUtils.addParticle(world, ModParticles.SHADOW_ORB.get(), 1, x, y, z, 0d, 0.025d, 0d, 0.025d);
        }

        if (random.nextFloat() < 0.125f) {
            EndlessUtils.addParticle(world, ModParticles.SHADOW_STRIP.get(), 1, x, y, z, 0d, 0.025d, 0d, 0.025d);
        }
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            if (player != null) {
                RandomSource random = player.getRandom();
                Level world = player.level();

                if (player.hasEffect(ModMobEffects.FREEZING.get())) {
                   spawnFreezeParticles(player, world, random);
                }
                if (player.hasEffect(ModMobEffects.SHADOW_STATE.get())) {
                    spawnShadowParticles(player, world, random);
                }
            }
        });
        return true;
    }
}
