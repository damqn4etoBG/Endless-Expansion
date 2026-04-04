package net.damqn4etobg.endlessexpansion.event;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.datagen.*;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;

@EventBusSubscriber(modid = EndlessExpansion.MODID)
public class EndlessEvents {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(EndlessModelsProvider::new);
        event.getGenerator().addProvider(true, new LootTableProvider(event.getGenerator().getPackOutput(), Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(EndlessLootTableProvider::new, LootContextParamSets.BLOCK)), event.getLookupProvider()));

        // TAGS
        event.getGenerator().addProvider(true, new EndlessItemTagsProvider(event.getGenerator().getPackOutput(), event.getLookupProvider()));
        event.getGenerator().addProvider(true, new EndlessBlockTagsProvider(event.getGenerator().getPackOutput(), event.getLookupProvider()));
        event.getGenerator().addProvider(true, new EndlessBiomeTagsProvider(event.getGenerator().getPackOutput(), event.getLookupProvider()));
    }
}
