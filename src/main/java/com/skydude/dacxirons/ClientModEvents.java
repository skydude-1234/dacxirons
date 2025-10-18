package com.skydude.dacxirons;

import com.skydude.dacxirons.client.model.*;
import com.skydude.dacxirons.entity.spells.EldritchSlash.EldritchSlashRenderer;
import com.skydude.dacxirons.item.armor.BlazymancerSpellArmorItem;
import com.skydude.dacxirons.registries.EntityRegistry;
import com.skydude.dacxirons.registries.ItemRegistries;
import net.mcreator.dungeonsandcombat.client.renderer.KamathRenderer;
import net.mcreator.dungeonsandcombat.client.renderer.WeaknessRenderer;
import net.mcreator.dungeonsandcombat.entity.WeaknessEntity;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModEntityRenderers;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModParticleTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.skydude.dacxirons.client.model.crimson_spell_model;

import javax.annotation.Nullable;

// ClientModEvents

@Mod.EventBusSubscriber(modid = dacxirons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        //Entity renderer stuffs
        event.registerEntityRenderer(EntityRegistry.SUMMONED_WEAKNESS.get(), WeaknessRenderer::new);
        event.registerEntityRenderer(EntityRegistry.SUMMONED_KAMATH.get(), KamathRenderer::new);
        event.registerEntityRenderer(EntityRegistry.ELDRITCH_SLASH.get(), EldritchSlashRenderer::new);


    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(crimson_spell_model.LAYER_LOCATION, crimson_spell_model::createBodyLayer);
    }
    @SubscribeEvent
    public static void onRegisterLayerDefinitions2(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(blazymancer_model.LAYER_LOCATION, blazymancer_model::createBodyLayer);
    }
    @SubscribeEvent
    public static void onRegisterLayerDefinitions3(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ebony_spell_model.LAYER_LOCATION, ebony_spell_model::createBodyLayer);
    }
    @SubscribeEvent
    public static void onRegisterLayerDefinitions4(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(blessed_priest_model.LAYER_LOCATION, blessed_priest_model::createBodyLayer);
    }



}
