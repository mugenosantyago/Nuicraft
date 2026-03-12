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

    // Factory methods for each mask — use the Ta (red) 64x64 texture as the default
    // since mask_mata_*.png are 16x16 item icons, not 3D UV maps.
    public static MaskArmorRenderer mataAkaku()  { return ofTypeAndKoro("akaku",  "ta"); }
    public static MaskArmorRenderer mataHau()     { return ofTypeAndKoro("hau",    "ta"); }
    public static MaskArmorRenderer mataHuna()    { return ofTypeAndKoro("huna",   "ta"); }
    public static MaskArmorRenderer mataKakama()  { return ofTypeAndKoro("kakama", "ta"); }
    public static MaskArmorRenderer mataKaukau()   { return ofTypeAndKoro("kaukau", "ta"); }
    public static MaskArmorRenderer mataKomau()   { return ofTypeAndKoro("komau",  "ta"); }
    public static MaskArmorRenderer mataMahiki()  { return ofTypeAndKoro("mahiki", "ta"); }
    public static MaskArmorRenderer mataMatatu()  { return ofTypeAndKoro("matatu", "ta"); }
    public static MaskArmorRenderer mataMiru()    { return ofTypeAndKoro("miru",   "ta"); }
    public static MaskArmorRenderer mataPakari()  { return ofTypeAndKoro("pakari", "ta"); }
    public static MaskArmorRenderer mataRaru()    { return ofTypeAndKoro("raru",   "ta"); }
    public static MaskArmorRenderer mataRuru()    { return ofTypeAndKoro("ruru",   "ta"); }

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

    /**
     * Creates a renderer for a koro-colored variant.
     * Picks the colored texture ({color}_{mask}_mask.png) when available,
     * falling back to the base mask_mata_{maskType}.png otherwise.
     * All paths are fully lowercase to satisfy ResourceLocation requirements.
     */
    public static MaskArmorRenderer ofTypeAndKoro(String maskType, String koro) {
        String color = koroToColor(koro);
        // e.g. "red_kaukau_mask.png"
        String coloredTexture = color != null
            ? "textures/armor/" + color + "_" + maskType + "_mask.png"
            : "textures/armor/mask_mata_" + maskType + ".png";
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/armor/" + maskType + ".geo.json"),
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, coloredTexture)
        );
    }

    /**
     * Creates a renderer for a mask type using the default (Ta/red) colored texture.
     */
    public static MaskArmorRenderer ofType(String maskType) {
        return ofTypeAndKoro(maskType, "ta");
    }
}
