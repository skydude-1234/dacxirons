package com.skydude.dacxirons.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class SpellBurningAuraEffect extends MobEffect {
    public SpellBurningAuraEffect() {
        super(MobEffectCategory.BENEFICIAL, -6730217);
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
