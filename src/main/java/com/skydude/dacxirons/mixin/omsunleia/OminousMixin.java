package com.skydude.dacxirons.mixin.omsunleia;

import com.skydude.dacxirons.compat.omsunleia.SunleiaEvents;
import io.redspace.ironsspellbooks.player.ServerPlayerEvents;
import net.mcreator.dungeonsandcombat.entity.SunleiaEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ServerPlayerEvents.class, remap = false)
public abstract class OminousMixin {

    @Redirect(
            method = "handleOminousEntities",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;hasEffect(Lnet/minecraft/world/effect/MobEffect;)Z",
                    remap = true
            ),
            require = 2,
            remap = false
    )
    private static boolean dacxirons$requireOldFriendsForSunleia(Player player, MobEffect effect, EntityJoinLevelEvent event) {
        if (!player.hasEffect(effect)) {
            return false;
        }

        return !(event.getEntity() instanceof SunleiaEntity) || SunleiaEvents.hasOldFriendsAdvancement(player);
    }
}
