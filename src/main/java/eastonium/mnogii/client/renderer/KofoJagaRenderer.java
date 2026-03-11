package eastonium.mnogii.client.renderer;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.client.model.KofoJagaModel;
import eastonium.mnogii.client.model.MnogiiModelLayers;
import eastonium.mnogii.entity.EntityKofoJaga;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class KofoJagaRenderer extends MobRenderer<EntityKofoJaga, LivingEntityRenderState, KofoJagaModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/entity/kofo_jaga.png");

    public KofoJagaRenderer(EntityRendererProvider.Context context) {
        super(context, new KofoJagaModel(context.bakeLayer(MnogiiModelLayers.KOFO_JAGA)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState state) { return TEXTURE; }

    @Override
    public LivingEntityRenderState createRenderState() { return new LivingEntityRenderState(); }
}
