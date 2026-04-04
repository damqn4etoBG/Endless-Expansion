package net.damqn4etobg.endlessexpansion.block;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.block.custom.PlanksBlock;
import net.damqn4etobg.endlessexpansion.block.custom.WoodBlock;
import net.damqn4etobg.endlessexpansion.item.EndlessItems;
import net.damqn4etobg.endlessexpansion.sound.EndlessSounds;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class EndlessBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(EndlessExpansion.MODID);

    public static final DeferredBlock<Block> GLACIER_BRICKS = registerBlock("glacier_bricks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).sound(SoundType.GLASS));

    public static final DeferredBlock<WoodBlock> ARBOR_LOG = registerBlock("arbor_log", WoodBlock::new, EndlessBlocks::arborWoodProps);
    public static final DeferredBlock<WoodBlock> ARBOR_WOOD = registerBlock("arbor_wood", WoodBlock::new, EndlessBlocks::arborWoodProps);
    public static final DeferredBlock<WoodBlock> STRIPPED_ARBOR_LOG = registerBlock("stripped_arbor_log", WoodBlock::new, EndlessBlocks::arborWoodProps);
    public static final DeferredBlock<WoodBlock> STRIPPED_ARBOR_WOOD = registerBlock("stripped_arbor_wood", WoodBlock::new, EndlessBlocks::arborWoodProps);
    public static final DeferredBlock<PlanksBlock> ARBOR_PLANKS = registerBlock("arbor_planks", PlanksBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS));
    public static final DeferredBlock<TintedParticleLeavesBlock> ARBOR_LEAVES = registerBlock("arbor_leaves", props -> new TintedParticleLeavesBlock(0f, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_LEAVES));
    public static final DeferredBlock<StairBlock> ARBOR_STAIRS = registerBlock("arbor_stairs", props -> new StairBlock(ARBOR_PLANKS.get().defaultBlockState(), props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_STAIRS));
    public static final DeferredBlock<SlabBlock> ARBOR_SLAB = registerBlock("arbor_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SLAB));
    public static final DeferredBlock<ButtonBlock> ARBOR_BUTTON = registerBlock("arbor_button", props -> new ButtonBlock(EndlessBlockSetTypes.ARBOR, 20, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_BUTTON));
    public static final DeferredBlock<PressurePlateBlock> ARBOR_PRESSURE_PLATE = registerBlock("arbor_pressure_plate", props -> new PressurePlateBlock(EndlessBlockSetTypes.ARBOR, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PRESSURE_PLATE));
    public static final DeferredBlock<FenceBlock> ARBOR_FENCE = registerBlock("arbor_fence", FenceBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_FENCE));
    public static final DeferredBlock<FenceGateBlock> ARBOR_FENCE_GATE = registerBlock("arbor_fence_gate", props -> new FenceGateBlock(EndlessWoodTypes.ARBOR, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_FENCE_GATE));
    public static final DeferredBlock<TrapDoorBlock> ARBOR_TRAPDOOR = registerBlock("arbor_trapdoor", props -> new TrapDoorBlock(EndlessBlockSetTypes.ARBOR, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_TRAPDOOR));
    public static final DeferredBlock<DoorBlock> ARBOR_DOOR = registerBlock("arbor_door", props -> new DoorBlock(EndlessBlockSetTypes.ARBOR, props), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_DOOR));

    private static BlockBehaviour.Properties arborWoodProps() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_WOOD).strength(2.5f).sound(EndlessSounds.ARBOR_WOOD_SOUNDS);
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> func, Supplier<BlockBehaviour.Properties> properties) {
        DeferredBlock<T> registered = BLOCKS.registerBlock(name, func, properties);
        registerBlockItem(name, registered);
        return registered;
    }

    private static <T extends Block> DeferredItem<BlockItem> registerBlockItem(String name, DeferredBlock<T> block) {
        return EndlessItems.ITEMS.registerSimpleBlockItem(name, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
