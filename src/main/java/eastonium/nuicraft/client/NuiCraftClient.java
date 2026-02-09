package eastonium.nuicraft.client;

import com.mojang.blaze3d.platform.NativeImage;
import eastonium.nuicraft.client.model.*;
import eastonium.nuicraft.client.renderer.*;
import eastonium.nuicraft.core.NuiCraftEntityTypes;
import eastonium.nuicraft.core.NuiCraftItems;
import eastonium.nuicraft.NuiCraft;
import mod.azure.azurelib.common.render.armor.AzArmorRendererRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.PreparableReloadListener.PreparationBarrier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.AddClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class NuiCraftClient {

    public static void registerModBusEvents(IEventBus modEventBus) {
        modEventBus.addListener(NuiCraftClient::registerLayerDefinitions);
        modEventBus.addListener(NuiCraftClient::registerRenderers);
        modEventBus.addListener(NuiCraftClient::onClientSetup);
        modEventBus.addListener(NuiCraftClient::onAddReloadListeners);
    }

    /** Manually load mask texture from mod resources and register it so it is available for 3D armor rendering. */
    private static void onAddReloadListeners(AddClientReloadListenersEvent event) {
        event.addListener(
                ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "mask_armor_texture"),
                new PreparableReloadListener() {
                    @Override
                    public CompletableFuture<Void> reload(
                            PreparationBarrier stage,
                            ResourceManager resourceManager,
                            Executor backgroundExecutor,
                            Executor gameExecutor
                    ) {
                        CompletableFuture<NativeImage> loadImage = CompletableFuture.supplyAsync(() -> {
                            try {
                                var res = resourceManager.getResource(MaskArmorRenderer.TEX).orElse(null);
                                if (res == null) {
                                    var withPng = ResourceLocation.fromNamespaceAndPath(
                                            NuiCraft.MODID,
                                            "textures/entity/equipment/humanoid/nuicraft_mask.png");
                                    res = resourceManager.getResource(withPng).orElse(null);
                                }
                                if (res != null) {
                                    try (var in = res.open()) {
                                        return NativeImage.read(in);
                                    }
                                }
                            } catch (Exception e) {
                                NuiCraft.LOGGER.warn("Failed to load mask armor texture: {}", e.getMessage());
                            }
                            return null;
                        }, backgroundExecutor);

                        return loadImage
                                .thenCompose(stage::wait)
                                .thenAcceptAsync(nativeImage -> {
                                    var tm = Minecraft.getInstance().getTextureManager();
                                    tm.release(MaskArmorRenderer.TEX);
                                    if (nativeImage != null) {
                                        try {
                                            tm.register(MaskArmorRenderer.TEX,
                                                    new DynamicTexture(MaskArmorRenderer.TEX::toString, nativeImage));
                                        } catch (Exception e) {
                                            nativeImage.close();
                                            NuiCraft.LOGGER.warn("Failed to register mask armor texture: {}", e.getMessage());
                                        }
                                    }
                                }, gameExecutor);
                    }
                }
        );
    }

    private static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(NuiCraftModelLayers.MASK, MaskModel::createMaskLayer);
        event.registerLayerDefinition(NuiCraftModelLayers.MAHI, MahiModel::createBodyLayer);
        event.registerLayerDefinition(NuiCraftModelLayers.FIKOU, FikouModel::createBodyLayer);
        event.registerLayerDefinition(NuiCraftModelLayers.HOI, HoiModel::createBodyLayer);
        event.registerLayerDefinition(NuiCraftModelLayers.KOFO_JAGA, KofoJagaModel::createBodyLayer);
        event.registerLayerDefinition(NuiCraftModelLayers.NUI_JAGA, NuiJagaModel::createBodyLayer);
    }

    private static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> AzArmorRendererRegistry.register(
                MaskArmorRenderer::new,
                NuiCraftItems.MASK_MATA_GOLD.get(),
                NuiCraftItems.MASK_MATA_KAKAMA.get(),
                NuiCraftItems.MASK_MATA_PAKARI.get(),
                NuiCraftItems.MASK_MATA_KAUKAU.get(),
                NuiCraftItems.MASK_MATA_MIRU.get(),
                NuiCraftItems.MASK_MATA_HAU.get(),
                NuiCraftItems.MASK_MATA_AKAKU.get(),
                NuiCraftItems.MASK_NUVA_KAKAMA.get(),
                NuiCraftItems.MASK_NUVA_PAKARI.get(),
                NuiCraftItems.MASK_NUVA_KAUKAU.get(),
                NuiCraftItems.MASK_NUVA_MIRU.get(),
                NuiCraftItems.MASK_NUVA_HAU.get(),
                NuiCraftItems.MASK_NUVA_AKAKU.get(),
                NuiCraftItems.MASK_IGNIKA.get(),
                NuiCraftItems.MASK_VAHI.get()
        ));
    }

    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(NuiCraftEntityTypes.MAHI.get(), MahiRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.FIKOU.get(), FikouRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.HOI.get(), HoiRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.KOFO_JAGA.get(), KofoJagaRenderer::new);
        event.registerEntityRenderer(NuiCraftEntityTypes.NUI_JAGA.get(), NuiJagaRenderer::new);
    }
}
