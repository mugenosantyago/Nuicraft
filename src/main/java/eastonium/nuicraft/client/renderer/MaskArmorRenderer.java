package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import mod.azure.azurelib.common.model.AzBone;
import mod.azure.azurelib.common.render.AzRendererPipelineContext;
import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import mod.azure.azurelib.common.render.armor.AzArmorRendererPipelineContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * AzureLib armor renderer for NuiCraft masks. Renders the mask geo on the head slot
 * and offsets the head bone so the mask sits in front of the face instead of on top.
 */
public class MaskArmorRenderer extends AzArmorRenderer {

    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            NuiCraft.MODID, "geo/armor/mask.geo.json");
    /** Armor texture; file at textures/entity/equipment/humanoid/nuicraft_mask.png (vanilla-style armor path). */
    public static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            NuiCraft.MODID, "textures/entity/equipment/humanoid/nuicraft_mask");

    /** Nudge mask forward in front of face (negative Z in model space). Units are 1/16 block. */
    private static final float MASK_FORWARD_OFFSET = -3f;
    /** No vertical offset - geo cube is already at face level (Y 21-28). */
    private static final float MASK_DOWN_OFFSET = 0f;

    public MaskArmorRenderer() {
        super(
                AzArmorRendererConfig.builder(GEO, TEX)
                        .setRenderType(RenderType.entityTranslucent(TEX))
                        .setPrerenderEntry(MaskArmorRenderer::pushMaskInFrontOfFace)
                        .setBoneTextureOverrideProvider(MaskArmorRenderer::maskTextureForBone)
                        .build()
        );
    }

    /** Use mask texture for head bone so the correct texture is bound when rendering the quad. */
    @Nullable
    private static ResourceLocation maskTextureForBone(AzBone bone) {
        return bone != null && "armorHead".equals(bone.getName()) ? TEX : null;
    }

    /** Force-load mask texture into TextureManager so it is available when the armor is drawn. */
    private static void ensureTextureLoaded() {
        try {
            Minecraft.getInstance().getTextureManager().getTexture(TEX);
        } catch (Exception ignored) {
            // Texture may be missing; renderer will show placeholder
        }
    }

    @Override
    public void prepForRender(
            @Nullable Entity entity,
            ItemStack stack,
            @Nullable EquipmentSlot slot,
            @Nullable HumanoidModel<?> baseModel
    ) {
        ensureTextureLoaded();
        super.prepForRender(entity, stack, slot, baseModel);
    }

    @Override
    public void prepForRenderWithoutEntity(
            ItemStack stack,
            @Nullable EquipmentSlot slot,
            @Nullable HumanoidModel<?> baseModel
    ) {
        ensureTextureLoaded();
        super.prepForRenderWithoutEntity(stack, slot, baseModel);
    }

    private static AzRendererPipelineContext<UUID, ItemStack> pushMaskInFrontOfFace(
            AzRendererPipelineContext<UUID, ItemStack> context) {
        if (!(context instanceof AzArmorRendererPipelineContext armorContext)
                || armorContext.currentSlot() != EquipmentSlot.HEAD) {
            return context;
        }
        context.setTextureOverride(TEX);
        AzBone head = armorContext.boneContext().head;
        if (head == null) {
            return context;
        }
        // Only adjust position so the quad stays at head/face. Do not change pivot (keeps cube in correct place).
        // Large down offset would move the quad to waist/legs and appear as "flat thing in front of pants".
        head.updatePosition(
                head.getPosX(),
                head.getPosY() - MASK_DOWN_OFFSET,
                head.getPosZ() + MASK_FORWARD_OFFSET
        );
        return context;
    }
}
