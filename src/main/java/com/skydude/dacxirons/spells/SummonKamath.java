package com.skydude.dacxirons.spells;


import com.skydude.dacxirons.dacxirons;
import com.skydude.dacxirons.entity.mobs.SummonedKamath;
import com.skydude.dacxirons.entity.mobs.SummonedWeakness;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.spells.eldritch.AbstractEldritchSpell;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModAttributes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.Optional;


@AutoSpellConfig
public class SummonKamath extends AbstractEldritchSpell {
    private final ResourceLocation spellId = new ResourceLocation(dacxirons.MOD_ID, "summonkamath");
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(SchoolRegistry.ELDRITCH_RESOURCE)
            .setMaxLevel(8)
            .setCooldownSeconds(600)
            .build();

    public int spllLevel;

    @Override
    public List<MutableComponent> getUniqueInfo(int spellPower, LivingEntity caster) {

        return List.of(Component.translatable("ui.irons_spellbooks.summon_count", "1"));


    }


    private float getKamakathDamage(int spellLevel, LivingEntity caster) {
        return this.getSpellPower(spellLevel, caster) * 1f;
    }
    public SummonKamath() {
        this.manaCostPerLevel = 25;
        this.baseSpellPower = 10;
        this.spellPowerPerLevel = 1;
        this.castTime = 200 ;
        this.baseManaCost = 600;

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
        int summonTime = 20 * 60 * 10;




        float radius = 1.5f + .185f * spellLevel;

            Monster kamath = new SummonedKamath(entity, true);

            // set the maxhealth
            double maxHealth = (getKamakathDamage(spellLevel, entity ));
            kamath.getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxHealth);
            kamath.setHealth((float) maxHealth);
            System.out.println(maxHealth);

                kamath.finalizeSpawn((ServerLevel) world, world.getCurrentDifficultyAt(kamath.getOnPos()), MobSpawnType.MOB_SUMMONED, null, null);
            kamath.addEffect(new MobEffectInstance(MobEffectRegistry.RAISE_DEAD_TIMER.get(), summonTime, 0, false, false, false));
            var yrot = 6.281f / spellLevel + entity.getYRot() * Mth.DEG_TO_RAD;
            Vec3 spawn = Utils.moveToRelativeGroundLevel(world, entity.getEyePosition().add(new Vec3(radius * Mth.cos(yrot), 0, radius * Mth.sin(yrot))), 10);
            kamath.setPos(spawn.x, spawn.y, spawn.z);
            kamath.setYRot(entity.getYRot());
            kamath.setOldPosAndRot();
            world.addFreshEntity(kamath);


        int effectAmplifier = spellLevel - 1;
      //  if (entity.hasEffect(MobEffectRegistry.RAISE_DEAD_TIMER.get()))
     //       effectAmplifier += entity.getEffect(MobEffectRegistry.RAISE_DEAD_TIMER.get()).getAmplifier() + 1;
        entity.addEffect(new MobEffectInstance(MobEffectRegistry.RAISE_DEAD_TIMER.get(), summonTime, 0, false, false, true));

        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }



}
