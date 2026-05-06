package net.damqn4etobg.endlessexpansion.tag;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class EndlessTags {
    public static class Items {
        public static final TagKey<Item> LUMINITE_INGOT = tag("luminite_ingot");
        public static final TagKey<Item> COBALT_INGOT = tag("cobalt_ingot");
        public static final TagKey<Item> FLAMMATINE_INGOT = tag("flammatine_ingot");
        public static final TagKey<Item> SHADOWSTEEL_INGOT = tag("shadowsteel_ingot");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(Identifier.fromNamespaceAndPath(EndlessExpansion.MODID, name));
        }
    }

    public static class Blocks {
        public static final TagKey<Block> INCORRECT_FOR_COBALT_TOOL = tag("incorrect_for_cobalt_tool");
        public static final TagKey<Block> INCORRECT_FOR_FLAMMATINE_TOOL = tag("incorrect_for_flammatine_tool");
        public static final TagKey<Block> INCORRECT_FOR_SHADOWSTEEL_TOOL = tag("incorrect_for_shadowsteel_tool");
        public static final TagKey<Block> MINEABLE_WITH_PAXEL = tag("mineable/paxel");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(Identifier.fromNamespaceAndPath(EndlessExpansion.MODID, name));
        }
    }

    public static class Biomes {

    }
}
