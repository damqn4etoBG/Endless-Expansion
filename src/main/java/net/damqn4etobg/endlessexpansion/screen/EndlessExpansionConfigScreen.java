package net.damqn4etobg.endlessexpansion.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.config.EndlessExpansionClientConfig;
import net.damqn4etobg.endlessexpansion.config.EndlessExpansionServerConfig;
import net.damqn4etobg.endlessexpansion.screen.gui.components.ConfigButton;
import net.damqn4etobg.endlessexpansion.screen.gui.components.PlatformIconConfigButton;
import net.damqn4etobg.endlessexpansion.util.ModChatStyles;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.LayoutSettings;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class EndlessExpansionConfigScreen extends Screen {
    public static final CubeMap CUBE_MAP_TITANIC_FOREST = new CubeMap(ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/gui/title/background/titanic_forest/panorama"));
    public static final CubeMap CUBE_MAP_FROZEN_WASTES = new CubeMap(ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/gui/title/background/frozen_wastes/panorama"));
    public static final CubeMap CUBE_MAP_SINKHOLE = new CubeMap(ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/gui/title/background/sinkhole/panorama"));
    private static final ResourceLocation PANORAMA_OVERLAY = ResourceLocation.parse("textures/gui/title/background/panorama_overlay.png");
    private final PanoramaRenderer panorama_titanic_forest = new PanoramaRenderer(CUBE_MAP_TITANIC_FOREST);
    private final PanoramaRenderer panorama_frozen_wastes = new PanoramaRenderer(CUBE_MAP_FROZEN_WASTES);
    private final PanoramaRenderer panorama_sinkhole = new PanoramaRenderer(CUBE_MAP_SINKHOLE);
    private final Screen lastScreen;
    public static final Component MADE_BY_TEXT = Component.translatable("menu.endlessexpansion.config.made_by");
    public static final Component INSPIRED_TEXT = Component.translatable("menu.endlessexpansion.config.inspired_by");
    public static final Component VERSION = Component.literal("Endless Expansion " + EndlessExpansion.MOD_VERSION);
    private static final ResourceLocation CURSEFORGE_LOGO = ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/gui/platform/curseforge.png");
    private static final ResourceLocation GITHUB_LOGO = ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/gui/platform/github_alt.png");
    private static final ResourceLocation MODRINTH_LOGO = ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/gui/platform/modrinth.png");
    private static final ResourceLocation MIRAHEZE_LOGO = ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/gui/platform/miraheze.png");
    private long firstRenderTime;
    private static final ResourceLocation VIGNETTE_LOCATION = ResourceLocation.parse("textures/misc/vignette.png");

    public EndlessExpansionConfigScreen(Screen screen) {
        super(Component.translatable("menu.endlessexpansion.config.name"));
        this.lastScreen = screen;
    }

    @Override
    protected void init() {
        GridLayout gridlayout = new GridLayout();
        gridlayout.defaultCellSetting().paddingHorizontal(5).paddingBottom(4).alignHorizontallyCenter();
        GridLayout.RowHelper rowHelper = gridlayout.createRowHelper(2);
        LayoutSettings configTitleSettings = LayoutSettings.defaults().paddingHorizontal(5).paddingBottom(8).alignHorizontallyCenter();

        rowHelper.addChild(new StringWidget(Component.translatable("menu.endlessexpansion.config.client_config").withStyle(ModChatStyles.LIGHT_GRAY.withBold(false)), this.font), 2, configTitleSettings);
        // custom main menu button
        rowHelper.addChild(ConfigButton.builder(Component.translatable("menu.endlessexpansion.config.custom_menu"), (button) -> {
            {}
        }).width(100).build())
                .setTooltip(Tooltip.create(Component.translatable("menu.endlessexpansion.config.custom_menu_desc")));

        rowHelper.addChild(ConfigButton.builder(EndlessExpansionClientConfig.CUSTOM_MAIN_MENU.get() ? CommonComponents.OPTION_ON.copy().withStyle(ChatFormatting.GREEN) : CommonComponents.OPTION_OFF.copy().withStyle(ChatFormatting.RED),
                button -> {
                    // Toggle the customMainMenu state
                    EndlessExpansionClientConfig.CUSTOM_MAIN_MENU.set(!EndlessExpansionClientConfig.CUSTOM_MAIN_MENU.get());
                    // Update button label and save the config
                    button.setMessage(EndlessExpansionClientConfig.CUSTOM_MAIN_MENU.get() ? CommonComponents.OPTION_ON.copy().withStyle(ChatFormatting.GREEN) : CommonComponents.OPTION_OFF.copy().withStyle(ChatFormatting.RED));
        }).width(100).build()).setTooltip(Tooltip.create(Component.translatable("menu.endlessexpansion.config.custom_menu_switch")));

        // background select button
        rowHelper.addChild(ConfigButton.builder(Component.translatable("menu.endlessexpansion.config.background_selector"), (button) -> {
                    {}
                }).width(100).build())
                .setTooltip(Tooltip.create(Component.translatable("menu.endlessexpansion.config.background_selector_desc")));

        rowHelper.addChild(ConfigButton.builder(
                Component.literal(EndlessExpansionClientConfig.BACKGROUND_NAME.get()).withStyle(getChatFormattingForBackground(EndlessExpansionClientConfig.BACKGROUND_NAME.get())),
                button -> {
                    // Update the background name and button label
                    updateBackgroundName();
                    updateButtonLabelBackgroundSelector(button);
                }).width(100).build()).setTooltip(Tooltip.create(Component.translatable("menu.endlessexpansion.config.background_note")));

        // mod sounds button
        rowHelper.addChild(ConfigButton.builder(Component.translatable("menu.endlessexpansion.config.mod_sounds"), (button) -> {
                    {}
                }).width(100).build())
                .setTooltip(Tooltip.create(Component.translatable("menu.endlessexpansion.config.mod_sounds_desc")));

        rowHelper.addChild(ConfigButton.builder(
                Component.literal(EndlessExpansionClientConfig.MOD_SOUNDS.get()).withStyle(getChatFormattingForSounds(EndlessExpansionClientConfig.MOD_SOUNDS.get())),
                button -> {
                    updateModSoundsName();
                    updateButtonLabelModSoundsSelector(button);
                    button.setTooltip(getTooltipForSounds());
                }).width(100).build()).setTooltip(getTooltipForSounds());

        // bossbar style button
        rowHelper.addChild(ConfigButton.builder(Component.translatable("menu.endlessexpansion.config.bossbar"), (button) -> {
                    {}
                }).width(100).build())
                .setTooltip(Tooltip.create(Component.translatable("menu.endlessexpansion.config.bossbar_desc")));

        rowHelper.addChild(ConfigButton.builder(EndlessExpansionClientConfig.BOSSBAR_STYLE.get().equals("Percent") ? Component.translatable("menu.endlessexpansion.config.percent").withStyle(ChatFormatting.YELLOW) : Component.translatable("menu.endlessexpansion.config.value").withStyle(ModChatStyles.ORANGE),
                button -> {
                    setBossbarStyle();
                    getBossbarStyle(button);
                    button.setTooltip(getBossbarStyleTooltip());
                }).width(100).build()).setTooltip(getBossbarStyleTooltip());

        rowHelper.addChild(new StringWidget(Component.translatable("menu.endlessexpansion.config.server_config").withStyle(ModChatStyles.LIGHT_GRAY.withBold(false)), this.font), 2, configTitleSettings.paddingTop(8));
        // server packets button
        rowHelper.addChild(ConfigButton.builder(Component.translatable("menu.endlessexpansion.config.server_effect_packets"), (button) -> {
                    {}
                }).width(100).build())
                .setTooltip(Tooltip.create(Component.translatable("menu.endlessexpansion.config.server_effect_packets_desc")));

        ConfigButton sendEffectPacketsButton = ConfigButton.builder(getSendServerEffectPackets() ? CommonComponents.OPTION_ON.copy().withStyle(ModChatStyles.GREEN_OFF) : CommonComponents.OPTION_OFF.copy().withStyle(ModChatStyles.RED_OFF), button -> {
        }).width(100).build();
        rowHelper.addChild(sendEffectPacketsButton).setTooltip(Tooltip.create(Component.translatable("menu.endlessexpansion.config.server_only").withStyle(ModChatStyles.LIGHT_GRAY)));
        sendEffectPacketsButton.active = false;

        rowHelper.addChild(ConfigButton.builder(CommonComponents.GUI_DONE, (button) -> Minecraft.getInstance().setScreen(this.lastScreen)).width(200).build(), 2, rowHelper.newCellSettings().paddingTop(6));

        gridlayout.arrangeElements();
        FrameLayout.alignInRectangle(gridlayout, 0, this.height / 6 - 12, this.width, this.height, 0.5F, 0.0F);
        gridlayout.visitWidgets(this::addRenderableWidget);

        // Calculate the position for the second square button
        int textWidth1 = this.font.width(MADE_BY_TEXT);
        int textHeight1 = this.font.lineHeight;

        int x = this.width - textWidth1 - 2;
        int y = this.height - textHeight1 - 2;

        this.addRenderableWidget(new PlainTextButton(x, y + 1, textWidth1, textHeight1, MADE_BY_TEXT, (button) -> {
            this.minecraft.setScreen(new ModCreditsScreen(Minecraft.getInstance().screen));
        }, this.font)).setTooltip(Tooltip.create(Component.translatable("menu.endlessexpansion.config.credits")));

        int textWidth2 = this.font.width(INSPIRED_TEXT);
        int textHeight2 = this.font.lineHeight;

        int x2 = this.width - textWidth2 - 2;
        int y2 = y - textHeight2 - 1;

        this.addRenderableWidget(new PlainTextButton(x2, y2, textWidth2, textHeight2, INSPIRED_TEXT, (button) -> {
            Util.getPlatform().openUri("https://twbtiw.miraheze.org/wiki/Main_Page");
        }, this.font)).setTooltip(Tooltip.create(Component.translatable("menu.endlessexpansion.config.ohawhewhe")));

        int textWidth3 = this.font.width(VERSION);
        int textHeight3 = this.font.lineHeight;

        this.addRenderableWidget(new StringWidget(2, y + 1, textWidth3, textHeight3, VERSION, this.font));

        int buttonWidth = 20;
        int buttonHeight = 20;

        this.addRenderableWidget(new PlatformIconConfigButton(2, y2 - 12, buttonWidth, buttonHeight,
                CURSEFORGE_LOGO, 1f, (b) -> {
            Util.getPlatform().openUri("https://www.curseforge.com/minecraft/mc-mods/endless-expansion");
        }, Tooltip.create(Component.literal("§cCurseforge"))));

        this.addRenderableWidget(new PlatformIconConfigButton(2 + 24, y2 - 12, buttonWidth, buttonHeight,
                MODRINTH_LOGO, 1f, (b) -> {
            Util.getPlatform().openUri("https://modrinth.com/mod/endless-expansion");
        }, Tooltip.create(Component.literal("§aModrinth"))));

        this.addRenderableWidget(new PlatformIconConfigButton(2 + 48, y2 - 12, buttonWidth, buttonHeight,
                GITHUB_LOGO, 1f, (b) -> {
            Util.getPlatform().openUri("https://github.com/damqn4etoBG/Endless-Expansion");
        }, Tooltip.create(Component.literal("Github"))));

        this.addRenderableWidget(new PlatformIconConfigButton(2 + 72, y2 - 12, buttonWidth, buttonHeight,
                MIRAHEZE_LOGO, 1f, (b) -> {
            Util.getPlatform().openUri("https://endlessexpansion.miraheze.org");
        }, Tooltip.create(Component.literal("§eMiraheze Wiki"))));
    }

    @Override
    public void onClose() {
        super.onClose();
        Minecraft.getInstance().setScreen(this.lastScreen);
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        if (firstRenderTime == 0L)
            this.firstRenderTime = Util.getMillis();

        float f = (float) (Util.getMillis() - this.firstRenderTime) / 250.0F;
        float alpha = Mth.clamp(f,0.0F, 1.0F);

        switch (EndlessExpansionClientConfig.BACKGROUND_NAME.get()) {
            case "Titanic Forest" -> this.panorama_titanic_forest.render(delta, alpha);
            case "Frozen Wastes" -> this.panorama_frozen_wastes.render(delta, alpha);
            case "Sinkhole" -> this.panorama_sinkhole.render(delta, alpha);
            default -> {
                return;
            }
        }

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR);
        guiGraphics.blit(VIGNETTE_LOCATION, 0, 0, 0, 0.0F, 0.0F, this.width, this.height, this.width, this.height);
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
        guiGraphics.blit(PANORAMA_OVERLAY, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
        RenderSystem.setShaderTexture(0, PANORAMA_OVERLAY);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 15, 16777215);
        super.render(guiGraphics, mouseX, mouseY, delta);
    }

    private void updateBackgroundName() {
        String currentBackground = EndlessExpansionClientConfig.BACKGROUND_NAME.get();

        if ("Titanic Forest".equals(currentBackground)) {
            EndlessExpansionClientConfig.BACKGROUND_NAME.set("Frozen Wastes");
        } else if ("Frozen Wastes".equals(currentBackground)) {
            EndlessExpansionClientConfig.BACKGROUND_NAME.set("Sinkhole");
        } else if ("Sinkhole".equals(currentBackground)) {
            EndlessExpansionClientConfig.BACKGROUND_NAME.set("Titanic Forest");
        }
    }

    private void updateModSoundsName() {
        String currentString = EndlessExpansionClientConfig.MOD_SOUNDS.get();

        if ("ON".equals(currentString)) {
            EndlessExpansionClientConfig.MOD_SOUNDS.set("Partial");
        } else if ("Partial".equals(currentString)) {
            EndlessExpansionClientConfig.MOD_SOUNDS.set("OFF");
        } else if ("OFF".equals(currentString)) {
            EndlessExpansionClientConfig.MOD_SOUNDS.set("ON");
        }
    }

    private ChatFormatting getChatFormattingForBackground(String backgroundName) {
        return switch (backgroundName) {
            case "Titanic Forest" -> ChatFormatting.AQUA;
            case "Frozen Wastes" -> ChatFormatting.BLUE;
            case "Sinkhole" -> ChatFormatting.WHITE;
            default -> ChatFormatting.RED;
        };
    }

    private ChatFormatting getChatFormattingForSounds(String modSounds) {
        return switch (modSounds) {
            case "ON" -> ChatFormatting.GREEN;
            case "Partial" -> ChatFormatting.YELLOW;
            case "OFF" -> ChatFormatting.RED;
            default -> ChatFormatting.WHITE;
        };
    }

    private Tooltip getTooltipForSounds() {
        String currentString = EndlessExpansionClientConfig.MOD_SOUNDS.get();
        return switch (currentString) {
            case "ON" -> Tooltip.create(Component.translatable("menu.endlessexpansion.config.mod_sounds_on").withStyle(ModChatStyles.LIGHT_GRAY));
            case "Partial" -> Tooltip.create(Component.translatable("menu.endlessexpansion.config.mod_sounds_partial").withStyle(ModChatStyles.LIGHT_GRAY));
            case "OFF" -> Tooltip.create(Component.translatable("menu.endlessexpansion.config.mod_sounds_off").withStyle(ModChatStyles.LIGHT_GRAY));
            default -> Tooltip.create(Component.empty());
        };
    }

    private void updateButtonLabelBackgroundSelector(ConfigButton button) {
        button.setMessage(Component.literal(EndlessExpansionClientConfig.BACKGROUND_NAME.get()).withStyle(getChatFormattingForBackground(EndlessExpansionClientConfig.BACKGROUND_NAME.get())));
    }

    private void updateButtonLabelModSoundsSelector(ConfigButton button) {
        String currentString = EndlessExpansionClientConfig.MOD_SOUNDS.get();
         switch (currentString) {
            case "ON" -> button.setMessage(CommonComponents.OPTION_ON.copy().withStyle(getChatFormattingForSounds(EndlessExpansionClientConfig.MOD_SOUNDS.get())));
            case "Partial" -> button.setMessage(Component.translatable("menu.endlessexpansion.config.partial").withStyle(getChatFormattingForSounds(EndlessExpansionClientConfig.MOD_SOUNDS.get())));
            case "OFF" -> button.setMessage(CommonComponents.OPTION_OFF.copy().withStyle(getChatFormattingForSounds(EndlessExpansionClientConfig.MOD_SOUNDS.get())));
            default -> button.setMessage(Component.literal(currentString).withStyle(getChatFormattingForSounds(EndlessExpansionClientConfig.MOD_SOUNDS.get())));
        }
    }

    private boolean getSendServerEffectPackets() {
        if(EndlessExpansionServerConfig.SERVER_CONFIG.isLoaded()) {
            return EndlessExpansionServerConfig.SEND_EFFECT_PACKETS.get();
        }
        return true;
    }

    private void setBossbarStyle() {
        String current = EndlessExpansionClientConfig.BOSSBAR_STYLE.get();
        switch (current) {
            case "Percent" -> EndlessExpansionClientConfig.BOSSBAR_STYLE.set("Value");
            case "Value" -> EndlessExpansionClientConfig.BOSSBAR_STYLE.set("Percent");
        }
    }

    private void getBossbarStyle(ConfigButton button) {
        String current = EndlessExpansionClientConfig.BOSSBAR_STYLE.get();
        switch (current) {
            case "Percent" -> button.setMessage(Component.translatable("menu.endlessexpansion.config.percent").withStyle(ChatFormatting.YELLOW));
            case "Value" -> button.setMessage(Component.translatable("menu.endlessexpansion.config.value").withStyle(ModChatStyles.ORANGE));
            default -> button.setMessage(Component.literal(EndlessExpansionClientConfig.BOSSBAR_STYLE.get()));
        }
    }

    private Tooltip getBossbarStyleTooltip() {
        String current = EndlessExpansionClientConfig.BOSSBAR_STYLE.get();
        return switch (current) {
            case "Percent" -> Tooltip.create(Component.translatable("menu.endlessexpansion.config.bossbar.percent").withStyle(ModChatStyles.LIGHT_GRAY));
            case "Value" -> Tooltip.create(Component.translatable("menu.endlessexpansion.config.bossbar.value").withStyle(ModChatStyles.LIGHT_GRAY));
            default -> Tooltip.create(Component.literal(EndlessExpansionClientConfig.BOSSBAR_STYLE.get()));
        };
    }
}