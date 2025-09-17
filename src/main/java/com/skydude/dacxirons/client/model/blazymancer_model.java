package com.skydude.dacxirons.client.model;// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class blazymancer_model<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("dacxirons", "blazymancer_model"), "main");
	public final ModelPart head;
	public final ModelPart body;
	public final ModelPart left_arm;
	public final ModelPart right_arm;

	public blazymancer_model(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.left_arm = root.getChild("left_arm");
		this.right_arm = root.getChild("right_arm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(27, 89).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.7F))
		.texOffs(32, 112).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.0F))
		.texOffs(0, 112).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.75F))
		.texOffs(24, 21).addBox(-4.0F, -11.0F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hat_r1 = head.addOrReplaceChild("hat_r1", CubeListBuilder.create().texOffs(44, 87).addBox(-9.4603F, 0.8244F, -10.4069F, 21.0F, 1.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7132F, -8.0145F, -0.4814F, 0.0873F, 0.0F, 0.0F));

		PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(75, 13).addBox(4.2409F, 0.1163F, -0.3811F, 5.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(4.8909F, -3.5365F, -1.4069F, 0.9819F, 0.4504F, -0.1387F));

		PartDefinition head_r2 = head.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(74, 5).addBox(-0.6561F, 2.0609F, -0.5885F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.8909F, -3.5365F, -1.4069F, 0.5309F, 0.9522F, -0.802F));

		PartDefinition head_r3 = head.addOrReplaceChild("head_r3", CubeListBuilder.create().texOffs(76, 5).addBox(-0.4693F, -1.1267F, -0.0581F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(4.8909F, -3.5365F, -1.4069F, 1.0456F, -0.0728F, 0.1864F));

		PartDefinition head_r4 = head.addOrReplaceChild("head_r4", CubeListBuilder.create().texOffs(79, 20).addBox(3.5843F, 6.086F, 0.6973F, 5.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(4.8909F, -3.5365F, -1.4069F, 0.421F, 1.1629F, -0.88F));

		PartDefinition hat_r2 = head.addOrReplaceChild("hat_r2", CubeListBuilder.create().texOffs(116, 7).addBox(-0.9485F, -6.338F, -6.6727F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(0.0F, -12.7266F, 2.8578F, -1.362F, -0.0268F, 0.0028F));

		PartDefinition hat_r3 = head.addOrReplaceChild("hat_r3", CubeListBuilder.create().texOffs(48, 16).addBox(-2.0F, -5.8846F, -4.2876F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, -12.7266F, 2.8578F, -0.6109F, 0.0F, 0.0F));

		PartDefinition hat_r4 = head.addOrReplaceChild("hat_r4", CubeListBuilder.create().texOffs(56, 28).addBox(-3.0F, -3.0259F, -4.9715F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, -12.7266F, 2.8578F, -0.1309F, 0.0F, 0.0F));

		PartDefinition hat_r5 = head.addOrReplaceChild("hat_r5", CubeListBuilder.create().texOffs(96, 27).addBox(-4.0F, 1.9982F, -6.2859F, 8.0F, 5.0F, 8.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -14.7266F, 1.8578F, 0.0436F, 0.0F, 0.0F));

		PartDefinition hat_r6 = head.addOrReplaceChild("hat_r6", CubeListBuilder.create().texOffs(116, 7).mirror().addBox(-1.0515F, -6.338F, -6.6727F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.4F)).mirror(false), PartPose.offsetAndRotation(0.0F, -12.7266F, 2.8578F, -1.362F, 0.0268F, -0.0028F));

		PartDefinition head_r5 = head.addOrReplaceChild("head_r5", CubeListBuilder.create().texOffs(79, 20).mirror().addBox(-8.5843F, 6.086F, 0.6973F, 5.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)).mirror(false), PartPose.offsetAndRotation(-4.8909F, -3.5365F, -1.4069F, 0.421F, -1.1629F, 0.88F));

		PartDefinition head_r6 = head.addOrReplaceChild("head_r6", CubeListBuilder.create().texOffs(75, 13).mirror().addBox(-9.2409F, 0.1163F, -0.3811F, 5.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)).mirror(false), PartPose.offsetAndRotation(-4.8909F, -3.5365F, -1.4069F, 0.9819F, -0.4504F, 0.1387F));

		PartDefinition head_r7 = head.addOrReplaceChild("head_r7", CubeListBuilder.create().texOffs(74, 5).mirror().addBox(-3.3439F, 2.0609F, -0.5885F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.8909F, -3.5365F, -1.4069F, 0.5309F, -0.9522F, 0.802F));

		PartDefinition head_r8 = head.addOrReplaceChild("head_r8", CubeListBuilder.create().texOffs(76, 5).mirror().addBox(-3.5307F, -1.1267F, -0.0581F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(-4.8909F, -3.5365F, -1.4069F, 1.0456F, 0.0728F, -0.1864F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(26, 50).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(25, 70).mirror().addBox(-3.0F, -3.5F, -0.5F, 6.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 11.5F, 2.5F, 2.7925F, 0.0F, -3.1416F));

		PartDefinition body_r2 = body.addOrReplaceChild("body_r2", CubeListBuilder.create().texOffs(47, 41).mirror().addBox(-2.0F, -2.5F, -4.0F, 2.0F, 4.0F, 9.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -0.5F, -1.0F, 0.0872F, 0.0038F, -0.4798F));

		PartDefinition body_r3 = body.addOrReplaceChild("body_r3", CubeListBuilder.create().texOffs(70, 39).mirror().addBox(-2.0221F, 0.4658F, -2.0F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.75F)).mirror(false), PartPose.offsetAndRotation(3.5F, 10.5F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition body_r4 = body.addOrReplaceChild("body_r4", CubeListBuilder.create().texOffs(70, 39).addBox(-0.9779F, 0.4658F, -2.0F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offsetAndRotation(-3.5F, 10.5F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition body_r5 = body.addOrReplaceChild("body_r5", CubeListBuilder.create().texOffs(25, 70).addBox(-3.0F, -3.5F, -0.5F, 6.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.5F, -2.5F, -0.3054F, 0.0F, 0.0F));

		PartDefinition body_r6 = body.addOrReplaceChild("body_r6", CubeListBuilder.create().texOffs(25, 70).addBox(-3.0F, -3.5F, -0.5F, 6.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.5F, 2.5F, 2.7925F, 0.0F, 3.1416F));

		PartDefinition body_r7 = body.addOrReplaceChild("body_r7", CubeListBuilder.create().texOffs(47, 41).addBox(0.0F, -2.5F, -4.0F, 2.0F, 4.0F, 9.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(3.0F, -0.5F, -1.0F, 0.0872F, -0.0038F, 0.4798F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition left_arm_r1 = left_arm.addOrReplaceChild("left_arm_r1", CubeListBuilder.create().texOffs(0, 42).addBox(-4.0F, -4.0F, -3.0F, 7.0F, 8.0F, 6.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(1.0F, 2.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition left_arm_r2 = left_arm.addOrReplaceChild("left_arm_r2", CubeListBuilder.create().texOffs(50, 54).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition right_arm_r1 = right_arm.addOrReplaceChild("right_arm_r1", CubeListBuilder.create().texOffs(0, 42).mirror().addBox(-3.0F, -4.0F, -3.0F, 7.0F, 8.0F, 6.0F, new CubeDeformation(-0.2F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition right_arm_r2 = right_arm.addOrReplaceChild("right_arm_r2", CubeListBuilder.create().texOffs(50, 54).mirror().addBox(-3.0F, -3.0F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}