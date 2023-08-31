package net.damqn4etobg.endlessexpansion.item;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.fluid.ModFluids;
import net.damqn4etobg.endlessexpansion.item.custom.LuminiteStaffItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
