package net.damqn4etobg.endlessexpansion.screen.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.damqn4etobg.endlessexpansion.screen.renderer.InfoArea;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraftforge.energy.IEnergyStorage;

import java.text.DecimalFormat;
import java.util.List;

public class EnergyInfoArea extends InfoArea {
    private final IEnergyStorage energy;

    public EnergyInfoArea(Minecraft minecraft, MultiBufferSource.BufferSource bufferSource, int xMin, int yMin) {
        this(minecraft, bufferSource, xMin, yMin, null, 4, 54);
    }

    public EnergyInfoArea(Minecraft minecraft, MultiBufferSource.BufferSource bufferSource, int xMin, int yMin, IEnergyStorage energy) {
        this(minecraft, bufferSource, xMin, yMin, energy, 4, 54);
    }

    public EnergyInfoArea(Minecraft minecraft, MultiBufferSource.BufferSource bufferSource, int xMin, int yMin, IEnergyStorage energy, int width, int height) {
        super(minecraft, bufferSource, new Rect2i(xMin, yMin, width, height));
        this.energy = energy;
    }

    public List<Component> getTooltips() {
        DecimalFormat df = new DecimalFormat("#.##");
        String energyString = energy.getEnergyStored() + " / " + energy.getMaxEnergyStored() + " FE";
        if (energy.getEnergyStored() <= 0) {
            energyString = df.format(energy.getEnergyStored()) + " FE / " + df.format(energy.getMaxEnergyStored() / 1000000.0) + " MFE";
        }
        if (energy.getEnergyStored() >= 1000) {
            energyString = df.format(energy.getEnergyStored() / 1000.0) + " kFE / " + df.format(energy.getMaxEnergyStored() / 1000.0) + " kFE";
        }
        if (energy.getEnergyStored() >= 1000000) {
            energyString = df.format(energy.getEnergyStored() / 1000000.0) + " MFE / " + df.format(energy.getMaxEnergyStored() / 1000000.0) + " MFE";
        }
        if (energy.getEnergyStored() >= 1000000000) {
            energyString = df.format(energy.getEnergyStored() / 1000000000.0) + " GFE / " + df.format(energy.getMaxEnergyStored() / 1000000000.0) + " GFE";
        }
        return List.of(Component.literal(energyString));
    }
    @Override
    public void draw(GuiGraphics guiGraphics) {
        final int height = area.getHeight();
        int stored = (int)(height*(energy.getEnergyStored()/(float)energy.getMaxEnergyStored()));
        guiGraphics.fillGradient(
                area.getX(), area.getY()+(height-stored),
                area.getX() + area.getWidth(), area.getY() +area.getHeight(),
                0xff00ff00, 0xff00b100
        );
    }
}