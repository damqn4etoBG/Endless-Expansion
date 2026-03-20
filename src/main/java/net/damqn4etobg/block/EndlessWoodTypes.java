package net.damqn4etobg.block;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class EndlessWoodTypes {
    public static final WoodType ARBOR = WoodType.register(new WoodType(EndlessExpansion.MODID + ":arbor", EndlessBlockSetTypes.ARBOR, SoundType.WOOD, SoundType.BAMBOO_WOOD_HANGING_SIGN, SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN));
}
