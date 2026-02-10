package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
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
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "geo/armor/akaku.geo.json"),
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/armor/mask_mata_akaku.png")
        );
    }

    public static MaskArmorRenderer mataHau() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "geo/armor/hau.geo.json"),
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/armor/mask_mata_hau.png")
        );
    }

    public static MaskArmorRenderer mataHuna() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "geo/armor/huna.geo.json"),
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/armor/mask_mata_huna.png")
        );
    }

    public static MaskArmorRenderer mataKakama() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "geo/armor/kakama.geo.json"),
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/armor/mask_mata_kakama.png")
        );
    }

    public static MaskArmorRenderer mataKaukau() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "geo/armor/kaukau.geo.json"),
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/armor/mask_mata_kaukau.png")
        );
    }

    public static MaskArmorRenderer mataKomau() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "geo/armor/komau.geo.json"),
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/armor/mask_mata_komau.png")
        );
    }

    public static MaskArmorRenderer mataMahiki() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "geo/armor/mahiki.geo.json"),
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/armor/mask_mata_mahiki.png")
        );
    }

    public static MaskArmorRenderer mataMatatu() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "geo/armor/matatu.geo.json"),
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/armor/mask_mata_matatu.png")
        );
    }

    public static MaskArmorRenderer mataMiru() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "geo/armor/miru.geo.json"),
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/armor/mask_mata_miru.png")
        );
    }

    public static MaskArmorRenderer mataPakari() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "geo/armor/pakari.geo.json"),
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/armor/mask_mata_pakari.png")
        );
    }

    public static MaskArmorRenderer mataRaru() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "geo/armor/raru.geo.json"),
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/armor/mask_mata_raru.png")
        );
    }

    public static MaskArmorRenderer mataRuru() {
        return new MaskArmorRenderer(
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "geo/armor/ruru.geo.json"),
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/armor/mask_mata_ruru.png")
        );
    }
}
