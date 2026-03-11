package eastonium.mnogii.network;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.morph.MorphState;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Client → Server: player pressed M or T to request a morph toggle.
 * Server validates the request (must have a mask equipped) and either
 * sets the new state or reverts to NONE.
 */
public record MorphRequestPayload(MorphState requested) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<MorphRequestPayload> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "morph_request"));

    public static final StreamCodec<FriendlyByteBuf, MorphRequestPayload> STREAM_CODEC =
            StreamCodec.of(
                    (buf, p) -> buf.writeByte(p.requested().ordinal()),
                    buf -> new MorphRequestPayload(
                            MorphState.values()[Math.min(buf.readByte(), MorphState.values().length - 1)])
            );

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }
}
