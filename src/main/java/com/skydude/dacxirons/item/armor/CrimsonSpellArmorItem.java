package com.skydude.dacxirons.item.armor;


//This is crimsonspellarmoritem

import com.skydude.dacxirons.client.model.crimson_spell_model;
import net.mcreator.dungeonsandcombat.client.model.Modelebonycrimson;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModMobEffects;
import net.mcreator.dungeonsandcombat.item.CrimsonItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

//import static com.skydude.dacxirons.registries.EffectRegistry.SPELL_STRENGTH;

import javax.annotation.Nullable;

import static com.skydude.dacxirons.registries.EffectRegistry.CAST_SPEED;
import static com.skydude.dacxirons.registries.EffectRegistry.SPELL_STRENGTH;
import static net.minecraft.world.effect.MobEffects.*;

public class CrimsonSpellArmorItem extends ImbueabledacxironsArmor {


    public CrimsonSpellArmorItem(ArmorItem.Type type, Properties properties) {
        super(dacxironsArmorMaterials.CRIMSON_SPELL_ARMOR, type, properties);


    }
    //tooltip
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.dacxirons.crimson_wizard").withStyle(style -> style.withItalic(true)).withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable("tooltip.dacxirons.crimson_wizard2").withStyle(style -> style.withItalic(true)).withStyle(ChatFormatting.RED));
    }


    @SuppressWarnings("removal")
    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player ) {
        // if (event.phase == TickEvent.Phase.END) {
        //  Player player = event.player;


        //    AttributeInstance swimSpeed = player.getAttribute(ForgeMod.SWIM_SPEED.get());
        if (CorrectArmor.hasFullSetOn(player, dacxironsArmorMaterials.CRIMSON_SPELL_ARMOR)) {

            if (player.hasEffect(DungeonsAndCombatModMobEffects.BLEEDING.get())) {
                //player.addEffect(new MobEffectInstance(DungeonsAndCombatModMobEffects.BLEEDING.get(), 200, 0));
                if (!player.hasEffect(SPELL_STRENGTH.get())) {
                    if (player.getEffect(SPELL_STRENGTH.get()) == null || player.getEffect(SPELL_STRENGTH.get()).getDuration() < 10) {
                        player.addEffect(new MobEffectInstance(
                                SPELL_STRENGTH.get(),
                                10,                  // duration in ticks (20 seconds)
                                2,                    // EFFECT LEVEL 0 = 1, 1 = 2, 2 = 3, etc
                                true,                 // ambient — blue outline + no flashing
                                false,                 // show particles
                                true                  // show icon
                        ));
                    }
                }
                if (!player.hasEffect(DAMAGE_RESISTANCE)) {
                    if (player.getEffect(DAMAGE_RESISTANCE) == null || player.getEffect(DAMAGE_RESISTANCE).getDuration() < 10) {
                        player.addEffect(new MobEffectInstance(
                                DAMAGE_RESISTANCE,
                                10,
                                2,
                                true,
                                false,
                                true
                        ));
                    }
                }
                if (!player.hasEffect(MOVEMENT_SPEED)) {
                    if (player.getEffect(MOVEMENT_SPEED) == null || player.getEffect(MOVEMENT_SPEED).getDuration() < 10) {
                        player.addEffect(new MobEffectInstance(
                                MOVEMENT_SPEED,
                                10,
                                2,
                                true,
                                false,
                                true
                        ));
                    }
                }
                if (!player.hasEffect(CAST_SPEED.get())) {
                    if (player.getEffect(CAST_SPEED.get()) == null || player.getEffect(CAST_SPEED.get()).getDuration() < 10) {
                        player.addEffect(new MobEffectInstance(
                                CAST_SPEED.get(),
                                10,                  // duration in ticks (20 seconds)
                                1,                    // amplifier (level 3 effect)
                                true,                 // ambient — blue outline + no flashing
                                false,                 // show particles
                                true                  // show icon
                        ));
                    }
                }

            }
        }
    }
    public static class Helmet extends CrimsonSpellArmorItem {
        public Helmet() {
            super(Type.HELMET, (new Item.Properties()).fireResistant());
        }

        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("head", (new crimson_spell_model(Minecraft.getInstance().getEntityModels().bakeLayer(crimson_spell_model.LAYER_LOCATION))).head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                    armorModel.crouching = living.isShiftKeyDown();
                    armorModel.riding = defaultModel.riding;
                    armorModel.young = living.isBaby();
                    return armorModel;
                }
            });
        }


        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "dacxirons:textures/entities/crimson_wizard_armor.png";
        }
    }
    public static class Chestplate extends CrimsonSpellArmorItem {
        public Chestplate() {
            super(Type.CHESTPLATE, (new Item.Properties()).fireResistant());
        }

        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                @OnlyIn(Dist.CLIENT)
                public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("body", (new crimson_spell_model(Minecraft.getInstance().getEntityModels().bakeLayer(crimson_spell_model.LAYER_LOCATION))).body, "left_arm", (new crimson_spell_model(Minecraft.getInstance().getEntityModels().bakeLayer(crimson_spell_model.LAYER_LOCATION))).left_arm, "right_arm", (new crimson_spell_model(Minecraft.getInstance().getEntityModels().bakeLayer(crimson_spell_model.LAYER_LOCATION))).right_arm, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                    armorModel.crouching = living.isShiftKeyDown();
                    armorModel.riding = defaultModel.riding;
                    armorModel.young = living.isBaby();
                    return armorModel;
                }
            });
        }


        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "dacxirons:textures/entities/crimson_wizard_armor.png";
        }
    }
    public static class Leggings extends CrimsonSpellArmorItem {
        public Leggings() {
            super(Type.LEGGINGS, (new Item.Properties()).fireResistant());
        }

        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                @OnlyIn(Dist.CLIENT)
                public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("left_leg", (new crimson_spell_model(Minecraft.getInstance().getEntityModels().bakeLayer(crimson_spell_model.LAYER_LOCATION))).left_leg, "right_leg", (new crimson_spell_model(Minecraft.getInstance().getEntityModels().bakeLayer(crimson_spell_model.LAYER_LOCATION))).right_leg, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                    armorModel.crouching = living.isShiftKeyDown();
                    armorModel.riding = defaultModel.riding;
                    armorModel.young = living.isBaby();
                    return armorModel;
                }
            });
        }



        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "dacxirons:textures/entities/crimson_wizard_armor.png";
        }
    }
    public static class Boots extends CrimsonSpellArmorItem {
        public Boots() {
            super(Type.BOOTS, (new Item.Properties()).fireResistant());
        }

        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                @OnlyIn(Dist.CLIENT)
                public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("left_leg", (new crimson_spell_model(Minecraft.getInstance().getEntityModels().bakeLayer(crimson_spell_model.LAYER_LOCATION))).left_shoe, "right_leg", (new crimson_spell_model(Minecraft.getInstance().getEntityModels().bakeLayer(crimson_spell_model.LAYER_LOCATION))).right_shoe, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
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
            return "dacxirons:textures/entities/crimson_wizard_armor.png";
        }
    }



}
