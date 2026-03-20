package net.damqn4etobg.endlessexpansion.item.wand;

import net.damqn4etobg.endlessexpansion.item.wand.evolution.WandEvolution;
import net.damqn4etobg.endlessexpansion.item.wand.evolution.WandEvolutions;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Properties;

public class WandItem extends Item {
    public WandItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        if (pStack.hasTag() && pStack.getTag().contains("evolution")) {
            pTooltipComponents.add(Component.translatable("tooltip.endlessexpansion.wand.selected_evolution"));
            pTooltipComponents.add(Component.translatable("wand_evolution.endlessexpansion."
                    + pStack.getTag().getString("evolution").replace("endlessexpansion:", "") + ".name"));
        }
    }

    public WandEvolution getEvolution(ItemStack stack) {
        if(stack.hasTag() && stack.getTag().contains("evolution")) {
            return WandEvolutions.getFromId(stack.getTag().getString("evolution"));
        }
        return getBaseEvolution();
    }

    public WandEvolution getBaseEvolution() {
        return WandEvolution.empty();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        WandEvolution evolution = getEvolution(stack);
        if(evolution != null) {
            if (stack.isDamageableItem() && stack.getDamageValue() < stack.getMaxDamage()) {
                evolution.use(pLevel, pPlayer, stack);
                pPlayer.getCooldowns().addCooldown(this, evolution.getCooldown());
                stack.hurtAndBreak(1, pPlayer, onBroken -> onBroken.broadcastBreakEvent(pUsedHand));
                return InteractionResultHolder.sidedSuccess(stack, pLevel.isClientSide());
            } else {
                return InteractionResultHolder.fail(stack);
            }
        }
        return InteractionResultHolder.pass(stack);
    }
}