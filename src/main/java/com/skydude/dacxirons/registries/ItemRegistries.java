package com.skydude.dacxirons.registries;

import com.google.common.collect.ImmutableMultimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.item.UpgradeOrbItem;
import io.redspace.ironsspellbooks.item.armor.UpgradeTypes;
import io.redspace.ironsspellbooks.item.curios.CurioBaseItem;
import io.redspace.ironsspellbooks.item.spell_books.SimpleAttributeSpellBook;
import io.redspace.ironsspellbooks.item.weapons.StaffItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.skydude.dacxirons.dacxirons;
import com.skydude.dacxirons.item.armor.*;
import com.skydude.dacxirons.item.armor.dacxironsArmorItem;
import com.skydude.dacxirons.item.armor.CrimsonSpellArmorItem;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;


public class ItemRegistries {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, dacxirons.MOD_ID);


    public static final RegistryObject<Item> DIVINEMANUSCRIPT = ITEMS.register("divine_manuscript_spell_book", DivineManuscriptSpellbook::new);

    public static final RegistryObject<Item> CRIMSON_WIZARD_HAT = ITEMS.register("crimson_wizard_hat",
            () -> new CrimsonSpellArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper.equipment().fireResistant()));
    public static final RegistryObject<Item> CRIMSON_WIZARD_ROBE = ITEMS.register("crimson_wizard_robe",
            () -> new CrimsonSpellArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper.equipment().fireResistant()));
    public static final RegistryObject<Item> CRIMSON_WIZARD_LEGGINGS = ITEMS.register("crimson_wizard_leggings",
            () -> new CrimsonSpellArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper.equipment().fireResistant()));
    public static final RegistryObject<Item> CRIMSON_WIZARD_BOOTS = ITEMS.register("crimson_wizard_boots",
            () -> new CrimsonSpellArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper.equipment().fireResistant()));

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
