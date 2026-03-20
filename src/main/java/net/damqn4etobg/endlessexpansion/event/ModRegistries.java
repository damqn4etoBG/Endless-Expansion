package net.damqn4etobg.endlessexpansion.event;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.item.wand.evolution.WandEvolution;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = EndlessExpansion.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistries {
    public static final ResourceKey<Registry<WandEvolution>> WAND_EVOLUTION = createKey("evolution_table/evolutions");

    public static Supplier<IForgeRegistry<WandEvolution>> WAND_EVOLUTION_REGISTRY;

    @SubscribeEvent
    public static void createRegistries(NewRegistryEvent event) {
        WAND_EVOLUTION_REGISTRY = event.create(new RegistryBuilder<WandEvolution>().setMaxID(Integer.MAX_VALUE >> 2).setName(WAND_EVOLUTION.location()));
    }

    private static <T> ResourceKey<Registry<T>> createKey(String name) {
        return ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, name));
    }
}
