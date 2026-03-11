package eastonium.mnogii.event;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.core.MnogiiEntityTypes;
import eastonium.mnogii.network.OpenDialoguePayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.PacketDistributor;

/**
 * Handles right-click on Matoran and Turaga to open dialogue and award quest 2 for Turaga.
 */
public class DialogueEventHandler {

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getLevel().isClientSide()) {
            return;
        }
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) {
            return;
        }
        var entity = event.getTarget();
        // Matoran: trade screen handled by EntityMatoran.mobInteract
        if (entity.getType() == MnogiiEntityTypes.TURAGA.get()) {
            PacketDistributor.sendToPlayer(serverPlayer, new OpenDialoguePayload("turaga"));
            // Quest 2: "Trade for the first time with one Turaga"
            var advancementHolder = serverPlayer.getServer().getAdvancements().get(ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "quest_2"));
            if (advancementHolder != null) {
                var progress = serverPlayer.getAdvancements().getOrStartProgress(advancementHolder);
                progress.grantProgress("quest_2_0");
            }
            event.setCanceled(true);
        }
    }
}
