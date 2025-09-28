package com.skydude.dacxirons.item.armor.set_bonuses;

import io.redspace.ironsspellbooks.api.events.SpellDamageEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.skydude.dacxirons.item.armor.EbonyMagicSpellArmorItem.fullebonymagic;

@Mod.EventBusSubscriber // if you use this, handlers must be static
public class EbonyMagicEffect {

    @SubscribeEvent
    public static void onSpellAttack(SpellDamageEvent event) {
        if(fullebonymagic) {
            LivingEntity target = event.getEntity();

            // run on server only
            if (target.level().isClientSide()) return;

            // test with something visible first
            MobEffectInstance effect = new MobEffectInstance(
                    MobEffects.GLOWING, // swap to MobEffects.WITHER after confirming
                    200,                // duration (ticks)
                    0,                  // amplifier
                    false,              // ambient
                    true                // show particles
            );

            MinecraftServer server = target.getServer();
            if (server == null) return;

            // ensure main server thread
            server.execute(() -> {

                boolean applied = target.addEffect(effect);

            });
        }
    }
}
