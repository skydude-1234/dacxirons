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

public class Oathh {
    // UUIDs per slot and attribute ensure proper application/removal
    private static final UUID ELDRITCH_UUID = UUID.fromString("671ad88d-901d-4691-b2a2-6767e67026d3");
    private static final UUID RESIST_UUID = UUID.fromString("9c3f9a0b-0f1e-4c8a-9f6b-2a6b9f9d3b11");

    @SubscribeEvent
    public static void onItemAttributeModifiers(ItemAttributeModifierEvent event) {
        Item item = event.getItemStack().getItem();
        ResourceLocation itemId = ForgeRegistries.ITEMS.getKey(item);

        if (itemId != null && itemId.toString().equals("dungeons_and_combat:oath_sword")) {
            if (event.getSlotType() == EquipmentSlot.MAINHAND) {
                Attribute eldritchPower = ForgeRegistries.ATTRIBUTES.getValue(
                        new ResourceLocation("irons_spellbooks:eldritch_spell_power")
                );
                Attribute resistPower = ForgeRegistries.ATTRIBUTES.getValue(
                        new ResourceLocation("irons_spellbooks:spell_resist")
                );

                if (eldritchPower != null) {
                    event.addModifier(eldritchPower, new AttributeModifier(
                            ELDRITCH_UUID,
                            "eldritch Spell Power",
                            0.10,
                            Operation.MULTIPLY_BASE
                    ));
                }
                if (resistPower != null) {
                    event.addModifier(resistPower, new AttributeModifier(
                            RESIST_UUID,
                            "resist Spell Power",
                            0.05,
                            Operation.MULTIPLY_BASE
                    ));
                }

            }
        }
    }
}


