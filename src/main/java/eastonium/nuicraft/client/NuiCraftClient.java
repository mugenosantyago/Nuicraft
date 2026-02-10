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
        // Custom renderers with models for core Bionicle mobs
        event.registerEntityRenderer(NuiCraftEntityTypes.MAHI.get(), MahiRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.FIKOU.get(), FikouRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.HOI.get(), HoiRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.KOFO_JAGA.get(), KofoJagaRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.NUI_JAGA.get(), NuiJagaRenderer::new);

        // Generic renderers for other mobs using Pig model + textures
        // Bohrok
        event.registerEntityRenderer(NuiCraftEntityTypes.BOHROKTAHNOK.get(), ctx -> new GenericNuiCraftRenderer(ctx, "bohrok_tahnok"));
        event.registerEntityRenderer(NuiCraftEntityTypes.GAHLOK.get(), ctx -> new GenericNuiCraftRenderer(ctx, "gahloktexture"));
        event.registerEntityRenderer(NuiCraftEntityTypes.KOHRAK.get(), ctx -> new GenericNuiCraftRenderer(ctx, "kohraktexture"));
        event.registerEntityRenderer(NuiCraftEntityTypes.LEHVAK.get(), ctx -> new GenericNuiCraftRenderer(ctx, "lehvaktexture"));
        event.registerEntityRenderer(NuiCraftEntityTypes.NUHVOK.get(), ctx -> new GenericNuiCraftRenderer(ctx, "nuhvoktexture"));
        event.registerEntityRenderer(NuiCraftEntityTypes.PAHRAK.get(), ctx -> new GenericNuiCraftRenderer(ctx, "pahraktexture"));

        // Rahkshi
        event.registerEntityRenderer(NuiCraftEntityTypes.GUURAHK.get(), ctx -> new GenericNuiCraftRenderer(ctx, "guurahk"));
        event.registerEntityRenderer(NuiCraftEntityTypes.KURAHK.get(), ctx -> new GenericNuiCraftRenderer(ctx, "kurahk"));
        event.registerEntityRenderer(NuiCraftEntityTypes.LERAHK.get(), ctx -> new GenericNuiCraftRenderer(ctx, "lerahk"));
        event.registerEntityRenderer(NuiCraftEntityTypes.PANRAHK.get(), ctx -> new GenericNuiCraftRenderer(ctx, "panrahk"));
        event.registerEntityRenderer(NuiCraftEntityTypes.RAHKSHIYELLOW.get(), ctx -> new GenericNuiCraftRenderer(ctx, "rahkshi_yellow"));
        event.registerEntityRenderer(NuiCraftEntityTypes.TURAHK.get(), ctx -> new GenericNuiCraftRenderer(ctx, "turahk"));
        event.registerEntityRenderer(NuiCraftEntityTypes.VOHRAK.get(), ctx -> new GenericNuiCraftRenderer(ctx, "vohrak"));

        // Matoran
        event.registerEntityRenderer(NuiCraftEntityTypes.AGNIMATORAN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "agnitexture"));
        event.registerEntityRenderer(NuiCraftEntityTypes.AKHMOUMATORAN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "ahkmoumatoran"));
        event.registerEntityRenderer(NuiCraftEntityTypes.BOREASMATORAN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "boreastexture"));
        event.registerEntityRenderer(NuiCraftEntityTypes.HAFUMATORAN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "hafutexture"));
        event.registerEntityRenderer(NuiCraftEntityTypes.HEWKIIMATORAN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "hewkiitexture"));
        event.registerEntityRenderer(NuiCraftEntityTypes.JALLERMATORAN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "jallertexture"));
        event.registerEntityRenderer(NuiCraftEntityTypes.KOKKANMATORAN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "kokkantexture"));
        event.registerEntityRenderer(NuiCraftEntityTypes.KONGUMATORAN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "kongutexture"));
        event.registerEntityRenderer(NuiCraftEntityTypes.KOTUMATORAN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "matorankotu"));
        event.registerEntityRenderer(NuiCraftEntityTypes.MACKUMATORAN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "mackutexture"));
        event.registerEntityRenderer(NuiCraftEntityTypes.MATOROMATORAN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "matorotexture"));
        event.registerEntityRenderer(NuiCraftEntityTypes.NUPARUMATORAN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "nuparumatorantexture"));
        event.registerEntityRenderer(NuiCraftEntityTypes.OKOTHMATORAN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "okothtexture"));
        event.registerEntityRenderer(NuiCraftEntityTypes.ONEPUMATORAN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "oneputexture"));
        event.registerEntityRenderer(NuiCraftEntityTypes.PAKASTAAMATORAN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "matoranpakastaa"));
        event.registerEntityRenderer(NuiCraftEntityTypes.TUULIMATORAN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "matorantuuli"));
        event.registerEntityRenderer(NuiCraftEntityTypes.VOHONMATORAN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "vohon_matoran"));
        event.registerEntityRenderer(NuiCraftEntityTypes.ZEMYAMATORAN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "zemya_matoran"));

        // Turaga
        event.registerEntityRenderer(NuiCraftEntityTypes.MATAUTURAGA.get(), ctx -> new GenericNuiCraftRenderer(ctx, "matau_turaga"));
        event.registerEntityRenderer(NuiCraftEntityTypes.NOKAMATURAGA.get(), ctx -> new GenericNuiCraftRenderer(ctx, "nokama_turaga"));
        event.registerEntityRenderer(NuiCraftEntityTypes.NUJUTURAGA.get(), ctx -> new GenericNuiCraftRenderer(ctx, "nuju_turaga"));
        event.registerEntityRenderer(NuiCraftEntityTypes.ONEWATURAGA.get(), ctx -> new GenericNuiCraftRenderer(ctx, "onewa_turaga"));
        event.registerEntityRenderer(NuiCraftEntityTypes.VAKAMATURAGA.get(), ctx -> new GenericNuiCraftRenderer(ctx, "vakama_turaga"));
        event.registerEntityRenderer(NuiCraftEntityTypes.WHENAUTURAGA.get(), ctx -> new GenericNuiCraftRenderer(ctx, "whenua_turaga"));

        // Rahi
        event.registerEntityRenderer(NuiCraftEntityTypes.KANERA.get(), ctx -> new GenericNuiCraftRenderer(ctx, "kanera"));
        event.registerEntityRenderer(NuiCraftEntityTypes.MUAKA.get(), ctx -> new GenericNuiCraftRenderer(ctx, "muaka"));
        event.registerEntityRenderer(NuiCraftEntityTypes.NUIRAMAGREEN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "nuiramagreen"));
        event.registerEntityRenderer(NuiCraftEntityTypes.NUIRAMAORANGE.get(), ctx -> new GenericNuiCraftRenderer(ctx, "nuiramaorange"));
        event.registerEntityRenderer(NuiCraftEntityTypes.SPIDERFIKOU.get(), ctx -> new GenericNuiCraftRenderer(ctx, "spider_fikou"));
        event.registerEntityRenderer(NuiCraftEntityTypes.TARAKAVABLUE.get(), ctx -> new GenericNuiCraftRenderer(ctx, "tarakava_blue"));
        event.registerEntityRenderer(NuiCraftEntityTypes.TARAKAVAGREEN.get(), ctx -> new GenericNuiCraftRenderer(ctx, "tarakava_green"));
        event.registerEntityRenderer(NuiCraftEntityTypes.TARAKAVAYELLOW.get(), ctx -> new GenericNuiCraftRenderer(ctx, "tarakava_yellow"));
        event.registerEntityRenderer(NuiCraftEntityTypes.MAKUTA.get(), ctx -> new GenericNuiCraftRenderer(ctx, "makuta"));
    }
}
