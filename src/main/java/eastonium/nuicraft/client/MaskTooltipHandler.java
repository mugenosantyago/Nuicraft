package eastonium.nuicraft.client;

import eastonium.nuicraft.core.NuiCraftItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

/**
 * Adds "When worn:" stat lines to Kanohi mask tooltips so players can see the boosts in inventory.
 */
public class MaskTooltipHandler {

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.isEmpty()) return;
        Item item = stack.getItem();
        List<Component> tooltip = event.getToolTip();

        double armor = 0, toughness = 0, speed = 0, attack = 0, kbResist = 0, reach = 0;
        if (item == NuiCraftItems.MASK_MATA_HAU.get()) {
            armor = 4; toughness = 2;
        } else if (item == NuiCraftItems.MASK_MATA_KAUKAU.get()) {
            armor = 2;
        } else if (item == NuiCraftItems.MASK_MATA_MIRU.get()) {
            armor = 2;
        } else if (item == NuiCraftItems.MASK_MATA_KAKAMA.get()) {
            armor = 2; speed = 0.4;
        } else if (item == NuiCraftItems.MASK_MATA_PAKARI.get()) {
            armor = 3; toughness = 1; attack = 3;
        } else if (item == NuiCraftItems.MASK_MATA_AKAKU.get()) {
            armor = 2;
        } else if (item == NuiCraftItems.MASK_MATA_HUNA.get()) {
            armor = 1;
        } else if (item == NuiCraftItems.MASK_MATA_MAHIKI.get()) {
            armor = 1;
        } else if (item == NuiCraftItems.MASK_MATA_MATATU.get()) {
            armor = 2; kbResist = 0.5; reach = 3;
        } else if (item == NuiCraftItems.MASK_MATA_KOMAU.get()) {
            armor = 2;
        } else if (item == NuiCraftItems.MASK_MATA_RARU.get()) {
            armor = 2;
        } else if (item == NuiCraftItems.MASK_MATA_RURU.get()) {
            armor = 2;
        } else {
            return;
        }

        tooltip.add(Component.empty());
        tooltip.add(Component.translatable("item.nuicraft.mask.when_worn"));
        if (armor > 0) tooltip.add(Component.translatable("item.nuicraft.mask.armor", (int) armor));
        if (toughness > 0) tooltip.add(Component.translatable("item.nuicraft.mask.toughness", (int) toughness));
        if (speed > 0) tooltip.add(Component.translatable("item.nuicraft.mask.speed", (int) (speed * 100)));
        if (attack > 0) tooltip.add(Component.translatable("item.nuicraft.mask.attack", (int) attack));
        if (kbResist > 0) tooltip.add(Component.translatable("item.nuicraft.mask.knockback_resist", (int) (kbResist * 100)));
        if (reach > 0) tooltip.add(Component.translatable("item.nuicraft.mask.reach", (int) reach));
    }
}
