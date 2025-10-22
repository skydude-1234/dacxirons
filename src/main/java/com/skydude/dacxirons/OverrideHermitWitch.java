package com.skydude.dacxirons;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import static com.skydude.dacxirons.dacxirons.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class OverrideHermitWitch {
    private static final ResourceLocation WITCH_ID =
            new ResourceLocation("dungeons_and_combat", "hermit_witch");
    private static final ResourceLocation SCEPTER_ID =
            new ResourceLocation("dungeons_and_combat", "corroding_flame"); // item id

    @SubscribeEvent
    public static void onDrops(LivingDropsEvent e) {
        var ent = e.getEntity();
        var typeKey = ForgeRegistries.ENTITY_TYPES.getKey(ent.getType());
        if (typeKey == null || !typeKey.equals(WITCH_ID)) return;

        e.getDrops().removeIf(drop -> {
            var id = ForgeRegistries.ITEMS.getKey(drop.getItem().getItem());
            return id != null && id.equals(SCEPTER_ID);
        });
    }
}
