package net.damqn4etobg.endlessexpansion.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.entity.custom.WraithEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WraithRenderer extends MobRenderer<WraithEntity, WraithModel<WraithEntity>> {
    public WraithRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new WraithModel<>(pContext.bakeLayer(ModModelLayers.WRAITH_LAYER)), 0.35f);
    }

    @Override
    public ResourceLocation getTextureLocation(WraithEntity pEntity) {
        return new ResourceLocation(EndlessExpansion.MODID, "textures/entity/wraith.png");
    }

    @Override
    public void render(WraithEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.scale(1.5f, 1.5f, 1.5f);
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
