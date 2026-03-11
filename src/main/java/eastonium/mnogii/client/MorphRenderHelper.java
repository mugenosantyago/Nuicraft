package eastonium.mnogii.client;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.morph.MorphState;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * Shared helpers for determining which geo/texture to use for a morphed player.
 */
public final class MorphRenderHelper {

    private MorphRenderHelper() {}

    /**
     * Extracts the mask ID ("hau", "kaukau", …) from the item's registry name.
     * Returns null if the item is not a Mnogii mask.
     */
    @Nullable
    public static String getMaskId(ItemStack stack) {
        if (stack.isEmpty()) return null;
        ResourceLocation key = BuiltInRegistries.ITEM.getKey(stack.getItem());
        if (key == null || !key.getNamespace().equals(Mnogii.MODID)) return null;
        String path = key.getPath();                        // e.g. "mask_mata_hau"
        if (!path.startsWith("mask_mata_")) return null;
        String maskId = path.substring("mask_mata_".length()); // e.g. "hau"
        return MorphState.isImplemented(maskId) ? maskId : null;
    }

    /** Geo file for the given morph state and mask. */
    public static ResourceLocation geoLocation(MorphState state, String maskId) {
        if (state == MorphState.TOA) {
            String toaName = MorphState.toaNameFor(maskId);
            if (toaName != null) {
                return ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "geo/entity/" + toaName + ".geo.json");
            }
        }
        // Matoran form (or Toa fallback if no Toa model for this mask)
        String effectiveMask = MorphState.hasMatoranModel(maskId) ? maskId : "hau";
        return ResourceLocation.fromNamespaceAndPath(
                Mnogii.MODID, "geo/entity/" + effectiveMask + "_matoran.geo.json");
    }

    /** Texture for the given morph state and mask. */
    public static ResourceLocation textureLocation(MorphState state, String maskId) {
        if (state == MorphState.TOA) {
            String toaName = MorphState.toaNameFor(maskId);
            if (toaName != null) {
                return ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/entity/" + toaName + ".png");
            }
        }
        // Matoran form
        String effectiveMask = MorphState.hasMatoranModel(maskId) ? maskId : "hau";
        String koro = MorphState.canonicalKoroFor(effectiveMask);
        return ResourceLocation.fromNamespaceAndPath(
                Mnogii.MODID, "textures/entity/" + effectiveMask + "_matoran/" + koro + ".png");
    }

    /** Animation file for the given morph state and mask. */
    public static ResourceLocation animationLocation(MorphState state, String maskId) {
        if (state == MorphState.TOA) {
            String toaName = MorphState.toaNameFor(maskId);
            if (toaName != null) {
                return ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "animations/entity/" + toaName + ".animation.json");
            }
        }
        String effectiveMask = MorphState.hasMatoranModel(maskId) ? maskId : "hau";
        return ResourceLocation.fromNamespaceAndPath(
                Mnogii.MODID, "animations/entity/" + effectiveMask + "_matoran.animation.json");
    }
}
