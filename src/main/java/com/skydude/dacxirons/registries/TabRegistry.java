package com.skydude.dacxirons.registries;

import com.skydude.dacxirons.dacxirons;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, dacxirons.MOD_ID);

    public static final RegistryObject<CreativeModeTab> DACXIRONS_TAB = CREATIVE_MODE_TABS.register("dacxirons_tab", () ->
            CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ItemRegistries.CRIMSON_WIZARD_ROBE.get()))
                    .title(Component.translatable("itemGroup.dacxirons.dacxirons_tab"))
                    .displayItems((parameters, output) -> {
                        // Add your items here

                        output.accept(ItemRegistries.CRIMSON_WIZARD_HOOD.get());
                        output.accept(ItemRegistries.CRIMSON_WIZARD_ROBE.get());
                        output.accept(ItemRegistries.CRIMSON_WIZARD_LEGGINGS.get());
                        output.accept(ItemRegistries.CRIMSON_WIZARD_BOOTS.get());
                        output.accept(ItemRegistries.BLAZYMANCER_HOOD.get());
                        output.accept(ItemRegistries.BLAZYMANCER_TUNIC.get());
                        output.accept(ItemRegistries.DIVINEMANUSCRIPT.get());
                     //   output.accept(ItemRegistries.SCEPTEROVERCOMPENSATION.get());
                        output.accept(ItemRegistries.CRIMSON_CLOTH.get());
                        output.accept(ItemRegistries.EBONY_WIZARD_HOOD.get());
                        output.accept(ItemRegistries.EBONY_WIZARD_ROBE.get());
                        output.accept(ItemRegistries.EBONY_WIZARD_LEGGINGS.get());
                        output.accept(ItemRegistries.EBONY_WIZARD_BOOTS.get());
                        output.accept(ItemRegistries.BLESSED_PRIEST_HOOD.get());
                        output.accept(ItemRegistries.BLESSED_PRIEST_ROBE.get());
                        output.accept(ItemRegistries.BLESSED_PRIEST_LEGGINGS.get());
                        output.accept(ItemRegistries.BLESSED_PRIEST_BOOTS.get());
                        output.accept(ItemRegistries.WITHER_RUNE.get());
                        output.accept(ItemRegistries.DIVINE_MOLD.get());
                        output.accept(ItemRegistries.SCEPTER_COMPENSATION_STAFF.get());
                        output.accept(ItemRegistries.SCEPTERPYROCLASTIC.get());
                        output.accept(ItemRegistries.CORRODING_FLAME_STAFF.get());
                        //output.accept(ItemRegistries.SUMMONS_NECKLACE.get());
                    })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
