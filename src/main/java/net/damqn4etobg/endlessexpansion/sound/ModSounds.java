package net.damqn4etobg.endlessexpansion.sound;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EndlessExpansion.MODID);

    public static final RegistryObject<SoundEvent> INFUSER_INFUSING = registerSoundEvent("block.infuser.infusing");
    public static final RegistryObject<SoundEvent> DASH = registerSoundEvent("ambient.dash");
    public static final RegistryObject<SoundEvent> DASH_INDICATOR = registerSoundEvent("ambient.dash_indicator");
    public static final ForgeSoundType ARBOR_WOOD_SOUNDS = new ForgeSoundType(1f, 0.85f, () -> SoundEvents.WOOD_BREAK, () -> SoundEvents.WOOD_STEP, () -> SoundEvents.WOOD_PLACE, () -> SoundEvents.WOOD_HIT, () -> SoundEvents.WOOD_FALL);
    public static final ForgeSoundType ARBOR_HANGING_SIGN_SOUNDS = new ForgeSoundType(1f, 0.85f, () -> SoundEvents.HANGING_SIGN_BREAK, () -> SoundEvents.HANGING_SIGN_STEP, () -> SoundEvents.HANGING_SIGN_PLACE, () -> SoundEvents.HANGING_SIGN_HIT, () -> SoundEvents.HANGING_SIGN_FALL);
    public static final ForgeSoundType BLISSWOOD_WOOD_SOUNDS = new ForgeSoundType(1f, 1.25f, () -> SoundEvents.CHERRY_WOOD_BREAK, () -> SoundEvents.CHERRY_WOOD_STEP, () -> SoundEvents.CHERRY_WOOD_PLACE, () -> SoundEvents.CHERRY_WOOD_HIT, () -> SoundEvents.CHERRY_WOOD_FALL);
    public static final ForgeSoundType BLISSWOOD_HANGING_SIGN_SOUNDS = new ForgeSoundType(1f, 1.25f, () -> SoundEvents.CHERRY_WOOD_HANGING_SIGN_BREAK, () -> SoundEvents.CHERRY_WOOD_HANGING_SIGN_STEP, () -> SoundEvents.CHERRY_WOOD_HANGING_SIGN_PLACE, () -> SoundEvents.CHERRY_WOOD_HANGING_SIGN_HIT, () -> SoundEvents.CHERRY_WOOD_HANGING_SIGN_FALL);
    public static final ForgeSoundType BLISSWOOD_SAPLING_SOUNDS = new ForgeSoundType(1f, 1.25f, () -> SoundEvents.CHERRY_SAPLING_BREAK, () -> SoundEvents.CHERRY_SAPLING_STEP, () -> SoundEvents.CHERRY_SAPLING_PLACE, () -> SoundEvents.CHERRY_SAPLING_HIT, () -> SoundEvents.CHERRY_SAPLING_FALL);
    public static final RegistryObject<SoundEvent> ARBOR_DOOR_CLOSE = registerSoundEvent("block.arbor_door.close");
    public static final RegistryObject<SoundEvent> ARBOR_DOOR_OPEN = registerSoundEvent("block.arbor_door.open");
    public static final RegistryObject<SoundEvent> ARBOR_TRAPDOOR_OPEN = registerSoundEvent("block.arbor_trapdoor.open");
    public static final RegistryObject<SoundEvent> ARBOR_TRAPDOOR_CLOSE = registerSoundEvent("block.arbor_trapdoor.close");
    public static final RegistryObject<SoundEvent> ARBOR_PRESSURE_PLATE_ON = registerSoundEvent("block.arbor_pressure_plate.on");
    public static final RegistryObject<SoundEvent> ARBOR_PRESSURE_PLATE_OFF = registerSoundEvent("block.arbor_pressure_plate.off");
    public static final RegistryObject<SoundEvent> ARBOR_BUTTON_ON = registerSoundEvent("block.arbor_button.on");
    public static final RegistryObject<SoundEvent> ARBOR_BUTTON_OFF = registerSoundEvent("block.arbor_button.off");
    public static final RegistryObject<SoundEvent> ARBOR_FENCE_GATE_CLOSE = registerSoundEvent("block.arbor_fence_gate.close");
    public static final RegistryObject<SoundEvent> ARBOR_FENCE_GATE_OPEN = registerSoundEvent("block.arbor_fence_gate.open");
    public static final RegistryObject<SoundEvent> BLISSWOOD_DOOR_CLOSE = registerSoundEvent("block.blisswood_door.close");
    public static final RegistryObject<SoundEvent> BLISSWOOD_DOOR_OPEN = registerSoundEvent("block.blisswood_door.open");
    public static final RegistryObject<SoundEvent> BLISSWOOD_TRAPDOOR_OPEN = registerSoundEvent("block.blisswood_trapdoor.open");
    public static final RegistryObject<SoundEvent> BLISSWOOD_TRAPDOOR_CLOSE = registerSoundEvent("block.blisswood_trapdoor.close");
    public static final RegistryObject<SoundEvent> BLISSWOOD_PRESSURE_PLATE_ON = registerSoundEvent("block.blisswood_pressure_plate.on");
    public static final RegistryObject<SoundEvent> BLISSWOOD_PRESSURE_PLATE_OFF = registerSoundEvent("block.blisswood_pressure_plate.off");
    public static final RegistryObject<SoundEvent> BLISSWOOD_BUTTON_ON = registerSoundEvent("block.blisswood_button.on");
    public static final RegistryObject<SoundEvent> BLISSWOOD_BUTTON_OFF = registerSoundEvent("block.blisswood_button.off");
    public static final RegistryObject<SoundEvent> BLISSWOOD_FENCE_GATE_CLOSE = registerSoundEvent("block.blisswood_fence_gate.close");
    public static final RegistryObject<SoundEvent> BLISSWOOD_FENCE_GATE_OPEN = registerSoundEvent("block.blisswood_fence_gate.open");
    public static final RegistryObject<SoundEvent> SHOORMIE_HURT = registerSoundEvent("entity.shroomie.hurt");
    public static final RegistryObject<SoundEvent> SHOORMIE_DEATH = registerSoundEvent("entity.shroomie.death");
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_SHADOWSTEEL = registerSoundEvent("item.armor.equip_shadowsteel");
    public static final RegistryObject<SoundEvent> WRAITH_HURT = registerSoundEvent("entity.wraith.hurt");
    public static final RegistryObject<SoundEvent> WRAITH_DEATH = registerSoundEvent("entity.wraith.death");
    public static final ForgeSoundType PACKED_SNOW_SOUNDS = new ForgeSoundType(1f, 0.75f, () -> SoundEvents.SNOW_BREAK, () -> SoundEvents.SNOW_STEP, () -> SoundEvents.SNOW_PLACE, () -> SoundEvents.SNOW_HIT, () -> SoundEvents.SNOW_FALL);

    public static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
