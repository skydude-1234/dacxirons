package com.skydude.dacxirons.registries;

import com.google.common.collect.ImmutableMultimap;
import com.skydude.dacxirons.item.CrimsonClothItem;
import com.skydude.dacxirons.item.DivineMoldTemplateItem;
import com.skydude.dacxirons.item.WitherRuneItem;
import com.skydude.dacxirons.item.armor.BlazymancerSpellArmorItem;
import com.skydude.dacxirons.item.armor.BlessedPriestArmorItem;
import com.skydude.dacxirons.item.armor.EbonyMagicSpellArmorItem;
import com.skydude.dacxirons.item.spellbook.DivineManuscriptSpellbook;
import com.skydude.dacxirons.item.weapons.CorrodingFlameItem;
import com.skydude.dacxirons.item.weapons.ScepterofOvercompensation;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.item.spell_books.SimpleAttributeSpellBook;
import com.skydude.dacxirons.item.weapons.sceptercompensation;
import com.skydude.dacxirons.item.weapons.pyromancerStaffItem;

import io.redspace.ironsspellbooks.item.weapons.BloodStaffItem;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.skydude.dacxirons.dacxirons;
import com.skydude.dacxirons.item.armor.CrimsonSpellArmorItem;

import java.util.Collection;
import java.util.UUID;


public class ItemRegistries {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, dacxirons.MOD_ID);

    public static final RegistryObject<Item> CRIMSON_CLOTH = ITEMS.register("crimson_cloth", CrimsonClothItem::new);
    public static final RegistryObject<Item> DIVINE_MOLD = ITEMS.register("divine_mold", DivineMoldTemplateItem::new);

    public static final RegistryObject<Item> SCEPTEROVERCOMPENSATION = ITEMS.register("scepter_of_over_compensation",
            ScepterofOvercompensation::new);
    public static final RegistryObject<Item> SCEPTERPYROCLASTIC = ITEMS.register("pyromancer_staff",
           pyromancerStaffItem::new);
    public static final RegistryObject<Item> CORRODING_FLAME_STAFF = ITEMS.register("corroding_flame_staff",
            CorrodingFlameItem::new);

    public static final RegistryObject<Item> SCEPTER_COMPENSATION_STAFF = ITEMS.register("scepter_of_compensation_staff", sceptercompensation::new);
    public static final RegistryObject<Item> DIVINEMANUSCRIPT = ITEMS.register("divine_manuscript_spell_book", DivineManuscriptSpellbook::new);

    public static final RegistryObject<Item> CRIMSON_WIZARD_HOOD = ITEMS.register("crimson_wizard_hood",
            CrimsonSpellArmorItem.Helmet::new);

    public static final RegistryObject<Item> CRIMSON_WIZARD_ROBE = ITEMS.register("crimson_wizard_robe",
            CrimsonSpellArmorItem.Chestplate::new);

    public static final RegistryObject<Item> CRIMSON_WIZARD_LEGGINGS = ITEMS.register("crimson_wizard_leggings",
            CrimsonSpellArmorItem.Leggings::new);

    public static final RegistryObject<Item> CRIMSON_WIZARD_BOOTS = ITEMS.register("crimson_wizard_boots",
            CrimsonSpellArmorItem.Boots::new);
    public static final RegistryObject<Item> EBONY_WIZARD_HOOD = ITEMS.register("ebony_wizard_hood",
            EbonyMagicSpellArmorItem.Helmet::new);

    public static final RegistryObject<Item> EBONY_WIZARD_ROBE = ITEMS.register("ebony_wizard_robe",
            EbonyMagicSpellArmorItem.Chestplate::new);

    public static final RegistryObject<Item> EBONY_WIZARD_LEGGINGS = ITEMS.register("ebony_wizard_leggings",
            EbonyMagicSpellArmorItem.Leggings::new);

    public static final RegistryObject<Item> EBONY_WIZARD_BOOTS = ITEMS.register("ebony_wizard_boots",
            EbonyMagicSpellArmorItem.Boots::new);
    public static final RegistryObject<Item> BLESSED_PRIEST_HOOD = ITEMS.register("blessed_priest_hood",
            BlessedPriestArmorItem.Helmet::new);

    public static final RegistryObject<Item> BLESSED_PRIEST_ROBE = ITEMS.register("blessed_priest_robe",
            BlessedPriestArmorItem.Chestplate::new);

    public static final RegistryObject<Item> BLESSED_PRIEST_LEGGINGS = ITEMS.register("blessed_priest_leggings",
            BlessedPriestArmorItem.Leggings::new);

    public static final RegistryObject<Item> BLESSED_PRIEST_BOOTS = ITEMS.register("blessed_priest_boots",
            BlessedPriestArmorItem.Boots::new);


    public static final RegistryObject<Item> BLAZYMANCER_HOOD = ITEMS.register("blazymancer_hood",
            BlazymancerSpellArmorItem.Helmet::new);
    public static final RegistryObject<Item> BLAZYMANCER_TUNIC = ITEMS.register("blazymancer_tunic",
            BlazymancerSpellArmorItem.Chestplate::new);
    public static final RegistryObject<Item> WITHER_RUNE = ITEMS.register("wither_rune",
            WitherRuneItem::new);


    public static final RegistryObject<Item> GILLAGER_SPELL_BOOK = ITEMS.register("gillager_spell_book", () -> {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(AttributeRegistry.HOLY_SPELL_POWER.get(), new AttributeModifier(UUID.fromString("999ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", .08, AttributeModifier.Operation.MULTIPLY_BASE));
        builder.put(AttributeRegistry.CAST_TIME_REDUCTION.get(), new AttributeModifier(UUID.fromString("999ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", .08, AttributeModifier.Operation.MULTIPLY_BASE));
        builder.put(AttributeRegistry.MAX_MANA.get(), new AttributeModifier(UUID.fromString("999ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", 200, AttributeModifier.Operation.ADDITION));
        return new SimpleAttributeSpellBook(10, SpellRarity.LEGENDARY, builder.build());



        });


  // something about curios or smthng idk
  //  public static Collection<RegistryObject<Item>> getdacxironsItems()
//    {
//        return ITEMS.getEntries();
//    }

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
    public static Collection<RegistryObject<Item>> getItems() {
        return ITEMS.getEntries();
}

}
