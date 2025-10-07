package com.skydude.dacxirons.entity.spells.EldritchSlash;



import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.PoseStack.Pose;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import com.skydude.dacxirons.dacxirons;
import io.redspace.ironsspellbooks.entity.spells.flame_strike.FlameStrike;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.util.Random;

public class EldritchSlashRenderer extends EntityRenderer<EldritchSlash> {
    private static final ResourceLocation[] TEXTURES = {
            dacxirons.id("textures/entities/eldritch_slash/flame_strike_1.png"),
            dacxirons.id("textures/entities/eldritch_slash/flame_strike_2.png"),
            dacxirons.id("textures/entities/eldritch_slash/flame_strike_3.png"),
            dacxirons.id("textures/entities/eldritch_slash/flame_strike_4.png")
    };

    public EldritchSlashRenderer(Context context) {
        super(context);
    }

    @Override
    public void render(EldritchSlash entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int light) {
        poseStack.pushPose();

        Pose pose = poseStack.last();
        poseStack.mulPose(Axis.YP.rotationDegrees(90 - entity.getYRot()));
        poseStack.mulPose(Axis.ZP.rotationDegrees(entity.getXRot()));
        float randomZ = new Random(31L * entity.getId()).nextInt(-8, 8);
        poseStack.mulPose(Axis.XP.rotationDegrees(randomZ));

        drawSlash(pose, entity, bufferSource, entity.getBbWidth() * 1.5f, entity.isMirrored());

        poseStack.popPose();

        super.render(entity, yaw, partialTicks, poseStack, bufferSource, light);
    }

    private void drawSlash(Pose pose, EldritchSlash entity, MultiBufferSource bufferSource, float width, boolean mirrored) {
        Matrix4f poseMatrix = pose.pose();
        Matrix3f normalMatrix = pose.normal();

        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));
        float halfWidth = width * .5f;
        float height = entity.getBbHeight() * .5f;
        //old color: 125, 0, 10
        consumer.vertex(poseMatrix, -halfWidth, height, -halfWidth).color(255, 255, 255, 255).uv(0f, mirrored ? 0f : 1f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0f, 1f, 0f).endVertex();
        consumer.vertex(poseMatrix, halfWidth, height, -halfWidth).color(255, 255, 255, 255).uv(1f, mirrored ? 0f : 1f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0f, 1f, 0f).endVertex();
        consumer.vertex(poseMatrix, halfWidth, height, halfWidth).color(255, 255, 255, 255).uv(1f, mirrored ? 1f : 0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0f, 1f, 0f).endVertex();
        consumer.vertex(poseMatrix, -halfWidth, height, halfWidth).color(255, 255, 255, 255).uv(0f, mirrored ? 1f : 0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0f, 1f, 0f).endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(EldritchSlash entity) {
        int frame = (entity.tickCount / entity.ticksPerFrame) % TEXTURES.length;
        return TEXTURES[frame];
        //return TEXTURE;
    }
}