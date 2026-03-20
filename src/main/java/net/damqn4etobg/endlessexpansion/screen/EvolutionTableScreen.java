package net.damqn4etobg.endlessexpansion.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.item.wand.WandItem;
import net.damqn4etobg.endlessexpansion.item.wand.evolution.PlayerWandData;
import net.damqn4etobg.endlessexpansion.item.wand.evolution.WandEvolution;
import net.damqn4etobg.endlessexpansion.item.wand.evolution.WandEvolutions;
import net.damqn4etobg.endlessexpansion.networking.ModMessages;
import net.damqn4etobg.endlessexpansion.networking.packet.EvolutionTableC2SPacket;
import net.damqn4etobg.endlessexpansion.screen.gui.components.EvolutionButton;
import net.damqn4etobg.endlessexpansion.screen.gui.components.MultipleComponentButton;
import net.damqn4etobg.endlessexpansion.screen.menu.EvolutionTableMenu;
import net.damqn4etobg.endlessexpansion.util.ModChatStyles;
import net.damqn4etobg.endlessexpansion.util.RenderHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EvolutionTableScreen extends AbstractContainerScreen<EvolutionTableMenu> {
    private static final ResourceLocation INVENTORY_TEXTURE = ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID,"textures/gui/inventory_only.png");
    private static final ResourceLocation MAIN_TEXTURE = ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID,"textures/gui/evolution_table.png");
    private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.parse("textures/block/blackstone.png");
    private static final ResourceLocation VIGNETTE_LOCATION = ResourceLocation.parse("textures/misc/vignette.png");
    private static final int PADDING = 5;
    private float zoom = 1.0f;
    private float scrollX = 0;
    private float scrollY = 0;
    private final int viewWidth = 238;
    private final int viewHeight = 93;
    private final int tileSize = 32;
    private final List<EvolutionButton> evolutionButtons = new ArrayList<>();
    private boolean itemInSlot = false;
    private boolean hasUpdated = false;
    private MultipleComponentButton evolveButton;
    private WandEvolution selectedEvolution;
    private ItemStack lastStack = ItemStack.EMPTY;
    private int cost = 0;

    public EvolutionTableScreen(EvolutionTableMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.minecraft = getMinecraft();
    }

    @Override
    protected void init() {
        super.init();
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        ItemStack stack = menu.getBlockEntity().getItemHandler().getStackInSlot(0);
        itemInSlot = stack.getItem() instanceof WandItem;
        evolveButton = new MultipleComponentButton(x + 125, y + 84, 50, 20, Component.translatable("menu.endlessexpansion.evolution_table.evolve"),
                b -> {
                    if(itemInSlot) {
                        if(selectedEvolution != null && (minecraft.player.experienceLevel >= cost || minecraft.player.isCreative())) {
                            ModMessages.sendToServer(new EvolutionTableC2SPacket(selectedEvolution, minecraft.player.getUUID(), cost));
                            selectedEvolution = null;
                            minecraft.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ENCHANTMENT_TABLE_USE, 1f));
                            for(EvolutionButton btn : evolutionButtons) {
                                btn.setFocused(false);
                            }
                        }
                    }
                }, 200);
        this.addRenderableWidget(evolveButton);
        updateDescription();
        evolveButton.active = false;
        if(itemInSlot && evolutionButtons.isEmpty()) {
            createEvolutionButtons();
            hasUpdated = true;
        }
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        ItemStack stack = menu.getBlockEntity().getItemHandler().getStackInSlot(0);
        itemInSlot = stack.getItem() instanceof WandItem;

        if(itemInSlot && !hasUpdated) {
            evolutionButtons.clear();
            createEvolutionButtons();
            hasUpdated = true;
        } else if (!itemInSlot && hasUpdated) {
            evolutionButtons.clear();
            hasUpdated = false;
            selectedEvolution = null;
        }
        if(!ItemStack.matches(lastStack, stack)) {
            lastStack = stack.copy();
            evolutionButtons.clear();
            createEvolutionButtons();
            selectedEvolution = null;
        }

        evolveButton.active = itemInSlot && selectedEvolution != null;

        // cost management
        if(selectedEvolution != null) {
            ResourceLocation id = ForgeRegistries.ITEMS.getKey(stack.getItem());
            Collection<String> evosBeforeHighest = new ArrayList<>();
            if(id != null) evosBeforeHighest = PlayerWandData.getEvolutionsBeforeHighest(minecraft.player, id.toString());
            if(evosBeforeHighest.contains(selectedEvolution.getBaseId().toString())) {
                cost = 0;
            } else {
                cost = selectedEvolution.getXpCost();
            }
            WandItem item = (WandItem) stack.getItem();
            if(selectedEvolution.getBaseId().equals(item.getEvolution(stack).getBaseId())) cost = 0;
        }
        updateDescription();
    }

    private void updateDescription() {
        List<Component> components = new ArrayList<>();
        ItemStack stack = menu.getBlockEntity().getItemHandler().getStackInSlot(0);
        components.add(Component.translatable("menu.endlessexpansion.evolution_table.evolve.desc"));
        if(selectedEvolution != null) {
            components.add(selectedEvolution.getName());
            WandItem item = (WandItem) stack.getItem();
            if(!selectedEvolution.getBaseId().equals(item.getEvolution(stack).getBaseId())) {
                if(minecraft.player.experienceLevel >= cost || minecraft.player.isCreative()) {
                    components.add(Component.translatable("menu.endlessexpansion.evolution_table.evolve.cost", cost).withStyle(ChatFormatting.GREEN));
                } else {
                    components.add(Component.translatable("menu.endlessexpansion.evolution_table.evolve.cost", cost).withStyle(ChatFormatting.RED));
                }
                if(cost == 0) {
                    components.add(Component.translatable("menu.endlessexpansion.evolution_table.evolve.free").withStyle(ModChatStyles.ORANGE.withItalic(true)));
                }
            } else {
                components.add(Component.translatable("menu.endlessexpansion.evolution_table.evolve.cost", cost).withStyle(ChatFormatting.GRAY));
                components.add(Component.translatable("menu.endlessexpansion.evolution_table.evolve.same").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }
        evolveButton.setDescription(components);
    }

    private void createEvolutionButtons() {
        Collection<WandEvolution> evolutions = WandEvolutions.getWandEvolutions(menu.getBlockEntity().getItemHandler().getStackInSlot(0).getItem());
        if(evolutions != null) {
            int i = 0;
            for(WandEvolution evo : evolutions) {
                int x = 108 + (i % 5) * 40;
                EvolutionButton button;

                if(evo.getIcon() != null) {
                    button = new EvolutionButton(x, 35, b -> selectedEvolution = evo, evo.getIcon());
                } else {
                    button = new EvolutionButton(x, 35, b -> selectedEvolution = evo, evo.getIconTexture());
                }

                button.setName(Component.literal(evo.getName().getString()));
                button.setDescription(Component.literal(evo.getDescription().getString()));
                button.setCooldown(evo.getCooldown());
                evolutionButtons.add(button);
                i++;
            }
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int pMouseX, int pMouseY) {
        this.titleLabelY = -42;
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2 + 2;

        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 13421772, false);
        guiGraphics.drawString(this.font, playerInventoryTitle, inventoryLabelX, inventoryLabelY + 25, 13421772, false);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, INVENTORY_TEXTURE);
        RenderSystem.setShaderTexture(1, MAIN_TEXTURE);

        PoseStack pose = guiGraphics.pose();
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        int viewX = x - 31;
        int viewY = y - 21;
        guiGraphics.blit(INVENTORY_TEXTURE, x, y + 113 + PADDING - 10, 0, 0, imageWidth, imageHeight);
        guiGraphics.blit(MAIN_TEXTURE, x - 40, y - 30, 0, 0, 256, imageHeight);

        RenderHelper.enableScissor(viewX, viewY, viewWidth, viewHeight);
        pose.pushPose();
        pose.translate(viewX + scrollX, viewY + scrollY, 0);
        pose.scale(zoom, zoom, 1f);
        for(int tileX = -64 * tileSize / 8; tileX < viewWidth + tileSize; tileX += tileSize) {
            for (int tileY = -192; tileY < viewHeight + 192; tileY += tileSize) {
                guiGraphics.blit(BACKGROUND_TEXTURE, tileX, tileY, 0, 0.0F, 0.0F, viewWidth, viewHeight, 16, 16);
            }
        }
        if(!evolutionButtons.isEmpty() && evolutionButtons.size() > 1) {
            EvolutionButton baseButton = evolutionButtons.iterator().next();
            guiGraphics.fill(baseButton.getX(), baseButton.getY() + 8, baseButton.getX() + 20 + 20 * evolutionButtons.size(), baseButton.getY() + 12, -1);
        }
        pose.popPose();
        RenderSystem.disableScissor();

//        RenderSystem.enableBlend();
//        RenderSystem.blendFunc(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR);
//        guiGraphics.blit(VIGNETTE_LOCATION, viewX, viewY, 0, viewHeight, viewWidth, viewHeight);
//        RenderSystem.disableBlend();
//        RenderSystem.defaultBlendFunc();
        RenderHelper.drawVignette(guiGraphics, viewX, viewY, 0, viewHeight, viewWidth, viewHeight);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        PoseStack pose = pGuiGraphics.pose();
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        int viewX = x - 31;
        int viewY = y - 21;
        int scaledMouseX = (int) ((pMouseX - (viewX + scrollX)) / zoom);
        int scaledMouseY = (int) ((pMouseY - (viewY + scrollY)) / zoom);
        RenderHelper.enableScissor(viewX, viewY, viewWidth, viewHeight);
        pose.pushPose();
        pose.translate(viewX + scrollX, viewY + scrollY, 0);
        for(EvolutionButton button : evolutionButtons) {
            button.render(pGuiGraphics, scaledMouseX, scaledMouseY, pPartialTick, zoom);
        }
        RenderSystem.disableScissor();
        pose.popPose();

        pose.pushPose();
        pose.scale(zoom, zoom, 1f);

        for(EvolutionButton button : evolutionButtons) {
            if(button.isHovered()) {
                button.renderTooltip(pGuiGraphics, (int) (pMouseX / zoom), (int) (pMouseY / zoom));
            }
        }
        pose.popPose();
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        int viewX = x - 31;
        int viewY = y - 21;
        if(pMouseX >= viewX && pMouseX <= viewX + viewWidth && pMouseY >= viewY && pMouseY <= viewY + viewHeight) {
            float prevZoom = zoom;
            zoom = Mth.clamp(zoom + (float) pDelta * 0.1f, 0.75f, 1.5f);

            float mouseRelX = (float) (pMouseX - viewX);
            float mouseRelY = (float) (pMouseY - viewY);

            scrollX -= (mouseRelX / prevZoom - mouseRelX / zoom);
            scrollY -= (mouseRelY / prevZoom - mouseRelY / zoom);

            float gridWidth = tileSize * 10 * zoom;
            float gridHeight = tileSize * 10 * zoom;

            float maxScrollX = Math.max(0, (gridWidth - viewWidth));
            float maxScrollY = Math.max(0, (gridHeight - viewHeight));

            scrollX = Mth.clamp(scrollX, -maxScrollX, maxScrollX);
            scrollY = Mth.clamp(scrollY, -maxScrollY, maxScrollY / 2);
        }
        return super.mouseScrolled(pMouseX, pMouseY, pDelta);
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        int viewX = x - 31;
        int viewY = y - 21;
        if (pMouseX >= viewX && pMouseX <= viewX + viewWidth && pMouseY >= viewY && pMouseY <= viewY + viewHeight) {
            float gridWidth = tileSize * 10 * zoom;
            float gridHeight = tileSize * 10 * zoom;

            float maxScrollX = Math.max(0, (gridWidth - (float) viewWidth / 2));
            float maxScrollY = Math.max(0, (gridHeight - viewHeight) / 2);

            scrollX = Mth.clamp(scrollX + (float) pDragX, -maxScrollX, maxScrollX);
            scrollY = Mth.clamp(scrollY + (float) pDragY, -maxScrollY, maxScrollY);
            return true;
        }
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        for(EvolutionButton btn : evolutionButtons) {
            if(btn.isHovered()) {
                btn.onPress();
                btn.setFocused(true);
                for(EvolutionButton other : evolutionButtons) {
                    if(other != btn) other.setFocused(false);
                }
                return true;
            }
        }
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }
}
