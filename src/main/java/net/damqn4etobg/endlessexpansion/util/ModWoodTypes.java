package net.damqn4etobg.endlessexpansion.util;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType ARBOR = WoodType.register(new WoodType(EndlessExpansion.MODID + ":arbor", BlockSetType.JUNGLE));
}
