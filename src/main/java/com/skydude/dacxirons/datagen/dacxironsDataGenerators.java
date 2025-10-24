package com.skydude.dacxirons.datagen;


import com.skydude.dacxirons.dacxirons;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = dacxirons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class dacxironsDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();




        generator.addProvider(event.includeServer(), new dacxironsGlobalLootModifiersProvider(packOutput));
    }
}
