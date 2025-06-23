package com.skydude.dacxirons.spellpowerboost;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "dacxirons")
public class LootInjector {

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        // Example: Inject into Iron's Spellbooks loot tables
        ResourceLocation tableName = event.getName();

        // Replace with the actual loot table name from the mod you're targeting
        if (tableName.equals(new ResourceLocation("dungeons_and_combat", "chests/catacumbs"))) {
            LootPool pool = LootPool.lootPool()
                    .add(LootTableReference.lootTableReference(new ResourceLocation("dacxirons", "inject/bonus_loot"))
                            .setWeight(1))
                    .name("dacxirons_inject")
                    .build();

            event.getTable().addPool(pool);
        }
    }
}
