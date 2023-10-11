package net.damqn4etobg.endlessexpansion.event.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.dimension.ModDimensions;
import net.damqn4etobg.endlessexpansion.tag.ModTags;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class FreezingHudOverlay {

    private static final ResourceLocation FILLIED_FREEZE = new ResourceLocation(EndlessExpansion.MODID,
            "textures/freeze/filled_freeze.png");

    private static final ResourceLocation EMPTY_FREEZE = new ResourceLocation(EndlessExpansion.MODID,
            "textures/freeze/empty_freeze.png");

    private static final int SHAKING_THRESHOLD = 7;
    private static boolean isShaking = false;
    private static double shakeOffsetX = 0.0;
    private static double shakeOffsetY = 0.0;

    public static final IGuiOverlay HUD_FREEZE = ((gui, guiGraphics, partialTick, width, height) -> {
        BlockPos playerPos = gui.getMinecraft().player.blockPosition();
        Holder<Biome> holder = gui.getMinecraft().player.level().getBiome(playerPos);

        boolean inSpecificBiome = holder.is(ModTags.Biomes.IS_FROZEN_WASTES);

        if (gui.getMinecraft().player != null && gui.getMinecraft().player.getAbilities().mayfly) {
            return;
        }

        if (gui.getMinecraft().player != null && gui.getMinecraft().player.level().dimension() != ModDimensions.WORLD_BEYOND_LEVEL_KEY) {
            return;
        }

        if (gui.getMinecraft().player != null && !inSpecificBiome) {
            return;
        }

        int x = width / 2;
        int y = height;

//        if (ClientFreezeData.getPlayerFreeze() >= SHAKING_THRESHOLD) {
//            // Start shaking when freeze level is 7 or higher
//            if (!isShaking) {
//                isShaking = true;
//                shakeOffsetX = Math.random() * 2.0 - 1.0;
//                shakeOffsetY = Math.random() * 2.0 - 1.0;
//            }
//            x += shakeOffsetX;
//            y += shakeOffsetY;
//        } else {
//            // Reset shaking
//            isShaking = false;
//            shakeOffsetX = 0.0;
//            shakeOffsetY = 0.0;
//        }

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, EMPTY_FREEZE);
        for (int i = 0; i < 10; i++) {
            if(!hasAnyArmorEquipped(gui.getMinecraft().player)) {
                guiGraphics.blit(EMPTY_FREEZE, x - 96 + (i * 9), y - 50, 0, 0, 10, 10,
                        10, 10);
            } else {
                guiGraphics.blit(EMPTY_FREEZE, x - 96 + (i * 9), y - 50 - 10, 0, 0, 10, 10,
                        10, 10);
            }
        }

        RenderSystem.setShaderTexture(0, FILLIED_FREEZE);
            for (int i = 0; i < 10; i++) {
                if (ClientFreezeData.getPlayerFreeze() > i) {
                    if(!hasAnyArmorEquipped(gui.getMinecraft().player)) {
                        guiGraphics.blit(FILLIED_FREEZE, x - 96 + (i * 9), y - 50, 0, 0, 10, 10,
                                10, 10);
                    } else {
                        guiGraphics.blit(FILLIED_FREEZE, x - 96 + (i * 9), y - 50 - 10, 0, 0, 10, 10,
                                10, 10);
                    }
                } else {
                    break;
                }
            }
    });

    private static boolean hasAnyArmorEquipped(Player player) {
        // Check each armor slot (head, chest, legs, and feet)
        for (ItemStack stack : player.getArmorSlots()) {
            if (!stack.isEmpty() && stack.getItem() instanceof ArmorItem) {
                return true;
            }
        }
        return false;
    }


}
