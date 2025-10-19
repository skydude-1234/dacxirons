//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.skydude.dacxirons.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.HashSet;
import java.util.Set;

import com.skydude.dacxirons.client.model.FairyScepterItemModel;
import com.skydude.dacxirons.item.weapons.FairyWandStaff;
import net.mcreator.dungeonsandcombat.item.FairyScepterItem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class FairyScepterItemRenderer extends GeoItemRenderer<FairyWandStaff> {
    private static final float SCALE_RECIPROCAL = 0.0625F;
    protected boolean renderArms = false;
    protected MultiBufferSource currentBuffer;
    protected RenderType renderType;
    public ItemDisplayContext transformType;
    protected FairyWandStaff animatable;
    private final Set<String> hiddenBones = new HashSet();
    private final Set<String> suppressedBones = new HashSet();

    public FairyScepterItemRenderer() {
        super(new FairyScepterItemModel());
    }

    public RenderType getRenderType(FairyWandStaff animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(this.getTextureLocation(animatable));
    }

    public void renderByItem(ItemStack stack, ItemDisplayContext transformType, PoseStack matrixStack, MultiBufferSource bufferIn, int combinedLightIn, int p_239207_6_) {
        this.transformType = transformType;
        if (this.animatable != null) {
            this.animatable.getTransformType(transformType);
        }

        super.renderByItem(stack, transformType, matrixStack, bufferIn, combinedLightIn, p_239207_6_);
    }

    public void actuallyRender(PoseStack matrixStackIn, FairyWandStaff animatable, BakedGeoModel model, RenderType type, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, boolean isRenderer, float partialTicks, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.currentBuffer = renderTypeBuffer;
        this.renderType = type;
        this.animatable = animatable;
        super.actuallyRender(matrixStackIn, animatable, model, type, renderTypeBuffer, vertexBuilder, isRenderer, partialTicks, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        if (this.renderArms) {
            this.renderArms = false;
        }

    }

    public ResourceLocation getTextureLocation(FairyWandStaff instance) {
        return super.getTextureLocation(instance);
    }
}
