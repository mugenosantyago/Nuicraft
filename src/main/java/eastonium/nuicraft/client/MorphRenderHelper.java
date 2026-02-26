package eastonium.nuicraft.client;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.morph.MorphState;
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
     * Returns null if the item is not a NuiCraft mask.
     */
    @Nullable
    public static String getMaskId(ItemStack stack) {
        if (stack.isEmpty()) return null;
        ResourceLocation key = BuiltInRegistries.ITEM.getKey(stack.getItem());
        if (key == null || !key.getNamespace().equals(NuiCraft.MODID)) return null;
        String path = key.getPath();                        // e.g. "mask_mata_hau"
        if (!path.startsWith("mask_mata_")) return null;
        String maskId = path.substring("mask_mata_".length()); // e.g. "hau"
        return MorphState.isImplemented(maskId) ? maskId : null;
    }

    /** Geo file for the given morph state and mask.  Toa falls back to matoran until Toa models arrive. */
    public static ResourceLocation geoLocation(MorphState state, String maskId) {
        // Both MATORAN and TOA use the matoran body geo for now
        return ResourceLocation.fromNamespaceAndPath(
                NuiCraft.MODID, "geo/entity/" + maskId + "_matoran.geo.json");
    }

    /** Texture for the given morph state, mask and koro color. */
    public static ResourceLocation textureLocation(MorphState state, String maskId) {
        String koro = MorphState.canonicalKoroFor(maskId);
        return ResourceLocation.fromNamespaceAndPath(
                NuiCraft.MODID, "textures/entity/" + maskId + "_matoran/" + koro + ".png");
    }

    /** Animation file for the given morph state and mask. */
    public static ResourceLocation animationLocation(String maskId) {
        return ResourceLocation.fromNamespaceAndPath(
                NuiCraft.MODID, "animations/entity/" + maskId + "_matoran.animation.json");
    }
}
