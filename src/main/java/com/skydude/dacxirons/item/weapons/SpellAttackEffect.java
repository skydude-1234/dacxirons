package com.skydude.dacxirons.item.weapons;

import io.redspace.ironsspellbooks.api.events.SpellDamageEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.skydude.dacxirons.item.armor.EbonyMagicSpellArmorItem.fullebonymagic;
import static com.skydude.dacxirons.item.weapons.sceptercompensation.isholding;

public class SpellAttackEffect{


    public static void SpellEffectAddTarget( LivingEntity target, MobEffect effect, int duration, int amplifier, boolean ambient, boolean showParticles) {

            // run on server only


            // test with something visible first
            MobEffectInstance mobeffect = new MobEffectInstance(
                    MobEffects.GLOWING, // swap to MobEffects.WITHER after confirming
                    200,                // duration (ticks)
                    1,                  // amplifier
                    false,              // ambient
                    true                // show particles
            );

        target.addEffect(mobeffect);


            // ensure main server thread


    }


    public static void SpellEffectAdd(SpellDamageEvent event, Player player, MobEffect effect, int duration, int amplifier, boolean ambient, boolean showParticles) {

            // run on server only
            if (player.level().isClientSide()) return;


            // effect
            MobEffectInstance mobeffect = new MobEffectInstance(
                    effect, // swap to MobEffects.WITHER after confirming
                    duration,                // duration (ticks)
                    amplifier,                  // amplifier
                    ambient,              // ambient
                    showParticles                // show particles
            );

            MinecraftServer server = player.getServer();
            if (server == null) return;

            // ensure main server thread
            server.execute(() -> {
                player.addEffect(mobeffect);

            });

    }
    @SubscribeEvent
    public static void onSpellAttack(SpellDamageEvent event) {

        System.out.println( "hellur");
        if(isholding){
            System.out.println("helloooow");
            SpellAttackEffect.SpellEffectAddTarget( event.getEntity(), MobEffects.GLOWING, 300, 1, true, true);

        }

    }
}
