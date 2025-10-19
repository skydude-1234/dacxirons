package com.skydude.dacxirons.registries;

import com.skydude.dacxirons.client.model.blazymancer_model;
import com.skydude.dacxirons.client.model.blessed_priest_model;
import com.skydude.dacxirons.client.model.crimson_spell_model;
import com.skydude.dacxirons.client.model.ebony_spell_model;
import com.skydude.dacxirons.dacxirons;
import com.skydude.dacxirons.renderers.EldritchSlashRenderer;
import com.skydude.dacxirons.renderers.AcidFireBallRenderer;
import net.mcreator.dungeonsandcombat.client.renderer.KamathRenderer;
import net.mcreator.dungeonsandcombat.client.renderer.WeaknessRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = dacxirons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RendererRegistry {
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        //Entity renderer stuffs
        event.registerEntityRenderer(EntityRegistry.SUMMONED_WEAKNESS.get(), WeaknessRenderer::new);
        event.registerEntityRenderer(EntityRegistry.SUMMONED_KAMATH.get(), KamathRenderer::new);
        event.registerEntityRenderer(EntityRegistry.ELDRITCH_SLASH.get(), EldritchSlashRenderer::new);
        event.registerEntityRenderer(EntityRegistry.ACID_BALL.get(),  AcidFireBallRenderer::new);


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