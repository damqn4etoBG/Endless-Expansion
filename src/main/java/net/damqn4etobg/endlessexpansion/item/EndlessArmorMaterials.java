package net.damqn4etobg.endlessexpansion.item;

import com.google.common.collect.Maps;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.tag.EndlessTags;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.Map;

public class EndlessArmorMaterials {
    public static final ArmorMaterial FLAMMATINE = new ArmorMaterial(1872, makeDefense(2, 6, 8, 2, 10), 15, SoundEvents.ARMOR_EQUIP_CHAIN, 2.25f, 0.0f, EndlessTags.Items.FLAMMATINE_INGOT, createId("flammatine"));
    public static final ArmorMaterial SHADOWSTEEL = new ArmorMaterial(2257, makeDefense(3, 7, 9, 3, 11), 16, SoundEvents.ARMOR_EQUIP_LEATHER, 2.75f, 0.1f, EndlessTags.Items.SHADOWSTEEL_INGOT, createId("shadowsteel"));

    private static Map<ArmorType, Integer> makeDefense(int boots, int leggings, int chestplate, int helmet, int body) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, leggings, ArmorType.CHESTPLATE, chestplate, ArmorType.HELMET, helmet, ArmorType.BODY, body));
    }

    static ResourceKey<EquipmentAsset> createId(String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(EndlessExpansion.MODID, name));
    }
}
