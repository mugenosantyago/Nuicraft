package eastonium.nuicraft.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

/**
 * 2D flat mask rendered in front of the face - a single quad aligned
 * to the head, positioned in front like a proper face mask.
 */
public class MaskModel extends EntityModel<LivingEntityRenderState> {
    public MaskModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createMaskLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // Flat quad in front of face. Face is -Z in head-local space (addBox uses same coords as head box).
        // Head box is (-4,-8,-4) to (4,0,4); face is at Z=-4. Put plane just in front at Z=-4.5.
        root.addOrReplaceChild("mask", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-4.0F, -8.0F, -4.5F, 8.0F, 8.0F, 0.05F),
                PartPose.ZERO);

        return LayerDefinition.create(mesh, 32, 32);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
    }
}
