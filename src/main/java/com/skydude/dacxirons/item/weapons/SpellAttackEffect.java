package com.skydude.dacxirons.item.weapons;

import com.skydude.dacxirons.item.armor.EbonyMagicSpellArmorItem;
import io.redspace.ironsspellbooks.api.events.SpellDamageEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SpellAttackEffect{


    public static void SpellEffectAdd( LivingEntity target, MobEffect effect, int duration, int amplifier, boolean ambient, boolean showParticles) {

            // run on server only


        // test with something visible first
        MobEffectInstance mobeffect = new MobEffectInstance(
                MobEffects.GLOWING, // swap to MobEffects.WITHER after confirming
                duration,                // duration (ticks)
                amplifier,                  // amplifier
                ambient,              // ambient
                showParticles                // show particles
        );

        target.addEffect(mobeffect);


        // ensure main server thread


    }


}
