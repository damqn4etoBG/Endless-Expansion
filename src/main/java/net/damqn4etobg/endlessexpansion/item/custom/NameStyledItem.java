package net.damqn4etobg.endlessexpansion.item.custom;

import net.damqn4etobg.endlessexpansion.util.EndlessStyles;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class NameStyledItem extends Item {
    private final Style style;

    public NameStyledItem(Style style, Properties properties) {
        super(properties);
        this.style = style;
    }

    @Override
    public Component getName(ItemStack stack) {
        if (style != null) return Component.translatable(descriptionId).withStyle(style);
        else return EndlessStyles.getAnnotated(Component.translatable(descriptionId));
    }
}
