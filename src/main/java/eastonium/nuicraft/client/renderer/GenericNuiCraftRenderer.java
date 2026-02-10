package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.world.entity.Mob;
import net.minecraft.resources.ResourceLocation;

/**
 * Generic renderer for NuiCraft mobs that don't have custom models.
 * Uses PigModel as a fallback - it's a simple quadruped model.
 */
public class GenericNuiCraftRenderer<T extends Mob> extends MobRenderer<T, LivingEntityRenderState, PigModel> {
    private final String entityName;

    public GenericNuiCraftRenderer(EntityRendererProvider.Context context, String entityName) {
        super(context, new PigModel(context.bakeLayer(ModelLayers.PIG)), 0.5F);
        this.entityName = entityName;
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
        return ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/entity/" + entityName + ".png");
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
