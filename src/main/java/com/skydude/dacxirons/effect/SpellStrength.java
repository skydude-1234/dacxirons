package com.skydude.dacxirons.effect;



import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

public class SpellStrength extends MobEffect {
    private static final UUID SPELLMODIFIER_ID = UUID.fromString("c1b5c67e-5040-4fcb-9e9e-01e199cfb001");

    public SpellStrength() {
        super(MobEffectCategory.BENEFICIAL, 0xFF4500); // Orange-red color

        // Get the fire spell power attribute from Iron's Spellbooks
        Attribute firePower = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("irons_spellbooks", "spell_power"));

        if (firePower != null) {
            // Apply attribute modifier: +5 fire spell power
            this.addAttributeModifier(
                    firePower,
                    SPELLMODIFIER_ID.toString(),
                    0.1,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            );
        } else {
            System.err.println("Failed to find 'fire_spell_power' attribute for EmpowerFireEffect!");
        }

    }
@Override
public double getAttributeModifierValue(int amplifier, AttributeModifier modifier) {
    // Multiply base value by (1 + amplifier)
    return (1 + amplifier) * modifier.getAmount();
}
}
