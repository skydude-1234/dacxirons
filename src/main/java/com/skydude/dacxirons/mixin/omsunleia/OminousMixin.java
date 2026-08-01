package com.skydude.dacxirons.mixin.omsunleia;

import com.skydude.dacxirons.compat.omsunleia.SunleiaEvents;
import io.redspace.ironsspellbooks.api.entity.IOminousEntity;
import io.redspace.ironsspellbooks.player.ServerPlayerEvents;
import net.mcreator.dungeonsandcombat.entity.SunleiaEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(value = ServerPlayerEvents.class, remap = false)
public abstract class OminousMixin {

    @Inject(
            method = "handleOminousEntities",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;isEmpty()Z"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false
    )
    private static void dacxirons$requireOldFriendsForSunleia(EntityJoinLevelEvent event, CallbackInfo ci, ServerLevel serverLevel, Entity entity, IOminousEntity ominousSettings, float rangeSqr, Vec3 center, List<Player> ominousPlayers) {
        if (entity instanceof SunleiaEntity) {
            ominousPlayers.removeIf(player -> !SunleiaEvents.hasOldFriendsAdvancement(player));
        }
    }
}
