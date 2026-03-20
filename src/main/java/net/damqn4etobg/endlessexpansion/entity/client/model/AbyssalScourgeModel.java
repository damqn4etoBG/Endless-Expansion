package net.damqn4etobg.endlessexpansion.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.damqn4etobg.endlessexpansion.entity.animations.ModAnimationDefinitions;
import net.damqn4etobg.endlessexpansion.entity.custom.AbyssalScourgeEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class AbyssalScourgeModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart abyssal_scourge;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart upper_jaw;
    private final ModelPart lower_jaw;
    private final ModelPart torso;
    private final ModelPart segment_1;
    private final ModelPart left_upper_tentacle;
    private final ModelPart right_upper_tentacle;
    private final ModelPart left_lower_tentacle;
    private final ModelPart right_lower_tentacle;
    private final ModelPart segment_2;
    private final ModelPart segment_3;
    private final ModelPart segment_4;
    private final ModelPart segment_5;
    private final ModelPart segment_6;
    private final ModelPart segment_7;
    private final ModelPart segment_8;
    private final ModelPart tail;

    public AbyssalScourgeModel(ModelPart root) {
        this.abyssal_scourge = root.getChild("abyssal_scourge");
        this.body = this.abyssal_scourge.getChild("body");
        this.head = this.body.getChild("head");
        this.upper_jaw = this.head.getChild("upper_jaw");
        this.lower_jaw = this.head.getChild("lower_jaw");
        this.torso = this.body.getChild("torso");
        this.segment_1 = this.torso.getChild("segment_1");
        this.left_upper_tentacle = this.segment_1.getChild("left_upper_tentacle");
        this.right_upper_tentacle = this.segment_1.getChild("right_upper_tentacle");
        this.left_lower_tentacle = this.segment_1.getChild("left_lower_tentacle");
        this.right_lower_tentacle = this.segment_1.getChild("right_lower_tentacle");
        this.segment_2 = this.torso.getChild("segment_2");
        this.segment_3 = this.torso.getChild("segment_3");
        this.segment_4 = this.torso.getChild("segment_4");
        this.segment_5 = this.torso.getChild("segment_5");
        this.segment_6 = this.torso.getChild("segment_6");
        this.segment_7 = this.torso.getChild("segment_7");
        this.segment_8 = this.torso.getChild("segment_8");
        this.tail = this.segment_8.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition abyssal_scourge = partdefinition.addOrReplaceChild("abyssal_scourge", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = abyssal_scourge.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition upper_jaw = head.addOrReplaceChild("upper_jaw", CubeListBuilder.create().texOffs(0, 41).addBox(-10.0F, -27.0F, -34.0F, 19.0F, 7.0F, 23.0F, new CubeDeformation(0.0F))
                .texOffs(84, 31).addBox(-9.0F, -29.0F, -31.0F, 17.0F, 2.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(136, 129).addBox(-8.0F, -29.0F, -33.0F, 15.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(172, 119).addBox(-1.0F, -35.0F, -27.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(52, 134).addBox(-1.0F, -36.0F, -23.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(150, 61).addBox(-1.0F, -36.0F, -21.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 154).addBox(-1.0F, -36.0F, -20.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(56, 154).addBox(-1.0F, -36.0F, -19.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 131).addBox(-1.0F, -36.0F, -18.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(150, 25).addBox(-1.0F, -31.0F, -29.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(150, 29).addBox(-1.0F, -30.0F, -30.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(150, 66).addBox(-1.0F, -34.0F, -28.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(124, 191).addBox(-1.0F, -37.0F, -25.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(60, 127).addBox(-1.0F, -36.0F, -26.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 203).addBox(-1.0F, -38.0F, -24.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(204, 71).addBox(-1.0F, -39.0F, -23.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(60, 131).addBox(-1.0F, -31.0F, -23.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(146, 175).addBox(5.0F, -33.0F, -24.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(124, 202).addBox(5.0F, -35.0F, -22.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(150, 53).addBox(5.0F, -31.0F, -26.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(56, 157).addBox(5.0F, -32.0F, -25.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(68, 127).addBox(5.0F, -34.0F, -20.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(154, 61).addBox(5.0F, -34.0F, -18.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(154, 69).addBox(5.0F, -34.0F, -17.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(154, 67).addBox(5.0F, -31.0F, -20.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 141).addBox(5.0F, -34.0F, -23.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(154, 29).addBox(5.0F, -30.0F, -27.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(78, 204).addBox(-7.0F, -35.0F, -22.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(50, 158).addBox(-7.0F, -34.0F, -17.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 149).addBox(-7.0F, -34.0F, -20.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(154, 64).addBox(-7.0F, -34.0F, -18.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 183).addBox(-7.0F, -33.0F, -24.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(52, 145).addBox(-7.0F, -34.0F, -23.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(46, 158).addBox(-7.0F, -31.0F, -20.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(42, 158).addBox(-7.0F, -32.0F, -25.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(150, 57).addBox(-7.0F, -31.0F, -26.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(38, 158).addBox(-7.0F, -30.0F, -27.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(84, 53).addBox(-9.0F, -21.0F, -34.0F, 17.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(136, 119).addBox(-9.0F, -26.0F, -35.0F, 17.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(196, 55).addBox(-8.0F, -19.0F, -32.0F, 0.0F, 3.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(196, 39).addBox(7.0F, -19.0F, -32.0F, 0.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = upper_jaw.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(68, 179).addBox(-1.0F, -3.0F, -7.0F, 0.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.0F, -31.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition lower_jaw = head.addOrReplaceChild("lower_jaw", CubeListBuilder.create().texOffs(136, 103).addBox(-9.0F, -9.0F, -33.0F, 17.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(118, 133).addBox(-10.0F, -7.0F, -33.0F, 19.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(142, 71).addBox(-9.0F, -5.0F, -33.0F, 17.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(136, 126).addBox(-9.0F, -7.0F, -34.0F, 17.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(98, 191).addBox(-8.0F, -12.0F, -32.0F, 0.0F, 3.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(196, 23).addBox(7.0F, -12.0F, -32.0F, 0.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r2 = lower_jaw.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(38, 179).addBox(-1.0F, -3.0F, -7.0F, 0.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -31.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition segment_1 = torso.addOrReplaceChild("segment_1", CubeListBuilder.create().texOffs(74, 103).addBox(-11.0F, -24.0F, -19.0F, 21.0F, 20.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_upper_tentacle = segment_1.addOrReplaceChild("left_upper_tentacle", CubeListBuilder.create().texOffs(38, 160).addBox(25.0F, -22.0F, -7.0F, 4.0F, 4.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(174, 199).addBox(26.0F, -22.0F, 8.0F, 2.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(174, 86).addBox(26.0F, -21.0F, 18.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(188, 15).addBox(10.0F, -22.0F, -17.0F, 13.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(98, 179).addBox(21.0F, -22.0F, -13.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(60, 120).addBox(23.0F, -22.0F, -10.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_upper_tentacle = segment_1.addOrReplaceChild("right_upper_tentacle", CubeListBuilder.create().texOffs(184, 139).addBox(-24.0F, -22.0F, -17.0F, 13.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(60, 106).addBox(-26.0F, -22.0F, -13.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(60, 113).addBox(-28.0F, -22.0F, -10.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(158, 50).addBox(-30.0F, -22.0F, -7.0F, 4.0F, 4.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(54, 197).addBox(-29.0F, -22.0F, 8.0F, 2.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(142, 86).addBox(-29.0F, -21.0F, 18.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_lower_tentacle = segment_1.addOrReplaceChild("left_lower_tentacle", CubeListBuilder.create().texOffs(76, 160).addBox(25.0F, -11.0F, -7.0F, 4.0F, 4.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(198, 199).addBox(26.0F, -11.0F, 8.0F, 2.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(114, 175).addBox(26.0F, -10.0F, 18.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(190, 168).addBox(10.0F, -11.0F, -17.0F, 13.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(156, 204).addBox(21.0F, -11.0F, -13.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(142, 204).addBox(23.0F, -11.0F, -10.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_lower_tentacle = segment_1.addOrReplaceChild("right_lower_tentacle", CubeListBuilder.create().texOffs(158, 149).addBox(-30.0F, -11.0F, -7.0F, 4.0F, 4.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(198, 102).addBox(-29.0F, -11.0F, 8.0F, 2.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(158, 168).addBox(-29.0F, -10.0F, 18.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(206, 90).addBox(-26.0F, -11.0F, -13.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(190, 176).addBox(-24.0F, -11.0F, -17.0F, 13.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(206, 83).addBox(-28.0F, -11.0F, -10.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition segment_2 = torso.addOrReplaceChild("segment_2", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -23.0F, -9.0F, 19.0F, 18.0F, 23.0F, new CubeDeformation(0.0F))
                .texOffs(118, 149).addBox(-1.0F, -29.0F, -8.0F, 0.0F, 6.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition segment_3 = torso.addOrReplaceChild("segment_3", CubeListBuilder.create().texOffs(74, 71).addBox(-10.0F, -22.0F, 14.0F, 18.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(146, 184).addBox(-1.0F, -28.0F, 15.0F, 0.0F, 6.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition segment_4 = torso.addOrReplaceChild("segment_4", CubeListBuilder.create().texOffs(0, 71).addBox(-9.0F, -21.0F, 30.0F, 16.0F, 14.0F, 21.0F, new CubeDeformation(0.0F))
                .texOffs(150, 0).addBox(-1.0F, -27.0F, 31.0F, 0.0F, 6.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition segment_5 = torso.addOrReplaceChild("segment_5", CubeListBuilder.create().texOffs(0, 106).addBox(-8.0F, -20.0F, 51.0F, 14.0F, 12.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(184, 119).addBox(-1.0F, -26.0F, 52.0F, 0.0F, 6.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition segment_6 = torso.addOrReplaceChild("segment_6", CubeListBuilder.create().texOffs(84, 0).addBox(-7.0F, -19.0F, 67.0F, 12.0F, 10.0F, 21.0F, new CubeDeformation(0.0F))
                .texOffs(0, 158).addBox(-1.0F, -25.0F, 68.0F, 0.0F, 6.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition segment_7 = torso.addOrReplaceChild("segment_7", CubeListBuilder.create().texOffs(0, 134).addBox(-6.0F, -18.0F, 88.0F, 10.0F, 8.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 183).addBox(-1.0F, -24.0F, 89.0F, 0.0F, 6.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition segment_8 = torso.addOrReplaceChild("segment_8", CubeListBuilder.create().texOffs(60, 133).addBox(-5.0F, -17.0F, 104.0F, 8.0F, 6.0F, 21.0F, new CubeDeformation(0.0F))
                .texOffs(158, 25).addBox(-1.0F, -23.0F, 105.0F, 0.0F, 6.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tail = segment_8.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(196, 160).addBox(3.0F, -15.0F, 123.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(78, 197).addBox(-10.0F, -15.0F, 123.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(174, 184).addBox(-10.0F, -15.0F, 128.0F, 3.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(196, 147).addBox(-12.0F, -15.0F, 134.0F, 2.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(188, 0).addBox(5.0F, -15.0F, 128.0F, 3.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(28, 197).addBox(8.0F, -15.0F, 134.0F, 2.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animateWalk(ModAnimationDefinitions.ABYSSAL_SCOURGE_SWIM, limbSwing, limbSwingAmount, 2f, 2.5f);

        if(((AbyssalScourgeEntity) entity).getAttackTicks() > 0) {
            this.animate(((AbyssalScourgeEntity) entity).attackAnimationState, ModAnimationDefinitions.ABYSSAL_SCOURGE_ATTACK, ageInTicks, 1f);
        } else {
            this.animate(((AbyssalScourgeEntity) entity).idleAnimationState, ModAnimationDefinitions.ABYSSAL_SCOURGE_IDLE, ageInTicks, 1f);
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        abyssal_scourge.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return abyssal_scourge;
    }
}
