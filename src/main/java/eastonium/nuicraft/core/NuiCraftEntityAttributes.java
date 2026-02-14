package eastonium.nuicraft.core;

import eastonium.nuicraft.entity.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

public class NuiCraftEntityAttributes {

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        // Passive Rahi
        event.put(NuiCraftEntityTypes.MAHI.get(), EntityMahi.createAttributes().build());
        event.put(NuiCraftEntityTypes.FIKOU.get(), EntityFikou.createAttributes().build());
        event.put(NuiCraftEntityTypes.HOI.get(), EntityHoi.createAttributes().build());

        // Hostile Rahi
        event.put(NuiCraftEntityTypes.KOFO_JAGA.get(), EntityKofoJaga.createAttributes().build());
        event.put(NuiCraftEntityTypes.NUI_JAGA.get(), EntityNuiJaga.createAttributes().build());

        // NPCs
        event.put(NuiCraftEntityTypes.MATORAN.get(), EntityMatoran.createAttributes().build());
        event.put(NuiCraftEntityTypes.TURAGA.get(), EntityTuraga.createAttributes().build());
    }
}
