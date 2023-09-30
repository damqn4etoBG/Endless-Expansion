package net.damqn4etobg.endlessexpansion.effect;

import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GrowthSpurtEffect extends MobEffect {
    protected GrowthSpurtEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }
    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {

//        if (!pLivingEntity.level().isClientSide()) {
//            pLivingEntity.getDimensions(Pose.STANDING).scale(5f, 5f);
//        }
        Pose pPose = pLivingEntity.getPose();
        EntityDimensions.scalable(5f, 5f);
        pLivingEntity.getDimensions(pPose).scale(5f);

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
