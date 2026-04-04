package net.damqn4etobg.endlessexpansion.datagen;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;

import java.util.concurrent.CompletableFuture;

public class EndlessBiomeTagsProvider extends BiomeTagsProvider {
    public EndlessBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, EndlessExpansion.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}
