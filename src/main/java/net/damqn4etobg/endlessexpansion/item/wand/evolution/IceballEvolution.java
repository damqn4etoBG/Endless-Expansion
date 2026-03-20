package net.damqn4etobg.endlessexpansion.item.wand.evolution;

import net.damqn4etobg.endlessexpansion.effect.ModMobEffects;
import net.damqn4etobg.endlessexpansion.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class IceballEvolution extends WandEvolution {
    public IceballEvolution(String id) {
        super(id);
    }

    @Override
    public void use(Level level, Player player, ItemStack stack) {
        ItemStack projectileStack = Blocks.ICE.asItem().getDefaultInstance();
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOW_HIT, SoundSource.NEUTRAL, 1.0F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!level.isClientSide) {
            Snowball snowball = new IceWandProjectile(level, player);
            snowball.setItem(projectileStack);
            snowball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2F, 1.0F);
            level.addFreshEntity(snowball);
        }
    }

    public static class IceWandProjectile extends Snowball {
        private static final int PARTICLE_INTERVAL = 1;

        public IceWandProjectile(EntityType<? extends Snowball> pEntityType, Level level) {
            super(pEntityType, level);
        }

        public IceWandProjectile(Level level, LivingEntity pShooter) {
            super(level, pShooter);
        }

        public IceWandProjectile(Level level, double pX, double pY, double pZ) {
            super(level, pX, pY, pZ);
        }

        @Override
        public void tick() {
            super.tick();
            if (!this.level().isClientSide() && this.tickCount % PARTICLE_INTERVAL == 0) {
                spawnTrailParticle();
            }
        }

        private void spawnTrailParticle() {
            // Adjust particle spawn position as needed
            double posX = this.getX();
            double posY = this.getY();
            double posZ = this.getZ();

            // Spawn blue particle at the projectile's position
            ((ServerLevel) this.level()).sendParticles(ModParticles.ICE_DROP.get(), posX, posY, posZ, 2, 0.0D, 0.0D, 0.0D, 0.0D);
        }

        @Override
        protected void onHitEntity(EntityHitResult pResult) {
            super.onHitEntity(pResult);
            Entity entity = pResult.getEntity();
            if (entity instanceof LivingEntity livingEntity) {
                livingEntity.addEffect(new MobEffectInstance(ModMobEffects.FREEZING.get(), 200, 0, false, false, true));
            }
        }

        @Override
        protected void onHitBlock(BlockHitResult pResult) {
            BlockPos blockPos = pResult.getBlockPos();
            BlockState blockState = this.level().getBlockState(blockPos);

            if (blockState == Blocks.FIRE.defaultBlockState()) {
                this.level().setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3);
            }
            super.onHitBlock(pResult);
        }
    }
}
