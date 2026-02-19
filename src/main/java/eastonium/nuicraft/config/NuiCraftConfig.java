package eastonium.nuicraft.config;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * Common config for NuiCraft (server-safe).
 */
public class NuiCraftConfig {

    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final NuiCraftConfig CONFIG;
    public static final ModConfigSpec SPEC;

    static {
        var pair = BUILDER.configure(NuiCraftConfig::new);
        CONFIG = pair.getLeft();
        SPEC = pair.getRight();
    }

    private NuiCraftConfig(ModConfigSpec.Builder builder) {
        // config values are static
    }
}
