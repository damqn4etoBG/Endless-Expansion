package net.damqn4etobg.endlessexpansion.block;

import net.damqn4etobg.endlessexpansion.sound.EndlessSounds;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class EndlessBlockSetTypes {
    public static final BlockSetType ARBOR = BlockSetType.register(new BlockSetType("arbor", true, true, true, BlockSetType.PressurePlateSensitivity.EVERYTHING, EndlessSounds.ARBOR_WOOD_SOUNDS,
            EndlessSounds.ARBOR_DOOR_CLOSE.get(), // Door close sound
            EndlessSounds.ARBOR_DOOR_OPEN.get(),  // Door open sound
            EndlessSounds.ARBOR_TRAPDOOR_CLOSE.get(), // Trapdoor close sound
            EndlessSounds.ARBOR_TRAPDOOR_OPEN.get(),  // Trapdoor open sound
            EndlessSounds.ARBOR_PRESSURE_PLATE_OFF.get(), // Pressure plate click off
            EndlessSounds.ARBOR_PRESSURE_PLATE_ON.get(),  // Pressure plate click on
            EndlessSounds.ARBOR_BUTTON_OFF.get(),         // Button click off
            EndlessSounds.ARBOR_BUTTON_ON.get()           // Button click on
    ));
}
