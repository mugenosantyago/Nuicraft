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
        event.put(NuiCraftEntityTypes.SPIDER_FIKOU.get(), EntitySpiderFikou.createAttributes().build());
        event.put(NuiCraftEntityTypes.HOI.get(), EntityHoi.createAttributes().build());

        // Hostile Rahi
        event.put(NuiCraftEntityTypes.MUAKA.get(), EntityMuaka.createAttributes().build());
        event.put(NuiCraftEntityTypes.TARAKAVA.get(), EntityTarakava.createAttributes().build());
        event.put(NuiCraftEntityTypes.KOFO_JAGA.get(), EntityKofoJaga.createAttributes().build());
        event.put(NuiCraftEntityTypes.NUI_JAGA.get(), EntityNuiJaga.createAttributes().build());

        // Flying Rahi
        event.put(NuiCraftEntityTypes.GUKKO.get(), EntityGukko.createAttributes().build());
        event.put(NuiCraftEntityTypes.NUI_RAMA.get(), EntityNuiRama.createAttributes().build());

        // NPCs
        var matoranAttrs = EntityMatoran.createAttributes().build();
        event.put(NuiCraftEntityTypes.MATORAN.get(), matoranAttrs);
        event.put(NuiCraftEntityTypes.MATORAN_TA.get(), matoranAttrs);
        event.put(NuiCraftEntityTypes.MATORAN_GA.get(), matoranAttrs);
        event.put(NuiCraftEntityTypes.MATORAN_LE.get(), matoranAttrs);
        event.put(NuiCraftEntityTypes.MATORAN_ONU.get(), matoranAttrs);
        event.put(NuiCraftEntityTypes.MATORAN_KO.get(), matoranAttrs);
        event.put(NuiCraftEntityTypes.MATORAN_PO.get(), matoranAttrs);
        var turagaAttrs = EntityTuraga.createAttributes().build();
        event.put(NuiCraftEntityTypes.TURAGA.get(),        turagaAttrs);
        event.put(NuiCraftEntityTypes.TURAGA_VAKAMA.get(), turagaAttrs);
        event.put(NuiCraftEntityTypes.TURAGA_NOKAMA.get(), turagaAttrs);
        event.put(NuiCraftEntityTypes.TURAGA_MATAU.get(),  turagaAttrs);
        event.put(NuiCraftEntityTypes.TURAGA_ONEWA.get(),  turagaAttrs);
        event.put(NuiCraftEntityTypes.TURAGA_WHENUA.get(), turagaAttrs);
        event.put(NuiCraftEntityTypes.TURAGA_NUJU.get(),   turagaAttrs);

        // Toa (one per Koro biome)
        var toaAttrs = EntityToa.createAttributes().build();
        event.put(NuiCraftEntityTypes.TOA_TAHU.get(), toaAttrs);
        event.put(NuiCraftEntityTypes.TOA_GALI.get(), toaAttrs);
        event.put(NuiCraftEntityTypes.TOA_LEWA.get(), toaAttrs);
        event.put(NuiCraftEntityTypes.TOA_ONUA.get(), toaAttrs);
        event.put(NuiCraftEntityTypes.TOA_POHATU.get(), toaAttrs);
        event.put(NuiCraftEntityTypes.TOA_KOPAKA.get(), toaAttrs);
    }
}
