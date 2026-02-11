package eastonium.nuicraft.jei;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.core.NuiCraftItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.stream.Stream;

/**
 * JEI plugin for NuiCraft. Registers mask items with JEI (item list + info) and ensures
 * data-pack recipes (mask_mata_*.json, etc.) are available for recipe lookup.
 */
@JeiPlugin
public class NuiCraftJeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        // Explicitly add all Mata masks to JEI with description so they appear in item list and show recipe lookup
        List<ItemStack> masks = Stream.of(
                NuiCraftItems.MASK_MATA_HAU,
                NuiCraftItems.MASK_MATA_KAUKAU,
                NuiCraftItems.MASK_MATA_MIRU,
                NuiCraftItems.MASK_MATA_KAKAMA,
                NuiCraftItems.MASK_MATA_PAKARI,
                NuiCraftItems.MASK_MATA_AKAKU,
                NuiCraftItems.MASK_MATA_HUNA,
                NuiCraftItems.MASK_MATA_MAHIKI,
                NuiCraftItems.MASK_MATA_MATATU,
                NuiCraftItems.MASK_MATA_KOMAU,
                NuiCraftItems.MASK_MATA_RARU,
                NuiCraftItems.MASK_MATA_RURU
        ).map(d -> new ItemStack(d.get())).toList();
        registration.addItemStackInfo(masks, Component.translatable("jei.nuicraft.mask.info"));
    }
}
