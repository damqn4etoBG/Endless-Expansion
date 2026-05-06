package net.damqn4etobg.endlessexpansion.datagen;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.item.EndlessArmorMaterials;
import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.function.BiConsumer;

public class EndlessEquipmentInfoProvider extends EquipmentAssetProvider {
    public EndlessEquipmentInfoProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void registerModels(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output) {
        output.accept(EndlessArmorMaterials.SHADOWSTEEL.assetId(), EquipmentClientInfo.builder()
                .addHumanoidLayers(Identifier.fromNamespaceAndPath(EndlessExpansion.MODID, "shadowsteel"))
                .build());
    }
}
