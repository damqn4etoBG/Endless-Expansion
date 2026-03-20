package net.damqn4etobg.endlessexpansion.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.damqn4etobg.endlessexpansion.block.entity.EvolutionTableBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class EvolutionTableBERenderer implements BlockEntityRenderer<EvolutionTableBlockEntity> {
    private final BlockEntityRendererProvider.Context context;
    private int ticks = 0;

    public EvolutionTableBERenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(EvolutionTableBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        ItemRenderer renderer = context.getItemRenderer();
        ItemStack stack = blockEntity.getItemHandler().getStackInSlot(0);

        poseStack.pushPose();
        poseStack.translate(0.5, 0.75f, 0.5f);
        float rotation = 0f;

        while(rotation <= 45f) {
            ticks++;
            rotation = (float) ticks / 20;
            poseStack.mulPose(Axis.ZP.rotationDegrees(rotation));
        }

        renderer.renderStatic(stack, ItemDisplayContext.GROUND, packedLight, OverlayTexture.NO_OVERLAY, poseStack, bufferSource, blockEntity.getLevel(), 0);
        poseStack.popPose();
    }
}
