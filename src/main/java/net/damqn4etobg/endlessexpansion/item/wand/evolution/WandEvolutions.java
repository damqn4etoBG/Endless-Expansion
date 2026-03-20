package net.damqn4etobg.endlessexpansion.item.wand.evolution;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.event.ModRegistries;
import net.damqn4etobg.endlessexpansion.item.ModItems;
import net.damqn4etobg.endlessexpansion.item.wand.WandItem;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class WandEvolutions {
    public static final DeferredRegister<WandEvolution> WAND_EVOLUTIONS = DeferredRegister.create(ModRegistries.WAND_EVOLUTION, EndlessExpansion.MODID);

    public static final RegistryObject<WandEvolution> EXAMPLE = WAND_EVOLUTIONS.register("example", () -> new ExampleEvolution("example"));
    public static final RegistryObject<WandEvolution> ICEBALL = WAND_EVOLUTIONS.register("iceball", () -> new IceballEvolution("iceball"));
    public static final RegistryObject<WandEvolution> ICEBALL_TWO = WAND_EVOLUTIONS.register("iceball_two", () -> new IceballTwoEvolution("iceball_two"));
    public static final RegistryObject<WandEvolution> FIREBALL = WAND_EVOLUTIONS.register("fireball", () -> new FireballEvolution("fireball"));

    public static void register(IEventBus eventBus) {
        WAND_EVOLUTIONS.register(eventBus);
    }

    public static Collection<WandEvolution> getWandEvolutions(Item wand) {
        if(listExistsForWand(wand)) {
            ResourceLocation id = ForgeRegistries.ITEMS.getKey(wand);
            if(id == null) return List.of();
            ResourceLocation key = ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, id.getPath());
            List<ResourceLocation> evoIds = WandEvolutionLoader.ITEM_TO_EVOLUTION_IDS.getOrDefault(key, List.of());
            return evoIds.stream().map(WandEvolutionLoader.LOADED_EVOLUTIONS::get).filter(Objects::nonNull).toList();
        } else {
            return null;
        }
    }

    public static Collection<WandEvolution> getWandEvolutions(ItemStack stack) {
        return getWandEvolutions(stack.getItem());
    }

    public static WandEvolution getFromId(String id) {
        return ModRegistries.WAND_EVOLUTION_REGISTRY.get().getValue(ResourceLocation.parse(id));
    }

    public static boolean listExistsForWand(Item wand) {
        ResourceLocation wandID = ForgeRegistries.ITEMS.getKey(wand);
        if(wandID == null) return false;
        ResourceLocation fileLoc = ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "evolution_table/" + wandID.getPath() + ".json");
        return Minecraft.getInstance().getResourceManager().getResource(fileLoc).isPresent();
    }

    public static Collection<String> getAllEvolutionLocations() {
        Collection<String> evolutions = new ArrayList<>();
        for(RegistryObject<WandEvolution> object : WAND_EVOLUTIONS.getEntries()) {
            evolutions.add(object.getId().toString());
        }
        return evolutions;
    }

    public static Collection<String> getAllWands() {
        Collection<String> wands = new ArrayList<>();
        for(RegistryObject<Item> object : ModItems.ITEMS.getEntries()) {
            if(object.get() instanceof WandItem item) {
                ResourceLocation id = ForgeRegistries.ITEMS.getKey(item);
                if(id != null) wands.add(id.toString());
            }
        }
        return wands;
    }
}
