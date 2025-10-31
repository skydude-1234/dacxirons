package com.skydude.dacxirons.item.armor;




import com.skydude.dacxirons.client.model.blazymancer_model;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;



public class BlazymancerSpellArmorItem extends ImbueabledacxironsArmor {

    public static boolean blazymancerfull = false;
    public BlazymancerSpellArmorItem(ArmorItem.Type type, Properties properties) {
        super(dacxironsArmorMaterials.BLAZYMANCER_ARMOR, type, properties);


    }
    //tooltip
    @Override
    public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.translatable("fullsetbonus"));
        list.add(Component.translatable("§9Burning Spell Aura:"));
        list.add(Component.translatable("§7When attacked, set your attacker ablaze and cast firebolt"));
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
