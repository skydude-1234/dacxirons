//package com.skydude.dacxirons.mixin;
//
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin(targets = "net.mcreator.dungeonsandcombat.item.OathSwordItem$1")
//public abstract class OathSwordTierMixin {
//    @Inject(method = "getUses", at = @At("HEAD"), cancellable = true)
//
//    private void dacextras$replaceUses(CallbackInfoReturnable<Integer> cir) {
//
//        cir.setReturnValue(1200);
//    }
//}