package eastonium.nuicraft.client;

import eastonium.nuicraft.client.model.MahiModel;
import eastonium.nuicraft.client.model.NuiCraftModelLayers;
import eastonium.nuicraft.client.renderer.MahiRenderer;
import eastonium.nuicraft.core.NuiCraftEntityTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@OnlyIn(Dist.CLIENT)
public class NuiCraftClient {

    public static void registerModBusEvents(IEventBus modEventBus) {
        modEventBus.addListener(NuiCraftClient::registerLayerDefinitions);
        modEventBus.addListener(NuiCraftClient::registerRenderers);
    }

    private static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(NuiCraftModelLayers.MAHI, MahiModel::createBodyLayer);
    }

    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(NuiCraftEntityTypes.MAHI.get(), MahiRenderer::new);
    }
}
