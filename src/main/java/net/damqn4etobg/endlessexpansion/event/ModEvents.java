package net.damqn4etobg.endlessexpansion.event;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.capability.dash.PlayerDashProvider;
import net.damqn4etobg.endlessexpansion.capability.freeze.PlayerFreezeProvider;
import net.damqn4etobg.endlessexpansion.capability.wand.EvolutionDataProvider;
import net.damqn4etobg.endlessexpansion.command.EndExpCommand;
import net.damqn4etobg.endlessexpansion.effect.ModMobEffects;
import net.damqn4etobg.endlessexpansion.event.client.bossbar.ModBossbarHandler;
import net.damqn4etobg.endlessexpansion.event.client.bossbar.ModBossbarInstance;
import net.damqn4etobg.endlessexpansion.event.server.ModBossbarData;
import net.damqn4etobg.endlessexpansion.event.server.ModBossbarSavedEntry;
import net.damqn4etobg.endlessexpansion.item.enchantment.ModEnchantments;
import net.damqn4etobg.endlessexpansion.networking.ModMessages;
import net.damqn4etobg.endlessexpansion.networking.packet.EvolutionDataSyncS2CPacket;
import net.damqn4etobg.endlessexpansion.particle.ModParticles;
import net.damqn4etobg.endlessexpansion.util.EndlessUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = EndlessExpansion.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    protected Minecraft minecraft;

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new EndExpCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerFreezeProvider.PLAYER_FREEZE).isPresent()) {
                event.addCapability(ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "freeze"), new PlayerFreezeProvider());
            }
            if(!event.getObject().getCapability(PlayerDashProvider.PLAYER_DASH).isPresent()) {
                event.addCapability(ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "dash"), new PlayerDashProvider());
            }
            if(!event.getObject().getCapability(EvolutionDataProvider.EVOLUTION_DATA).isPresent()) {
                event.addCapability(ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "evolution_data"), new EvolutionDataProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerFreezeProvider.PLAYER_FREEZE).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerFreezeProvider.PLAYER_FREEZE).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
            event.getOriginal().getCapability(PlayerDashProvider.PLAYER_DASH).ifPresent(oldDash -> {
                event.getEntity().getCapability(PlayerDashProvider.PLAYER_DASH).ifPresent(newDash -> {
                    newDash.copyFrom(oldDash);
                });
            });
            event.getOriginal().getCapability(EvolutionDataProvider.EVOLUTION_DATA).ifPresent(oldStore -> {
                event.getEntity().getCapability(EvolutionDataProvider.EVOLUTION_DATA).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof ServerPlayer player && !event.getLevel().isClientSide()) {
            ModBossbarHandler.syncToPlayer(player);
            for (ModBossbarInstance bar : ModBossbarHandler.getServerBars()) {
                if (bar.getEntity() != null && bar.isPlayerInRange(player, 32D)) {
                    bar.addVisiblePlayer(player.getUUID());
                }
            }
            player.getCapability(EvolutionDataProvider.EVOLUTION_DATA).ifPresent(data -> ModMessages.sendToPlayer(new EvolutionDataSyncS2CPacket(data.serializeNBT()), player));
        }
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        if (!event.getEntity().level().isClientSide()) {
            Player player = event.getEntity();
            UUID playerId = player.getUUID();

            for (ModBossbarInstance bar : ModBossbarHandler.getServerBars()) {
                bar.removeVisiblePlayer(playerId);
            }
        }
    }

    // handle server-sided events
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            Level world = player.level();

            player.getCapability(PlayerFreezeProvider.PLAYER_FREEZE).ifPresent(freeze -> {
                if (freeze.getFreeze() >= 10 && (!player.isCreative() && !player.isSpectator())) {
                    player.addEffect(new MobEffectInstance(ModMobEffects.FREEZING.get(), 100, 0, false, false, true));
                }
            });

            if (player.hasEffect(ModMobEffects.SHADOW_STATE.get())) {
                if (!player.isInvisible()) {
                    player.setInvisible(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.LevelTickEvent event) {
        if(event.phase == TickEvent.Phase.END && !event.level.isClientSide()) {
            ModBossbarHandler.serverTick();
        }
    }

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        ServerLevel level = event.getServer().overworld();
        ModBossbarHandler.loadBossbars(level);
        EndlessExpansion.LOGGER.info("Loaded saved modded bossbar data!");
    }

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof LivingEntity living && !event.getLevel().isClientSide()) {
            UUID uuid = living.getUUID();

            if (ModBossbarHandler.hasPendingBossbar(uuid)) {
                String id = ModBossbarHandler.consumePendingBossbar(uuid);
                ServerLevel serverLevel = (ServerLevel) event.getLevel();

                ModBossbarData savedData = ModBossbarHandler.getSavedData(serverLevel);
                ModBossbarSavedEntry entry = savedData.getSavedBossbars().get(id);

                if(entry != null) {
                    Component name = entry.displayName;
                    int colorFrom = entry.colorFrom;
                    int colorTo = entry.colorTo;
                    boolean forcedVisible = entry.forcedVisible;
                    boolean forcedVisibleValue = entry.forcedVisibleValue;

                    ModBossbarInstance bar = new ModBossbarInstance(id, living, name, colorFrom, colorTo, forcedVisible, forcedVisibleValue);
                    ModBossbarHandler.serverAdd(bar);
                } else {
                    EndlessExpansion.LOGGER.warn("Had pending bossbar id= {} but no saved entry!", id);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onServerStopping(ServerStoppingEvent event) {
        ServerLevel level = event.getServer().overworld();
        ModBossbarHandler.saveBossbars(level);
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if(!(event.getSource().getEntity() instanceof Player player)) return;

        ItemStack weapon = player.getMainHandItem();

        RandomSource random = player.level().getRandom();

        if(EnchantmentHelper.getEnchantments(weapon).containsKey(ModEnchantments.IMPACT_EXPLODE.get())) {
            if (!(event.getSource().getDirectEntity() instanceof AbstractArrow arrow)) return;
            event.getEntity().hurt(event.getEntity().damageSources().magic(), (float) (arrow.getBaseDamage() * 2D));
            double x = arrow.getX();
            double y = arrow.getY();
            double z = arrow.getZ();

            EndlessUtils.addParticle(event.getEntity().level(), ModParticles.SPARK.get(), 50, x, y, z, 0.15, 0.15, 0.15, 0.1D);
            event.getEntity().level().playSound(null, arrow.blockPosition(), SoundEvents.FIREWORK_ROCKET_BLAST, SoundSource.AMBIENT, 1f, 1f);
        }
    }
}