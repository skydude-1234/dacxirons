package com.skydude.dacxirons;



import com.skydude.dacxirons.entity.spells.EldritchSlash.EldritchSlashRenderer;
import com.skydude.dacxirons.registries.EntityRegistry;



import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;




import static com.skydude.dacxirons.dacxirons.MOD_ID;


@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {


    @SubscribeEvent
    public static void rendererRegister(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(EntityRegistry.ELDRITCH_SLASH.get(), EldritchSlashRenderer::new);

    }


}

