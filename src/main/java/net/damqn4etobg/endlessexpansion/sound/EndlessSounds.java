package net.damqn4etobg.endlessexpansion.sound;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EndlessSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, EndlessExpansion.MODID);

    public static final Supplier<SoundEvent> INFUSER_INFUSING = registerSoundEvent("block.infuser.infusing");
    public static final Supplier<SoundEvent> DASH = registerSoundEvent("ambient.dash");
    public static final Supplier<SoundEvent> DASH_INDICATOR = registerSoundEvent("ambient.dash_indicator");
    public static final DeferredSoundType ARBOR_WOOD_SOUNDS = new DeferredSoundType(1f, 0.85f, () -> SoundEvents.WOOD_BREAK, () -> SoundEvents.WOOD_STEP, () -> SoundEvents.WOOD_PLACE, () -> SoundEvents.WOOD_HIT, () -> SoundEvents.WOOD_FALL);
    public static final DeferredSoundType ARBOR_HANGING_SIGN_SOUNDS = new DeferredSoundType(1f, 0.85f, () -> SoundEvents.HANGING_SIGN_BREAK, () -> SoundEvents.HANGING_SIGN_STEP, () -> SoundEvents.HANGING_SIGN_PLACE, () -> SoundEvents.HANGING_SIGN_HIT, () -> SoundEvents.HANGING_SIGN_FALL);
    public static final Supplier<SoundEvent> ARBOR_DOOR_CLOSE = registerSoundEvent("block.arbor_door.close");
    public static final Supplier<SoundEvent> ARBOR_DOOR_OPEN = registerSoundEvent("block.arbor_door.open");
    public static final Supplier<SoundEvent> ARBOR_TRAPDOOR_OPEN = registerSoundEvent("block.arbor_trapdoor.open");
    public static final Supplier<SoundEvent> ARBOR_TRAPDOOR_CLOSE = registerSoundEvent("block.arbor_trapdoor.close");
    public static final Supplier<SoundEvent> ARBOR_PRESSURE_PLATE_ON = registerSoundEvent("block.arbor_pressure_plate.on");
    public static final Supplier<SoundEvent> ARBOR_PRESSURE_PLATE_OFF = registerSoundEvent("block.arbor_pressure_plate.off");
    public static final Supplier<SoundEvent> ARBOR_BUTTON_ON = registerSoundEvent("block.arbor_button.on");
    public static final Supplier<SoundEvent> ARBOR_BUTTON_OFF = registerSoundEvent("block.arbor_button.off");
    public static final Supplier<SoundEvent> ARBOR_FENCE_GATE_CLOSE = registerSoundEvent("block.arbor_fence_gate.close");
    public static final Supplier<SoundEvent> ARBOR_FENCE_GATE_OPEN = registerSoundEvent("block.arbor_fence_gate.open");
    public static final Supplier<SoundEvent> SHROOMIE_HURT = registerSoundEvent("entity.shroomie.hurt");
    public static final Supplier<SoundEvent> SHROOMIE_DEATH = registerSoundEvent("entity.shroomie.death");
    public static final Supplier<SoundEvent> ARMOR_EQUIP_SHADOWSTEEL = registerSoundEvent("item.armor.equip_shadowsteel");
    public static final Supplier<SoundEvent> WRAITH_HURT = registerSoundEvent("entity.wraith.hurt");
    public static final Supplier<SoundEvent> WRAITH_DEATH = registerSoundEvent("entity.wraith.death");
    public static final DeferredSoundType PACKED_SNOW_SOUNDS = new DeferredSoundType(1f, 0.75f, () -> SoundEvents.SNOW_BREAK, () -> SoundEvents.SNOW_STEP, () -> SoundEvents.SNOW_PLACE, () -> SoundEvents.SNOW_HIT, () -> SoundEvents.SNOW_FALL);

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
