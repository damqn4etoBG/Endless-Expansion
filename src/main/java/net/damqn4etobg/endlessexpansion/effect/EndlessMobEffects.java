package net.damqn4etobg.endlessexpansion.effect;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EndlessMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, EndlessExpansion.MODID);

    public static final Supplier<MobEffect> FREEZING = MOB_EFFECTS.register("freezing", () -> new FreezeEffect(MobEffectCategory.HARMFUL, 3124687));
    public static final Supplier<MobEffect> SHADOW_STATE = MOB_EFFECTS.register("shadow_state", () -> new FreezeEffect(MobEffectCategory.BENEFICIAL, 1315860)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, Identifier.fromNamespaceAndPath(EndlessExpansion.MODID, "shadow_state_speed_buff"), 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
