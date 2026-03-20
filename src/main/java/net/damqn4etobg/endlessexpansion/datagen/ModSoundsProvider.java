package net.damqn4etobg.endlessexpansion.datagen;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.sound.ModSounds;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class ModSoundsProvider extends SoundDefinitionsProvider {
    protected ModSoundsProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, EndlessExpansion.MODID, helper);
    }

    @Override
    public void registerSounds() {
        add(ModSounds.INFUSER_INFUSING, simpleSound(ModSounds.INFUSER_INFUSING, Component.empty()));
        add(SoundEvents.ANVIL_PLACE, simpleEditedSound(List.of(SoundEvents.ANVIL_PLACE, SoundEvents.ANVIL_PLACE), Component.empty(), 1.25f, 1f));

    }

    private SoundDefinition simpleSound(RegistryObject<SoundEvent> sound, Component subtitle) {
        ResourceLocation soundLocation = ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, ForgeRegistries.SOUND_EVENTS.getKey(sound.get()).getPath());
        return definition().with(sound(soundLocation)).subtitle(subtitle.toString());
    }

    private SoundDefinition simpleSound(String sound, Component subtitle) {
        ResourceLocation soundLocation = ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, sound);
        return definition().with(sound(soundLocation)).subtitle(subtitle.toString());
    }

    private SoundDefinition simpleSound(List<RegistryObject<SoundEvent>> sounds, Component subtitle, float pitch, float volume) {
        List<ResourceLocation> resourceLocations = new ArrayList<>();
        for(int i = 0; i < sounds.size(); i++) {
            ResourceLocation soundLocation = ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, ForgeRegistries.SOUND_EVENTS.getKey(sounds.get(i).get()).getPath());
            resourceLocations.add(soundLocation);
        }

        SoundDefinition definition = definition();
        for (ResourceLocation resourceLocation : resourceLocations) {
            definition.with(sound(resourceLocation).pitch(pitch).volume(volume));
        }

        return definition.subtitle(subtitle.getString());
    }

    private SoundDefinition simpleEditedSound(List<SoundEvent> sounds, Component subtitle, float pitch, float volume) {
        List<ResourceLocation> resourceLocations = new ArrayList<>();
        for(int i = 0; i < sounds.size(); i++) {
            ResourceLocation soundLocation = ResourceLocation.fromNamespaceAndPath("minecraft", ForgeRegistries.SOUND_EVENTS.getKey(sounds.get(i)).getPath());
            resourceLocations.add(soundLocation);
        }

        SoundDefinition definition = definition();
        for (ResourceLocation resourceLocation : resourceLocations) {
            definition.with(sound(resourceLocation).pitch(pitch).volume(volume));
        }

        return definition.subtitle(subtitle.getString());
    }
}
