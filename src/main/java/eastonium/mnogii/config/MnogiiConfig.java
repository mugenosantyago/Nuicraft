package eastonium.mnogii.config;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * Common config for Mnogii (server-safe).
 */
public class MnogiiConfig {

    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final MnogiiConfig CONFIG;
    public static final ModConfigSpec SPEC;

    static {
        var pair = BUILDER.configure(MnogiiConfig::new);
        CONFIG = pair.getLeft();
        SPEC = pair.getRight();
    }

    private MnogiiConfig(ModConfigSpec.Builder builder) {
        // config values are static
    }
}
