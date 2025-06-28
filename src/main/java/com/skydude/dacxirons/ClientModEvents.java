package com.skydude.dacxirons;

import com.skydude.dacxirons.registries.EntityRegistry;
import net.mcreator.dungeonsandcombat.client.renderer.KamathRenderer;
import net.mcreator.dungeonsandcombat.client.renderer.WeaknessRenderer;
import net.mcreator.dungeonsandcombat.entity.WeaknessEntity;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModEntityRenderers;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// Your mod id
@Mod.EventBusSubscriber(modid = dacxirons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // Your entity type here (e.g., SummonedWeakness entity)
        event.registerEntityRenderer(EntityRegistry.SUMMONED_WEAKNESS.get(), WeaknessRenderer::new);
        event.registerEntityRenderer(EntityRegistry.SUMMONED_KAMATH.get(), KamathRenderer::new);

        // Register other renderers if needed
    }
}
