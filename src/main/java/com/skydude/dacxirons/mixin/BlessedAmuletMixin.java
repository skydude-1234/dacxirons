package com.skydude.dacxirons.mixin;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModMobEffects;
import net.mcreator.dungeonsandcombat.item.BloodymancerItem;
import net.mcreator.dungeonsandcombat.procedures.BlessedAmuletWhileBaubleIsEquippedTickProcedure;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BlessedAmuletWhileBaubleIsEquippedTickProcedure.class, remap = false)
public abstract class BlessedAmuletMixin implements IPresetSpellContainer {
    @Unique private static final Logger LOG = LoggerFactory.getLogger("dacxirons");
    @Inject(method = "execute", at = @At("HEAD"), cancellable = true)
    private static void execute(Entity entity, CallbackInfo ci) {

        if (entity instanceof LivingEntity) {
            LivingEntity _entity = (LivingEntity)entity;

            _entity.removeEffect(MobEffectRegistry.BLIGHT.get());
            _entity.removeEffect(MobEffectRegistry.REND.get());
            _entity.removeEffect(MobEffectRegistry.GUIDING_BOLT.get());
            _entity.removeEffect(MobEffectRegistry.AIRBORNE.get());
            _entity.removeEffect(MobEffectRegistry.SLOWED.get());
            _entity.removeEffect(MobEffectRegistry.CHILLED.get());
            _entity.removeEffect(MobEffectRegistry.BLIGHT.get());
            _entity.removeEffect(MobEffectRegistry.BLIGHT.get());
            _entity.removeEffect(MobEffectRegistry.BLIGHT.get());



            _entity.removeEffect((MobEffect) DungeonsAndCombatModMobEffects.FATAL_OATH.get());
        }
    }

//     Ensure the sword always has a spell container when right-clicked
//     Ensure the sword has a spell container when its tooltip is displayed



    // Implementation of IPresetSpellContainer

}
