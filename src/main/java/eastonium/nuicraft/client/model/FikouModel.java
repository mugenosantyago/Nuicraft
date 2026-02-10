package eastonium.nuicraft.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

/**
 * Custom Spider Fikou model ported from bionicle_qfn ModelSpiderFikou.
 * Texture: 64x64 (spiderfikoutexture.png from bionicle_qfn).
 */
public class FikouModel extends EntityModel<LivingEntityRenderState> {
    private static final CubeDeformation DEF = CubeDeformation.NONE;

    public final ModelPart PakariMask;
    public final ModelPart Body;
    public final ModelPart PrimeraPiernaIzquierda;
    public final ModelPart SegundaPiernaIzquierda;
    public final ModelPart TerceraPiernaIzquierda;
    public final ModelPart PrimeraPiernaDerecha;
    public final ModelPart SegundaPiernaDerecha;
    public final ModelPart TerceraPiernaDerecha;

    public FikouModel(ModelPart root) {
        super(root);
        this.PakariMask = root.getChild("PakariMask");
        this.Body = root.getChild("Body");
        this.PrimeraPiernaIzquierda = root.getChild("PrimeraPiernaIzquierda");
        this.SegundaPiernaIzquierda = root.getChild("SegundaPiernaIzquierda");
        this.TerceraPiernaIzquierda = root.getChild("TerceraPiernaIzquierda");
        this.PrimeraPiernaDerecha = root.getChild("PrimeraPiernaDerecha");
        this.SegundaPiernaDerecha = root.getChild("SegundaPiernaDerecha");
        this.TerceraPiernaDerecha = root.getChild("TerceraPiernaDerecha");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("PakariMask", CubeListBuilder.create()
                .texOffs(30, 33).addBox(-2.0f, 30.0f, -9.0f, 4.0f, 1.0f, 2.0f, DEF)
                .texOffs(4, 34).addBox(-2.0f, 19.0f, -9.0f, 4.0f, 1.0f, 1.0f, DEF)
                .texOffs(38, 21).addBox(1.0f, 20.0f, -9.0f, 1.0f, 1.0f, 1.0f, DEF)
                .texOffs(22, 36).addBox(-2.0f, 20.0f, -9.0f, 1.0f, 1.0f, 1.0f, DEF)
                .texOffs(18, 33).addBox(-2.0f, 21.0f, -10.0f, 4.0f, 1.0f, 2.0f, DEF)
                .texOffs(23, 8).addBox(-4.0f, 23.0f, -10.0f, 8.0f, 1.0f, 2.0f, DEF)
                .texOffs(20, 30).addBox(-3.0f, 26.0f, -10.0f, 6.0f, 1.0f, 2.0f, DEF)
                .texOffs(24, 19).addBox(-4.0f, 25.0f, -10.0f, 8.0f, 1.0f, 1.0f, DEF)
                .texOffs(36, 36).addBox(-1.0f, 24.0f, -10.0f, 2.0f, 1.0f, 1.0f, DEF)
                .texOffs(36, 30).addBox(1.0f, 24.0f, -9.0f, 2.0f, 2.0f, 1.0f, DEF)
                .texOffs(30, 36).addBox(-3.0f, 24.0f, -9.0f, 2.0f, 2.0f, 1.0f, DEF)
                .texOffs(6, 36).addBox(-1.0f, 29.0f, -10.0f, 2.0f, 1.0f, 2.0f, DEF)
                .texOffs(37, 14).addBox(2.0f, 28.0f, -9.0f, 1.0f, 2.0f, 1.0f, DEF)
                .texOffs(24, 36).addBox(1.0f, 27.0f, -10.0f, 1.0f, 2.0f, 2.0f, DEF)
                .texOffs(18, 36).addBox(-2.0f, 27.0f, -10.0f, 1.0f, 2.0f, 2.0f, DEF)
                .texOffs(28, 33).addBox(3.0f, 24.0f, -10.0f, 1.0f, 1.0f, 1.0f, DEF)
                .texOffs(18, 30).addBox(-4.0f, 24.0f, -10.0f, 1.0f, 1.0f, 1.0f, DEF)
                .texOffs(0, 34).addBox(4.0f, 23.0f, -9.0f, 1.0f, 4.0f, 2.0f, DEF)
                .texOffs(0, 14).addBox(-5.0f, 23.0f, -9.0f, 1.0f, 4.0f, 2.0f, DEF)
                .texOffs(18, 26).addBox(3.0f, 27.0f, -9.0f, 1.0f, 1.0f, 1.0f, DEF)
                .texOffs(14, 37).addBox(2.0f, 20.0f, -9.0f, 1.0f, 2.0f, 1.0f, DEF)
                .texOffs(12, 34).addBox(2.0f, 22.0f, -10.0f, 2.0f, 1.0f, 2.0f, DEF)
                .texOffs(34, 11).addBox(-4.0f, 22.0f, -10.0f, 2.0f, 1.0f, 2.0f, DEF)
                .texOffs(7, 5).addBox(-3.0f, 20.0f, -9.0f, 1.0f, 2.0f, 1.0f, DEF)
                .texOffs(7, 0).addBox(-3.0f, 28.0f, -9.0f, 1.0f, 2.0f, 1.0f, DEF)
                .texOffs(18, 22).addBox(-4.0f, 27.0f, -9.0f, 1.0f, 1.0f, 1.0f, DEF),
                PartPose.offsetAndRotation(0.0f, 27.0f, 28.0f, -1.5708f, 0.0f, 0.0f));

        root.addOrReplaceChild("Body", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-3.0f, -3.0f, -6.0f, 6.0f, 3.0f, 11.0f, DEF)
                .texOffs(0, 30).addBox(-4.0f, -5.0f, -1.0f, 8.0f, 2.0f, 2.0f, DEF)
                .texOffs(18, 14).addBox(-4.0f, -5.0f, -6.0f, 8.0f, 2.0f, 3.0f, DEF)
                .texOffs(0, 14).addBox(-3.0f, -5.0f, 1.0f, 6.0f, 2.0f, 6.0f, DEF)
                .texOffs(0, 5).addBox(1.0f, -5.0f, -9.0f, 2.0f, 2.0f, 3.0f, DEF)
                .texOffs(0, 0).addBox(-3.0f, -5.0f, -9.0f, 2.0f, 2.0f, 3.0f, DEF),
                PartPose.offset(0.0f, 24.0f, 0.0f));

