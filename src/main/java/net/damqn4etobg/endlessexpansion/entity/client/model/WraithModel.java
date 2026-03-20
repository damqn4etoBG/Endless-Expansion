package net.damqn4etobg.endlessexpansion.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.damqn4etobg.endlessexpansion.entity.animations.ModAnimationDefinitions;
import net.damqn4etobg.endlessexpansion.entity.custom.WraithEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.Entity;

public class WraithModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart wraith;
	private final ModelPart body;
	private final ModelPart torso;
	private final ModelPart head;
	private final ModelPart left_arm;
	private final ModelPart right_arm;

	public WraithModel(ModelPart root) {
		super(RenderType::entityTranslucent);
		this.wraith = root.getChild("wraith");
		this.body = this.wraith.getChild("body");
		this.torso = this.body.getChild("torso");
		this.head = this.body.getChild("head");
		this.left_arm = this.body.getChild("left_arm");
		this.right_arm = this.body.getChild("right_arm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition wraith = partdefinition.addOrReplaceChild("wraith", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = wraith.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(58, 30).addBox(0.0F, -4.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(24, 58).addBox(-2.0F, -4.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(24, 51).addBox(-5.0F, -7.0F, -2.0F, 10.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.0F, -23.0F, -3.0F, 10.0F, 16.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(24, 39).addBox(2.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(24, 45).addBox(-4.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 22).addBox(-4.25F, -32.0F, -4.0F, 9.0F, 9.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(34, 13).addBox(-6.25F, -32.0F, -5.0F, 2.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(34, 32).addBox(4.75F, -32.0F, -5.0F, 2.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 39).addBox(3.75F, -34.0F, -5.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(32, 58).addBox(-2.25F, -39.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(38, 58).addBox(-3.25F, -37.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(58, 40).addBox(2.75F, -37.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(58, 34).addBox(1.75F, -39.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 51).addBox(-5.25F, -34.0F, -5.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(32, 0).addBox(-3.25F, -35.0F, -5.0F, 7.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(58, 13).addBox(-1.0F, 0.0F, -1.5F, 3.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -23.0F, 0.0F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(52, 51).addBox(-2.0F, 0.0F, -1.5F, 3.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -23.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animateWalk(ModAnimationDefinitions.WRAITH_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);

		if(((WraithEntity) entity).getAttackTicks() > 0) {
			this.animate(((WraithEntity) entity).attackAnimationState, ModAnimationDefinitions.WRAITH_ATTACK, ageInTicks, 1f);
		} else {
			this.animate(((WraithEntity) entity).idleAnimationState, ModAnimationDefinitions.WRAITH_IDLE, ageInTicks, 1f);
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		wraith.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return wraith;
	}
}