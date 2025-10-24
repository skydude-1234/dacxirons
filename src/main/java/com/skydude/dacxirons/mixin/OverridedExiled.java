package com.skydude.dacxirons.mixin;

import com.skydude.dacxirons.registries.ItemRegistries;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModItems;
import net.mcreator.dungeonsandcombat.procedures.ExiledChoosedProcedure;
import net.mcreator.dungeonsandcombat.procedures.RogueChoosedProcedure;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(value = ExiledChoosedProcedure.class, remap = false)
public abstract class OverridedExiled {
    @Inject(method = "execute", at = @At("TAIL"), cancellable = true)
    //  @Inject(method = "execute(Lnet/minecraft/world/entity/Entity;)V", at = @At("HEAD"), cancellable = true)

    private static void overrideExecute(Entity entity, CallbackInfo ci ) {


        if (!(entity instanceof Player player)) {return;}


        player.setItemInHand(InteractionHand.MAIN_HAND, (new ItemStack(ItemRegistries.SCEPTER_COMPENSATION_STAFF.get())));

//        if(player.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty()) {
//            player.setItemInHand(InteractionHand.OFF_HAND, (new ItemStack((ItemLike) DungeonsAndCombatModItems.DAGGER.get())).copy());
//        }

        ci.cancel(); // cancel original method
    }



}
