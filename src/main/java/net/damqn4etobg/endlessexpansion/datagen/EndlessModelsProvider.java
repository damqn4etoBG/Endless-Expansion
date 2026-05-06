package net.damqn4etobg.endlessexpansion.datagen;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

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
        itemModels.generateFlatItem(LUMINITE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(LUMINITE_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(LUMINITE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(LUMINITE_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(LUMINITE_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(FLAMMATINE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(FLAMMATINE_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(FLAMMATINE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(FLAMMATINE_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(FLAMMATINE_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(SHADOWSTEEL_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(SHADOWSTEEL_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(SHADOWSTEEL_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(SHADOWSTEEL_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(SHADOWSTEEL_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(MYSTICAL_EVERBLUE_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SHADOWSTEEL_HOOD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SHADOWSTEEL_CLOAK.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SHADOWSTEEL_PANTS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SHADOWSTEEL_BOOTS.get(), ModelTemplates.FLAT_ITEM);

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
        createBlockRenderType(INFUSING_STATION.get(), Identifier.fromNamespaceAndPath(EndlessExpansion.MODID, "test"), blockModels.modelOutput, blockModels.blockStateOutput);
    }

    public static void createBlockRenderType(Block block, Identifier renderType, BiConsumer<Identifier, ModelInstance> output, Consumer<BlockModelDefinitionGenerator> generator) {
        TexturedModel.Provider provider = TexturedModel.CUBE.updateTemplate((template -> template.extend().renderType(renderType).build()));
        MultiVariant variant = BlockModelGenerators.plainVariant(provider.create(block, output));
        generator.accept(MultiVariantGenerator.dispatch(block, variant));
    }
}
