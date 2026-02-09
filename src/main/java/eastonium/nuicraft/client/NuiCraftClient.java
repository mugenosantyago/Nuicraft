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

    /** Manually load all mask textures so they are available for 3D armor rendering. */
    private static void onAddReloadListeners(AddClientReloadListenersEvent event) {
        event.addListener(
                ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "mask_armor_textures"),
                new PreparableReloadListener() {
                    @Override
                    public CompletableFuture<Void> reload(
                            PreparationBarrier stage,
                            ResourceManager resourceManager,
                            Executor backgroundExecutor,
                            Executor gameExecutor
                    ) {
                        CompletableFuture<java.util.List<java.util.Map.Entry<ResourceLocation, NativeImage>>> loadAll =
                                CompletableFuture.supplyAsync(() -> {
                                    var list = new java.util.ArrayList<java.util.Map.Entry<ResourceLocation, NativeImage>>();
                                    for (ResourceLocation tex : MaskArmorRenderer.getMaskTextureLocations()) {
                                        try {
                                            var res = resourceManager.getResource(tex).orElse(null);
                                            if (res == null) {
                                                res = resourceManager.getResource(ResourceLocation.fromNamespaceAndPath(
                                                        tex.getNamespace(), tex.getPath() + ".png")).orElse(null);
                                            }
                                            if (res != null) {
                                                try (var in = res.open()) {
                                                    list.add(java.util.Map.entry(tex, NativeImage.read(in)));
                                                }
                                            }
                                        } catch (Exception ignored) {
                                        }
                                    }
                                    return list;
                                }, backgroundExecutor);

                        return loadAll
                                .thenCompose(stage::wait)
                                .thenAcceptAsync(list -> {
                                    var tm = Minecraft.getInstance().getTextureManager();
                                    for (var e : list) {
                                        try {
                                            tm.release(e.getKey());
                                            tm.register(e.getKey(), new DynamicTexture(e.getKey()::toString, e.getValue()));
                                        } catch (Exception ex) {
                                            e.getValue().close();
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
