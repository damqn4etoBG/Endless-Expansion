package net.damqn4etobg.endlessexpansion.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.entity.client.layer.ModModelLayers;
import net.damqn4etobg.endlessexpansion.entity.client.model.AbyssalScourgeModel;
import net.damqn4etobg.endlessexpansion.entity.custom.AbyssalScourgeEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AbyssalScourgeRenderer extends MobRenderer<AbyssalScourgeEntity, AbyssalScourgeModel<AbyssalScourgeEntity>> {
    public AbyssalScourgeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AbyssalScourgeModel<>(pContext.bakeLayer(ModModelLayers.ABYSSAL_SCOURGE_LAYER)), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(AbyssalScourgeEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/entity/abyssal_scourge/abyssal_scourge.png");
    }

    @Override
    public void render(AbyssalScourgeEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.scale(1.5f, 1.5f, 1.5f);
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
