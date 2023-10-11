package net.damqn4etobg.endlessexpansion.tag;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Items {
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(EndlessExpansion.MODID, name));
        }
        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
    public static class Blocks {
        public static final TagKey<Block> NEEDS_COBALT_TOOL = tag("needs_cobalt_tool");
        public static final TagKey<Block> NEEDS_CELESTIAL_TOOL = tag("needs_celestial_tool");
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(EndlessExpansion.MODID, name));
        }
        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Biomes {

        public static final TagKey<Biome> IS_WORLD_BEYOND = tag("is_world_beyond");
        public static final TagKey<Biome> IS_TITANIC_FOREST = tag("is_titanic_forest");
        public static final TagKey<Biome> IS_FROZEN_WASTES= tag("is_frozen_wastes");

        private static TagKey<Biome> tag(String name) {
            return biomeTagCreate(new ResourceLocation(EndlessExpansion.MODID, name));
        }
        private static TagKey<Biome> forgeTag(String name) {
            return biomeTagCreate(new ResourceLocation("forge", name));
        }
    }

    private static TagKey<Biome> biomeTagCreate(ResourceLocation resourceLocation) {
        return TagKey.create(Registries.BIOME, resourceLocation);
    }
}
