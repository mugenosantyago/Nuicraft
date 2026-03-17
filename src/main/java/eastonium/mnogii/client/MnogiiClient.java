package eastonium.mnogii.client;

import eastonium.mnogii.client.model.*;
import eastonium.mnogii.client.renderer.*;
import eastonium.mnogii.client.screen.ElementSwiperScreen;
import eastonium.mnogii.client.screen.PurifierScreen;
import eastonium.mnogii.core.MnogiiEntityTypes;
import eastonium.mnogii.core.MnogiiRegistration;
import eastonium.mnogii.entity.EntityThrownDisc;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import org.joml.Vector4f;
public class MnogiiClient {

    public static void registerModBusEvents(IEventBus modEventBus) {
        modEventBus.addListener(MnogiiClient::registerLayerDefinitions);
        modEventBus.addListener(MnogiiClient::registerRenderers);
        modEventBus.addListener(MnogiiClient::addPlayerLayers);
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

    private static void addPlayerLayers(EntityRenderersEvent.AddLayers event) {
        for (var skin : event.getSkins()) {
            var renderer = event.getSkin(skin);
            if (renderer instanceof PlayerRenderer playerRenderer) {
                playerRenderer.addLayer(new MaskRenderLayer(playerRenderer));
            }
        }
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
        // Regular liquid protodermis — silver-blue, water-like
        event.registerFluidType(waterLikeFluidExt(0xFFC4D8E0),
                MnogiiRegistration.TYPE_PROTODERMIS.get());

        // Pure liquid protodermis — warm gold, water-like
        event.registerFluidType(waterLikeFluidExt(0xFFE8D880),
                MnogiiRegistration.TYPE_PROTODERMIS_PURE.get());

        // Molten protodermis — deep orange-red, lava-like
        event.registerFluidType(lavaLikeFluidExt(0xFFE05020),
                MnogiiRegistration.TYPE_PROTODERMIS_MOLTEN.get());

        // Pure molten protodermis — bright gold, lava-like
        event.registerFluidType(lavaLikeFluidExt(0xFFFFCC00),
                MnogiiRegistration.TYPE_PROTODERMIS_PURE_MOLTEN.get());
    }

    private static IClientFluidTypeExtensions waterLikeFluidExt(int tintColor) {
        ResourceLocation stillLoc   = ResourceLocation.withDefaultNamespace("block/water_still");
        ResourceLocation flowLoc    = ResourceLocation.withDefaultNamespace("block/water_flow");
        ResourceLocation overlayLoc = ResourceLocation.withDefaultNamespace("block/water_overlay");
        ResourceLocation screenOverlayLoc = ResourceLocation.withDefaultNamespace("textures/misc/underwater.png");
        float fogR = ((tintColor >> 16) & 0xFF) / 255.0f;
        float fogG = ((tintColor >> 8)  & 0xFF) / 255.0f;
        float fogB = (tintColor         & 0xFF) / 255.0f;
        return new IClientFluidTypeExtensions() {
            @Override public ResourceLocation getStillTexture()   { return stillLoc; }
            @Override public ResourceLocation getFlowingTexture() { return flowLoc;  }
            @Override public ResourceLocation getOverlayTexture() { return overlayLoc; }
            @Override public int getTintColor()                    { return tintColor; }
            @Override public ResourceLocation getRenderOverlayTexture(Minecraft mc) { return screenOverlayLoc; }
            @Override public Vector4f modifyFogColor(Camera camera, float partialTick,
                    ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor) {
                return new Vector4f(fogR, fogG, fogB, 1.0f);
            }
            @Override public void modifyFogRender(Camera camera, FogEnvironment fogEnv,
                    float partialTick, float renderDistance, FogData fogData) {
                fogData.environmentalStart = -2.0f;
                fogData.environmentalEnd = 12.0f;
            }
        };
    }

    private static IClientFluidTypeExtensions lavaLikeFluidExt(int tintColor) {
        ResourceLocation stillLoc = ResourceLocation.withDefaultNamespace("block/lava_still");
        ResourceLocation flowLoc  = ResourceLocation.withDefaultNamespace("block/lava_flow");
        float fogR = ((tintColor >> 16) & 0xFF) / 255.0f;
        float fogG = ((tintColor >> 8)  & 0xFF) / 255.0f;
        float fogB = (tintColor         & 0xFF) / 255.0f;
        return new IClientFluidTypeExtensions() {
            @Override public ResourceLocation getStillTexture()   { return stillLoc; }
            @Override public ResourceLocation getFlowingTexture() { return flowLoc;  }
            @Override public int getTintColor()                    { return tintColor; }
            @Override public Vector4f modifyFogColor(Camera camera, float partialTick,
                    ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor) {
                return new Vector4f(fogR, fogG, fogB, 1.0f);
            }
            @Override public void modifyFogRender(Camera camera, FogEnvironment fogEnv,
                    float partialTick, float renderDistance, FogData fogData) {
                fogData.environmentalStart = 0.25f;
                fogData.environmentalEnd = 1.5f;
            }
        };
    }
}
