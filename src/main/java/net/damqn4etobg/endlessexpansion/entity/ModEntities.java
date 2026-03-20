package net.damqn4etobg.endlessexpansion.entity;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.entity.custom.*;
import net.damqn4etobg.endlessexpansion.entity.projectile.CobaltBolt;
import net.damqn4etobg.endlessexpansion.entity.projectile.MysticalCobaltBolt;
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
                    .sized(0.75f, 2.25f).fireImmune().build("wraith"));

    public static final RegistryObject<EntityType<ArborBoatEntity>> ARBOR_BOAT =
            ENTITY_TYPES.register("arbor_boat", () -> EntityType.Builder.<ArborBoatEntity>of(ArborBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("arbor_boat"));

    public static final RegistryObject<EntityType<ArborChestBoatEntity>> ARBOR_CHEST_BOAT =
            ENTITY_TYPES.register("arbor_chest_boat", () -> EntityType.Builder.<ArborChestBoatEntity>of(ArborChestBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("arbor_chest_boat"));

    public static final RegistryObject<EntityType<BlisswoodBoatEntity>> BLISSWOOD_BOAT =
            ENTITY_TYPES.register("blisswood_boat", () -> EntityType.Builder.<BlisswoodBoatEntity>of(BlisswoodBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("blisswood_boat"));

    public static final RegistryObject<EntityType<BlisswoodChestBoatEntity>> BLISSWOOD_CHEST_BOAT =
            ENTITY_TYPES.register("blisswood_chest_boat", () -> EntityType.Builder.<BlisswoodChestBoatEntity>of(BlisswoodChestBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("blisswood_chest_boat"));

    public static final RegistryObject<EntityType<CobaltBolt>> COBALT_BOLT =
            ENTITY_TYPES.register("cobalt_bolt", () -> EntityType.Builder.<CobaltBolt>of(CobaltBolt::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).clientTrackingRange(4).updateInterval(20).fireImmune().build("cobalt_bolt"));

    public static final RegistryObject<EntityType<MysticalCobaltBolt>> MYSTICAL_COBALT_BOLT =
            ENTITY_TYPES.register("mystical_cobalt_bolt", () -> EntityType.Builder.<MysticalCobaltBolt>of(MysticalCobaltBolt::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).clientTrackingRange(4).updateInterval(20).fireImmune().build("mystical_cobalt_bolt"));

    public static final RegistryObject<EntityType<ShroomieEntity>> SHROOMIE =
            ENTITY_TYPES.register("shroomie", () -> EntityType.Builder.of(ShroomieEntity::new, MobCategory.CREATURE)
                    .sized(0.5f, 1.5f).build("shroomie"));

    public static final RegistryObject<EntityType<AbyssalScourgeEntity>> ABYSSAL_SCOURGE =
            ENTITY_TYPES.register("abyssal_scourge", () -> EntityType.Builder.of(AbyssalScourgeEntity::new, MobCategory.MONSTER)
                    .sized(2.5f, 1.5f).build("abyssal_scourge"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
