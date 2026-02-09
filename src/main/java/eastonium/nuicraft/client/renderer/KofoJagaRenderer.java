package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.entity.EntityKofoJaga;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class KofoJagaRenderer extends MobRenderer<EntityKofoJaga, SpiderModel<EntityKofoJaga>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/entity/kofo_jaga.png");

    public KofoJagaRenderer(EntityRendererProvider.Context context) {
        super(context, new SpiderModel<>(context.bakeLayer(ModelLayers.SPIDER)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityKofoJaga entity) {
        return TEXTURE;
    }
}
