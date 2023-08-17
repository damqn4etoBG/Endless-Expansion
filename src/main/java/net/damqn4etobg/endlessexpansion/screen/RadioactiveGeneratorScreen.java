package net.damqn4etobg.endlessexpansion.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.screen.renderer.EnergyInfoArea;
import net.damqn4etobg.endlessexpansion.screen.renderer.FluidTankRenderer;
import net.damqn4etobg.endlessexpansion.screen.renderer.TemperatureInfoArea;
import net.damqn4etobg.endlessexpansion.util.MouseUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import java.util.Optional;

public class RadioactiveGeneratorScreen extends AbstractContainerScreen<RadioactiveGeneratorMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(EndlessExpansion.MODID,"textures/gui/radioactive_generator_gui.png");
    private EnergyInfoArea energyInfoArea;
    private FluidTankRenderer renderer;
    private FluidTankRenderer wasteRenderer;
    private TemperatureInfoArea temperatureInfoArea;

    private Minecraft minecraft;
    private MultiBufferSource.BufferSource bufferSource;

    public MultiBufferSource.BufferSource getBufferSource() {
        return bufferSource;
    }

    public RadioactiveGeneratorScreen(RadioactiveGeneratorMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        this.minecraft = getMinecraft();
    }

    @Override
    protected void init() {
        super.init();
        assignEnergyInfoArea(minecraft, getBufferSource());
        assignFluidRenderer();
        assignFluidWasteRenderer();
        assignTemperatureInfoArea(minecraft, getBufferSource());
    }

    private void assignFluidRenderer() {
        renderer = new FluidTankRenderer(256000, true, 16, 54);
    }

    private void assignFluidWasteRenderer() {
        wasteRenderer = new FluidTankRenderer(256000, true, 4, 54);
    }

    private void assignEnergyInfoArea(Minecraft minecraft, MultiBufferSource.BufferSource bufferSource) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        energyInfoArea = new EnergyInfoArea(minecraft, bufferSource, x + 154, y + 16, menu.blockEntity.getEnergyStorage());
    }

    private void assignTemperatureInfoArea(Minecraft minecraft, MultiBufferSource.BufferSource bufferSource) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        temperatureInfoArea = new TemperatureInfoArea(minecraft, bufferSource, x + 17, y + 16, menu.blockEntity.getTemperature());
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int pMouseX, int pMouseY) {

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        renderEnergyAreaTooltips(guiGraphics, pMouseX, pMouseY, x, y);
        renderFluidAreaTooltips(guiGraphics, pMouseX, pMouseY, x, y);
        renderFluidWasteAreaTooltips(guiGraphics, pMouseX, pMouseY, x, y);
        renderTemperatureAreaTooltips(guiGraphics, pMouseX, pMouseY, x, y);
    }


    private void renderFluidAreaTooltips(GuiGraphics guiGraphics, int pMouseX, int pMouseY, int x, int y) {
        if (isMouseAboveArea(pMouseX, pMouseY, x, y, 135, 16, 16, 54)) {
            List<Component> tooltip = renderer.getTooltip(menu.getFluidStack(), TooltipFlag.NORMAL);
            renderTooltip(guiGraphics, pMouseX - x, pMouseY - y);
        }
    }

    private void renderFluidWasteAreaTooltips(GuiGraphics guiGraphics, int pMouseX, int pMouseY, int x, int y) {
        if(isMouseAboveArea(pMouseX, pMouseY, x, y, 135, 16, 16, 54)) {
            renderTooltip(guiGraphics, pMouseX - x, pMouseY - y);
        }
    }

    private void renderEnergyAreaTooltips(GuiGraphics guiGraphics, int pMouseX, int pMouseY, int x, int y) {
        if(isMouseAboveArea(pMouseX, pMouseY, x, y, 154, 16, 4, 54)) {
            renderTooltip(guiGraphics,pMouseX - x, pMouseY - y);
        }
    }


    private void renderTemperatureAreaTooltips(GuiGraphics guiGraphics, int pMouseX, int pMouseY, int x, int y) {
        if(isMouseAboveArea(pMouseX, pMouseY, x, y, 154, 16, 4, 54)) {
            renderTooltip(guiGraphics,pMouseX - x, pMouseY - y);
        }
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        PoseStack pPoseStack = new PoseStack();

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(guiGraphics, x, y);
        energyInfoArea.draw(pPoseStack);
        renderer.render(pPoseStack, x + 134, y + 16, menu.getFluidStack());
        wasteRenderer.render(pPoseStack, x + 29, y + 16, menu.getFluidStackWaste());
        temperatureInfoArea.draw(pPoseStack);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 104, y + 44, 177, 0, 23, menu.getScaledProgress());
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }
}
