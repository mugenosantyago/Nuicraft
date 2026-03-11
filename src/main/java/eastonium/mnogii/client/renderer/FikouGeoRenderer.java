package eastonium.mnogii.client.renderer;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.client.animator.FikouAnimator;
import eastonium.mnogii.entity.EntityFikou;
import mod.azure.azurelib.common.render.entity.AzEntityRenderer;
import mod.azure.azurelib.common.render.entity.AzEntityRendererConfig;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class FikouGeoRenderer extends AzEntityRenderer<EntityFikou> {

    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            Mnogii.MODID, "geo/fikou.geo.json");
    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            Mnogii.MODID, "textures/entity/fikou.png");

    public FikouGeoRenderer(EntityRendererProvider.Context context) {
        super(
                AzEntityRendererConfig.<EntityFikou>builder(GEO, TEX)
                        .setAnimatorProvider(FikouAnimator::new)
                        .build(),
                context
        );
    }
}
