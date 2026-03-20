package net.damqn4etobg.endlessexpansion.entity;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public abstract class AnimatedMonster extends Monster {
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();
    public static final EntityDataAccessor<Integer> ATTACK_TICKS = SynchedEntityData.defineId(AnimatedMonster.class, EntityDataSerializers.INT);
    private int idleAnimationTimeout;

    protected AnimatedMonster(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            setupAttackTicks();
        } else {
            setupAnimationStates(); // CLIENT SIDE
        }
    }

    public void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.getIdleAnimationDuration(getIdleAnimTimeoutTicks());
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        setupAttackAnimation(this.attackAnimationState, this.getAttackTicks(), this.tickCount);
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }

    public void setupAttackTicks() {
        int attackTicks = getAttackTicks();
        if (attackTicks > 0) {
            setAttackTicks(attackTicks - 1);
        }
    }

    private void setupAttackAnimation(AnimationState attackAnimationState, int attackTicks, int tickCount) {
        if (attackTicks > 0 && !attackAnimationState.isStarted()) {
            attackAnimationState.start(tickCount);
        } else if (attackTicks <= 0 && attackAnimationState.isStarted()) {
            attackAnimationState.stop();
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACK_TICKS, 0);
    }

    public void setAttackTicks(int ticks) {
        this.entityData.set(ATTACK_TICKS, ticks);
    }

    public int getAttackTicks() {
        return this.entityData.get(ATTACK_TICKS);
    }

    protected abstract @NotNull AnimationDefinition getIdleAnimation();
    protected abstract int getIdleAnimTimeoutTicks();

    public int getIdleAnimationDuration(int timeoutTicks) {
        return ((int) this.getIdleAnimation().lengthInSeconds() * 20) + this.random.nextInt(timeoutTicks);
    }
}