package net.damqn4etobg.endlessexpansion.entity.client.renderer;

import net.damqn4etobg.endlessexpansion.entity.client.layer.EndlessModelLayers;
import net.damqn4etobg.endlessexpansion.entity.client.model.ShadowsteelArmorModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class ShadowsteelArmorRenderer {
    public static HumanoidModel<HumanoidRenderState> getArmorModel(Entity entity, ItemStack stack, EquipmentSlot slot) {
        Minecraft mc = Minecraft.getInstance();
        ModelPart backed = mc.getEntityModels().bakeLayer(EndlessModelLayers.SHADOWSTEEL_ARMOR_LAYER);
        return new ShadowsteelArmorModel(backed);
    }
}
