package net.damqn4etobg.endlessexpansion.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class TranslucentArmorModel<T extends LivingEntity> extends HumanoidArmorModel<T> {
    // Huge thanks to https://github.com/Starexify/Cosmicore/
    public final String name;
    private final EquipmentSlot slot;

    public TranslucentArmorModel(ModelPart pRoot, String name, EquipmentSlot slot) {
        super(pRoot);
        this.name = name;
        this.slot = slot;
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        RenderType renderType = RenderType.entityTranslucent(getArmorLocation());
        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();

        VertexConsumer translucentBuffer = ItemRenderer.getArmorFoilBuffer(bufferSource, renderType, false, false);
        super.renderToBuffer(pPoseStack, translucentBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }

    protected ResourceLocation getArmorLocation() {
        String layer = slot == EquipmentSlot.LEGS ? "layer_2" : "layer_1";
        return ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/models/armor/" + name + "_" + layer + ".png");
    }
}