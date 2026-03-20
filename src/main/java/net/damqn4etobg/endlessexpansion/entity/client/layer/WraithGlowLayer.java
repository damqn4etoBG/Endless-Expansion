package net.damqn4etobg.endlessexpansion.entity.client.layer;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.entity.custom.WraithEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class WraithGlowLayer<T extends WraithEntity, M extends EntityModel<T>> extends EyesLayer<T, M> {
    // usually we need to create a black texture and only the eyes colored, but since the wraith's texture is already just black with red eyes, we can use it here
    private static final RenderType GLOW = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/entity/wraith/wraith.png"));

    public WraithGlowLayer(RenderLayerParent<T, M> pRenderer) {
        super(pRenderer);
    }

    @Override
    public RenderType renderType() {
        return GLOW;
    }
}
