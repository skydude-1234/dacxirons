package com.skydude.dacxirons.spells;


import com.skydude.dacxirons.dacxirons;
import com.skydude.dacxirons.registries.dacxironsSpellRegistry;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.fireball.MagicFireball;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.mcreator.dungeonsandcombat.DungeonsAndCombatMod;
import net.mcreator.dungeonsandcombat.entity.MagicArrowEntity;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModMobEffects;
import net.mcreator.dungeonsandcombat.procedures.PyromancerScepterRightclickedProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Optional;


@AutoSpellConfig
public class TripleFireballSpell extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation(dacxirons.MOD_ID, "triple_fireball_spell");


    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.dacxirons.triple_fireball_ballamount"),
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.radius", getRadius(spellLevel, caster))
        );
    }
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.FIRE_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(50)
            .build();

    public TripleFireballSpell() {
        this.manaCostPerLevel = 30;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 40;
        this.baseManaCost = 120;
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
        return Optional.of(io.redspace.ironsspellbooks.registries.SoundRegistry.FIREBALL_START.get());
    }




    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {

        DungeonsAndCombatMod.queueServerWork(10, () -> {
            spawnFireballOnce(world, entity, spellLevel);
        });

        DungeonsAndCombatMod.queueServerWork(20, () -> {
            world.playSound(null, entity.blockPosition(), SoundRegistry.FIRE_CAST.get(), SoundSource.PLAYERS);
            spawnFireballOnce(world, entity, spellLevel);
        });

        DungeonsAndCombatMod.queueServerWork(30, () -> {
            world.playSound(null, entity.blockPosition(), SoundRegistry.FIRE_CAST.get(), SoundSource.PLAYERS);
            spawnFireballOnce(world, entity, spellLevel);
        });

        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }
    private void spawnFireballOnce(Level world, LivingEntity caster, int spellLevel) {
        if (caster == null || !caster.isAlive()) return;

        Vec3 origin = caster.getEyePosition();
        MagicFireball fireball = new MagicFireball(world, caster);

        fireball.setDamage(getDamage(spellLevel, caster));
        fireball.setExplosionRadius(getRadius(spellLevel, caster));

        fireball.setPos(origin.add(caster.getForward()).subtract(0, fireball.getBbHeight() / 2.0, 0));
        fireball.shoot(caster.getLookAngle());

        world.addFreshEntity(fireball);



    }
    public float getDamage(int spellLevel, LivingEntity caster) {
        return 5 + 5 * getSpellPower(spellLevel, caster);
    }

    public int getRadius(int spellLevel, LivingEntity caster) {
        return 2 + (int) getSpellPower(spellLevel, caster);
    }

}
