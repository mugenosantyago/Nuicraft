package eastonium.nuicraft.client;

import eastonium.nuicraft.entity.EntityGukko;
import eastonium.nuicraft.network.GukkoInputPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

/**
 * Client-only: sends Gukko movement input to the server every tick while the player is riding a Gukko.
 */
public class GukkoInputSender {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        if (!event.getEntity().level().isClientSide()) return;
        Entity vehicle = event.getEntity().getVehicle();
        if (!(vehicle instanceof EntityGukko gukko)) return;
        var mc = Minecraft.getInstance();
        if (mc.getConnection() == null) return;
        var opts = mc.options;
        boolean forward = opts.keyUp.isDown();
        boolean back = opts.keyDown.isDown();
        boolean left = opts.keyLeft.isDown();
        boolean right = opts.keyRight.isDown();
        boolean up = opts.keyJump.isDown();
        boolean down = opts.keyShift.isDown();
        mc.getConnection().send(new ServerboundCustomPayloadPacket(
                new GukkoInputPayload(gukko.getId(), forward, back, left, right, up, down)));
    }
}
