package net.damqn4etobg.endlessexpansion.entity.custom;

import net.damqn4etobg.endlessexpansion.entity.AnimatedWaterMonster;
import net.damqn4etobg.endlessexpansion.entity.ai.goal.SwimTowardsPlayerGoal;
import net.damqn4etobg.endlessexpansion.entity.animations.ModAnimationDefinitions;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class AbyssalScourgeEntity extends AnimatedWaterMonster {
    public AbyssalScourgeEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected @NotNull AnimationDefinition getIdleAnimation() {
        return ModAnimationDefinitions.ABYSSAL_SCOURGE_IDLE;
    }

    @Override
    protected int getIdleAnimTimeoutTicks() {
        return 60;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 50D)
                .add(Attributes.FOLLOW_RANGE, 20D)
                .add(Attributes.MOVEMENT_SPEED, 10D)
                .add(Attributes.ARMOR_TOUGHNESS, 2.5f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 15f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(2, new SwimTowardsPlayerGoal(this, 15D, 50D, true));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
    }
}
