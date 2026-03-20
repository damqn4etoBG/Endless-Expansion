package net.damqn4etobg.item;

import net.damqn4etobg.block.EndlessBlocks;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EndlessCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EndlessExpansion.MODID);

    public static final Supplier<CreativeModeTab> ENDLESS_EXPANSION_TAB = TABS.register("endless_expansion", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(EndlessBlocks.ARBOR_STAIRS.asItem()))
            .title(Component.translatable("creative_mode_tab.endless_expansion"))
            .displayItems((params, output) -> {
                output.accept(EndlessBlocks.ARBOR_LOG.get());
                output.accept(EndlessBlocks.ARBOR_WOOD.get());
                output.accept(EndlessBlocks.STRIPPED_ARBOR_LOG.get());
                output.accept(EndlessBlocks.STRIPPED_ARBOR_WOOD.get());
                output.accept(EndlessBlocks.ARBOR_PLANKS.get());
                output.accept(EndlessBlocks.ARBOR_LEAVES.get());
                output.accept(EndlessBlocks.ARBOR_STAIRS.get());
                output.accept(EndlessBlocks.ARBOR_SLAB.get());
                output.accept(EndlessBlocks.ARBOR_BUTTON.get());
                output.accept(EndlessBlocks.ARBOR_PRESSURE_PLATE.get());
                output.accept(EndlessBlocks.ARBOR_FENCE.get());
                output.accept(EndlessBlocks.ARBOR_FENCE_GATE.get());
                output.accept(EndlessItems.LUMINITE.get());
            })
            .build());

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}
