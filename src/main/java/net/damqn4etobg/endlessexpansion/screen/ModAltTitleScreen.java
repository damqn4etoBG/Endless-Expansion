package net.damqn4etobg.endlessexpansion.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class ModAltTitleScreen extends TitleScreen {

    private static final ResourceLocation MINECRAFT_TITLE_TEXTURES = new ResourceLocation("textures/gui/title/minecraft.png");

    public ModAltTitleScreen() {
        super(false);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        Minecraft.getInstance().getTextureManager().bindForSetup(MINECRAFT_TITLE_TEXTURES);
        int scaledWidth = this.width / 2 - 122;
        int scaledHeight = this.height / 4;
        guiGraphics.drawCenteredString(this.font, "Custom Label", this.width / 2, scaledHeight - 30, 0xFFFFFF);

        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }
}
