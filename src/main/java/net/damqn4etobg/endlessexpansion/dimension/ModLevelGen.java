package net.damqn4etobg.endlessexpansion.dimension;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.worldgen.biome.surface.ModSurfaceRules;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.stream.Stream;

public class ModLevelGen extends NoiseRouterData {
    protected static final NoiseSettings WORLD_BEYOND_NOISE_SETTINGS = NoiseSettings.create(-64, 384, 1, 2);
    public static final ResourceKey<NoiseGeneratorSettings> WORLD_BEYOND = ResourceKey.create(Registries.NOISE_SETTINGS, ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "world_beyond_noise"));
    public static final ResourceKey<DensityFunction> SHIFT_X = ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.parse("shift_x"));
    public static final ResourceKey<DensityFunction> SHIFT_Z = ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.parse("shift_z"));
    public static final ResourceKey<DensityFunction> FACTOR_LARGE = ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.parse("overworld_large_biomes/factor"));
    public static final ResourceKey<DensityFunction> DEPTH_LARGE = ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.parse("overworld_large_biomes/depth"));
    public static final ResourceKey<DensityFunction> SLOPED_CHEESE_LARGE = ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.parse("overworld_large_biomes/sloped_cheese"));
    public static final ResourceKey<DensityFunction> ENTRANCES = ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.parse("overworld/caves/entrances"));
    public static final ResourceKey<DensityFunction> NOODLE = ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.parse("overworld/caves/noodle"));
    public static final ResourceKey<DensityFunction> Y = ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.parse("y"));
    public static final ResourceKey<DensityFunction> SPAGHETTI_2D = ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.parse("overworld/caves/spaghetti_2d"));
    public static final ResourceKey<DensityFunction> SPAGHETTI_ROUGHNESS_FUNCTION = ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.parse("overworld/caves/spaghetti_roughness_function"));
    public static final ResourceKey<DensityFunction> PILLARS = ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.parse("overworld/caves/pillars"));

    public static NoiseGeneratorSettings worldBeyond(BootstapContext<?> pContext) {
        return new NoiseGeneratorSettings(WORLD_BEYOND_NOISE_SETTINGS, Blocks.STONE.defaultBlockState(),
                Blocks.WATER.defaultBlockState(), worldBeyondNoise(pContext.lookup(Registries.DENSITY_FUNCTION),
                pContext.lookup(Registries.NOISE)), ModSurfaceRules.makeRules(), (new OverworldBiomeBuilder()).spawnTarget(),
                63, false, true, true, false);
    }

    public static NoiseRouter worldBeyondNoise(HolderGetter<DensityFunction> densityFunctions, HolderGetter<NormalNoise.NoiseParameters> noise) {
        DensityFunction aqBarrier = DensityFunctions.noise(noise.getOrThrow(Noises.AQUIFER_BARRIER), 0.6525f);
        DensityFunction aqLevelFloodness = DensityFunctions.noise(noise.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS), 0.7025f);
        DensityFunction aqLevelSpread = DensityFunctions.noise(noise.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_SPREAD), 0.7142857142857143);
        DensityFunction aqLava = DensityFunctions.noise(noise.getOrThrow(Noises.AQUIFER_LAVA));
        DensityFunction shiftX = getFunction(densityFunctions, SHIFT_X);
        DensityFunction shiftZ = getFunction(densityFunctions, SHIFT_Z);
        DensityFunction temperature = DensityFunctions.shiftedNoise2d(shiftX, shiftZ, 0.3525f, noise.getOrThrow(Noises.TEMPERATURE_LARGE));
        DensityFunction vegetation = DensityFunctions.shiftedNoise2d(shiftX, shiftZ, 0.3525f, noise.getOrThrow(Noises.VEGETATION_LARGE));
        DensityFunction factor = getFunction(densityFunctions, FACTOR_LARGE);
        DensityFunction depth = getFunction(densityFunctions, DEPTH_LARGE);
        DensityFunction noiseGradient = noiseGradientDensity(DensityFunctions.cache2d(factor), depth);
        DensityFunction slopedCheese = getFunction(densityFunctions, SLOPED_CHEESE_LARGE);
        DensityFunction entrances = DensityFunctions.min(slopedCheese, DensityFunctions.mul(DensityFunctions.constant(5.5), getFunction(densityFunctions, ENTRANCES)));
        DensityFunction underground = DensityFunctions.rangeChoice(slopedCheese, -1000000.0, 1.8505, entrances, underground(densityFunctions, noise, slopedCheese));
        DensityFunction noodle = DensityFunctions.min(postProcess(slideOverworld(false, underground)), getFunction(densityFunctions, NOODLE));
        DensityFunction y = getFunction(densityFunctions, Y);
        int veinsMinY = Stream.of(VeinType.values()).mapToInt((p_224495_) -> p_224495_.minY).min().orElse(-DimensionType.MIN_Y * 2);
        int veinsMaxX = Stream.of(VeinType.values()).mapToInt((p_224457_) -> p_224457_.maxY).max().orElse(-DimensionType.MIN_Y * 2);
        DensityFunction oreVeininess = yLimitedInterpolatable(y, DensityFunctions.noise(noise.getOrThrow(Noises.ORE_VEININESS), 1.5, 2.0), veinsMinY, veinsMaxX, 0);
        DensityFunction oreVeinA = yLimitedInterpolatable(y, DensityFunctions.noise(noise.getOrThrow(Noises.ORE_VEIN_A), 4.2, 5.35), veinsMinY, veinsMaxX, 0).abs();
        DensityFunction oreVeinB = yLimitedInterpolatable(y, DensityFunctions.noise(noise.getOrThrow(Noises.ORE_VEIN_B), 3.5, 4.35), veinsMinY, veinsMaxX, 0).abs();
        DensityFunction $$26 = DensityFunctions.add(DensityFunctions.constant(-0.07999999821186066), DensityFunctions.max(oreVeinA, oreVeinB));
        DensityFunction oreGap = DensityFunctions.noise(noise.getOrThrow(Noises.ORE_GAP));

        return new NoiseRouter(aqBarrier, aqLevelFloodness, aqLevelSpread, aqLava,
                temperature, vegetation, getFunction(densityFunctions, CONTINENTS_LARGE), getFunction(densityFunctions, EROSION_LARGE), depth,
                getFunction(densityFunctions, RIDGES), slideOverworld(false, DensityFunctions.add(noiseGradient, DensityFunctions.constant(-0.7554)).clamp(-64.0, 64.0)),
                noodle, oreVeininess, $$26, oreGap);
    }

    private static DensityFunction underground(HolderGetter<DensityFunction> pDensityFunctions, HolderGetter<NormalNoise.NoiseParameters> pNoiseParameters, DensityFunction p_256658_) {
        DensityFunction $$3 = getFunction(pDensityFunctions, SPAGHETTI_2D);
        DensityFunction $$4 = getFunction(pDensityFunctions, SPAGHETTI_ROUGHNESS_FUNCTION);
        DensityFunction $$5 = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.CAVE_LAYER), 8.5);
        DensityFunction $$6 = DensityFunctions.mul(DensityFunctions.constant(3.5), $$5.square());
        DensityFunction $$7 = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.CAVE_CHEESE), 0.6666666666666666);
        DensityFunction $$8 = DensityFunctions.add(DensityFunctions.add(DensityFunctions.constant(0.27), $$7).clamp(-1.0, 1.0), DensityFunctions.add(DensityFunctions.constant(1.5), DensityFunctions.mul(DensityFunctions.constant(-0.64), p_256658_)).clamp(0.0, 0.5));
        DensityFunction $$9 = DensityFunctions.add($$6, $$8);
        DensityFunction $$10 = DensityFunctions.min(DensityFunctions.min($$9, getFunction(pDensityFunctions, ENTRANCES)), DensityFunctions.add($$3, $$4));
        DensityFunction $$11 = getFunction(pDensityFunctions, PILLARS);
        DensityFunction $$12 = DensityFunctions.rangeChoice($$11, -1000000.0, 0.03, DensityFunctions.constant(-1000000.0), $$11);
        return DensityFunctions.max($$10, $$12);
    }

    public static void bootstrapNoiseGen(BootstapContext<NoiseGeneratorSettings> pContext) {
        pContext.register(WORLD_BEYOND, worldBeyond(pContext));
    }

    public static DensityFunction getFunction(HolderGetter<DensityFunction> pDensityFunctions, ResourceKey<DensityFunction> pKey) {
        return new DensityFunctions.HolderHolder(pDensityFunctions.getOrThrow(pKey));
    }

    public static DensityFunction noiseGradientDensity(DensityFunction pMinFunction, DensityFunction pMaxFunction) {
        DensityFunction $$2 = DensityFunctions.mul(pMaxFunction, pMinFunction);
        return DensityFunctions.mul(DensityFunctions.constant(4.0), $$2.quarterNegative());
    }

    public enum VeinType {
        COPPER(Blocks.COPPER_ORE.defaultBlockState(), Blocks.RAW_COPPER_BLOCK.defaultBlockState(), Blocks.GRANITE.defaultBlockState(), 0, 50),
        IRON(Blocks.DEEPSLATE_IRON_ORE.defaultBlockState(), Blocks.RAW_IRON_BLOCK.defaultBlockState(), Blocks.TUFF.defaultBlockState(), -60, -8);

        final BlockState ore;
        final BlockState rawOreBlock;
        final BlockState filler;
        final int minY;
        final int maxY;

        VeinType(BlockState pOre, BlockState pRawOreBlock, BlockState pFiller, int pMinY, int pMaxY) {
            this.ore = pOre;
            this.rawOreBlock = pRawOreBlock;
            this.filler = pFiller;
            this.minY = pMinY;
            this.maxY = pMaxY;
        }
    }

    private static DensityFunction yLimitedInterpolatable(DensityFunction p_209472_, DensityFunction p_209473_, int p_209474_, int p_209475_, int p_209476_) {
        return DensityFunctions.interpolated(DensityFunctions.rangeChoice(p_209472_, p_209474_, p_209475_ + 1, p_209473_, DensityFunctions.constant(p_209476_)));
    }

    private static DensityFunction slideOverworld(boolean pAmplified, DensityFunction pDensityFunction) {
        return slide(pDensityFunction, -64, 384, pAmplified ? 16 : 80, pAmplified ? 0 : 64, -0.078125, 0, 24, pAmplified ? 0.4 : 0.1171875);
    }

    private static DensityFunction slide(DensityFunction pDensityFunction, int pMinY, int pMaxY, int p_224447_, int p_224448_, double p_224449_, int p_224450_, int p_224451_, double p_224452_) {
        DensityFunction $$9 = pDensityFunction;
        DensityFunction $$10 = DensityFunctions.yClampedGradient(pMinY + pMaxY - p_224447_, pMinY + pMaxY - p_224448_, 1.0, 0.0);
        $$9 = DensityFunctions.lerp($$10, p_224449_, $$9);
        DensityFunction $$11 = DensityFunctions.yClampedGradient(pMinY + p_224450_, pMinY + p_224451_, 0.0, 1.0);
        $$9 = DensityFunctions.lerp($$11, p_224452_, $$9);
        return $$9;
    }

    private static DensityFunction postProcess(DensityFunction pDensityFunction) {
        DensityFunction $$1 = DensityFunctions.blendDensity(pDensityFunction);
        return DensityFunctions.mul(DensityFunctions.interpolated($$1), DensityFunctions.constant(0.64)).squeeze();
    }
}