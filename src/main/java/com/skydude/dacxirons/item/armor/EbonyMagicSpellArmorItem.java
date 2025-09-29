package com.skydude.dacxirons.item.armor;




import com.skydude.dacxirons.client.model.crimson_spell_model;
import com.skydude.dacxirons.client.model.ebony_spell_model;
import io.redspace.ironsspellbooks.api.events.SpellDamageEvent;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModMobEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.security.PublicKey;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.skydude.dacxirons.registries.EffectRegistry.CAST_SPEED;
import static com.skydude.dacxirons.registries.EffectRegistry.SPELL_STRENGTH;
import static net.minecraft.world.effect.MobEffects.DAMAGE_RESISTANCE;
import static net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED;

public class EbonyMagicSpellArmorItem extends ImbueabledacxironsArmor {

public static boolean fullebonymagic;
    public EbonyMagicSpellArmorItem(Type type, Properties properties) {
        super(dacxironsArmorMaterials.EBONY_SPELL_ARMOR, type, properties);


    }
    //tooltip
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("fullsetbonus"));
        tooltip.add(Component.translatable("tooltip.dacxirons.ebonyfullset"));
        tooltip.add(Component.translatable("tooltip.dacxirons.ebony_wizard_set").withStyle(style -> style.withItalic(true)).withStyle(ChatFormatting.DARK_GRAY));

    }


    @SuppressWarnings("removal")
    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player ) {
        // if (event.phase == TickEvent.Phase.END) {
        //  Player player = event.player;

        if (CorrectArmor.hasFullSetOn(player, dacxironsArmorMaterials.EBONY_SPELL_ARMOR, 4)) {
            //    AttributeInstance swimSpeed = player.getAttribute(ForgeMod.SWIM_SPEED.get());
            fullebonymagic = true;

        }
    }

    public static class Helmet extends EbonyMagicSpellArmorItem {
        public Helmet() {
            super(Type.HELMET, (new Properties()).fireResistant());
        }

        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("head", (new ebony_spell_model(Minecraft.getInstance().getEntityModels().bakeLayer(ebony_spell_model.LAYER_LOCATION))).head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                    armorModel.crouching = living.isShiftKeyDown();
                    armorModel.riding = defaultModel.riding;
                    armorModel.young = living.isBaby();
                    return armorModel;
                }
            });
        }


        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "dacxirons:textures/entities/ebony_wizard_armor.png";
        }
    }
    public static class Chestplate extends EbonyMagicSpellArmorItem {
        public Chestplate() {
            super(Type.CHESTPLATE, (new Properties()).fireResistant());
        }

        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                @OnlyIn(Dist.CLIENT)
                public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("body", (new ebony_spell_model(Minecraft.getInstance().getEntityModels().bakeLayer(ebony_spell_model.LAYER_LOCATION))).body, "left_arm", (new ebony_spell_model(Minecraft.getInstance().getEntityModels().bakeLayer(ebony_spell_model.LAYER_LOCATION))).left_arm, "right_arm", (new ebony_spell_model(Minecraft.getInstance().getEntityModels().bakeLayer(ebony_spell_model.LAYER_LOCATION))).right_arm, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                    armorModel.crouching = living.isShiftKeyDown();
                    armorModel.riding = defaultModel.riding;
                    armorModel.young = living.isBaby();
                    return armorModel;
                }
            });
        }


        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "dacxirons:textures/entities/ebony_wizard_armor.png";
        }
    }
    public static class Leggings extends EbonyMagicSpellArmorItem {
        public Leggings() {
            super(Type.LEGGINGS, (new Properties()).fireResistant());
        }

        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                @OnlyIn(Dist.CLIENT)
                public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("left_leg", (new ebony_spell_model(Minecraft.getInstance().getEntityModels().bakeLayer(ebony_spell_model.LAYER_LOCATION))).left_leg, "right_leg", (new ebony_spell_model(Minecraft.getInstance().getEntityModels().bakeLayer(ebony_spell_model.LAYER_LOCATION))).right_leg, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                    armorModel.crouching = living.isShiftKeyDown();
                    armorModel.riding = defaultModel.riding;
                    armorModel.young = living.isBaby();
                    return armorModel;
                }
            });
        }



        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "dacxirons:textures/entities/ebony_wizard_armor.png";
        }
    }
    public static class Boots extends EbonyMagicSpellArmorItem {
        public Boots() {
            super(Type.BOOTS, (new Properties()).fireResistant());
        }

        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                @OnlyIn(Dist.CLIENT)
                public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("left_leg", (new ebony_spell_model(Minecraft.getInstance().getEntityModels().bakeLayer(ebony_spell_model.LAYER_LOCATION))).left_shoe, "right_leg", (new ebony_spell_model(Minecraft.getInstance().getEntityModels().bakeLayer(ebony_spell_model.LAYER_LOCATION))).right_shoe, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
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
            return "dacxirons:textures/entities/ebony_wizard_armor.png";
        }
    }



}
