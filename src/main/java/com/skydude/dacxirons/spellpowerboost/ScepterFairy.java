// Spell power 10
// cooldown_reduction 5
// cast_time_reduction
package com.skydude.dacxirons.spellpowerboost;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.UUID;

//@Mod.EventBusSubscriber(modid = "com.example.spellpowerboostb", bus = Mod.EventBusSubscriber.Bus.FORGE)
@EventBusSubscriber(
        modid = "dacxirons",
        bus = Bus.FORGE
)

public class ScepterFairy {
    // UUIDs per slot and attribute ensure proper application/removal
    private static final UUID NATURE_UUID = UUID.fromString("546e4567-e89b-12d3-a456-426614174005");
    private static final UUID MANA_UUID = UUID.fromString("546e4567-e67b-12d3-a456-426614174005");

    @SubscribeEvent
    public static void onItemAttributeModifiers(ItemAttributeModifierEvent event) {
        Item item = event.getItemStack().getItem();
        ResourceLocation itemId = ForgeRegistries.ITEMS.getKey(item);

        if (itemId != null && itemId.toString().equals("dungeons_and_combat:fairy_scepter")) {
            if (event.getSlotType() == EquipmentSlot.MAINHAND) {
                Attribute firePower = ForgeRegistries.ATTRIBUTES.getValue(
                        new ResourceLocation("irons_spellbooks:nature_spell_power")
                );

                if (firePower != null) {
                    event.addModifier(firePower, new AttributeModifier(
                            NATURE_UUID,
                            "Nature Spell POwer",
                            0.10,
                            Operation.MULTIPLY_BASE
                    ));
                }
                Attribute regenPower = ForgeRegistries.ATTRIBUTES.getValue(
                        new ResourceLocation("irons_spellbooks:mana_regen")
                );
                if (regenPower != null) {
                    event.addModifier(firePower, new AttributeModifier(
                            NATURE_UUID,
                            "sss Spell POwer",
                            0.15,
                            Operation.MULTIPLY_BASE
                    ));
                }
            }
        }
    }
}


