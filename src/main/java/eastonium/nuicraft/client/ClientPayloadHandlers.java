package eastonium.nuicraft.client;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.client.renderer.PlayerMorphGeoRenderer;
import eastonium.nuicraft.client.screen.DialogueScreen;
import eastonium.nuicraft.morph.MorphState;
import eastonium.nuicraft.network.MorphBroadcastPayload;
import eastonium.nuicraft.network.OpenDialoguePayload;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

/**
 * Client-only payload handlers — called from NuiCraftPayloads via DistExecutor so this class
 * is never loaded on the dedicated server (avoids NoClassDefFoundError for client-only classes).
 */
public final class ClientPayloadHandlers {

    private ClientPayloadHandlers() {}

    public static void handleOpenDialogue(OpenDialoguePayload payload) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            Component title = Component.translatable("entity." + NuiCraft.MODID + "." + payload.dialogueType());
            Component message = Component.translatable("dialogue." + NuiCraft.MODID + "." + payload.dialogueType() + ".greeting");
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
