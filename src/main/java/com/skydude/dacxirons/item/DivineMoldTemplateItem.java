package com.skydude.dacxirons.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class DivineMoldTemplateItem extends Item {
    public DivineMoldTemplateItem() {
        super((new Item.Properties()).stacksTo(64).rarity(Rarity.RARE));
    }

    public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.literal("§7Holy Spell Upgrade"));
//        list.add(Component.literal(""));
//        list.add(Component.literal("§7Applies to:"));
//        list.add(Component.literal(" §9Sandstone Equipment"));
//        list.add(Component.literal("§7Ingredients:"));
//        list.add(Component.literal(" §9Blessed Gold Ingots"));
    }
}