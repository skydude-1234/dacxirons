//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.skydude.dacxirons.effect;

import javax.annotation.Nullable;

import com.skydude.dacxirons.registries.EffectRegistry;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.spells.fire.FireballSpell;
import io.redspace.ironsspellbooks.spells.fire.HeatSurgeSpell;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModMobEffects;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class SpellBurnProcedure {

    @SubscribeEvent
    public static void onLivingAttacked(LivingAttackEvent event) {
        if (event != null && event.getEntity() != null) {
            if(event.getEntity().hasEffect(EffectRegistry.SPELL_BURNING_AURA.get())) {

                assert event.getSource().getEntity() != null;
                assert event.getSource().getDirectEntity() != null;

                execute(event.getEntity(), event.getEntity().getLastAttacker());
            }
        }

    }

    public class CommandUtils {
        public static void runCommandAtPlayer(ServerPlayer player, String command) {
            MinecraftServer server = player.getServer();
            if (server == null) return;

            // Create a source stack at the player’s position, but still with server (OP) permissions
            CommandSourceStack source = server.createCommandSourceStack()
                    .withPosition(player.position())   // runs at player’s coordinates
                    .withRotation(player.getRotationVector()) // optional: face the same direction as player
                    .withEntity(player); // so selectors like @s can point to the player

            // Execute the command as if typed in console, at that location
            server.getCommands().performPrefixedCommand(source, command);
        }
    }



    private static void execute(Entity entity, Entity sourceentity ) {
        if (entity instanceof ServerPlayer living && sourceentity != null) {
            if (living.hasEffect( EffectRegistry.SPELL_BURNING_AURA.get())) {
                    sourceentity.setSecondsOnFire(10);
                  //  CommandUtils.runCommandAtPlayer((ServerPlayer) entity, "cast " + sourceentity.getStringUUID() + " fireball 1");
                   // CommandUtils.runCommandAtPlayer((ServerPlayer) entity, "cast @s dacxirons:heat_surge 3");
                    AbstractSpell spell = new HeatSurgeSpell();
                    spell.castSpell(living.level(), 4, living, MagicData.getPlayerMagicData(living).getCastSource(), false);
                }


        }
    }
}
