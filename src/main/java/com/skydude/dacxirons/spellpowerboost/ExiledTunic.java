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

public class ExiledTunic {
    // UUIDs per slot and attribute ensure proper application/removal
    private static final UUID MANA_UUID = UUID.fromString("824e4567-e89b-12d3-a456-426614174000");
    private static final UUID SPELL_UUID = UUID.fromString("824e4567-e89b-12d3-a456-426614174000");

    @SubscribeEvent
    public static void onItemAttributeModifiers(ItemAttributeModifierEvent event) {
        Item item = event.getItemStack().getItem();
        ResourceLocation itemId = ForgeRegistries.ITEMS.getKey(item);

        if (itemId != null && itemId.toString().equals("dungeons_and_combat:exiled_chestplate")) {
            if (event.getSlotType() == EquipmentSlot.CHEST) {
                Attribute manaPower = ForgeRegistries.ATTRIBUTES.getValue(
                        new ResourceLocation("irons_spellbooks:max_mana")
                );
                Attribute spellPower = ForgeRegistries.ATTRIBUTES.getValue(
                        new ResourceLocation("irons_spellbooks:spell_power")
                );

                if (manaPower != null) {
                    event.addModifier(manaPower, new AttributeModifier(
                            MANA_UUID,
                            "Spell Power Bonus Mana",
                            50,
                            Operation.ADDITION
                    ));
                }
                if (spellPower != null) {
                    event.addModifier(spellPower, new AttributeModifier(
                            SPELL_UUID,
                            "Spell Power Bonus Mana",
                            0.05,
                            Operation.MULTIPLY_BASE
                    ));
                }

            }
        }
    }
}

