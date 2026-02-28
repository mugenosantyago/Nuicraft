package eastonium.nuicraft.morph;

import eastonium.nuicraft.client.MorphRenderHelper;
import eastonium.nuicraft.network.MorphBroadcastPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.network.PacketDistributor;

/**
 * Server-side morph state lifecycle.
 *
 * Client-side render / tick / logout events live in
 * {@link eastonium.nuicraft.client.PlayerMorphClientEvents}, which is only
 * registered on the client dist to avoid loading client-only classes on the server.
 */
public class PlayerMorphEventHandler {

    // ── Server-side ──────────────────────────────────────────────────────────

    /**
     * Called from NuiCraftPayloads when a MorphRequestPayload arrives.
     * If the player already has the requested state active it reverts to NONE (toggle).
     */
    public static void handleMorphRequest(ServerPlayer player, MorphState requested) {
        if (MorphRenderHelper.getMaskId(player.getItemBySlot(EquipmentSlot.HEAD)) == null) {
            applyAndBroadcast(player, MorphState.NONE);
            return;
        }
        MorphState current = player.getData(NuiCraftAttachments.MORPH_STATE.get());
        MorphState next = (current == requested) ? MorphState.NONE : requested;
        applyAndBroadcast(player, next);
    }

    /** When the helmet slot changes server-side, revert morph if the mask was removed. */
    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (event.getSlot() != EquipmentSlot.HEAD) return;
        if (MorphRenderHelper.getMaskId(player.getItemBySlot(EquipmentSlot.HEAD)) == null) {
            MorphState current = player.getData(NuiCraftAttachments.MORPH_STATE.get());
            if (current != MorphState.NONE) {
                applyAndBroadcast(player, MorphState.NONE);
            }
        }
    }

    private static void applyAndBroadcast(ServerPlayer player, MorphState state) {
        player.setData(NuiCraftAttachments.MORPH_STATE.get(), state);
        PacketDistributor.sendToPlayersTrackingEntityAndSelf(player,
                new MorphBroadcastPayload(player.getUUID(), state));
    }
}
