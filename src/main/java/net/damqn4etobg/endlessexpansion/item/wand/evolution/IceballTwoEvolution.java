package net.damqn4etobg.endlessexpansion.item.wand.evolution;

import net.damqn4etobg.endlessexpansion.particle.ModParticles;
import net.damqn4etobg.endlessexpansion.util.EndlessUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import java.util.List;

public class IceballTwoEvolution extends WandEvolution{
    public IceballTwoEvolution(String id) {
        super(id);
    }

    @Override
    public void use(Level level, Player player, ItemStack stack) {
        ItemStack projectileStack = Blocks.ICE.asItem().getDefaultInstance();
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOW_HIT, SoundSource.NEUTRAL, 1.0F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!level.isClientSide) {
            Snowball snowball = new IceballTwoProjectile(level, player);
            snowball.setItem(projectileStack);
            snowball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2F, 1.0F);
            level.addFreshEntity(snowball);
        }
    }

    public static class IceballTwoProjectile extends IceballEvolution.IceWandProjectile {
        public IceballTwoProjectile(Level level, LivingEntity pShooter) {
            super(level, pShooter);
        }

        @Override
        protected void onHitEntity(EntityHitResult pResult) {

            Entity entity = pResult.getEntity();
            List<Entity> list = entity.level().getEntities(entity, this.getBoundingBox().inflate(2D));
            for(Entity entity1 : list) {

            }
            System.out.println(getZ());
            super.onHitEntity(pResult);
        }

        @Override
        protected void onHitBlock(BlockHitResult pResult) {
            EndlessUtils.addParticle(level(), ModParticles.EXPLODE_SPARK.get(), 1, getX(), getY(), getZ(), 0, 0, 0, 0);
            super.onHitBlock(pResult);
        }
    }
}
