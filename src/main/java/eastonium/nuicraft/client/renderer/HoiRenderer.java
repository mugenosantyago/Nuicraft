package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.client.model.HoiModel;
import eastonium.nuicraft.client.model.NuiCraftModelLayers;
import eastonium.nuicraft.entity.EntityHoi;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HoiRenderer extends MobRenderer<EntityHoi, LivingEntityRenderState, HoiModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/entity/hoi.png");

    public HoiRenderer(EntityRendererProvider.Context context) {
        super(context, new HoiModel(context.bakeLayer(NuiCraftModelLayers.HOI)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState state) { return TEXTURE; }

    @Override
    public LivingEntityRenderState createRenderState() { return new LivingEntityRenderState(); }
}
