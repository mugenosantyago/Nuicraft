package eastonium.mnogii.client;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.client.screen.DialogueScreen;
import eastonium.mnogii.network.OpenDialoguePayload;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

/**
 * Client-only payload handlers — called from MnogiiPayloads via DistExecutor so this class
 * is never loaded on the dedicated server (avoids NoClassDefFoundError for client-only classes).
 */
public final class ClientPayloadHandlers {

    private ClientPayloadHandlers() {}

    public static void handleOpenDialogue(OpenDialoguePayload payload) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            Component title = Component.translatable("entity." + Mnogii.MODID + "." + payload.dialogueType());
            Component message = Component.translatable("dialogue." + Mnogii.MODID + "." + payload.dialogueType() + ".greeting");
            mc.setScreen(new DialogueScreen(title, message));
        }
    }
}
