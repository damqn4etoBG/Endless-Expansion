package net.damqn4etobg.endlessexpansion.item.alchemy;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.effect.ModMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, EndlessExpansion.MODID);
    public static final RegistryObject<Potion> TEST = POTIONS.register("test",
            () -> new Potion(new MobEffectInstance(ModMobEffects.GROWTH_SPURT.get(), 3600)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
