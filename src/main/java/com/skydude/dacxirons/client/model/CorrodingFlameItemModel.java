package com.skydude.dacxirons.client.model;


import com.skydude.dacxirons.item.weapons.CorrodingFlameItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CorrodingFlameItemModel extends GeoModel<CorrodingFlameItem> {
    public ResourceLocation getAnimationResource(CorrodingFlameItem animatable) {
        return new ResourceLocation("dungeons_and_combat", "animations/corroding_flame_scepter.animation.json");
    }

    public ResourceLocation getModelResource(CorrodingFlameItem animatable) {
        return new ResourceLocation("dungeons_and_combat", "geo/corroding_flame_scepter.geo.json");
    }

    public ResourceLocation getTextureResource(CorrodingFlameItem animatable) {
        return new ResourceLocation("dungeons_and_combat", "textures/item/corroding_flame_scepter.png");
    }
}
