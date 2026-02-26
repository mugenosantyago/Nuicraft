package eastonium.nuicraft.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

public class NuiCraftKeys {

    public static final KeyMapping MORPH_MATORAN = new KeyMapping(
            "key.nuicraft.morph_matoran",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_M,
            "key.categories.nuicraft"
    );

    public static final KeyMapping MORPH_TOA = new KeyMapping(
            "key.nuicraft.morph_toa",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_T,
            "key.categories.nuicraft"
    );

    public static void register(RegisterKeyMappingsEvent event) {
        event.register(MORPH_MATORAN);
        event.register(MORPH_TOA);
    }
}
