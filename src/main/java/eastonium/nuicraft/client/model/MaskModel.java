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

        // Single flat quad in front of face - thin plane at z = -4.5 (forward from head)
        // Covers face area roughly -4..4 X, -8..0 Y
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
