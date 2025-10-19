//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.skydude.dacxirons.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.mcreator.dungeonsandcombat.client.model.Modelacid_fire_ball;
import com.skydude.dacxirons.entity.spells.AcidBall.AcidFireball;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class AcidFireBallRenderer extends EntityRenderer<AcidFireball> {
    private static final ResourceLocation texture = new ResourceLocation("dungeons_and_combat:textures/entities/acid_fire_ball.png");
    private final Modelacid_fire_ball model;

    public AcidFireBallRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new Modelacid_fire_ball(context.bakeLayer(Modelacid_fire_ball.LAYER_LOCATION));
    }

    public void render(AcidFireball entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
        VertexConsumer vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F + Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
        this.model.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
    }

    public ResourceLocation getTextureLocation(AcidFireball entity) {
        return texture;
    }
}
