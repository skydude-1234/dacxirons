package com.skydude.dacxirons.mixin;

import com.skydude.dacxirons.registries.dacxironsSpellRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.spells.SpellData;
import net.mcreator.dungeonsandcombat.item.OathSwordItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

@Mixin(value = OathSwordItem.class, remap = false)
public abstract class OathSwordItemMixin implements IPresetSpellContainer {
    @Unique private static final Logger LOG = LoggerFactory.getLogger("dacxirons");

    @Unique
    private static final SpellDataRegistryHolder[] DEFAULT_SPELLS = new SpellDataRegistryHolder[]{
            new SpellDataRegistryHolder(dacxironsSpellRegistry.MAGIC_ARROW, 1)
            // add more default spells here
    };

    @Unique private List<SpellData> dacxirons$spellData;

    @Unique
    private List<SpellData> dacxirons$getSpells() {
        if (dacxirons$spellData == null) {
            dacxirons$spellData = Arrays.stream(DEFAULT_SPELLS)
                    .map(SpellDataRegistryHolder::getSpellData)
                    .toList();
        }
        return dacxirons$spellData;
    }

    // Ensure the sword always has a spell container when right-clicked
    // Ensure the sword has a spell container when its tooltip is displayed
    @Inject(method = "appendHoverText", at = @At("HEAD"))
    private void dacxirons$onHover(ItemStack stack, Level level,
                                   java.util.List<net.minecraft.network.chat.Component> tooltip,
                                   net.minecraft.world.item.TooltipFlag flag,
                                   CallbackInfo ci) {
        if (!ISpellContainer.isSpellContainer(stack)) { // <-- use "stack" here
            initializeSpellContainer(stack);
            LOG.info("[dacxirons] Initialized spell container (on hover) for {}", stack.getHoverName().getString());
        }
    }



    // Implementation of IPresetSpellContainer
    @Override
    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null || ISpellContainer.isSpellContainer(itemStack)) return;

        var spells = dacxirons$getSpells();
        var container = ISpellContainer.create(spells.size(), true, false);
        spells.forEach(sd -> container.addSpell(sd.getSpell(), sd.getLevel(), true, null));
        container.save(itemStack);
    }
}
