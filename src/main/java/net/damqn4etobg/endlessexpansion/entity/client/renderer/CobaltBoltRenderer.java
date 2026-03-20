package net.damqn4etobg.endlessexpansion.entity.client.renderer;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class CobaltBoltRenderer extends ArrowRenderer {
    public CobaltBoltRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(Entity entity) {
        return ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/entity/projectile/cobalt_bolt.png");
    }
}
