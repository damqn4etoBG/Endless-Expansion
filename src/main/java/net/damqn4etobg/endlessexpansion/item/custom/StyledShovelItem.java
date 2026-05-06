package net.damqn4etobg.endlessexpansion.item.custom;

import net.damqn4etobg.endlessexpansion.util.EndlessStyles;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;

public class StyledShovelItem extends ShovelItem {
    public StyledShovelItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
        super(material, attackDamage, attackSpeed, properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        return EndlessStyles.getAnnotated(Component.translatable(descriptionId));
    }
}
