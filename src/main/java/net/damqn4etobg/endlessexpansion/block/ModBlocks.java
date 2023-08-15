package net.damqn4etobg.endlessexpansion.block;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.block.custom.RadioactiveGeneratorBlock;
import net.damqn4etobg.endlessexpansion.fluid.ModFluids;
import net.damqn4etobg.endlessexpansion.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, EndlessExpansion.MODID);

    public static final RegistryObject<Block> URANIUM_BLOCK = registerBlock("uranium_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL)));

    public static final RegistryObject<Block> RADIOACTIVE_GENERATOR = registerBlock("radioactive_generator",
            () -> new RadioactiveGeneratorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(4f).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistryObject<LiquidBlock> NUCLEAR_WASTE_BLOCK = BLOCKS.register("nuclear_waste_block",
            () -> new LiquidBlock(ModFluids.SOURCE_NUCLEAR_WASTE, BlockBehaviour.Properties.copy(Blocks.WATER)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
