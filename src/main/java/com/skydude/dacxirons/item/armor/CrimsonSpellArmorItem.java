package com.skydude.dacxirons.item.armor;


//This is crimsonspellarmoritem

import com.skydude.dacxirons.effect.SpellStrength;
import com.skydude.dacxirons.registries.EffectRegistry;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModMobEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
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

import static com.skydude.dacxirons.registries.EffectRegistry.SPELL_STRENGTH;

public class CrimsonSpellArmorItem extends ImbueabledacxironsArmor {
    private static final UUID FIRE_UUID = UUID.fromString("924e4567-e89b-12d3-a456-426614174000");

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
                    player.addEffect(new MobEffectInstance(SPELL_STRENGTH.get(), 400,2)); // 10 seconds


                }

            }
        }
    }
}
