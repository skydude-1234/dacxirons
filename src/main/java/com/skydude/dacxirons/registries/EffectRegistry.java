package com.skydude.dacxirons.registries;


import com.skydude.dacxirons.dacxirons;
import com.skydude.dacxirons.effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectRegistry {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, dacxirons.MOD_ID);

    public static final RegistryObject<MobEffect> SPELL_STRENGTH =
            MOB_EFFECTS.register("spell_strength", SpellStrength::new);
    public static final RegistryObject<MobEffect> CAST_SPEED =
            MOB_EFFECTS.register("cast_speed", CastSpeed:: new);
    public static final RegistryObject<MobEffect> SPELL_BURNING_AURA =
            MOB_EFFECTS.register("spell_burn", SpellBurningAuraEffect:: new);
    public static final RegistryObject<MobEffect> BLOOD_SPELL_STRENGTH =
            MOB_EFFECTS.register("blood_spell_strength", BloodSpellStrength:: new);
}
