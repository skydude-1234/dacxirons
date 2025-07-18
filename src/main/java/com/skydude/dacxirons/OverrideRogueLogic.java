package com.skydude.dacxirons;

import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class OverrideRogueLogic {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!dacxironsConfig.ENABLE_CUSTOM_ROGUE_LOGIC.get()) return;

        Entity entity = event.player;


        if (entity instanceof ServerPlayer player) {
            if (player.getAdvancements().getOrStartProgress(player.server.getAdvancements()
                    .getAdvancement(new ResourceLocation("dungeons_and_combat:the_rogue"))).isDone()) {

                // OVERRIDE or undo RogueClassEverProcedure effects
                LivingEntity living = (LivingEntity) entity;
//                living.getAttribute(Attributes.LUCK).setBaseValue((double)1.0F);
//                living.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double)16.0F);
//                living.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((double)2.0F);
//                living.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.12);
//                living.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(4.2);

                // Remove invisi shit
                living.removeEffect(MobEffects.INVISIBILITY);


                // custom shit
                if (player.isShiftKeyDown() && !player.level().isClientSide()) {
                    Entity lastHurt = player.getLastHurtMob();

                    // If the player HASN'T recently hurt anything, allow invisibility
                    if (lastHurt == null || player.getLastHurtMobTimestamp() + (dacxironsConfig.INVIS_REMOVAL_TIME.get() * 20) < player.tickCount) {
                        living.addEffect(new MobEffectInstance(MobEffectRegistry.TRUE_INVISIBILITY.get(), 10, 0, true, false));
                    } else {
                        // remove invisibility if they just hit something
                        living.removeEffect(MobEffectRegistry.TRUE_INVISIBILITY.get());
                    }
                }
            }
        }
    }
}

