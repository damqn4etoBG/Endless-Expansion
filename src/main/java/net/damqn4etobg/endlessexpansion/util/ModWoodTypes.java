package net.damqn4etobg.endlessexpansion.util;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.block.ModBlockSetTypes;
import net.damqn4etobg.endlessexpansion.sound.ModSounds;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType ARBOR = WoodType.register(new WoodType(EndlessExpansion.MODID + ":arbor", ModBlockSetTypes.ARBOR, ModSounds.ARBOR_WOOD_SOUNDS, ModSounds.ARBOR_HANGING_SIGN_SOUNDS, ModSounds.ARBOR_FENCE_GATE_CLOSE.get(), ModSounds.ARBOR_FENCE_GATE_OPEN.get()));
    public static final WoodType BLISSWOOD = WoodType.register(new WoodType(EndlessExpansion.MODID + ":blisswood", ModBlockSetTypes.BLISSWOOD, ModSounds.BLISSWOOD_WOOD_SOUNDS, ModSounds.BLISSWOOD_HANGING_SIGN_SOUNDS, ModSounds.BLISSWOOD_FENCE_GATE_CLOSE.get(), ModSounds.BLISSWOOD_FENCE_GATE_OPEN.get()));
}
