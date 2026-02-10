/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.model.geom.ModelLayerLocation
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeDeformation
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.LayerDefinition
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.client.model.geom.builders.PartDefinition
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 */
package bionicleqfn.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class ModelSpiderFikou<T extends Entity>
extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("bionicle_qfn", "model_spider_fikou"), "main");
    public final ModelPart PakariMask;
    public final ModelPart Body;
    public final ModelPart PrimeraPiernaIzquierda;
    public final ModelPart SegundaPiernaIzquierda;
    public final ModelPart TerceraPiernaIzquierda;
    public final ModelPart PrimeraPiernaDerecha;
    public final ModelPart SegundaPiernaDerecha;
    public final ModelPart TerceraPiernaDerecha;

    public ModelSpiderFikou(ModelPart root) {
        this.PakariMask = root.m_171324_("PakariMask");
        this.Body = root.m_171324_("Body");
        this.PrimeraPiernaIzquierda = root.m_171324_("PrimeraPiernaIzquierda");
        this.SegundaPiernaIzquierda = root.m_171324_("SegundaPiernaIzquierda");
        this.TerceraPiernaIzquierda = root.m_171324_("TerceraPiernaIzquierda");
        this.PrimeraPiernaDerecha = root.m_171324_("PrimeraPiernaDerecha");
        this.SegundaPiernaDerecha = root.m_171324_("SegundaPiernaDerecha");
        this.TerceraPiernaDerecha = root.m_171324_("TerceraPiernaDerecha");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.m_171576_();
        PartDefinition PakariMask = partdefinition.m_171599_("PakariMask", CubeListBuilder.m_171558_().m_171514_(30, 33).m_171488_(-2.0f, 30.0f, -9.0f, 4.0f, 1.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(4, 34).m_171488_(-2.0f, 19.0f, -9.0f, 4.0f, 1.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(38, 21).m_171488_(1.0f, 20.0f, -9.0f, 1.0f, 1.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(22, 36).m_171488_(-2.0f, 20.0f, -9.0f, 1.0f, 1.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(18, 33).m_171488_(-2.0f, 21.0f, -10.0f, 4.0f, 1.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(23, 8).m_171488_(-4.0f, 23.0f, -10.0f, 8.0f, 1.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(20, 30).m_171488_(-3.0f, 26.0f, -10.0f, 6.0f, 1.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(24, 19).m_171488_(-4.0f, 25.0f, -10.0f, 8.0f, 1.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(36, 36).m_171488_(-1.0f, 24.0f, -10.0f, 2.0f, 1.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(36, 30).m_171488_(1.0f, 24.0f, -9.0f, 2.0f, 2.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(30, 36).m_171488_(-3.0f, 24.0f, -9.0f, 2.0f, 2.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(6, 36).m_171488_(-1.0f, 29.0f, -10.0f, 2.0f, 1.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(37, 14).m_171488_(2.0f, 28.0f, -9.0f, 1.0f, 2.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(24, 36).m_171488_(1.0f, 27.0f, -10.0f, 1.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(18, 36).m_171488_(-2.0f, 27.0f, -10.0f, 1.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(28, 33).m_171488_(3.0f, 24.0f, -10.0f, 1.0f, 1.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(18, 30).m_171488_(-4.0f, 24.0f, -10.0f, 1.0f, 1.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(0, 34).m_171488_(4.0f, 23.0f, -9.0f, 1.0f, 4.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(0, 14).m_171488_(-5.0f, 23.0f, -9.0f, 1.0f, 4.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(18, 26).m_171488_(3.0f, 27.0f, -9.0f, 1.0f, 1.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(14, 37).m_171488_(2.0f, 20.0f, -9.0f, 1.0f, 2.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(12, 34).m_171488_(2.0f, 22.0f, -10.0f, 2.0f, 1.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(34, 11).m_171488_(-4.0f, 22.0f, -10.0f, 2.0f, 1.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(7, 5).m_171488_(-3.0f, 20.0f, -9.0f, 1.0f, 2.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(7, 0).m_171488_(-3.0f, 28.0f, -9.0f, 1.0f, 2.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(18, 22).m_171488_(-4.0f, 27.0f, -9.0f, 1.0f, 1.0f, 1.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)0.0f, (float)27.0f, (float)28.0f, (float)-1.5708f, (float)0.0f, (float)0.0f));
        PartDefinition Body = partdefinition.m_171599_("Body", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-3.0f, -3.0f, -6.0f, 6.0f, 3.0f, 11.0f, new CubeDeformation(0.0f)).m_171514_(0, 30).m_171488_(-4.0f, -5.0f, -1.0f, 8.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(18, 14).m_171488_(-4.0f, -5.0f, -6.0f, 8.0f, 2.0f, 3.0f, new CubeDeformation(0.0f)).m_171514_(0, 14).m_171488_(-3.0f, -5.0f, 1.0f, 6.0f, 2.0f, 6.0f, new CubeDeformation(0.0f)).m_171514_(0, 5).m_171488_(1.0f, -5.0f, -9.0f, 2.0f, 2.0f, 3.0f, new CubeDeformation(0.0f)).m_171514_(0, 0).m_171488_(-3.0f, -5.0f, -9.0f, 2.0f, 2.0f, 3.0f, new CubeDeformation(0.0f)), PartPose.m_171419_((float)0.0f, (float)24.0f, (float)0.0f));
        PartDefinition PrimeraPiernaIzquierda = partdefinition.m_171599_("PrimeraPiernaIzquierda", CubeListBuilder.m_171558_(), PartPose.m_171419_((float)3.0f, (float)23.0f, (float)-5.0f));
        PartDefinition cube_r1 = PrimeraPiernaIzquierda.m_171599_("cube_r1", CubeListBuilder.m_171558_().m_171514_(0, 26).m_171488_(3.0f, -3.0f, -5.0f, 8.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)-3.0f, (float)1.0f, (float)5.0f, (float)0.0f, (float)0.2618f, (float)0.2618f));
        PartDefinition SegundaPiernaIzquierda = partdefinition.m_171599_("SegundaPiernaIzquierda", CubeListBuilder.m_171558_(), PartPose.m_171419_((float)3.0f, (float)23.0f, (float)0.0f));
        PartDefinition cube_r2 = SegundaPiernaIzquierda.m_171599_("cube_r2", CubeListBuilder.m_171558_().m_171514_(20, 26).m_171488_(2.0f, -3.0f, -1.0f, 8.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)-3.0f, (float)1.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.2618f));
        PartDefinition TerceraPiernaIzquierda = partdefinition.m_171599_("TerceraPiernaIzquierda", CubeListBuilder.m_171558_(), PartPose.m_171419_((float)3.0f, (float)23.0f, (float)4.0f));
        PartDefinition cube_r3 = TerceraPiernaIzquierda.m_171599_("cube_r3", CubeListBuilder.m_171558_().m_171514_(23, 4).m_171488_(3.0f, -3.0f, 2.0f, 8.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)-3.0f, (float)1.0f, (float)-4.0f, (float)0.0f, (float)-0.2618f, (float)0.2618f));
        PartDefinition PrimeraPiernaDerecha = partdefinition.m_171599_("PrimeraPiernaDerecha", CubeListBuilder.m_171558_(), PartPose.m_171419_((float)-3.0f, (float)23.0f, (float)-5.0f));
        PartDefinition cube_r4 = PrimeraPiernaDerecha.m_171599_("cube_r4", CubeListBuilder.m_171558_().m_171514_(20, 22).m_171488_(-11.0f, -3.0f, -5.0f, 8.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)3.0f, (float)1.0f, (float)5.0f, (float)0.0f, (float)-0.2618f, (float)-0.2618f));
        PartDefinition SegundaPiernaDerecha = partdefinition.m_171599_("SegundaPiernaDerecha", CubeListBuilder.m_171558_(), PartPose.m_171419_((float)-3.0f, (float)23.0f, (float)0.0f));
        PartDefinition cube_r5 = SegundaPiernaDerecha.m_171599_("cube_r5", CubeListBuilder.m_171558_().m_171514_(23, 0).m_171488_(-10.0f, -3.0f, -1.0f, 8.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)3.0f, (float)1.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)-0.2618f));
        PartDefinition TerceraPiernaDerecha = partdefinition.m_171599_("TerceraPiernaDerecha", CubeListBuilder.m_171558_(), PartPose.m_171419_((float)-3.0f, (float)23.0f, (float)4.0f));
        PartDefinition cube_r6 = TerceraPiernaDerecha.m_171599_("cube_r6", CubeListBuilder.m_171558_().m_171514_(0, 22).m_171488_(-11.0f, -3.0f, 2.0f, 8.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)3.0f, (float)1.0f, (float)-4.0f, (float)0.0f, (float)0.2618f, (float)-0.2618f));
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)64, (int)64);
    }

    public void m_7695_(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.PakariMask.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Body.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.PrimeraPiernaIzquierda.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.SegundaPiernaIzquierda.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TerceraPiernaIzquierda.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.PrimeraPiernaDerecha.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.SegundaPiernaDerecha.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TerceraPiernaDerecha.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void m_6973_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.TerceraPiernaIzquierda.f_104204_ = Mth.m_14089_((float)(limbSwing * 0.6662f)) * limbSwingAmount;
        this.PrimeraPiernaDerecha.f_104204_ = Mth.m_14089_((float)(limbSwing * 1.0f)) * 1.0f * limbSwingAmount;
        this.SegundaPiernaDerecha.f_104205_ = Mth.m_14089_((float)(limbSwing * 1.0f)) * 1.0f * limbSwingAmount;
        this.SegundaPiernaIzquierda.f_104205_ = Mth.m_14089_((float)(limbSwing * 0.6662f)) * limbSwingAmount;
        this.TerceraPiernaDerecha.f_104204_ = Mth.m_14089_((float)(limbSwing * 1.0f)) * 1.0f * limbSwingAmount;
        this.PrimeraPiernaIzquierda.f_104204_ = Mth.m_14089_((float)(limbSwing * 0.6662f)) * limbSwingAmount;
    }
}
