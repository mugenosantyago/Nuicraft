package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.entity.EntityFikou;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FikouRenderer extends MobRenderer<EntityFikou, SpiderModel<EntityFikou>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/entity/fikou.png");

    public FikouRenderer(EntityRendererProvider.Context context) {
        super(context, new SpiderModel<>(context.bakeLayer(ModelLayers.SPIDER)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityFikou entity) {
        return TEXTURE;
    }
}
