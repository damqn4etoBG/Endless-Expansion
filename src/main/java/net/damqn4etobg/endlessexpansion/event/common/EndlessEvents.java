package net.damqn4etobg.endlessexpansion.event.common;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.datagen.*;
import net.damqn4etobg.endlessexpansion.item.IArmorSetBonus;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

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

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);

        if (head.getItem() instanceof IArmorSetBonus bonus && bonus.hasFullSet(player)) bonus.applySetBonus(player, head);
    }
}
