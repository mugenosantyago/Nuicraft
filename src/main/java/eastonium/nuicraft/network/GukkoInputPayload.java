package eastonium.nuicraft.network;

import eastonium.nuicraft.NuiCraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Payload sent from client to server when the player is riding a Gukko, to sync movement input.
 */
public record GukkoInputPayload(int entityId, boolean forward, boolean back, boolean left, boolean right, boolean up, boolean down) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<GukkoInputPayload> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "gukko_input"));

    public static final StreamCodec<FriendlyByteBuf, GukkoInputPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            GukkoInputPayload::entityId,
            ByteBufCodecs.BOOL,
            GukkoInputPayload::forward,
            ByteBufCodecs.BOOL,
            GukkoInputPayload::back,
            ByteBufCodecs.BOOL,
            GukkoInputPayload::left,
            ByteBufCodecs.BOOL,
            GukkoInputPayload::right,
            ByteBufCodecs.BOOL,
            GukkoInputPayload::up,
            ByteBufCodecs.BOOL,
            GukkoInputPayload::down,
            GukkoInputPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
