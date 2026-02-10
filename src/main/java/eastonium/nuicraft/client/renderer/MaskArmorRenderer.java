package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;

/**
 * 3D mask armor renderer using individual 3D models for each Mata mask.
 * Each mask has its own geo.json model converted from the OBJ files.
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

    public static MaskArmorRenderer mataHau() {
        return new MaskArmorRenderer(maskGeo("hau"), maskTexture("mata_hau"));
    }

    public static MaskArmorRenderer mataKakama() {
        return new MaskArmorRenderer(maskGeo("kakama"), maskTexture("mata_kakama"));
    }

    public static MaskArmorRenderer mataPakari() {
        return new MaskArmorRenderer(maskGeo("pakari"), maskTexture("mata_pakari"));
    }

    public static MaskArmorRenderer mataKaukau() {
        return new MaskArmorRenderer(maskGeo("kaukau"), maskTexture("mata_kaukau"));
    }

    public static MaskArmorRenderer mataMiru() {
        return new MaskArmorRenderer(maskGeo("miru"), maskTexture("mata_miru"));
    }

    public static MaskArmorRenderer mataAkaku() {
        return new MaskArmorRenderer(maskGeo("akaku"), maskTexture("mata_akaku"));
    }
}
