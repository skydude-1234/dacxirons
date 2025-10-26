package com.skydude.dacxirons.item.curios;

import io.redspace.ironsspellbooks.api.events.SpellDamageEvent;
import io.redspace.ironsspellbooks.compat.Curios;
import io.redspace.ironsspellbooks.item.curios.SimpleDescriptiveCurio;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.SlotContext;

@Mod.EventBusSubscriber
public class WitherRing extends SimpleDescriptiveCurio {

    public WitherRing() {
        super(ItemPropertiesHelper.equipment().stacksTo(1), "ring");
    }

    public static boolean held = false;
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);
        slotContext.entity().removeEffect(MobEffects.POISON);
        held = true;

    }

    @SubscribeEvent
    public static void onSpellAttack(SpellDamageEvent event) {
        if(held) {
            LivingEntity target = event.getEntity();

            // test with something visible first
            MobEffectInstance effect = new MobEffectInstance(
                    MobEffects.WITHER, // swap to MobEffects.WITHER after confirming
                    200,                // duration (ticks)
                    0,                  // amplifier
                    false,              // ambient
                    true                // show particles
            );

            // ensure main server thread

            target.addEffect(effect);

        }
    }

}
