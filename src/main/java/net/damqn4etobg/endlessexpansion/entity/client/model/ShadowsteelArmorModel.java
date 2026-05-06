package net.damqn4etobg.endlessexpansion.entity.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class ShadowsteelArmorModel extends HumanoidModel<HumanoidRenderState> {
    public ShadowsteelArmorModel(ModelPart root) {
        super(root, RenderTypes::entityTranslucent);
    }
}
