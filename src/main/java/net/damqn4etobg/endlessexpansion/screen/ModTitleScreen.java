package net.damqn4etobg.endlessexpansion.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.screens.*;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.client.ForgeHooksClient;
import org.jetbrains.annotations.NotNull;

public class ModTitleScreen extends TitleScreen {
    public static final CubeMap PANORAMA_RESOURCES =
            new CubeMap(new ResourceLocation(EndlessExpansion.MODID,"textures/gui/title/background/panorama"));
    public static final ResourceLocation PANORAMA_OVERLAY_TEXTURES =
            new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
    public static final PanoramaRenderer PANORAMA = new PanoramaRenderer(PANORAMA_RESOURCES);
    private long firstRenderTime;

    private net.minecraftforge.client.gui.TitleScreenModUpdateIndicator modUpdateNotification;

    public ModTitleScreen(Screen screen) {

    }

    @Override
    protected void init() {
        Button modButton = null;
        modUpdateNotification = net.minecraftforge.client.gui.TitleScreenModUpdateIndicator.init(this, modButton);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        if (firstRenderTime == 0L)
            this.firstRenderTime = Util.getMillis();

        float f = (float) (Util.getMillis() - this.firstRenderTime) / 250.0F;
        float alpha = Mth.clamp(f, 0.0F, 1.0F);
        float elapsedPartials = minecraft.getDeltaFrameTime();

        PANORAMA.render(elapsedPartials, alpha);
        guiGraphics.blit(PANORAMA_OVERLAY_TEXTURES, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
        RenderSystem.setShaderTexture(0, PANORAMA_OVERLAY_TEXTURES);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 15, 16777215);

        int textWidth1 = this.font.width(EndlessExpansionMainMenuScreen.MADE_BY_TEXT);
        int textHeight1 = this.font.lineHeight;

        int x = this.width - textWidth1 - 2;
        int y = this.height - textHeight1 - 2;

        guiGraphics.drawString(this.font, EndlessExpansionMainMenuScreen.MADE_BY_TEXT, x, y, 16777215);

        int textWidth2 = this.font.width(EndlessExpansionMainMenuScreen.INSPIRED_TEXT);
        int textHeight2 = this.font.lineHeight;

        int x2 = this.width - textWidth2 - 2;
        int y2 = y - textHeight2 - 2;

        this.addRenderableWidget(new PlainTextButton(x2, y2, textWidth2, textHeight2, EndlessExpansionMainMenuScreen.INSPIRED_TEXT, (button) -> {
            Util.getPlatform().openUri("https://www.easyzoom.com/imageaccess/1af41cb38a924d6aaab39bb247190204");
        }, this.font));

        int textWidth3 = this.font.width(EndlessExpansionMainMenuScreen.VERSION);
        int textHeight3 = this.font.lineHeight;

        int x3 = 2; //0 is left corner + 2 px for padding

        this.addRenderableWidget(new PlainTextButton(x3, y, textWidth3, textHeight3, EndlessExpansionMainMenuScreen.VERSION, (button) -> {
            Util.getPlatform().openUri("https://www.curseforge.com/minecraft");
        }, this.font));
        modUpdateNotification.render(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
    }
}

