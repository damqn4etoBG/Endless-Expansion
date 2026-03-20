package net.damqn4etobg.endlessexpansion.item.enchantment;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Properties;
import java.util.function.Supplier;

public class ModEnchantedBookItem extends EnchantedBookItem {
    private final Supplier<EnchantmentInstance> enchantmentSupplier;

    public ModEnchantedBookItem(Properties pProperties, Supplier<EnchantmentInstance> enchantment) {
        super(pProperties);
        this.enchantmentSupplier = enchantment;
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = new ItemStack(this);
        EnchantmentInstance instance = enchantmentSupplier.get();
        EnchantedBookItem.addEnchantment(stack, instance); // safe here!
        return stack;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        EnchantmentInstance instance = enchantmentSupplier.get(); // safe to call now
        tooltip.add(Component.translatable(instance.enchantment.getDescriptionId()).withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable(instance.enchantment.getDescriptionId() + "_desc").withStyle(ChatFormatting.DARK_GRAY));
    }
}
