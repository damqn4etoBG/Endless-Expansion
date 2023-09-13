package net.damqn4etobg.endlessexpansion.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
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
            new CubeMap(new ResourceLocation("textures/gui/title/background/panorama"));
    public static final ResourceLocation PANORAMA_OVERLAY_TEXTURES =
            new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
    public static final PanoramaRenderer PANORAMA = new PanoramaRenderer(PANORAMA_RESOURCES);
    private PanoramaRenderer panoramaRenderer;
    private long firstRenderTime;
    public ModTitleScreen(Screen screen) {
        panoramaRenderer = new PanoramaRenderer(PANORAMA_RESOURCES);
    }

    @Override
    protected void init() {
        int l = this.height / 4 + 48;

        // Calculate the center X position of the screen
        int centerX = this.width / 2;

        Button customMainMenuButton = this.addRenderableWidget(Button.builder(Component.literal("Custom Main Menu"), button -> {})
                .pos(centerX - 84, l + 20 * 2 - 48) // Centered position for the first button
                .size(98, 20)
                .build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        if (firstRenderTime == 0L)
            this.firstRenderTime = Util.getMillis();

        float f = (float) (Util.getMillis() - this.firstRenderTime) / 1000.0F;
        float elapsedPartials = minecraft.getDeltaFrameTime();
        float alpha = Mth.clamp(f, 0.0F, 1.0F);

        this.panoramaRenderer.render(elapsedPartials, 1);
        PANORAMA.render(elapsedPartials, alpha);

        guiGraphics.blit(PANORAMA_OVERLAY_TEXTURES, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
        super.render(guiGraphics, mouseX, mouseY, delta);
    }
}

