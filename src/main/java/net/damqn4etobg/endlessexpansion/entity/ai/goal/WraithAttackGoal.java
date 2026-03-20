package net.damqn4etobg.endlessexpansion.entity.ai.goal;

import net.damqn4etobg.endlessexpansion.entity.custom.WraithEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.level.LightLayer;

public class WraithAttackGoal extends MeleeAttackGoal {
    private final WraithEntity wraith;
    private double targetX;
    private double targetY;
    private double targetZ;

    public WraithAttackGoal(WraithEntity pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.wraith = pMob;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        double attackReach = this.getAttackReachSqr(pEnemy);

        if (pDistToEnemySqr <= attackReach && this.getTicksUntilNextAttack() <= 0) {
            this.resetAttackCooldown();
            this.mob.swing(InteractionHand.MAIN_HAND);
            this.mob.doHurtTarget(pEnemy);
            this.wraith.setAttackTicks(20); // <-- Ensure this always triggers!
        }
    }

    @Override
    public boolean canUse() {
        long dayTime = this.mob.level().getDayTime() % 24000;
        boolean isNight = dayTime >= 13000 && dayTime <= 23000;
//        this.targetX = wraith.getTarget().getX();
//        this.targetY = wraith.getTarget().getY();
//        this.targetZ = wraith.getTarget().getZ();
        BlockPos pos = new BlockPos((int) this.targetX, (int) this.targetY, (int) this.targetZ);
        if(!isNight) this.stop();
        return isNight && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        long dayTime = this.mob.level().getDayTime() % 24000;
        boolean isNight = dayTime >= 13000 && dayTime <= 23000;
        if(!isNight) this.stop();
        return isNight && super.canContinueToUse();
    }

    private boolean isDarkAt(BlockPos pos) {
        return this.mob.level().getBrightness(LightLayer.BLOCK, pos) <= 2 && this.mob.level().getBrightness(LightLayer.SKY, pos) <= 2;
    }
}