package net.damqn4etobg.endlessexpansion.item.custom;

import net.damqn4etobg.endlessexpansion.tag.EndlessTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class PaxelItem extends AxeItem {
    public PaxelItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
        super(material, attackDamage, attackSpeed, properties);

        HolderGetter<Block> holdergetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        properties.component(DataComponents.TOOL, new Tool(List.of(Tool.Rule.deniesDrops(holdergetter.getOrThrow(material.incorrectBlocksForDrops())), Tool.Rule.minesAndDrops(holdergetter.getOrThrow(EndlessTags.Blocks.MINEABLE_WITH_PAXEL), material.speed())), 1.0F, 1, true));
    }
}
