package eastonium.nuicraft.network;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.morph.MorphState;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

/**
 * Server → Client: broadcast a player's morph state to all nearby clients
 * (including the morphing player themselves) so they can render it correctly.
 */
public record MorphBroadcastPayload(UUID playerUUID, MorphState state) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<MorphBroadcastPayload> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "morph_broadcast"));

    public static final StreamCodec<FriendlyByteBuf, MorphBroadcastPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8.map(UUID::fromString, UUID::toString),
            MorphBroadcastPayload::playerUUID,
            StreamCodec.of(
                    (buf, s) -> buf.writeByte(s.ordinal()),
                    buf -> MorphState.values()[Math.min(buf.readByte(), MorphState.values().length - 1)]
            ),
            MorphBroadcastPayload::state,
            MorphBroadcastPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }
}
