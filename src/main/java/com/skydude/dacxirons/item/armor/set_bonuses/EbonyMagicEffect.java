package com.skydude.dacxirons.item.armor.set_bonuses;

import com.skydude.dacxirons.item.armor.CorrectArmor;
import com.skydude.dacxirons.item.armor.EbonyMagicSpellArmorItem;
import com.skydude.dacxirons.item.armor.dacxironsArmorMaterials;
import com.skydude.dacxirons.registries.EffectRegistry;
import com.skydude.dacxirons.registries.ItemRegistries;
import io.redspace.ironsspellbooks.api.events.SpellDamageEvent;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;



@Mod.EventBusSubscriber // handlers must be static
public class EbonyMagicEffect {

    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingTickEvent event) {
        execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());

    }

    @SubscribeEvent
    public static void onSpellAttack(SpellDamageEvent event) {
        if(EbonyMagicSpellArmorItem.fullebonymagic) {
            LivingEntity target = event.getEntity();


            // test with something visible first
            MobEffectInstance effect = new MobEffectInstance(
                    MobEffects.GLOWING, // swap to MobEffects.WITHER after confirming
                    200,                // duration (ticks)
                    0,                  // amplifier
                    false,              // ambient
                    true                // show particles
            );



            // ensure main server thread

                target.addEffect(effect);

        } else { return; }
    }
    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity != null) {


            if (entity instanceof LivingEntity) {
                if (CorrectArmor.hasFullSetOn((LivingEntity) entity, dacxironsArmorMaterials.EBONY_SPELL_ARMOR, 4)) {


                    entity.getPersistentData().putDouble("fuego", entity.getPersistentData().getDouble("fuego") + (double)1.0F);
                    if (entity.getPersistentData().getDouble("fuego") == (double)20.0F) {
                        world.addParticle(ParticleTypes.ASH, x + 0.4, y + (double)1.0F, z + 0.4, (double)0.0F, 0.08, (double)0.0F);
                        world.addParticle(ParticleTypes.ASH, x - 0.4, y + (double)1.0F, z + 0.4, (double)0.0F, 0.08, (double)0.0F);
                        world.addParticle(ParticleTypes.SMOKE, x + (double)0.0F, y + (double)0.0F, z + (double)0.0F, (double)0.0F, 0.08, (double)0.0F);
                    }

                    if (entity.getPersistentData().getDouble("fuego") == (double)5.0F) {
                        world.addParticle(ParticleTypes.ASH, x - 0.4, y + (double)0.5F, z + 0.4, (double)0.0F, 0.1, (double)0.0F);
                        world.addParticle(ParticleTypes.ASH, x + 0.4, y + (double)0.5F, z - 0.4, (double)0.0F, 0.1, (double)0.0F);
                    }

                    if (entity.getPersistentData().getDouble("fuego") == (double)10.0F) {
                        world.addParticle(ParticleTypes.ASH, x - 0.4, y + 1.3, z - 0.4, (double)0.0F, 0.07, (double)0.0F);
                        world.addParticle(ParticleTypes.SMOKE, x + (double)0.0F, y + (double)0.0F, z + (double)0.0F, (double)0.0F, 0.08, (double)0.0F);
                    }

                    if (entity.getPersistentData().getDouble("fuego") == (double)25.0F) {
                        world.addParticle(ParticleTypes.ASH, x + 0.4, y + 0.8, z - 0.4, (double)0.0F, 0.1, (double)0.0F);
                        world.addParticle(ParticleTypes.ASH, x - 0.3, y + 0.8, z + 0.4, (double)0.0F, 0.1, (double)0.0F);
                        entity.getPersistentData().putDouble("fuego", (double)0.0F);
                    }
                }
            }



        }
    }
}

