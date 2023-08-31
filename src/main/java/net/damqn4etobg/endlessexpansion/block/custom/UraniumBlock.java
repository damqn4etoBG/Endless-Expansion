package net.damqn4etobg.endlessexpansion.block.custom;

import net.damqn4etobg.endlessexpansion.util.AbstractRadialBlock;
import net.damqn4etobg.endlessexpansion.util.TickableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;

public class UraniumBlock extends AbstractRadialBlock {
    public UraniumBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected int getRadius() {
        return 5;
    }

    @Override
    public void CheckRadius(Level level, Player player, BlockPos pos) {
        player.lavaHurt();
        super.CheckRadius(level, player, pos);
    }

    public void randomTick(Level level, BlockPos pos, RandomSource source) {

    }
}
