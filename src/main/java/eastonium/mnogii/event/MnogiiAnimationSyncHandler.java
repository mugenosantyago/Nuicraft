package eastonium.mnogii.event;

import eastonium.mnogii.entity.*;
import mod.azure.azurelib.common.animation.dispatch.command.AzCommand;
import mod.azure.azurelib.common.animation.play_behavior.AzPlayBehaviors;
import mod.azure.azurelib.common.network.packet.AzEntityDispatchCommandPacket;
import mod.azure.azurelib.common.platform.Services;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

/**
 * Syncs the current animation state of Mnogii entities to a player when they
 * first start tracking that entity (i.e. entering render distance).
 *
 * Note: commands sent here may arrive before the client animator is ready,
 * but AzureLib will queue them until the animator initialises.
 */
public class MnogiiAnimationSyncHandler {

    @SubscribeEvent
    public static void onStartTracking(PlayerEvent.StartTracking event) {
        Entity entity = event.getTarget();
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        if (!isAnimatableEntity(entity)) return;

        boolean moving = entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-5;
        String anim = moving ? "walk" : "idle";

        AzCommand cmd = AzCommand.create("base_controller", anim, AzPlayBehaviors.LOOP);
        var packet = new AzEntityDispatchCommandPacket(entity.getId(), cmd);
        Services.NETWORK.sendToPlayer(packet, player);

        // For Turaga, also sync the pose controller so the correct stance is shown
        if (entity instanceof EntityTuraga) {
            AzCommand poseCmd = AzCommand.create("pose_controller", "pose", AzPlayBehaviors.LOOP);
            Services.NETWORK.sendToPlayer(
                new AzEntityDispatchCommandPacket(entity.getId(), poseCmd), player);
        }
    }

    private static boolean isAnimatableEntity(Entity entity) {
        return entity instanceof EntityMatoran
            || entity instanceof EntityTuraga
            || entity instanceof EntityToa
            || entity instanceof EntityMuaka
            || entity instanceof EntityTarakava
            || entity instanceof EntityFikou
            || entity instanceof EntityGukko
            || entity instanceof EntityNuiRama;
    }
}
