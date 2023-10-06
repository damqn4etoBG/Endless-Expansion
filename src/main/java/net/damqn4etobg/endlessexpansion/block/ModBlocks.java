package net.damqn4etobg.endlessexpansion.block;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.block.custom.ModFlammableRotatedPillarBlock;
import net.damqn4etobg.endlessexpansion.block.custom.RadioactiveGeneratorBlock;
import net.damqn4etobg.endlessexpansion.block.custom.TitanumGrassBlock;
import net.damqn4etobg.endlessexpansion.block.custom.WorldBeyondPortalBlock;
import net.damqn4etobg.endlessexpansion.fluid.ModFluids;
import net.damqn4etobg.endlessexpansion.item.ModItems;
import net.damqn4etobg.endlessexpansion.worldgen.tree.ArborTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, EndlessExpansion.MODID);
    public static final RegistryObject<Block> URANIUM_BLOCK = registerBlock("uranium_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> RADIOACTIVE_GENERATOR = registerBlock("radioactive_generator",
            () -> new RadioactiveGeneratorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistryObject<LiquidBlock> NUCLEAR_WASTE_BLOCK = BLOCKS.register("nuclear_waste_block",
            () -> new LiquidBlock(ModFluids.SOURCE_NUCLEAR_WASTE, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<Block> ARBOR_LOG = registerBlock("arbor_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)
                    .strength(2.5f)));
    public static final RegistryObject<Block> ARBOR_WOOD = registerBlock("arbor_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
                    .strength(2.5f)));
    public static final RegistryObject<Block> STRIPPED_ARBOR_LOG = registerBlock("stripped_arbor_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)
                    .strength(2.5f)));
    public static final RegistryObject<Block> STRIPPED_ARBOR_WOOD = registerBlock("stripped_arbor_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)
                    .strength(2.5f)));

    public static final RegistryObject<Block> ARBOR_PLANKS = registerBlock("arbor_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
                    .strength(2.5f)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 20;
                }
            });
    public static final RegistryObject<Block> ARBOR_LEAVES = registerBlock("arbor_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 60;
                }
            });

    public static final RegistryObject<Block> ARBOR_STAIRS = registerBlock("arbor_stairs",
            () -> new StairBlock(() -> ModBlocks.ARBOR_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));
    public static final RegistryObject<Block> ARBOR_SLAB = registerBlock("arbor_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));

    public static final RegistryObject<Block> ARBOR_BUTTON = registerBlock("arbor_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON),
                    BlockSetType.OAK, 20, true));
    public static final RegistryObject<Block> ARBOR_PRESSURE_PLATE = registerBlock("arbor_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE),
                    BlockSetType.OAK));

    public static final RegistryObject<Block> ARBOR_FENCE = registerBlock("arbor_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final RegistryObject<Block> ARBOR_FENCE_GATE = registerBlock("arbor_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE));

    public static final RegistryObject<Block> TITANUM_SOIL = registerBlock("titanum_soil",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));

    public static final RegistryObject<Block> TITANUM_GRASS_BLOCK = registerBlock("titanum_grass_block",
            () -> new TitanumGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).randomTicks()));

    public static final RegistryObject<Block> ARBOR_SAPLING = registerBlock("arbor_sapling",
            () -> new SaplingBlock(new ArborTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> GLACIER_BRICKS = registerBlock("glacier_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> WORLDBEYOND_PORTAL = registerBlockWithoutBlockItem("world_beyond_portal",
            WorldBeyondPortalBlock::new);
    public static final RegistryObject<Block> DEEPSLATE_LUMINITE_ORE = registerBlock("deepslate_luminite_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_GOLD_ORE).sound(SoundType.AMETHYST).lightLevel(state -> 10)));

    public static final RegistryObject<Block> MYSTICAL_EVERBLUE_OCRHID = registerBlock("mystical_everblue_orchid",
            () -> new FlowerBlock(() -> MobEffects.LUCK, 5,
                    BlockBehaviour.Properties.copy(Blocks.BLUE_ORCHID).noOcclusion().noCollission().lightLevel(state -> 7)) {
                @Override
                public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                    pTooltip.add(Component.literal("§d§oIt gives off a strong Magical energy"));
                    super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
                }
            });

    public static final RegistryObject<Block> POTTED_MYSTICAL_EVERBLUE_OCRHID = BLOCKS.register("potted_mystical_everblue_orchid",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.MYSTICAL_EVERBLUE_OCRHID,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_BLUE_ORCHID).noOcclusion().lightLevel(state -> 7)));

    public static final RegistryObject<Block> PACKED_SNOW_BLOCK = registerBlock("packed_snow_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK).strength(1.0f)));

    public static final RegistryObject<Block> COBALT_ORE = registerBlock("cobalt_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> DEEPSLATE_COBALT_ORE = registerBlock("deepslate_cobalt_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));

    public static final RegistryObject<Block> COBALT_BLOCK = registerBlock("cobalt_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> CELESTIAL_BLOCK = registerBlock("celestial_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }
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
