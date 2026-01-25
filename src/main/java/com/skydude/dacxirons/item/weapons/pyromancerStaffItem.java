

package com.skydude.dacxirons.item.weapons;

import com.skydude.dacxirons.renderers.sceptercompensationstaffRenderer;
import com.skydude.dacxirons.registries.ItemRegistries;
import com.skydude.dacxirons.registries.dacxironsSpellRegistry;
import io.redspace.ironsspellbooks.api.events.SpellDamageEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.spells.SpellData;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.item.weapons.StaffItem;
import io.redspace.ironsspellbooks.item.weapons.StaffTier;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModMobEffects;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static com.skydude.dacxirons.dacxirons.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE) //

public class pyromancerStaffItem extends StaffItem implements IPresetSpellContainer {


    public static StaffTier PYROCLASTIC = new StaffTier(3, -2.4f,
            new AttributeContainer(AttributeRegistry.FIRE_SPELL_POWER, .25, AttributeModifier.Operation.MULTIPLY_BASE)
    );

    public pyromancerStaffItem( ) {
        super(ItemPropertiesHelper.equipment(1), PYROCLASTIC);
    }
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IClientItemExtensions() {
            private final BlockEntityWithoutLevelRenderer renderer = new sceptercompensationstaffRenderer();

            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return this.renderer;
            }
        });
    }
//    //spells container stuff
//    private static final SpellDataRegistryHolder[] DEFAULT_SPELLS = new SpellDataRegistryHolder[]{
//            new SpellDataRegistryHolder(dacxironsSpellRegistry.TRIPLE_FIREBALL_SPELL, 3)
//
//    };

    private List<SpellData> spellData = null;


//    public List<SpellData> getSpells() {
//        if (spellData == null) {
//            spellData = Arrays.stream(DEFAULT_SPELLS).map(SpellDataRegistryHolder::getSpellData).toList();
//        }
//        return spellData;
//    }
    @Override
    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) return;

        if (!ISpellContainer.isSpellContainer(itemStack)) {
            var spellContainer = ISpellContainer.create(1, true, false).mutableCopy();
            spellContainer.addSpell(dacxironsSpellRegistry.TRIPLE_FIREBALL_SPELL.get(), 1, true);
            ISpellContainer.set(itemStack, spellContainer.toImmutable());
        }
    }
    // end of spells container stuff






    public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);

        list.add(Component.literal("§7Ability:"));
        list.add(Component.translatable("ui.dacxirons.pyroclasticabilityeffect"));
    }

    @SubscribeEvent
    public static void onSpellAttack(SpellDamageEvent event) {
        LivingEntity target = event.getEntity();

        // Get the player/caster
        LivingEntity attacker = (LivingEntity) event.getSpellDamageSource().getEntity();

        if (attacker != null) {

            if (attacker.getMainHandItem().is(ItemRegistries.SCEPTERPYROCLASTIC.get())) {
                // only server side
                if (!attacker.level().isClientSide) {
                    SpellAttackEffect.SpellEffectAdd(attacker, DungeonsAndCombatModMobEffects.FLAME_GRANT_ME_STRENGTH.get(), 100, 1, false, true);
                }
            }
        }
    }
}
