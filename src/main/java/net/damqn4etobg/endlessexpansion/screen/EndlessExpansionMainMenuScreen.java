package net.damqn4etobg.endlessexpansion.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.EndlessExpansionConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EndlessExpansionMainMenuScreen extends Screen {
    public static final CubeMap CUBE_MAP_TITANIC_FOREST = new CubeMap(new ResourceLocation(EndlessExpansion.MODID, "textures/gui/title/background/titanic_forest/panorama"));
    public static final CubeMap CUBE_MAP_FROZEN_WASTES = new CubeMap(new ResourceLocation(EndlessExpansion.MODID, "textures/gui/title/background/frozen_wastes/panorama"));
    public static final CubeMap CUBE_MAP_SINKHOLE = new CubeMap(new ResourceLocation(EndlessExpansion.MODID, "textures/gui/title/background/sinkhole/panorama"));
    private static final ResourceLocation PANORAMA_OVERLAY = new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
    private final PanoramaRenderer panorama_titanic_forest = new PanoramaRenderer(CUBE_MAP_TITANIC_FOREST);
    private final PanoramaRenderer panorama_frozen_wastes = new PanoramaRenderer(CUBE_MAP_FROZEN_WASTES);
    private final PanoramaRenderer panorama_sinkhole = new PanoramaRenderer(CUBE_MAP_SINKHOLE);
    private final EndlessExpansionConfig config;
    private final Screen lastScreen;
    public static final Component MADE_BY_TEXT = Component.literal("Made by damqn4etoBG and Officer");
    public static final Component INSPIRED_TEXT = Component.literal("Inspired by The World Beyond The Ice Wall");
    public static final Component VERSION = Component.literal("Endless Expansion " + EndlessExpansionConfig.MOD_VERSION);
    private static final ResourceLocation CURSEFORGE_LOGO = new ResourceLocation(EndlessExpansion.MODID, "textures/gui/platform/curseforge.png");
    private static final ResourceLocation GITHUB_LOGO = new ResourceLocation(EndlessExpansion.MODID, "textures/gui/platform/github.png");

    private long firstRenderTime;
    public EndlessExpansionMainMenuScreen(Screen screen) {
        super(Component.translatable("menu.endlessexpansion.config.name"));
        config = EndlessExpansionConfig.loadConfig();
        this.lastScreen = screen;
    }
    @Override
    protected void init() {
        int k = 24;
        int yPos = this.height / 4 + 48;
        GridLayout gridlayout = new GridLayout();
        gridlayout.defaultCellSetting().paddingHorizontal(5).paddingBottom(4).alignHorizontallyCenter();
        GridLayout.RowHelper gridlayout$rowhelper = gridlayout.createRowHelper(2);

        // 12 px between buttons
        // Calculate the center X position of the screen
        int centerX = this.width / 2;

        gridlayout$rowhelper.addChild(Button.builder(Component.translatable("menu.endlessexpansion.config.custom_menu"), (button) -> {
            {}
        }).width(100).build())
                .setTooltip(Tooltip.create(Component.translatable("menu.endlessexpansion.config.custom_menu_desc")));

        gridlayout$rowhelper.addChild(Button.builder(config.isCustomMainMenu() ? Component.literal("ON").withStyle(ChatFormatting.GREEN) : Component.literal("OFF").withStyle(ChatFormatting.RED),
                button -> {
                    // Toggle the customMainMenu state
                    config.setCustomMainMenu(!config.isCustomMainMenu());

                    // Update button label and save the config
                    button.setMessage(
                            config.isCustomMainMenu() ? Component.literal("ON").withStyle(ChatFormatting.GREEN) : Component.literal("OFF").withStyle(ChatFormatting.RED)
                    );
        }).width(100).build()).setTooltip(Tooltip.create(Component.translatable("menu.endlessexpansion.config.custom_menu_switch")));

        gridlayout$rowhelper.addChild(Button.builder(Component.translatable("menu.endlessexpansion.config.background_selector"), (button) -> {
                    {}
                }).width(100).build())
                .setTooltip(Tooltip.create(Component.translatable("menu.endlessexpansion.config.background_selector_desc")));

        gridlayout$rowhelper.addChild(Button.builder(
                Component.literal(config.getBackgroundName()).withStyle(getChatFormattingForBackground(config.getBackgroundName())),
                button -> {
                    // Update the background name and button label
                    updateBackgroundName(config);
                    updateButtonLabelBackgroundSelector(button, config);
                }).width(100).build());

        gridlayout$rowhelper.addChild(Button.builder(CommonComponents.GUI_DONE, (button) -> {
            Minecraft.getInstance().setScreen(this.lastScreen);
        }).width(200).build(), 2, gridlayout$rowhelper.newCellSettings().paddingTop(6));

        gridlayout.arrangeElements();
        FrameLayout.alignInRectangle(gridlayout, 0, this.height / 6 - 12, this.width, this.height, 0.5F, 0.0F);
        gridlayout.visitWidgets(this::addRenderableWidget);

        int buttonWidth = 20; // Adjust the width as needed
        int buttonHeight = 20; // Adjust the height as needed
        int padding = 4; // Adjust the padding between buttons
        int centerX2 = this.width / 2;
        int firstButtonX = centerX - buttonWidth - padding / 2;
        int buttonY = this.height - buttonHeight - 10; // Adjust the vertical position

//        this.addRenderableWidget(new EndlessExpansionMenuButton.PlatformIconButton(firstButtonX, buttonY, buttonWidth, buttonHeight,
//                ModGuiTextures.CURSEFORGE_LOGO, 0.085f, (button) -> {
//            System.out.println(ModGuiTextures.CURSEFORGE_LOGO);
//        }, Tooltip.create(Component.empty())));

//        this.addRenderableWidget(new ImageButton(firstButtonX, buttonY, buttonWidth, buttonWidth, 0, 0, 20, CURSEFORGE_LOGO, 256, 256, (button) -> {
//            System.out.println(CURSEFORGE_LOGO);
//            System.out.println(Button.ACCESSIBILITY_TEXTURE);
//        }, Component.translatable("narrator.button.language")));
//
//        // Calculate the position for the second square button
//        int secondButtonX = centerX2 + padding / 2;
//
//        // Create and add the second square button
//        this.addRenderableWidget(new ImageButton(secondButtonX, buttonY, buttonWidth, buttonHeight, 0, 0, GITHUB_LOGO, (button) -> {
//
//        }));
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    @Override
    public void onClose() {
        super.onClose();
        Minecraft.getInstance().setScreen(this.lastScreen);
        config.saveConfig();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        if (firstRenderTime == 0L)
            this.firstRenderTime = Util.getMillis();

        float f = (float) (Util.getMillis() - this.firstRenderTime) / 250.0F;
        float alpha = Mth.clamp(f, 0.0F, 1.0F);
        float elapsedPartials = minecraft.getDeltaFrameTime();

        if(config.getBackgroundName().equals("Titanic Forest")) {
            panorama_titanic_forest.render(elapsedPartials, alpha);
            guiGraphics.blit(PANORAMA_OVERLAY, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
        } else if (config.getBackgroundName().equals("Frozen Wastes")) {
            panorama_frozen_wastes.render(elapsedPartials, alpha);
            guiGraphics.blit(PANORAMA_OVERLAY, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
        } else if (config.getBackgroundName().equals("Sinkhole")) {
            panorama_sinkhole.render(elapsedPartials, alpha);
            guiGraphics.blit(PANORAMA_OVERLAY, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
        }

        RenderSystem.setShaderTexture(0, PANORAMA_OVERLAY);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 15, 16777215);

        int textWidth1 = this.font.width(MADE_BY_TEXT);
        int textHeight1 = this.font.lineHeight;

        int x = this.width - textWidth1 - 2;
        int y = this.height - textHeight1 - 2;

        guiGraphics.drawString(this.font, MADE_BY_TEXT, x, y, 16777215);

        int textWidth2 = this.font.width(INSPIRED_TEXT);
        int textHeight2 = this.font.lineHeight;

        int x2 = this.width - textWidth2 - 2;
        int y2 = y - textHeight2 - 2;

        this.addRenderableWidget(new PlainTextButton(x2, y2, textWidth2, textHeight2, INSPIRED_TEXT, (button) -> {
            Util.getPlatform().openUri("https://twbtiw.miraheze.org/wiki/Main_Page");
            button.setTooltip(Tooltip.create(Component.literal("Created By ohawhewhe")));
        }, this.font));

        int textWidth3 = this.font.width(VERSION);
        int textHeight3 = this.font.lineHeight;

        int x3 = 2; //0 is left corner + 2 px for padding

        this.addRenderableWidget(new PlainTextButton(x3, y, textWidth3, textHeight3, VERSION, (button) -> {
            Util.getPlatform().openUri("https://www.curseforge.com/minecraft/mc-mods/endless-expansion");
        }, this.font));

        super.render(guiGraphics, mouseX, mouseY, delta);

    }

    private void updateBackgroundName(EndlessExpansionConfig config) {
        String currentBackground = config.getBackgroundName();

        if ("Titanic Forest".equals(currentBackground)) {
            config.setBackgroundName("Frozen Wastes");
        } else if ("Frozen Wastes".equals(currentBackground)) {
            config.setBackgroundName("Sinkhole");
        } else if ("Sinkhole".equals(currentBackground)) {
            config.setBackgroundName("Titanic Forest");
        }

        config.saveConfig(); // Save the updated configuration here
    }

    private ChatFormatting getChatFormattingForBackground(String backgroundName) {
        switch (backgroundName) {
            case "Titanic Forest":
                return ChatFormatting.AQUA;
            case "Frozen Wastes":
                return ChatFormatting.BLUE;
            case "Sinkhole":
                return ChatFormatting.WHITE;
            default:
                return ChatFormatting.RED;
        }
    }

    private void updateButtonLabelBackgroundSelector(Button button, EndlessExpansionConfig config) {
        button.setMessage(Component.literal(config.getBackgroundName()).withStyle(getChatFormattingForBackground(config.getBackgroundName())));
    }

}
