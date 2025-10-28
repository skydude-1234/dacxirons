package com.skydude.dacxirons.item.armor;




import com.skydude.dacxirons.client.model.blessed_priest_model;
import com.skydude.dacxirons.client.model.ebony_spell_model;
import com.skydude.dacxirons.registries.EffectRegistry;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModItems;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModParticleTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import static com.skydude.dacxirons.registries.EffectRegistry.CAST_SPEED;
import static com.skydude.dacxirons.registries.EffectRegistry.SPELL_STRENGTH;
import static net.minecraft.world.effect.MobEffects.DAMAGE_RESISTANCE;
import static net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED;
@Mod.EventBusSubscriber
public class BlessedPriestArmorItem extends ImbueabledacxironsArmor {
    private static final UUID BLESSED_PRIEST_DAY_RESIST_UUID =
            UUID.fromString("8b0de1f9-4df6-4f2f-8741-47a8f2d5c0ad");
    // public static boolean fullebonymagic;
    public BlessedPriestArmorItem(Type type, Properties properties) {
        super(dacxironsArmorMaterials.BLESSED_PRIEST_ARMOR, type, properties.rarity(Rarity.RARE));


    }
    //tooltip
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("fullsetbonus"));
        tooltip.add(Component.translatable("tooltip.dacxirons.blessedprestfullset"));
        tooltip.add(Component.translatable("tooltip.dacxirons.blessedfullset").withStyle(style -> style.withItalic(true)).withStyle(ChatFormatting.YELLOW));
        //cast speed
        //cooldown reduction
        //protection against blood and eldritch magic
    }


    @SuppressWarnings("removal")
    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (level.isClientSide) return; // do attribute edits on server only

        boolean fullSet = CorrectArmor.hasFullSetOn(player, dacxironsArmorMaterials.BLESSED_PRIEST_ARMOR, 4);
        boolean day = level.isDay();

        // --- Cast Speed effect refresh (optional, see note below) ---
        // If you meant *20 seconds*, use 400 ticks, not 10.
        final int DURATION_TICKS = 400;          // 20s
        final int REFRESH_THRESHOLD = 40;        // reapply when <2s left
        if (fullSet && day) {
            MobEffectInstance castspeed = player.getEffect(CAST_SPEED.get());
            if (castspeed == null || castspeed.getDuration() <= REFRESH_THRESHOLD) {
                player.addEffect(new MobEffectInstance(
                        CAST_SPEED.get(),
                        DURATION_TICKS,
                        1,      // amplifier  0 = 1, 1 = 2
                        true,   // ambient
                        false,  // show particles
                        true    // show icon
                ));
            }
        } else {
            // optional: clear effect when bonus stops
            player.removeEffect(CAST_SPEED.get());
        }

        // --- Eldritch resist attribute bonus while full set + day ---
        var eldritch_resist_attr = player.getAttribute(AttributeRegistry.ELDRITCH_MAGIC_RESIST.get());
        if (eldritch_resist_attr == null) return; // attribute might be absent on some entities

        if (fullSet && day) {
            // Only add if not present
            if (eldritch_resist_attr.getModifier(BLESSED_PRIEST_DAY_RESIST_UUID) == null) {
                AttributeModifier eldritchresist = new AttributeModifier(
                        BLESSED_PRIEST_DAY_RESIST_UUID,
                        "Blessed Priest day resist",
                        1.0D,
                        AttributeModifier.Operation.MULTIPLY_BASE
                );
                // If your mappings have this helper, prefer it:
                // attr.addOrReplaceTransientModifier(mod);
                eldritch_resist_attr.addTransientModifier(eldritchresist);
            }
        } else {
            // Remove when not applicable so it doesn't stick
            eldritch_resist_attr.removeModifier(BLESSED_PRIEST_DAY_RESIST_UUID);
        }

        // blood resist
        var blood_resist_attr = player.getAttribute(AttributeRegistry.ELDRITCH_MAGIC_RESIST.get());
        if (blood_resist_attr == null) return; // attribute might be absent on some entities

        if (fullSet && day) {
            // Only add if not present
            if (blood_resist_attr.getModifier(BLESSED_PRIEST_DAY_RESIST_UUID) == null) {
                AttributeModifier bloodresist = new AttributeModifier(
                        BLESSED_PRIEST_DAY_RESIST_UUID,
                        "Blessed Priest day resist",
                        1.0D,
                        AttributeModifier.Operation.MULTIPLY_BASE
                );
                // If your mappings have this helper, prefer it:
                // attr.addOrReplaceTransientModifier(mod);
                blood_resist_attr.addTransientModifier(bloodresist);
            }
        } else {
            // Remove when not applicable so it doesn't stick
            blood_resist_attr.removeModifier(BLESSED_PRIEST_DAY_RESIST_UUID);
        }
    }

    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingTickEvent event) {
        execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
    }

    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity != null) {
            if (CorrectArmor.hasFullSetOn((LivingEntity) entity, dacxironsArmorMaterials.BLESSED_PRIEST_ARMOR, 4)) {
                entity.getPersistentData().putDouble("fuego", entity.getPersistentData().getDouble("fuego") + (double) 1.0F);
                if (entity.getPersistentData().getDouble("fuego") == (double) 20.0F) {
                    world.addParticle((SimpleParticleType) DungeonsAndCombatModParticleTypes.BLESSED_SPARKLE.get(), x + 0.4, y + (double) 1.0F, z + 0.4, (double) 0.0F, 0.08, (double) 0.0F);
                }

                if (entity.getPersistentData().getDouble("fuego") == (double) 5.0F) {
                    world.addParticle((SimpleParticleType) DungeonsAndCombatModParticleTypes.BLESSED_SPARKLE.get(), x - 0.4, y + (double) 0.5F, z + 0.4, (double) 0.0F, 0.1, (double) 0.0F);
                }

                if (entity.getPersistentData().getDouble("fuego") == (double) 10.0F) {
                    world.addParticle((SimpleParticleType) DungeonsAndCombatModParticleTypes.BLESSED_SPARKLE.get(), x - 0.4, y + 1.3, z - 0.4, (double) 0.0F, 0.07, (double) 0.0F);
                }

                if (entity.getPersistentData().getDouble("fuego") == (double) 25.0F) {
                    world.addParticle((SimpleParticleType) DungeonsAndCombatModParticleTypes.BLESSED_SPARKLE.get(), x + 0.4, y + 0.8, z - 0.4, (double) 0.0F, 0.1, (double) 0.0F);
                    entity.getPersistentData().putDouble("fuego", (double) 0.0F);
                }
            }
        }
    }
    public static class Helmet extends BlessedPriestArmorItem {
        public Helmet() {
            super(Type.HELMET, (new Properties()).fireResistant());
        }

        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("head", (new blessed_priest_model(Minecraft.getInstance().getEntityModels().bakeLayer(blessed_priest_model.LAYER_LOCATION))).head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                    armorModel.crouching = living.isShiftKeyDown();
                    armorModel.riding = defaultModel.riding;
                    armorModel.young = living.isBaby();
                    return armorModel;
                }
            });
        }


        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "dacxirons:textures/entities/blessed_priest_armor.png";
        }
    }
    public static class Chestplate extends BlessedPriestArmorItem {
        public Chestplate() {
            super(Type.CHESTPLATE, (new Properties()).fireResistant());
        }

        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                @OnlyIn(Dist.CLIENT)
                public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("body", (new blessed_priest_model(Minecraft.getInstance().getEntityModels().bakeLayer(blessed_priest_model.LAYER_LOCATION))).body, "left_arm", (new blessed_priest_model(Minecraft.getInstance().getEntityModels().bakeLayer(blessed_priest_model.LAYER_LOCATION))).left_arm, "right_arm", (new blessed_priest_model(Minecraft.getInstance().getEntityModels().bakeLayer(blessed_priest_model.LAYER_LOCATION))).right_arm, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                    armorModel.crouching = living.isShiftKeyDown();
                    armorModel.riding = defaultModel.riding;
                    armorModel.young = living.isBaby();
                    return armorModel;
                }
            });
        }


        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "dacxirons:textures/entities/blessed_priest_armor.png";
        }
    }
    public static class Leggings extends BlessedPriestArmorItem {
        public Leggings() {
            super(Type.LEGGINGS, (new Properties()).fireResistant());
        }

        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                @OnlyIn(Dist.CLIENT)
                public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("left_leg", (new blessed_priest_model(Minecraft.getInstance().getEntityModels().bakeLayer(blessed_priest_model.LAYER_LOCATION))).left_leg, "right_leg", (new blessed_priest_model(Minecraft.getInstance().getEntityModels().bakeLayer(blessed_priest_model.LAYER_LOCATION))).right_leg, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                    armorModel.crouching = living.isShiftKeyDown();
                    armorModel.riding = defaultModel.riding;
                    armorModel.young = living.isBaby();
                    return armorModel;
                }
            });
        }



        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "dacxirons:textures/entities/blessed_priest_armor.png";
        }
    }
    public static class Boots extends BlessedPriestArmorItem {
        public Boots() {
            super(Type.BOOTS, (new Properties()).fireResistant());
        }

        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                @OnlyIn(Dist.CLIENT)
                public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("left_leg", (new blessed_priest_model(Minecraft.getInstance().getEntityModels().bakeLayer(blessed_priest_model.LAYER_LOCATION))).left_shoe, "right_leg", (new blessed_priest_model(Minecraft.getInstance().getEntityModels().bakeLayer(blessed_priest_model.LAYER_LOCATION))).right_shoe, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                    armorModel.crouching = living.isShiftKeyDown();
                    armorModel.riding = defaultModel.riding;
                    armorModel.young = living.isBaby();
                    return armorModel;
                }
            });
        }

//        public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
//            super.appendHoverText(itemstack, level, list, flag);
//            list.add(Component.literal("§7Bonus Armor set:"));
// }

        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "dacxirons:textures/entities/blessed_priest_armor.png";
        }
    }



}
