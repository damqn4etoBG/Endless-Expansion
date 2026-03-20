package net.damqn4etobg.endlessexpansion.screen.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

import java.util.ArrayList;
import java.util.List;

public class MultipleComponentButton extends Button {
    private List<Component> description;
    private int tooltipWidth = 300;

    public MultipleComponentButton(int pX, int pY, int pWidth, int pHeight, Component pMessage, OnPress pOnPress) {
        super(pX, pY, pWidth, pHeight, pMessage, pOnPress, DEFAULT_NARRATION);
    }

    public MultipleComponentButton(int pX, int pY, int pWidth, int pHeight, Component pMessage, OnPress pOnPress, int tooltipWidth) {
        super(pX, pY, pWidth, pHeight, pMessage, pOnPress, DEFAULT_NARRATION);
        this.tooltipWidth = tooltipWidth;
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.isHovered = pMouseX >= this.getX() && pMouseY >= this.getY() && pMouseX < this.getX() + this.width && pMouseY < this.getY() + this.height;
        if(isHovered && active) {
            renderTooltip(pGuiGraphics, pMouseX, pMouseY);
        }
        this.renderWidget(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    public void setDescription(List<Component> description) {
        this.description = description;
    }

    public void renderTooltip(GuiGraphics graphics, int mouseX, int mouseY) {
        Font font = Minecraft.getInstance().font;
        List<List<FormattedCharSequence>> sequences = new ArrayList<>();
        for(Component component : description) {
            sequences.add(font.split(component, tooltipWidth));
        }
        List<FormattedCharSequence> flat = sequences.stream().flatMap(List::stream).toList();
        graphics.renderTooltip(font, flat, mouseX, mouseY);
    }
}
