package net.damqn4etobg.endlessexpansion.block;

import net.damqn4etobg.endlessexpansion.sound.ModSounds;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModBlockSetTypes {
    public static final BlockSetType ARBOR = BlockSetType.register(new BlockSetType("arbor", true, ModSounds.ARBOR_WOOD_SOUNDS,
            ModSounds.ARBOR_DOOR_OPEN.get(), // Door close sound
            ModSounds.ARBOR_DOOR_CLOSE.get(),  // Door open sound
            ModSounds.ARBOR_TRAPDOOR_OPEN.get(), // Trapdoor close sound
            ModSounds.ARBOR_TRAPDOOR_CLOSE.get(),  // Trapdoor open sound
            ModSounds.ARBOR_PRESSURE_PLATE_ON.get(), // Pressure plate click off
            ModSounds.ARBOR_PRESSURE_PLATE_OFF.get(),  // Pressure plate click on
            ModSounds.ARBOR_BUTTON_ON.get(),         // Button click off
            ModSounds.ARBOR_BUTTON_OFF.get()           // Button click on
    ));

    public static final BlockSetType BLISSWOOD = BlockSetType.register(new BlockSetType("blisswood", true, ModSounds.BLISSWOOD_WOOD_SOUNDS,
            ModSounds.BLISSWOOD_DOOR_OPEN.get(), // Door close sound
            ModSounds.BLISSWOOD_DOOR_CLOSE.get(),  // Door open sound
            ModSounds.BLISSWOOD_TRAPDOOR_OPEN.get(), // Trapdoor close sound
            ModSounds.BLISSWOOD_TRAPDOOR_CLOSE.get(),  // Trapdoor open sound
            ModSounds.BLISSWOOD_PRESSURE_PLATE_ON.get(), // Pressure plate click off
            ModSounds.BLISSWOOD_PRESSURE_PLATE_OFF.get(),  // Pressure plate click on
            ModSounds.BLISSWOOD_BUTTON_ON.get(),         // Button click off
            ModSounds.BLISSWOOD_BUTTON_OFF.get()           // Button click on
    ));
}
