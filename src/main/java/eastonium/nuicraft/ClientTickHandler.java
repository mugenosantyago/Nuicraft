package eastonium.nuicraft;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class ClientTickHandler {
    @SubscribeEvent
    public void onClientPlayerTick(PlayerTickEvent.Pre event) {
        // Client-side mask effects will go here
    }
}
