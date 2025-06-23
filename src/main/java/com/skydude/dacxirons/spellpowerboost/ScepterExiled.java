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

public class ScepterExiled {
    // UUIDs per slot and attribute ensure proper application/removal
    private static final UUID COOLDOWN_UUID = UUID.fromString("546e4567-e89b-13d3-a456-426614174000");
    private static final UUID CAST_UUID = UUID.fromString("546e4567-e89b-12d3-a456-426614174001");
    private static final UUID SPELL_UUID = UUID.fromString("546e4567-e89b-12d3-a456-426614174002");

    @SubscribeEvent
    public static void onItemAttributeModifiers(ItemAttributeModifierEvent event) {
        Item item = event.getItemStack().getItem();
        ResourceLocation itemId = ForgeRegistries.ITEMS.getKey(item);

        if (itemId != null && itemId.toString().equals("dungeons_and_combat:scepter_of_compensation")) {
            if (event.getSlotType() == EquipmentSlot.MAINHAND) {
                Attribute cooldownPower = ForgeRegistries.ATTRIBUTES.getValue(
                        new ResourceLocation("irons_spellbooks:cooldown_reduction")
                );
                Attribute castPower = ForgeRegistries.ATTRIBUTES.getValue(
                        new ResourceLocation("irons_spellbooks:cast_time_reduction")
                );
                Attribute spellPower = ForgeRegistries.ATTRIBUTES.getValue(
                        new ResourceLocation("irons_spellbooks:spell_power")
                );

                if (cooldownPower != null) {
                    event.addModifier(cooldownPower, new AttributeModifier(
                            COOLDOWN_UUID,
                            "Cooldown Reduction",
                            0.05,
                            Operation.MULTIPLY_BASE
                    ));
                }
                if (spellPower != null) {
                    event.addModifier(spellPower, new AttributeModifier(
                            SPELL_UUID,
                            "Spell Power Bonus",
                            0.1,
                            Operation.MULTIPLY_BASE
                    ));
                }

                if (castPower != null) {
                    event.addModifier(castPower, new AttributeModifier(
                            CAST_UUID,
                            "Cast Time Reduction",
                            0.05,
                            Operation.MULTIPLY_BASE
                    ));
                }
            }
        }
    }
}

