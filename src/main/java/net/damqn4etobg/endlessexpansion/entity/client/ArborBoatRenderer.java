package net.damqn4etobg.endlessexpansion.entity.client;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.entity.custom.ArborBoatEntity;
import net.damqn4etobg.endlessexpansion.entity.custom.ArborChestBoatEntity;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.Map;
import java.util.stream.Stream;

public class ArborBoatRenderer extends BoatRenderer {
    private final Map<ArborBoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public ArborBoatRenderer(EntityRendererProvider.Context pContext, boolean pChestBoat) {
        super(pContext, pChestBoat);
        this.boatResources = Stream.of(ArborBoatEntity.Type.values()).collect(ImmutableMap.toImmutableMap(type -> type,
                type -> Pair.of(new ResourceLocation(EndlessExpansion.MODID, getTextureLocation(type, pChestBoat)), this.createBoatModel(pContext, type, pChestBoat))));
    }

    private static String getTextureLocation(ArborBoatEntity.Type pType, boolean pChestBoat) {
        return pChestBoat ? "textures/entity/chest_boat/" + pType.getName() + ".png" : "textures/entity/boat/" + pType.getName() + ".png";
    }

    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context pContext, ArborBoatEntity.Type pType, boolean pChestBoat) {
        ModelLayerLocation modellayerlocation = pChestBoat ? ArborBoatRenderer.createChestBoatModelName(pType) : ArborBoatRenderer.createBoatModelName(pType);
        ModelPart modelpart = pContext.bakeLayer(modellayerlocation);
        return pChestBoat ? new ChestBoatModel(modelpart) : new BoatModel(modelpart);
    }

    public static ModelLayerLocation createBoatModelName(ArborBoatEntity.Type pType) {
        return createLocation("boat/" + pType.getName(), "main");
    }

    public static ModelLayerLocation createChestBoatModelName(ArborBoatEntity.Type pType) {
        return createLocation("chest_boat/" + pType.getName(), "main");
    }

    private static ModelLayerLocation createLocation(String pPath, String pModel) {
        return new ModelLayerLocation(new ResourceLocation(EndlessExpansion.MODID, pPath), pModel);
    }

    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
        if(boat instanceof ArborBoatEntity modBoat) {
            return this.boatResources.get(modBoat.getModVariant());
        } else if(boat instanceof ArborChestBoatEntity modChestBoatEntity) {
            return this.boatResources.get(modChestBoatEntity.getModVariant());
        } else {
            return null;
        }
    }
}
