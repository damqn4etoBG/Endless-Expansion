package net.damqn4etobg.endlessexpansion.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.damqn4etobg.endlessexpansion.capability.freeze.PlayerFreezeProvider;
import net.damqn4etobg.endlessexpansion.capability.wand.EvolutionDataProvider;
import net.damqn4etobg.endlessexpansion.command.argument.HexArgumentType;
import net.damqn4etobg.endlessexpansion.dimension.ModDimensions;
import net.damqn4etobg.endlessexpansion.dimension.portal.WorldBeyondCommandTeleporter;
import net.damqn4etobg.endlessexpansion.event.ModRegistries;
import net.damqn4etobg.endlessexpansion.event.client.bossbar.ModBossbarHandler;
import net.damqn4etobg.endlessexpansion.event.client.bossbar.ModBossbarInstance;
import net.damqn4etobg.endlessexpansion.event.server.ModServerBossEvent;
import net.damqn4etobg.endlessexpansion.item.wand.WandItem;
import net.damqn4etobg.endlessexpansion.item.wand.evolution.WandEvolutions;
import net.damqn4etobg.endlessexpansion.networking.ModMessages;
import net.damqn4etobg.endlessexpansion.networking.packet.EvolutionDataSyncS2CPacket;
import net.damqn4etobg.endlessexpansion.networking.packet.FreezeDataSyncS2CPacket;
import net.damqn4etobg.endlessexpansion.networking.packet.UpdateModBossbarS2CPacket;
import net.damqn4etobg.endlessexpansion.screen.EndlessExpansionConfigScreen;
import net.damqn4etobg.endlessexpansion.tag.ModTags;
import net.damqn4etobg.endlessexpansion.worldgen.biome.ModBiomes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.ComponentArgument;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class EndExpCommand {
    public static final SuggestionProvider<CommandSourceStack> SUGGEST_BOSSBAR = (ctx, builder) -> SharedSuggestionProvider.suggest(ModBossbarHandler.getServerBossbarIDs().stream(), builder);
    public static final SuggestionProvider<CommandSourceStack> SUGGEST_EVOLUTION = (ctx, builder) -> SharedSuggestionProvider.suggest(WandEvolutions.getAllEvolutionLocations(), builder);
    public static final SuggestionProvider<CommandSourceStack> SUGGEST_WAND = (ctx, builder) -> SharedSuggestionProvider.suggest(WandEvolutions.getAllWands(), builder);

    public EndExpCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("endexp")
                .requires((commandSourceStack -> commandSourceStack.hasPermission(2)))
                .then(Commands.literal("tpWorldBeyond")
                        .then(Commands.argument("targets", EntityArgument.players()).executes(this::tpWorldBeyond)))

                .then(Commands.literal("freeze")
                        .then(Commands.literal("add")
                                .then(Commands.argument("amount", IntegerArgumentType.integer(0, 10))
                                        .then(Commands.argument("targets", EntityArgument.players()).executes(this::addFreeze))))

                        .then(Commands.literal("subtract")
                                .then(Commands.argument("amount", IntegerArgumentType.integer(0, 10))
                                        .then(Commands.argument("targets", EntityArgument.players()).executes(this::subFreeze)))))

                .then(Commands.literal("locateAllBiomes").executes(this::locateAllBiomes))

                .then(Commands.literal("bossbar")
                        .then(Commands.literal("add")
                                .then(Commands.argument("id", StringArgumentType.word())
                                        .then(Commands.argument("entity", EntityArgument.entity())
                                                .then(Commands.argument("text", ComponentArgument.textComponent()).executes(this::addBossbar)))))

                        .then(Commands.literal("remove")
                                .then(Commands.argument("id", StringArgumentType.word()).suggests(SUGGEST_BOSSBAR).executes(this::removeBossbar)))

                        .then(Commands.literal("list").executes(this::listBossbars))

                        .then(Commands.literal("get")
                                .then(Commands.argument("id", StringArgumentType.word()).suggests(SUGGEST_BOSSBAR)
                                        .then(Commands.literal("max").executes(this::getBossbarMax))
                                        //.then(Commands.literal("players").executes(this::getBossbarPlayers))
                                        .then(Commands.literal("value").executes(this::getBossbarValue))
                                        .then(Commands.literal("visible").executes(this::getBossbarVisible))
                                        .then(Commands.literal("entityUUID").executes(this::getBossbarEntityUUID))))

                        .then(Commands.literal("set")
                                .then(Commands.argument("id", StringArgumentType.word()).suggests(SUGGEST_BOSSBAR)
                                        .then(Commands.literal("color")
                                                .then(Commands.argument("colorFrom", HexArgumentType.hexColor()).then(Commands.argument("colorTo", HexArgumentType.hexColor()).executes(this::setBossbarColor))))
                                        .then(Commands.literal("text")
                                                .then(Commands.argument("text", ComponentArgument.textComponent()).executes(this::setBossbarText)))
                                        .then(Commands.literal("visible")
                                                .then(Commands.argument("visible", BoolArgumentType.bool()).executes(this::setBossbarVisible)))))

                        .then(Commands.literal("reset")
                                .then(Commands.argument("id", StringArgumentType.word()).suggests(SUGGEST_BOSSBAR)
                                        .then(Commands.literal("color").executes(this::resetBossbarColors))
                                        .then(Commands.literal("text").executes(this::resetBossbarText))
                                        .then(Commands.literal("visible").executes(this::resetBossbarVisibility)))))

                .then(Commands.literal("wand")
                        .then(Commands.literal("setEvolution")
                                .then(Commands.argument("evolution", ResourceLocationArgument.id()).suggests(SUGGEST_EVOLUTION).executes(this::setEvolution)))
                        .then(Commands.literal("highestEvolution")
                                .then(Commands.literal("get")
                                        .then(Commands.argument("target", EntityArgument.player()).executes(this::getHighestEvolutionsForPlayer)))
                                .then(Commands.literal("set")
                                        .then(Commands.argument("target", EntityArgument.player())
                                                .then(Commands.argument("wand", ResourceLocationArgument.id()).suggests(SUGGEST_WAND)
                                                        .then(Commands.argument("evolution", ResourceLocationArgument.id()).suggests(SUGGEST_EVOLUTION).executes(this::setHighestEvolution)))))
                                .then(Commands.literal("clear")
                                        .then(Commands.argument("target", EntityArgument.player()).executes(this::clearHighestEvolutions)))))

                .then(Commands.literal("openConfigScreen").executes(this::openConfigScreen))
        );
    }

    private int tpWorldBeyond(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        Collection<? extends ServerPlayer> targets = EntityArgument.getPlayers(ctx, "targets");

        for (ServerPlayer player : targets) {
            BlockPos pos = player.getOnPos();
            if (player.level() instanceof ServerLevel serverlevel) {
                MinecraftServer minecraftserver = serverlevel.getServer();
                ResourceKey<Level> resourcekey = player.level().dimension() == ModDimensions.WORLD_BEYOND_LEVEL_KEY ?
                        Level.OVERWORLD : ModDimensions.WORLD_BEYOND_LEVEL_KEY;
                ServerLevel portalDimension = minecraftserver.getLevel(resourcekey);
                if (portalDimension != null && !player.isPassenger()) {
                    if (resourcekey == ModDimensions.WORLD_BEYOND_LEVEL_KEY) {
                        player.changeDimension(portalDimension, new WorldBeyondCommandTeleporter(pos, true));
                    } else {
                        player.changeDimension(portalDimension, new WorldBeyondCommandTeleporter(pos, false));
                    }
                }
            }
        }

        if (targets.size() == 1) {
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.tpWorldBeyond", targets.iterator().next().getName()), true);
        } else {
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.tpWorldBeyond_multiple", targets.size()), true);
        }
        return 1;
    }

    private int addFreeze(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        int amount = IntegerArgumentType.getInteger(ctx, "amount");
        Collection<? extends ServerPlayer> targets = EntityArgument.getPlayers(ctx, "targets");

        for (ServerPlayer player : targets) {
            BlockPos playerPos = player.blockPosition();
            Holder<Biome> holder = player.level().getBiome(playerPos);
            boolean inSpecificBiome = holder.is(ModTags.Biomes.IS_FROZEN_WASTES);

            if (player.level().dimension() == ModDimensions.WORLD_BEYOND_LEVEL_KEY) {
                player.getCapability(PlayerFreezeProvider.PLAYER_FREEZE).ifPresent(freeze -> {
                    if (inSpecificBiome) {
                        freeze.addFreeze(amount);
                    }
                    ModMessages.sendToPlayer(new FreezeDataSyncS2CPacket(freeze.getFreeze()), player);
                });
            } else {
                player.getCapability(PlayerFreezeProvider.PLAYER_FREEZE).ifPresent(freeze -> {
                    ModMessages.sendToPlayer(new FreezeDataSyncS2CPacket(freeze.getFreeze()), player);
                });
            }
        }

        if (targets.size() == 1) {
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.freeze.add", amount, targets.iterator().next().getName()), true);
        } else {
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.freeze.add_multiple", amount, targets.size()), true);
        }
        return 1;
    }

    private int subFreeze(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        int amount = IntegerArgumentType.getInteger(ctx, "amount");
        Collection<? extends ServerPlayer> targets = EntityArgument.getPlayers(ctx, "targets");

        for (ServerPlayer player : targets) {
            BlockPos playerPos = player.blockPosition();
            Holder<Biome> holder = player.level().getBiome(playerPos);
            boolean inSpecificBiome = holder.is(ModTags.Biomes.IS_FROZEN_WASTES);
            if (player.level().dimension() == ModDimensions.WORLD_BEYOND_LEVEL_KEY) {
                player.getCapability(PlayerFreezeProvider.PLAYER_FREEZE).ifPresent(freeze -> {
                    if (inSpecificBiome) {
                        freeze.subFreeze(amount);
                    }
                    ModMessages.sendToPlayer(new FreezeDataSyncS2CPacket(freeze.getFreeze()), player);
                });
            } else {
                player.getCapability(PlayerFreezeProvider.PLAYER_FREEZE).ifPresent(freeze -> {
                    ModMessages.sendToPlayer(new FreezeDataSyncS2CPacket(freeze.getFreeze()), player);
                });
            }
        }

        if (targets.size() == 1) {
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.freeze.subtract", amount, targets.iterator().next().getName()), true);
        } else {
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.freeze.subtract_multiple", amount, targets.size()), true);
        }
        return 1;
    }

    private int locateAllBiomes(CommandContext<CommandSourceStack> ctx) {
        CommandSourceStack source = ctx.getSource();
        ServerLevel world = source.getLevel();
        if(source.getPlayer().level().dimension() == ModDimensions.WORLD_BEYOND_LEVEL_KEY) {
            long startTime = System.nanoTime();
            source.sendSuccess(() -> Component.translatable("commands.endlessexpansion.locateAllBiomes"), false);
            for (ResourceKey<Biome> biome : ModBiomes.ALL_BIOMES) {
                world.getServer().getCommands().performPrefixedCommand(source, "locate biome " + biome.location());
            }
            long endTime = System.nanoTime(); // End timing
            long durationMs = (endTime - startTime) / 1_000_000; // Convert nanoseconds to milliseconds
            double durationSeconds = durationMs / 1000.0; // Convert to seconds
            source.sendSuccess(() -> Component.translatable("commands.endlessexpansion.locateAllBiomes.end", durationSeconds).withStyle(ChatFormatting.YELLOW), false);
        } else {
            source.sendFailure(Component.translatable("commands.endlessexpansion.world_beyond_only"));
        }
        return 1;
    }

    private int addBossbar(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        Entity entity = EntityArgument.getEntity(ctx, "entity");
        Component text = ComponentArgument.getComponent(ctx, "text");
        String id = StringArgumentType.getString(ctx, "id");
        if(entity instanceof LivingEntity livingEntity && ModBossbarHandler.getServerBossbar(id) == null) {
            ModServerBossEvent.addBossbarWithID(id, livingEntity, text);
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.bossbar.add.success", id).withStyle(ChatFormatting.GREEN), true);
        } else {
            ctx.getSource().sendFailure(Component.translatable("commands.endlessexpansion.bossbar.add.failure", id));
        }
        return 1;
    }

    private int removeBossbar(CommandContext<CommandSourceStack> ctx) {
        String id = StringArgumentType.getString(ctx, "id");
        if(ModBossbarHandler.getServerBossbar(id) != null) {
            ModServerBossEvent.removeBossbar(id);
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.bossbar.remove.success", id).withStyle(ChatFormatting.GREEN), true);
        } else {
            ctx.getSource().sendFailure(Component.translatable("commands.endlessexpansion.bossbar.remove.failure", id));
        }
        return 1;
    }

    private int listBossbars(CommandContext<CommandSourceStack> ctx) {
        Collection<ModBossbarInstance> bossbars = ModBossbarHandler.getServerBars();
        if(bossbars.isEmpty()) {
            ctx.getSource().sendFailure(Component.translatable("commands.bossbar.list.bars.none"));
        } else {
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.bossbar.list.success", ModBossbarHandler.getServerBossbarIDs()).withStyle(ChatFormatting.GREEN), false);
        }
        return 1;
    }

    private int getBossbarMax(CommandContext<CommandSourceStack> ctx) {
        String id = StringArgumentType.getString(ctx, "id");
        if(ModBossbarHandler.getServerBossbar(id) == null) {
            ctx.getSource().sendFailure(Component.translatable("commands.endlessexpansion.bossbar.remove.failure", id));
        } else {
            int max = ModBossbarHandler.getServerBossbar(id).getMaxHealth();
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.bossbar.get.max", id, max).withStyle(ChatFormatting.GREEN), false);
        }
        return 1;
    }

    private int getBossbarPlayers(CommandContext<CommandSourceStack> ctx) {
        String id = StringArgumentType.getString(ctx, "id");
        if(ModBossbarHandler.getServerBossbar(id) == null) {
            ctx.getSource().sendFailure(Component.translatable("commands.endlessexpansion.bossbar.remove.failure", id));
        } else {
            Collection<String> playerNames = new ArrayList<>();
            for (UUID uuid : ModBossbarHandler.getServerBossbar(id).getVisiblePlayers()) {
                ServerPlayer player = ctx.getSource().getServer().getPlayerList().getPlayer(uuid);
                if (player != null) {
                    playerNames.add(player.getName().getString());
                }
            }
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.bossbar.get.players", id, playerNames).withStyle(ChatFormatting.GREEN), false);
        }
        return 1;
    }

    private int getBossbarValue(CommandContext<CommandSourceStack> ctx) {
        String id = StringArgumentType.getString(ctx, "id");
        ModBossbarInstance bar = ModBossbarHandler.getClientBossbar(id);

        if(ModBossbarHandler.getServerBossbar(id) == null) {
            ctx.getSource().sendFailure(Component.translatable("commands.endlessexpansion.bossbar.remove.failure", id));
        } else {
            int value = bar.getHealth();
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.bossbar.get.value", id, value).withStyle(ChatFormatting.GREEN), false);
        }
        return 1;
    }

    private int getBossbarVisible(CommandContext<CommandSourceStack> ctx) {
        String id = StringArgumentType.getString(ctx, "id");
        ModBossbarInstance bar = ModBossbarHandler.getClientBossbar(id);
        boolean visible;

        if(ModBossbarHandler.getServerBossbar(id) == null) {
            ctx.getSource().sendFailure(Component.translatable("commands.endlessexpansion.bossbar.remove.failure", id));
        } else {
            if(!bar.getForcedVisible()) {
                visible = bar.getVisible();
            } else {
                visible = bar.getForcedVisibleValue();
            }
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.bossbar.get.visible", id, visible).withStyle(ChatFormatting.GREEN), false);
        }
        return 1;
    }

    private int getBossbarEntityUUID(CommandContext<CommandSourceStack> ctx) {
        String id = StringArgumentType.getString(ctx, "id");
        ModBossbarInstance bar = ModBossbarHandler.getClientBossbar(id);

        if(ModBossbarHandler.getServerBossbar(id) == null) {
            ctx.getSource().sendFailure(Component.translatable("commands.endlessexpansion.bossbar.remove.failure", id));
        } else {
            UUID uuid = bar.getEntityUUID();
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.bossbar.get.uuid", id, uuid).withStyle(ChatFormatting.GREEN), false);
        }
        return 1;
    }

    private int setBossbarColor(CommandContext<CommandSourceStack> ctx) {
        String id = StringArgumentType.getString(ctx, "id");
        int colorFrom = HexArgumentType.getHexColor(ctx, "colorFrom");
        int colorTo = HexArgumentType.getHexColor(ctx, "colorTo");
        ModBossbarInstance bar = ModBossbarHandler.getServerBossbar(id);

        if(bar == null) {
            ctx.getSource().sendFailure(Component.translatable("commands.endlessexpansion.bossbar.remove.failure", id));
        } else {
            bar.setBarColor(colorFrom, colorTo);
            ModServerBossEvent.updateColor(id, colorFrom, colorTo);
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.bossbar.set.color", id, colorFrom, colorTo).withStyle(ChatFormatting.GREEN), false);
        }
        return 1;
    }

    private int setBossbarText(CommandContext<CommandSourceStack> ctx) {
        String id = StringArgumentType.getString(ctx, "id");
        Component text = ComponentArgument.getComponent(ctx, "text");
        ModBossbarInstance bar = ModBossbarHandler.getServerBossbar(id);

        if(bar == null) {
            ctx.getSource().sendFailure(Component.translatable("commands.endlessexpansion.bossbar.remove.failure", id));
        } else {
            bar.setDisplayName(text);
            ModBossbarHandler.syncToAll();
            ModServerBossEvent.updateText(id, text);
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.bossbar.set.text", id, text).withStyle(ChatFormatting.GREEN), false);
        }
        return 1;
    }

    private int setBossbarVisible(CommandContext<CommandSourceStack> ctx) {
        String id = StringArgumentType.getString(ctx, "id");
        boolean visible = BoolArgumentType.getBool(ctx, "visible");
        ModBossbarInstance bar = ModBossbarHandler.getServerBossbar(id);

        if(bar == null) {
            ctx.getSource().sendFailure(Component.translatable("commands.endlessexpansion.bossbar.remove.failure", id));
        } else {
            bar.setVisibilityForced(true, visible);
            ModBossbarHandler.syncToAll();
            ModServerBossEvent.updateVisibility(id, true, visible);

            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.bossbar.set.visible", id, visible).withStyle(ChatFormatting.GREEN), false);
        }
        return 1;
    }

    private int resetBossbarVisibility(CommandContext<CommandSourceStack> ctx) {
        String id = StringArgumentType.getString(ctx, "id");
        ModBossbarInstance bar = ModBossbarHandler.getServerBossbar(id);

        if (bar == null) {
            ctx.getSource().sendFailure(Component.translatable("commands.endlessexpansion.bossbar.remove.failure", id));
        } else {
            bar.setVisibilityForced(false, false);
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.bossbar.reset.visible", id).withStyle(ChatFormatting.GREEN), false);
            ModMessages.sendToClients(new UpdateModBossbarS2CPacket(id, ModServerBossEvent.SetMode.VISIBILITY, null, null, null, false, false));
        }
        return 1;
    }

    private int resetBossbarColors(CommandContext<CommandSourceStack> ctx) {
        String id = StringArgumentType.getString(ctx, "id");
        ModBossbarInstance bar = ModBossbarHandler.getServerBossbar(id);
        int colorFrom = 0xFFb50000;
        int colorTo = 0xFFFF0000;

        if (bar == null) {
            ctx.getSource().sendFailure(Component.translatable("commands.endlessexpansion.bossbar.remove.failure", id));
        } else {
            bar.setBarColor(colorFrom, colorTo);
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.bossbar.reset.color", id).withStyle(ChatFormatting.GREEN), false);
            ModServerBossEvent.updateColor(id, colorFrom, colorTo);
        }
        return 1;
    }

    private int resetBossbarText(CommandContext<CommandSourceStack> ctx) {
        String id = StringArgumentType.getString(ctx, "id");
        ModBossbarInstance bar = ModBossbarHandler.getServerBossbar(id);

        if (bar == null) {
            ctx.getSource().sendFailure(Component.translatable("commands.endlessexpansion.bossbar.remove.failure", id));
        } else {
            bar.setDisplayName(bar.getEntity().getDisplayName());
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.bossbar.reset.text", id).withStyle(ChatFormatting.GREEN), false);
            ModServerBossEvent.updateText(id, bar.getEntity().getDisplayName());
        }
        return 1;
    }

    private int setEvolution(CommandContext<CommandSourceStack> ctx) {
        ItemStack wand = ctx.getSource().getPlayer().getMainHandItem();
        ResourceLocation evolutionLoc = ResourceLocationArgument.getId(ctx, "evolution");
        if(wand.getItem() instanceof WandItem) {
            if(ModRegistries.WAND_EVOLUTION_REGISTRY.get().getValue(evolutionLoc) != null) {
                CompoundTag tag = wand.getOrCreateTag();
                tag.putString("evolution", evolutionLoc.toString());
                ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.wand.setEvolution.success", evolutionLoc.toString()), false);
            } else {
                ctx.getSource().sendFailure(Component.translatable("commands.endlessexpansion.wand.setEvolution.failure", evolutionLoc.toString()));
            }
        } else {
            ctx.getSource().sendFailure(Component.translatable("commands.endlessexpansion.wand.setEvolution.failure_item"));
        }
        return 1;
    }

    private int getHighestEvolutionsForPlayer(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = EntityArgument.getPlayer(ctx, "target");
        player.getCapability(EvolutionDataProvider.EVOLUTION_DATA).ifPresent(data -> {
            Map<String, String> evolutions = data.getHighestEvolutions();
            ModMessages.sendToPlayer(new EvolutionDataSyncS2CPacket(data.serializeNBT()), player);
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.wand.getHighestEvosForPlayer.success", player.getName(), evolutions), false);
        });
        if(!player.getCapability(EvolutionDataProvider.EVOLUTION_DATA).isPresent()) ctx.getSource().sendFailure(Component.translatable("commands.endlessexpansion.wand.getHighestEvosForPlayer.failure"));
        return 1;
    }

    private int setHighestEvolution(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = EntityArgument.getPlayer(ctx, "target");
        ResourceLocation wand = ResourceLocationArgument.getId(ctx, "wand");
        ResourceLocation evolution = ResourceLocationArgument.getId(ctx, "evolution");
        player.getCapability(EvolutionDataProvider.EVOLUTION_DATA).ifPresent(data -> {
           if(WandEvolutions.getAllEvolutionLocations().contains(evolution.toString())) {
               data.setHighestEvolution(wand.toString(), evolution.toString());
               ModMessages.sendToPlayer(new EvolutionDataSyncS2CPacket(data.serializeNBT()), player);
               ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.wand.setHighestEvolution.success", player.getName(), wand, evolution), false);
           } else {
               ctx.getSource().sendFailure(Component.translatable("commands.endlessexpansion.wand.setHighestEvolution.failure", player.getName(), wand, evolution, evolution));
           }
        });
        if(!player.getCapability(EvolutionDataProvider.EVOLUTION_DATA).isPresent()) ctx.getSource().sendFailure(Component.translatable("commands.endlessexpansion.wand.getHighestEvosForPlayer.failure"));
        return 1;
    }

    private int clearHighestEvolutions(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = EntityArgument.getPlayer(ctx, "target");
        player.getCapability(EvolutionDataProvider.EVOLUTION_DATA).ifPresent(data -> {
            data.clearHighestEvolutions();
            ModMessages.sendToPlayer(new EvolutionDataSyncS2CPacket(data.serializeNBT()), player);
            ctx.getSource().sendSuccess(() -> Component.translatable("commands.endlessexpansion.wand.clearHighestEvolutions.success", player.getName()), false);
        });
        if(!player.getCapability(EvolutionDataProvider.EVOLUTION_DATA).isPresent()) ctx.getSource().sendFailure(Component.translatable("commands.endlessexpansion.wand.getHighestEvosForPlayer.failure"));
        return 1;
    }

    private int openConfigScreen(CommandContext<CommandSourceStack> ctx) {
        Minecraft.getInstance().setScreen(new EndlessExpansionConfigScreen(Minecraft.getInstance().screen));
        return 1;
    }
}