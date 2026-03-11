package eastonium.mnogii.client;

import eastonium.mnogii.morph.MorphState;
import eastonium.mnogii.network.MorphRequestPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;

/**
 * Reads M / T key presses each client tick.
 * Validates that a mask is equipped, then sends a MorphRequestPayload to the server.
 * The server is authoritative — it verifies and then broadcasts the state change back.
 */
public class PlayerMorphKeyHandler {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.screen != null) return;

        if (MnogiiKeys.MORPH_MATORAN.consumeClick()) {
            if (hasMask(mc)) sendToServer(mc, MorphState.MATORAN);
        }
        if (MnogiiKeys.MORPH_TOA.consumeClick()) {
            if (hasMask(mc)) sendToServer(mc, MorphState.TOA);
        }
    }

    private static void sendToServer(Minecraft mc, MorphState state) {
        if (mc.getConnection() != null) {
            mc.getConnection().send(new ServerboundCustomPayloadPacket(new MorphRequestPayload(state)));
        }
    }

    private static boolean hasMask(Minecraft mc) {
        ItemStack helmet = mc.player.getItemBySlot(EquipmentSlot.HEAD);
        return MorphRenderHelper.getMaskId(helmet) != null;
    }
}
