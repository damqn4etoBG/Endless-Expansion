package net.damqn4etobg.endlessexpansion.screen.renderer;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModLogoRenderer {
    private final boolean keepLogoThroughFade;
    public static final ResourceLocation ENDLESS_EXPANSION_LOGO = new ResourceLocation(EndlessExpansion.MODID, "textures/gui/title/endlessexpansion.png");

    public ModLogoRenderer(boolean pKeepLogoThroughFade) {
        this.keepLogoThroughFade = pKeepLogoThroughFade;
    }

    public void renderLogo(GuiGraphics pGuiGraphics, int pScreenWidth, float pTransparency) {
        this.renderLogo(pGuiGraphics, pScreenWidth, pTransparency, 30);
    }

    public void renderLogo(GuiGraphics pGuiGraphics, int pScreenWidth, float pTransparency, int pHeight) {
        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, this.keepLogoThroughFade ? 1.0F : pTransparency);
        int i = pScreenWidth / 2 - 128;
        pGuiGraphics.blit(this.ENDLESS_EXPANSION_LOGO, i, pHeight, 0.0F, 0.0F, 256, 100, 256, 100);
        int j = pScreenWidth / 2 - 64;
        int k = pHeight + 44 - 7;
        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
