package eastonium.nuicraft.core;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.entity.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = NuiCraft.MODID)
public class NuiCraftEntityAttributes {
    
    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(NuiCraftEntityTypes.MAHI.get(), EntityMahi.createAttributes().build());
        event.put(NuiCraftEntityTypes.FIKOU.get(), EntityFikou.createAttributes().build());
        event.put(NuiCraftEntityTypes.HOI.get(), EntityHoi.createAttributes().build());
        event.put(NuiCraftEntityTypes.KOFO_JAGA.get(), EntityKofoJaga.createAttributes().build());
        event.put(NuiCraftEntityTypes.NUI_JAGA.get(), EntityNuiJaga.createAttributes().build());
    }
}
