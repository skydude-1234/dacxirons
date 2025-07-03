package com.skydude.dacxirons;

import com.skydude.dacxirons.client.model.crimson_spell_model;
import com.skydude.dacxirons.registries.EntityRegistry;
import net.mcreator.dungeonsandcombat.client.renderer.KamathRenderer;
import net.mcreator.dungeonsandcombat.client.renderer.WeaknessRenderer;
import net.mcreator.dungeonsandcombat.entity.WeaknessEntity;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.skydude.dacxirons.client.model.crimson_spell_model;

// ClientModEvents

@Mod.EventBusSubscriber(modid = dacxirons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        //Entity renderer stuffs
        event.registerEntityRenderer(EntityRegistry.SUMMONED_WEAKNESS.get(), WeaknessRenderer::new);
        event.registerEntityRenderer(EntityRegistry.SUMMONED_KAMATH.get(), KamathRenderer::new);



    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(crimson_spell_model.LAYER_LOCATION, crimson_spell_model::createBodyLayer);
    }

}
