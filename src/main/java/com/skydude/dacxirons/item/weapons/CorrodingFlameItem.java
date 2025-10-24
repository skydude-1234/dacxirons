
package com.skydude.dacxirons.item.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import com.skydude.dacxirons.registries.ItemRegistries;
import com.skydude.dacxirons.registries.dacxironsSpellRegistry;
import com.skydude.dacxirons.renderers.CorrodingFlameItemRenderer;
import io.redspace.ironsspellbooks.api.events.SpellDamageEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.spells.SpellData;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.item.weapons.StaffItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
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
@Mod.EventBusSubscriber
public class CorrodingFlameItem extends StaffItem implements GeoItem, IPresetSpellContainer {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public String animationprocedure = "empty";
    public static ItemDisplayContext transformType;
    String prevAnim = "empty";
    public static boolean isholding;
    public static Player holder;

    public CorrodingFlameItem() {
        super(ItemPropertiesHelper.equipment().fireResistant().stacksTo(1).rarity(Rarity.UNCOMMON), 3, -2.4,
                Map.of(
                        AttributeRegistry.NATURE_SPELL_POWER.get(),
                        new AttributeModifier(UUID.fromString("671ad88d-901d-4691-b2a2-6767e67026d3"), " naturee", .15, Operation.MULTIPLY_BASE)

                ));
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IClientItemExtensions() {
            private final BlockEntityWithoutLevelRenderer renderer = new CorrodingFlameItemRenderer();

            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return this.renderer;
            }
        });
    }
    //spells container stuff
    private static final SpellDataRegistryHolder[] DEFAULT_SPELLS = new SpellDataRegistryHolder[]{
            new SpellDataRegistryHolder(dacxironsSpellRegistry.ACID_BALL_SPELL, 3)

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

    public void getTransformType(ItemDisplayContext type) {
        transformType = type;
    }


//    private PlayState procedurePredicate(AnimationState event) {
//        if (transformType != null) {
//            if (!this.animationprocedure.equals("empty") && event.getController().getAnimationState() == State.STOPPED || !this.animationprocedure.equals(this.prevAnim) && !this.animationprocedure.equals("empty")) {
//                if (!this.animationprocedure.equals(this.prevAnim)) {
//                    event.getController().forceAnimationReset();
//                }
//
//                event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
//                if (event.getController().getAnimationState() == State.STOPPED) {
//                    this.animationprocedure = "empty";
//                    event.getController().forceAnimationReset();
//                }
//            } else if (this.animationprocedure.equals("empty")) {
//                this.prevAnim = "empty";
//                return PlayState.STOP;
//            }
//        }
//
//        this.prevAnim = this.animationprocedure;
//        return PlayState.CONTINUE;
//    }

    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
//        AnimationController procedureController = new AnimationController(this, "procedureController", 0, this::procedurePredicate);
//        data.add(new AnimationController[]{procedureController});
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;

    }



    public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.literal("§7 Ability:"));
        list.add(Component.literal(" §9Your Spells poison enemies"));

    }

    @SubscribeEvent
    public static void onSpellAttack(SpellDamageEvent event) {
        LivingEntity target = event.getEntity();

        // Get the player/caster
        LivingEntity attacker = (LivingEntity) event.getSpellDamageSource().getEntity();

        if (attacker != null) {

            if (attacker.getMainHandItem().is(ItemRegistries.CORRODING_FLAME_STAFF.get())) {
                // only server side
                if (!attacker.level().isClientSide) {
                    int duration = 100;
                    SpellAttackEffect.SpellEffectAdd(target, MobEffects.POISON, duration, 1, false, true);
                }
            }
        }
    }


}
