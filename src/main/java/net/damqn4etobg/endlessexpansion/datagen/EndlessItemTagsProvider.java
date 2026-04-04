package net.damqn4etobg.endlessexpansion.datagen;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.tag.EndlessTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

import static net.damqn4etobg.endlessexpansion.item.EndlessItems.*;

public class EndlessItemTagsProvider extends ItemTagsProvider {
    public EndlessItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, EndlessExpansion.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EndlessTags.Items.LUMINITE_INGOT).add(LUMINITE_INGOT.get());
        tag(EndlessTags.Items.COBALT_INGOT).add(COBALT_INGOT.get());
        tag(EndlessTags.Items.FLAMMATINE_INGOT).add(FLAMMATINE_INGOT.get());
        tag(EndlessTags.Items.SHADOWSTEEL_INGOT).add(SHADOWSTEEL_INGOT.get());
    }
}
