package net.damqn4etobg.endlessexpansion.event;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.block.ModBlocks;
import net.damqn4etobg.endlessexpansion.entity.ModEntities;
import net.damqn4etobg.endlessexpansion.entity.custom.AbyssalScourgeEntity;
import net.damqn4etobg.endlessexpansion.entity.custom.ShroomieEntity;
import net.damqn4etobg.endlessexpansion.entity.custom.WraithEntity;
import net.damqn4etobg.endlessexpansion.item.ModItems;
import net.damqn4etobg.endlessexpansion.item.alchemy.ModPotions;
import net.damqn4etobg.endlessexpansion.networking.ModMessages;
import net.damqn4etobg.endlessexpansion.util.BetterBrewingRecipe;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = EndlessExpansion.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModMessages.register();
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.ARBOR_SAPLING.getId(), ModBlocks.POTTED_ARBOR_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.MYSTICAL_EVERBLUE_OCRHID.getId(), ModBlocks.POTTED_MYSTICAL_EVERBLUE_OCRHID);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.BLISSWOOD_SAPLING.getId(), ModBlocks.POTTED_BLISSWOOD_SAPLING);

            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD, ModItems.MYSTICAL_EVERBLUE_POWDER.get(), ModPotions.MYSTICAL_POTION.get()));
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD, ModItems.LUMINITE.get(), ModPotions.GLOWING_POTION.get()));
        });
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.WRAITH.get(), WraithEntity.createAttributes().build());
        event.put(ModEntities.SHROOMIE.get(), ShroomieEntity.createAttributes().build());
        event.put(ModEntities.ABYSSAL_SCOURGE.get(), AbyssalScourgeEntity.createAttributes().build());
    }
}
