package net.damqn4etobg.endlessexpansion.datagen;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.tag.EndlessTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class EndlessBlockTagsProvider extends BlockTagsProvider {
    public EndlessBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, EndlessExpansion.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EndlessTags.Blocks.MINEABLE_WITH_PAXEL).addTag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(BlockTags.MINEABLE_WITH_AXE).addTag(BlockTags.MINEABLE_WITH_SHOVEL);
    }
}
