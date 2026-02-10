package eastonium.nuicraft.core;

import eastonium.nuicraft.entity.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

public class NuiCraftEntityAttributes {
    
    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        // Core Bionicle mobs with custom models
        event.put(NuiCraftEntityTypes.MAHI.get(), EntityMahi.createAttributes().build());
        event.put(NuiCraftEntityTypes.FIKOU.get(), EntityFikou.createAttributes().build());
        event.put(NuiCraftEntityTypes.HOI.get(), EntityHoi.createAttributes().build());
        event.put(NuiCraftEntityTypes.KOFO_JAGA.get(), EntityKofoJaga.createAttributes().build());
        event.put(NuiCraftEntityTypes.NUI_JAGA.get(), EntityNuiJaga.createAttributes().build());

        // Bohrok
        event.put(NuiCraftEntityTypes.BOHROKTAHNOK.get(), EntityBohrokTahnok.createAttributes().build());
        event.put(NuiCraftEntityTypes.GAHLOK.get(), EntityGahlok.createAttributes().build());
        event.put(NuiCraftEntityTypes.KOHRAK.get(), EntityKohrak.createAttributes().build());
        event.put(NuiCraftEntityTypes.LEHVAK.get(), EntityLehvak.createAttributes().build());
        event.put(NuiCraftEntityTypes.NUHVOK.get(), EntityNuhvok.createAttributes().build());
        event.put(NuiCraftEntityTypes.PAHRAK.get(), EntityPahrak.createAttributes().build());

        // Rahkshi
        event.put(NuiCraftEntityTypes.GUURAHK.get(), EntityGuurahk.createAttributes().build());
        event.put(NuiCraftEntityTypes.KURAHK.get(), EntityKurahk.createAttributes().build());
        event.put(NuiCraftEntityTypes.LERAHK.get(), EntityLerahk.createAttributes().build());
        event.put(NuiCraftEntityTypes.PANRAHK.get(), EntityPanrahk.createAttributes().build());
        event.put(NuiCraftEntityTypes.RAHKSHIYELLOW.get(), EntityRahkshiYellow.createAttributes().build());
        event.put(NuiCraftEntityTypes.TURAHK.get(), EntityTurahk.createAttributes().build());
        event.put(NuiCraftEntityTypes.VOHRAK.get(), EntityVohrak.createAttributes().build());

        // Matoran
        event.put(NuiCraftEntityTypes.AGNIMATORAN.get(), EntityAgniMatoran.createAttributes().build());
        event.put(NuiCraftEntityTypes.AKHMOUMATORAN.get(), EntityAkhmouMatoran.createAttributes().build());
        event.put(NuiCraftEntityTypes.BOREASMATORAN.get(), EntityBoreasMatoran.createAttributes().build());
        event.put(NuiCraftEntityTypes.HAFUMATORAN.get(), EntityHafuMatoran.createAttributes().build());
        event.put(NuiCraftEntityTypes.HEWKIIMATORAN.get(), EntityHewkiiMatoran.createAttributes().build());
        event.put(NuiCraftEntityTypes.JALLERMATORAN.get(), EntityJallerMatoran.createAttributes().build());
        event.put(NuiCraftEntityTypes.KOKKANMATORAN.get(), EntityKokkanMatoran.createAttributes().build());
        event.put(NuiCraftEntityTypes.KONGUMATORAN.get(), EntityKonguMatoran.createAttributes().build());
        event.put(NuiCraftEntityTypes.KOTUMATORAN.get(), EntityKotuMatoran.createAttributes().build());
        event.put(NuiCraftEntityTypes.MACKUMATORAN.get(), EntityMackuMatoran.createAttributes().build());
        event.put(NuiCraftEntityTypes.MATOROMATORAN.get(), EntityMatoroMatoran.createAttributes().build());
        event.put(NuiCraftEntityTypes.NUPARUMATORAN.get(), EntityNuparuMatoran.createAttributes().build());
        event.put(NuiCraftEntityTypes.OKOTHMATORAN.get(), EntityOkothMatoran.createAttributes().build());
        event.put(NuiCraftEntityTypes.ONEPUMATORAN.get(), EntityOnepuMatoran.createAttributes().build());
        event.put(NuiCraftEntityTypes.PAKASTAAMATORAN.get(), EntityPakastaaMatoran.createAttributes().build());
        event.put(NuiCraftEntityTypes.TUULIMATORAN.get(), EntityTuuliMatoran.createAttributes().build());
        event.put(NuiCraftEntityTypes.VOHONMATORAN.get(), EntityVohonMatoran.createAttributes().build());
        event.put(NuiCraftEntityTypes.ZEMYAMATORAN.get(), EntityZemyaMatoran.createAttributes().build());

        // Turaga
        event.put(NuiCraftEntityTypes.MATAUTURAGA.get(), EntityMatauTuraga.createAttributes().build());
        event.put(NuiCraftEntityTypes.NOKAMATURAGA.get(), EntityNokamaTuraga.createAttributes().build());
        event.put(NuiCraftEntityTypes.NUJUTURAGA.get(), EntityNujuTuraga.createAttributes().build());
        event.put(NuiCraftEntityTypes.ONEWATURAGA.get(), EntityOnewaaTuraga.createAttributes().build());
        event.put(NuiCraftEntityTypes.VAKAMATURAGA.get(), EntityVakamaTuraga.createAttributes().build());
        event.put(NuiCraftEntityTypes.WHENAUTURAGA.get(), EntityWhenauTuraga.createAttributes().build());

        // Rahi
        event.put(NuiCraftEntityTypes.KANERA.get(), EntityKaneRa.createAttributes().build());
        event.put(NuiCraftEntityTypes.MUAKA.get(), EntityMuaka.createAttributes().build());
        event.put(NuiCraftEntityTypes.NUIRAMAGREEN.get(), EntityNuiRamaGreen.createAttributes().build());
        event.put(NuiCraftEntityTypes.NUIRAMAORANGE.get(), EntityNuiRamaOrange.createAttributes().build());
        event.put(NuiCraftEntityTypes.SPIDERFIKOU.get(), EntitySpiderFikou.createAttributes().build());
        event.put(NuiCraftEntityTypes.TARAKAVABLUE.get(), EntityTarakavaBlue.createAttributes().build());
        event.put(NuiCraftEntityTypes.TARAKAVAGREEN.get(), EntityTarakavaGreen.createAttributes().build());
        event.put(NuiCraftEntityTypes.TARAKAVAYELLOW.get(), EntityTarakavaYellow.createAttributes().build());
        event.put(NuiCraftEntityTypes.MAKUTA.get(), EntityMakuta.createAttributes().build());
    }
}
