package net.damqn4etobg.endlessexpansion.event.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.capability.dash.PlayerDashProvider;
import net.damqn4etobg.endlessexpansion.effect.ModMobEffects;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ShadowDashOverlay {
    private static final ResourceLocation VIGNETTE_LOCATION = ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/misc/shadow_dash_vignette.png");
    private static float vignetteAlpha = 0f;

    public static final IGuiOverlay HUD_SHADOW_DASH = ((gui, guiGraphics, partialTick, width, height) -> {
        gui.getMinecraft().getProfiler().push("shadowDashOverlay");
        if(gui.getMinecraft().player == null) return;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, VIGNETTE_LOCATION);

        gui.getMinecraft().player.getCapability(PlayerDashProvider.PLAYER_DASH).ifPresent(dash -> {
            if(gui.getMinecraft().player.hasEffect(ModMobEffects.SHADOW_STATE.get())) {
                if (dash.getDashTicksElapsed() < 10) {
                    vignetteAlpha = Math.min(1f, vignetteAlpha + 0.075f); // Fade in
                } else {
                    vignetteAlpha = Math.max(0f, vignetteAlpha - 0.025f); // Fade out
                }

                if(vignetteAlpha > 0f) {
                    RenderSystem.setShaderColor(1F, 1, 1F, vignetteAlpha);
                    RenderSystem.enableBlend();
                    RenderSystem.defaultBlendFunc();
                    guiGraphics.blit(VIGNETTE_LOCATION, 0, 0, 0, 0, width, height, width, height);
                    RenderSystem.disableBlend();
                    RenderSystem.defaultBlendFunc();
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                }
            } else {
                vignetteAlpha = Math.max(0f, vignetteAlpha - 0.02f);
            }
        });
        gui.getMinecraft().getProfiler().pop();
    });
}
