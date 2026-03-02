package eastonium.nuicraft.client;

import eastonium.nuicraft.client.model.*;
import eastonium.nuicraft.client.renderer.*;
import eastonium.nuicraft.client.screen.ElementSwiperScreen;
import eastonium.nuicraft.client.screen.PurifierScreen;
import eastonium.nuicraft.core.NuiCraftEntityTypes;
import eastonium.nuicraft.core.NuiCraftRegistration;
import eastonium.nuicraft.entity.EntityThrownDisc;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.common.NeoForge;

public class NuiCraftClient {

    public static void registerModBusEvents(IEventBus modEventBus) {
        modEventBus.addListener(NuiCraftClient::registerLayerDefinitions);
        modEventBus.addListener(NuiCraftClient::registerRenderers);
        modEventBus.addListener(NuiCraftClient::registerAddLayers);
        modEventBus.addListener(NuiCraftClient::clientSetup);
        modEventBus.addListener(NuiCraftClient::registerScreens);
        modEventBus.addListener(NuiCraftClient::registerClientExtensions);
        modEventBus.addListener(NuiCraftKeys::register);
    }

    private static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(NuiCraftRegistration.PURIFIER_MENU.get(), PurifierScreen::new);
        event.register(NuiCraftRegistration.ELEMENT_SWIPER_MENU.get(), ElementSwiperScreen::new);
    }

    private static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MaskArmorRendererRegistry.registerAll();
            NeoForge.EVENT_BUS.register(GukkoInputSender.class);
            NeoForge.EVENT_BUS.register(PlayerMorphKeyHandler.class);
            NeoForge.EVENT_BUS.register(PlayerMorphClientEvents.class);
        });
    }

    private static void registerAddLayers(EntityRenderersEvent.AddLayers event) {
        // Grab the renderer context to build the morph renderer
        PlayerMorphGeoRenderer.init(event.getContext());
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
        event.registerEntityRenderer(NuiCraftEntityTypes.SPIDER_FIKOU.get(), SpiderFikouGeoRenderer::new);
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
        event.registerEntityRenderer(NuiCraftEntityTypes.TURAGA.get(),        TuragaGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.TURAGA_VAKAMA.get(), TuragaGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.TURAGA_NOKAMA.get(), TuragaGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.TURAGA_MATAU.get(),  TuragaGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.TURAGA_ONEWA.get(),  TuragaGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.TURAGA_WHENUA.get(), TuragaGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.TURAGA_NUJU.get(),   TuragaGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.MUAKA.get(), context -> new GenericNuiCraftRenderer<>(context, "muaka"));
        event.registerEntityRenderer(NuiCraftEntityTypes.TARAKAVA.get(), context -> new GenericNuiCraftRenderer<>(context, "tarakava"));
        event.registerEntityRenderer(NuiCraftEntityTypes.GUKKO.get(), context -> new GenericNuiCraftRenderer<>(context, "gukko"));
        event.registerEntityRenderer(NuiCraftEntityTypes.NUI_RAMA.get(), context -> new GenericNuiCraftRenderer<>(context, "nui_rama"));
        event.registerEntityRenderer(NuiCraftEntityTypes.TOA_TAHU.get(), ToaGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.TOA_GALI.get(), ToaGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.TOA_LEWA.get(), ToaGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.TOA_ONUA.get(), ToaGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.TOA_POHATU.get(), ToaGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.TOA_KOPAKA.get(), ToaGeoRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.THROWN_DISC.get(), ThrownItemRenderer::new);
    }

    private static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        // Regular liquid protodermis — silver-blue
        event.registerFluidType(fluidExt(
                "minecraft:block/water_still", "minecraft:block/water_flow", 0xFFC4D8E0),
                NuiCraftRegistration.TYPE_PROTODERMIS.get());

        // Pure liquid protodermis — warm gold
        event.registerFluidType(fluidExt(
                "minecraft:block/water_still", "minecraft:block/water_flow", 0xFFE8D880),
                NuiCraftRegistration.TYPE_PROTODERMIS_PURE.get());

        // Molten protodermis — deep orange-red (lava-like)
        event.registerFluidType(fluidExt(
                "minecraft:block/lava_still", "minecraft:block/lava_flow", 0xFFE05020),
                NuiCraftRegistration.TYPE_PROTODERMIS_MOLTEN.get());

        // Pure molten protodermis — bright gold
        event.registerFluidType(fluidExt(
                "minecraft:block/lava_still", "minecraft:block/lava_flow", 0xFFFFCC00),
                NuiCraftRegistration.TYPE_PROTODERMIS_PURE_MOLTEN.get());
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
