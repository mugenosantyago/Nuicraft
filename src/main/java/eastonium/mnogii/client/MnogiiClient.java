package eastonium.mnogii.client;

import eastonium.mnogii.client.model.*;
import eastonium.mnogii.client.renderer.*;
import eastonium.mnogii.client.screen.ElementSwiperScreen;
import eastonium.mnogii.client.screen.PurifierScreen;
import eastonium.mnogii.core.MnogiiEntityTypes;
import eastonium.mnogii.core.MnogiiRegistration;
import eastonium.mnogii.entity.EntityThrownDisc;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
public class MnogiiClient {

    public static void registerModBusEvents(IEventBus modEventBus) {
        modEventBus.addListener(MnogiiClient::registerLayerDefinitions);
        modEventBus.addListener(MnogiiClient::registerRenderers);
        modEventBus.addListener(MnogiiClient::clientSetup);
        modEventBus.addListener(MnogiiClient::registerScreens);
        modEventBus.addListener(MnogiiClient::registerClientExtensions);
        modEventBus.addListener(MnogiiClient::registerSpecialModelRenderers);
    }

    private static void registerSpecialModelRenderers(RegisterSpecialModelRendererEvent event) {
        event.register(MaskSpecialModelRenderer.Unbaked.TYPE_ID, MaskSpecialModelRenderer.Unbaked.CODEC);
    }

    private static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(MnogiiRegistration.PURIFIER_MENU.get(), PurifierScreen::new);
        event.register(MnogiiRegistration.ELEMENT_SWIPER_MENU.get(), ElementSwiperScreen::new);
    }

    private static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MaskArmorRendererRegistry.registerAll();
        });
    }

    private static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MnogiiModelLayers.MAHI, MahiModel::createBodyLayer);
        // Fikou uses Geo model (fikou.geo.json), no layer
        event.registerLayerDefinition(MnogiiModelLayers.HOI, HoiModel::createBodyLayer);
        event.registerLayerDefinition(MnogiiModelLayers.KOFO_JAGA, KofoJagaModel::createBodyLayer);
        event.registerLayerDefinition(MnogiiModelLayers.NUI_JAGA, NuiJagaModel::createBodyLayer);
    }

    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MnogiiEntityTypes.MAHI.get(), MahiRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.FIKOU.get(), FikouGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.SPIDER_FIKOU.get(), SpiderFikouGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.HOI.get(), HoiRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.KOFO_JAGA.get(), KofoJagaRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.NUI_JAGA.get(), NuiJagaRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.MATORAN.get(), MatoranGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.MATORAN_TA.get(), MatoranGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.MATORAN_GA.get(), MatoranGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.MATORAN_LE.get(), MatoranGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.MATORAN_ONU.get(), MatoranGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.MATORAN_KO.get(), MatoranGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.MATORAN_PO.get(), MatoranGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.MATORAN_PURPLE.get(), MatoranGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.MATORAN_YELLOW.get(), MatoranGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.TURAGA.get(),        TuragaGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.TURAGA_VAKAMA.get(), TuragaGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.TURAGA_NOKAMA.get(), TuragaGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.TURAGA_MATAU.get(),  TuragaGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.TURAGA_ONEWA.get(),  TuragaGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.TURAGA_WHENUA.get(), TuragaGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.TURAGA_NUJU.get(),   TuragaGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.MUAKA.get(), MuakaGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.TARAKAVA.get(), TarakavaGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.GUKKO.get(), GukkoGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.NUI_RAMA.get(), NuiRamaGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.TOA_TAHU.get(), ToaGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.TOA_GALI.get(), ToaGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.TOA_LEWA.get(), ToaGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.TOA_ONUA.get(), ToaGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.TOA_POHATU.get(), ToaGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.TOA_KOPAKA.get(), ToaGeoRenderer::new);
        event.registerEntityRenderer(MnogiiEntityTypes.THROWN_DISC.get(), ThrownItemRenderer::new);
    }

    private static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        // Regular liquid protodermis — silver-blue
        event.registerFluidType(fluidExt(
                "minecraft:block/water_still", "minecraft:block/water_flow", 0xFFC4D8E0),
                MnogiiRegistration.TYPE_PROTODERMIS.get());

        // Pure liquid protodermis — warm gold
        event.registerFluidType(fluidExt(
                "minecraft:block/water_still", "minecraft:block/water_flow", 0xFFE8D880),
                MnogiiRegistration.TYPE_PROTODERMIS_PURE.get());

        // Molten protodermis — deep orange-red (lava-like)
        event.registerFluidType(fluidExt(
                "minecraft:block/lava_still", "minecraft:block/lava_flow", 0xFFE05020),
                MnogiiRegistration.TYPE_PROTODERMIS_MOLTEN.get());

        // Pure molten protodermis — bright gold
        event.registerFluidType(fluidExt(
                "minecraft:block/lava_still", "minecraft:block/lava_flow", 0xFFFFCC00),
                MnogiiRegistration.TYPE_PROTODERMIS_PURE_MOLTEN.get());
    }

    /** Builds a simple {@link IClientFluidTypeExtensions} for the given still/flow textures and tint. */
    private static IClientFluidTypeExtensions fluidExt(String still, String flow, int tintColor) {
        ResourceLocation stillLoc = ResourceLocation.parse(still);
        ResourceLocation flowLoc  = ResourceLocation.parse(flow);
        return new IClientFluidTypeExtensions() {
            @Override public ResourceLocation getStillTexture()   { return stillLoc; }
            @Override public ResourceLocation getFlowingTexture() { return flowLoc;  }
            @Override public int getTintColor()                    { return tintColor; }
        };
    }
}
