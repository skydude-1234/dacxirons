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

public class bloodymancerhelmet {
    // UUIDs per slot and attribute ensure proper application/removal
    private static final UUID BLOOD_UUID = UUID.fromString("623e4567-e89b-12d3-a456-426614174001");
    private static final UUID MANA_UUID = UUID.fromString("623e4567-e89b-12d3-a456-426614174002");

    @SubscribeEvent
    public static void onItemAttributeModifiers(ItemAttributeModifierEvent event) {
        Item item = event.getItemStack().getItem();
        ResourceLocation itemId = ForgeRegistries.ITEMS.getKey(item);

        if (itemId != null && itemId.toString().equals("dungeons_and_combat:bloodymancer_helmet")) {
            if (event.getSlotType() == EquipmentSlot.HEAD) {
                Attribute bloodPower = ForgeRegistries.ATTRIBUTES.getValue(
                        new ResourceLocation("irons_spellbooks:blood_spell_power")
                );
                Attribute manaPower = ForgeRegistries.ATTRIBUTES.getValue(
                        new ResourceLocation("irons_spellbooks:max_mana")
                );


                if (bloodPower != null) {
                    event.addModifier(bloodPower, new AttributeModifier(
                            BLOOD_UUID,
                            "Spell Power Bonus Blood",
                            0.10,
                            Operation.MULTIPLY_BASE
                    ));
                }
                if (manaPower != null) {
                    event.addModifier(manaPower, new AttributeModifier(
                            MANA_UUID,
                            "Spell  Bonus Mana",
                            75,
                            Operation.ADDITION
                    ));
                }
            }
        }
    }
}
