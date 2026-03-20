package net.damqn4etobg.item;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EndlessItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EndlessExpansion.MODID);

    public static final DeferredItem<Item> LUMINITE = ITEMS.registerSimpleItem("luminite");

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
