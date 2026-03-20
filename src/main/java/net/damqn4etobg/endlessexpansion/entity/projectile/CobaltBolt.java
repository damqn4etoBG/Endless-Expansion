package net.damqn4etobg.endlessexpansion.entity.projectile;

import net.damqn4etobg.endlessexpansion.entity.ModEntities;
import net.damqn4etobg.endlessexpansion.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CobaltBolt extends AbstractArrow {
    public CobaltBolt(EntityType<? extends CobaltBolt> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public CobaltBolt(Level pLevel, double pX, double pY, double pZ) {
        this(ModEntities.COBALT_BOLT.get(), pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    public CobaltBolt(Level pLevel, LivingEntity pShooter) {
        super(ModEntities.COBALT_BOLT.get(), pShooter, pLevel);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.COBALT_BOLT.get());
    }
}
