package eastonium.mnogii.client.renderer;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.client.model.FikouModel;
import eastonium.mnogii.client.model.MnogiiModelLayers;
import eastonium.mnogii.entity.EntityFikou;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class FikouRenderer extends MobRenderer<EntityFikou, LivingEntityRenderState, FikouModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/entity/fikou.png");

    public FikouRenderer(EntityRendererProvider.Context context) {
        super(context, new FikouModel(context.bakeLayer(MnogiiModelLayers.FIKOU)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState state) { return TEXTURE; }

    @Override
    public LivingEntityRenderState createRenderState() { return new LivingEntityRenderState(); }
}
