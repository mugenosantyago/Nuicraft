package eastonium.mnogii.client.renderer;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

/**
 * Render state for Matoran: carries koro + mask so the renderer can choose
 * texture (and later geo) by variant. Path pattern: matoran_{koro}_{mask}
 * (e.g. matoran_ta_pakari, matoran_ga_hau).
 */
@net.neoforged.api.distmarker.OnlyIn(net.neoforged.api.distmarker.Dist.CLIENT)
public class MatoranRenderState extends LivingEntityRenderState {
    /** Koro ordinal (color: TA=red, GA=blue, PO=brown, ONU=black, KO=white, LE=green). */
    public int koroOrdinal;
    /** Mask ordinal (12 Kanohi Mata). */
    public int maskOrdinal;
}
