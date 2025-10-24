package com.skydude.dacxirons.item.weapons;

import com.skydude.dacxirons.registries.dacxironsSpellRegistry;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.spells.SpellData;
import io.redspace.ironsspellbooks.item.weapons.StaffItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;




import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
public class ScepterofOvercompensation extends StaffItem implements IPresetSpellContainer {
    private static final SpellDataRegistryHolder[] DEFAULT_SPELLS = new SpellDataRegistryHolder[]{
            new SpellDataRegistryHolder(dacxironsSpellRegistry.ARCANE_ARROW, 1)

    };

    private List<SpellData> spellData = null;

    public ScepterofOvercompensation() {
        super(ItemPropertiesHelper.equipment().stacksTo(1).rarity(Rarity.RARE), 5, -2.4,
                Map.of(
                        AttributeRegistry.CAST_TIME_REDUCTION.get(),
                        new AttributeModifier(UUID.fromString("697ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", .10, AttributeModifier.Operation.MULTIPLY_BASE),
                        AttributeRegistry.SPELL_POWER.get(),
                        new AttributeModifier(UUID.fromString("697ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", .15, AttributeModifier.Operation.MULTIPLY_BASE),
                        AttributeRegistry.COOLDOWN_REDUCTION.get(),
                        new AttributeModifier(UUID.fromString("697ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", .10, AttributeModifier.Operation.MULTIPLY_BASE)
                ));
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
}



