package com.skydude.dacxirons;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = "dacxirons", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AnvilEvents {

    // Replace these with the real item IDs
    private static final ResourceLocation TARGET_ITEM_ID = new ResourceLocation("dungeons_and_combat", "scepter_of_compensation");
    private static final ResourceLocation REPAIR_MATERIAL_ID = new ResourceLocation("irons_spellbooks", "arcane_essence");

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();   // Item to be repaired
        ItemStack right = event.getRight(); // Repair material

        // Null/empty checks
        if (left.isEmpty() || right.isEmpty()) return;

        // Look up the target item and repair material
        Item targetItem = ForgeRegistries.ITEMS.getValue(TARGET_ITEM_ID);
        Item repairMaterial = ForgeRegistries.ITEMS.getValue(REPAIR_MATERIAL_ID);

        // If either item wasn't found in registry, skip
        if (targetItem == null || repairMaterial == null) return;

        // Check for valid anvil inputs
        if (left.getItem() == targetItem && right.getItem() == repairMaterial && left.isDamaged()) {
            ItemStack output = left.copy();
            int repairAmount = 100; // How much durability to repair
            output.setDamageValue(Math.max(0, output.getDamageValue() - repairAmount));

            event.setOutput(output);      // Set the resulting item
            event.setCost(2);             // XP cost
            event.setMaterialCost(1);     // Number of repair items used
        }
    }
}
