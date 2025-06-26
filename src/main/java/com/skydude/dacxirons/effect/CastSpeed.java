package com.skydude.dacxirons.effect;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.UUID;

public class CastSpeed extends MobEffect {
    private static final UUID CAST_SPEED_ID = UUID.fromString("c1b5c67e-5040-4fcb-9e9e-01e199cfb001");

    public CastSpeed() {
        super(MobEffectCategory.BENEFICIAL, 0xFF4500); // Orange-red color

        // Get the fire spell power attribute from Iron's Spellbooks
        Attribute castPower = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("irons_spellbooks", "cast_time_reduction"));

        if (castPower != null) {
            this.addAttributeModifier(
                    castPower,
                    CAST_SPEED_ID.toString(),
                    0.1,
                    AttributeModifier.Operation.MULTIPLY_BASE
            );
        }

    }
@Override
public double getAttributeModifierValue(int amplifier, AttributeModifier modifier) {
    // Multiply base value by (1 + amplifier)
    return (1 + amplifier) * modifier.getAmount();
}
}
