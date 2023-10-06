package net.damqn4etobg.endlessexpansion.effect;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EndlessExpansion.MODID);

    public static final RegistryObject<MobEffect> GROWTH_SPURT = MOB_EFFECTS.register("growth_spurt",
            () -> new GrowthSpurtEffect(MobEffectCategory.BENEFICIAL, 3124687));
    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
