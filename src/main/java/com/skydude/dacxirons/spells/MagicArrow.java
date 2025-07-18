package com.skydude.dacxirons.spells;



import com.skydude.dacxirons.dacxirons;
import com.skydude.dacxirons.registries.SoundRegistry;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import net.mcreator.dungeonsandcombat.entity.LaserBeamEntity;
import net.mcreator.dungeonsandcombat.entity.MagicArrowEntity;
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
public class MagicArrow extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation(dacxirons.MOD_ID, "magic_arrow");
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setAllowCrafting(false)
            .setSchoolResource(SchoolRegistry.EVOCATION_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(3)
            .build();

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        // return List.of(Component.translatable("ui.irons_spellbooks.summon_count", spellLevel));
        return List.of(Component.translatable("ui.dacxirons.sunleia.beam", Math.round(5 * getSpellPower(spellLevel, caster))));
    }

    public MagicArrow() {
        this.manaCostPerLevel = 10;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 0;
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
        return Optional.of(SoundRegistry.MAGIC_ARROW_SOUND.get());
    }


    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.empty();
    }


    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("minecraft", "entity.evoker.cast_spell"));
        if (sound != null) {
            world.playSound(null, entity.blockPosition(), sound, SoundSource.NEUTRAL, 1.0f, 1.0f);
            world.playSound(null, entity.blockPosition(), sound, SoundSource.NEUTRAL, 1.0f, 1.0f);
        } else {
            System.out.println("Failed to find sound: entity.evoker.cast_spell");
        }
        MagicArrowEntity.shoot(world, entity, RandomSource.create(), 1, 5 * getSpellPower(spellLevel, entity), 1);


            super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }
}
