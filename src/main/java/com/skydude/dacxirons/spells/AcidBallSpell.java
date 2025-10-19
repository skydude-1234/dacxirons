package com.skydude.dacxirons.spells;


import com.skydude.dacxirons.dacxirons;
import com.skydude.dacxirons.entity.spells.AcidBall.AcidFireball;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.fireball.MagicFireball;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.mcreator.dungeonsandcombat.DungeonsAndCombatMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Optional;


@AutoSpellConfig
public class AcidBallSpell extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation(dacxirons.MOD_ID, "acid_ball_spell");


    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.radius", getRadius(spellLevel, caster))
        );
    }
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.NATURE_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(5)
            .build();

    public AcidBallSpell() {
        this.manaCostPerLevel = 10;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 10;
        this.baseManaCost = 40;
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

//    @Override
//    public Optional<SoundEvent> getCastStartSound() {
//        return Optional.of(SoundEvents.SLIME_ATTACK);
//    }




    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        Vec3 origin = entity.getEyePosition();
        AcidFireball acidball = new AcidFireball(entity, world);

        acidball.setDamage(getDamage(spellLevel, entity));
        acidball.setExplosionRadius(getRadius(spellLevel, entity));

        acidball.setPos(origin.add(entity.getForward()).subtract(0, acidball.getBbHeight() / 2.0, 0));
        acidball.shoot(entity.getLookAngle());

        world.addFreshEntity(acidball);




        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    public float getDamage(int spellLevel, LivingEntity caster) {
        return 5 + 3 * getSpellPower(spellLevel, caster);
    }

    public int getRadius(int spellLevel, LivingEntity caster) {
     //   return 4 + (int) getSpellPower(spellLevel, caster);
        return 4;
    }

}
