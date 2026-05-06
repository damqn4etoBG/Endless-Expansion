package net.damqn4etobg.endlessexpansion.item;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EndlessItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EndlessExpansion.MODID);

    public static final DeferredItem<Item> ARBOR_STICK = ITEMS.registerSimpleItem("arbor_stick");
    public static final DeferredItem<Item> LUMINITE_POWDER = ITEMS.registerSimpleItem("luminite_powder");
    public static final DeferredItem<Item> LUMINITE_INGOT = ITEMS.registerSimpleItem("luminite_ingot");
    public static final DeferredItem<Item> PYRONIUM = ITEMS.registerSimpleItem("pyronium");
    public static final DeferredItem<NameStyledItem> FLAMMATINE_INGOT = ITEMS.registerItem("flammatine_ingot", props -> new NameStyledItem(null, props));
    public static final DeferredItem<NameStyledItem> SHADOW_ESSENCE = ITEMS.registerItem("shadow_essence", props -> new NameStyledItem(null, props));
    public static final DeferredItem<NameStyledItem> SHADOWSTEEL_INGOT = ITEMS.registerItem("shadowsteel_ingot", props -> new NameStyledItem(null, props));
    public static final DeferredItem<Item> LUMINITE_SWORD = ITEMS.registerItem("luminite_sword", props -> new Item(props.sword(EndlessToolMaterials.LUMINITE, 3f, -2.4f)));
    public static final DeferredItem<Item> LUMINITE_PICKAXE = ITEMS.registerItem("luminite_pickaxe", props -> new Item(props.pickaxe(EndlessToolMaterials.LUMINITE, 1.5f, -2.8f)));
    public static final DeferredItem<StyledAxeItem> LUMINITE_AXE = ITEMS.registerItem("luminite_axe", props -> new StyledAxeItem(EndlessToolMaterials.LUMINITE, 5f, -3.1f, props));
    public static final DeferredItem<StyledShovelItem> LUMINITE_SHOVEL = ITEMS.registerItem("luminite_shovel", props -> new StyledShovelItem(EndlessToolMaterials.LUMINITE, 1.0f, -3f, props));
    public static final DeferredItem<StyledHoeItem> LUMINITE_HOE = ITEMS.registerItem("luminite_hoe", props -> new StyledHoeItem(EndlessToolMaterials.LUMINITE, -2.0f, -1f, props));
    public static final DeferredItem<Item> RAW_COBALT = ITEMS.registerSimpleItem("raw_cobalt");
    public static final DeferredItem<Item> COBALT_INGOT = ITEMS.registerSimpleItem("cobalt_ingot");
    public static final DeferredItem<Item> COBALT_SWORD = ITEMS.registerItem("cobalt_sword", props -> new Item(props.sword(EndlessToolMaterials.COBALT, 3f, -2.4f)));
    public static final DeferredItem<Item> COBALT_PICKAXE = ITEMS.registerItem("cobalt_pickaxe", props -> new Item(props.pickaxe(EndlessToolMaterials.COBALT, 1.5f, -2.8f)));
    public static final DeferredItem<StyledAxeItem> COBALT_AXE = ITEMS.registerItem("cobalt_axe", props -> new StyledAxeItem(EndlessToolMaterials.COBALT, 6f, -3.1f, props));
    public static final DeferredItem<StyledShovelItem> COBALT_SHOVEL = ITEMS.registerItem("cobalt_shovel", props -> new StyledShovelItem(EndlessToolMaterials.COBALT, 1.0f, -3f, props));
    public static final DeferredItem<StyledHoeItem> COBALT_HOE = ITEMS.registerItem("cobalt_hoe", props -> new StyledHoeItem(EndlessToolMaterials.COBALT, -2.0f, -1f, props));
    public static final DeferredItem<PaxelItem> COBALT_PAXEL = ITEMS.registerItem("cobalt_paxel", props -> new PaxelItem(EndlessToolMaterials.COBALT, 6f, -3.1f, props));
    public static final DeferredItem<NameStyledItem> FLAMMATINE_SWORD = ITEMS.registerItem("flammatine_sword", props -> new NameStyledItem(null, props.sword(EndlessToolMaterials.FLAMMATINE, 3f, -2.4f)));
    public static final DeferredItem<NameStyledItem> FLAMMATINE_PICKAXE = ITEMS.registerItem("flammatine_pickaxe", props -> new NameStyledItem(null, props.pickaxe(EndlessToolMaterials.FLAMMATINE, 1.5f, -2.8f)));
    public static final DeferredItem<StyledAxeItem> FLAMMATINE_AXE = ITEMS.registerItem("flammatine_axe", props -> new StyledAxeItem(EndlessToolMaterials.FLAMMATINE, 6f, -3.1f, props));
    public static final DeferredItem<StyledShovelItem> FLAMMATINE_SHOVEL = ITEMS.registerItem("flammatine_shovel", props -> new StyledShovelItem(EndlessToolMaterials.FLAMMATINE, 1.0f, -3f, props));
    public static final DeferredItem<StyledHoeItem> FLAMMATINE_HOE = ITEMS.registerItem("flammatine_hoe", props -> new StyledHoeItem(EndlessToolMaterials.FLAMMATINE, -2.0f, -1f, props));
