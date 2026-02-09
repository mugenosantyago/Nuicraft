package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.client.model.FikouModel;
import eastonium.nuicraft.client.model.NuiCraftModelLayers;
import eastonium.nuicraft.entity.EntityFikou;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class FikouRenderer extends MobRenderer<EntityFikou, LivingEntityRenderState, FikouModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/entity/fikou.png");

    public FikouRenderer(EntityRendererProvider.Context context) {
        super(context, new FikouModel(context.bakeLayer(NuiCraftModelLayers.FIKOU)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState state) { return TEXTURE; }

    @Override
    public LivingEntityRenderState createRenderState() { return new LivingEntityRenderState(); }
}
