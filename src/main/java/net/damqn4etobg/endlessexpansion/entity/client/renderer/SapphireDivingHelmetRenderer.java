package net.damqn4etobg.endlessexpansion.entity.client.renderer;

import net.damqn4etobg.endlessexpansion.entity.client.layer.ModModelLayers;
import net.damqn4etobg.endlessexpansion.entity.client.model.SapphireDivingHelmetModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class SapphireDivingHelmetRenderer {
    public static HumanoidModel<LivingEntity> getArmorModel(Entity entity, ItemStack stack, EquipmentSlot slot) {
        Minecraft minecraft = Minecraft.getInstance();
        ModelPart baked = minecraft.getEntityModels().bakeLayer(ModModelLayers.SAPPHIRE_DIVING_HELMET_LAYER);
        return new SapphireDivingHelmetModel<>(baked);
    }
}
