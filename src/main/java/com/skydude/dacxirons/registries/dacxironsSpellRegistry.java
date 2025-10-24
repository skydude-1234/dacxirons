package com.skydude.dacxirons.registries;

import com.skydude.dacxirons.dacxirons;
//import com.skydude.dacxirons.spells.Summon;
import com.skydude.dacxirons.spells.*;
//import com.skydude.dacxirons.spells.Summon;
import com.skydude.dacxirons.spells.Blood.BloodyArrow;
import com.skydude.dacxirons.spells.Blood.CrimsonOath;
import com.skydude.dacxirons.spells.eldritch.EldritchSlashSpell;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class dacxironsSpellRegistry {
  //  public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SpellRegistry.SPELL_REGISTRY_KEY, dacxirons.MOD_ID);
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SpellRegistry.SPELL_REGISTRY_KEY, dacxirons.MOD_ID);;
   public static final RegistryObject<AbstractSpell> SUMMONER_WEAKNESS = registerSpell(new SummonWeakness());;
    public static final RegistryObject<AbstractSpell> SUMMON_KAMATH  = registerSpell(new SummonKamath());;

    public static final RegistryObject<AbstractSpell> SUNLEIA_BEAM = registerSpell(new SunleiaBeam());;
    public static final RegistryObject<AbstractSpell> ARCANE_ARROW = registerSpell(new ArcaneArrow());
  public static final RegistryObject<AbstractSpell> BLOODY_ARROW = registerSpell(new BloodyArrow());
  public static final RegistryObject<AbstractSpell> TRIPLE_FIREBALL_SPELL = registerSpell(new TripleFireballSpell());
  public static final RegistryObject<AbstractSpell> ACID_BALL_SPELL = registerSpell(new AcidBallSpell());;
  public static final RegistryObject<AbstractSpell> FAIRYS_WISH_SPELL = registerSpell(new FairysWishSpell());;
  public static final RegistryObject<AbstractSpell> CRIMSON_OATH = registerSpell(new CrimsonOath());;

    public static final RegistryObject<AbstractSpell> ELDRITCH_SLASH_SPELL = registerSpell(new EldritchSlashSpell());

    public static RegistryObject<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    public static void register(IEventBus eventBus) {
        SPELLS.register(eventBus);
    }

}

