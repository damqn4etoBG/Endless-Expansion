package net.damqn4etobg.endlessexpansion.item;

import net.damqn4etobg.endlessexpansion.tag.EndlessTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

public class EndlessToolMaterials {
    public static final ToolMaterial LUMINITE = new ToolMaterial(
            // Cannot break this material
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            415,
            6f,
            2f,
            14,
            // repair material
            EndlessTags.Items.LUMINITE_INGOT
    );
    public static final ToolMaterial COBALT = new ToolMaterial(EndlessTags.Blocks.INCORRECT_FOR_COBALT_TOOL, 1137, 7f, 2.5f, 14, EndlessTags.Items.COBALT_INGOT);
    public static final ToolMaterial FLAMMATINE = new ToolMaterial(EndlessTags.Blocks.INCORRECT_FOR_FLAMMATINE_TOOL, 1872, 8f, 3.5f, 15, EndlessTags.Items.FLAMMATINE_INGOT);
    public static final ToolMaterial SHADOWSTEEL = new ToolMaterial(EndlessTags.Blocks.INCORRECT_FOR_SHADOWSTEEL_TOOL, 2257, 9f, 4.25f, 16, EndlessTags.Items.SHADOWSTEEL_INGOT);
}
