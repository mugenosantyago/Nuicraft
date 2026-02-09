package eastonium.nuicraft.client.model;

import eastonium.nuicraft.entity.EntityMahi;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class MahiModel<T extends EntityMahi> extends QuadrupedModel<T> {
    
    public MahiModel(ModelPart root) {
        super(root, false, 10.0F, 4.0F, 2.0F, 2.0F, 24);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = QuadrupedModel.createBodyMesh(12, CubeDeformation.NONE);
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("head", 
            CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F),
            PartPose.offset(0.0F, 4.0F, -8.0F));

        partdefinition.addOrReplaceChild("body", 
            CubeListBuilder.create()
                .texOffs(28, 8)
                .addBox(-5.0F, -10.0F, -7.0F, 10.0F, 16.0F, 8.0F),
            PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, ((float)Math.PI / 2F), 0.0F, 0.0F));

        CubeListBuilder legBuilder = CubeListBuilder.create()
            .texOffs(0, 16)
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F);

        partdefinition.addOrReplaceChild("right_hind_leg", legBuilder, PartPose.offset(-3.0F, 12.0F, 7.0F));
        partdefinition.addOrReplaceChild("left_hind_leg", legBuilder, PartPose.offset(3.0F, 12.0F, 7.0F));
        partdefinition.addOrReplaceChild("right_front_leg", legBuilder, PartPose.offset(-3.0F, 12.0F, -5.0F));
        partdefinition.addOrReplaceChild("left_front_leg", legBuilder, PartPose.offset(3.0F, 12.0F, -5.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }
}
