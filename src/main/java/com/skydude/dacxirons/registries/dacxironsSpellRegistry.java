package com.skydude.dacxirons.registries;

import com.skydude.dacxirons.dacxirons;
//import com.skydude.dacxirons.spells.Summon;
import com.skydude.dacxirons.spells.Summon;
import com.skydude.dacxirons.spells.SummonKamath;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class dacxironsSpellRegistry {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SpellRegistry.SPELL_REGISTRY_KEY, dacxirons.MOD_ID);

    public static void register(IEventBus eventBus) {
        SPELLS.register(eventBus);
    }

    public static RegistryObject<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    public static final RegistryObject<AbstractSpell> SUMMONER_WEAKNESS = registerSpell(new Summon());
    public static final RegistryObject<AbstractSpell> SUMMONED_KAMATH = registerSpell(new SummonKamath());
}

