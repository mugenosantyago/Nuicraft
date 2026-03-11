package eastonium.mnogii.client.renderer;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.entity.EntityMatoran;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

/**
 * Renders Matoran with variant-specific texture (and later geo) by koro + mask.
 * <ul>
 *   <li>Texture: textures/entity/matoran_{koro}_{mask}.png (e.g. matoran_ta_pakari.png).</li>
 *   <li>When using geo: geo/matoran_{koro}_{mask}.geo.json (same variant key).</li>
 * </ul>
 * 6 koros (ta, ga, po, onu, le, ko) × 12 masks = 72 variants. Spawn color from biome; mask random.
 */
public class MatoranRenderer extends MobRenderer<EntityMatoran, MatoranRenderState, PigModel> {

    public MatoranRenderer(EntityRendererProvider.Context context) {
        super(context, new PigModel(context.bakeLayer(ModelLayers.PIG)), 0.5F);
    }

    @Override
    public void extractRenderState(EntityMatoran entity, MatoranRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.koroOrdinal = entity.getKoro().ordinal();
        state.maskOrdinal = entity.getMask().ordinal();
    }

    @Override
    public ResourceLocation getTextureLocation(MatoranRenderState state) {
        String koroName = entityKoroName(state.koroOrdinal);
        String maskName = entityMaskName(state.maskOrdinal);
        String variant = "matoran_" + koroName + "_" + maskName;
        return ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/entity/" + variant + ".png");
    }

    @Override
    public MatoranRenderState createRenderState() {
        return new MatoranRenderState();
    }

    private static String entityKoroName(int ordinal) {
        String[] names = { "ta", "ga", "po", "onu", "le", "ko" };
        return ordinal >= 0 && ordinal < names.length ? names[ordinal] : "ta";
    }

    private static String entityMaskName(int ordinal) {
        String[] names = { "hau", "kaukau", "miru", "kakama", "pakari", "akaku", "huna", "mahiki", "matatu", "komau", "raru", "ruru" };
        return ordinal >= 0 && ordinal < names.length ? names[ordinal] : "pakari";
    }
}
