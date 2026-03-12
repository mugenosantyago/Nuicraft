package eastonium.mnogii.client;

import eastonium.mnogii.entity.EntityGukko;
import eastonium.mnogii.network.GukkoDescentPayload;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

/**
 * Runs every client tick while the local player is riding a Gukko.
 * Reads the actual Shift key state and sends it to the server via
 * {@link GukkoDescentPayload} whenever the state changes.
 *
 * This is needed because pressing Shift while in a vehicle causes Minecraft
 * to send STOP_RIDING instead of START_SNEAKING, so the server never learns
 * the player is holding Shift unless we send our own packet.
 */
public class GukkoInputSender {

    private static boolean lastDescend = false;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        var player = mc.player;
        if (player == null || mc.level == null) return;

        boolean onGukko = player.getVehicle() instanceof EntityGukko;

        if (!onGukko) {
            if (lastDescend) {
                lastDescend = false;
                ClientPacketDistributor.sendToServer(new GukkoDescentPayload(false));
            }
            return;
        }

        boolean descend = mc.options.keyShift.isDown();
        if (descend != lastDescend) {
            lastDescend = descend;
            ClientPacketDistributor.sendToServer(new GukkoDescentPayload(descend));
        }
    }
}
