package net.damqn4etobg.endlessexpansion.entity.client.model;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class SapphireDivingHelmetModel<T extends LivingEntity> extends TranslucentArmorModel<T> {
    private final ModelPart left_leg;
    private final ModelPart right_arm;
    private final ModelPart hat;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart right_leg;
    private final ModelPart left_boot;
    private final ModelPart right_boot;

    public SapphireDivingHelmetModel(ModelPart root) {
        super(root, "sapphire_diving_helmet", EquipmentSlot.HEAD); // Minecraft requires us to have all the parts to render
        this.left_leg = root.getChild("left_leg");
        this.right_arm = root.getChild("right_arm");
        this.hat = root.getChild("hat");
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.left_arm = root.getChild("left_arm");
        this.right_leg = root.getChild("right_leg");
        this.left_boot = root.getChild("left_boot");
        this.right_boot = root.getChild("right_boot");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 51).addBox(5.0F, -9.0F, -6.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(4, 51).addBox(-6.0F, -9.0F, -6.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 27).addBox(-6.0F, 1.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(44, 22).addBox(-5.0F, 1.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 9).addBox(-5.0F, -9.0F, -4.0F, 1.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(18, 9).addBox(4.0F, -9.0F, -4.0F, 1.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(22, 27).addBox(-6.0F, -10.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(36, 11).addBox(5.0F, 1.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(44, 33).addBox(4.0F, 1.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(36, 0).addBox(5.0F, -10.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(44, 44).addBox(-5.0F, 1.0F, -6.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 49).addBox(-4.0F, 1.0F, -5.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 38).addBox(-5.0F, -9.0F, 4.0F, 10.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 46).addBox(-5.0F, -10.0F, -6.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 51).addBox(5.0F, -9.0F, 5.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 49).addBox(-5.0F, 1.0F, 5.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 50).addBox(-4.0F, 1.0F, 4.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 48).addBox(-5.0F, -10.0F, 5.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 51).addBox(-6.0F, -9.0F, 5.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition left_boot = partdefinition.addOrReplaceChild("left_boot", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition right_boot = partdefinition.addOrReplaceChild("right_boot", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
    }

    @Override
    protected ResourceLocation getArmorLocation() {
        return ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/models/armor/sapphire_diving_helmet.png");
    }
}
