package com.skydude.dacxirons.registries;


import com.skydude.dacxirons.dacxirons;
import com.skydude.dacxirons.effect.*;
import com.skydude.dacxirons.dacxirons;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.caelus.api.CaelusApi;

import static net.minecraftforge.registries.ForgeRegistries.Keys.MOB_EFFECTS;

public class EffectRegistry {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, dacxirons.MOD_ID);

    public static final RegistryObject<MobEffect> SPELL_STRENGTH =
            MOB_EFFECTS.register("spell_strength", SpellStrength::new);
}
