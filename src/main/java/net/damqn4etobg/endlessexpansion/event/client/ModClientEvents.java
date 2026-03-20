package net.damqn4etobg.endlessexpansion.event.client;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.capability.dash.PlayerDashProvider;
import net.damqn4etobg.endlessexpansion.config.EndlessExpansionClientConfig;
import net.damqn4etobg.endlessexpansion.config.EndlessExpansionServerConfig;
import net.damqn4etobg.endlessexpansion.dimension.ModDimensions;
import net.damqn4etobg.endlessexpansion.effect.ModMobEffects;
import net.damqn4etobg.endlessexpansion.event.client.bossbar.ModBossbarHandler;
import net.damqn4etobg.endlessexpansion.networking.ModMessages;
import net.damqn4etobg.endlessexpansion.networking.packet.DashC2SPacket;
import net.damqn4etobg.endlessexpansion.networking.packet.DashParticlesC2SPacket;
import net.damqn4etobg.endlessexpansion.networking.packet.EffectParticlesC2SPacket;
import net.damqn4etobg.endlessexpansion.networking.packet.FreezeC2SPacket;
import net.damqn4etobg.endlessexpansion.screen.ModTitleScreen;
import net.damqn4etobg.endlessexpansion.sound.ModSoundOptions;
import net.damqn4etobg.endlessexpansion.sound.ModSounds;
import net.damqn4etobg.endlessexpansion.util.KeyBinding;
import net.damqn4etobg.endlessexpansion.worldgen.biome.ModBiomes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.InputEvent;
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

            if (player.hasEffect(ModMobEffects.FREEZING.get())) {
                if(canSendEffectPackets()) {
                    ModMessages.sendToServer(new EffectParticlesC2SPacket());
                }
            }

            handleFreezingEffect(player, player.level());

            if (player.hasEffect(ModMobEffects.SHADOW_STATE.get())) {
                if(canSendEffectPackets()) {
                    ModMessages.sendToServer(new EffectParticlesC2SPacket());
                }
                player.getCapability(PlayerDashProvider.PLAYER_DASH).ifPresent(dash -> {
                    dash.incrementDashTicks();

                    if (dash.getDashTicksElapsed() >= 39 && !dash.canDash()) {
                        if (!ModSoundOptions.OFF()) {
                            world.playSound(player, player.blockPosition(), ModSounds.DASH_INDICATOR.get(), SoundSource.AMBIENT, 1f, 1f);
                        }
                    }
                });
            }

            ModBossbarHandler.clientTick();
        }
    }

    @SubscribeEvent
    public static void onClientWorldUnload(ClientPlayerNetworkEvent.LoggingOut event) {
        //ModBossbarHandler.clear(); // clear on client side
    }

    @SubscribeEvent
    public static void onClientWorldLoad(ClientPlayerNetworkEvent.LoggingIn event) {

    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (player != null && player.hasEffect(ModMobEffects.SHADOW_STATE.get()) && KeyBinding.DASHING_KEY.consumeClick()) {
            player.getCapability(PlayerDashProvider.PLAYER_DASH).ifPresent(dash -> {
                if (dash.canDash()) {
                    dash.resetDashTicks();

                    double speed = 1.5D;
                    double lookAngle = player.getYRot();
                    double dx = -Math.sin(Math.toRadians(lookAngle)) * speed;
                    double dz = Math.cos(Math.toRadians(lookAngle)) * speed;

                    player.setDeltaMovement(dx, player.getDeltaMovement().y, dz);
                    ModMessages.sendToServer(new DashParticlesC2SPacket());
                    ModMessages.sendToServer(new DashC2SPacket());
                }
            });
        }
    }

    private static void handleFreezingEffect(Player player, Level world) {
        boolean inWater = player.isInWater();
        boolean inWorldBeyond = world.dimension() == ModDimensions.WORLD_BEYOND_LEVEL_KEY;
        BlockPos playerPos = player.blockPosition();
        Holder<Biome> playerBiome = world.getBiome(playerPos);
        boolean inSpecificBiome = playerBiome == ModBiomes.FROZEN_WASTES;

        if (inSpecificBiome && inWater && inWorldBeyond && player.getRandom().nextFloat() < 0.5f) {
            ModMessages.sendToServer(new FreezeC2SPacket());
        }

        if (inSpecificBiome && inWorldBeyond && player.getRandom().nextFloat() < 0.15f) {
            ModMessages.sendToServer(new FreezeC2SPacket());
        }

        if (!inSpecificBiome && inWorldBeyond && player.getRandom().nextFloat() < 0.005f) { // about 10 secs avg 10 sec = 0.005f
            ModMessages.sendToServer(new FreezeC2SPacket());
        }

        if(world.isClientSide()) {
            if (ClientFreezeData.getPlayerFreeze() >= 10 && (!player.isCreative() && !player.isSpectator())) {
                player.addEffect(new MobEffectInstance(ModMobEffects.FREEZING.get(), 100, 0, false, false, true));
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Mod.EventBusSubscriber(modid = EndlessExpansion.MODID, value = Dist.CLIENT)
    public static class ModOnlyInClientEvents {
        private static boolean customTitleSet = false;
        @SubscribeEvent
        public static void onGuiOpened(ScreenEvent.Init event) {
            if (event.getScreen() instanceof TitleScreen && !(event.getScreen() instanceof ModTitleScreen) && EndlessExpansionClientConfig.CUSTOM_MAIN_MENU.get() && !customTitleSet) {
                Minecraft.getInstance().setScreen(new ModTitleScreen(false));
                EndlessExpansion.LOGGER.info("Setting Mod Title Screen");
                customTitleSet = true;
            }
        }

        @SubscribeEvent
        public static void onGuiOpening(ScreenEvent.Opening event) {
            Screen screen = event.getNewScreen();

            if (screen instanceof TitleScreen) {
                customTitleSet = false;
                //EndlessExpansion.LOGGER.info("Resetting Mod Title Screen");
            }
        }
    }

    private static boolean canSendEffectPackets() {
        return EndlessExpansionServerConfig.SEND_EFFECT_PACKETS.get() && Minecraft.getInstance().getConnection() != null;
    }
}