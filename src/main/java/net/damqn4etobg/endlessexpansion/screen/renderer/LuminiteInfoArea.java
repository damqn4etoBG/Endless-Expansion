package net.damqn4etobg.endlessexpansion.screen.renderer;

import net.damqn4etobg.endlessexpansion.util.ILuminiteEssence;
import net.damqn4etobg.endlessexpansion.util.ITemperature;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;

import java.text.DecimalFormat;
import java.util.List;

public class LuminiteInfoArea extends InfoArea {
    private final ILuminiteEssence essence;

    public LuminiteInfoArea(Minecraft minecraft, MultiBufferSource.BufferSource bufferSource, int xMin, int yMin) {
        this(minecraft, bufferSource, xMin, yMin, null, 4, 54);
    }

    public LuminiteInfoArea(Minecraft minecraft, MultiBufferSource.BufferSource bufferSource, int xMin, int yMin, ILuminiteEssence essence) {
        this(minecraft, bufferSource, xMin, yMin, essence, 4, 54);
    }

    public LuminiteInfoArea(Minecraft minecraft, MultiBufferSource.BufferSource bufferSource, int xMin, int yMin, ILuminiteEssence essence, int width, int height) {
        super(minecraft, bufferSource, new Rect2i(xMin, yMin, width, height));
        this.essence = essence;
    }

    public List<Component> getTooltips() {
        String luminiteString = "Luminite Essence: " + essence.getEssence() + " / " + essence.getMaxEssence();
        return List.of(Component.literal(luminiteString));
    }
    @Override
    public void draw(GuiGraphics guiGraphics) {
        final int height = area.getHeight();
        int stored = (int)(height*(essence.getEssence()/(float)essence.getMaxEssence()));
        guiGraphics.fillGradient(
                area.getX(), area.getY()+(height-stored),
                area.getX() + area.getWidth(), area.getY() +area.getHeight(),
                0xfffafa32, 0xffb3b323
        );
    }
}

