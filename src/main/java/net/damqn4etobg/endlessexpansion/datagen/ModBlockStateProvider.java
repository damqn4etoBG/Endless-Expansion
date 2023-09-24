package net.damqn4etobg.endlessexpansion.datagen;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class    ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, EndlessExpansion.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
//        logBlock(((RotatedPillarBlock) ModBlocks.ARBOR_LOG.get()));
//        axisBlock((RotatedPillarBlock) ModBlocks.ARBOR_WOOD.get(), blockTexture(ModBlocks.ARBOR_LOG.get()), blockTexture(ModBlocks.ARBOR_LOG.get()));
//        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_ARBOR_LOG.get(), new ResourceLocation(EndlessExpansion.MODID, "block/stripped_arbor_log"),
//                new ResourceLocation(EndlessExpansion.MODID, "block/stripped_arbor_log_top"));
//        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_ARBOR_WOOD.get(), new ResourceLocation(EndlessExpansion.MODID, "block/stripped_arbor_log"),
//                new ResourceLocation(EndlessExpansion.MODID, "block/stripped_arbor_log"));
//
//        blockWithItem(ModBlocks.ARBOR_PLANKS);
//        blockWithItem(ModBlocks.ARBOR_LEAVES);
//        saplingBlock(ModBlocks.ARBOR_SAPLING);
//
//        simpleBlockItem(ModBlocks.ARBOR_LOG.get(), models().withExistingParent("endlessexpansion:arbor_log", "minecraft:block/cube_column"));
//        simpleBlockItem(ModBlocks.ARBOR_WOOD.get(), models().withExistingParent("endlessexpansion:arbor_wood", "minecraft:block/cube_column"));
//        simpleBlockItem(ModBlocks.STRIPPED_ARBOR_LOG.get(), models().withExistingParent("endlessexpansion:stripped_arbor_log", "minecraft:block/cube_column"));
//        simpleBlockItem(ModBlocks.STRIPPED_ARBOR_WOOD.get(), models().withExistingParent("endlessexpansion:stripped_arbor_wood", "minecraft:block/cube_column"));
//
//        simpleBlockWithItem(ModBlocks.MYSTICAL_EVERBLUE_OCRHID.get(), models().cross(blockTexture(ModBlocks.MYSTICAL_EVERBLUE_OCRHID.get()).getPath(),
//                blockTexture(ModBlocks.MYSTICAL_EVERBLUE_OCRHID.get())).renderType("cutout"));
//        simpleBlockWithItem(ModBlocks.POTTED_MYSTICAL_EVERBLUE_OCRHID.get(), models().singleTexture("potted_mystical_everblue_orchid", new ResourceLocation("flower_pot_cross"), "plant",
//                blockTexture(ModBlocks.MYSTICAL_EVERBLUE_OCRHID.get())).renderType("cutout"));
//
//        blockWithItem(ModBlocks.GLACIER_BRICKS);
//        blockWithItem(ModBlocks.DEEPSLATE_LUMINITE_ORE);
//        blockWithItem(ModBlocks.DEEPSLATE_COBALT_ORE);
//        blockWithItem(ModBlocks.COBALT_ORE);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
}
