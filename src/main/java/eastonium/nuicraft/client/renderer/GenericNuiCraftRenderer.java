package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.world.entity.Mob;
import net.minecraft.resources.ResourceLocation;

/**
 * Generic renderer for NuiCraft mobs that don't have custom models.
 * Falls back to humanoid model to prevent null renderer crashes.
 */
public class GenericNuiCraftRenderer<T extends Mob> extends MobRenderer<T, HumanoidRenderState, HumanoidModel<HumanoidRenderState>> {
    private final String entityName;

    public GenericNuiCraftRenderer(EntityRendererProvider.Context context, String entityName) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5F);
        this.entityName = entityName;
    }

    @Override
    public ResourceLocation getTextureLocation(HumanoidRenderState state) {
        return ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/entity/" + entityName + ".png");
    }

    @Override
    public HumanoidRenderState createRenderState() {
        return new HumanoidRenderState();
    }
}