        PartDefinition PrimeraPiernaIzquierda = root.addOrReplaceChild("PrimeraPiernaIzquierda", CubeListBuilder.create(), PartPose.offset(3.0f, 23.0f, -5.0f));
        PrimeraPiernaIzquierda.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 26).addBox(3.0f, -3.0f, -5.0f, 8.0f, 2.0f, 2.0f, DEF), PartPose.offsetAndRotation(-3.0f, 1.0f, 5.0f, 0.0f, 0.2618f, 0.2618f));

        PartDefinition SegundaPiernaIzquierda = root.addOrReplaceChild("SegundaPiernaIzquierda", CubeListBuilder.create(), PartPose.offset(3.0f, 23.0f, 0.0f));
        SegundaPiernaIzquierda.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(20, 26).addBox(2.0f, -3.0f, -1.0f, 8.0f, 2.0f, 2.0f, DEF), PartPose.offsetAndRotation(-3.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.2618f));

        PartDefinition TerceraPiernaIzquierda = root.addOrReplaceChild("TerceraPiernaIzquierda", CubeListBuilder.create(), PartPose.offset(3.0f, 23.0f, 4.0f));
        TerceraPiernaIzquierda.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(23, 4).addBox(3.0f, -3.0f, 2.0f, 8.0f, 2.0f, 2.0f, DEF), PartPose.offsetAndRotation(-3.0f, 1.0f, -4.0f, 0.0f, -0.2618f, 0.2618f));

        PartDefinition PrimeraPiernaDerecha = root.addOrReplaceChild("PrimeraPiernaDerecha", CubeListBuilder.create(), PartPose.offset(-3.0f, 23.0f, -5.0f));
        PrimeraPiernaDerecha.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(20, 22).addBox(-11.0f, -3.0f, -5.0f, 8.0f, 2.0f, 2.0f, DEF), PartPose.offsetAndRotation(3.0f, 1.0f, 5.0f, 0.0f, -0.2618f, -0.2618f));

        PartDefinition SegundaPiernaDerecha = root.addOrReplaceChild("SegundaPiernaDerecha", CubeListBuilder.create(), PartPose.offset(-3.0f, 23.0f, 0.0f));
        SegundaPiernaDerecha.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(23, 0).addBox(-10.0f, -3.0f, -1.0f, 8.0f, 2.0f, 2.0f, DEF), PartPose.offsetAndRotation(3.0f, 1.0f, 0.0f, 0.0f, 0.0f, -0.2618f));

        PartDefinition TerceraPiernaDerecha = root.addOrReplaceChild("TerceraPiernaDerecha", CubeListBuilder.create(), PartPose.offset(-3.0f, 23.0f, 4.0f));
        TerceraPiernaDerecha.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 22).addBox(-11.0f, -3.0f, 2.0f, 8.0f, 2.0f, 2.0f, DEF), PartPose.offsetAndRotation(3.0f, 1.0f, -4.0f, 0.0f, 0.2618f, -0.2618f));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        float f = state.walkAnimationPos;
        float f1 = state.walkAnimationSpeed;
        TerceraPiernaIzquierda.xRot = Mth.cos(f * 0.6662f) * f1;
        PrimeraPiernaDerecha.xRot = Mth.cos(f * 1.0f) * 1.0f * f1;
        SegundaPiernaDerecha.yRot = Mth.cos(f * 1.0f) * 1.0f * f1;
        SegundaPiernaIzquierda.yRot = Mth.cos(f * 0.6662f) * f1;
        TerceraPiernaDerecha.xRot = Mth.cos(f * 1.0f) * 1.0f * f1;
        PrimeraPiernaIzquierda.xRot = Mth.cos(f * 0.6662f) * f1;
    }
}
