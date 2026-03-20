package net.damqn4etobg.endlessexpansion.entity.projectile;

import net.damqn4etobg.endlessexpansion.entity.ModEntities;
import net.damqn4etobg.endlessexpansion.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MysticalCobaltBolt extends AbstractArrow {
    public MysticalCobaltBolt(EntityType<? extends MysticalCobaltBolt> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public MysticalCobaltBolt(Level pLevel, double pX, double pY, double pZ) {
        this(ModEntities.MYSTICAL_COBALT_BOLT.get(), pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    public MysticalCobaltBolt(Level pLevel, LivingEntity pShooter) {
        super(ModEntities.MYSTICAL_COBALT_BOLT.get(), pShooter, pLevel);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.MYSTICAL_COBALT_BOLT.get());
    }
}
