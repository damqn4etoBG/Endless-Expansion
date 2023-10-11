package net.damqn4etobg.endlessexpansion.event.client;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.EndlessExpansionConfig;
import net.damqn4etobg.endlessexpansion.dimension.ModDimensions;
import net.damqn4etobg.endlessexpansion.freeze.PlayerFreezeProvider;
import net.damqn4etobg.endlessexpansion.networking.ModMessages;
import net.damqn4etobg.endlessexpansion.networking.packet.FreezeC2SPacket;
import net.damqn4etobg.endlessexpansion.screen.ModTitleScreen;
import net.damqn4etobg.endlessexpansion.util.TickCounter;
import net.damqn4etobg.endlessexpansion.worldgen.biome.ModBiomes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = EndlessExpansion.MODID, value = Dist.CLIENT)
public class ModClientEvents {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            Level world = player.level();

            boolean inWater = player.isInWater();
            boolean inWorldBeyond = world.dimension() == ModDimensions.WORLD_BEYOND_LEVEL_KEY;

            BlockPos playerPos = player.blockPosition();
            Holder<Biome> playerBiome = player.level().getBiome(playerPos);

            boolean inSpecificBiome = playerBiome == ModBiomes.FROZEN_WASTES;

            if (inSpecificBiome && inWater && inWorldBeyond && event.player.getRandom().nextFloat() < 0.25f) { //about 10 secs avg 10 sec = 0.005f
                ModMessages.sendToServer(new FreezeC2SPacket());
            }

            if (inSpecificBiome && inWorldBeyond && event.player.getRandom().nextFloat() < 0.005f) { //about 10 secs avg 10 sec = 0.005f
                ModMessages.sendToServer(new FreezeC2SPacket());
            }

            if (!inSpecificBiome && inWorldBeyond && event.player.getRandom().nextFloat() < 0.005f) { //about 10 secs avg 10 sec = 0.005f
                ModMessages.sendToServer(new FreezeC2SPacket());
            }
        }
    }
    @OnlyIn(Dist.CLIENT)
    @Mod.EventBusSubscriber(modid = EndlessExpansion.MODID, value = Dist.CLIENT)
    public static class ModOnlyInClientEvents {
        @SubscribeEvent
        public static void onGuiOpened(ScreenEvent.Init event) {
           // System.out.println("onGuiOpened event triggered");
            if (event.getScreen() instanceof TitleScreen) {
                //System.out.println("TitleScreen detected");
                if (!(event.getScreen() instanceof ModTitleScreen) && EndlessExpansionConfig.loadConfig().isCustomMainMenu()) {
                   // System.out.println("Creating ModTitleScreen");
                    Minecraft.getInstance().forceSetScreen(new ModTitleScreen());
                }
            }
        }
    }
    @Mod.EventBusSubscriber(modid = EndlessExpansion.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModClientBusEvents {
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("freeze", FreezingHudOverlay.HUD_FREEZE);
            System.out.println("registering freeze overlay");
        }
    }
}
