package net.damqn4etobg.endlessexpansion.item.wand.evolution;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.capability.wand.EvolutionDataProvider;
import net.damqn4etobg.endlessexpansion.networking.ModMessages;
import net.damqn4etobg.endlessexpansion.networking.packet.EvolutionDataSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class PlayerWandData {
    public static void updateHighestEvolution(Player player, String wandID, String newEvolutionID) {
        player.getCapability(EvolutionDataProvider.EVOLUTION_DATA).ifPresent(data -> {
            String current = data.getHighestEvolution(wandID);
            if(current.isEmpty() || isHighestEvolution(wandID, current, newEvolutionID)) {
                data.setHighestEvolution(wandID, newEvolutionID);
                if(player instanceof ServerPlayer serverPlayer) {
                    ModMessages.sendToPlayer(new EvolutionDataSyncS2CPacket(data.serializeNBT()), serverPlayer);
                }
            }
        });
    }

    public static String getHighestEvolution(Player player, String wandID) {
        AtomicReference<String> evo = new AtomicReference<>("");
        player.getCapability(EvolutionDataProvider.EVOLUTION_DATA).ifPresent(data -> evo.set(data.getHighestEvolution(wandID)));
        return evo.get();
    }

    public static Collection<String> getEvolutionsBeforeHighest(Player player, String wandID) {
        Collection<String> evolutions = new ArrayList<>();
        Map<ResourceLocation, Integer> map = WandEvolutionLoader.EVOLUTION_TO_INDEX.get(ResourceLocation.parse(wandID));
        if (map == null) {
            EndlessExpansion.LOGGER.error("Evolutions before highest map is null!");
            return evolutions;
        }

        player.getCapability(EvolutionDataProvider.EVOLUTION_DATA).ifPresent(data -> {
            String highestStr = data.getHighestEvolution(wandID);
            if (highestStr.isEmpty()) {
                EndlessExpansion.LOGGER.error("Highest evolution is null!");
                return;
            }

            ResourceLocation highestEvo = ResourceLocation.parse(highestStr);
            Integer highestIndex = map.get(highestEvo);
            if (highestIndex == null) {
                EndlessExpansion.LOGGER.error("Highest evolution index is null!");
                return;
            }

            for(Map.Entry<ResourceLocation, Integer> entry : map.entrySet()) {
                if(entry.getValue() < highestIndex) {
                    evolutions.add(entry.getKey().toString());
                }
            }
        });
        return evolutions;
    }

    public static boolean isHighestEvolution(String wandID, String currentID, String newID) {
        int currentEvolution = WandEvolutionLoader.EVOLUTION_TO_INDEX.get(ResourceLocation.parse(wandID)).get(ResourceLocation.parse(currentID));
        int newEvolution = WandEvolutionLoader.EVOLUTION_TO_INDEX.get(ResourceLocation.parse(wandID)).get(ResourceLocation.parse(newID));
        return newEvolution > currentEvolution;
    }
}
