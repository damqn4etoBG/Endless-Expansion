package net.damqn4etobg.endlessexpansion.event.client;

import com.mojang.blaze3d.platform.ScreenManager;
import net.damqn4etobg.endlessexpansion.Config;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.screen.ModTitleScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.main.GameConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public void onGuiOpened(FMLLoadCompleteEvent event) {
        ScreenEvent screenEvent = null;
        if (screenEvent.getScreen() instanceof TitleScreen && !(screenEvent.getScreen() instanceof ModTitleScreen)) {
            Minecraft.getInstance().forceSetScreen(new ModTitleScreen());
            System.out.println("setting mod title screen");
        }
    }
}
