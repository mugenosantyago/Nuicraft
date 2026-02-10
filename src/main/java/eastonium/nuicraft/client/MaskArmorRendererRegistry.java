package eastonium.nuicraft.client;

import eastonium.nuicraft.client.renderer.MaskArmorRenderer;
import eastonium.nuicraft.core.NuiCraftItems;
import mod.azure.azurelib.common.render.armor.AzArmorRendererRegistry;

/**
 * Registers the 3D mask models for all 12 Mata masks.
 * Each mask has its own geo.json model and texture.
 */
public class MaskArmorRendererRegistry {

    public static void registerAll() {
        // Register Mata masks - each mask gets its own renderer factory
        AzArmorRendererRegistry.register(MaskArmorRenderer::mataAkaku, NuiCraftItems.MASK_MATA_AKAKU.get());
        AzArmorRendererRegistry.register(MaskArmorRenderer::mataHau, NuiCraftItems.MASK_MATA_HAU.get());
        AzArmorRendererRegistry.register(MaskArmorRenderer::mataHuna, NuiCraftItems.MASK_MATA_HUNA.get());
        AzArmorRendererRegistry.register(MaskArmorRenderer::mataKakama, NuiCraftItems.MASK_MATA_KAKAMA.get());
        AzArmorRendererRegistry.register(MaskArmorRenderer::mataKaukau, NuiCraftItems.MASK_MATA_KAUKAU.get());
        AzArmorRendererRegistry.register(MaskArmorRenderer::mataKomau, NuiCraftItems.MASK_MATA_KOMAU.get());
        AzArmorRendererRegistry.register(MaskArmorRenderer::mataMahiki, NuiCraftItems.MASK_MATA_MAHIKI.get());
        AzArmorRendererRegistry.register(MaskArmorRenderer::mataMatatu, NuiCraftItems.MASK_MATA_MATATU.get());
        AzArmorRendererRegistry.register(MaskArmorRenderer::mataMiru, NuiCraftItems.MASK_MATA_MIRU.get());
        AzArmorRendererRegistry.register(MaskArmorRenderer::mataPakari, NuiCraftItems.MASK_MATA_PAKARI.get());
        AzArmorRendererRegistry.register(MaskArmorRenderer::mataRaru, NuiCraftItems.MASK_MATA_RARU.get());
        AzArmorRendererRegistry.register(MaskArmorRenderer::mataRuru, NuiCraftItems.MASK_MATA_RURU.get());

        // Register Toa masks (colored variants)
        AzArmorRendererRegistry.register(MaskArmorRenderer::toaHauRed, NuiCraftItems.MASK_TOA_HAU_RED.get());
        AzArmorRendererRegistry.register(MaskArmorRenderer::toaKaukauBlue, NuiCraftItems.MASK_TOA_KAUKAU_BLUE.get());
        AzArmorRendererRegistry.register(MaskArmorRenderer::toaKakamaBrown, NuiCraftItems.MASK_TOA_KAKAMA_BROWN.get());
        AzArmorRendererRegistry.register(MaskArmorRenderer::toaMiruGreen, NuiCraftItems.MASK_TOA_MIRU_GREEN.get());
        AzArmorRendererRegistry.register(MaskArmorRenderer::toaPakariBlack, NuiCraftItems.MASK_TOA_PAKARI_BLACK.get());
        AzArmorRendererRegistry.register(MaskArmorRenderer::toaAkakuWhite, NuiCraftItems.MASK_TOA_AKAKU_WHITE.get());
    }
}
