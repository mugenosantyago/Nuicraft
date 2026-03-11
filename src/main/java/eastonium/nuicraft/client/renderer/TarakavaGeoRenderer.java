package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.client.animator.TarakavaAnimator;
import eastonium.nuicraft.entity.EntityTarakava;
import mod.azure.azurelib.common.render.entity.AzEntityRenderer;
import mod.azure.azurelib.common.render.entity.AzEntityRendererConfig;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class TarakavaGeoRenderer extends AzEntityRenderer<EntityTarakava> {

    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            NuiCraft.MODID, "geo/entity/tarakava.geo.json");
    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            NuiCraft.MODID, "textures/entity/tarakava.png");

    public TarakavaGeoRenderer(EntityRendererProvider.Context context) {
        super(
                AzEntityRendererConfig.<EntityTarakava>builder(GEO, TEX)
                        .setAnimatorProvider(TarakavaAnimator::new)
                        .setShadowRadius(1.0f)
                        .build(),
                context
        );
    }
}
