package net.damqn4etobg.endlessexpansion.dimension;

import com.mojang.datafixers.util.Pair;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.worldgen.biome.ModBiomes;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouter;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.function.Consumer;

public class ModDimensions {
    public static NoiseRouter blankNoiseRouter = new NoiseRouter(ModDensityFunction.densityFunction, ModDensityFunction.densityFunction, ModDensityFunction.densityFunction, ModDensityFunction.densityFunction, ModDensityFunction.densityFunction, ModDensityFunction.densityFunction, ModDensityFunction.densityFunction, ModDensityFunction.densityFunction, ModDensityFunction.densityFunction, ModDensityFunction.densityFunction, ModDensityFunction.densityFunction, ModDensityFunction.densityFunction, ModDensityFunction.densityFunction, ModDensityFunction.densityFunction, ModDensityFunction.densityFunction);
    public static List<Climate.ParameterPoint> blankSpawnTarget = List.of(new Climate.ParameterPoint(new Climate.Parameter(0, 0), new Climate.Parameter(0, 0), new Climate.Parameter(0, 0), new Climate.Parameter(0, 0), new Climate.Parameter(0, 0), new Climate.Parameter(0, 0), 0));
    public static ResourceKey<LevelStem> WORLD_BEYOND_KEY = ResourceKey.create(Registries.LEVEL_STEM, ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "world_beyond"));
    public static ResourceKey<Level> WORLD_BEYOND_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "world_beyond"));
    public static ResourceKey<DimensionType> WORLD_BEYOND_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "world_beyond_type"));
    public static ResourceLocation WORLD_BEYOND_EFFECTS = ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "world_beyond");
    private static final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);
    private static final Climate.Parameter OCEAN_RANGE = Climate.Parameter.span(-1.0F, -0.88F);
    private static final Climate.Parameter LOWEST = Climate.Parameter.point(-1.0F);
    private static final Climate.Parameter ZERO = Climate.Parameter.point(0.0F);
    private static final Climate.Parameter DEFAULT_WEIRDNESS = Climate.Parameter.span(0.8F, 1.0F);
    private static final Climate.Parameter[] temperatures = new Climate.Parameter[]{Climate.Parameter.span(-1.0F, -0.45F), Climate.Parameter.span(-0.45F, -0.15F), Climate.Parameter.span(-0.15F, 0.2F), Climate.Parameter.span(0.2F, 0.55F), Climate.Parameter.span(0.55F, 1.0F)};
    private static final Climate.Parameter deepOceanContinentalness = Climate.Parameter.span(-1.05F, -0.455F);
    private static final Climate.Parameter oceanContinentalness = Climate.Parameter.span(-0.455F, -0.19F);
    private static final ResourceKey<Biome>[][] OCEANS = new ResourceKey[][]{{Biomes.DEEP_FROZEN_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN}, {Biomes.FROZEN_OCEAN, Biomes.COLD_OCEAN, Biomes.OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.WARM_OCEAN}};
    private static final Climate.Parameter[] humidities = new Climate.Parameter[]{Climate.Parameter.span(-1.0F, -0.35F), Climate.Parameter.span(-0.35F, -0.1F), Climate.Parameter.span(-0.1F, 0.1F), Climate.Parameter.span(0.1F, 0.3F), Climate.Parameter.span(0.3F, 1.0F)};
    private static final Climate.Parameter[] erosions = new Climate.Parameter[]{Climate.Parameter.span(-1.0F, -0.78F), Climate.Parameter.span(-0.78F, -0.375F), Climate.Parameter.span(-0.375F, -0.2225F), Climate.Parameter.span(-0.2225F, 0.05F), Climate.Parameter.span(0.05F, 0.45F), Climate.Parameter.span(0.45F, 0.55F), Climate.Parameter.span(0.55F, 1.0F)};
    private static final Climate.Parameter FROZEN_RANGE = temperatures[0];
    private static final Climate.Parameter UNFROZEN_RANGE = Climate.Parameter.span(temperatures[1], temperatures[4]);
    private static final Climate.Parameter coastContinentalness = Climate.Parameter.span(-0.19F, -0.11F);
    private static final Climate.Parameter inlandContinentalness = Climate.Parameter.span(-0.11F, 0.55F);
    private static final Climate.Parameter nearInlandContinentalness = Climate.Parameter.span(-0.11F, 0.03F);
    private static final Climate.Parameter midInlandContinentalness = Climate.Parameter.span(0.03F, 0.3F);
    private static final Climate.Parameter farInlandContinentalness = Climate.Parameter.span(0.3F, 1.0F);

    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(WORLD_BEYOND_DIM_TYPE, new DimensionType(OptionalLong.empty(), true, false, false, true, 0.5D, true, false, -64, 384, 384, BlockTags.INFINIBURN_OVERWORLD, WORLD_BEYOND_EFFECTS, 0f, new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        MultiNoiseBiomeSource biomeSource = MultiNoiseBiomeSource.createFromList(new Climate.ParameterList<>(generateBiomePoints(biomeRegistry)));
        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(biomeSource, noiseGenSettings.getOrThrow(ModLevelGen.WORLD_BEYOND));
        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.WORLD_BEYOND_DIM_TYPE), noiseBasedChunkGenerator);

        context.register(WORLD_BEYOND_KEY, stem);
    }

    private static List<Pair<Climate.ParameterPoint, Holder<Biome>>> generateBiomePoints(HolderGetter<Biome> biomeRegistry) {
        ArrayList<Pair<Climate.ParameterPoint, Holder<Biome>>> biomePoints = new ArrayList<>();

        //addOceans(pair -> biomePoints.add(Pair.of(pair.getFirst(), biomeRegistry.getOrThrow(pair.getSecond()))));
        //addShoreBiomes(pair -> biomePoints.add(Pair.of(pair.getFirst(), biomeRegistry.getOrThrow(pair.getSecond()))));

        //addInlandBiomes(pair -> biomePoints.add(Pair.of(pair.getFirst(), biomeRegistry.getOrThrow(pair.getSecond()))));
        //addDebugBiomes(pair -> biomePoints.add(Pair.of(pair.getFirst(), biomeRegistry.getOrThrow(pair.getSecond()))));

        addOceans(pair -> biomePoints.add(Pair.of(pair.getFirst(), biomeRegistry.getOrThrow(pair.getSecond()))));
        //addRivers(pair -> biomePoints.add(Pair.of(pair.getFirst(), biomeRegistry.getOrThrow(pair.getSecond()))));
        addNearSlice(pair -> biomePoints.add(Pair.of(pair.getFirst(), biomeRegistry.getOrThrow(pair.getSecond()))), Climate.Parameter.span(-1.0F, -0.8F));
        addMidSlice(pair -> biomePoints.add(Pair.of(pair.getFirst(), biomeRegistry.getOrThrow(pair.getSecond()))), Climate.Parameter.span(-0.8F, -0.6F));
        addFarSlice(pair -> biomePoints.add(Pair.of(pair.getFirst(), biomeRegistry.getOrThrow(pair.getSecond()))), Climate.Parameter.span(-0.6F, -0.4F));
        addMidSlice(pair -> biomePoints.add(Pair.of(pair.getFirst(), biomeRegistry.getOrThrow(pair.getSecond()))), Climate.Parameter.span(-0.4F, -0.2F));
        addNearSlice(pair -> biomePoints.add(Pair.of(pair.getFirst(), biomeRegistry.getOrThrow(pair.getSecond()))), Climate.Parameter.span(-0.2F, 0.0F));
        addMidSlice(pair -> biomePoints.add(Pair.of(pair.getFirst(), biomeRegistry.getOrThrow(pair.getSecond()))), Climate.Parameter.span(0.2F, 0.4F));
        addFarSlice(pair -> biomePoints.add(Pair.of(pair.getFirst(), biomeRegistry.getOrThrow(pair.getSecond()))), Climate.Parameter.span(0.4F, 0.6F));
        addMidSlice(pair -> biomePoints.add(Pair.of(pair.getFirst(), biomeRegistry.getOrThrow(pair.getSecond()))), Climate.Parameter.span(0.6F, 0.8F));
        addNearSlice(pair -> biomePoints.add(Pair.of(pair.getFirst(), biomeRegistry.getOrThrow(pair.getSecond()))), Climate.Parameter.span(0.8F, 1.0F));

        return biomePoints;
    }

    private static void addOceans(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> pConsumer) {
        for (int i = 0; i < temperatures.length; ++i) {
            Climate.Parameter temperature = temperatures[i];
            addOceanBiome(pConsumer, temperature, FULL_RANGE, deepOceanContinentalness, FULL_RANGE, FULL_RANGE, ZERO, OCEANS[0][i]);
            addOceanBiome(pConsumer, temperature, FULL_RANGE, oceanContinentalness, FULL_RANGE, FULL_RANGE, ZERO, OCEANS[1][i]);
        }
        addOceanBiome(pConsumer, Climate.Parameter.span(temperatures[0], temperatures[1]), FULL_RANGE, deepOceanContinentalness, FULL_RANGE, FULL_RANGE, ZERO, ModBiomes.ABYSSAL_OCEAN);
    }

    private static void addDebugBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> pConsumer) {
        addSurfaceBiome(pConsumer, UNFROZEN_RANGE, FULL_RANGE, deepOceanContinentalness, FULL_RANGE, FULL_RANGE, ZERO, 0.0F, ModBiomes.ABYSSAL_OCEAN);
    }

    private static void addRivers(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> pConsumer) {
        for (int i = 0; i < temperatures.length; ++i) {
            Climate.Parameter temperature = temperatures[i];
            Climate.Parameter pParam = Climate.Parameter.span(-0.05F, 0.05F);

            // Adding river biomes with extended ranges for erosion and continentalness to make rivers longer
            addSurfaceBiome(pConsumer, FROZEN_RANGE, FULL_RANGE, Climate.Parameter.span(coastContinentalness, farInlandContinentalness), Climate.Parameter.span(erosions[0], erosions[3]), pParam, ZERO, 0.0F, Biomes.FROZEN_RIVER);
            addSurfaceBiome(pConsumer, UNFROZEN_RANGE, FULL_RANGE, Climate.Parameter.span(coastContinentalness, farInlandContinentalness), Climate.Parameter.span(erosions[0], erosions[3]), pParam, ZERO, 0.0F, Biomes.RIVER);
            addSurfaceBiome(pConsumer, FROZEN_RANGE, FULL_RANGE, Climate.Parameter.span(nearInlandContinentalness, farInlandContinentalness), Climate.Parameter.span(erosions[0], erosions[3]), pParam, ZERO, 0.0F, Biomes.FROZEN_RIVER);
            addSurfaceBiome(pConsumer, UNFROZEN_RANGE, FULL_RANGE, Climate.Parameter.span(nearInlandContinentalness, farInlandContinentalness), Climate.Parameter.span(erosions[0], erosions[3]), pParam, ZERO, 0.0F, Biomes.RIVER);
            addSurfaceBiome(pConsumer, FROZEN_RANGE, FULL_RANGE, Climate.Parameter.span(coastContinentalness, farInlandContinentalness), Climate.Parameter.span(erosions[3], erosions[5]), pParam, ZERO, 0.0F, Biomes.FROZEN_RIVER);
            addSurfaceBiome(pConsumer, UNFROZEN_RANGE, FULL_RANGE, Climate.Parameter.span(coastContinentalness, farInlandContinentalness), Climate.Parameter.span(erosions[3], erosions[5]), pParam, ZERO, 0.0F, Biomes.RIVER);
            addSurfaceBiome(pConsumer, FROZEN_RANGE, FULL_RANGE, Climate.Parameter.span(coastContinentalness, farInlandContinentalness), erosions[6], pParam, ZERO, 0.0F, Biomes.FROZEN_RIVER);
            addSurfaceBiome(pConsumer, UNFROZEN_RANGE, FULL_RANGE, Climate.Parameter.span(coastContinentalness, farInlandContinentalness), erosions[6], pParam, ZERO, 0.0F, Biomes.RIVER);
            addSurfaceBiome(pConsumer, FROZEN_RANGE, FULL_RANGE, Climate.Parameter.span(inlandContinentalness, farInlandContinentalness), erosions[6], pParam, ZERO, 0.0F, Biomes.FROZEN_RIVER);
            addSurfaceBiome(pConsumer, UNFROZEN_RANGE, FULL_RANGE, Climate.Parameter.span(inlandContinentalness, farInlandContinentalness), erosions[6], pParam, ZERO, 0.0F, Biomes.RIVER);
        }
    }

    private static ResourceKey<Biome> pickGravelOrRockyBeach(int pTemperature, int pHumidity) {
        if (pTemperature == 2) {
            if (pHumidity == 0) {
                return ModBiomes.ROCKY_BEACH;
            } else {
                return ModBiomes.GRAVEL_BEACH;
            }
        } else {
            if (pHumidity < 2) {
                return ModBiomes.GRAVEL_BEACH;
            } else {
                return ModBiomes.ROCKY_BEACH;
            }
        }
    }

    private static ResourceKey<Biome> pickFrozenOrNormalBeach(int pTemperature) {
        if (pTemperature == 0) {
            return ModBiomes.FROZEN_BEACH;
        } else {
            return pTemperature >= 3 ? ModBiomes.BASALT_BEACH : ModBiomes.BEACH;
        }
    }

    private static void addNearSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter depth) {
        addSurfaceBiome(consumer, Climate.Parameter.span(temperatures[1], temperatures[2]), FULL_RANGE, nearInlandContinentalness, erosions[6], depth, ZERO, 0.0F, ModBiomes.SUNKEN_WASTES);

        for (int t = 0; t < temperatures.length; t++) {
            Climate.Parameter temperature = temperatures[t];
            for (int h = 0; h < humidities.length; h++) {
                Climate.Parameter humidity = humidities[h];
                ResourceKey<Biome> land = pickNearBiome(t, h);
                ResourceKey<Biome> beach = pickBeachFor(land, t, h);

                addSurfaceBiome(consumer, temperature, humidity, coastContinentalness, erosions[6], depth, ZERO, 0.0f, beach);
                addSurfaceBiome(consumer, temperature, humidity, nearInlandContinentalness, erosions[3], depth, ZERO, 0.0f, land);
            }
        }
    }

    private static void addMidSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter depth) {
        for (int t = 0; t < temperatures.length; t++) {
            Climate.Parameter temperature = temperatures[t];
            for (int h = 0; h < humidities.length; h++) {
                Climate.Parameter humidity = humidities[h];
                ResourceKey<Biome> land = pickNearBiome(t, h);

                addSurfaceBiome(consumer, temperature, humidity, midInlandContinentalness, erosions[2], depth, ZERO, 0.0f, land);
            }
        }
    }

    private static void addFarSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter depth) {
        for (int t = 0; t < temperatures.length; t++) {
            Climate.Parameter temperature = temperatures[t];
            for (int h = 0; h < humidities.length; h++) {
                Climate.Parameter humidity = humidities[h];
                ResourceKey<Biome> land = pickNearBiome(t, h);

                addSurfaceBiome(consumer, temperature, humidity, farInlandContinentalness, erosions[1], depth, ZERO, 0.0f, land);
            }
        }
    }

    private static ResourceKey<Biome> pickNearBiome(int temperature, int humidity) {
        return switch (temperature) {
            case 0 -> ModBiomes.FROZEN_WASTES;
            case 1 -> ModBiomes.TITANIC_FOREST;
            case 2 -> ModBiomes.ZERZURA;
            case 3 -> ModBiomes.SCORCHED_WASTES;
            case 4 -> ModBiomes.VOLCANIC_WASTES;
            default -> ModBiomes.TITANIC_FOREST;
        };
    }

    private static ResourceKey<Biome> pickBeachFor(ResourceKey<Biome> land, int temperature, int humidity) {
        if (land == ModBiomes.FROZEN_WASTES) return ModBiomes.FROZEN_BEACH;
        if (land == ModBiomes.SCORCHED_WASTES) return ModBiomes.BEACH;
        if (land == ModBiomes.VOLCANIC_WASTES) return ModBiomes.BASALT_BEACH;
        return pickGravelOrRockyBeach(temperature, humidity);
    }

    private static void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> pConsumer, Climate.Parameter pTemperature, Climate.Parameter pHumidity, Climate.Parameter pContinentalness, Climate.Parameter pErosion, Climate.Parameter pDepth, Climate.Parameter pWeirdness, float offset, ResourceKey<Biome> pKey) {
        pConsumer.accept(Pair.of(Climate.parameters(pTemperature, pHumidity, pContinentalness, pErosion, pDepth, pWeirdness, offset), pKey));
        pConsumer.accept(Pair.of(Climate.parameters(pTemperature, pHumidity, pContinentalness, pErosion, pDepth, pWeirdness, offset), pKey));
    }

    private static void addOceanBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> pConsumer, Climate.Parameter pTemperature, Climate.Parameter pHumidity, Climate.Parameter pContinentalness, Climate.Parameter pErosion, Climate.Parameter pDepth, Climate.Parameter pWeirdness, ResourceKey<Biome> pKey) {
        pConsumer.accept(Pair.of(Climate.parameters(pTemperature, pHumidity, pContinentalness, pErosion, pDepth, pWeirdness, 0.0f), pKey));
        pConsumer.accept(Pair.of(Climate.parameters(pTemperature, pHumidity, pContinentalness, pErosion, pDepth, pWeirdness, 0.0f), pKey));
    }
}