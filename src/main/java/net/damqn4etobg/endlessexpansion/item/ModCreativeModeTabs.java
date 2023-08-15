package net.damqn4etobg.endlessexpansion.item;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EndlessExpansion.MODID);

    public static final RegistryObject<CreativeModeTab> ENDLESSEXPANSION_TAB = CREATIVE_MODE_TABS.register("endless_expansion",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.URANIUM_INGOT.get()))
                    .title(Component.translatable("creativemodetab.endless_expansion"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.URANIUM_INGOT.get());
                        pOutput.accept(ModBlocks.URANIUM_BLOCK.get());
                        pOutput.accept(ModBlocks.RADIOACTIVE_GENERATOR.get());
                    })
                    .build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
