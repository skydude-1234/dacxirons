package com.skydude.dacxirons;

import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "dacxirons")
public class OathSwordStaffBehavior {

    private static final ResourceLocation OATH_ID = new ResourceLocation("dungeonsandcombat", "oath_sword");

    @SubscribeEvent
    public static void onTooltip(net.minecraftforge.event.entity.player.ItemTooltipEvent e) {
        var stack = e.getItemStack();
        if (stack.isEmpty()) return;

        var key = stack.getItem().builtInRegistryHolder().key().location();
        if (!key.equals(OATH_ID)) return;

        // purely visual info on hover
        e.getToolTip().add(net.minecraft.network.chat.Component.literal("§7Oathbound: §fReady"));
        // If you need more, add more Components here (translatables, etc.)
    }

    private static void ensureSpellContainer(ItemStack stack) {
        if (!io.redspace.ironsspellbooks.api.spells.ISpellContainer.isSpellContainer(stack)) {
            var spells = java.util.List.of(
                    new SpellDataRegistryHolder(
                            com.skydude.dacxirons.registries.dacxironsSpellRegistry.MAGIC_ARROW, 1
                    ).getSpellData()
            );
            var cont = io.redspace.ironsspellbooks.api.spells.ISpellContainer.create(spells.size(), true, false);
            spells.forEach(sd -> cont.addSpell(sd.getSpell(), sd.getLevel(), true, null));
            cont.save(stack);
        }
    }

}
