package net.damqn4etobg.endlessexpansion.item.custom;

import net.damqn4etobg.endlessexpansion.util.EndlessUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Properties;

public class ShadowsteelArmorItem extends ModArmorItem {
    public ShadowsteelArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pIsAdvanced) {
        EndlessUtils.addAdditionalInfo(pTooltip, "tooltip.endlessexpansion.armor_set_bonus", "tooltip.endlessexpansion.shadowsteel_bonus");
    }
}