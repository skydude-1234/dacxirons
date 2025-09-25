package com.skydude.dacxirons.mixin;

import com.skydude.dacxirons.item.armor.dacxironsArmorItem;
import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import net.mcreator.dungeonsandcombat.item.BloodymancerItem;
import net.mcreator.dungeonsandcombat.procedures.OathSwordRightclickedProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = BloodymancerItem.class, remap = false)
public abstract class BloodymancerMixin implements IPresetSpellContainer {
    @Unique private static final Logger LOG = LoggerFactory.getLogger("dacxirons");

//     Ensure the sword always has a spell container when right-clicked
//     Ensure the sword has a spell container when its tooltip is displayed



    // Implementation of IPresetSpellContainer
    @Override
    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }

        if (itemStack.getItem() instanceof BloodymancerItem armorItem)

            if (armorItem.getEquipmentSlot() == EquipmentSlot.CHEST
                // || armorItem.getEquipmentSlot() == EquipmentSlot.HEAD // Maybe I use in future????
            )
            {
                if (!ISpellContainer.isSpellContainer(itemStack)) {
                    var spellContainer = ISpellContainer.create(1, true, true);
                    spellContainer.save(itemStack);
                    //new
                    initializeSpellContainer(itemStack);
                }
            }

    }
}
