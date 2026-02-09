package eastonium.nuicraft.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

/**
 * Custom Bionicle mask model that extends forward from the face
 * instead of being a flat cube helmet
 */
public class MaskModel extends EntityModel<LivingEntityRenderState> {
    private final ModelPart mask;

    public MaskModel(ModelPart root) {
        super(root);
        this.mask = root.getChild("mask");
    }

    public static LayerDefinition createMaskLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition maskPart = root.addOrReplaceChild("mask", CubeListBuilder.create()
                // Main face plate - extends forward
                .texOffs(0, 0)
                .addBox(-4.0F, -8.0F, -5.5F, 8.0F, 8.0F, 1.5F)
                // Top ridge
                .texOffs(0, 10)
                .addBox(-3.0F, -9.0F, -5.0F, 6.0F, 1.0F, 5.0F)
                // Brow ridge
                .texOffs(0, 16)
                .addBox(-4.5F, -6.0F, -5.5F, 9.0F, 2.0F, 1.0F)
                // Nose/mouth piece extending forward  
                .texOffs(0, 19)
                .addBox(-2.0F, -4.0F, -7.0F, 4.0F, 3.0F, 2.0F)
                // Cheek guards - left
                .texOffs(20, 0)
                .addBox(-5.0F, -6.0F, -4.5F, 1.0F, 5.0F, 4.0F)
                // Cheek guards - right
                .texOffs(20, 0)
                .addBox(4.0F, -6.0F, -4.5F, 1.0F, 5.0F, 4.0F),
                PartPose.ZERO);

        return LayerDefinition.create(mesh, 32, 32);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        this.mask.yRot = state.yRot * ((float) Math.PI / 180F);
        this.mask.xRot = state.xRot * ((float) Math.PI / 180F);
    }
}
