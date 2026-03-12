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

    private static final float SCALE_HAU     = 1.05f;
    private static final float SCALE_KAKAMA  = 1.0f;
    private static final float SCALE_KAUKAU  = 1.0f;
    private static final float SCALE_PAKARI  = 0.92f;
    private static final float SCALE_RARU    = 0.92f;
    private static final float SCALE_MAHIKI  = 0.85f;
    private static final float SCALE_HUNA    = 0.90f;
    private static final float SCALE_MATATU  = 0.90f;
    private static final float SCALE_RURU    = 0.82f;
    private static final float SCALE_MIRU    = 1.08f;

    public MaskArmorRenderer(ResourceLocation modelPath, ResourceLocation texturePath, float scale) {
        super(createConfig(modelPath, texturePath, scale));
    }

    public MaskArmorRenderer(ResourceLocation modelPath, ResourceLocation texturePath) {
        this(modelPath, texturePath, 1.0f);
    }

    private static AzArmorRendererConfig createConfig(ResourceLocation modelLocation, ResourceLocation textureLocation, float scale) {
        var builder = AzArmorRendererConfig.builder(modelLocation, textureLocation);
        if (scale != 1.0f) {
            builder.setScale(scale);
        }
        return builder.build();
    }

    public static MaskArmorRenderer mataHau()     { return ofTypeKoroScale("hau",    "ta", SCALE_HAU); }
    public static MaskArmorRenderer mataHuna()    { return ofTypeKoroScale("huna",   "ta", SCALE_HUNA); }
    public static MaskArmorRenderer mataKakama()  { return ofTypeKoroScale("kakama", "ta", SCALE_KAKAMA); }
    public static MaskArmorRenderer mataKaukau()   { return ofTypeKoroScale("kaukau", "ta", SCALE_KAUKAU); }
    public static MaskArmorRenderer mataMahiki()  { return ofTypeKoroScale("mahiki", "ta", SCALE_MAHIKI); }
    public static MaskArmorRenderer mataMatatu()  { return ofTypeKoroScale("matatu", "ta", SCALE_MATATU); }
    public static MaskArmorRenderer mataMiru()    { return ofTypeKoroScale("miru",   "ta", SCALE_MIRU); }
    public static MaskArmorRenderer mataPakari()  { return ofTypeKoroScale("pakari", "ta", SCALE_PAKARI); }
    public static MaskArmorRenderer mataRaru()    { return ofTypeKoroScale("raru",   "ta", SCALE_RARU); }
    public static MaskArmorRenderer mataRuru()    { return ofTypeKoroScale("ruru",   "ta", SCALE_RURU); }

    /**
     * Maps Koro village names to the lowercase color prefix used in mask texture filenames.
     * e.g. "ta" → "red", "ga" → "blue"
     * ResourceLocation paths must be lowercase [a-z0-9/._-].
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

    private static float scaleForMask(String maskType) {
        return switch (maskType) {
            case "pakari" -> SCALE_PAKARI;
            case "raru"   -> SCALE_RARU;
            case "mahiki" -> SCALE_MAHIKI;
            case "huna"   -> SCALE_HUNA;
            case "matatu" -> SCALE_MATATU;
            case "ruru"   -> SCALE_RURU;
            case "miru"   -> SCALE_MIRU;
            case "kakama" -> SCALE_KAKAMA;
            case "kaukau" -> SCALE_KAUKAU;
            case "hau"    -> SCALE_HAU;
            default       -> 1.0f;
        };
    }

    /**
     * Creates a renderer for a koro-colored variant with auto-detected scale.
     */
    public static MaskArmorRenderer ofTypeAndKoro(String maskType, String koro) {
        return ofTypeKoroScale(maskType, koro, scaleForMask(maskType));
    }

    /**
     * Creates a renderer for a koro-colored variant with an explicit scale.
     */
    public static MaskArmorRenderer ofTypeKoroScale(String maskType, String koro, float scale) {
        String color = koroToColor(koro);
        String coloredTexture = color != null
            ? "textures/armor/" + color + "_" + maskType + "_mask.png"
            : "textures/armor/mask_mata_" + maskType + ".png";
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/" + maskType + ".geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, coloredTexture),
            scale
        );
    }

    /**
     * Creates a renderer for a mask type using the default (Ta/red) colored texture.
     */
    public static MaskArmorRenderer ofType(String maskType) {
        return ofTypeAndKoro(maskType, "ta");
    }
}
