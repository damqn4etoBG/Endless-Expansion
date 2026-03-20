package net.damqn4etobg.endlessexpansion.entity.custom;

import net.damqn4etobg.endlessexpansion.entity.AnimatedMonster;
import net.damqn4etobg.endlessexpansion.entity.ai.goal.WraithAttackGoal;
import net.damqn4etobg.endlessexpansion.entity.animations.ModAnimationDefinitions;
import net.damqn4etobg.endlessexpansion.event.server.ModServerBossEvent;
import net.damqn4etobg.endlessexpansion.particle.ModParticles;
import net.damqn4etobg.endlessexpansion.sound.ModSounds;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class WraithEntity extends AnimatedMonster {
    public WraithEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public void tick() {
        super.tick();
        spawnParticles();
    }

    @Override
    protected @NotNull AnimationDefinition getIdleAnimation() {
        return ModAnimationDefinitions.WRAITH_IDLE;
    }

    @Override
    protected int getIdleAnimTimeoutTicks() {
        return 60;
    }

    private void spawnParticles() {
        double x = this.getX() + random.nextDouble() - 0.5;
        double y = this.getY() + random.nextDouble() + 0.5;
        double z = this.getZ() + random.nextDouble() - 0.5;
        if (random.nextFloat() < 0.0625f) {
            this.level().addParticle(ModParticles.SHADOW_ORB.get(), x, y, z, 0d, 0.025d, 0d);
        }
        if (random.nextFloat() < 0.0625f) {
            this.level().addParticle(ModParticles.SHADOW_STRIP.get(), x, y, z, 0d, 0.025d, 0d);
        }
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        ModServerBossEvent.addBoss(this.level(), this);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(1, new WraithAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        //this.goalSelector.addGoal(2, new WraithMoveTowardsTargetGoal(this, 1D, 32.0F));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 45D)
                .add(Attributes.FOLLOW_RANGE, 16D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ARMOR, 1.5f)
                .add(Attributes.ARMOR_TOUGHNESS, 2.5f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 10f);
    }

    @Override
    public boolean isNoGravity() {
        return false;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.WRAITH_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSounds.WRAITH_HURT.get();
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        boolean result = super.doHurtTarget(pEntity);
        if (result) {
            this.setAttackTicks(20);
        }
        return result;
    }
}
