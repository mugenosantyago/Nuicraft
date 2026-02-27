package eastonium.nuicraft.morph;

import eastonium.nuicraft.client.MorphRenderHelper;
import eastonium.nuicraft.client.animator.PlayerMorphAnimator;
import eastonium.nuicraft.client.renderer.PlayerMorphGeoRenderer;
import eastonium.nuicraft.network.MorphBroadcastPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

/**
 * Handles the server-side morph state lifecycle and client-side rendering swap.
 *
 * Server responsibilities:
 *   - Apply a MorphRequestPayload (validate + update attachment + broadcast)
 *   - Force-revert morph when the player removes their mask
 *
 * Client responsibilities:
 *   - Cancel the vanilla player renderer when morphed
 *   - Draw the matoran/toa geo model in its place
 *   - Drive the idle animation
 */
public class PlayerMorphEventHandler {

    // ── Server-side ──────────────────────────────────────────────────────────

    /**
     * Called from NuiCraftPayloads when a MorphRequestPayload arrives.
     * If the player already has the requested state active it reverts to NONE (toggle).
     */
    public static void handleMorphRequest(ServerPlayer player, MorphState requested) {
        // Must have a recognised mask equipped
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
        // If they no longer have a valid mask, revert
        if (MorphRenderHelper.getMaskId(player.getItemBySlot(EquipmentSlot.HEAD)) == null) {
            MorphState current = player.getData(NuiCraftAttachments.MORPH_STATE.get());
            if (current != MorphState.NONE) {
                applyAndBroadcast(player, MorphState.NONE);
            }
        }
    }

    private static void applyAndBroadcast(ServerPlayer player, MorphState state) {
        player.setData(NuiCraftAttachments.MORPH_STATE.get(), state);
        // Broadcast to all players tracking this player (including self)
        PacketDistributor.sendToPlayersTrackingEntityAndSelf(player,
                new MorphBroadcastPayload(player.getUUID(), state));
    }

    // ── Client-side ──────────────────────────────────────────────────────────

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
        // In 1.21.8 RenderPlayerEvent carries a render state snapshot, not the live entity.
        // Resolve the player from the entity ID stored in the render state.
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;
        int entityId = event.getRenderState().id;
        if (!(mc.level.getEntity(entityId) instanceof AbstractClientPlayer player)) return;

        MorphState state = PlayerMorphGeoRenderer.MORPH_STATES.getOrDefault(
                player.getUUID(), MorphState.NONE);

        if (state == MorphState.NONE) return;
        if (MorphRenderHelper.getMaskId(player.getItemBySlot(EquipmentSlot.HEAD)) == null) return;

        event.setCanceled(true);

        PlayerMorphGeoRenderer renderer = PlayerMorphGeoRenderer.get();
        if (renderer == null) return;

        renderer.renderMorphed(
                player,
                event.getPartialTick(),          // already a float in 1.21.8
                event.getPoseStack(),
                event.getMultiBufferSource(),
                event.getPackedLight()
        );
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof AbstractClientPlayer player)) return;
        if (!player.level().isClientSide()) return;
        MorphState state = PlayerMorphGeoRenderer.MORPH_STATES.getOrDefault(
                player.getUUID(), MorphState.NONE);
        if (state == MorphState.NONE) return;
        if (player.tickCount % 20 == 0) {
            PlayerMorphAnimator.sendIdleCommand(player);
        }
    }

    // ── Client broadcast reception (wired in NuiCraftPayloads) ───────────────

    @OnlyIn(Dist.CLIENT)
    public static void onMorphBroadcast(MorphBroadcastPayload payload) {
        if (payload.state() == MorphState.NONE) {
            PlayerMorphGeoRenderer.MORPH_STATES.remove(payload.playerUUID());
        } else {
            PlayerMorphGeoRenderer.MORPH_STATES.put(payload.playerUUID(), payload.state());
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientLogout(ClientPlayerNetworkEvent.LoggingOut event) {
        PlayerMorphGeoRenderer.MORPH_STATES.clear();
    }
}
