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
                        pOutput.accept(ModBlocks.ARBOR_LEAVES.get());
                        pOutput.accept(ModBlocks.ARBOR_SAPLING.get());
                        pOutput.accept(ModBlocks.ARBOR_LOG.get());
                        pOutput.accept(ModBlocks.ARBOR_WOOD.get());
                        pOutput.accept(ModBlocks.STRIPPED_ARBOR_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_ARBOR_WOOD.get());
                        pOutput.accept(ModBlocks.ARBOR_PLANKS.get());
                        pOutput.accept(ModBlocks.ARBOR_STAIRS.get());
                        pOutput.accept(ModBlocks.ARBOR_SLAB.get());
                        pOutput.accept(ModBlocks.ARBOR_FENCE.get());
                        pOutput.accept(ModBlocks.ARBOR_FENCE_GATE.get());
                        pOutput.accept(ModBlocks.ARBOR_PRESSURE_PLATE.get());
                        pOutput.accept(ModBlocks.ARBOR_BUTTON.get());
                        pOutput.accept(ModItems.ARBOR_STICK.get());
                        pOutput.accept(ModBlocks.TITANUM_GRASS_BLOCK.get());
                        pOutput.accept(ModBlocks.TITANUM_SOIL.get());
                        pOutput.accept(ModBlocks.GLACIER_BRICKS.get());
                        pOutput.accept(ModItems.LUMINITE_STAFF.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_LUMINITE_ORE.get());
                        pOutput.accept(ModItems.LUMINITE.get());
                        pOutput.accept(ModBlocks.MYSTICAL_EVERBLUE_OCRHID.get());
                        pOutput.accept(ModItems.MYSTICAL_EVERBLUE_POWDER.get());
                        pOutput.accept(ModBlocks.PACKED_SNOW_BLOCK.get());
                        pOutput.accept(ModBlocks.COBALT_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_COBALT_ORE.get());
                        pOutput.accept(ModItems.RAW_COBALT.get());
                        pOutput.accept(ModItems.COBALT_INGOT.get());
                        pOutput.accept(ModBlocks.COBALT_BLOCK.get());
                        pOutput.accept(ModItems.CELESTIAL_INGOT.get());
                        pOutput.accept(ModBlocks.CELESTIAL_BLOCK.get());
                        pOutput.accept(ModItems.COBALT_SWORD.get());
                        pOutput.accept(ModItems.COBALT_PICKAXE.get());
                        pOutput.accept(ModItems.COBALT_SHOVEL.get());
                        pOutput.accept(ModItems.COBALT_AXE.get());
                        pOutput.accept(ModItems.COBALT_HOE.get());
                        pOutput.accept(ModItems.CELESTIAL_SWORD.get());
                        pOutput.accept(ModItems.CELESTIAL_PICKAXE.get());
                        pOutput.accept(ModItems.CELESTIAL_SHOVEL.get());
                        pOutput.accept(ModItems.CELESTIALT_AXE.get());
                        pOutput.accept(ModItems.CELESTIAL_HOE.get());
                        pOutput.accept(ModItems.COBALT_HELMET.get());
                        pOutput.accept(ModItems.COBALT_CHESTPLATE.get());
                        pOutput.accept(ModItems.COBALT_LEGGINGS.get());
                        pOutput.accept(ModItems.COBALT_BOOTS.get());
                        pOutput.accept(ModItems.CELESTIAL_HELMET.get());
                        pOutput.accept(ModItems.CELESTIAL_CHESTPLATE.get());
                        pOutput.accept(ModItems.CELESTIAL_LEGGINGS.get());
                        pOutput.accept(ModItems.CELESTIAL_BOOTS.get());
                    })
                    .build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
