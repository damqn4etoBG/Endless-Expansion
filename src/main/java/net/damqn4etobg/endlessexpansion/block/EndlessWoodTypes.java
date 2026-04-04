package net.damqn4etobg.endlessexpansion.block;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.sound.EndlessSounds;
import net.minecraft.world.level.block.state.properties.WoodType;

public class EndlessWoodTypes {
    public static final WoodType ARBOR = WoodType.register(new WoodType(EndlessExpansion.MODID + ":arbor", EndlessBlockSetTypes.ARBOR, EndlessSounds.ARBOR_WOOD_SOUNDS, EndlessSounds.ARBOR_HANGING_SIGN_SOUNDS, EndlessSounds.ARBOR_FENCE_GATE_CLOSE.get(), EndlessSounds.ARBOR_FENCE_GATE_OPEN.get()));
}
