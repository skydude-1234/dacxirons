package com.skydude.dacxirons.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;


import com.skydude.dacxirons.dacxirons;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, dacxirons.MOD_ID);

    public static final RegistryObject<SoundEvent> MAGIC_ARROW_SOUND =
            SOUNDS.register("magic_arrow_sound",
                    () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(dacxirons.MOD_ID, "magic_arrow_sound")));

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
