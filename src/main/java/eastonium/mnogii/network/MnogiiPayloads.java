package eastonium.mnogii.network;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.client.ClientPayloadHandlers;
import eastonium.mnogii.entity.EntityGukko;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class MnogiiPayloads {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(Mnogii.MODID).versioned("1");

        registrar.playToClient(
                OpenDialoguePayload.TYPE,
                OpenDialoguePayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> {
                    if (FMLEnvironment.dist.isClient()) {
                        ClientPayloadHandlers.handleOpenDialogue(payload);
                    }
                })
        );

        registrar.playToServer(
                GukkoDescentPayload.TYPE,
                GukkoDescentPayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> {
                    if (context.player() instanceof ServerPlayer player
                            && player.getVehicle() instanceof EntityGukko gukko) {
                        gukko.setWantsDescend(payload.descending());
                    }
                })
        );
    }
}
