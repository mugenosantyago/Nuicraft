package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;

/**
 * 3D mask armor renderer using the single Hau/mask model (geo/armor/mask.geo.json)
 * for all Kanohi masks, with a different texture per mask type.
 */
public class MaskArmorRenderer extends AzArmorRenderer {

    private static final ResourceLocation MASK_GEO = ResourceLocation.fromNamespaceAndPath(
            NuiCraft.MODID, "geo/armor/mask.geo.json");

    public MaskArmorRenderer(ResourceLocation texturePath) {
        super(createConfig(MASK_GEO, texturePath));
    }

    private static AzArmorRendererConfig createConfig(ResourceLocation modelLocation, ResourceLocation textureLocation) {
        return AzArmorRendererConfig.builder(modelLocation, textureLocation).build();
    }

    /** Texture path under nuicraft namespace: textures/entity/equipment/humanoid/masks/{name}.png */
    private static ResourceLocation maskTexture(String name) {
        return ResourceLocation.fromNamespaceAndPath(
                NuiCraft.MODID, "textures/entity/equipment/humanoid/masks/" + name + ".png");
    }

    public static MaskArmorRenderer mataHau() {
        return new MaskArmorRenderer(maskTexture("mata_hau"));
    }

    public static MaskArmorRenderer mataHauGold() {
        return new MaskArmorRenderer(maskTexture("mata_hau_gold"));
    }

    public static MaskArmorRenderer mataKakama() {
        return new MaskArmorRenderer(maskTexture("mata_kakama"));
    }

    public static MaskArmorRenderer mataPakari() {
        return new MaskArmorRenderer(maskTexture("mata_pakari"));
    }

    public static MaskArmorRenderer mataKaukau() {
        return new MaskArmorRenderer(maskTexture("mata_kaukau"));
    }

    public static MaskArmorRenderer mataMiru() {
        return new MaskArmorRenderer(maskTexture("mata_miru"));
    }

    public static MaskArmorRenderer mataAkaku() {
        return new MaskArmorRenderer(maskTexture("mata_akaku"));
    }

    public static MaskArmorRenderer nuvaKakama() {
        return new MaskArmorRenderer(maskTexture("nuva_kakama"));
    }

    public static MaskArmorRenderer nuvaPakari() {
        return new MaskArmorRenderer(maskTexture("nuva_pakari"));
    }

    public static MaskArmorRenderer nuvaKaukau() {
        return new MaskArmorRenderer(maskTexture("nuva_kaukau"));
    }

    public static MaskArmorRenderer nuvaMiru() {
        return new MaskArmorRenderer(maskTexture("nuva_miru"));
    }

    public static MaskArmorRenderer nuvaHau() {
        return new MaskArmorRenderer(maskTexture("nuva_hau"));
    }

    public static MaskArmorRenderer nuvaAkaku() {
        return new MaskArmorRenderer(maskTexture("nuva_akaku"));
    }

    public static MaskArmorRenderer ignika() {
        return new MaskArmorRenderer(maskTexture("ignika_0"));
    }

    public static MaskArmorRenderer vahi() {
        return new MaskArmorRenderer(maskTexture("vahi_0"));
    }
}
