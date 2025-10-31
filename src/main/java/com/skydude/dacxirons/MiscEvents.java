package com.skydude.dacxirons;

import com.skydude.dacxirons.item.armor.CorrectArmor;
import com.skydude.dacxirons.item.armor.dacxironsArmorMaterials;
import com.skydude.dacxirons.registries.EffectRegistry;
import com.skydude.dacxirons.registries.ItemRegistries;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModItems;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModMobEffects;
import net.mcreator.dungeonsandcombat.item.BloodymancerItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Mod.EventBusSubscriber
public class MiscEvents {



    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity living =  event.getEntity();
        if (living.getItemBySlot(EquipmentSlot.CHEST).getItem() == DungeonsAndCombatModItems.BLOODYMANCER_CHESTPLATE.get() && living.getItemBySlot(EquipmentSlot.HEAD).getItem() == DungeonsAndCombatModItems.BLOODYMANCER_HELMET.get()){
            var effect = living.getEffect(DungeonsAndCombatModMobEffects.BLEEDING.get());

            if(event.getEntity().hasEffect(DungeonsAndCombatModMobEffects.BLEEDING.get())){
                // null checkk
                if(effect == null) return;

                for (int i = 0; i < Objects.requireNonNull(event.getEntity().getEffect(DungeonsAndCombatModMobEffects.BLEEDING.get())).getAmplifier() + 3; i++) {

                    event.getEntity().addEffect(new MobEffectInstance(EffectRegistry.BLOOD_SPELL_STRENGTH.get(), 100, i, true, true));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() instanceof BloodymancerItem) {
            var tooltip = event.getToolTip();
            tooltip.add(1, Component.translatable("fullsetbonus"));
            tooltip.add(2, Component.translatable("Blood Bending"));
            tooltip.add(3, Component.translatable("tooltip.dacxirons.blood_bending_dec"));
        }
    }
}
