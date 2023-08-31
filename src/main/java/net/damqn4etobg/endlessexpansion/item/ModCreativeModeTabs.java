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
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.ARBOR_SAPLING.get()))
                    .title(Component.translatable("creativemodetab.endless_expansion"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.URANIUM_INGOT.get());
                        pOutput.accept(ModBlocks.URANIUM_BLOCK.get());
                        pOutput.accept(ModBlocks.RADIOACTIVE_GENERATOR.get());
                        pOutput.accept(ModItems.NUCLEAR_WASTE_BUCKET.get());
                        pOutput.accept(ModBlocks.ARBOR_LEAVES.get());
                        pOutput.accept(ModBlocks.ARBOR_SAPLING.get());
                        pOutput.accept(ModBlocks.ARBOR_PLANKS.get());
                        pOutput.accept(ModBlocks.ARBOR_WOOD.get());
                        pOutput.accept(ModBlocks.ARBOR_LOG.get());
                        pOutput.accept(ModItems.ARBOR_STICK.get());
                        pOutput.accept(ModBlocks.STRIPPED_ARBOR_WOOD.get());
                        pOutput.accept(ModBlocks.STRIPPED_ARBOR_LOG.get());
                        pOutput.accept(ModBlocks.TITANUM_GRASS_BLOCK.get());
                        pOutput.accept(ModBlocks.TITANUM_SOIL.get());
                        pOutput.accept(ModBlocks.GLACIER_BRICKS.get());
                        pOutput.accept(ModItems.LUMINITE_STAFF.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_LUMINITE_ORE.get());
                        pOutput.accept(ModItems.LUMINITE.get());
                    })
                    .build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
