package com.skydude.dacxirons.registries;

import com.skydude.dacxirons.dacxirons;

import com.skydude.dacxirons.entity.mobs.SummonedKamath;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.entity.mobs.SummonedZombie;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, dacxirons.MOD_ID);
    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    public static final RegistryObject<EntityType<SummonedKamath>> SUMMONED_KAMATH =
            ENTITIES.register("summoned_kamath", () -> EntityType.Builder.of(SummonedKamath::new, MobCategory.MONSTER)
                    .sized(.6f, 1.8f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(dacxirons.MOD_ID, "summoned_kamath").toString()));




}
