package net.damqn4etobg.endlessexpansion.item.custom;

import net.damqn4etobg.endlessexpansion.screen.GuideScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Properties;

public class GuideItem extends Item {
    public GuideItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(pLevel.isClientSide()) {
            Minecraft.getInstance().setScreen(new GuideScreen());
        }
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }
}
