package eastonium.mnogii.client.renderer;

import eastonium.mnogii.Mnogii;
import mod.azure.azurelib.common.render.item.AzItemRenderer;
import mod.azure.azurelib.common.render.item.AzItemRendererConfig;
import net.minecraft.resources.ResourceLocation;

/**
 * 3D mask item renderer using AzureLib.
 * Renders masks as 3D voxel models when held in hand and in inventory.
 * Uses the same geo/armor models as MaskArmorRenderer so the item
 * looks identical whether worn or held.
 */
public class MaskItemRenderer extends AzItemRenderer {

    public MaskItemRenderer(ResourceLocation modelPath, ResourceLocation texturePath) {
        super(createConfig(modelPath, texturePath));
    }

    private static AzItemRendererConfig createConfig(ResourceLocation modelLocation, ResourceLocation textureLocation) {
        return AzItemRendererConfig.builder(modelLocation, textureLocation)
                .useNewOffset(true)
                .build();
    }

    // Factory methods mirroring MaskArmorRenderer — one per base mask type

    public static MaskItemRenderer mataAkaku() {
        return new MaskItemRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/akaku.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_akaku.png")
        );
    }

    public static MaskItemRenderer mataHau() {
        return new MaskItemRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/hau.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_hau.png")
        );
    }

    public static MaskItemRenderer mataHuna() {
        return new MaskItemRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/huna.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_huna.png")
        );
    }

    public static MaskItemRenderer mataKakama() {
        return new MaskItemRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/kakama.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_kakama.png")
        );
    }

    public static MaskItemRenderer mataKaukau() {
        return new MaskItemRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/kaukau.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_kaukau.png")
        );
    }

    public static MaskItemRenderer mataKomau() {
        return new MaskItemRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/komau.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_komau.png")
        );
    }

    public static MaskItemRenderer mataMahiki() {
        return new MaskItemRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/mahiki.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_mahiki.png")
        );
    }

    public static MaskItemRenderer mataMatatu() {
        return new MaskItemRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/matatu.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_matatu.png")
        );
    }

    public static MaskItemRenderer mataMiru() {
        return new MaskItemRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/miru.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_miru.png")
        );
    }

    public static MaskItemRenderer mataPakari() {
        return new MaskItemRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/pakari.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_pakari.png")
        );
    }

    public static MaskItemRenderer mataRaru() {
        return new MaskItemRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/raru.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_raru.png")
        );
    }

    public static MaskItemRenderer mataRuru() {
        return new MaskItemRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/ruru.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_ruru.png")
        );
    }

    /**
     * Maps Koro village names to the lowercase color prefix used in mask texture filenames.
     * Mirrors the mapping in MaskArmorRenderer to ensure held/worn textures match.
     */
    private static String koroToColor(String koro) {
        return switch (koro) {
            case "ta"     -> "red";
            case "ga"     -> "blue";
            case "po"     -> "brown";
            case "ko"     -> "white";
            case "le"     -> "green";
            case "onu"    -> "black";
            case "purple" -> "purple";
            case "yellow" -> "yellow";
            default       -> null;
        };
    }

    /**
     * Creates a renderer for a koro-colored variant.
     * Matches the texture selection logic in MaskArmorRenderer.ofTypeAndKoro().
     */
    public static MaskItemRenderer ofTypeAndKoro(String maskType, String koro) {
        String color = koroToColor(koro);
        String coloredTexture = color != null
            ? "textures/armor/" + color + "_" + maskType + "_mask.png"
            : "textures/armor/mask_mata_" + maskType + ".png";
        return new MaskItemRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/" + maskType + ".geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, coloredTexture)
        );
    }
}
