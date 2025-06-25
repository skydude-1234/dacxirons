package com.skydude.dacxirons.item.armor;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public enum dacxironsArmorMaterials implements ArmorMaterial {


    // Abyssal Warlock Armor
    CRIMSON_SPELL_ARMOR("crimson_spell_armor", 35, new int[]{6, 8, 10, 5}, 20, SoundEvents.ARMOR_EQUIP_NETHERITE, 2.0F, 0.0F,
            () -> Ingredient.of(ForgeRegistries.ITEMS.getValue(new ResourceLocation("dungeons_and_combat", "crimson_ingot"))), Map.of(
            AttributeRegistry.MAX_MANA.get(), new AttributeModifier("Max mana", 125, AttributeModifier.Operation.ADDITION),
            AttributeRegistry.ELDRITCH_SPELL_POWER.get(), new AttributeModifier("Eldritch Spell power", 0.15, AttributeModifier.Operation.MULTIPLY_BASE),
            AttributeRegistry.BLOOD_SPELL_POWER.get(), new AttributeModifier("Blood Spell power", 0.10, AttributeModifier.Operation.MULTIPLY_BASE)
    ));



    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;
    private final Map<Attribute, AttributeModifier> additionalAttributes;

    dacxironsArmorMaterials(String pName, int pDurabilityMultiplier, int[] pSlotProtections, int pEnchantmentValue, SoundEvent pSound, float pToughness, float pKnockbackResistance, Supplier<Ingredient> pRepairIngredient, Map<Attribute, AttributeModifier> additionalAttributes)
    {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;
        this.slotProtections = pSlotProtections;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.toughness = pToughness;
        this.knockbackResistance = pKnockbackResistance;
        this.repairIngredient = pRepairIngredient;
        this.additionalAttributes = additionalAttributes;
    }


    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return HEALTH_PER_SLOT[type.getSlot().getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.slotProtections[type.getSlot().getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return this.sound;
    }


    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }


    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    public Map<Attribute, AttributeModifier> getAdditionalAttributes() {
        return additionalAttributes;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}