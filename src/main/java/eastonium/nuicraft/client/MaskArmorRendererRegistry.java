package eastonium.nuicraft.client;

import eastonium.nuicraft.client.renderer.MaskArmorRenderer;
import eastonium.nuicraft.core.NuiCraftItems;
import mod.azure.azurelib.common.render.armor.AzArmorRendererRegistry;

public class MaskArmorRendererRegistry {
    public static void registerAll() {
        // Register all 12 Mata masks with AzureLib
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
    }
}
