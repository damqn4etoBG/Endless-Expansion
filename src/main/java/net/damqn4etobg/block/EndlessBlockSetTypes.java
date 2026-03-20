package net.damqn4etobg.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class EndlessBlockSetTypes {
    public static final BlockSetType ARBOR = BlockSetType.register(new BlockSetType("arbor", true, true, true, BlockSetType.PressurePlateSensitivity.EVERYTHING, SoundType.WOOD,
            SoundEvents.WOODEN_DOOR_CLOSE, // Door close sound
            SoundEvents.WOODEN_DOOR_CLOSE,  // Door open sound
            SoundEvents.WOODEN_DOOR_CLOSE, // Trapdoor close sound
            SoundEvents.WOODEN_DOOR_CLOSE,  // Trapdoor open sound
            SoundEvents.WOODEN_DOOR_CLOSE, // Pressure plate click off
            SoundEvents.WOODEN_DOOR_CLOSE,  // Pressure plate click on
            SoundEvents.WOODEN_DOOR_CLOSE,         // Button click off
            SoundEvents.WOODEN_DOOR_CLOSE           // Button click on
    ));
}
