package com.skydude.dacxirons.mixin.omsunleia;

import io.redspace.ironsspellbooks.api.entity.IOminousEntity;
import net.mcreator.dungeonsandcombat.entity.SunleiaEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(value = SunleiaEntity.class, remap = false)
public abstract class SunleiaMixin extends Monster implements IOminousEntity {

    protected SunleiaMixin(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Unique
    private static final String dacxirons$OMINOUS_NBT_KEY = "dacxirons_ominous";

    @Unique
    private static final AttributeModifier dacxirons$OMINOUS_DAMAGE_MODIFIER = new AttributeModifier(
            UUID.fromString("2f78eb06-ae3b-4d57-823f-f2b03a3300ed"),
            "sunleia_ominous_damage",
            100,
            AttributeModifier.Operation.ADDITION
    );

    @Unique
    private static final EntityDataAccessor<Boolean> dacxirons$OMINOUS =
            SynchedEntityData.defineId(
                    SunleiaMixin.class,
                    EntityDataSerializers.BOOLEAN
            );

    @Inject(method = "defineSynchedData", at = @At("TAIL"), remap = true)
    private void dacxirons$defineSynchedData(CallbackInfo ci) {
        getEntityData().define(dacxirons$OMINOUS, false);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"), remap = true)
    private void dacxirons$saveOminousState(CompoundTag tag, CallbackInfo ci) {
        tag.putBoolean(dacxirons$OMINOUS_NBT_KEY, isOminous());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"), remap = true)
    private void dacxirons$loadOminousState(CompoundTag tag, CallbackInfo ci) {
        dacxirons$setOminous(tag.getBoolean(dacxirons$OMINOUS_NBT_KEY));
    }

    @Override
    public void onOminousTrigger() {
        dacxirons$setOminous(true);
    }

    @Unique
    private void dacxirons$setOminous(boolean ominous) {
        getEntityData().set(dacxirons$OMINOUS, ominous);

        AttributeInstance attackDamage = getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackDamage == null) {
            return;
        }

        AttributeModifier currentModifier =
                attackDamage.getModifier(dacxirons$OMINOUS_DAMAGE_MODIFIER.getId());
        if (ominous && currentModifier == null) {
            attackDamage.addPermanentModifier(dacxirons$OMINOUS_DAMAGE_MODIFIER);
        } else if (!ominous && currentModifier != null) {
            attackDamage.removeModifier(dacxirons$OMINOUS_DAMAGE_MODIFIER.getId());
        }
    }

    @Override
    public boolean isOminous() {
        return getEntityData().get(dacxirons$OMINOUS);
    }

    @Override
    public float ominousTriggerRange() {
        return 24.0F;
    }

}
