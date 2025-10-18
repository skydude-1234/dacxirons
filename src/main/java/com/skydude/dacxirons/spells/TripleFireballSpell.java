package com.skydude.dacxirons.spells;


import com.skydude.dacxirons.dacxirons;
import com.skydude.dacxirons.registries.SoundRegistry;
import com.skydude.dacxirons.registries.dacxironsSpellRegistry;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.damage.DamageSources;
import net.mcreator.dungeonsandcombat.entity.MagicArrowEntity;
import net.mcreator.dungeonsandcombat.procedures.PyromancerScepterRightclickedProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Optional;


@AutoSpellConfig
public class TripleFireballSpell extends AbstractSpell {
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

    public TripleFireballSpell() {
        this.manaCostPerLevel = 10;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 0;
        this.castTime = 30;
        this.baseManaCost = 30;

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
        //target entitty

        double reach = 48.0; // how far to check
        var start = entity.getEyePosition();
        var end   = start.add(entity.getLookAngle().scale(reach));

        var aabb = entity.getBoundingBox().expandTowards(entity.getLookAngle().scale(reach)).inflate(1.0);
        var filter = (java.util.function.Predicate<Entity>) (e ->
                e instanceof LivingEntity
                        && e != entity
                        && e.isPickable()
                        && !e.isSpectator()
                        && e.isAlive()
        );

        var hit = net.minecraft.world.entity.projectile.ProjectileUtil.getEntityHitResult(
                entity, start, end, aabb, filter, reach * reach);

        LivingEntity target = (hit != null && hit.getEntity() instanceof LivingEntity le) ? le : null;
// end of target entity


// actual projectile
        PyromancerScepterRightclickedProcedure.execute.shoot(world, entity, RandomSource.create(), 1, 5 * getSpellPower(spellLevel, entity), 1);

        // 0 damage, just to register as a spell for onSpellAttack event
        if(target != null){
            DamageSources.applyDamage(target, 0, dacxironsSpellRegistry.MAGIC_ARROW.get().getDamageSource(entity));
        }

            super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }
}
