package com.skydude.dacxirons;

import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.WeakHashMap;

@Mod.EventBusSubscriber(modid = "dacxirons")
public class OverrideRogueLogic {

    // Track last tick player hit something (melee or spell)
    private static final WeakHashMap<ServerPlayer, Integer> lastHitTickMap = new WeakHashMap<>();

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof ServerPlayer player)) return;

        // Always record hit timestamp regardless of damage source type
        lastHitTickMap.put(player, player.tickCount);
        System.out.println("[OverrideRogueLogic] Registered hit for player " + player.getName().getString() + " at tick " + player.tickCount);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!dacxironsConfig.ENABLE_CUSTOM_ROGUE_LOGIC.get()) return;
        if (!(event.player instanceof ServerPlayer player)) return;

        if (!player.getAdvancements().getOrStartProgress(
                        player.server.getAdvancements().getAdvancement(
                                new ResourceLocation("dungeons_and_combat:the_rogue")))
                .isDone()) return;

        LivingEntity living = player;

        // Remove vanilla invisibility to avoid conflicts

        living.removeEffect(MobEffects.INVISIBILITY);

        int lastHitTick = lastHitTickMap.getOrDefault(player, -99999);
        int cooldownTicks = (int) (dacxironsConfig.INVIS_REMOVAL_TIME.get() * 20);

        if (player.isShiftKeyDown() && !player.level().isClientSide()) {
            if (lastHitTick + cooldownTicks < player.tickCount) {
                if (!living.hasEffect(MobEffectRegistry.TRUE_INVISIBILITY.get())) {
                    living.addEffect(new MobEffectInstance(MobEffectRegistry.TRUE_INVISIBILITY.get(), 10, 0, true, false));
                 //   System.out.println("[OverrideRogueLogic] Added TRUE_INVISIBILITY to " + player.getName().getString());
                }
            }

        }
    }
}
