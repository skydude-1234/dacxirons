package com.skydude.dacxirons.item.armor;


//This is crimsonspellarmoritem

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import com.skydude.dacxirons.registries.ItemRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;


import javax.annotation.Nonnull;

import java.util.List;

public class CrimsonSpellArmorItem extends ImbueabledacxironsArmor {
    public CrimsonSpellArmorItem(ArmorItem.Type type, Properties properties) {
        super(dacxironsArmorMaterials.CRIMSON_SPELL_ARMOR, type, properties);


    }

}