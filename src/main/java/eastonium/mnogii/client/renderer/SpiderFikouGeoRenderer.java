package eastonium.mnogii.client.renderer;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.client.animator.SpiderFikouAnimator;
import eastonium.mnogii.entity.EntitySpiderFikou;
import mod.azure.azurelib.common.render.entity.AzEntityRenderer;
import mod.azure.azurelib.common.render.entity.AzEntityRendererConfig;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SpiderFikouGeoRenderer extends AzEntityRenderer<EntitySpiderFikou> {

    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            Mnogii.MODID, "geo/spider_fikou.geo.json");
    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            Mnogii.MODID, "textures/entity/spiderfikoutexture.png");

    public SpiderFikouGeoRenderer(EntityRendererProvider.Context context) {
        super(
                AzEntityRendererConfig.<EntitySpiderFikou>builder(GEO, TEX)
                        .setAnimatorProvider(SpiderFikouAnimator::new)
                        .build(),
                context
        );
    }
}
