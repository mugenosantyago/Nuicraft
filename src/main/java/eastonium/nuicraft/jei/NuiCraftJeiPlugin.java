package eastonium.nuicraft.jei;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.core.NuiCraftItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;

/**
 * JEI plugin for NuiCraft. Ensures our mod is recognized by JEI so that
 * items (bamboo disc, kanoka discs, etc.) and data-pack recipes appear in the item list and recipe lookup.
 */
@JeiPlugin
public class NuiCraftJeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        // Items are discovered from the item registry and creative tab.
        // Data-pack recipes (data/nuicraft/recipes/*.json) are picked up by JEI automatically.
    }
}
