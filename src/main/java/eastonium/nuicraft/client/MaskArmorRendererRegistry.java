package eastonium.nuicraft.client;

import eastonium.nuicraft.client.renderer.MaskArmorRenderer;
import eastonium.nuicraft.core.NuiCraftItems;
import mod.azure.azurelib.common.render.armor.AzArmorRendererRegistry;

/**
 * Registers the 3D mask model (geo/armor/mask.geo.json) for all NuiCraft mask items.
 * Same model for every mask; texture varies by mask type.
 */
public class MaskArmorRendererRegistry {

    public static void registerAll() {
        // Register only the Mata masks that have armor renderers
        AzArmorRendererRegistry.register(NuiCraftItems.MASK_MATA_AKAKU.get(), MaskArmorRenderer::mataAkaku);
        AzArmorRendererRegistry.register(NuiCraftItems.MASK_MATA_HAU.get(), MaskArmorRenderer::mataHau);
        AzArmorRendererRegistry.register(NuiCraftItems.MASK_MATA_KAKAMA.get(), MaskArmorRenderer::mataKakama);
        AzArmorRendererRegistry.register(NuiCraftItems.MASK_MATA_KAUKAU.get(), MaskArmorRenderer::mataKaukau);
        AzArmorRendererRegistry.register(NuiCraftItems.MASK_MATA_MIRU.get(), MaskArmorRenderer::mataMiru);
        AzArmorRendererRegistry.register(NuiCraftItems.MASK_MATA_PAKARI.get(), MaskArmorRenderer::mataPakari);
    }
}
