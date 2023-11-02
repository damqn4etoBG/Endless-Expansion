package net.damqn4etobg.endlessexpansion.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.screen.renderer.LuminiteInfoArea;
import net.damqn4etobg.endlessexpansion.util.MouseUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.Optional;

public class InfuserScreen extends AbstractContainerScreen<InfuserMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(EndlessExpansion.MODID,"textures/gui/infuser_gui.png");

    public InfuserScreen(InfuserMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        this.minecraft = getMinecraft();
    }
    private LuminiteInfoArea essenceInfoArea;

    private MultiBufferSource.BufferSource bufferSource;

    public MultiBufferSource.BufferSource getBufferSource() {
        return bufferSource;
    }

    @Override
    protected void init() {
        super.init();
        assignEssenceInfoArea(this.minecraft, getBufferSource());
    }

    private void assignEssenceInfoArea(Minecraft minecraft, MultiBufferSource.BufferSource bufferSource) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        essenceInfoArea = new LuminiteInfoArea(minecraft, bufferSource, x + 17, y + 16, menu.blockEntity.getEssenceStorage());
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int pMouseX, int pMouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.titleLabelY = 5;
        guiGraphics.drawString(this.font, this.title, this.titleLabelX + 62 , this.titleLabelY, 4210752, false);

        renderEssenceAreaTooltips(guiGraphics, pMouseX, pMouseY, x, y, 17, 16);
    }

    private void renderEssenceAreaTooltips(GuiGraphics guiGraphics, int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY) {
        if(isMouseAboveArea(pMouseX, pMouseY, x, y, offsetX, offsetY)) {
            guiGraphics.renderTooltip(this.font, essenceInfoArea.getTooltips(),
                    Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(guiGraphics, x, y);
        essenceInfoArea.draw(guiGraphics);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 50, y + 46, 176, 0, menu.getScaledProgress(), 7); // switch scaled progress and height for up and down arrow
            guiGraphics.blit(TEXTURE, x + 126 + (-menu.getScaledProgress()), y + 46, -(menu.getScaledProgress()) + 198, 7, menu.getScaledProgress(), 7);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, 4, 54);
    }
}
