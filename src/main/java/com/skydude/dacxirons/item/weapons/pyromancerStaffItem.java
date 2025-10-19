

package com.skydude.dacxirons.item.weapons;

import com.skydude.dacxirons.item.renderer.sceptercompensationstaffRenderer;
import com.skydude.dacxirons.registries.ItemRegistries;
import com.skydude.dacxirons.registries.dacxironsSpellRegistry;
import io.redspace.ironsspellbooks.api.events.SpellDamageEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.spells.SpellData;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.item.weapons.StaffItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModMobEffects;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
import software.bernie.geckolib.core.animation.AnimationController.State;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import static com.skydude.dacxirons.dacxirons.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE) //

public class pyromancerStaffItem extends StaffItem implements IPresetSpellContainer {

    public static boolean isholding = false;
    public static Player holder;


    public pyromancerStaffItem() {
        super(ItemPropertiesHelper.equipment().stacksTo(1).rarity(Rarity.UNCOMMON), 2, -3,
                Map.of(
                        AttributeRegistry.FIRE_SPELL_POWER.get(),
                        new AttributeModifier(UUID.fromString("001ad88d-901d-4691-b2a2-3664e42026d3"), " fire", .1, Operation.MULTIPLY_BASE)

              ));
    }
    //spells container stuff
    private static final SpellDataRegistryHolder[] DEFAULT_SPELLS = new SpellDataRegistryHolder[]{
            new SpellDataRegistryHolder(dacxironsSpellRegistry.TRIPLE_FIREBALL_SPELL, 1)

    };

    private List<SpellData> spellData = null;
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IClientItemExtensions() {
            private final BlockEntityWithoutLevelRenderer renderer = new sceptercompensationstaffRenderer();

            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return this.renderer;
            }
        });
    }

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






    public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);

        list.add(Component.literal("§7Ability:"));
        list.add(Component.translatable("ui.dacxirons.pyroclasticabilityeffect"));
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex){
        if(player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == ItemRegistries.SCEPTERPYROCLASTIC.get()){
            isholding = true;
            holder = player;


        } else {
            isholding = false;
        }


    }
    @SubscribeEvent
    public static void onSpellAttack(SpellDamageEvent event) {

        if (isholding) {
            //    SpellAttackEffect.SpellEffectAdd(event.getEntity(), MobEffects.FL, 200,1, false, true);
            event.getEntity().setSecondsOnFire(5);
            // add effect
            var aaaaa = event.getSpellDamageSource();
// to prevent client-server causing effect to stay at 00:00

            // apply only on the server
            if (event.getSpellDamageSource().spell().getSchoolType() == SchoolRegistry.EVOCATION.get()) {

                MobEffectInstance mobeffect = new MobEffectInstance(
                        DungeonsAndCombatModMobEffects.FLAME_GRANT_ME_STRENGTH.get(),
                        60, 0, false, true
                );

                if (holder instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
                    serverPlayer.addEffect(mobeffect); // server player
                } else if (holder != null && holder.level() instanceof net.minecraft.server.level.ServerLevel) {
                    holder.addEffect(mobeffect); // any server-side entity
                } else {
                   // Do effect server only
                    net.minecraftforge.fml.DistExecutor.unsafeRunWhenOn(
                            net.minecraftforge.api.distmarker.Dist.CLIENT,
                            () -> () -> {
                                var minecraft   = net.minecraft.client.Minecraft.getInstance();
                                var srv  = minecraft.getSingleplayerServer();              // null on dedicated
                                if (srv == null) return;
                                var sLvl = srv.getLevel(holder.level().dimension()); // server copy of the same dimension
                                if (sLvl == null) return;
                                var real = sLvl.getEntity(holder.getUUID());         // server-side twin of holder
                                if (real instanceof net.minecraft.world.entity.LivingEntity le) {
                                    le.addEffect(mobeffect); // finally apply on the server
                                }
                            }
                    );
                }



        }
    }
}
}
