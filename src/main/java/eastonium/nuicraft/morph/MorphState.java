package eastonium.nuicraft.morph;

import com.mojang.serialization.Codec;

/**
 * Tracks whether the player is in their normal form, matoran form, or toa form.
 * Stored server-side via NuiCraftAttachments and synced to nearby clients.
 *
 * Keys (when wearing a mask):
 *   M → toggle MATORAN form (uses the worn mask's body geo + koro color texture)
 *   T → toggle TOA    form  (toa geo — falls back to matoran until Toa models are delivered)
 */
public enum MorphState {
    NONE,
    MATORAN,
    TOA;

    public static final com.mojang.serialization.Codec<MorphState> CODEC = com.mojang.serialization.Codec.BYTE.xmap(
            b -> (b >= 0 && b < values().length) ? values()[b] : NONE,
            m -> (byte) m.ordinal()
    );
    /** MapCodec variant for use with AttachmentType.serialize(MapCodec). */
    public static final com.mojang.serialization.MapCodec<MorphState> MAP_CODEC = CODEC.fieldOf("value");

    /** Canonical koro color (lower-case) for each implemented mask. */
    public static String canonicalKoroFor(String maskId) {
        return switch (maskId) {
            case "hau"    -> "ta";
            case "huna"   -> "ga";
            case "miru"   -> "le";
            case "pakari" -> "onu";
            case "kaukau" -> "ko";
            case "kakama" -> "po";
            default       -> "ta";
        };
    }

    /**
     * Maps a worn mask to the corresponding Toa entity name.
     * Uses canonical Bionicle lore (HAU=Tahu, KAUKAU=Gali, MIRU=Lewa,
     * PAKARI=Onua, KAKAMA=Pohatu, AKAKU=Kopaka).
     * Returns null if no Toa corresponds to this mask.
     */
    public static String toaNameFor(String maskId) {
        return switch (maskId) {
            case "hau"    -> "toa_tahu";
            case "kaukau" -> "toa_gali";
            case "miru"   -> "toa_lewa";
            case "pakari" -> "toa_onua";
            case "kakama" -> "toa_pohatu";
            case "akaku"  -> "toa_kopaka";
            case "huna"   -> "toa_tahu";  // Vakama wore HUNA as a matoran → Tahu
            default       -> null;
        };
    }

    /** Mask IDs that are valid for morph (matoran form: must have geo+textures; toa form: must map to a Toa). */
    public static boolean isImplemented(String maskId) {
        return switch (maskId) {
            // Matoran models available
            case "hau", "huna", "miru", "pakari", "kaukau", "kakama" -> true;
            // AKAKU only valid for Toa form (no matoran model yet)
            case "akaku" -> true;
            default      -> false;
        };
    }

    /** Returns true if this mask has a matoran body model ready. */
    public static boolean hasMatoranModel(String maskId) {
        return switch (maskId) {
            case "hau", "huna", "miru", "pakari", "kaukau", "kakama" -> true;
            default -> false;
        };
    }
}
