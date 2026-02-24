package eastonium.nuicraft.client;

import eastonium.nuicraft.client.model.*;
import eastonium.nuicraft.client.renderer.*;
import eastonium.nuicraft.core.NuiCraftEntityTypes;
import eastonium.nuicraft.entity.EntityThrownDisc;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class NuiCraftClient {

    public static void registerModBusEvents(IEventBus modEventBus) {
        modEventBus.addListener(NuiCraftClient::registerLayerDefinitions);
        modEventBus.addListener(NuiCraftClient::registerRenderers);
        modEventBus.addListener(NuiCraftClient::clientSetup);
    }

    private static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MaskArmorRendererRegistry.registerAll();
            net.neoforged.neoforge.common.NeoForge.EVENT_BUS.register(GukkoInputSender.class);
        });
    }

    private static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(NuiCraftModelLayers.MAHI, MahiModel::createBodyLayer);
        // Fikou uses Geo model (fikou.geo.json), no layer
        event.registerLayerDefinition(NuiCraftModelLayers.HOI, HoiModel::createBodyLayer);
        event.registerLayerDefinition(NuiCraftModelLayers.KOFO_JAGA, KofoJagaModel::createBodyLayer);
        event.registerLayerDefinition(NuiCraftModelLayers.NUI_JAGA, NuiJagaModel::createBodyLayer);
    }

    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(NuiCraftEntityTypes.MAHI.get(), MahiRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.FIKOU.get(), FikouGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.HOI.get(), HoiRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.KOFO_JAGA.get(), KofoJagaRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.NUI_JAGA.get(), NuiJagaRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.MATORAN.get(), MatoranGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.MATORAN_TA.get(), MatoranGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.MATORAN_GA.get(), MatoranGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.MATORAN_LE.get(), MatoranGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.MATORAN_ONU.get(), MatoranGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.MATORAN_KO.get(), MatoranGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.MATORAN_PO.get(), MatoranGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.TURAGA.get(), TuragaGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.MUAKA.get(), context -> new GenericNuiCraftRenderer<>(context, "muaka"));
        event.registerEntityRenderer(NuiCraftEntityTypes.TARAKAVA.get(), context -> new GenericNuiCraftRenderer<>(context, "tarakava"));
        event.registerEntityRenderer(NuiCraftEntityTypes.GUKKO.get(), context -> new GenericNuiCraftRenderer<>(context, "gukko"));
        event.registerEntityRenderer(NuiCraftEntityTypes.NUI_RAMA.get(), context -> new GenericNuiCraftRenderer<>(context, "nui_rama"));
        event.registerEntityRenderer(NuiCraftEntityTypes.TOA_TAHU.get(), context -> new GenericNuiCraftRenderer<>(context, "toa_tahu"));
        event.registerEntityRenderer(NuiCraftEntityTypes.TOA_GALI.get(), context -> new GenericNuiCraftRenderer<>(context, "toa_gali"));
        event.registerEntityRenderer(NuiCraftEntityTypes.TOA_LEWA.get(), context -> new GenericNuiCraftRenderer<>(context, "toa_lewa"));
        event.registerEntityRenderer(NuiCraftEntityTypes.TOA_ONUA.get(), context -> new GenericNuiCraftRenderer<>(context, "toa_onua"));
        event.registerEntityRenderer(NuiCraftEntityTypes.TOA_POHATU.get(), context -> new GenericNuiCraftRenderer<>(context, "toa_pohatu"));
        event.registerEntityRenderer(NuiCraftEntityTypes.TOA_KOPAKA.get(), context -> new GenericNuiCraftRenderer<>(context, "toa_kopaka"));
        event.registerEntityRenderer(NuiCraftEntityTypes.THROWN_DISC.get(), ThrownItemRenderer::new);
    }
}
