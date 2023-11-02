package net.damqn4etobg.endlessexpansion.entity;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.entity.custom.ArborBoatEntity;
import net.damqn4etobg.endlessexpansion.entity.custom.ArborChestBoatEntity;
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
                    .sized(1.5f, 1.5f).fireImmune().build("wraith"));

    public static final RegistryObject<EntityType<ArborBoatEntity>> ARBOR_BOAT =
            ENTITY_TYPES.register("arbor_boat", () -> EntityType.Builder.<ArborBoatEntity>of(ArborBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_boat"));

    public static final RegistryObject<EntityType<ArborChestBoatEntity>> ARBOR_CHEST_BOAT =
            ENTITY_TYPES.register("arbor_chest_boat", () -> EntityType.Builder.<ArborChestBoatEntity>of(ArborChestBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("arbor_chest_boat"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
