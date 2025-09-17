package com.skydude.dacxirons.item.armor;




import com.skydude.dacxirons.client.model.blazymancer_model;
import com.skydude.dacxirons.client.model.crimson_spell_model;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModMobEffects;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModParticleTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.skydude.dacxirons.registries.EffectRegistry.CAST_SPEED;
import static com.skydude.dacxirons.registries.EffectRegistry.SPELL_STRENGTH;
import static net.minecraft.world.effect.MobEffects.DAMAGE_RESISTANCE;
import static net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED;

public class BlazymancerSpellArmorItem extends ImbueabledacxironsArmor {

    public static boolean blazymancerfull = false;
    public BlazymancerSpellArmorItem(ArmorItem.Type type, Properties properties) {
        super(dacxironsArmorMaterials.BLAZYMANCER_ARMOR, type, properties);


    }
    //tooltip
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.dacxirons.crimson_wizard").withStyle(style -> style.withItalic(true)).withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable("tooltip.dacxirons.crimson_wizard2").withStyle(style -> style.withItalic(true)).withStyle(ChatFormatting.RED));

    }


    @SuppressWarnings("removal")
    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        // if (event.phase == TickEvent.Phase.END) {
        //  Player player = event.player;

        if (CorrectArmor.hasFullSetOn(player, dacxironsArmorMaterials.CRIMSON_SPELL_ARMOR, 4)) {
            blazymancerfull = true;
        } else{
            System.out.println("bomb");
        }

        //    AttributeInstance swimSpeed = player.getAttribute(ForgeMod.SWIM_SPEED.get());


    }
    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingTickEvent event) {
        blazymanceraura(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
    }
    private static void blazymanceraura(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
        if (blazymancerfull);
        {
            if (entity instanceof LivingEntity) {
                LivingEntity _entity = (LivingEntity)entity;
                if (!_entity.level().isClientSide()) {
                    _entity.addEffect(new MobEffectInstance((MobEffect)DungeonsAndCombatModMobEffects.BURNING_AURA.get(), 60, 0, false, false));
                }
            }

            entity.getPersistentData().putDouble("fuego", entity.getPersistentData().getDouble("fuego") + (double)1.0F);
            if (entity.getPersistentData().getDouble("fuego") == (double)20.0F) {
                world.addParticle((SimpleParticleType)DungeonsAndCombatModParticleTypes.ACID_FLAME.get(), x + 0.4, y + (double)1.0F, z + 0.4, (double)0.0F, 0.08, (double)0.0F);
                world.addParticle((SimpleParticleType)DungeonsAndCombatModParticleTypes.ACID_FLAME.get(), x - 0.4, y + (double)1.0F, z + 0.4, (double)0.0F, 0.08, (double)0.0F);
                world.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, x + (double)0.0F, y + (double)0.0F, z + (double)0.0F, (double)0.0F, 0.08, (double)0.0F);
            }

            if (entity.getPersistentData().getDouble("fuego") == (double)5.0F) {
                world.addParticle((SimpleParticleType)DungeonsAndCombatModParticleTypes.ACID_FLAME.get(), x - 0.4, y + (double)0.5F, z + 0.4, (double)0.0F, 0.1, (double)0.0F);
                world.addParticle((SimpleParticleType)DungeonsAndCombatModParticleTypes.ACID_FLAME.get(), x + 0.4, y + (double)0.5F, z - 0.4, (double)0.0F, 0.1, (double)0.0F);
            }

            if (entity.getPersistentData().getDouble("fuego") == (double)10.0F) {
                world.addParticle((SimpleParticleType)DungeonsAndCombatModParticleTypes.ACID_FLAME.get(), x - 0.4, y + 1.3, z - 0.4, (double)0.0F, 0.07, (double)0.0F);
                world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, x + (double)0.0F, y + (double)0.0F, z + (double)0.0F, (double)0.0F, 0.08, (double)0.0F);
            }

            if (entity.getPersistentData().getDouble("fuego") == (double)25.0F) {
                world.addParticle((SimpleParticleType)DungeonsAndCombatModParticleTypes.ACID_FLAME.get(), x + 0.4, y + 0.8, z - 0.4, (double)0.0F, 0.1, (double)0.0F);
                world.addParticle((SimpleParticleType)DungeonsAndCombatModParticleTypes.ACID_FLAME.get(), x - 0.3, y + 0.8, z + 0.4, (double)0.0F, 0.1, (double)0.0F);
                entity.getPersistentData().putDouble("fuego", (double)0.0F);
            }
        }
    }
    public static class Helmet extends BlazymancerSpellArmorItem {
        public Helmet() {
            super(Type.HELMET, (new Properties()).fireResistant());
        }

        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("head", (new blazymancer_model(Minecraft.getInstance().getEntityModels().bakeLayer(blazymancer_model.LAYER_LOCATION))).head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                    armorModel.crouching = living.isShiftKeyDown();
                    armorModel.riding = defaultModel.riding;
                    armorModel.young = living.isBaby();
                    return armorModel;
                }
            });
        }


        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "dacxirons:textures/entities/blazymancer_armor.png";
        }
    }
    public static class Chestplate extends BlazymancerSpellArmorItem {
        public Chestplate() {
            super(Type.CHESTPLATE, (new Properties()).fireResistant());
        }

        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                @OnlyIn(Dist.CLIENT)
                public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("body", (new blazymancer_model(Minecraft.getInstance().getEntityModels().bakeLayer(blazymancer_model.LAYER_LOCATION))).body, "left_arm", (new blazymancer_model(Minecraft.getInstance().getEntityModels().bakeLayer(blazymancer_model.LAYER_LOCATION))).left_arm, "right_arm", (new blazymancer_model(Minecraft.getInstance().getEntityModels().bakeLayer(blazymancer_model.LAYER_LOCATION))).right_arm, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                    armorModel.crouching = living.isShiftKeyDown();
                    armorModel.riding = defaultModel.riding;
                    armorModel.young = living.isBaby();
                    return armorModel;
                }
            });
        }


        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "dacxirons:textures/entities/blazymancer_armor.png";
        }
    }





}
