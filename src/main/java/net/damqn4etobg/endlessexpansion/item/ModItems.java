package net.damqn4etobg.endlessexpansion.item;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.fluid.ModFluids;
import net.damqn4etobg.endlessexpansion.item.custom.LuminiteStaffItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EndlessExpansion.MODID);

    public static final RegistryObject<Item> URANIUM_INGOT = ITEMS.register("uranium_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUCLEAR_WASTE_BUCKET = ITEMS.register("nuclear_waste_bucket",
            () -> new BucketItem(ModFluids.SOURCE_NUCLEAR_WASTE,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> LUMINITE_STAFF = ITEMS.register("luminite_staff",
            LuminiteStaffItem::new);

    public static final RegistryObject<Item> LUMINITE = ITEMS.register("luminite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ARBOR_STICK = ITEMS.register("arbor_stick",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_COBALT = ITEMS.register("raw_cobalt",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COBALT_INGOT = ITEMS.register("cobalt_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CELESTIAL_INGOT = ITEMS.register("celestial_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COBALT_SWORD = ITEMS.register("cobalt_sword",
            () -> new SwordItem(ModToolTiers.COBALT, 3, -2.4f, new Item.Properties()));

    public static final RegistryObject<Item> COBALT_PICKAXE = ITEMS.register("cobalt_pickaxe",
            () -> new PickaxeItem(ModToolTiers.COBALT, 1, -2.8f, new Item.Properties()));

    public static final RegistryObject<Item> COBALT_SHOVEL = ITEMS.register("cobalt_shovel",
            () -> new ShovelItem(ModToolTiers.COBALT, 1, -3f, new Item.Properties()));

    public static final RegistryObject<Item> COBALT_AXE = ITEMS.register("cobalt_axe",
            () -> new AxeItem(ModToolTiers.COBALT, 6, -3.0f, new Item.Properties()));

    public static final RegistryObject<Item> COBALT_HOE = ITEMS.register("cobalt_hoe",
            () -> new HoeItem(ModToolTiers.COBALT, -5, 0, new Item.Properties()));

    public static final RegistryObject<Item> CELESTIAL_SWORD = ITEMS.register("celestial_sword",
            () -> new SwordItem(ModToolTiers.CELESTIAL, 5, -2f, new Item.Properties()));

    public static final RegistryObject<Item> CELESTIAL_PICKAXE = ITEMS.register("celestial_pickaxe",
            () -> new PickaxeItem(ModToolTiers.CELESTIAL, 1, -2.8f, new Item.Properties()));

    public static final RegistryObject<Item> CELESTIAL_SHOVEL = ITEMS.register("celestial_shovel",
            () -> new ShovelItem(ModToolTiers.CELESTIAL, 1, -2.7f, new Item.Properties()));

    public static final RegistryObject<Item> CELESTIALT_AXE = ITEMS.register("celestial_axe",
            () -> new AxeItem(ModToolTiers.CELESTIAL, 8, -3.0f, new Item.Properties()));

    public static final RegistryObject<Item> CELESTIAL_HOE = ITEMS.register("celestial_hoe",
            () -> new HoeItem(ModToolTiers.CELESTIAL, -5, -2f, new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
