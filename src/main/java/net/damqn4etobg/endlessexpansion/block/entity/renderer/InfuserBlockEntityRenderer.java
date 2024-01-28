package net.damqn4etobg.endlessexpansion.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.damqn4etobg.endlessexpansion.block.entity.InfuserBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class InfuserBlockEntityRenderer implements BlockEntityRenderer<InfuserBlockEntity> {

    public InfuserBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }
    @Override
    public void render(InfuserBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        ItemStack itemStackOutput = pBlockEntity.getRenderStackOutput();
        ItemStack itemStackInput1 = pBlockEntity.getRenderStackInput1();
        ItemStack itemStackInput2 = pBlockEntity.getRenderStackInput2();
        ItemStack itemStackLuminite = pBlockEntity.getRenderStackLuminite();

        Direction blockEnitityFacing = pBlockEntity.getBlockState().getValue(BlockStateProperties.FACING);

        //pBlockEntity.getBlockPos().north();
        //LUMINITE
        pPoseStack.pushPose();
        pPoseStack.translate(0.35f, 0.82f, 0.34f);
        pPoseStack.scale(0.22f, 0.22f, 0.22f);
        pPoseStack.mulPose(Axis.XP.rotationDegrees(270));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(blockEnitityFacing.getStepX() * 90));

        itemRenderer.renderStatic(itemStackLuminite, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pBlockEntity.getLevel(), 1);
        pPoseStack.popPose();
        //IS1
        pPoseStack.pushPose();
        switch (blockEnitityFacing) {
            case EAST:
                pPoseStack.mulPose(Axis.YP.rotationDegrees(90));
            case WEST:
                pPoseStack.mulPose(Axis.YP.rotationDegrees(270));
            case SOUTH:
                pPoseStack.mulPose(Axis.YP.rotationDegrees(180));
            case NORTH:
                pPoseStack.mulPose(Axis.YP.rotationDegrees(0));
        }
        pPoseStack.translate(0.35f, 0.82f, 0.65f);
        pPoseStack.scale(0.22f, 0.22f, 0.22f);
        pPoseStack.mulPose(Axis.XP.rotationDegrees(270));
        //pPoseStack.mulPose(Axis.ZP.rotationDegrees(blockEnitityFacing.getStepX() * 90));


        itemRenderer.renderStatic(itemStackInput1, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pBlockEntity.getLevel(), 1);
        pPoseStack.popPose();

        //IS2
        pPoseStack.pushPose();
        switch (blockEnitityFacing) {
            case EAST:
                pPoseStack.translate(0.78f, 0.82f, 0.65f);

            case WEST:
                pPoseStack.translate(0.78f, 0.82f, 0.65f);
                pPoseStack.mulPose(Axis.YP.rotationDegrees(270));
            case SOUTH:
                pPoseStack.translate(0.78f, 0.82f, 0.65f);
                pPoseStack.mulPose(Axis.YP.rotationDegrees(0));
            case NORTH:
                pPoseStack.translate(0.78f, 0.82f, 0.65f);
                pPoseStack.mulPose(Axis.YP.rotationDegrees(180));
        }
        pPoseStack.scale(0.22f, 0.22f, 0.22f);
        pPoseStack.mulPose(Axis.XP.rotationDegrees(270));

        itemRenderer.renderStatic(itemStackInput2, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pBlockEntity.getLevel(), 1);
        pPoseStack.popPose();

    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
