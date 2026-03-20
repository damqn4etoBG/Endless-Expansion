package net.damqn4etobg.endlessexpansion.screen.gui.components;

import com.mojang.blaze3d.systems.RenderSystem;
import net.damqn4etobg.endlessexpansion.util.ModChatStyles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EvolutionButton extends Button {
    private final @Nullable ItemStack icon;
    private final @Nullable ResourceLocation texture;
    private final boolean useTexture;
    private final int baseX;
    private final int baseY;
    private boolean hovered;
    private Component name;
    private Component description;
    private int cooldown;

    public EvolutionButton(int pX, int pY, OnPress pOnPress, @Nullable ItemStack itemStack) {
        super(pX, pY, 20, 20, Component.empty(), pOnPress, DEFAULT_NARRATION);
        this.icon = itemStack;
        this.texture = null;
        this.useTexture = false;
        this.baseX = pX;
        this.baseY = pY;
    }

    public EvolutionButton(int pX, int pY, OnPress pOnPress, @Nullable ResourceLocation icon) {
        super(pX, pY, 20, 20, Component.empty(), pOnPress, DEFAULT_NARRATION);
        this.icon = null;
        this.texture = icon;
        this.useTexture = true;
        this.baseX = pX;
        this.baseY = pY;
    }

    public void updatePos(float scrollX, float scrollY, float zoom) {
        this.setPosition((int) (this.getBaseX() + scrollX / 2), (int) (this.getBaseY() + scrollY / 2));
        this.setWidth((int) (20 * zoom));
        this.setHeight((int) (20 * zoom));
    }

    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick, float zoom) {
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().scale(zoom, zoom, 1f);
        //this.hovered = MouseUtil.isMouseOver(pMouseX, pMouseY, getX(), getY(), this.width, this.height);
        this.hovered = pMouseX >= this.getX() && pMouseY >= this.getY() && pMouseX < this.getX() + this.width && pMouseY < this.getY() + this.height;
//        if(hovered) {
//            renderTooltip(pGuiGraphics, pMouseX, pMouseY);
//        }
        this.renderWidget(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        if (useTexture && texture != null) {
            RenderSystem.setShaderTexture(0, texture);
            pGuiGraphics.blit(texture, getX() + 2, getY() + 2, 0, 0, 16, 16, 16, 16);
        } else {
            if(icon != null) pGuiGraphics.renderItem(icon, getX() + 2, getY() + 2);
        }
        pGuiGraphics.pose().popPose();
    }

    public void renderTooltip(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        Font font = Minecraft.getInstance().font;
        List<FormattedCharSequence> wrappedDesc = font.split(description, 100);
        List<FormattedCharSequence> tooltipLines = new ArrayList<>();
        tooltipLines.add(name.copy().getVisualOrderText());
        tooltipLines.addAll(wrappedDesc);
        tooltipLines.add(Component.translatable("tooltip.endlessexpansion.wand.cooldown", cooldown / 20).withStyle(ModChatStyles.ORANGE).getVisualOrderText());
        pGuiGraphics.renderTooltip(font, tooltipLines, pMouseX, pMouseY);
    }

    @Override
    public void onPress() {
        super.onPress();
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1f));
    }

    public int getBaseX() { return this.baseX; }
    public int getBaseY() { return this.baseY; }
    public void setHovered(boolean hovered) { this.hovered = hovered; }
    public boolean isHovered() { return hovered; }
    public void setName(Component name) { this.name = name; }
    public Component getName() { return this.name; }
    public void setDescription(Component description) { this.description = description; }
    public Component getDescription() { return this.description; }
    public void setCooldown(int cooldown) { this.cooldown = cooldown; }
}


