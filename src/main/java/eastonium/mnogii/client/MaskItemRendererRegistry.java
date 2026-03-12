package eastonium.mnogii.client;

import eastonium.mnogii.client.renderer.MaskItemRenderer;
import eastonium.mnogii.core.MnogiiItems;
import mod.azure.azurelib.common.render.item.AzItemRendererRegistry;
import net.minecraft.world.item.Item;

/**
 * Registers AzureLib 3D item renderers for all Mata mask items so they display
 * as 3D models both when held in hand and in the inventory GUI.
 *
 * Mirrors the structure of MaskArmorRendererRegistry so item textures always
 * match the worn armor textures.
 */
public class MaskItemRendererRegistry {

    public static void registerAll() {
        // Base 12 Mata masks
        AzItemRendererRegistry.register(MnogiiItems.MASK_MATA_AKAKU.get(),  MaskItemRenderer::mataAkaku);
        AzItemRendererRegistry.register(MnogiiItems.MASK_MATA_HAU.get(),    MaskItemRenderer::mataHau);
        AzItemRendererRegistry.register(MnogiiItems.MASK_MATA_HUNA.get(),   MaskItemRenderer::mataHuna);
        AzItemRendererRegistry.register(MnogiiItems.MASK_MATA_KAKAMA.get(), MaskItemRenderer::mataKakama);
        AzItemRendererRegistry.register(MnogiiItems.MASK_MATA_KAUKAU.get(), MaskItemRenderer::mataKaukau);
        AzItemRendererRegistry.register(MnogiiItems.MASK_MATA_KOMAU.get(),  MaskItemRenderer::mataKomau);
        AzItemRendererRegistry.register(MnogiiItems.MASK_MATA_MAHIKI.get(), MaskItemRenderer::mataMahiki);
        AzItemRendererRegistry.register(MnogiiItems.MASK_MATA_MATATU.get(), MaskItemRenderer::mataMatatu);
        AzItemRendererRegistry.register(MnogiiItems.MASK_MATA_MIRU.get(),   MaskItemRenderer::mataMiru);
        AzItemRendererRegistry.register(MnogiiItems.MASK_MATA_PAKARI.get(), MaskItemRenderer::mataPakari);
        AzItemRendererRegistry.register(MnogiiItems.MASK_MATA_RARU.get(),   MaskItemRenderer::mataRaru);
        AzItemRendererRegistry.register(MnogiiItems.MASK_MATA_RURU.get(),   MaskItemRenderer::mataRuru);

        // Colored variants — same geo, koro-specific texture
        registerColoredVariants("akaku",
            MnogiiItems.MASK_MATA_AKAKU_TA.get(),  MnogiiItems.MASK_MATA_AKAKU_GA.get(),
            MnogiiItems.MASK_MATA_AKAKU_PO.get(),  MnogiiItems.MASK_MATA_AKAKU_KO.get(),
            MnogiiItems.MASK_MATA_AKAKU_LE.get(),  MnogiiItems.MASK_MATA_AKAKU_ONU.get(),
            MnogiiItems.MASK_MATA_AKAKU_PURPLE.get(), MnogiiItems.MASK_MATA_AKAKU_YELLOW.get());
        registerColoredVariants("hau",
            MnogiiItems.MASK_MATA_HAU_TA.get(),    MnogiiItems.MASK_MATA_HAU_GA.get(),
            MnogiiItems.MASK_MATA_HAU_PO.get(),    MnogiiItems.MASK_MATA_HAU_KO.get(),
            MnogiiItems.MASK_MATA_HAU_LE.get(),    MnogiiItems.MASK_MATA_HAU_ONU.get(),
            MnogiiItems.MASK_MATA_HAU_PURPLE.get(), MnogiiItems.MASK_MATA_HAU_YELLOW.get());
        registerColoredVariants("huna",
            MnogiiItems.MASK_MATA_HUNA_TA.get(),   MnogiiItems.MASK_MATA_HUNA_GA.get(),
            MnogiiItems.MASK_MATA_HUNA_PO.get(),   MnogiiItems.MASK_MATA_HUNA_KO.get(),
            MnogiiItems.MASK_MATA_HUNA_LE.get(),   MnogiiItems.MASK_MATA_HUNA_ONU.get(),
            MnogiiItems.MASK_MATA_HUNA_PURPLE.get(), MnogiiItems.MASK_MATA_HUNA_YELLOW.get());
        registerColoredVariants("kakama",
            MnogiiItems.MASK_MATA_KAKAMA_TA.get(), MnogiiItems.MASK_MATA_KAKAMA_GA.get(),
            MnogiiItems.MASK_MATA_KAKAMA_PO.get(), MnogiiItems.MASK_MATA_KAKAMA_KO.get(),
            MnogiiItems.MASK_MATA_KAKAMA_LE.get(), MnogiiItems.MASK_MATA_KAKAMA_ONU.get(),
            MnogiiItems.MASK_MATA_KAKAMA_PURPLE.get(), MnogiiItems.MASK_MATA_KAKAMA_YELLOW.get());
        registerColoredVariants("kaukau",
            MnogiiItems.MASK_MATA_KAUKAU_TA.get(), MnogiiItems.MASK_MATA_KAUKAU_GA.get(),
            MnogiiItems.MASK_MATA_KAUKAU_PO.get(), MnogiiItems.MASK_MATA_KAUKAU_KO.get(),
            MnogiiItems.MASK_MATA_KAUKAU_LE.get(), MnogiiItems.MASK_MATA_KAUKAU_ONU.get(),
            MnogiiItems.MASK_MATA_KAUKAU_PURPLE.get(), MnogiiItems.MASK_MATA_KAUKAU_YELLOW.get());
        registerColoredVariants("komau",
            MnogiiItems.MASK_MATA_KOMAU_TA.get(),  MnogiiItems.MASK_MATA_KOMAU_GA.get(),
            MnogiiItems.MASK_MATA_KOMAU_PO.get(),  MnogiiItems.MASK_MATA_KOMAU_KO.get(),
            MnogiiItems.MASK_MATA_KOMAU_LE.get(),  MnogiiItems.MASK_MATA_KOMAU_ONU.get(),
            MnogiiItems.MASK_MATA_KOMAU_PURPLE.get(), MnogiiItems.MASK_MATA_KOMAU_YELLOW.get());
        registerColoredVariants("mahiki",
            MnogiiItems.MASK_MATA_MAHIKI_TA.get(), MnogiiItems.MASK_MATA_MAHIKI_GA.get(),
            MnogiiItems.MASK_MATA_MAHIKI_PO.get(), MnogiiItems.MASK_MATA_MAHIKI_KO.get(),
            MnogiiItems.MASK_MATA_MAHIKI_LE.get(), MnogiiItems.MASK_MATA_MAHIKI_ONU.get(),
            MnogiiItems.MASK_MATA_MAHIKI_PURPLE.get(), MnogiiItems.MASK_MATA_MAHIKI_YELLOW.get());
        registerColoredVariants("matatu",
            MnogiiItems.MASK_MATA_MATATU_TA.get(), MnogiiItems.MASK_MATA_MATATU_GA.get(),
            MnogiiItems.MASK_MATA_MATATU_PO.get(), MnogiiItems.MASK_MATA_MATATU_KO.get(),
            MnogiiItems.MASK_MATA_MATATU_LE.get(), MnogiiItems.MASK_MATA_MATATU_ONU.get(),
            MnogiiItems.MASK_MATA_MATATU_PURPLE.get(), MnogiiItems.MASK_MATA_MATATU_YELLOW.get());
        registerColoredVariants("miru",
            MnogiiItems.MASK_MATA_MIRU_TA.get(),   MnogiiItems.MASK_MATA_MIRU_GA.get(),
            MnogiiItems.MASK_MATA_MIRU_PO.get(),   MnogiiItems.MASK_MATA_MIRU_KO.get(),
            MnogiiItems.MASK_MATA_MIRU_LE.get(),   MnogiiItems.MASK_MATA_MIRU_ONU.get(),
            MnogiiItems.MASK_MATA_MIRU_PURPLE.get(), MnogiiItems.MASK_MATA_MIRU_YELLOW.get());
        registerColoredVariants("pakari",
            MnogiiItems.MASK_MATA_PAKARI_TA.get(), MnogiiItems.MASK_MATA_PAKARI_GA.get(),
            MnogiiItems.MASK_MATA_PAKARI_PO.get(), MnogiiItems.MASK_MATA_PAKARI_KO.get(),
            MnogiiItems.MASK_MATA_PAKARI_LE.get(), MnogiiItems.MASK_MATA_PAKARI_ONU.get(),
            MnogiiItems.MASK_MATA_PAKARI_PURPLE.get(), MnogiiItems.MASK_MATA_PAKARI_YELLOW.get());
        registerColoredVariants("raru",
            MnogiiItems.MASK_MATA_RARU_TA.get(),   MnogiiItems.MASK_MATA_RARU_GA.get(),
            MnogiiItems.MASK_MATA_RARU_PO.get(),   MnogiiItems.MASK_MATA_RARU_KO.get(),
            MnogiiItems.MASK_MATA_RARU_LE.get(),   MnogiiItems.MASK_MATA_RARU_ONU.get(),
            MnogiiItems.MASK_MATA_RARU_PURPLE.get(), MnogiiItems.MASK_MATA_RARU_YELLOW.get());
        registerColoredVariants("ruru",
            MnogiiItems.MASK_MATA_RURU_TA.get(),   MnogiiItems.MASK_MATA_RURU_GA.get(),
            MnogiiItems.MASK_MATA_RURU_PO.get(),   MnogiiItems.MASK_MATA_RURU_KO.get(),
            MnogiiItems.MASK_MATA_RURU_LE.get(),   MnogiiItems.MASK_MATA_RURU_ONU.get(),
            MnogiiItems.MASK_MATA_RURU_PURPLE.get(), MnogiiItems.MASK_MATA_RURU_YELLOW.get());
    }

    private static final String[] KORO_ORDER = {"ta", "ga", "po", "ko", "le", "onu", "purple", "yellow"};

    private static void registerColoredVariants(String maskType, Item... items) {
        for (int i = 0; i < items.length; i++) {
            final String koro = i < KORO_ORDER.length ? KORO_ORDER[i] : "ta";
            AzItemRendererRegistry.register(items[i], () -> MaskItemRenderer.ofTypeAndKoro(maskType, koro));
        }
    }
}
