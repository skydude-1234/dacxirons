package com.skydude.dacxirons.compat.omsunleia;

import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public final class SunleiaEvents {
    private static final ResourceLocation OLD_FRIENDS =
            ResourceLocation.fromNamespaceAndPath("dungeons_and_combat", "old_friends");

    private SunleiaEvents() {
    }

    public static boolean hasOldFriendsAdvancement(Player player) {
        if (!(player instanceof ServerPlayer serverPlayer)) {
            return false;
        }

        MinecraftServer server = serverPlayer.getServer();
        if (server == null) {
            return false;
        }

        Advancement oldFriends = server.getAdvancements().getAdvancement(OLD_FRIENDS);
        return oldFriends != null
                && serverPlayer.getAdvancements().getOrStartProgress(oldFriends).isDone();
    }
}
