package eastonium.mnogii.client.renderer;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.client.animator.TarakavaAnimator;
import eastonium.mnogii.entity.EntityTarakava;
import mod.azure.azurelib.common.render.entity.AzEntityRenderer;
import mod.azure.azurelib.common.render.entity.AzEntityRendererConfig;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class TarakavaGeoRenderer extends AzEntityRenderer<EntityTarakava> {

    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            Mnogii.MODID, "geo/entity/tarakava.geo.json");
    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            Mnogii.MODID, "textures/entity/tarakava.png");

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
