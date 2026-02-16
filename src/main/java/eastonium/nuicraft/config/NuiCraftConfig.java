package eastonium.nuicraft.config;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * Common config for NuiCraft (server-safe).
 */
public class NuiCraftConfig {

    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final NuiCraftConfig CONFIG;
    public static final ModConfigSpec SPEC;

    /** Path to the Matanui world save folder (relative to game dir, e.g. "saves/Matanui world"). Empty = disable import. */
    public static final ModConfigSpec.ConfigValue<String> MATANUI_WORLD_PATH;

    static {
        MATANUI_WORLD_PATH = BUILDER
                .comment("Path to the Matanui world save folder, relative to game directory.",
                        "Example: saves/Matanui world",
                        "The Mata Nui dimension will copy region data from this world's overworld on first load.",
                        "Leave empty to use flat generation instead. World must be opened in 1.21 once to upgrade chunk format.")
                .define("matanuiWorldPath", "saves/Matanui world");

        var pair = BUILDER.configure(NuiCraftConfig::new);
        CONFIG = pair.getLeft();
        SPEC = pair.getRight();
    }

    private NuiCraftConfig(ModConfigSpec.Builder builder) {
        // config values are static
    }

    public static String getMatanuiWorldPath() {
        String path = MATANUI_WORLD_PATH.get();
        return path == null || path.isBlank() ? "" : path.trim();
    }
}
