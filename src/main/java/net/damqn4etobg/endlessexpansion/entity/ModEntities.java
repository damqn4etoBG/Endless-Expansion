package net.damqn4etobg.endlessexpansion.entity;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.entity.custom.WraithEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EndlessExpansion.MODID);

    public static final RegistryObject<EntityType<WraithEntity>> WRAITH =
            ENTITY_TYPES.register("wraith", () -> EntityType.Builder.of(WraithEntity::new, MobCategory.MONSTER)
                    .sized(1.5f, 1.5f).build("wraith"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
