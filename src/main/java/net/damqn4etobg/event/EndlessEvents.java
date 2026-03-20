package net.damqn4etobg.event;

import net.damqn4etobg.datagen.EndlessModelsProvider;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = EndlessExpansion.MODID)
public class EndlessEvents {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(EndlessModelsProvider::new);
    }
}
