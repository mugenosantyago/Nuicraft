package eastonium.mnogii.network;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.client.ClientPayloadHandlers;
import eastonium.mnogii.morph.PlayerMorphEventHandler;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class MnogiiPayloads {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(Mnogii.MODID).versioned("1");

        // playToClient handlers: lambda only runs on the client, but we also guard with
        // FMLEnvironment.dist so that referencing ClientPayloadHandlers in the body never
        // causes the server to load that class (and transitively DialogueScreen / Screen).
        registrar.playToClient(
                OpenDialoguePayload.TYPE,
                OpenDialoguePayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> {
                    if (FMLEnvironment.dist.isClient()) {
                        ClientPayloadHandlers.handleOpenDialogue(payload);
                    }
                })
        );

        // Morph system
        registrar.playToServer(
                MorphRequestPayload.TYPE,
                MorphRequestPayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> {
                    if (context.player() instanceof ServerPlayer sp) {
                        PlayerMorphEventHandler.handleMorphRequest(sp, payload.requested());
                    }
                })
        );
        registrar.playToClient(
                MorphBroadcastPayload.TYPE,
                MorphBroadcastPayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> {
                    if (FMLEnvironment.dist.isClient()) {
                        ClientPayloadHandlers.handleMorphBroadcast(payload);
                    }
                })
        );
    }
}
