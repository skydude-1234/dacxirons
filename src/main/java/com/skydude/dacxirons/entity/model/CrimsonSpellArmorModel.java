package com.skydude.dacxirons.entity.model;

import com.gametechbc.traveloptics.item.armor.TectonicCrestArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CrimsonSpellArmorModel extends GeoModel<TectonicCrestArmorItem> {
    public ResourceLocation getAnimationResource(TectonicCrestArmorItem object) {
        return new ResourceLocation("dacxirons", "animations/tectonic_crest.animation.json");
    }

    public ResourceLocation getModelResource(TectonicCrestArmorItem object) {
        return new ResourceLocation("dacxirons", "geo/tectonic_crest.geo.json");
    }

    public ResourceLocation getTextureResource(TectonicCrestArmorItem object) {
        return new ResourceLocation("dacxirons", "textures/models/armor/tectonic_crest.png");
    }
}