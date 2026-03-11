package eastonium.mnogii.client.renderer;

import eastonium.mnogii.Mnogii;
import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;

/**
 * 3D mask armor renderer using AzureLib.
 * Renders masks as 3D voxel models on the player's face.
 */
public class MaskArmorRenderer extends AzArmorRenderer {

    public MaskArmorRenderer(ResourceLocation modelPath, ResourceLocation texturePath) {
        super(createConfig(modelPath, texturePath));
    }

    private static AzArmorRendererConfig createConfig(ResourceLocation modelLocation, ResourceLocation textureLocation) {
        return AzArmorRendererConfig.builder(modelLocation, textureLocation).build();
    }

    // Factory methods for each mask
    public static MaskArmorRenderer mataAkaku() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/akaku.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_akaku.png")
        );
    }

    public static MaskArmorRenderer mataHau() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/hau.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_hau.png")
        );
    }

    public static MaskArmorRenderer mataHuna() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/huna.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_huna.png")
        );
    }

    public static MaskArmorRenderer mataKakama() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/kakama.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_kakama.png")
        );
    }

    public static MaskArmorRenderer mataKaukau() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/kaukau.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_kaukau.png")
        );
    }

    public static MaskArmorRenderer mataKomau() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/komau.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_komau.png")
        );
    }

    public static MaskArmorRenderer mataMahiki() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/mahiki.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_mahiki.png")
        );
    }

    public static MaskArmorRenderer mataMatatu() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/matatu.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_matatu.png")
        );
    }

    public static MaskArmorRenderer mataMiru() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/miru.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_miru.png")
        );
    }

    public static MaskArmorRenderer mataPakari() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/pakari.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_pakari.png")
        );
    }

    public static MaskArmorRenderer mataRaru() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/raru.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_raru.png")
        );
    }

    public static MaskArmorRenderer mataRuru() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/ruru.geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_ruru.png")
        );
    }

    /**
     * Maps Koro village names to the color prefix used in mask texture filenames.
     * e.g. "ta" → "RED", "ga" → "BLUE"
     */
    private static String koroToColor(String koro) {
        return switch (koro) {
            case "ta"     -> "RED";
            case "ga"     -> "BLUE";
            case "po"     -> "BROWN";
            case "ko"     -> "WHITE";
            case "le"     -> "GREEN";
            case "onu"    -> "BLACK";
            case "purple" -> "PURPLE";
            case "yellow" -> "YELLOW";
            default       -> null;
        };
    }

    /**
     * Creates a renderer for a koro-colored variant.
     * Picks the colored texture ({COLOR}_{MASK}_mask.png) when available,
     * falling back to the base mask_mata_{maskType}.png otherwise.
     */
    public static MaskArmorRenderer ofTypeAndKoro(String maskType, String koro) {
        String color = koroToColor(koro);
        // Build the colored texture name, e.g. "RED_HAU_mask.png"
        // Note: maskType is lowercase (e.g. "hau"), texture uses UPPERCASE
        String upperMask = maskType.toUpperCase();
        String coloredTexture = color != null
            ? "textures/armor/" + color + "_" + upperMask + "_mask.png"
            : "textures/armor/mask_mata_" + maskType + ".png";
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/" + maskType + ".geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, coloredTexture)
        );
    }

    /**
     * Creates a renderer for a colored variant (legacy — uses base texture).
     */
    public static MaskArmorRenderer ofType(String maskType) {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/" + maskType + ".geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/armor/mask_mata_" + maskType + ".png")
        );
    }
}
