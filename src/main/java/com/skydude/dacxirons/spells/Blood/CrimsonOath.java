package com.skydude.dacxirons.spells.Blood;


import com.skydude.dacxirons.dacxirons;
import com.skydude.dacxirons.registries.EffectRegistry;
import com.skydude.dacxirons.registries.dacxironsSpellRegistry;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.mcreator.dungeonsandcombat.entity.BloodyArrowEntity;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModMobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@AutoSpellConfig
public class CrimsonOath extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation(dacxirons.MOD_ID, "crimson_oath");
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.EPIC)
            .setSchoolResource(SchoolRegistry.BLOOD_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(60)
            .build();

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        // return List.of(Component.translatable("ui.irons_spellbooks.summon_count", spellLevel));
        return List.of(Component.translatable("ui.dacxirons.healthreduction", Math.round(getHealthReduction(spellLevel, caster) * 100.0)),
                Component.translatable("ui.dacxirons.bloodpower", getBloodPower(spellLevel, caster)),
                Component.translatable("ui.dacxirons.duration2", getDuration(spellLevel, caster) / 20)

        );
    }

    public CrimsonOath() {
        this.manaCostPerLevel = 10;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 30;
        this.baseManaCost = 30;

    }


    @Override
    public CastType getCastType() {
        return CastType.INSTANT;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(SoundRegistry.BLOOD_EXPLOSION.get());
    }


    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.empty();
    }


    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {


        entity.hurt(world.damageSources().magic(), entity.getHealth() * getHealthReduction(spellLevel, entity));
        entity.addEffect(new MobEffectInstance(DungeonsAndCombatModMobEffects.BLEEDING.get(), getDuration(spellLevel, entity), 0, true, false, true));
        MagicManager.spawnParticles(world, ParticleHelper.SIPHON, entity.getX(), entity.getY() + .25, entity.getZ(), 15, entity.getBbWidth() * 0.5, entity.getBbWidth() * 0.5, entity.getBbWidth() * 0.5, 0, false);
        entity.addEffect(new MobEffectInstance(EffectRegistry.BLOOD_SPELL_STRENGTH.get(), getDuration(spellLevel, entity), spellLevel, true, false, true));

        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    public float getHealthReduction( int spellLevel , LivingEntity caster){
        return .80F - (.10F * spellLevel) ;
    }
    public int getDuration( int spellLevel, LivingEntity caster){
        return (int) (20 * (10 + (20 * getSpellPower(spellLevel, caster))));
    }
    public int getBloodPower(int spellLevel, LivingEntity caster){
        return (int)(10 + (spellLevel * 10));
    }
}
