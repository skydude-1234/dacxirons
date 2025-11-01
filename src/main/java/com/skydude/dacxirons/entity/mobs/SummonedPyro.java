package com.skydude.dacxirons.entity.mobs;

import com.skydude.dacxirons.dacxirons;
import com.skydude.dacxirons.registries.EntityRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.entity.mobs.MagicSummon;
import io.redspace.ironsspellbooks.entity.mobs.goals.*;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.util.OwnerHelper;
import net.mcreator.dungeonsandcombat.entity.KamathEntity;
import net.mcreator.dungeonsandcombat.entity.PyroKnightEntity;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModAttributes;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.UUID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = dacxirons.MOD_ID)
public class SummonedPyro extends PyroKnightEntity implements MagicSummon, GeoAnimatable {

    private static final EntityDataAccessor<Boolean> DATA_IS_ANIMATING_RISE = SynchedEntityData.defineId(SummonedPyro.class, EntityDataSerializers.BOOLEAN);
    protected LivingEntity cachedSummoner;
    protected UUID summonerUUID;
    private int riseAnimTime = 80;

    public SummonedPyro(EntityType<SummonedPyro> type, Level world) {
        super((EntityType) type, world);
        xpReward = 0;

    }
    // Create attributes, inherit Pyro Knight
    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        // start from mcreator Pyro Knight’s builder
        AttributeSupplier.Builder builder = PyroKnightEntity.createAttributes();

        // add the custom attribute that the mcreator pyro knight procedure expects
        builder.add(DungeonsAndCombatModAttributes.ACTIONSTATE.get(), 0.0D);


        // Add to summoned pyro
        event.put(EntityRegistry.SUMMONED_PYRO.get(), builder.build());

    }
    public SummonedPyro(LivingEntity owner, boolean playRiseAnimation) {
        this(EntityRegistry.SUMMONED_PYRO.get(), owner.level());
        setSummoner(owner);
        if (playRiseAnimation)
            triggerRiseAnimation();

    }


    //
    //  goals
    //
    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2f, true));
        this.goalSelector.addGoal(7, new GenericFollowOwnerGoal(this, this::getSummoner, 0.9f, 15, 5, false, 25));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));

        this.targetSelector.addGoal(1, new GenericOwnerHurtByTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(2, new GenericOwnerHurtTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(3, new GenericCopyOwnerTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(4, (new GenericHurtByTargetGoal(this, (entity) -> entity == getSummoner())).setAlertOthers());

    }

//summoner

    @Override
    public LivingEntity getSummoner() {
        return OwnerHelper.getAndCacheOwner(level(), cachedSummoner, summonerUUID);
    }

    @Override
    public void onUnSummon() {
        if (!level().isClientSide) {
            MagicManager.spawnParticles(level(), ParticleTypes.POOF, getX(), getY(), getZ(), 25, .4, .8, .4, .03, false);
            discard();
        }
    }
    public void setSummoner(@Nullable LivingEntity owner) {
        if (owner != null) {
            this.summonerUUID = owner.getUUID();
            this.cachedSummoner = owner;
        }
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        RandomSource randomsource = Utils.random;
        this.populateDefaultEquipmentSlots(randomsource, pDifficulty);


        return pSpawnData;
    }
    @Override
    public boolean isAlliedTo(Entity pEntity) {
        return super.isAlliedTo(pEntity) || this.isAlliedHelper(pEntity);
    }

    //
    // more rules
    //

    @Override
    protected boolean shouldDespawnInPeaceful() {
    return false;
}

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.summonerUUID = OwnerHelper.deserializeOwner(compoundTag);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        OwnerHelper.serializeOwner(compoundTag, summonerUUID);
    }
    @Override
    public void remove(RemovalReason pReason) {
        // IronsSpellbooks.LOGGER.debug("Summoned Zombie: Attempt remove for: {}",pReason.toString());
        super.remove(pReason);
    }
    @Override
    public void onRemovedFromWorld() {
        //IronsSpellbooks.LOGGER.debug("Summoned Zombie: Removed from world, {}", this.getRemovalReason());
        super.onRemovedFromWorld();
        this.onRemovedHelper(this, MobEffectRegistry.RAISE_DEAD_TIMER.get());
    }
    @Override
    public void die(DamageSource pDamageSource) {
        this.onDeathHelper();
        //this.onRemovedHelper(this, MobEffectRegistry.RAISE_DEAD_TIMER.get());
        super.die(pDamageSource);
    }

    //
    //  Rise Animation Stuff
    //
    //

    protected void clientDiggingParticles(LivingEntity livingEntity) {
        RandomSource randomsource = livingEntity.getRandom();
        BlockState blockstate = livingEntity.getBlockStateOn();
        if (blockstate.getRenderShape() != RenderShape.INVISIBLE) {
            for (int i = 0; i < 15; ++i) {
                double d0 = livingEntity.getX() + (double) Mth.randomBetween(randomsource, -0.5F, 0.5F);
                double d1 = livingEntity.getY();
                double d2 = livingEntity.getZ() + (double) Mth.randomBetween(randomsource, -0.5F, 0.5F);
                livingEntity.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockstate), d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    public boolean isAnimatingRise() {
        return entityData.get(DATA_IS_ANIMATING_RISE);
    }

    public void triggerRiseAnimation() {
        entityData.set(DATA_IS_ANIMATING_RISE, true);
    }

    @Override
    public boolean isPushable() {
        return super.isPushable() && !isAnimatingRise();
    }

    @Override
    protected boolean isImmobile() {
        return super.isImmobile() || isAnimatingRise();
    }

    @Override
    public void tick() {
        if (isAnimatingRise()) {
            if (level().isClientSide)
                clientDiggingParticles(this);
            if (--riseAnimTime < 0) {
                entityData.set(DATA_IS_ANIMATING_RISE, false);
                //they do a weird head flick thing
                this.setXRot(0);
                this.setOldPosAndRot();
            }
        } else {
            super.tick();
        }
    }
    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (!pSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY) && (isAnimatingRise() || shouldIgnoreDamage(pSource))) {
            return false;
        }
        return super.hurt(pSource, pAmount);
    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_IS_ANIMATING_RISE, false);
    }
 /*
    Geckolib
     */

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        //inherit anims
        super.registerControllers(controllerRegistrar);
        //rise anim
     //   controllerRegistrar.add(new AnimationController(this, "rise", 0, this::risePredicate));
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public double getTick(Object o) {
        return this.tickCount;
    }


}
