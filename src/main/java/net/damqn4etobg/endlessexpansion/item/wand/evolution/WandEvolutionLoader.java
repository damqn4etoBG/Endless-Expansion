package net.damqn4etobg.endlessexpansion.item.wand.evolution;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.event.ModRegistries;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WandEvolutionLoader {
    public static final Map<ResourceLocation, WandEvolution> LOADED_EVOLUTIONS = new HashMap<>();
    public static final Map<ResourceLocation, List<ResourceLocation>> ITEM_TO_EVOLUTION_IDS = new HashMap<>();
    public static final Map<ResourceLocation, Map<ResourceLocation, Integer>> EVOLUTION_TO_INDEX = new HashMap<>();

    public static void loadResources() {
        Minecraft minecraft = Minecraft.getInstance();
        ResourceManager manager = minecraft.getResourceManager();
        try {
            for(var entry : manager.listResources("evolution_table/evolutions", path -> path.getPath().endsWith(".json")).entrySet()) {
                ResourceLocation id = entry.getKey();
                try (InputStream stream = entry.getValue().open()) {
                    JsonObject json = JsonParser.parseReader(new InputStreamReader(stream)).getAsJsonObject();
                    WandEvolution evo = WandEvolution.fromJson(id, json);



                    String[] pathParts = id.getPath().split("/");
                    String baseId = pathParts[pathParts.length - 1].replace(".json", "");
                    ResourceLocation actualId = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), baseId);
                    LOADED_EVOLUTIONS.put(actualId, evo);

                    // transferring the data from the json to the registered entry
                    WandEvolution registered = ModRegistries.WAND_EVOLUTION_REGISTRY.get().getValue(actualId);

                    registered.setName(evo.getName());
                    registered.setDescription(evo.getDescription());
                    registered.setIcon(evo.getIcon());
                    registered.setIconTexture(evo.getIconTexture());
                    registered.setXpCost(evo.getXpCost());
                    registered.setCooldown(evo.getCooldown());
                } catch (Exception e) {
                    EndlessExpansion.LOGGER.error("Failed to parse evolution file {}", id, e);
                }
            }
            EndlessExpansion.LOGGER.info("Loaded evolutions {}", LOADED_EVOLUTIONS.keySet());

            for(var entry : manager.listResources("evolution_table", path -> path.getPath().endsWith(".json") && !path.getPath().contains("evolutions")).entrySet()) {
                ResourceLocation id = entry.getKey();
                try (InputStream stream = entry.getValue().open()) {
                    JsonObject json = JsonParser.parseReader(new InputStreamReader(stream)).getAsJsonObject();
                    JsonArray evos = json.getAsJsonArray("evolutions");

                    List<ResourceLocation> list = new ArrayList<>();
                    Map<ResourceLocation, Integer> map = new HashMap<>();
                    String[] pathParts = id.getPath().split("/");
                    String baseId = pathParts[pathParts.length - 1].replace(".json", "");
                    ResourceLocation actualId = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), baseId);
                    int i = 0;

                    for(JsonElement elem : evos) {
                        list.add(ResourceLocation.parse(elem.getAsString()));
                        i++;
                        map.put(ResourceLocation.parse(elem.getAsString()), i);
                    }
                    ITEM_TO_EVOLUTION_IDS.put(actualId, list);
                    EVOLUTION_TO_INDEX.put(actualId, map);
                    EndlessExpansion.LOGGER.info("Mapped item {} to evolutions {}", actualId, list);
                    //EndlessExpansion.LOGGER.info("Item {} is with map {}", actualId, map);
                } catch (Exception e) {
                    EndlessExpansion.LOGGER.error("Failed to parse wand mapping file {}", id, e);
                }
            }
        } catch (Exception e) {
            EndlessExpansion.LOGGER.error("Error during evolution loading", e);
        }
    }
}
