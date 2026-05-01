package com.skydude.dacxirons.mixin;


import net.mcreator.dungeonsandcombat.procedures.LaserBeamProjectileHitsLivingEntityProcedure;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LaserBeamProjectileHitsLivingEntityProcedure.class, remap = false)
public abstract class LasarBeamFix   {
  @Inject(method = "execute", at = @At("HEAD"), cancellable = true)
    private static void execute(Entity immediatesourceentity, Entity sourceentity, CallbackInfo ci) {
        ci.cancel();
        if (immediatesourceentity != null) {
            immediatesourceentity.setSecondsOnFire(15);
            if (!immediatesourceentity.level().isClientSide()) {
                immediatesourceentity.discard();
            }

        }
    }
}
