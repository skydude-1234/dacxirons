//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.skydude.dacxirons.client.model;

import com.skydude.dacxirons.item.weapons.sceptercompensation;
import net.mcreator.dungeonsandcombat.item.ScepterOfCompensationItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class sceptercompensationmodel extends GeoModel<sceptercompensation> {
    public ResourceLocation getAnimationResource(sceptercompensation animatable) {
        return new ResourceLocation("dungeons_and_combat", "animations/scepterofcompensation.animation.json");
    }

    public ResourceLocation getModelResource(sceptercompensation animatable) {
        return new ResourceLocation("dungeons_and_combat", "geo/scepterofcompensation.geo.json");
    }

    public ResourceLocation getTextureResource(sceptercompensation animatable) {
        return new ResourceLocation("dungeons_and_combat", "textures/item/scepter_of_compensation.png");
    }
}
