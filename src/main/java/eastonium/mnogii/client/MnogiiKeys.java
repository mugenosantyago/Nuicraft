package eastonium.mnogii.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

public class MnogiiKeys {

    public static final KeyMapping MORPH_MATORAN = new KeyMapping(
            "key.mnogii.morph_matoran",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_M,
            "key.categories.mnogii"
    );

    public static final KeyMapping MORPH_TOA = new KeyMapping(
            "key.mnogii.morph_toa",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_T,
            "key.categories.mnogii"
    );

    public static void register(RegisterKeyMappingsEvent event) {
        event.register(MORPH_MATORAN);
        event.register(MORPH_TOA);
    }
}
