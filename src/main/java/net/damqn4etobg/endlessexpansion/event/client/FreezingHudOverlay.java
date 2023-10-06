package net.damqn4etobg.endlessexpansion.event.client;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class FreezingHudOverlay {
    private static final ResourceLocation FILLIED_FREEZE = new ResourceLocation(EndlessExpansion.MODID,
            "textures/freeze/filled_freeze.png");

    private static final ResourceLocation EMPTY_FREEZE = new ResourceLocation(EndlessExpansion.MODID,
            "textures/freeze/empty_freeze.png");

    public static final IGuiOverlay HUD_FREEZE = ((gui, guiGraphics, partialTick, width, height) -> {
//        int x = width / 2;
//        int y = height;
//
//        RenderSystem.setShader(GameRenderer::getPositionTexShader);
//        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//        RenderSystem.setShaderTexture(0, EMPTY_FREEZE);
//        for(int i = 0; i < 10; i++) {
//            guiGraphics.blit(EMPTY_FREEZE,x - 94 + (i * 9), y - 54,0,0,12,12,
//                    12,12);
//        }
//
//        RenderSystem.setShaderTexture(0, FILLIED_FREEZE);
//        for(int i = 0; i < 10; i++) {
//            if(ClientFreezeData.getPlayerFreeze() > i) {
//               guiGraphics.blit(FILLIED_FREEZE,x - 94 + (i * 9),y - 54,0,0,12,12,
//                        12,12);
//            } else {
//                break;
//            }
//        }
   });
}
