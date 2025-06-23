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

public class AcidMoltenBlazingBoots {
    // UUIDs per slot and attribute ensure proper application/removal
    private static final UUID FIRE_UUID = UUID.fromString("524e4567-e89b-12d3-a456-426614174000");


    @SubscribeEvent
    public static void onItemAttributeModifiers(ItemAttributeModifierEvent event) {
        Item item = event.getItemStack().getItem();
        ResourceLocation itemId = ForgeRegistries.ITEMS.getKey(item);

        if (itemId != null && itemId.toString().equals("dungeons_and_combat:acid_molten_blazing_boots")) {
            if (event.getSlotType() == EquipmentSlot.FEET) {
                Attribute firePower = ForgeRegistries.ATTRIBUTES.getValue(
                        new ResourceLocation("irons_spellbooks:fire_spell_power")
                );


                if (firePower != null) {
                    event.addModifier(firePower, new AttributeModifier(
                            FIRE_UUID,
                            "Spell Power Bonus Fire",
                            0.05,
                            Operation.MULTIPLY_BASE
                    ));
                }


            }
        }
    }
}

