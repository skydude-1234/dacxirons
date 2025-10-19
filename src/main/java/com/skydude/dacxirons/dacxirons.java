package com.skydude.dacxirons;

import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import com.skydude.dacxirons.registries.EntityRegistry;

import com.skydude.dacxirons.registries.*;
import org.jetbrains.annotations.NotNull;

@Mod("dacxirons")
public class dacxirons {
    public static final String MOD_ID = "dacxirons";

    public dacxirons() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, dacxironsConfig.COMMON_CONFIG);

        SoundRegistry.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        dacxironsSpellRegistry.register(modEventBus);
        ItemRegistries.register(modEventBus);
        EffectRegistry.MOB_EFFECTS.register(modEventBus);
        TabRegistry.register(modEventBus); //  register your creative tab


        EntityRegistry.register(modEventBus);
    }
    EntityRenderersEvent.RegisterRenderers event;


    private static final ResourceLocation ADVANCEMENT_ID = new ResourceLocation("dungeons_and_combat", "the_exiled");
    private static final ResourceLocation ATTRIBUTE_ID = new ResourceLocation("irons_spellbooks", "spell_power");

    private static final double BONUS_PERCENT = 10.0;
    private static final double BONUS_MULTIPLY = 1 + (BONUS_PERCENT / 100.0);

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.player.level().isClientSide()) return;

        ServerPlayer player = (ServerPlayer) event.player;
        Advancement adv = player.server.getAdvancements().getAdvancement(ADVANCEMENT_ID);
        if (adv == null) return;

        boolean hasAdvancement = player.getAdvancements().getOrStartProgress(adv).isDone();
        AttributeInstance attribute = player.getAttribute(ForgeRegistries.ATTRIBUTES.getValue(ATTRIBUTE_ID));
        if (attribute == null) return;

        double baseValue = attribute.getBaseValue();
        double defaultBase = 1.0;

        boolean bonusApplied = baseValue >= defaultBase * BONUS_MULTIPLY;

        if (hasAdvancement && !bonusApplied) {
            attribute.setBaseValue(baseValue * BONUS_MULTIPLY);
        } else if (!hasAdvancement && bonusApplied) {
            attribute.setBaseValue(baseValue / BONUS_MULTIPLY);
        }
    }
    public static ResourceLocation id(@NotNull String path) {
        return new ResourceLocation(dacxirons.MOD_ID, path);
    }
}