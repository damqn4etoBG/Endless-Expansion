package net.damqn4etobg.endlessexpansion.item;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.block.ModBlocks;
import net.damqn4etobg.endlessexpansion.item.enchantment.ModEnchantments;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EndlessExpansion.MODID);

    public static final RegistryObject<CreativeModeTab> ENDLESS_EXPANSION_TAB = CREATIVE_MODE_TABS.register("endless_expansion",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.ARBOR_SAPLING.get()))
                    .withBackgroundLocation(ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/gui/creative_mode_tab.png"))
                    .withSearchBar(60)
                    .title(Component.translatable("creative_mode_tab.endless_expansion"))
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
                        pOutput.accept(ModBlocks.ARBOR_DOOR.get());
                        pOutput.accept(ModBlocks.ARBOR_TRAPDOOR.get());
                        pOutput.accept(ModItems.ARBOR_SIGN.get());
                        pOutput.accept(ModItems.ARBOR_HANGING_SIGN.get());
                        pOutput.accept(ModItems.ARBOR_BOAT.get());
                        pOutput.accept(ModItems.ARBOR_CHEST_BOAT.get());
                        pOutput.accept(ModBlocks.BLISSWOOD_LEAVES.get());
                        pOutput.accept(ModBlocks.BLISSWOOD_SAPLING.get());
                        pOutput.accept(ModBlocks.BLISSWOOD_LOG.get());
                        pOutput.accept(ModBlocks.BLISSWOOD_WOOD.get());
                        pOutput.accept(ModBlocks.STRIPPED_BLISSWOOD_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_BLISSWOOD_WOOD.get());
                        pOutput.accept(ModBlocks.BLISSWOOD_PLANKS.get());
                        pOutput.accept(ModBlocks.BLISSWOOD_STAIRS.get());
                        pOutput.accept(ModBlocks.BLISSWOOD_SLAB.get());
                        pOutput.accept(ModBlocks.BLISSWOOD_FENCE.get());
                        pOutput.accept(ModBlocks.BLISSWOOD_FENCE_GATE.get());
                        pOutput.accept(ModBlocks.BLISSWOOD_PRESSURE_PLATE.get());
                        pOutput.accept(ModBlocks.BLISSWOOD_BUTTON.get());
                        pOutput.accept(ModBlocks.BLISSWOOD_TRAPDOOR.get());
                        pOutput.accept(ModBlocks.BLISSWOOD_DOOR.get());
                        pOutput.accept(ModItems.BLISSWOOD_SIGN.get());
                        pOutput.accept(ModItems.BLISSWOOD_HANGING_SIGN.get());
                        pOutput.accept(ModItems.BLISSWOOD_BOAT.get());
                        pOutput.accept(ModItems.BLISSWOOD_CHEST_BOAT.get());
                        pOutput.accept(ModItems.ARBOR_STICK.get());
                        pOutput.accept(ModItems.BLISSWOOD_STICK.get());
                        pOutput.accept(ModBlocks.TITANUM_GRASS_BLOCK.get());
                        pOutput.accept(ModBlocks.TITANUM_SOIL.get());
                        pOutput.accept(ModBlocks.GLACIER_BRICKS.get());
                        pOutput.accept(ModBlocks.PACKED_SNOW_BLOCK.get());
                        pOutput.accept(ModBlocks.ABYSSAL_SILT.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_LUMINITE_ORE.get());
                        pOutput.accept(ModBlocks.COBALT_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_COBALT_ORE.get());
                        pOutput.accept(ModBlocks.PYRONIUM_ORE.get());
                        pOutput.accept(ModBlocks.BLACKSTONE_PYRONIUM_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_BLACK_OPAL_ORE.get());
                        pOutput.accept(ModBlocks.INFUSER.get());
                        pOutput.accept(ModBlocks.EVOLUTION_TABLE.get());
                        pOutput.accept(ModBlocks.SMALL_RED_MUSHROOM.get());
                        pOutput.accept(ModBlocks.SMALL_BROWN_MUSHROOM.get());
                        pOutput.accept(ModItems.GLACIAL_CRYSTAL.get());
                        pOutput.accept(ModItems.LUMINITE.get());
                        pOutput.accept(ModBlocks.LUMINITE_BLOCK.get());
                        pOutput.accept(ModItems.LUMINITE_ESSENCE_BUCKET.get());
                        pOutput.accept(ModItems.LUMINITE_STAFF.get());
                        pOutput.accept(ModItems.RAW_COBALT.get());
                        pOutput.accept(ModItems.COBALT_INGOT.get());
                        pOutput.accept(ModBlocks.COBALT_BLOCK.get());
                        pOutput.accept(ModItems.COBALT_SWORD.get());
                        pOutput.accept(ModItems.COBALT_PICKAXE.get());
                        pOutput.accept(ModItems.COBALT_SHOVEL.get());
                        pOutput.accept(ModItems.COBALT_AXE.get());
                        pOutput.accept(ModItems.COBALT_HOE.get());
                        pOutput.accept(ModItems.COBALT_PAXEL.get());
                        pOutput.accept(ModItems.COBALT_HELMET.get());
                        pOutput.accept(ModItems.COBALT_CHESTPLATE.get());
                        pOutput.accept(ModItems.COBALT_LEGGINGS.get());
                        pOutput.accept(ModItems.COBALT_BOOTS.get());
                        pOutput.accept(ModItems.COBALT_ROD.get());
                        pOutput.accept(ModItems.COBALT_BOLT.get());
                        pOutput.accept(ModItems.MYSTICAL_COBALT_BOLT.get());
                        pOutput.accept(ModItems.CELESTIAL_INGOT.get());
                        pOutput.accept(ModBlocks.CELESTIAL_BLOCK.get());
                        pOutput.accept(ModItems.CELESTIAL_SWORD.get());
                        pOutput.accept(ModItems.CELESTIAL_PICKAXE.get());
                        pOutput.accept(ModItems.CELESTIAL_SHOVEL.get());
                        pOutput.accept(ModItems.CELESTIAL_AXE.get());
                        pOutput.accept(ModItems.CELESTIAL_HOE.get());
                        pOutput.accept(ModItems.CELESTIAL_PAXEL.get());
                        pOutput.accept(ModItems.CELESTIAL_HELMET.get());
                        pOutput.accept(ModItems.CELESTIAL_CHESTPLATE.get());
                        pOutput.accept(ModItems.CELESTIAL_LEGGINGS.get());
                        pOutput.accept(ModItems.CELESTIAL_BOOTS.get());
                        pOutput.accept(ModItems.PYRONIUM.get());
                        pOutput.accept(ModItems.PYRONIUM_INFUSED_COAL.get());
                        pOutput.accept(ModItems.FIRE_CORE.get());
                        pOutput.accept(ModItems.BLACK_OPAL.get());
                        pOutput.accept(ModItems.SHADOW_ESSENCE.get());
                        pOutput.accept(ModItems.SHADOWSTEEL_INGOT.get());
                        pOutput.accept(ModItems.SHADOWSTEEL_SWORD.get());
                        pOutput.accept(ModItems.SHADOWSTEEL_PICKAXE.get());
                        pOutput.accept(ModItems.SHADOWSTEEL_SHOVEL.get());
                        pOutput.accept(ModItems.SHADOWSTEEL_AXE.get());
                        pOutput.accept(ModItems.SHADOWSTEEL_HOE.get());
                        pOutput.accept(ModItems.SHADOWSTEEL_PAXEL.get());
                        pOutput.accept(ModItems.SHADOWSTEEL_HELMET.get());
                        pOutput.accept(ModItems.SHADOWSTEEL_CHESTPLATE.get());
                        pOutput.accept(ModItems.SHADOWSTEEL_LEGGINGS.get());
                        pOutput.accept(ModItems.SHADOWSTEEL_BOOTS.get());
                        pOutput.accept(ModBlocks.SAPPHIRE_CLUSTER.get());
                        pOutput.accept(ModItems.RAW_SAPPHIRE.get());
                        pOutput.accept(ModItems.REFINED_SAPPHIRE.get());
                        pOutput.accept(ModItems.SAPPHIRE_DIVING_HELMET.get());
                        pOutput.accept(ModItems.GLASS_VIAL.get());
                        pOutput.accept(ModBlocks.MYSTICAL_EVERBLUE_OCRHID.get());
                        pOutput.accept(ModItems.MYSTICAL_EVERBLUE_POWDER.get());
                        pOutput.accept(ModItems.MYSTICAL_COOKIE.get());
                        pOutput.accept(ModBlocks.MYSTICAL_COOKIE_JAR.get());
                        pOutput.accept(ModBlocks.MYSTICAL_GLASS.get());
                        pOutput.accept(ModBlocks.MYSTICAL_GLASS_PANE.get());
                        pOutput.accept(ModItems.FLAME_WAND.get());
                        pOutput.accept(ModItems.ICE_WAND.get());
                        ItemStack IMPACT_EXPLODE_BOOK = new ItemStack(Items.ENCHANTED_BOOK);
                        EnchantedBookItem.addEnchantment(IMPACT_EXPLODE_BOOK, new EnchantmentInstance(ModEnchantments.IMPACT_EXPLODE.get(), 1));
                        pOutput.accept(IMPACT_EXPLODE_BOOK);
                    })
                    .build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
