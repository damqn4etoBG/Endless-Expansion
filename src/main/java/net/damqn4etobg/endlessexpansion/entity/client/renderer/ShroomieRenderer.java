package net.damqn4etobg.endlessexpansion.entity.client.renderer;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.entity.client.layer.ModModelLayers;
import net.damqn4etobg.endlessexpansion.entity.client.model.ShroomieModel;
import net.damqn4etobg.endlessexpansion.entity.custom.ShroomieEntity;
import net.damqn4etobg.endlessexpansion.entity.variant.ShroomieVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class ShroomieRenderer extends MobRenderer<ShroomieEntity, ShroomieModel<ShroomieEntity>> {
    private static final Map<ShroomieVariant, ResourceLocation> LOCATION_BY_VARIANT = Util.make(Maps.newEnumMap(ShroomieVariant.class), map -> {
        map.put(ShroomieVariant.RED, ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/entity/shroomie/shroomie_red.png"));
        map.put(ShroomieVariant.BROWN, ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/entity/shroomie/shroomie_brown.png"));
        map.put(ShroomieVariant.DEV, ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/entity/shroomie/shroomie_dev.png"));
    });

    public ShroomieRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ShroomieModel<>(pContext.bakeLayer(ModModelLayers.SHROOMIE_LAYER)), 0.35f);
    }

    @Override
    public ResourceLocation getTextureLocation(ShroomieEntity pEntity) {
        return LOCATION_BY_VARIANT.get(pEntity.getVariant());
    }

    @Override
    public void render(ShroomieEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.scale(1.5f, 1.5f, 1.5f);
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
