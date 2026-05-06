package net.damqn4etobg.endlessexpansion.item.custom;

import net.damqn4etobg.endlessexpansion.effect.EndlessMobEffects;
import net.damqn4etobg.endlessexpansion.item.EndlessItems;
import net.damqn4etobg.endlessexpansion.item.IArmorSetBonus;
import net.damqn4etobg.endlessexpansion.util.EndlessKeyBinds;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ShadowsteelArmorItem extends NameStyledItem implements IArmorSetBonus {
    public ShadowsteelArmorItem(Properties properties) {
        super(null, properties);
    }

    @Override
    public List<Item> armorPieces() {
        return List.of(EndlessItems.SHADOWSTEEL_HOOD.get(), EndlessItems.SHADOWSTEEL_CLOAK.get(), EndlessItems.SHADOWSTEEL_PANTS.get(), EndlessItems.SHADOWSTEEL_BOOTS.get());
    }

    @Override
    public List<Component> setBonusTooltip() {
        return List.of(
                ARMOR_SET_BONUS_TOOLTIP,
                Component.translatable("tooltip.endlessexpansion.shadowsteel_bonus1"),
                Component.empty(),
                Component.translatable("tooltip.endlessexpansion.shadowsteel_bonus2", EndlessKeyBinds.KEY_DASH.getKey().getDisplayName().getString())
        );
    }

    @Override
    public void applySetBonus(Player player, ItemStack setPiece) {
        addStatusEffect(player, EndlessMobEffects.SHADOW_STATE.get(), 100, 0, false, false, true);
    }
}
