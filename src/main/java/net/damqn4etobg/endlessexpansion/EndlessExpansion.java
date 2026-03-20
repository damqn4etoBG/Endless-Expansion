package net.damqn4etobg.endlessexpansion;

import com.mojang.logging.LogUtils;
import net.damqn4etobg.endlessexpansion.block.ModBlocks;
import net.damqn4etobg.endlessexpansion.block.entity.ModBlockEntities;
import net.damqn4etobg.endlessexpansion.command.argument.ModArgumentTypes;
import net.damqn4etobg.endlessexpansion.config.EndlessExpansionClientConfig;
import net.damqn4etobg.endlessexpansion.config.EndlessExpansionServerConfig;
import net.damqn4etobg.endlessexpansion.effect.ModMobEffects;
import net.damqn4etobg.endlessexpansion.entity.ModEntities;
import net.damqn4etobg.endlessexpansion.fluid.ModFluidTypes;
import net.damqn4etobg.endlessexpansion.fluid.ModFluids;
import net.damqn4etobg.endlessexpansion.item.ModCreativeModeTabs;
import net.damqn4etobg.endlessexpansion.item.ModItems;
import net.damqn4etobg.endlessexpansion.item.alchemy.ModPotions;
import net.damqn4etobg.endlessexpansion.item.enchantment.ModEnchantments;
import net.damqn4etobg.endlessexpansion.item.wand.evolution.WandEvolutions;
import net.damqn4etobg.endlessexpansion.particle.ModParticles;
import net.damqn4etobg.endlessexpansion.recipe.ModRecipes;
import net.damqn4etobg.endlessexpansion.screen.menu.ModMenuTypes;
import net.damqn4etobg.endlessexpansion.sound.ModSounds;
import net.damqn4etobg.endlessexpansion.worldgen.feature.ModFeatures;
import net.damqn4etobg.endlessexpansion.worldgen.feature.ModTreeFeatures;
import net.damqn4etobg.endlessexpansion.worldgen.feature.placement.ModPlacementModifierTypes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(EndlessExpansion.MODID)
public class EndlessExpansion {
  public static final String MODID = "endlessexpansion";
  public static final Logger LOGGER = LogUtils.getLogger();
  public static final String MOD_VERSION = "§71.20.1-1.6";

  public EndlessExpansion(FMLJavaModLoadingContext context) {
    IEventBus modEventBus = context.getModEventBus();
    MinecraftForge.EVENT_BUS.register(this);

    ModCreativeModeTabs.register(modEventBus);
    ModItems.register(modEventBus);
    ModBlocks.register(modEventBus);
    ModBlockEntities.register(modEventBus);
    ModPotions.register(modEventBus);
    ModMobEffects.register(modEventBus);
    ModEntities.register(modEventBus);
    ModSounds.register(modEventBus);
    ModParticles.register(modEventBus);
    ModEnchantments.register(modEventBus);
    WandEvolutions.register(modEventBus);
    ModMenuTypes.register(modEventBus);
    ModRecipes.register(modEventBus);
    ModFluids.register(modEventBus);
    ModFluidTypes.register(modEventBus);
    ModTreeFeatures.register(modEventBus);
    ModFeatures.register(modEventBus);
    ModPlacementModifierTypes.register(modEventBus);
    ModArgumentTypes.register(modEventBus);

    context.registerConfig(ModConfig.Type.SERVER, EndlessExpansionServerConfig.SERVER_CONFIG);
    context.registerConfig(ModConfig.Type.CLIENT, EndlessExpansionClientConfig.CLIENT_CONFIG);
  }
}
