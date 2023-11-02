package net.damqn4etobg.endlessexpansion.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.EndlessExpansionConfig;
import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ModTitleScreen extends TitleScreen {
    public static final CubeMap CUBE_MAP_TITANIC_FOREST = new CubeMap(new ResourceLocation(EndlessExpansion.MODID, "textures/gui/title/background/titanic_forest/panorama"));
    public static final CubeMap CUBE_MAP_FROZEN_WASTES = new CubeMap(new ResourceLocation(EndlessExpansion.MODID, "textures/gui/title/background/frozen_wastes/panorama"));
    public static final CubeMap CUBE_MAP_SINKHOLE = new CubeMap(new ResourceLocation(EndlessExpansion.MODID, "textures/gui/title/background/sinkhole/panorama"));
    private static final ResourceLocation PANORAMA_OVERLAY = new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
    private final PanoramaRenderer panorama_titanic_forest = new PanoramaRenderer(CUBE_MAP_TITANIC_FOREST);
    private final PanoramaRenderer panorama_frozen_wastes = new PanoramaRenderer(CUBE_MAP_FROZEN_WASTES);
    private final PanoramaRenderer panorama_sinkhole = new PanoramaRenderer(CUBE_MAP_SINKHOLE);
    private final EndlessExpansionConfig config;
    private long firstRenderTime;
    private net.minecraftforge.client.gui.TitleScreenModUpdateIndicator modUpdateNotification;

    public ModTitleScreen() {
        config = EndlessExpansionConfig.loadConfig();
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
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
        guiGraphics.blit(PANORAMA_OVERLAY, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
        RenderSystem.setShaderTexture(0, PANORAMA_OVERLAY);

        int textHeight1 = this.font.lineHeight;

        int y = this.height - textHeight1 - 2;

        int textWidth3 = this.font.width(EndlessExpansionMainMenuScreen.VERSION);
        int textHeight3 = this.font.lineHeight;

        // If there is a forge update draw the text above the "New Forge version available"
        if (modUpdateNotification != null) {
            this.addRenderableWidget(new PlainTextButton(this.width - textWidth3 - 2, y - 19, textWidth3, textHeight3, EndlessExpansionMainMenuScreen.VERSION, (button) -> {
                Util.getPlatform().openUri("https://www.curseforge.com/minecraft/mc-mods/endless-expansion");
            }, this.font));
        } else {
            this.addRenderableWidget(new PlainTextButton(this.width - textWidth3 - 2, y - 9, textWidth3, textHeight3, EndlessExpansionMainMenuScreen.VERSION, (button) -> {
                Util.getPlatform().openUri("https://www.curseforge.com/minecraft/mc-mods/endless-expansion");
            }, this.font));
        }
    }
}