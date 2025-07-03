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

public class crimson_spell_model<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("dacxirons", "crimson_spell_model"), "main");
	public final ModelPart head;
	public final ModelPart body;
	public final ModelPart left_arm;
	public final ModelPart right_arm;
	public final ModelPart right_leg;
	public final ModelPart left_leg;
	public final ModelPart left_shoe;
	public final ModelPart right_shoe;

	public crimson_spell_model(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.left_arm = root.getChild("left_arm");
		this.right_arm = root.getChild("right_arm");
		this.right_leg = root.getChild("right_leg");
		this.left_leg = root.getChild("left_leg");
		this.left_shoe = root.getChild("left_shoe");
		this.right_shoe = root.getChild("right_shoe");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(29, 112).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.57F))
		.texOffs(64, 103).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 11.0F, 8.0F, new CubeDeformation(0.75F))
		.texOffs(96, 103).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 11.0F, 8.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(89, 100).addBox(-1.7823F, -0.6428F, -1.5853F, 3.0F, 3.0F, 4.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(-6.3636F, -1.7438F, -2.2166F, 0.2798F, -0.5653F, 1.57F));

		PartDefinition head_r2 = head.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(58, 84).addBox(-1.111F, -0.3702F, -5.4866F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(-6.3636F, -1.7438F, -2.2166F, 0.0617F, -0.5653F, 1.57F));

		PartDefinition head_r3 = head.addOrReplaceChild("head_r3", CubeListBuilder.create().texOffs(55, 97).addBox(-2.0457F, 0.8262F, -0.5973F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3636F, -1.7438F, -2.2166F, 1.1108F, -0.3633F, 1.6625F));

		PartDefinition head_r4 = head.addOrReplaceChild("head_r4", CubeListBuilder.create().texOffs(72, 87).mirror().addBox(-6.9491F, 0.1351F, -0.5642F, 6.0F, 3.0F, 3.0F, new CubeDeformation(-0.6F)).mirror(false), PartPose.offsetAndRotation(-7.7557F, -6.3658F, 4.2905F, -2.6659F, 1.0879F, -0.6199F));

		PartDefinition head_r5 = head.addOrReplaceChild("head_r5", CubeListBuilder.create().texOffs(88, 92).mirror().addBox(-2.154F, -0.4197F, -0.5695F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.7557F, -6.3658F, 4.2905F, -2.6333F, 0.6013F, -0.4981F));

		PartDefinition head_r6 = head.addOrReplaceChild("head_r6", CubeListBuilder.create().texOffs(84, 77).mirror().addBox(0.4164F, 0.113F, -1.5695F, 6.0F, 5.0F, 5.0F, new CubeDeformation(-0.6F)).mirror(false), PartPose.offsetAndRotation(-7.7557F, -6.3658F, 4.2905F, -2.379F, 0.0864F, 0.2049F));

		PartDefinition head_r7 = head.addOrReplaceChild("head_r7", CubeListBuilder.create().texOffs(84, 77).addBox(-6.4164F, 0.113F, -1.5695F, 6.0F, 5.0F, 5.0F, new CubeDeformation(-0.6F)), PartPose.offsetAndRotation(7.7557F, -6.3658F, 4.2905F, -2.379F, -0.0864F, -0.2049F));

		PartDefinition head_r8 = head.addOrReplaceChild("head_r8", CubeListBuilder.create().texOffs(88, 92).addBox(-3.846F, -0.4197F, -0.5695F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7557F, -6.3658F, 4.2905F, -2.6333F, -0.6013F, 0.4981F));

		PartDefinition head_r9 = head.addOrReplaceChild("head_r9", CubeListBuilder.create().texOffs(72, 87).addBox(0.9491F, 0.1351F, -0.5642F, 6.0F, 3.0F, 3.0F, new CubeDeformation(-0.6F)), PartPose.offsetAndRotation(7.7557F, -6.3658F, 4.2905F, -2.6659F, -1.0879F, 0.6199F));

		PartDefinition head_r10 = head.addOrReplaceChild("head_r10", CubeListBuilder.create().texOffs(55, 97).mirror().addBox(-0.9543F, 0.8262F, -0.5973F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.3636F, -1.7438F, -2.2166F, 1.1108F, 0.3633F, -1.6625F));

		PartDefinition head_r11 = head.addOrReplaceChild("head_r11", CubeListBuilder.create().texOffs(89, 100).mirror().addBox(-1.2177F, -0.6428F, -1.5853F, 3.0F, 3.0F, 4.0F, new CubeDeformation(-0.3F)).mirror(false), PartPose.offsetAndRotation(6.3636F, -1.7438F, -2.2166F, 0.2798F, 0.5653F, -1.57F));

		PartDefinition head_r12 = head.addOrReplaceChild("head_r12", CubeListBuilder.create().texOffs(58, 84).mirror().addBox(-0.889F, -0.3702F, -5.4866F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.3F)).mirror(false), PartPose.offsetAndRotation(6.3636F, -1.7438F, -2.2166F, 0.0617F, 0.5653F, -1.57F));

		PartDefinition head_r13 = head.addOrReplaceChild("head_r13", CubeListBuilder.create().texOffs(89, 46).addBox(-9.0F, -6.0F, 0.0F, 18.0F, 15.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, 1.0F, -1.8326F, 0.0F, 0.0F));

		PartDefinition head_r14 = head.addOrReplaceChild("head_r14", CubeListBuilder.create().texOffs(0, 122).addBox(-4.0F, -2.0F, -2.0F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(0.0F, -5.0F, 5.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(26, 50).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(47, 41).mirror().addBox(-2.0F, -2.5F, -4.0F, 2.0F, 4.0F, 9.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -0.5F, -1.0F, 0.0872F, 0.0038F, -0.4798F));

		PartDefinition body_r2 = body.addOrReplaceChild("body_r2", CubeListBuilder.create().texOffs(47, 41).addBox(0.0F, -2.5F, -4.0F, 2.0F, 4.0F, 9.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(3.0F, -0.5F, -1.0F, 0.0872F, -0.0038F, 0.4798F));

		PartDefinition body_r3 = body.addOrReplaceChild("body_r3", CubeListBuilder.create().texOffs(45, 87).mirror().addBox(-1.5F, -0.5F, -0.5F, 3.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, 1.5F, -2.5F, -0.1309F, -0.0873F, -0.1309F));

		PartDefinition body_r4 = body.addOrReplaceChild("body_r4", CubeListBuilder.create().texOffs(70, 39).mirror().addBox(-2.0221F, 0.4658F, -2.0F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.78F)).mirror(false), PartPose.offsetAndRotation(3.5F, 9.5F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition body_r5 = body.addOrReplaceChild("body_r5", CubeListBuilder.create().texOffs(70, 39).addBox(-0.9779F, 0.4658F, -2.0F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.78F)), PartPose.offsetAndRotation(-3.5F, 9.5F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition body_r6 = body.addOrReplaceChild("body_r6", CubeListBuilder.create().texOffs(45, 87).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 1.5F, -2.5F, -0.1309F, 0.0873F, 0.1309F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition left_arm_r1 = left_arm.addOrReplaceChild("left_arm_r1", CubeListBuilder.create().texOffs(109, 87).mirror().addBox(0.4656F, 0.2386F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.2F)).mirror(false)
		.texOffs(110, 70).mirror().addBox(0.4656F, 0.2386F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.85F)).mirror(false), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.0436F));

		PartDefinition left_arm_r2 = left_arm.addOrReplaceChild("left_arm_r2", CubeListBuilder.create().texOffs(0, 42).addBox(-4.0F, -4.0F, -3.0F, 7.0F, 8.0F, 6.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(1.0F, 2.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition right_arm_r1 = right_arm.addOrReplaceChild("right_arm_r1", CubeListBuilder.create().texOffs(110, 70).addBox(-4.4656F, 0.2386F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.85F))
		.texOffs(109, 87).addBox(-4.4656F, 0.2386F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.2F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.0436F));

		PartDefinition right_arm_r2 = right_arm.addOrReplaceChild("right_arm_r2", CubeListBuilder.create().texOffs(0, 42).mirror().addBox(-3.0F, -4.0F, -3.0F, 7.0F, 8.0F, 6.0F, new CubeDeformation(-0.2F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(80, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(80, 0).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition left_shoe = partdefinition.addOrReplaceChild("left_shoe", CubeListBuilder.create().texOffs(92, 15).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition right_shoe = partdefinition.addOrReplaceChild("right_shoe", CubeListBuilder.create().texOffs(92, 15).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

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
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_shoe.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_shoe.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}