package net.damqn4etobg.endlessexpansion.entity.client.renderer;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class MysticalCobaltBoltRenderer extends ArrowRenderer {
    public MysticalCobaltBoltRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(Entity entity) {
        return ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/entity/projectile/mystical_cobalt_bolt.png");
    }
}
