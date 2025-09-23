package com.skydude.dacxirons.registries;

//import com.skydude.dacxirons.entity.mobs.SummonedKamath;
import com.skydude.dacxirons.dacxirons;
import com.skydude.dacxirons.entity.mobs.SummonedWeakness;
import com.skydude.dacxirons.entity.spells.EldritchSlash.EldritchSlash;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.entity.spells.flame_strike.FlameStrike;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
        import com.skydude.dacxirons.entity.mobs.SummonedKamath;

import static com.skydude.dacxirons.dacxirons.MOD_ID;

public class EntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MOD_ID);
    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

//    public static final RegistryObject<EntityType<SummonedKamath>> SUMMONED_KAMATH =
//            ENTITIES.register("summoned_kamath", () -> EntityType.Builder.of(SummonedKamath::new, MobCategory.MONSTER)
//                    .sized(.6f, 1.8f)
//                    .clientTrackingRange(64)
//                    .build(new ResourceLocation(dacxirons.MOD_ID, "summoned_kamath").toString()));
//    public static final RegistryObject<EntityType<SummonedWeakness>> SUMMONED_KAMATH =
//            ENTITIES.register("summoned_kamath",
//                    () -> EntityType.Builder.<SummonedWeakness>of(SummonedWeakness::new, MobCategory.MONSTER)
//                        .sized(0.6f, 1.95f)
//                        .build(new ResourceLocation(MOD_ID, "summoned_kamath").toString()));

    public static final RegistryObject<EntityType<SummonedWeakness>> SUMMONED_WEAKNESS =
            ENTITIES.register("summoned_weakness", () -> EntityType.Builder.<SummonedWeakness>of(SummonedWeakness::new, MobCategory.MONSTER)
                    .sized(.6f, 1.8f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(MOD_ID, "summoned_weakness").toString()));

    public static final RegistryObject<EntityType<SummonedKamath>> SUMMONED_KAMATH =
            ENTITIES.register("summoned_kamath", () -> EntityType.Builder.<SummonedKamath>of(SummonedKamath::new, MobCategory.MONSTER)
                    .sized(.6f, 1.8f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(MOD_ID, "summoned_kamath").toString()));

    public static final RegistryObject<EntityType<EldritchSlash>> ELDRITCH_SLASH =
            ENTITIES.register("eldritch_slash", () -> EntityType.Builder.<EldritchSlash>of(EldritchSlash::new, MobCategory.MISC)
                    .sized(5f, 1f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(IronsSpellbooks.MODID, "eldritch_slash").toString()));



}
