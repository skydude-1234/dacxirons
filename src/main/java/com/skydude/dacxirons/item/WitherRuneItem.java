package com.skydude.dacxirons.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class WitherRuneItem extends Item {
    public WitherRuneItem() {
        super((new Properties()).stacksTo(64).rarity(Rarity.COMMON));
    }
}
