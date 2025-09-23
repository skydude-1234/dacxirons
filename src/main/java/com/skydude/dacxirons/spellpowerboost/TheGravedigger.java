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

public class TheGravedigger {
    // UUIDs per slot and attribute ensure proper application/removal
    private static final UUID SUMMON_UUID = UUID.fromString("546e4567-e89b-12d3-a456-426614174671");
    private static final UUID COOLDOWN_UUID = UUID.fromString("546e4567-e89b-12d3-a456-426614174672");

    @SubscribeEvent
    public static void onItemAttributeModifiers(ItemAttributeModifierEvent event) {
        Item item = event.getItemStack().getItem();
        ResourceLocation itemId = ForgeRegistries.ITEMS.getKey(item);

        if (itemId != null && itemId.toString().equals("dungeons_and_combat:the_gravedigger_greataxe")) {
            if (event.getSlotType() == EquipmentSlot.MAINHAND) {
                Attribute summonPower = ForgeRegistries.ATTRIBUTES.getValue(
                        new ResourceLocation("irons_spellbooks:summon_damage")
                );
                Attribute cooldownPower = ForgeRegistries.ATTRIBUTES.getValue(
                        new ResourceLocation("irons_spellbooks:cooldown_reduction")
                );

                if (summonPower != null) {
                    event.addModifier(summonPower, new AttributeModifier(
                            SUMMON_UUID,
                            "sumon dmg ",
                            0.1,
                            Operation.MULTIPLY_BASE
                    ));
                }
                if (cooldownPower != null) {
                    event.addModifier(cooldownPower, new AttributeModifier(
                            COOLDOWN_UUID,
                            "  cooldown reduc dacxiro",
                            0.05,
                            Operation.MULTIPLY_BASE
                    ));
                }


            }
        }
    }
}

