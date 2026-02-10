package eastonium.nuicraft.client;

import eastonium.nuicraft.client.renderer.MaskArmorRenderer;
import eastonium.nuicraft.core.NuiCraftItems;
import mod.azure.azurelib.common.render.armor.AzArmorRendererRegistry;

public class MaskArmorRendererRegistry {
    public static void registerAll() {
        // Register all 12 Mata masks with AzureLib
        // Signature: register(Item item, Supplier<AzArmorRenderer> supplier)
        AzArmorRendererRegistry.register(NuiCraftItems.MASK_MATA_AKAKU.get(), MaskArmorRenderer::mataAkaku);
        AzArmorRendererRegistry.register(NuiCraftItems.MASK_MATA_HAU.get(), MaskArmorRenderer::mataHau);
        AzArmorRendererRegistry.register(NuiCraftItems.MASK_MATA_HUNA.get(), MaskArmorRenderer::mataHuna);
        AzArmorRendererRegistry.register(NuiCraftItems.MASK_MATA_KAKAMA.get(), MaskArmorRenderer::mataKakama);
        AzArmorRendererRegistry.register(NuiCraftItems.MASK_MATA_KAUKAU.get(), MaskArmorRenderer::mataKaukau);
        AzArmorRendererRegistry.register(NuiCraftItems.MASK_MATA_KOMAU.get(), MaskArmorRenderer::mataKomau);
        AzArmorRendererRegistry.register(NuiCraftItems.MASK_MATA_MAHIKI.get(), MaskArmorRenderer::mataMahiki);
        AzArmorRendererRegistry.register(NuiCraftItems.MASK_MATA_MATATU.get(), MaskArmorRenderer::mataMatatu);
        AzArmorRendererRegistry.register(NuiCraftItems.MASK_MATA_MIRU.get(), MaskArmorRenderer::mataMiru);
        AzArmorRendererRegistry.register(NuiCraftItems.MASK_MATA_PAKARI.get(), MaskArmorRenderer::mataPakari);
        AzArmorRendererRegistry.register(NuiCraftItems.MASK_MATA_RARU.get(), MaskArmorRenderer::mataRaru);
        AzArmorRendererRegistry.register(NuiCraftItems.MASK_MATA_RURU.get(), MaskArmorRenderer::mataRuru);
    }
}
