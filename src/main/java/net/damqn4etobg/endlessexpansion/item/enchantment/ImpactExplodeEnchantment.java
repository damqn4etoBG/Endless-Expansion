package net.damqn4etobg.endlessexpansion.item.enchantment;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ImpactExplodeEnchantment extends Enchantment {
    protected ImpactExplodeEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public void doPostAttack(LivingEntity pUser, Entity pTarget, int pLevel) {
        if(!pUser.level().isClientSide()) {
            ServerLevel world = (ServerLevel) pUser.level();

            if(pUser instanceof Player player && player.getProjectile(player.getUseItem()).getItem() instanceof ArrowItem) {
                System.out.println("enchantment printed");
            }
        }
        super.doPostAttack(pUser, pTarget, pLevel);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isDiscoverable() {
        return true;
    }
}
