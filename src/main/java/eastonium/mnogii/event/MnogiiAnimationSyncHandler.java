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
 * first start tracking that entity (e.g. entering render distance or loading a chunk).
 *
 * This removes the need for entities to re-broadcast their state on a fixed timer
 * (the old tickCount % 20 pattern), which caused unnecessary network traffic.
 * Entities now only send animation commands when their state actually changes,
 * and this handler covers the "late joiner" case.
 */
public class MnogiiAnimationSyncHandler {

    @SubscribeEvent
    public static void onStartTracking(PlayerEvent.StartTracking event) {
        Entity entity = event.getTarget();
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        // Only handle our animatable entities that use a base_controller with idle/walk
        if (!isAnimatableEntity(entity)) return;

        boolean moving = entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-5;
        String anim = moving ? "walk" : "idle";

        AzCommand cmd = AzCommand.create("base_controller", anim, AzPlayBehaviors.LOOP);
        var packet = new AzEntityDispatchCommandPacket(entity.getId(), cmd);
        Services.NETWORK.sendToPlayer(packet, player);
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
