package com.skydude.dacxirons.spells;


import com.skydude.dacxirons.dacxirons;
import com.skydude.dacxirons.entity.mobs.SummonedBloodymancerCultist;
import com.skydude.dacxirons.entity.mobs.SummonedPyro;
import com.skydude.dacxirons.entity.mobs.SummonedWeakness;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@AutoSpellConfig
public class SummonBloodymancerCultist extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation(dacxirons.MOD_ID, "summon_bloodymancer_cultist");
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setAllowCrafting(true)
            .setMinRarity(SpellRarity.EPIC)
            .setSchoolResource(SchoolRegistry.BLOOD_RESOURCE)
            .setMaxLevel(6)
            .setCooldownSeconds(150)
            .build();

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.summon_count", Math.min(3, (spellLevel + 1) / 2)),
                      (Component.translatable("ui.dacxirons.summon_hp", Math.round(getHealth( caster, spellLevel)))),
                     (Component.translatable("ui.dacxirons.kamath.summon_duration", Math.round((float) getDuration(caster, spellLevel) / 20))));

    }

    public SummonBloodymancerCultist() {
        this.manaCostPerLevel = 20;
        this.baseSpellPower = 10;
        this.spellPowerPerLevel = 3;
        this.castTime = 30;
        this.baseManaCost = 100;

    }


    @Override
    public CastType getCastType() {
        return CastType.LONG;
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
        return Optional.of(SoundRegistry.RAISE_DEAD_START.get());
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundRegistry.RAISE_DEAD_FINISH.get());
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        int summonTime = 0;
        float radius = 1.5f + .185f * spellLevel;

        for (int i = 0; i < spellLevel; i += 2) {


            Monster summon = new SummonedBloodymancerCultist(entity, true);
            summonTime = getDuration( entity, spellLevel);
            summon.finalizeSpawn((ServerLevel) world, world.getCurrentDifficultyAt(summon.getOnPos()), MobSpawnType.MOB_SUMMONED, null, null);
            summon.addEffect(new MobEffectInstance(MobEffectRegistry.RAISE_DEAD_TIMER.get(), summonTime, 0, false, false, false));

            var yrot = 6.281f / spellLevel * i + entity.getYRot() * Mth.DEG_TO_RAD;
            Vec3 spawn = Utils.moveToRelativeGroundLevel(world, entity.getEyePosition().add(new Vec3(radius * Mth.cos(yrot), 0, radius * Mth.sin(yrot))), 10);
            //get health
            summon.setHealth(getHealth( entity, spellLevel));
            summon.setPos(spawn.x, spawn.y, spawn.z);


            summon.setYRot(entity.getYRot());
            summon.setOldPosAndRot();
            world.addFreshEntity(summon);
        }
        int effectAmplifier = spellLevel - 1;

        if (entity.hasEffect(MobEffectRegistry.RAISE_DEAD_TIMER.get())) {
            effectAmplifier += entity.getEffect(MobEffectRegistry.RAISE_DEAD_TIMER.get()).getAmplifier() + 1;
        }
        entity.addEffect(new MobEffectInstance(MobEffectRegistry.RAISE_DEAD_TIMER.get(), summonTime, effectAmplifier, false, false, true));

        super.onCast(world, spellLevel, entity, castSource, playerMagicData);

    }

    public float getHealth( LivingEntity entity, int spellLevel){
        return (float) (SummonedBloodymancerCultist.createAttributes().build().getValue(Attributes.MAX_HEALTH)* 0.005  * getSpellPower(spellLevel, entity));
    }
    public int getDuration( LivingEntity entity, int spellLevel){
        return (int) (6 * 15 * getSpellPower(spellLevel, entity));
    }

}
