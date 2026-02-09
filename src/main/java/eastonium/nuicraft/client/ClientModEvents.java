package eastonium.nuicraft.client;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.client.model.*;
import eastonium.nuicraft.client.renderer.*;
import eastonium.nuicraft.core.NuiCraftEntityTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = NuiCraft.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientModEvents {
    
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(NuiCraftModelLayers.MAHI, MahiModel::createBodyLayer);
    }
    
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(NuiCraftEntityTypes.MAHI.get(), MahiRenderer::new);
    }
}
