package eastonium.nuicraft.client;

import eastonium.nuicraft.client.model.*;
import eastonium.nuicraft.client.renderer.*;
import eastonium.nuicraft.core.NuiCraftEntityTypes;
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
        event.enqueueWork(MaskArmorRendererRegistry::registerAll);
    }

    private static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(NuiCraftModelLayers.MAHI, MahiModel::createBodyLayer);
        event.registerLayerDefinition(NuiCraftModelLayers.FIKOU, FikouModel::createBodyLayer);
        event.registerLayerDefinition(NuiCraftModelLayers.HOI, HoiModel::createBodyLayer);
        event.registerLayerDefinition(NuiCraftModelLayers.KOFO_JAGA, KofoJagaModel::createBodyLayer);
        event.registerLayerDefinition(NuiCraftModelLayers.NUI_JAGA, NuiJagaModel::createBodyLayer);
    }

    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // Custom renderers with models
        event.registerEntityRenderer(NuiCraftEntityTypes.MAHI.get(), MahiRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.FIKOU.get(), FikouRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.HOI.get(), HoiRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.KOFO_JAGA.get(), KofoJagaRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.NUI_JAGA.get(), NuiJagaRenderer::new);
        
        // Generic renderers for all other NuiCraft mobs (fallback to prevent null renderer crash)
        event.registerEntityRenderer(NuiCraftEntityTypes.USSAL_CRAB.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "ussal_crab"));
        event.registerEntityRenderer(NuiCraftEntityTypes.SHEEP_RAHI.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "sheep_rahi"));
        event.registerEntityRenderer(NuiCraftEntityTypes.RABBIT_RAHI.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "rabbit_rahi"));
        event.registerEntityRenderer(NuiCraftEntityTypes.MUAKA.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "muaka"));
        event.registerEntityRenderer(NuiCraftEntityTypes.KANE_RA.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "kane_ra"));
        event.registerEntityRenderer(NuiCraftEntityTypes.POKAWI.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "pokawi"));
        event.registerEntityRenderer(NuiCraftEntityTypes.TURTLE_RAHI.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "turtle_rahi"));
        event.registerEntityRenderer(NuiCraftEntityTypes.HIKAKI.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "hikaki"));
        event.registerEntityRenderer(NuiCraftEntityTypes.SHORE_TURTLE.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "shore_turtle"));
        event.registerEntityRenderer(NuiCraftEntityTypes.BIRD_RAHI.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "bird_rahi"));
        event.registerEntityRenderer(NuiCraftEntityTypes.BAT_RAHI.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "bat_rahi"));
        event.registerEntityRenderer(NuiCraftEntityTypes.HOTO_BUG.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "hoto_bug"));
        event.registerEntityRenderer(NuiCraftEntityTypes.MATORAN.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "matoran"));
        event.registerEntityRenderer(NuiCraftEntityTypes.BOHROK.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "bohrok"));
        event.registerEntityRenderer(NuiCraftEntityTypes.TURAGA.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "turaga"));
        event.registerEntityRenderer(NuiCraftEntityTypes.MUKAU.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "mukau"));
        event.registerEntityRenderer(NuiCraftEntityTypes.HAPAKA.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "hapaka"));
        event.registerEntityRenderer(NuiCraftEntityTypes.BOHROK_VA.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "bohrok_va"));
        event.registerEntityRenderer(NuiCraftEntityTypes.NUI_RAMA.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "nui_rama"));
        event.registerEntityRenderer(NuiCraftEntityTypes.ASH_BEAR.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "ash_bear"));
        event.registerEntityRenderer(NuiCraftEntityTypes.RAHAGA.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "rahaga"));
        event.registerEntityRenderer(NuiCraftEntityTypes.VAKO.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "vako"));
        event.registerEntityRenderer(NuiCraftEntityTypes.KIKANALO.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "kikanalo"));
        event.registerEntityRenderer(NuiCraftEntityTypes.RAHKSHI.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "rahkshi"));
        event.registerEntityRenderer(NuiCraftEntityTypes.KRAATA.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "kraata"));
        event.registerEntityRenderer(NuiCraftEntityTypes.PIRAKA.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "piraka"));
        event.registerEntityRenderer(NuiCraftEntityTypes.DARK_HUNTER.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "dark_hunter"));
        event.registerEntityRenderer(NuiCraftEntityTypes.FUSA.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "fusa"));
        event.registerEntityRenderer(NuiCraftEntityTypes.SKRALL.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "skrall"));
        event.registerEntityRenderer(NuiCraftEntityTypes.ZESK.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "zesk"));
        event.registerEntityRenderer(NuiCraftEntityTypes.AGORI.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "agori"));
        event.registerEntityRenderer(NuiCraftEntityTypes.FOX_RAHI.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "fox_rahi"));
        event.registerEntityRenderer(NuiCraftEntityTypes.KAVINIKA.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "kavinika"));
        event.registerEntityRenderer(NuiCraftEntityTypes.ARCHIVES_MOLE.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "archives_mole"));
        event.registerEntityRenderer(NuiCraftEntityTypes.TAKEA_SHARK.get(), ctx -> new GenericNuiCraftRenderer<>(ctx, "takea_shark"));
    }
}
