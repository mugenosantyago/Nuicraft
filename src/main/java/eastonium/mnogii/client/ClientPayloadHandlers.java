package eastonium.mnogii.client;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.client.renderer.PlayerMorphGeoRenderer;
import eastonium.mnogii.client.screen.DialogueScreen;
import eastonium.mnogii.morph.MorphState;
import eastonium.mnogii.network.MorphBroadcastPayload;
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

    public static void handleMorphBroadcast(MorphBroadcastPayload payload) {
        if (payload.state() == MorphState.NONE) {
            PlayerMorphGeoRenderer.MORPH_STATES.remove(payload.playerUUID());
        } else {
            PlayerMorphGeoRenderer.MORPH_STATES.put(payload.playerUUID(), payload.state());
        }
    }
}
