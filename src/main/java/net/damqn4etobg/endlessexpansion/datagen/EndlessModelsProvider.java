package net.damqn4etobg.endlessexpansion.datagen;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

import static net.damqn4etobg.endlessexpansion.block.EndlessBlocks.*;
import static net.damqn4etobg.endlessexpansion.item.EndlessItems.*;

public class EndlessModelsProvider extends ModelProvider {
    public EndlessModelsProvider(PackOutput output) {
        super(output, EndlessExpansion.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ARBOR_STICK.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(LUMINITE_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(PYRONIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SHADOW_ESSENCE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SHADOWSTEEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(FLAMMATINE_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(LUMINITE_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(RAW_COBALT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(COBALT_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(COBALT_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(COBALT_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(COBALT_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(COBALT_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(COBALT_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(COBALT_PAXEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.declareCustomModelItem(LUMINITE_SWORD.get());
        itemModels.declareCustomModelItem(LUMINITE_PICKAXE.get());
        itemModels.declareCustomModelItem(LUMINITE_AXE.get());
        itemModels.declareCustomModelItem(LUMINITE_SHOVEL.get());
        itemModels.declareCustomModelItem(LUMINITE_HOE.get());
        itemModels.declareCustomModelItem(FLAMMATINE_SWORD.get());
        itemModels.declareCustomModelItem(FLAMMATINE_PICKAXE.get());
        itemModels.declareCustomModelItem(FLAMMATINE_AXE.get());
        itemModels.declareCustomModelItem(FLAMMATINE_SHOVEL.get());
        itemModels.declareCustomModelItem(FLAMMATINE_HOE.get());
        itemModels.generateFlatItem(SHADOWSTEEL_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(SHADOWSTEEL_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(SHADOWSTEEL_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(SHADOWSTEEL_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(SHADOWSTEEL_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(MYSTICAL_EVERBLUE_POWDER.get(), ModelTemplates.FLAT_ITEM);

        blockModels.woodProvider(ARBOR_LOG.get()).logWithHorizontal(ARBOR_LOG.get()).wood(ARBOR_WOOD.get());
        blockModels.woodProvider(STRIPPED_ARBOR_LOG.get()).logWithHorizontal(STRIPPED_ARBOR_LOG.get()).wood(STRIPPED_ARBOR_WOOD.get());
        blockModels.createTrivialCube(ARBOR_LEAVES.get());
        blockModels.createTrivialCube(GLACIER_BRICKS.get());
        blockModels.family(ARBOR_PLANKS.get())
                .stairs(ARBOR_STAIRS.get())
                .slab(ARBOR_SLAB.get())
                .button(ARBOR_BUTTON.get())
                .pressurePlate(ARBOR_PRESSURE_PLATE.get())
                .fence(ARBOR_FENCE.get())
                .fenceGate(ARBOR_FENCE_GATE.get())
                .door(ARBOR_DOOR.get())
                .trapdoor(ARBOR_TRAPDOOR.get());
    }
}
