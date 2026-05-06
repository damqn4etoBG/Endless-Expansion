package net.damqn4etobg.endlessexpansion.item;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

import static net.damqn4etobg.endlessexpansion.block.EndlessBlocks.*;
import static net.damqn4etobg.endlessexpansion.item.EndlessItems.*;

public class EndlessCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EndlessExpansion.MODID);

    public static final Supplier<CreativeModeTab> ENDLESS_EXPANSION_TAB = TABS.register("endless_expansion", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ARBOR_STAIRS.asItem()))
            .title(Component.translatable("creative_mode_tab.endless_expansion"))
            .displayItems((params, output) -> add(output, List.of(
                    LUMINITE_POWDER,
                    LUMINITE_INGOT,
                    LUMINITE_SWORD,
                    LUMINITE_PICKAXE,
                    LUMINITE_AXE,
                    LUMINITE_SHOVEL,
                    LUMINITE_HOE,
                    ARBOR_STICK,
                    ARBOR_LOG,
                    ARBOR_WOOD,
                    STRIPPED_ARBOR_LOG,
                    STRIPPED_ARBOR_WOOD,
                    ARBOR_PLANKS,
                    ARBOR_LEAVES,
                    ARBOR_STAIRS,
                    ARBOR_SLAB,
                    ARBOR_BUTTON,
                    ARBOR_PRESSURE_PLATE,
                    ARBOR_FENCE,
                    ARBOR_FENCE_GATE,
                    ARBOR_DOOR,
                    ARBOR_TRAPDOOR,
                    RAW_COBALT,
                    COBALT_INGOT,
                    COBALT_SWORD,
                    COBALT_PICKAXE,
                    COBALT_AXE,
                    COBALT_SHOVEL,
                    COBALT_HOE,
                    COBALT_PAXEL,
                    PYRONIUM,
                    FLAMMATINE_INGOT,
                    FLAMMATINE_SWORD,
                    FLAMMATINE_PICKAXE,
                    FLAMMATINE_AXE,
                    FLAMMATINE_SHOVEL,
                    FLAMMATINE_HOE,
                    SHADOW_ESSENCE,
                    SHADOWSTEEL_INGOT,
                    SHADOWSTEEL_HOOD,
                    SHADOWSTEEL_CLOAK,
                    SHADOWSTEEL_PANTS,
                    SHADOWSTEEL_BOOTS,
                    SHADOWSTEEL_SWORD,
                    SHADOWSTEEL_PICKAXE,
                    SHADOWSTEEL_AXE,
                    SHADOWSTEEL_SHOVEL,
                    SHADOWSTEEL_HOE,
                    MYSTICAL_EVERBLUE_POWDER
            ))).build());

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }

    private static void add(CreativeModeTab.Output output, List<ItemLike> items) {
        items.forEach(output::accept);
    }
}
