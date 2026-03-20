package net.damqn4etobg.endlessexpansion.event.client.bossbar;

import com.mojang.blaze3d.systems.RenderSystem;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.config.EndlessExpansionClientConfig;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ModBossbarOverlay {
    private static final ResourceLocation BOSSBAR = ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/gui/bossbar.png");

    public static final IGuiOverlay HUD_BOSSBAR = ((gui, guiGraphics, partialTick, width, height) -> {
        gui.getMinecraft().getProfiler().push("modBossbarOverlay");
        Font font = gui.getFont();
        if (gui.getMinecraft().player == null) return;

        int textureWidth = 197;
        int textureHeight = 32;
        int x = width / 2 - textureWidth / 2;
        int y = 5;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, BOSSBAR);

        for(ModBossbarInstance bar : ModBossbarHandler.getVisibleBarsForPlayer()) {
            bar.updateDisplayHealth();
            if(bar.isVisible()) {
                float percent = bar.getHealthPercent() * 100;
                int health = bar.getHealth();

                RenderSystem.setShaderColor(1f, 1f, 1f, bar.getAlpha());

                if (EndlessExpansionClientConfig.BOSSBAR_STYLE.get().equals("Percent")) {
                    drawBossbar(guiGraphics, bar, bar.getDisplayName(), (int) percent, bar.getColorFrom(), bar.getColorTo(), x, y, textureWidth, textureHeight, width, font);
                } else if (EndlessExpansionClientConfig.BOSSBAR_STYLE.get().equals("Value")) {
                    drawBossbarValue(guiGraphics, bar, bar.getDisplayName(), health, bar.getColorFrom(), bar.getColorTo(), x, y, textureWidth, textureHeight, width, font);
                }

                y += 35;
            }
        }
        gui.getMinecraft().getProfiler().pop();
    });

    private static void drawBossbar(GuiGraphics guiGraphics, ModBossbarInstance bar, Component text, int health, int barColorFrom, int barColorTo, int x, int y, int textureWidth, int textureHeight, int width, Font font) {
        String s = text.getString() + " | " + health + "%";
        guiGraphics.blit(BOSSBAR, x, y, 0, 0, textureWidth, textureHeight);
        guiGraphics.fillGradient(x + 8, y + 10, (int) (x - 8 + textureWidth * bar.getHealthPercent()), y - 9 + textureHeight, barColorFrom, barColorTo);
        guiGraphics.drawString(font, s, width / 2 - (font.width(s) / 2), y + 13, 0xFFFFFF, true);
    }

    private static void drawBossbarValue(GuiGraphics guiGraphics, ModBossbarInstance bar, Component text, int health, int barColorFrom, int barColorTo, int x, int y, int textureWidth, int textureHeight, int width, Font font) {
        String s = text.getString() + " | " + health;
        guiGraphics.blit(BOSSBAR, x, y, 0, 0, textureWidth, textureHeight);
        guiGraphics.fillGradient(x + 8, y + 10, (int) (x - 8 + textureWidth * bar.getHealthPercent()), y - 9 + textureHeight, barColorFrom, barColorTo);
        guiGraphics.drawString(font, s, width / 2 - (font.width(s) / 2), y + 13, 0xFFFFFF, true);
    }
}