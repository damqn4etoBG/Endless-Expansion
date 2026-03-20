package net.damqn4etobg.endlessexpansion.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.entity.client.layer.ModModelLayers;
import net.damqn4etobg.endlessexpansion.entity.client.layer.WraithGlowLayer;
import net.damqn4etobg.endlessexpansion.entity.client.model.WraithModel;
import net.damqn4etobg.endlessexpansion.entity.custom.WraithEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WraithRenderer extends MobRenderer<WraithEntity, WraithModel<WraithEntity>> {
    public WraithRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new WraithModel<>(pContext.bakeLayer(ModModelLayers.WRAITH_LAYER)), 0.35f);
        this.addLayer(new WraithGlowLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(WraithEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/entity/wraith/wraith.png");
    }

//    @Override
//    protected @Nullable RenderType getRenderType(WraithEntity pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
//        return RenderType.entityTranslucent(getTextureLocation(pLivingEntity));
//    }

    @Override
    public void render(WraithEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.scale(1f, 1f, 1f);
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
