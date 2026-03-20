package net.damqn4etobg.endlessexpansion.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.damqn4etobg.endlessexpansion.entity.animations.ModAnimationDefinitions;
import net.damqn4etobg.endlessexpansion.entity.custom.ShroomieEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class ShroomieModel <T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart shroomie;
    private final ModelPart body;
    private final ModelPart torso;
    private final ModelPart head;
    private final ModelPart right_arm;
    private final ModelPart left_arm;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public ShroomieModel(ModelPart root) {
        this.shroomie = root.getChild("shroomie");
        this.body = this.shroomie.getChild("body");
        this.torso = this.body.getChild("torso");
        this.head = this.torso.getChild("head");
        this.right_arm = this.torso.getChild("right_arm");
        this.left_arm = this.torso.getChild("left_arm");
        this.left_leg = this.torso.getChild("left_leg");
        this.right_leg = this.torso.getChild("right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition shroomie = partdefinition.addOrReplaceChild("shroomie", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition body = shroomie.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 23).addBox(-3.0F, -16.0F, -2.0F, 6.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 11).addBox(-3.0F, -22.0F, -3.5F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.0F, -23.0F, -4.5F, 8.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 35).addBox(-3.0F, -24.0F, -3.5F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(30, 33).addBox(-4.0F, -20.0F, -4.5F, 8.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 34).addBox(-5.0F, -1.0F, -5.0F, 8.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -19.0F, -1.5F, 0.0F, 3.1416F, 0.0F));

        PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(32, 34).addBox(-7.0F, -2.0F, -1.0F, 8.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -18.0F, -3.5F, 0.0F, 1.5708F, 0.0F));

        PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(16, 34).addBox(-7.0F, -2.0F, -1.0F, 8.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -18.0F, 2.5F, 0.0F, -1.5708F, 0.0F));

        PartDefinition right_arm = torso.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(30, 22).addBox(3.0F, -8.0F, -2.0F, 2.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

        PartDefinition left_arm = torso.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 0).addBox(-5.0F, -8.0F, -2.0F, 2.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

        PartDefinition left_leg = torso.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(18, 23).addBox(-3.0F, -8.0F, -2.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_leg = torso.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(24, 11).addBox(0.0F, -8.0F, -2.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animateWalk(ModAnimationDefinitions.SHROOMIE_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(((ShroomieEntity) entity).idleAnimationState, ModAnimationDefinitions.SHROOMIE_IDLE, ageInTicks, 1f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        shroomie.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return shroomie;
    }
}
