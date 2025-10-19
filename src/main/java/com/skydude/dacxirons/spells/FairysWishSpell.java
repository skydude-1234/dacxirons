package com.skydude.dacxirons.spells;


import com.skydude.dacxirons.dacxirons;
import com.skydude.dacxirons.entity.spells.AcidBall.AcidFireball;
import com.skydude.dacxirons.registries.EffectRegistry;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

import static io.redspace.ironsspellbooks.api.util.Utils.random;


@AutoSpellConfig
public class FairysWishSpell extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation(dacxirons.MOD_ID, "fairys_wish_spell");


    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.dacxirons.amplifier",
                     //   (Utils.stringTruncation(getMaxEffectAmplifier(spellLevel, caster), 2) + 1)),
                        (int)(getMaxEffectAmplifier(spellLevel, caster))),
                Component.translatable("ui.dacxirons.duration", (getMaxDuration(spellLevel, caster) / 20))
        );
    }
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(SchoolRegistry.NATURE_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(60)
            .build();

    public FairysWishSpell() {
        this.manaCostPerLevel = 10;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 10;
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

//    @Override
//    public Optional<SoundEvent> getCastStartSound() {
//        return Optional.of(SoundEvents.SLIME_ATTACK);
//    }




    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        Vec3 origin = entity.getEyePosition();


        world.getEntitiesOfClass(LivingEntity.class, AABB.ofSize(entity.getBoundingBox().getCenter(), 6, 6, 6)).forEach(livingEntity -> {
      //      IronsSpellbooks.LOGGER.debug("cleanse: {}", livingEntity);
           if(Utils.shouldHealEntity(entity, livingEntity)){
                List<MobEffect> mobeefect = List.of(MobEffects.JUMP, EffectRegistry.CAST_SPEED.get(), EffectRegistry.SPELL_STRENGTH.get(), MobEffects.DAMAGE_BOOST);
               // pick random effect
                Random random = new Random();
                int randomIndex = random.nextInt(mobeefect.size());


                      MobEffectInstance s =  (new MobEffectInstance(mobeefect.get(randomIndex), (int) (Math.random() * (getMaxDuration(spellLevel, entity))), (int) (Math.random() * getMaxEffectAmplifier(spellLevel, entity)), true, true));
               livingEntity.addEffect(s);
                randomIndex = random.nextInt(mobeefect.size());

               MobEffectInstance s2 =  (new MobEffectInstance(mobeefect.get(randomIndex), (int) (Math.random() * (getMaxDuration(spellLevel, entity))), (int) (Math.random() * getMaxEffectAmplifier(spellLevel, entity)), true, true));
               livingEntity.addEffect(s2);
               System.out.println("s" + s);
               System.out.println("s2" + s2);
               MagicManager.spawnParticles(world, ParticleHelper.SIPHON, livingEntity.getX(), livingEntity.getY() + .25, livingEntity.getZ(), 15, livingEntity.getBbWidth() * 0.5, livingEntity.getBbWidth() * 0.5, livingEntity.getBbWidth() * 0.5, 0, false);
            }});

        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }



    public float getMaxEffectAmplifier(int spellLevel, LivingEntity caster) {
        return (int) (spellLevel);
    }

    public int getMaxDuration(int spellLevel, LivingEntity caster) {
           return (int) ((20 * 15) *  (0.5 * getSpellPower(spellLevel, caster)));

    }

}
