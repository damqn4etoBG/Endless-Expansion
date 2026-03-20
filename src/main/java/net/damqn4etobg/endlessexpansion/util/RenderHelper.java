package net.damqn4etobg.endlessexpansion.util;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class RenderHelper {
    private static final ResourceLocation VIGNETTE_LOCATION = ResourceLocation.parse("textures/misc/vignette.png");

    // in opengl y (0, 0) is bottom left, so we flip it
    public static void enableScissor(int x, int y, int width, int height) {
        double scale = Minecraft.getInstance().getWindow().getGuiScale();
        RenderSystem.enableScissor((int) (x * scale), (int) ((Minecraft.getInstance().getWindow().getHeight() - (y + height) * scale)), (int)(width * scale), (int)(height * scale));
    }

    public static void drawVignette(GuiGraphics guiGraphics, int x, int y, int uOffset, int vOffset, int width, int height) {
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR);
        guiGraphics.blit(VIGNETTE_LOCATION, x, y, uOffset, vOffset, width, height);
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    }
}
