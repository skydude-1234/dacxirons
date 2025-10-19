package com.skydude.dacxirons.entity.spells.AcidBall;

import com.mojang.logging.LogUtils;
import com.skydude.dacxirons.registries.EntityRegistry;
import com.skydude.dacxirons.registries.dacxironsSpellRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.config.ServerConfigs;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.network.spell.ClientboundFieryExplosionParticles;
import io.redspace.ironsspellbooks.setup.Messages;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.mcreator.dungeonsandcombat.procedures.AcidFireBallProjectileHitsBlockProcedure;
import net.mcreator.dungeonsandcombat.procedures.AcidFireBallProjectileHitsLivingEntityProcedure;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AcidFireball extends AbstractMagicProjectile {


    private final Level worldRef;

    public AcidFireball(EntityType<? extends Projectile> pEntityType, Level level) {
        super(pEntityType, level);
        this.worldRef = level;
        this.setNoGravity(true);
    }

    public AcidFireball( LivingEntity pShooter, Level level) {
        this(EntityRegistry.ACID_BALL.get(), level);
        this.setOwner(pShooter);
    }

    @Override
    public void trailParticles() {
        Vec3 vec3 = getDeltaMovement();
        double d0 = this.getX() - vec3.x;
        double d1 = this.getY() - vec3.y;
        double d2 = this.getZ() - vec3.z;
        var count = Mth.clamp((int) (vec3.lengthSqr() * 4), 1, 4);
        for (int i = 0; i < count; i++) {
            Vec3 random = Utils.getRandomVec3(.25);
            var f = i / ((float) count);
            var x = Mth.lerp(f, d0, this.getX());
            var y = Mth.lerp(f, d1, this.getY());
            var z = Mth.lerp(f, d2, this.getZ());
            this.worldRef.addParticle(ParticleTypes.LARGE_SMOKE, x - random.x, y + 0.5D - random.y, z - random.z, random.x * .5f, random.y * .5f, random.z * .5f);
            this.worldRef.addParticle(ParticleHelper.POISON_CLOUD, x - random.x, y + 0.5D - random.y, z - random.z, random.x * .5f, random.y * .5f, random.z * .5f);
        }
    }



    @Override
    public void impactParticles(double x, double y, double z) {
    }

    @Override
    public float getSpeed() {
        return 1.15f;
    }

    @Override
    public Optional<SoundEvent> getImpactSound() {
        return Optional.of(SoundEvents.GENERIC_EXPLODE);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        if (!this.worldRef.isClientSide) {
            impactParticles(xOld, yOld, zOld);
            float explosionRadius = getExplosionRadius();
            var explosionRadiusSqr = explosionRadius * explosionRadius;
            var entities = worldRef.getEntities(this, this.getBoundingBox().inflate(explosionRadius));
            Vec3 losPoint = Utils.raycastForBlock(worldRef, this.position(), this.position().add(0, 2, 0), ClipContext.Fluid.NONE).getLocation();
            for (Entity entity : entities) {
                double distanceSqr = entity.distanceToSqr(hitResult.getLocation());
                if (distanceSqr < explosionRadiusSqr && canHitEntity(entity) && Utils.hasLineOfSight(worldRef, losPoint, entity.getBoundingBox().getCenter(), true)) {
                    double p = (1 - distanceSqr / explosionRadiusSqr);
                    float damage = (float) (this.damage * p);
                    DamageSources.applyDamage(entity, damage, dacxironsSpellRegistry.ACID_BALL_SPELL.get().getDamageSource(this, getOwner()));
                    if (entity instanceof LivingEntity){
                        AcidFireBallProjectileHitsLivingEntityProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(),entity);
                    }
                }
            }

//            if (ServerConfigs.SPELL_GREIFING.get()) {
//                Explosion explosion = new Explosion(level, null, SpellRegistry.FIREBALL_SPELL.get().getDamageSource(this, getOwner()), null, this.getX(), this.getY(), this.getZ(), this.getExplosionRadius() / 2, true, Explosion.BlockInteraction.DESTROY);
//                if (!net.minecraftforge.event.ForgeEventFactory.onExplosionStart(level, explosion)) {
//                    explosion.explode();
//                    explosion.finalizeExplosion(false);
//                }
//            }

          //  Messages.sendToPlayersTrackingEntity(new ClientboundFieryExplosionParticles(new Vec3(getX(), getY() + .15f, getZ()), getExplosionRadius()), this);
            playSound(SoundEvents.GENERIC_EXPLODE, 4.0F, (1.0F + (this.worldRef.random.nextFloat() - this.worldRef.random.nextFloat()) * 0.2F) * 0.7F);
            this.discard();
        }
    }


        @SubscribeEvent
        public static void onProjectileImpact(net.minecraftforge.event.entity.ProjectileImpactEvent event) {
            if (!(event.getEntity() instanceof com.skydude.dacxirons.entity.spells.AcidBall.AcidFireball proj)) return;
            if (!(event.getRayTraceResult() instanceof BlockHitResult hit)) return;

            var level = proj.level();
            System.out.println("[AcidFireball] ProjectileImpactEvent fired side=" + (level.isClientSide ? "CLIENT" : "SERVER")
                    + " pos=" + hit.getBlockPos());

            if (!level.isClientSide) {
                var loc = hit.getLocation();
                AcidFireBallProjectileHitsBlockProcedure.execute(level, loc.x, loc.y, loc.z);
            }
        }




}
