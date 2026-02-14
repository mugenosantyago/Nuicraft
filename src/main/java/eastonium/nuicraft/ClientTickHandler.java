package eastonium.nuicraft;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

/**
 * Client-side mask effects and other per-tick logic can go here.
 * Gukko ride input is sent from {@link eastonium.nuicraft.client.GukkoInputSender}.
 */
public class ClientTickHandler {
    @SubscribeEvent
    public static void onClientPlayerTick(PlayerTickEvent.Pre event) {
        // Client-side mask effects will go here
    }
}
