package net.damqn4etobg.endlessexpansion.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.client.ForgeHooksClient;
import org.jetbrains.annotations.NotNull;

public class ModTitleScreen extends TitleScreen {
    private static final ResourceLocation MINECRAFT_TITLE_TEXTURES = new ResourceLocation("textures/gui/title/minecraft.png");
    public static ResourceLocation[] drawingTextures = new ResourceLocation[22];
    private String splashText;

    private TitleScreen titleScreen;

    public ModTitleScreen() {

    }

    @Override
    protected void init() {
        modTitleScreenInit(titleScreen);
        super.init();
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        int width = this.width;
        int height = this.height;
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager._enableBlend();
        float f11 = 1.0F;
        int l = Mth.ceil(f11 * 255.0F) << 24;
        guiGraphics.drawString(this.font, "TEstTEst " + ChatFormatting.YELLOW + "1.0", 2, height - 10, 0xFFFFFFFF);
        RenderSystem.setShaderTexture(0, MINECRAFT_TITLE_TEXTURES);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        ForgeHooksClient.renderMainMenu(this, guiGraphics, this.getMinecraft().font, width, height, l);
        if (this.splashText != null) {
            guiGraphics.pose().pushPose();
            float f2 = 1.8F - Mth.abs(Mth.sin((float) (Util.getMillis() % 1000L) / 1000.0F * ((float) Math.PI * 2F)) * 0.1F);
            f2 = f2 * 100.0F / (float) (this.font.width(this.splashText) + 32);
            guiGraphics.pose().scale(f2, f2, f2);
            guiGraphics.drawCenteredString(this.font, this.splashText, 0, -8, 16776960 | l);
            guiGraphics.pose().popPose();
        }


        String s1 = "Copyright Mojang AB. Do not distribute!";
        Font font = this.getMinecraft().font;
        guiGraphics.drawString(font, s1, width - this.getMinecraft().font.width(s1) - 2,
                height - 10, 0xFFFFFFFF);
        for (int i = 0; i < this.renderables.size(); ++i) {
            this.renderables.get(i).render(guiGraphics, mouseX, mouseY, partialTicks);
        }
        for (int i = 0; i < this.renderables.size(); i++) {
            renderables.get(i).render(guiGraphics, mouseX, mouseY, getMinecraft().getFrameTime());
        }
    }
    public static ModTitleScreen modTitleScreenInit(TitleScreen guiMainMenu)
    {
        ModTitleScreen modTitleScreen = new ModTitleScreen();
        modTitleScreen.resize(guiMainMenu.getMinecraft(), guiMainMenu.width, guiMainMenu.height);
        modTitleScreen.init();
        return modTitleScreen;
    }
}

