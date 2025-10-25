package com.skydude.dacxirons.entity.spells.SunsWrath;

import com.skydude.dacxirons.registries.EntityRegistry;
import com.skydude.dacxirons.registries.dacxironsSpellRegistry;
import io.redspace.ironsspellbooks.api.events.SpellHealEvent;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.mcreator.dungeonsandcombat.client.particle.BlessedSparkleParticle;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModMobEffects;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModParticleTypes;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;

import java.util.Optional;

public class SunsWrathAOE extends AoeEntity implements AntiMagicSusceptible {
    private final Level level;

    public SunsWrathAOE(EntityType<? extends Projectile> pEntityType, Level level) {
        super(pEntityType, level);


        this.level = level;
    }

    public SunsWrathAOE(Level level) {
        this(EntityRegistry.SUNS_WRATH_AOE.get(), level);
    }



    @Override
    public void applyEffect(LivingEntity target) {
        //var owner = getOwner();
        //IronsSpellbooks.LOGGER.debug("HealingAoe apply effect: target: {} owner: {} should heal: {}",target.getName().getString(),owner==null?null:owner.getName().getString(),owner==null?false: Utils.shouldHealEntity((LivingEntity) owner,target));
        if (getOwner() instanceof LivingEntity owner && !Utils.shouldHealEntity(owner, target)) {
            DamageSources.applyDamage(target, getDamage(), dacxironsSpellRegistry.SUNS_WRATH.get().getDamageSource(this, getOwner()));
            target.addEffect(new MobEffectInstance(DungeonsAndCombatModMobEffects.CURSE_OF_THE_SUN.get(), 100, 2, true, false));
        }
    }

    @Override
    protected boolean canHitEntity(Entity pTarget) {
        return !pTarget.isSpectator() && pTarget.isAlive() && pTarget.isPickable();
    }

    @Override
    public float getParticleCount() {
        return 1.15f;
    }

    @Override
    public void ambientParticles() {

        if (!level.isClientSide) {
            return;
        }

//        int color = 0xFFFF00;
//
        double vx = (random.nextDouble() - 0.5) * 0.02;
        double vy = (random.nextDouble()) * 0.02;
        double vz = (random.nextDouble() - 0.5) * 0.02;
        float f = getParticleCount();
        f = Mth.clamp(f * getRadius(), f / 4, f * 10);
        for (int i = 0; i < f; i++) {
            if (f - i < 1 && random.nextFloat() > f - i)
                return;
            var r = getRadius();
            Vec3 pos;
            if (isCircular()) {
                float distance = (1 - this.random.nextFloat() * this.random.nextFloat()) * r;
                pos = new Vec3(0, 0, distance).yRot(this.random.nextFloat() * 360);
            } else {
                pos = new Vec3(
                        Utils.getRandomScaled(r * .85f),
                        .2f,
                        Utils.getRandomScaled(r * .85f)
                );
            }
            level.addParticle(DungeonsAndCombatModParticleTypes.BLESSED_SPARKLE.get(), getX() + pos.x, getY() + pos.y + particleYOffset(), getZ() + pos.z, vx, vy, vz);
        }
     //   level.addParticle(DungeonsAndCombatModParticleTypes.BLESSED_SPARKLE.get(), getX(), getY(), getZ(), vx, vy, vz);

    }

    @Override
    protected Vec3 getInflation() {
        return new Vec3(0, 1, 0);
    }

    @Override
    public Optional<ParticleOptions> getParticle() {
        return Optional.of(DungeonsAndCombatModParticleTypes.BLESSED_SPARKLE.get());
    }

    @Override
    public void onAntiMagic(MagicData magicData) {
        discard();
    }
}
