package eastonium.mnogii.core;

import eastonium.mnogii.entity.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

public class MnogiiEntityAttributes {

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        // Passive Rahi
        event.put(MnogiiEntityTypes.MAHI.get(), EntityMahi.createAttributes().build());
        event.put(MnogiiEntityTypes.FIKOU.get(), EntityFikou.createAttributes().build());
        event.put(MnogiiEntityTypes.SPIDER_FIKOU.get(), EntitySpiderFikou.createAttributes().build());
        event.put(MnogiiEntityTypes.HOI.get(), EntityHoi.createAttributes().build());

        // Hostile Rahi
        event.put(MnogiiEntityTypes.MUAKA.get(), EntityMuaka.createAttributes().build());
        event.put(MnogiiEntityTypes.TARAKAVA.get(), EntityTarakava.createAttributes().build());
        event.put(MnogiiEntityTypes.KOFO_JAGA.get(), EntityKofoJaga.createAttributes().build());
        event.put(MnogiiEntityTypes.NUI_JAGA.get(), EntityNuiJaga.createAttributes().build());

        // Flying Rahi
        event.put(MnogiiEntityTypes.GUKKO.get(), EntityGukko.createAttributes().build());
        event.put(MnogiiEntityTypes.NUI_RAMA.get(), EntityNuiRama.createAttributes().build());

        // NPCs
        var matoranAttrs = EntityMatoran.createAttributes().build();
        event.put(MnogiiEntityTypes.MATORAN.get(), matoranAttrs);
        event.put(MnogiiEntityTypes.MATORAN_TA.get(), matoranAttrs);
        event.put(MnogiiEntityTypes.MATORAN_GA.get(), matoranAttrs);
        event.put(MnogiiEntityTypes.MATORAN_LE.get(), matoranAttrs);
        event.put(MnogiiEntityTypes.MATORAN_ONU.get(), matoranAttrs);
        event.put(MnogiiEntityTypes.MATORAN_KO.get(), matoranAttrs);
        event.put(MnogiiEntityTypes.MATORAN_PO.get(), matoranAttrs);
        var turagaAttrs = EntityTuraga.createAttributes().build();
        event.put(MnogiiEntityTypes.TURAGA.get(),        turagaAttrs);
        event.put(MnogiiEntityTypes.TURAGA_VAKAMA.get(), turagaAttrs);
        event.put(MnogiiEntityTypes.TURAGA_NOKAMA.get(), turagaAttrs);
        event.put(MnogiiEntityTypes.TURAGA_MATAU.get(),  turagaAttrs);
        event.put(MnogiiEntityTypes.TURAGA_ONEWA.get(),  turagaAttrs);
        event.put(MnogiiEntityTypes.TURAGA_WHENUA.get(), turagaAttrs);
        event.put(MnogiiEntityTypes.TURAGA_NUJU.get(),   turagaAttrs);

        // Toa (one per Koro biome)
        var toaAttrs = EntityToa.createAttributes().build();
        event.put(MnogiiEntityTypes.TOA_TAHU.get(), toaAttrs);
        event.put(MnogiiEntityTypes.TOA_GALI.get(), toaAttrs);
        event.put(MnogiiEntityTypes.TOA_LEWA.get(), toaAttrs);
        event.put(MnogiiEntityTypes.TOA_ONUA.get(), toaAttrs);
        event.put(MnogiiEntityTypes.TOA_POHATU.get(), toaAttrs);
        event.put(MnogiiEntityTypes.TOA_KOPAKA.get(), toaAttrs);
    }
}
