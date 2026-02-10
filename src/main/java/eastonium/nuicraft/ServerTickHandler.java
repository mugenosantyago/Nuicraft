package eastonium.nuicraft;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

public class ServerTickHandler {
    @SubscribeEvent
    public void onServerTick(ServerTickEvent.Post event) {
        // Mask powers will be added here
    }
}
