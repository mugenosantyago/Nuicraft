package eastonium.mnogii.client;

import eastonium.mnogii.client.renderer.MaskArmorRenderer;
import eastonium.mnogii.core.MnogiiItems;
import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Nuicraft-owned registry mapping mask {@link Item}s to their {@link AzArmorRenderer} factories.
 *
 * <p>This deliberately does NOT call {@code AzArmorRendererRegistry.register()} so that
 * AzureLib's {@code MixinHumanoidArmorLayer} never tries to handle masks. All 3D worn
 * rendering is handled by {@link eastonium.mnogii.client.renderer.MaskRenderLayer}, which
 * runs unconditionally as a vanilla {@code RenderLayer} and works in both standalone and
 * modpack environments.
 */
public class MaskArmorRendererRegistry {

    private static final Map<Item, Supplier<AzArmorRenderer>> SUPPLIERS = new HashMap<>();
    private static final Map<Item, AzArmorRenderer>          CACHE     = new HashMap<>();

    /** Called from {@code MnogiiClient.clientSetup()} on the client thread. */
    public static void registerAll() {
        // Base Mata masks (akaku and komau removed)
        register(MnogiiItems.MASK_MATA_HAU.get(),     MaskArmorRenderer::mataHau);
        register(MnogiiItems.MASK_MATA_HUNA.get(),    MaskArmorRenderer::mataHuna);
        register(MnogiiItems.MASK_MATA_KAKAMA.get(),  MaskArmorRenderer::mataKakama);
        register(MnogiiItems.MASK_MATA_KAUKAU.get(),  MaskArmorRenderer::mataKaukau);
        register(MnogiiItems.MASK_MATA_MAHIKI.get(),  MaskArmorRenderer::mataMahiki);
        register(MnogiiItems.MASK_MATA_MATATU.get(),  MaskArmorRenderer::mataMatatu);
        register(MnogiiItems.MASK_MATA_MIRU.get(),    MaskArmorRenderer::mataMiru);
        register(MnogiiItems.MASK_MATA_PAKARI.get(),  MaskArmorRenderer::mataPakari);
        register(MnogiiItems.MASK_MATA_RARU.get(),    MaskArmorRenderer::mataRaru);
        register(MnogiiItems.MASK_MATA_RURU.get(),    MaskArmorRenderer::mataRuru);

        // Colored variants (akaku and komau removed)
        registerColoredVariants("hau",
            MnogiiItems.MASK_MATA_HAU_TA.get(),     MnogiiItems.MASK_MATA_HAU_GA.get(),
            MnogiiItems.MASK_MATA_HAU_PO.get(),     MnogiiItems.MASK_MATA_HAU_KO.get(),
            MnogiiItems.MASK_MATA_HAU_LE.get(),     MnogiiItems.MASK_MATA_HAU_ONU.get(),
            MnogiiItems.MASK_MATA_HAU_PURPLE.get(), MnogiiItems.MASK_MATA_HAU_YELLOW.get());
        registerColoredVariants("huna",
            MnogiiItems.MASK_MATA_HUNA_TA.get(),     MnogiiItems.MASK_MATA_HUNA_GA.get(),
            MnogiiItems.MASK_MATA_HUNA_PO.get(),     MnogiiItems.MASK_MATA_HUNA_KO.get(),
            MnogiiItems.MASK_MATA_HUNA_LE.get(),     MnogiiItems.MASK_MATA_HUNA_ONU.get(),
            MnogiiItems.MASK_MATA_HUNA_PURPLE.get(), MnogiiItems.MASK_MATA_HUNA_YELLOW.get());
        registerColoredVariants("kakama",
            MnogiiItems.MASK_MATA_KAKAMA_TA.get(),     MnogiiItems.MASK_MATA_KAKAMA_GA.get(),
            MnogiiItems.MASK_MATA_KAKAMA_PO.get(),     MnogiiItems.MASK_MATA_KAKAMA_KO.get(),
            MnogiiItems.MASK_MATA_KAKAMA_LE.get(),     MnogiiItems.MASK_MATA_KAKAMA_ONU.get(),
            MnogiiItems.MASK_MATA_KAKAMA_PURPLE.get(), MnogiiItems.MASK_MATA_KAKAMA_YELLOW.get());
        registerColoredVariants("kaukau",
            MnogiiItems.MASK_MATA_KAUKAU_TA.get(),     MnogiiItems.MASK_MATA_KAUKAU_GA.get(),
            MnogiiItems.MASK_MATA_KAUKAU_PO.get(),     MnogiiItems.MASK_MATA_KAUKAU_KO.get(),
            MnogiiItems.MASK_MATA_KAUKAU_LE.get(),     MnogiiItems.MASK_MATA_KAUKAU_ONU.get(),
            MnogiiItems.MASK_MATA_KAUKAU_PURPLE.get(), MnogiiItems.MASK_MATA_KAUKAU_YELLOW.get());
        registerColoredVariants("mahiki",
            MnogiiItems.MASK_MATA_MAHIKI_TA.get(),     MnogiiItems.MASK_MATA_MAHIKI_GA.get(),
            MnogiiItems.MASK_MATA_MAHIKI_PO.get(),     MnogiiItems.MASK_MATA_MAHIKI_KO.get(),
            MnogiiItems.MASK_MATA_MAHIKI_LE.get(),     MnogiiItems.MASK_MATA_MAHIKI_ONU.get(),
            MnogiiItems.MASK_MATA_MAHIKI_PURPLE.get(), MnogiiItems.MASK_MATA_MAHIKI_YELLOW.get());
        registerColoredVariants("matatu",
            MnogiiItems.MASK_MATA_MATATU_TA.get(),     MnogiiItems.MASK_MATA_MATATU_GA.get(),
            MnogiiItems.MASK_MATA_MATATU_PO.get(),     MnogiiItems.MASK_MATA_MATATU_KO.get(),
            MnogiiItems.MASK_MATA_MATATU_LE.get(),     MnogiiItems.MASK_MATA_MATATU_ONU.get(),
            MnogiiItems.MASK_MATA_MATATU_PURPLE.get(), MnogiiItems.MASK_MATA_MATATU_YELLOW.get());
        registerColoredVariants("miru",
            MnogiiItems.MASK_MATA_MIRU_TA.get(),     MnogiiItems.MASK_MATA_MIRU_GA.get(),
            MnogiiItems.MASK_MATA_MIRU_PO.get(),     MnogiiItems.MASK_MATA_MIRU_KO.get(),
            MnogiiItems.MASK_MATA_MIRU_LE.get(),     MnogiiItems.MASK_MATA_MIRU_ONU.get(),
            MnogiiItems.MASK_MATA_MIRU_PURPLE.get(), MnogiiItems.MASK_MATA_MIRU_YELLOW.get());
        registerColoredVariants("pakari",
            MnogiiItems.MASK_MATA_PAKARI_TA.get(),     MnogiiItems.MASK_MATA_PAKARI_GA.get(),
            MnogiiItems.MASK_MATA_PAKARI_PO.get(),     MnogiiItems.MASK_MATA_PAKARI_KO.get(),
            MnogiiItems.MASK_MATA_PAKARI_LE.get(),     MnogiiItems.MASK_MATA_PAKARI_ONU.get(),
            MnogiiItems.MASK_MATA_PAKARI_PURPLE.get(), MnogiiItems.MASK_MATA_PAKARI_YELLOW.get());
        registerColoredVariants("raru",
            MnogiiItems.MASK_MATA_RARU_TA.get(),     MnogiiItems.MASK_MATA_RARU_GA.get(),
            MnogiiItems.MASK_MATA_RARU_PO.get(),     MnogiiItems.MASK_MATA_RARU_KO.get(),
            MnogiiItems.MASK_MATA_RARU_LE.get(),     MnogiiItems.MASK_MATA_RARU_ONU.get(),
            MnogiiItems.MASK_MATA_RARU_PURPLE.get(), MnogiiItems.MASK_MATA_RARU_YELLOW.get());
        registerColoredVariants("ruru",
            MnogiiItems.MASK_MATA_RURU_TA.get(),     MnogiiItems.MASK_MATA_RURU_GA.get(),
            MnogiiItems.MASK_MATA_RURU_PO.get(),     MnogiiItems.MASK_MATA_RURU_KO.get(),
            MnogiiItems.MASK_MATA_RURU_LE.get(),     MnogiiItems.MASK_MATA_RURU_ONU.get(),
            MnogiiItems.MASK_MATA_RURU_PURPLE.get(), MnogiiItems.MASK_MATA_RURU_YELLOW.get());
    }

    /**
     * Returns the cached renderer for {@code item}, creating it on first use.
     * Returns {@code null} if the item is not a registered mask.
     */
    public static AzArmorRenderer getOrCreate(Item item) {
        if (!SUPPLIERS.containsKey(item)) return null;
        return CACHE.computeIfAbsent(item, k -> SUPPLIERS.get(k).get());
    }

    // -------------------------------------------------------------------------
    // Internal helpers
    // -------------------------------------------------------------------------

    private static void register(Item item, Supplier<AzArmorRenderer> supplier) {
        SUPPLIERS.put(item, supplier);
    }

    /** Koro names in the fixed registration order: ta, ga, po, ko, le, onu, purple, yellow */
    private static final String[] KORO_ORDER = {"ta", "ga", "po", "ko", "le", "onu", "purple", "yellow"};

    private static void registerColoredVariants(String maskType, Item... items) {
        for (int i = 0; i < items.length; i++) {
            final String koro = i < KORO_ORDER.length ? KORO_ORDER[i] : "ta";
            register(items[i], () -> MaskArmorRenderer.ofTypeAndKoro(maskType, koro));
        }
    }
}
