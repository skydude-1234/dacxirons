
package com.skydude.dacxirons.client.model;

import com.skydude.dacxirons.item.weapons.FairyWandStaff;
import net.mcreator.dungeonsandcombat.item.FairyScepterItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FairyScepterItemModel extends GeoModel<FairyWandStaff> {
    public ResourceLocation getAnimationResource(FairyWandStaff animatable) {
        return new ResourceLocation("dungeons_and_combat", "animations/fairystaffanimated.animation.json");
    }

    public ResourceLocation getModelResource(FairyWandStaff animatable) {
        return new ResourceLocation("dungeons_and_combat", "geo/fairystaffanimated.geo.json");
    }

    public ResourceLocation getTextureResource(FairyWandStaff animatable) {
        return new ResourceLocation("dungeons_and_combat", "textures/item/fairy_staff_animated.png");
    }
}
