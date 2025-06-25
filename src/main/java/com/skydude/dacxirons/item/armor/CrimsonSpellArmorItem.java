package com.skydude.dacxirons.item.armor;


//This is crimsonspellarmoritem

import com.skydude.dacxirons.effect.SpellStrength;
import com.skydude.dacxirons.registries.EffectRegistry;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModMobEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import com.skydude.dacxirons.effect.SpellStrength;

import java.util.UUID;

//import static com.skydude.dacxirons.registries.EffectRegistry.SPELL_STRENGTH;
import net.minecraft.resources.ResourceLocation;

import static com.skydude.dacxirons.registries.EffectRegistry.CAST_SPEED;
import static com.skydude.dacxirons.registries.EffectRegistry.SPELL_STRENGTH;
import static net.minecraft.world.effect.MobEffects.*;

public class CrimsonSpellArmorItem extends ImbueabledacxironsArmor {


    public CrimsonSpellArmorItem(ArmorItem.Type type, Properties properties) {
        super(dacxironsArmorMaterials.CRIMSON_SPELL_ARMOR, type, properties);


    }


    @SuppressWarnings("removal")
    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player ) {
        // if (event.phase == TickEvent.Phase.END) {
        //  Player player = event.player;


        //    AttributeInstance swimSpeed = player.getAttribute(ForgeMod.SWIM_SPEED.get());
        if (CorrectArmor.hasFullSetOn(player, dacxironsArmorMaterials.CRIMSON_SPELL_ARMOR)) {

            if (player.hasEffect(DungeonsAndCombatModMobEffects.BLEEDING.get())) {
                //player.addEffect(new MobEffectInstance(DungeonsAndCombatModMobEffects.BLEEDING.get(), 200, 0));
                if (!player.hasEffect(SPELL_STRENGTH.get())) {
                    if (player.getEffect(SPELL_STRENGTH.get()) == null || player.getEffect(SPELL_STRENGTH.get()).getDuration() < 10) {
                        player.addEffect(new MobEffectInstance(
                                SPELL_STRENGTH.get(),
                                10,                  // duration in ticks (20 seconds)
                                2,                    // EFFECT LEVEL 0 = 1, 1 = 2, 2 = 3, etc
                                true,                 // ambient — blue outline + no flashing
                                false,                 // show particles
                                true                  // show icon
                        ));
                    }
                }
                if (!player.hasEffect(DAMAGE_RESISTANCE)) {
                    if (player.getEffect(DAMAGE_RESISTANCE) == null || player.getEffect(DAMAGE_RESISTANCE).getDuration() < 10) {
                        player.addEffect(new MobEffectInstance(
                                DAMAGE_RESISTANCE,
                                10,
                                2,
                                true,
                                false,
                                true
                        ));
                    }
                }
                if (!player.hasEffect(MOVEMENT_SPEED)) {
                    if (player.getEffect(MOVEMENT_SPEED) == null || player.getEffect(MOVEMENT_SPEED).getDuration() < 10) {
                        player.addEffect(new MobEffectInstance(
                                MOVEMENT_SPEED,
                                10,
                                2,
                                true,
                                false,
                                true
                        ));
                    }
                }
                if (!player.hasEffect(CAST_SPEED.get())) {
                    if (player.getEffect(CAST_SPEED.get()) == null || player.getEffect(CAST_SPEED.get()).getDuration() < 10) {
                        player.addEffect(new MobEffectInstance(
                                CAST_SPEED.get(),
                                10,                  // duration in ticks (20 seconds)
                                1,                    // amplifier (level 3 effect)
                                true,                 // ambient — blue outline + no flashing
                                false,                 // show particles
                                true                  // show icon
                        ));
                    }
                }

            }
        }
    }
}
