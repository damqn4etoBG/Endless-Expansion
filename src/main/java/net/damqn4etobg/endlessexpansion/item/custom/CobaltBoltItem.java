package net.damqn4etobg.endlessexpansion.item.custom;

import net.damqn4etobg.endlessexpansion.entity.projectile.CobaltBolt;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Properties;

public class CobaltBoltItem extends ArrowItem {
    public final float damage;

    public CobaltBoltItem(Properties pProperties, float damage) {
        super(pProperties);
        this.damage = damage;
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        CobaltBolt bolt = new CobaltBolt(pLevel, pShooter);
        bolt.setBaseDamage(this.damage);
        return bolt;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        return super.isInfinite(stack, bow, player);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        ItemStack crossbow = pPlayer.getMainHandItem();
        if(crossbow.getItem() instanceof CrossbowItem) {
            CompoundTag tag = crossbow.getOrCreateTag();
            tag.putBoolean("CobaltBolt", true);
        }
        return InteractionResult.SUCCESS;
    }
}
