package net.damqn4etobg.datagen;

import net.damqn4etobg.block.EndlessBlocks;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.item.EndlessItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class EndlessModelsProvider extends ModelProvider {
    public EndlessModelsProvider(PackOutput output) {
        super(output, EndlessExpansion.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(EndlessItems.LUMINITE.get(), ModelTemplates.FLAT_ITEM);

        blockModels.woodProvider(EndlessBlocks.ARBOR_LOG.get()).logWithHorizontal(EndlessBlocks.ARBOR_LOG.get()).wood(EndlessBlocks.ARBOR_WOOD.get());
        blockModels.woodProvider(EndlessBlocks.STRIPPED_ARBOR_LOG.get()).logWithHorizontal(EndlessBlocks.STRIPPED_ARBOR_LOG.get()).wood(EndlessBlocks.STRIPPED_ARBOR_WOOD.get());
        blockModels.createTrivialCube(EndlessBlocks.ARBOR_LEAVES.get());
        blockModels.family(EndlessBlocks.ARBOR_PLANKS.get())
                .stairs(EndlessBlocks.ARBOR_STAIRS.get())
                .slab(EndlessBlocks.ARBOR_SLAB.get())
                .button(EndlessBlocks.ARBOR_BUTTON.get())
                .pressurePlate(EndlessBlocks.ARBOR_PRESSURE_PLATE.get())
                .fence(EndlessBlocks.ARBOR_FENCE.get())
                .fenceGate(EndlessBlocks.ARBOR_FENCE_GATE.get()); // todo: add door, trapdoor
    }
}
