package net.damqn4etobg.endlessexpansion.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.damqn4etobg.endlessexpansion.Config;
import net.damqn4etobg.endlessexpansion.EndlessExpansionConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.*;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class EndlessExpansionMainMenuScreen extends Screen {
    public static final CubeMap CUBE_MAP = new CubeMap(new ResourceLocation("textures/gui/title/background/panorama"));
    private static final ResourceLocation PANORAMA_OVERLAY = new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
    private final PanoramaRenderer panorama = new PanoramaRenderer(CUBE_MAP);
    private final EndlessExpansionConfig config;
    private final Screen lastScreen;

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
        }).width(100).build());

        gridlayout$rowhelper.addChild(Button.builder(CommonComponents.GUI_DONE, (button) -> {
            this.minecraft.setScreen(this.lastScreen);
        }).width(200).build(), 2, gridlayout$rowhelper.newCellSettings().paddingTop(6));

        gridlayout.arrangeElements();
        FrameLayout.alignInRectangle(gridlayout, 0, this.height / 6 - 12, this.width, this.height, 0.5F, 0.0F);
        gridlayout.visitWidgets(this::addRenderableWidget);
    }

    @Override
    public void removed() {
        config.saveConfig();
        super.removed();
    }

    @Override
    public void onClose() {
        if(config.isCustomMainMenu()) {
            Minecraft.getInstance().setScreen(new ModTitleScreen(Minecraft.getInstance().screen));
        } else {
            Minecraft.getInstance().setScreen(this.lastScreen);
        }
        config.saveConfig();
        super.onClose();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        if (firstRenderTime == 0L)
            this.firstRenderTime = Util.getMillis();

        float f = (float) (Util.getMillis() - this.firstRenderTime) / 2000.0F;
        float alpha = Mth.clamp(f, 0.0F, 1.0F);
        float elapsedPartials = minecraft.getDeltaFrameTime();

        panorama.render(elapsedPartials, alpha);
        guiGraphics.blit(PANORAMA_OVERLAY, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
        RenderSystem.setShaderTexture(0, PANORAMA_OVERLAY);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 15, 16777215);
        super.render(guiGraphics, mouseX, mouseY, delta);
    }
}
