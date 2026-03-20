package net.damqn4etobg.endlessexpansion.block.custom;

import net.damqn4etobg.endlessexpansion.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Properties;

public class SapphireClusterBlock extends AmethystClusterBlock {
    public SapphireClusterBlock(int pSize, int pOffset, Properties pProperties) {
        super(pSize, pOffset, pProperties);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        double x = pPos.getX() + pRandom.nextDouble() + 0.25;
        double y = pPos.getY() + pRandom.nextDouble() + 0.125;
        double z = pPos.getZ() + pRandom.nextDouble() + 0.25;

        if(pRandom.nextFloat() < 0.25f) {
            pLevel.addParticle(ModParticles.SPARK.get(), x, y, z, 0d, 0.0125d, 0d);
        }
    }
}
