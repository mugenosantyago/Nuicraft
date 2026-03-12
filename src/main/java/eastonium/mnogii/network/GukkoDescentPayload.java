package eastonium.mnogii.network;

import eastonium.mnogii.Mnogii;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Sent from client to server every tick while riding a Gukko to communicate
 * whether the player is holding the sneak key (Shift) for descent.
 *
 * This is necessary because pressing Shift while riding normally sends
 * STOP_RIDING to the server instead of START_SNEAKING, so
 * driver.isShiftKeyDown() is always false on the server side.
 */
public record GukkoDescentPayload(boolean descending) implements CustomPacketPayload {

    public static final Type<GukkoDescentPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "gukko_descent"));

    public static final StreamCodec<FriendlyByteBuf, GukkoDescentPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            GukkoDescentPayload::descending,
            GukkoDescentPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
