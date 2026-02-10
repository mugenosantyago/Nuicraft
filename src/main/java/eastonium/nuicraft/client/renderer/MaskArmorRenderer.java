package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;

/**
 * 3D mask armor renderer using individual 3D models for each Mata mask.
 * Each mask has its own geo.json model.
 */
public class MaskArmorRenderer extends AzArmorRenderer {

    public MaskArmorRenderer(ResourceLocation modelPath, ResourceLocation texturePath) {
        super(createConfig(modelPath, texturePath));
    }

    private static AzArmorRendererConfig createConfig(ResourceLocation modelLocation, ResourceLocation textureLocation) {
        return AzArmorRendererConfig.builder(modelLocation, textureLocation).build();
    }

    private static ResourceLocation maskGeo(String name) {
        return ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "geo/armor/" + name + ".geo.json");
    }

    /** Texture path under nuicraft namespace: textures/entity/equipment/humanoid/masks/{name}.png */
    private static ResourceLocation maskTexture(String name) {
        return ResourceLocation.fromNamespaceAndPath(
                NuiCraft.MODID, "textures/entity/equipment/humanoid/masks/" + name + ".png");
    }

    public static MaskArmorRenderer mataAkaku() {
        return new MaskArmorRenderer(maskGeo("akaku"), maskTexture("mata_akaku"));
    }

    public static MaskArmorRenderer mataHau() {
        return new MaskArmorRenderer(maskGeo("hau"), maskTexture("mata_hau"));
    }

    public static MaskArmorRenderer mataHuna() {
        return new MaskArmorRenderer(maskGeo("huna"), maskTexture("mata_huna"));
    }

    public static MaskArmorRenderer mataKakama() {
        return new MaskArmorRenderer(maskGeo("kakama"), maskTexture("mata_kakama"));
    }

    public static MaskArmorRenderer mataKaukau() {
        return new MaskArmorRenderer(maskGeo("kaukau"), maskTexture("mata_kaukau"));
    }

    public static MaskArmorRenderer mataKomau() {
        return new MaskArmorRenderer(maskGeo("komau"), maskTexture("mata_komau"));
    }

    public static MaskArmorRenderer mataMahiki() {
        return new MaskArmorRenderer(maskGeo("mahiki"), maskTexture("mata_mahiki"));
    }

    public static MaskArmorRenderer mataMatatu() {
        return new MaskArmorRenderer(maskGeo("matatu"), maskTexture("mata_matatu"));
    }

    public static MaskArmorRenderer mataMiru() {
        return new MaskArmorRenderer(maskGeo("miru"), maskTexture("mata_miru"));
    }

    public static MaskArmorRenderer mataPakari() {
        return new MaskArmorRenderer(maskGeo("pakari"), maskTexture("mata_pakari"));
    }

    public static MaskArmorRenderer mataRaru() {
        return new MaskArmorRenderer(maskGeo("raru"), maskTexture("mata_raru"));
    }

    public static MaskArmorRenderer mataRuru() {
        return new MaskArmorRenderer(maskGeo("ruru"), maskTexture("mata_ruru"));
    }

    // Toa masks (colored variants)
    public static MaskArmorRenderer toaHauRed() {
        return new MaskArmorRenderer(maskGeo("hau"), maskTexture("toa_hau_red"));
    }

    public static MaskArmorRenderer toaKaukauBlue() {
        return new MaskArmorRenderer(maskGeo("kaukau"), maskTexture("toa_kaukau_blue"));
    }

    public static MaskArmorRenderer toaKakamaBrown() {
        return new MaskArmorRenderer(maskGeo("kakama"), maskTexture("toa_kakama_brown"));
    }

    public static MaskArmorRenderer toaMiruGreen() {
        return new MaskArmorRenderer(maskGeo("miru"), maskTexture("toa_miru_green"));
    }

    public static MaskArmorRenderer toaPakariBlack() {
        return new MaskArmorRenderer(maskGeo("pakari"), maskTexture("toa_pakari_black"));
    }

    public static MaskArmorRenderer toaAkakuWhite() {
        return new MaskArmorRenderer(maskGeo("akaku"), maskTexture("toa_akaku_white"));
    }
}

