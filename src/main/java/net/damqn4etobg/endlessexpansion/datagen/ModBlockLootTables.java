package net.damqn4etobg.endlessexpansion.datagen;

import net.damqn4etobg.endlessexpansion.block.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.ARBOR_LOG.get());
        this.dropSelf(ModBlocks.ARBOR_WOOD.get());
        this.dropSelf(ModBlocks.ARBOR_PLANKS.get());
        this.dropSelf(ModBlocks.STRIPPED_ARBOR_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_ARBOR_LOG.get());
        this.dropSelf(ModBlocks.ARBOR_SAPLING.get());

        this.add(ModBlocks.ARBOR_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.ARBOR_LEAVES.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBlocks.MYSTICAL_EVERBLUE_OCRHID.get());
        this.add(ModBlocks.POTTED_MYSTICAL_EVERBLUE_OCRHID.get(), createPotFlowerItemTable(ModBlocks.MYSTICAL_EVERBLUE_OCRHID.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}