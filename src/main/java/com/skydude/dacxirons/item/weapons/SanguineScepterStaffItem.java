//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.skydude.dacxirons.item.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import com.skydude.dacxirons.dacxirons;
import com.skydude.dacxirons.registries.ItemRegistries;
import com.skydude.dacxirons.registries.dacxironsSpellRegistry;
import com.skydude.dacxirons.renderers.SanguineScepterItemRenderer;
import io.redspace.ironsspellbooks.api.events.SpellDamageEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.spells.SpellData;
import io.redspace.ironsspellbooks.item.weapons.StaffItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModMobEffects;
import net.mcreator.dungeonsandcombat.procedures.SanguineScepterRightclickedProcedure;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationController.State;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
@Mod.EventBusSubscriber(modid = dacxirons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SanguineScepterStaffItem extends StaffItem implements GeoItem, IPresetSpellContainer {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public String animationprocedure = "empty";
    public static ItemDisplayContext transformType;
    String prevAnim = "empty";



    public SanguineScepterStaffItem() {
        super(ItemPropertiesHelper.equipment().stacksTo(1).rarity(Rarity.UNCOMMON), 3, -2.4,
                Map.of(
                        AttributeRegistry.FIRE_SPELL_POWER.get(),
                        new AttributeModifier(UUID.fromString("001ad88d-901d-4691-b2a2-3664e42026d3"), " fire", .1, Operation.MULTIPLY_BASE)

                ));
    }

    //spells container stuff
    private static final SpellDataRegistryHolder[] DEFAULT_SPELLS = new SpellDataRegistryHolder[]{
            new SpellDataRegistryHolder(dacxironsSpellRegistry.BLOODY_ARROW, 3)

    };

    private List<SpellData> spellData = null;


    public List<SpellData> getSpells() {
        if (spellData == null) {
            spellData = Arrays.stream(DEFAULT_SPELLS).map(SpellDataRegistryHolder::getSpellData).toList();
        }
        return spellData;
    }

    @Override
    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) return;

        if (!ISpellContainer.isSpellContainer(itemStack)) {
            var spells = getSpells();
            var spellContainer = ISpellContainer.create(spells.size(), true, false);
            spells.forEach(spellData -> spellContainer.addSpell(spellData.getSpell(), spellData.getLevel(), true, null));
            spellContainer.save(itemStack);
        }
    }

    // end of spells container stuff

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IClientItemExtensions() {
            private final BlockEntityWithoutLevelRenderer renderer = new SanguineScepterItemRenderer();

            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return this.renderer;
            }
        });
    }

    public void getTransformType(ItemDisplayContext type) {
        transformType = type;
    }

    private PlayState idlePredicate(AnimationState event) {
        if (transformType != null && this.animationprocedure.equals("empty")) {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("idle"));
            return PlayState.CONTINUE;
        } else {
            return PlayState.STOP;
        }
    }

    private PlayState procedurePredicate(AnimationState event) {
        if (transformType != null) {
            if (!this.animationprocedure.equals("empty") && event.getController().getAnimationState() == State.STOPPED || !this.animationprocedure.equals(this.prevAnim) && !this.animationprocedure.equals("empty")) {
                if (!this.animationprocedure.equals(this.prevAnim)) {
                    event.getController().forceAnimationReset();
                }

                event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
                if (event.getController().getAnimationState() == State.STOPPED) {
                    this.animationprocedure = "empty";
                    event.getController().forceAnimationReset();
                }
            } else if (this.animationprocedure.equals("empty")) {
                this.prevAnim = "empty";
                return PlayState.STOP;
            }
        }

        this.prevAnim = this.animationprocedure;
        return PlayState.CONTINUE;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        AnimationController procedureController = new AnimationController(this, "procedureController", 0, this::procedurePredicate);
        data.add(new AnimationController[]{procedureController});
        AnimationController idleController = new AnimationController(this, "idleController", 0, this::idlePredicate);
        data.add(new AnimationController[]{idleController});
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public int getEnchantmentValue() {
        return 12;
    }


    public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.literal("§7Ability: Your spells steal health from enemies"));
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == ItemRegistries.SANGUINE_SCEPTER_STAFF.get()) {
            event.player.addEffect(new MobEffectInstance(DungeonsAndCombatModMobEffects.BLEEDING.get(), 20, 0, false, true));

        }

    }
    @SubscribeEvent
    public static void onSpellAttack(SpellDamageEvent event) {
        LivingEntity target = event.getEntity();

        // Get the player/caster
        LivingEntity attacker = (LivingEntity) event.getSpellDamageSource().getEntity();

        if (attacker != null) {

            if (attacker.getMainHandItem().is(ItemRegistries.SANGUINE_SCEPTER_STAFF.get())) {
                // only server side
                if (!attacker.level().isClientSide) {
                   attacker.heal(3F);
                }
            }
        }
    }

}