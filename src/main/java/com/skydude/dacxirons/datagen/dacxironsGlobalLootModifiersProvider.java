package com.skydude.dacxirons.datagen;

import com.skydude.dacxirons.dacxirons;
import com.skydude.dacxirons.item.loot.AddItemModifier;
import com.skydude.dacxirons.registries.ItemRegistries;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModEntities;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import org.checkerframework.checker.units.qual.A;

public class dacxironsGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public dacxironsGlobalLootModifiersProvider(PackOutput output) {
        super(output, dacxirons.MOD_ID);
    }

    @Override
    protected void start() {
        add("corroding_flame_staff_hermit_witch", new AddItemModifier(new LootItemCondition[]{
                LootItemRandomChanceCondition.randomChance(5F).build(),
                new LootTableIdCondition.Builder(new ResourceLocation("dungeons_and_combat", "entities/hermit_witch")).build()
        }, ItemRegistries.CORRODING_FLAME_STAFF.get()));
    }
}
