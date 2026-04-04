package net.damqn4etobg.endlessexpansion;

import com.mojang.logging.LogUtils;
import net.damqn4etobg.endlessexpansion.block.EndlessBlocks;
import net.damqn4etobg.endlessexpansion.item.EndlessCreativeModeTabs;
import net.damqn4etobg.endlessexpansion.item.EndlessItems;
import net.damqn4etobg.endlessexpansion.sound.EndlessSounds;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(EndlessExpansion.MODID)
public class EndlessExpansion
{
    public static final String MODID = "endlessexpansion";
    private static final Logger LOGGER = LogUtils.getLogger();

    public EndlessExpansion(IEventBus modEventBus, ModContainer container)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (EndlessExpansion) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        EndlessItems.register(modEventBus);
        EndlessBlocks.register(modEventBus);
        EndlessCreativeModeTabs.register(modEventBus);
        EndlessSounds.register(modEventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        container.registerConfig(ModConfig.Type.CLIENT, EndlessConfig.Client.CONFIG_SPEC);
        container.registerConfig(ModConfig.Type.SERVER, EndlessConfig.Server.CONFIG_SPEC);
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
