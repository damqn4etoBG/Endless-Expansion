package net.damqn4etobg.endlessexpansion.worldgen.feature;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.damqn4etobg.endlessexpansion.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.SingleThreadedRandomSource;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;


public class VerticalWaterLineFeature extends Feature<NoneFeatureConfiguration> {
    public VerticalWaterLineFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    long worldSeed;
    final RandomSource random = new SingleThreadedRandomSource(worldSeed);

    final int amplitude = random.nextInt(3, 7);
    final float frequency1 = 0.01f; // lower frequency = smoother transitions
    final float frequency2 = 0.035f;

    final PerlinSimplexNoise noise = new PerlinSimplexNoise(random, ImmutableList.of(0, 1, 2)); // 0-4 was last one, lower octaves mean improved performance

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        BlockPos pos = pContext.origin();
        LevelAccessor world = pContext.level();
        worldSeed = pContext.level().getSeed();

        // Use Perlin noise to calculate the height offset based on x and z positions
        double noiseValue1 = noise.getValue(pos.getX() * frequency1, pos.getZ() * frequency1, false);
        double noiseValue2 = noise.getValue(pos.getX() * frequency2, pos.getZ() * frequency2, false);

        // Sum the noise values and apply amplitude
        int height = (int) (-55 + (noiseValue1 + noiseValue2) * amplitude);

        for (int y = 62; y >= height; y--) {
            if (y > -63) {
                BlockPos currentPos = new BlockPos(pos.getX(), y, pos.getZ());
                BlockState waterState = Blocks.WATER.defaultBlockState();
                world.setBlock(currentPos, waterState, 3);
            }
            if(height > -63) {
                BlockPos groundBlockPos = new BlockPos(pos.getX(), height - 1, pos.getZ());
                BlockPos lavaPos = new BlockPos(pos.getX(), height - 2, pos.getZ());
                BlockState groundBlockState = ModBlocks.ABYSSAL_SILT.get().defaultBlockState();

                if(world.getBlockState(lavaPos) == Blocks.LAVA.defaultBlockState()) {
                    world.setBlock(groundBlockPos, Blocks.MAGMA_BLOCK.defaultBlockState(), 3);
                } else {
                    world.setBlock(groundBlockPos, groundBlockState, 3);
                    world.setBlock(lavaPos, groundBlockState, 3); // 2 layers of abyssal silt
                }
            }
            if(random.nextFloat() < 0.0025f) {
                if(height > -63) {
                    BlockPos sapphirePos = new BlockPos(pos.getX(), height, pos.getZ());
                    BlockState sapphireState = ModBlocks.SAPPHIRE_CLUSTER.get().defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true);
                    world.setBlock(sapphirePos, sapphireState, 3);
                }
            }
        }
        return true;
    }
}
