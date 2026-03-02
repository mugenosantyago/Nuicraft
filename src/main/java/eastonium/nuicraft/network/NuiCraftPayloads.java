package eastonium.nuicraft.network;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.client.ClientPayloadHandlers;
import eastonium.nuicraft.entity.EntityGukko;
import eastonium.nuicraft.entity.EntityNuiRama;
import eastonium.nuicraft.morph.PlayerMorphEventHandler;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class NuiCraftPayloads {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(NuiCraft.MODID).versioned("1");

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

        registrar.playToServer(
                GukkoInputPayload.TYPE,
                GukkoInputPayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> {
                    var vehicle = context.player().getVehicle();
                    if (vehicle instanceof EntityGukko gukko && gukko.getId() == payload.entityId()) {
                        gukko.setMovementInput(payload.forward(), payload.back(), payload.left(), payload.right(), payload.up(), payload.down());
                    } else if (vehicle instanceof EntityNuiRama rama && rama.getId() == payload.entityId()) {
                        rama.setMovementInput(payload.forward(), payload.back(), payload.left(), payload.right(), payload.up(), payload.down());
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
