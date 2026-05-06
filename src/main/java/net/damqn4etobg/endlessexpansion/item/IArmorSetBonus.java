package net.damqn4etobg.endlessexpansion.item;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface IArmorSetBonus {
    List<Item> armorPieces();
    List<Component> setBonusTooltip();
    Component ARMOR_SET_BONUS_TOOLTIP = Component.translatable("tooltip.endlessexpansion.armor_set_bonus");

    void applySetBonus(Player player, ItemStack setPiece);

    default void addStatusEffect(Player player, MobEffect effect, int duration, int amplifier, boolean ambient, boolean visible, boolean showIcon) {
        Holder<MobEffect> holder = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect);
        boolean hasEffect = player.hasEffect(holder);

        if (hasFullSet(player)) {
            if (!hasEffect) {
                player.addEffect(new MobEffectInstance(holder, duration, amplifier, ambient, visible, showIcon));
            } else {
                MobEffectInstance current = player.getEffect(holder);
                if (current != null && current.getDuration() <= 20) {
                    player.removeEffect(holder);
                    player.addEffect(new MobEffectInstance(holder, duration, amplifier, ambient, visible, showIcon));
                }
            }
        }
    }

    default boolean hasFullSet(Player player) {
        if (armorPieces().isEmpty() || armorPieces().size() < 4 || armorPieces().size() > 4) return false;

        ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feet = player.getItemBySlot(EquipmentSlot.FEET);

        return head.is(armorPieces().getFirst()) && chest.is(armorPieces().get(1)) && legs.is(armorPieces().get(2)) && feet.is(armorPieces().getLast());
    }
}
