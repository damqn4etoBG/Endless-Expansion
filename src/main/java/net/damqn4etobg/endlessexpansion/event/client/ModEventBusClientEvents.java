package net.damqn4etobg.endlessexpansion.event.client;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.block.entity.ModBlockEntities;
import net.damqn4etobg.endlessexpansion.block.entity.renderer.EvolutionTableBERenderer;
import net.damqn4etobg.endlessexpansion.block.entity.renderer.InfuserBlockEntityRenderer;
import net.damqn4etobg.endlessexpansion.entity.ModEntities;
import net.damqn4etobg.endlessexpansion.entity.client.layer.ModModelLayers;
import net.damqn4etobg.endlessexpansion.entity.client.model.AbyssalScourgeModel;
import net.damqn4etobg.endlessexpansion.entity.client.model.SapphireDivingHelmetModel;
import net.damqn4etobg.endlessexpansion.entity.client.model.ShroomieModel;
import net.damqn4etobg.endlessexpansion.entity.client.model.WraithModel;
import net.damqn4etobg.endlessexpansion.entity.client.renderer.*;
import net.damqn4etobg.endlessexpansion.event.client.bossbar.ModBossbarOverlay;
import net.damqn4etobg.endlessexpansion.fluid.ModFluids;
import net.damqn4etobg.endlessexpansion.item.wand.evolution.WandEvolutionLoader;
import net.damqn4etobg.endlessexpansion.particle.ModParticles;
import net.damqn4etobg.endlessexpansion.particle.custom.*;
import net.damqn4etobg.endlessexpansion.screen.EvolutionTableScreen;
import net.damqn4etobg.endlessexpansion.screen.InfuserScreen;
import net.damqn4etobg.endlessexpansion.screen.MysticalCookieJarScreen;
import net.damqn4etobg.endlessexpansion.screen.RadioactiveGeneratorScreen;
import net.damqn4etobg.endlessexpansion.screen.menu.ModMenuTypes;
import net.damqn4etobg.endlessexpansion.util.KeyBinding;
import net.damqn4etobg.endlessexpansion.util.ModWoodTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = EndlessExpansion.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_NUCLEAR_WASTE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_NUCLEAR_WASTE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_LUMINITE_ESSENCE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_LUMINITE_ESSENCE.get(), RenderType.translucent());

        EntityRenderers.register(ModEntities.WRAITH.get(), WraithRenderer::new);
        EntityRenderers.register(ModEntities.SHROOMIE.get(), ShroomieRenderer::new);
        EntityRenderers.register(ModEntities.ABYSSAL_SCOURGE.get(), AbyssalScourgeRenderer::new);

        EntityRenderers.register(ModEntities.ARBOR_BOAT.get(), pContext -> new ArborBoatRenderer(pContext, false));
        EntityRenderers.register(ModEntities.ARBOR_CHEST_BOAT.get(), pContext -> new ArborBoatRenderer(pContext, true));
        EntityRenderers.register(ModEntities.BLISSWOOD_BOAT.get(), pContext -> new BlisswoodBoatRenderer(pContext, false));
        EntityRenderers.register(ModEntities.BLISSWOOD_CHEST_BOAT.get(), pContext -> new BlisswoodBoatRenderer(pContext, true));
        EntityRenderers.register(ModEntities.COBALT_BOLT.get(), CobaltBoltRenderer::new);
        EntityRenderers.register(ModEntities.MYSTICAL_COBALT_BOLT.get(), MysticalCobaltBoltRenderer::new);
        Sheets.addWoodType(ModWoodTypes.ARBOR);
        Sheets.addWoodType(ModWoodTypes.BLISSWOOD);

        MenuScreens.register(ModMenuTypes.RADIOACTIVE_GENERATOR_MENU.get(), RadioactiveGeneratorScreen::new);
        MenuScreens.register(ModMenuTypes.INFUSER_MENU.get(), InfuserScreen::new);
        MenuScreens.register(ModMenuTypes.MYSTICAL_COOKIE_JAR_MENU.get(), MysticalCookieJarScreen::new);
        MenuScreens.register(ModMenuTypes.EVOLUTION_TABLE_MENU.get(), EvolutionTableScreen::new);

        WandEvolutionLoader.loadResources();
        ItemProperties.register(Items.CROSSBOW, ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "cobalt_bolt"), ((itemStack, clientLevel, livingEntity, i) -> {
            if(livingEntity != null && CrossbowItem.isCharged(itemStack)) {
                CompoundTag tag = itemStack.getOrCreateTag();
                return tag.getBoolean("CobaltBolt") ? 1f : 0f;
            }
            return 0f;
        }));
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.WRAITH_LAYER, WraithModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SHROOMIE_LAYER, ShroomieModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.ABYSSAL_SCOURGE_LAYER, AbyssalScourgeModel::createBodyLayer);

        event.registerLayerDefinition(ModModelLayers.ARBOR_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModModelLayers.ARBOR_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
        event.registerLayerDefinition(ModModelLayers.BLISSWOOD_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModModelLayers.BLISSWOOD_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(ModModelLayers.SAPPHIRE_DIVING_HELMET_LAYER, SapphireDivingHelmetModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.INFUSER.get(), InfuserBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.EVOLUTION_TABLE.get(), EvolutionTableBERenderer::new);

        event.registerBlockEntityRenderer(ModBlockEntities.ARBOR_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ARBOR_HANGING_SIGN.get(), HangingSignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BLISSWOOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BLISSWOOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.SNOWFLAKE.get(), SnowflakeParticle.Provider::new);
        event.registerSpriteSet(ModParticles.SHADOW_ORB.get(), ShadowOrbParticle.Provider::new);
        event.registerSpriteSet(ModParticles.SHADOW_STRIP.get(), ShadowStripParticle.Provider::new);
        event.registerSpriteSet(ModParticles.SHADOW_SMOKE.get(), ShadowSmokeParticle.Provider::new);
        event.registerSpriteSet(ModParticles.SPARK.get(), SparkParticle.Provider::new);
        event.registerSpriteSet(ModParticles.ICE_DROP.get(), IceDropParticle.Provider::new);
        event.registerSpriteSet(ModParticles.EXPLODE_SPARK.get(), ExplodeSparkParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("freeze", FreezingHudOverlay.HUD_FREEZE);
        event.registerAboveAll("mod_bossbar", ModBossbarOverlay.HUD_BOSSBAR);
        event.registerBelowAll("shadow_dash", ShadowDashOverlay.HUD_SHADOW_DASH);
        EndlessExpansion.LOGGER.info("Registering Gui Overlays");
    }

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(KeyBinding.DASHING_KEY);
        event.register(KeyBinding.SHOW_CONFIG_KEY);
        EndlessExpansion.LOGGER.info("Registering Keymappings");
    }
}
