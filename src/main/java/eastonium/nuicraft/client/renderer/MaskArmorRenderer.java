package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import mod.azure.azurelib.common.model.AzBone;
import mod.azure.azurelib.common.render.AzRendererPipelineContext;
import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import mod.azure.azurelib.common.render.armor.AzArmorRendererPipelineContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

/**
 * AzureLib armor renderer for NuiCraft masks. Renders the mask geo on the head slot
 * and offsets the head bone so the mask sits in front of the face instead of on top.
 */
public class MaskArmorRenderer extends AzArmorRenderer {

    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            NuiCraft.MODID, "geo/armor/mask.geo.json");
    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            NuiCraft.MODID, "textures/entity/equipment/humanoid/nuicraft_mask.png");

    /** Push mask in front of face: -Z in model space (forward). Units are 1/16 block. */
    private static final float MASK_FORWARD_OFFSET = -12f;
    /** Lower mask from "on top" to face level. Positive = move down in world. */
    private static final float MASK_DOWN_OFFSET = 10f;

    public MaskArmorRenderer() {
        super(
                AzArmorRendererConfig.builder(GEO, TEX)
                        .setPrerenderEntry(MaskArmorRenderer::pushMaskInFrontOfFace)
                        .build()
        );
    }

    private static AzRendererPipelineContext<UUID, ItemStack> pushMaskInFrontOfFace(
            AzRendererPipelineContext<UUID, ItemStack> context) {
        if (!(context instanceof AzArmorRendererPipelineContext armorContext)
                || armorContext.currentSlot() != EquipmentSlot.HEAD) {
            return context;
        }
        AzBone head = armorContext.boneContext().head;
        if (head == null) {
            return context;
        }
        // Bone position is used as translate(posX/16, posY/16, posZ/16) with X negated.
        // To move mask down: decrease posY (e.g. -24 -> -34). To move forward (-Z): decrease posZ.
        head.updatePosition(
                head.getPosX(),
                head.getPosY() - MASK_DOWN_OFFSET,
                head.getPosZ() + MASK_FORWARD_OFFSET
        );
        return context;
    }
}
