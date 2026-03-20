package net.damqn4etobg.endlessexpansion.util;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class EndlessUtils {
    public static void addAdditionalInfo(List<Component> pTooltipComponents, String... paths) {
        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.endlessexpansion.hold_shift_active"));
            for(String path : paths) {
                pTooltipComponents.add(Component.translatable(path));
            }
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.endlessexpansion.hold_shift"));
        }
    }

    public static void addEffectBuff(Player player, MobEffect effect, int duration, int amplifier) {
        boolean hasEffect = player.hasEffect(effect);

        if(!hasEffect) {
            player.addEffect(new MobEffectInstance(effect, duration, amplifier, false, false, true));
        } else {
            MobEffectInstance currentEffect = player.getEffect(effect);
            if (currentEffect != null && currentEffect.getDuration() <= 20) {
                player.removeEffect(effect);
                player.addEffect(new MobEffectInstance(effect, duration, amplifier, false, false, true));
            }
        }
    }

    public static void addParticle(Level level, ParticleOptions particle, int particleCount, double pX, double pY, double pZ, double pXOffset, double pYOffset, double pZOffset, double pSpeed) {
        if(level.isClientSide()) {
            level.addParticle(particle, pX, pY, pZ, pXOffset, pYOffset, pZOffset);
        } else {
            ((ServerLevel) level).sendParticles(particle, pX, pY, pZ, particleCount, pXOffset, pYOffset, pZOffset, pSpeed);
        }
    }
}
