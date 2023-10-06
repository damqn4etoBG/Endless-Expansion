package net.damqn4etobg.endlessexpansion.worldgen;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ARBOR_PLACED_KEY = createKey("arbor_placed");
    public static final ResourceKey<PlacedFeature> TITANUM_GRASS_PLACED_KEY = createKey("titanum_grass_placed");
    public static final ResourceKey<PlacedFeature> LUMINITE_PLACED_KEY = createKey("luminite_placed");
    public static final ResourceKey<PlacedFeature>  MYSTICAL_EVERBLUE_ORCHID_PLACED_KEY = createKey("everblue_orchid_placed");
    public static final ResourceKey<PlacedFeature> COBALT_PLACED_KEY = createKey("cobalt_placed");
    public static final ResourceKey<PlacedFeature> MUD_MOSS_PLACED_KEY = createKey("mud_moss_placed");
    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ARBOR_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ARBOR_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.ARBOR_SAPLING.get()));

        register(context, TITANUM_GRASS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TITANUM_GRASS_KEY),
                List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context, LUMINITE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_LUMINITE_ORE_KEY),
                ModOrePlacement.rareOrePlacement(1,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(-20))));

        register(context, MYSTICAL_EVERBLUE_ORCHID_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MYSTICAL_EVERBLUE_ORCHID_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.MYSTICAL_EVERBLUE_OCRHID.get()));

        register(context, COBALT_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WORLDBEYOND_COBALT_ORE_KEY),
                ModOrePlacement.rareOrePlacement(1,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(-40))));

        register(context, MUD_MOSS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MUD_MOSS_KEY),
                List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    }


    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(EndlessExpansion.MODID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
