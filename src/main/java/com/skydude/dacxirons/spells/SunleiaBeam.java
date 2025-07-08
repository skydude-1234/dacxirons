package com.skydude.dacxirons.spells;

import com.skydude.dacxirons.dacxirons;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import net.mcreator.dungeonsandcombat.entity.LaserBeamEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

import net.minecraftforge.registries.ForgeRegistries;

import static com.ibm.icu.impl.ValidIdentifiers.Datatype.x;


@AutoSpellConfig
public class SunleiaBeam extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation(dacxirons.MOD_ID, "sunleia_beam");
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.HOLY_RESOURCE)
            .setMaxLevel(6)
            .setCooldownSeconds(6)
            .build();

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
       // return List.of(Component.translatable("ui.irons_spellbooks.summon_count", spellLevel));
        return List.of(Component.translatable("ui.dacxirons.sunleia.beam",  5 + (2 * spellLevel) * getSpellPower(spellLevel, caster)));
    }

    public SunleiaBeam() {
        this.manaCostPerLevel = 10;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 0;
        this.castTime = 30;
        this.baseManaCost = 100;

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
        return Optional.ofNullable(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("dungeons_and_combat:magic_boost")));
    }


    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.empty();
    }


    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        int summonTime = 20 * 60 * 10;
        LaserBeamEntity.shoot(world, entity, RandomSource.create(), 1, (2 * spellLevel) * getSpellPower(spellLevel, entity), 1);
            SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(
                    new ResourceLocation("dungeons_and_combat:magic_boost")
            );
            world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), sound, SoundSource.HOSTILE, 1.0F, 2.0F);



            // your spell logic here


        super.onCast(world, spellLevel, entity, castSource, playerMagicData);

    }





}
