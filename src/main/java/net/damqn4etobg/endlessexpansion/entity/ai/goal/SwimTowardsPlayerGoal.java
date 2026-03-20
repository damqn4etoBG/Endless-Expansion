package net.damqn4etobg.endlessexpansion.entity.ai.goal;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

public class SwimTowardsPlayerGoal extends Goal {
    private final PathfinderMob mob;
    private Player target;
    private final double speed;
    private final double followRadius;
    private final boolean waterOnly;

    public SwimTowardsPlayerGoal(PathfinderMob mob, double speed, double followRadius, boolean waterOnly) {
        this.mob = mob;
        this.speed = speed;
        this.followRadius = followRadius;
        this.waterOnly = waterOnly;
    }

    @Override
    public boolean canUse() {
        if (waterOnly && !mob.isInWater()) return false;

        this.target = mob.level().getNearestPlayer(mob, followRadius);
        return this.target != null;
    }

    @Override
    public void start() {
        if(target != null) {
            mob.getNavigation().moveTo(target, speed);
            System.out.println("staring " + target.getName());
        }
    }

    @Override
    public void tick() {
        if (target != null && mob.getNavigation().isDone()) {
            mob.getNavigation().moveTo(target, speed);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return target != null && target.isAlive() && mob.distanceTo(target) < followRadius && (!waterOnly || mob.isInWater());
    }

    @Override
    public void stop() {
        target = null;
        mob.getNavigation().stop();
    }
}
