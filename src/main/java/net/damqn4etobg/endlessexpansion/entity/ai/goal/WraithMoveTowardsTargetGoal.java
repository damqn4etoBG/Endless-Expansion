package net.damqn4etobg.endlessexpansion.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class WraithMoveTowardsTargetGoal extends Goal {
    private final PathfinderMob mob;
    @Nullable
    private LivingEntity target;
    private double wantedX;
    private double wantedY;
    private double wantedZ;
    private final double speedModifier;
    private final float within;

    public WraithMoveTowardsTargetGoal(PathfinderMob pMob, double pSpeedModifier, float pWithin) {
        this.mob = pMob;
        this.speedModifier = pSpeedModifier;
        this.within = pWithin;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        this.target = this.mob.getTarget();
        if (this.target == null) {
            return false;
        } else if (this.target.distanceToSqr(this.mob) > (double)(this.within * this.within)) {
            return false;
        } else {
            Vec3 $$0 = DefaultRandomPos.getPosTowards(this.mob, 16, 7, this.target.position(), 1.5707963705062866);
            if ($$0 == null) {
                return false;
            } else {
                this.wantedX = $$0.x;
                this.wantedY = $$0.y;
                this.wantedZ = $$0.z;
                BlockPos pos = new BlockPos((int) this.wantedX, (int) this.wantedY, (int) this.wantedZ);
                return isDarkAt(pos);
            }
        }
    }

    @Override
    public boolean canContinueToUse() {
        long dayTime = this.mob.level().getDayTime() % 24000;
        boolean isNight = dayTime >= 13000 && dayTime <= 23000;

        return isNight && !this.mob.getNavigation().isDone() && this.target.isAlive() && this.target.distanceToSqr(this.mob) < (double)(this.within * this.within);
    }

    @Override
    public void stop() {
        this.target = null;
    }

    @Override
    public void start() {
        this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
    }

    private boolean isDarkAt(BlockPos pos) {
        return this.mob.level().getBrightness(LightLayer.BLOCK, pos) <= 2 && this.mob.level().getBrightness(LightLayer.SKY, pos) <= 2;
    }
}
