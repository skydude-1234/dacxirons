package com.skydude.dacxirons.entity.model;

import com.gametechbc.traveloptics.item.armor.TectonicCrestArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class CrimsonSpellArmorLayer {
}
//import com.gametechbc.traveloptics.item.armor.TectonicCrestArmorItem;
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.vertex.VertexConsumer;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.texture.OverlayTexture;
//import net.minecraft.resources.ResourceLocation;
//import software.bernie.geckolib.cache.object.BakedGeoModel;
//import software.bernie.geckolib.renderer.GeoRenderer;
//import software.bernie.geckolib.renderer.layer.GeoRenderLayer;
//
//public class TectonicCrestArmorLayer extends GeoRenderLayer<TectonicCrestArmorItem> {
//    private static final ResourceLocation LAYER = new ResourceLocation("traveloptics", "textures/models/armor/tectonic_crest_layer.png");
//
//    public TectonicCrestArmorLayer(GeoRenderer<TectonicCrestArmorItem> entityRenderer) {
//        super(entityRenderer);
//    }
//
//    public void render(PoseStack poseStack, TectonicCrestArmorItem animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
//        RenderType glowRenderType = RenderType.eyes(LAYER);
//        this.getRenderer().reRender(this.getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, glowRenderType, bufferSource.m_6299_(glowRenderType), partialTick, packedLight, OverlayTexture.f_118083_, 1.0F, 1.0F, 1.0F, 0.5F);
//    }
//}
