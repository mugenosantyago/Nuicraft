package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.client.model.KofoJagaModel;
import eastonium.nuicraft.client.model.NuiCraftModelLayers;
import eastonium.nuicraft.entity.EntityKofoJaga;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KofoJagaRenderer extends MobRenderer<EntityKofoJaga, LivingEntityRenderState, KofoJagaModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/entity/kofo_jaga.png");

    public KofoJagaRenderer(EntityRendererProvider.Context context) {
        super(context, new KofoJagaModel(context.bakeLayer(NuiCraftModelLayers.KOFO_JAGA)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState state) { return TEXTURE; }

    @Override
    public LivingEntityRenderState createRenderState() { return new LivingEntityRenderState(); }
}
