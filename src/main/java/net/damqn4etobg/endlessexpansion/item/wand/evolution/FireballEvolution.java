package net.damqn4etobg.endlessexpansion.item.wand.evolution;

import net.damqn4etobg.endlessexpansion.effect.ModMobEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class FireballEvolution extends WandEvolution {
    public FireballEvolution(String id) {
        super(id);
    }

    @Override
    public void use(Level level, Player player, ItemStack stack) {
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FIRECHARGE_USE, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        double d0 = player.getX() + (double) ((float) player.getDirection().getStepX() * 0.35F);
        double d1 = player.getY() + (double) ((float) player.getDirection().getStepY() * 0.35F) + 1;
        double d2 = player.getZ() + (double) ((float) player.getDirection().getStepZ() * 0.35F);
        if (!level.isClientSide) {
            SmallFireball smallfireball = new CustomSmallFireball(level, d0, d1, d2, 0, 0, 0);
            // smallfireball.setItem(itemstack); the item shot, in this case the wand, we want a fireball.
            smallfireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3F, 1.0F);
            level.addFreshEntity(smallfireball);
        }
    }

    public static class CustomSmallFireball extends SmallFireball {
        private int ticksCounted = 0;

        public CustomSmallFireball(Level pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
        }

        @Override
        public void tick() {
            super.tick();
            ticksCounted++;
            if (ticksCounted >= 40) {
                this.remove(Entity.RemovalReason.DISCARDED);
            }
            if(this.isInWater()) {
                this.remove(Entity.RemovalReason.DISCARDED);
            }
        }

        @Override
        protected void onHitEntity(EntityHitResult pResult) {
            super.onHitEntity(pResult);
            Entity entity = pResult.getEntity();
            if (entity instanceof LivingEntity livingEntity) {
                if(livingEntity.hasEffect(ModMobEffects.FREEZING.get())) {
                    livingEntity.removeEffect(ModMobEffects.FREEZING.get());
                }
            }
        }
    }
}