//    public static final DeferredItem<PaxelItem> FLAMMATINE_PAXEL = ITEMS.registerItem("flammatine_paxel", props -> new PaxelItem(EndlessToolMaterials.FLAMMATINE, 6f, -3.1f, props));
    public static final DeferredItem<NameStyledItem> SHADOWSTEEL_SWORD = ITEMS.registerItem("shadowsteel_sword", props -> new NameStyledItem(null, props.sword(EndlessToolMaterials.SHADOWSTEEL, 3.25f, -2.4f)));
    public static final DeferredItem<NameStyledItem> SHADOWSTEEL_PICKAXE = ITEMS.registerItem("shadowsteel_pickaxe", props -> new NameStyledItem(null, props.pickaxe(EndlessToolMaterials.SHADOWSTEEL, 1.5f, -2.8f)));
    public static final DeferredItem<StyledAxeItem> SHADOWSTEEL_AXE = ITEMS.registerItem("shadowsteel_axe", props -> new StyledAxeItem(EndlessToolMaterials.SHADOWSTEEL, 6f, -3.1f, props));
    public static final DeferredItem<StyledShovelItem> SHADOWSTEEL_SHOVEL = ITEMS.registerItem("shadowsteel_shovel", props -> new StyledShovelItem(EndlessToolMaterials.SHADOWSTEEL, 1.0f, -3f, props));
    public static final DeferredItem<StyledHoeItem> SHADOWSTEEL_HOE = ITEMS.registerItem("shadowsteel_hoe", props -> new StyledHoeItem(EndlessToolMaterials.SHADOWSTEEL, -2.0f, -1f, props));
//    public static final DeferredItem<PaxelItem> SHADOWSTEEL_PAXEL = ITEMS.registerItem("shadowsteel_paxel", props -> new PaxelItem(EndlessToolMaterials.SHADOWSTEEL, 6f, -3.1f, props));
    public static final DeferredItem<Item> MYSTICAL_EVERBLUE_POWDER = ITEMS.registerSimpleItem("mystical_everblue_powder");
    public static final DeferredItem<ShadowsteelArmorItem> SHADOWSTEEL_HOOD = ITEMS.registerItem("shadowsteel_hood", ShadowsteelArmorItem::new, () -> new Item.Properties().humanoidArmor(EndlessArmorMaterials.SHADOWSTEEL, ArmorType.HELMET));
    public static final DeferredItem<ShadowsteelArmorItem> SHADOWSTEEL_CLOAK = ITEMS.registerItem("shadowsteel_cloak", ShadowsteelArmorItem::new, () -> new Item.Properties().humanoidArmor(EndlessArmorMaterials.SHADOWSTEEL, ArmorType.CHESTPLATE));
    public static final DeferredItem<ShadowsteelArmorItem> SHADOWSTEEL_PANTS = ITEMS.registerItem("shadowsteel_pants", ShadowsteelArmorItem::new, () -> new Item.Properties().humanoidArmor(EndlessArmorMaterials.SHADOWSTEEL, ArmorType.LEGGINGS));
    public static final DeferredItem<ShadowsteelArmorItem> SHADOWSTEEL_BOOTS = ITEMS.registerItem("shadowsteel_boots", ShadowsteelArmorItem::new, () -> new Item.Properties().humanoidArmor(EndlessArmorMaterials.SHADOWSTEEL, ArmorType.BOOTS));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
