package eastonium.nuicraft.network;

import eastonium.nuicraft.NuiCraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Payload sent from server to client to open the NPC dialogue screen.
 */
public record OpenDialoguePayload(String dialogueType) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<OpenDialoguePayload> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "open_dialogue"));

    public static final StreamCodec<FriendlyByteBuf, OpenDialoguePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            OpenDialoguePayload::dialogueType,
            OpenDialoguePayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
