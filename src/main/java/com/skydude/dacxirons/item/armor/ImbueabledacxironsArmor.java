package com.skydude.dacxirons.item.armor;



import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ItemAttributeModifierEvent;

public abstract class ImbueabledacxironsArmor extends dacxironsArmorItem implements IPresetSpellContainer {
    public ImbueabledacxironsArmor(dacxironsArmorMaterials materialIn, ArmorItem.Type type, Properties settings) {
        super(materialIn, type, settings);
    }

    @Override
    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }

        if (itemStack.getItem() instanceof dacxironsArmorItem armorItem)
        {
            if (armorItem.getEquipmentSlot() == EquipmentSlot.CHEST
                   // || armorItem.getEquipmentSlot() == EquipmentSlot.HEAD // Maybe I use in future????
            )
            {
                if (!ISpellContainer.isSpellContainer(itemStack)) {
                    var spellContainer = ISpellContainer.create(1, true, true);
                    spellContainer.save(itemStack);
                }
            }
        }
    }


}

