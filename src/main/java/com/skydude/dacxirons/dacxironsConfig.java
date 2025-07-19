package com.skydude.dacxirons;



import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class dacxironsConfig {
    public static final ForgeConfigSpec COMMON_CONFIG;
    public static final ForgeConfigSpec.BooleanValue ENABLE_CUSTOM_ROGUE_LOGIC;
    public static final ForgeConfigSpec.DoubleValue INVIS_REMOVAL_TIME

            ;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("Config (:");


        ENABLE_CUSTOM_ROGUE_LOGIC = builder
                .comment("True Invisibility for Rogue")
                .define("enableTrueInvis", true);

        INVIS_REMOVAL_TIME = builder
                .comment("How long invis will be removed when player hits a mob, in seconds")
                .defineInRange("Invis Removal Time", 3.0, 0, 100.0);

        builder.pop();


        COMMON_CONFIG = builder.build();
    }
}

