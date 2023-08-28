package net.damqn4etobg.endlessexpansion.worldgen.biome.surface;

import net.damqn4etobg.endlessexpansion.block.ModBlocks;
import net.damqn4etobg.endlessexpansion.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

import static net.minecraft.world.level.levelgen.SurfaceRules.abovePreliminarySurface;
import static net.minecraft.world.level.levelgen.SurfaceRules.stoneDepthCheck;

public class ModSurfaceRules {
    public static final SurfaceRules.ConditionSource NEW_DEFAULT_BLOCK = stoneDepthCheck(0, true, 1000, CaveSurface.FLOOR);

    public static final SurfaceRules.RuleSource TITANUM_SOIL = makeStateRule(ModBlocks.TITANUM_SOIL.get());
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource TITANUM_GRASS = makeStateRule(ModBlocks.TITANUM_GRASS_BLOCK.get());

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(abovePreliminarySurface(), GRASS_BLOCK), SurfaceRules.ifTrue(abovePreliminarySurface(), DIRT));

        SurfaceRules.RuleSource titanumSurface =
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), TITANUM_GRASS));


        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.TITANIC_FOREST), titanumSurface)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
