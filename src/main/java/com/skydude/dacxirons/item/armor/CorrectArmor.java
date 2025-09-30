package com.skydude.dacxirons.item.armor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

import com.skydude.dacxirons.item.armor.dacxironsArmorItem;
import com.skydude.dacxirons.item.armor.dacxironsArmorMaterials;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.item.ArmorMaterial;

import net.minecraft.world.item.ItemStack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.Arrays;

public class CorrectArmor {

    public static boolean hasFullSetOn(LivingEntity living, dacxironsArmorMaterials material, int requiredPieces) {
        requiredPieces = 4;
        int equippedPieces = 0;

        for (ItemStack armorStack : living.getArmorSlots()) {
            if (!armorStack.isEmpty() && armorStack.getItem() instanceof ArmorItem armorItem) {
                if (armorItem.getMaterial() == material) {
                    equippedPieces++;

                }
            }
        }

        return equippedPieces >= requiredPieces;
    }

}

//public class CorrectArmor {
//
//    public static boolean hasCorrectArmorOn(LivingEntity entity, ArmorMaterial material) {
//        ArrayList<EquipmentSlot> slots = new ArrayList<>(Arrays.asList(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET));
//        for (EquipmentSlot slot : slots) {
//            ItemStack stack = entity.getItemBySlot(slot);
//            if (stack.isEmpty()) {
//                return false;
//            }
//            if (!(stack.getItem() instanceof ArmorItem)) {
//                return false;
//            }
//            if (!((ArmorItem) stack.getItem()).getMaterial().equals(material)) {
//                return false;
//            }
//        }
//        return true;
//    }
//}
