package eastonium.nuicraft.morph;

import eastonium.nuicraft.NuiCraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class NuiCraftAttachments {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, NuiCraft.MODID);

    /** Stores the morph state (NONE / MATORAN / TOA) on each player. */
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<MorphState>> MORPH_STATE =
            ATTACHMENT_TYPES.register("morph_state", () ->
                    AttachmentType.builder(() -> MorphState.NONE)
                            .serialize(MorphState.MAP_CODEC)
                            .build()
            );

    public static void register(IEventBus modEventBus) {
        ATTACHMENT_TYPES.register(modEventBus);
    }
}
