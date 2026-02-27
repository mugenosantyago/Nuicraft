package eastonium.nuicraft.client;

import eastonium.nuicraft.entity.EntityGukko;
import eastonium.nuicraft.network.GukkoInputPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

/**
 * Client-only: sends Gukko movement input to the server only when it changes.
 */
public class GukkoInputSender {

    private static int lastEntityId = -1;
    private static boolean lastForward, lastBack, lastLeft, lastRight, lastUp, lastDown;

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        if (!event.getEntity().level().isClientSide()) return;
        Entity vehicle = event.getEntity().getVehicle();
        if (!(vehicle instanceof EntityGukko gukko)) {
            lastEntityId = -1;
            return;
        }
        var mc = Minecraft.getInstance();
        if (mc.getConnection() == null) return;
        var opts = mc.options;
        boolean forward = opts.keyUp.isDown();
        boolean back    = opts.keyDown.isDown();
        boolean left    = opts.keyLeft.isDown();
        boolean right   = opts.keyRight.isDown();
        boolean up      = opts.keyJump.isDown();
        boolean down    = opts.keyShift.isDown();
        int entityId    = gukko.getId();

        if (entityId == lastEntityId
                && forward == lastForward && back == lastBack
                && left == lastLeft && right == lastRight
                && up == lastUp && down == lastDown) {
            return;
        }

        lastEntityId = entityId;
        lastForward = forward; lastBack = back;
        lastLeft = left;       lastRight = right;
        lastUp = up;           lastDown = down;

        mc.getConnection().send(new ServerboundCustomPayloadPacket(
                new GukkoInputPayload(entityId, forward, back, left, right, up, down)));
    }
}
