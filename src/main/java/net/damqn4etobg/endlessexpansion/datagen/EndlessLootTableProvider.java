package net.damqn4etobg.endlessexpansion.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

import static net.damqn4etobg.endlessexpansion.block.EndlessBlocks.*;

public class EndlessLootTableProvider extends BlockLootSubProvider {
    private static final float[] JUNGLE_LEAVES_SAPLING_CHANCES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

    public EndlessLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected void generate() {
        dropSelf(ARBOR_LOG.get());
        dropSelf(ARBOR_WOOD.get());
        dropSelf(STRIPPED_ARBOR_LOG.get());
        dropSelf(STRIPPED_ARBOR_WOOD.get());
        dropSelf(ARBOR_PLANKS.get());
        dropSelf(ARBOR_STAIRS.get());
        dropSelf(ARBOR_SLAB.get());
        dropSelf(ARBOR_BUTTON.get());
        dropSelf(ARBOR_PRESSURE_PLATE.get());
        dropSelf(ARBOR_FENCE.get());
        dropSelf(ARBOR_FENCE_GATE.get());
        add(ARBOR_DOOR.get(), createDoorTable(ARBOR_DOOR.get()));
        dropSelf(ARBOR_TRAPDOOR.get());
        add(ARBOR_LEAVES.get(), createLeavesDrops(ARBOR_LEAVES.get(), ARBOR_LOG.get(), JUNGLE_LEAVES_SAPLING_CHANCES));
        dropSelf(GLACIER_BRICKS.get());
        dropSelf(INFUSING_STATION.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
