package eastonium.nuicraft.network;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.client.screen.DialogueScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class NuiCraftPayloads {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(NuiCraft.MODID).versioned("1");
        registrar.playToClient(
                OpenDialoguePayload.TYPE,
                OpenDialoguePayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> {
                    Minecraft mc = Minecraft.getInstance();
                    if (mc.player != null) {
                        Component title = Component.translatable("entity." + NuiCraft.MODID + "." + payload.dialogueType());
                        Component message = Component.translatable("dialogue." + NuiCraft.MODID + "." + payload.dialogueType() + ".greeting");
                        mc.setScreen(new DialogueScreen(title, message));
                    }
                })
        );
    }
}
