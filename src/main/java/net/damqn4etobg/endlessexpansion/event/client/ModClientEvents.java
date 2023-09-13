package net.damqn4etobg.endlessexpansion.event.client;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.screen.ModTitleScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.client.loading.ClientModLoader;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.util.thread.EffectiveSide;


@Mod.EventBusSubscriber(modid = EndlessExpansion.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModClientEvents {

//    @SubscribeEvent
//    public static void onGuiOpened(ScreenEvent.Init event) {
//        if (event.getScreen() instanceof TitleScreen && !(event.getScreen() instanceof ModTitleScreen)) {
//
//           Minecraft.getInstance().setScreen(new ModTitleScreen(Minecraft.getInstance().screen));
//        }
//    }

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("freeze", FreezingHudOverlay.HUD_FREEZE);
        System.out.println("registering freeze overlay");
    }
}
