
package com.skydude.dacxirons.client.model;

import com.skydude.dacxirons.item.weapons.SanguineScepterStaffItem;
import net.mcreator.dungeonsandcombat.item.SanguineScepterItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SanguineScepterItemModel extends GeoModel<SanguineScepterStaffItem> {
    public ResourceLocation getAnimationResource(SanguineScepterStaffItem animatable) {
        return new ResourceLocation("dungeons_and_combat", "animations/sanguinescepter.animation.json");
    }

    public ResourceLocation getModelResource(SanguineScepterStaffItem animatable) {
        return new ResourceLocation("dungeons_and_combat", "geo/sanguinescepter.geo.json");
    }

    public ResourceLocation getTextureResource(SanguineScepterStaffItem animatable) {
        return new ResourceLocation("dungeons_and_combat", "textures/item/sanguinescepter.png");
    }
}
