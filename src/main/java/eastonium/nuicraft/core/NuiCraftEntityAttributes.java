package eastonium.nuicraft.core;

import eastonium.nuicraft.entity.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

public class NuiCraftEntityAttributes {
    
    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(NuiCraftEntityTypes.MAHI.get(), EntityMahi.createAttributes().build());
        event.put(NuiCraftEntityTypes.FIKOU.get(), EntityFikou.createAttributes().build());
        event.put(NuiCraftEntityTypes.HOI.get(), EntityHoi.createAttributes().build());
        event.put(NuiCraftEntityTypes.KOFO_JAGA.get(), EntityKofoJaga.createAttributes().build());
        event.put(NuiCraftEntityTypes.NUI_JAGA.get(), EntityNuiJaga.createAttributes().build());
        
        // Batch 1
        event.put(NuiCraftEntityTypes.USSAL_CRAB.get(), EntityUssalCrab.createAttributes().build());
        event.put(NuiCraftEntityTypes.SHEEP_RAHI.get(), EntitySheepRahi.createAttributes().build());
        event.put(NuiCraftEntityTypes.RABBIT_RAHI.get(), EntityRabbitRahi.createAttributes().build());
        
        // Batch 2
        event.put(NuiCraftEntityTypes.MUAKA.get(), EntityMuaka.createAttributes().build());
        event.put(NuiCraftEntityTypes.KANE_RA.get(), EntityKaneRa.createAttributes().build());
        event.put(NuiCraftEntityTypes.POKAWI.get(), EntityPokawi.createAttributes().build());
        
        // Batch 3
        event.put(NuiCraftEntityTypes.TURTLE_RAHI.get(), EntityTurtleRahi.createAttributes().build());
        event.put(NuiCraftEntityTypes.HIKAKI.get(), EntityHikaki.createAttributes().build());
        event.put(NuiCraftEntityTypes.SHORE_TURTLE.get(), EntityShoreTurtle.createAttributes().build());
        
        // Batch 4
        event.put(NuiCraftEntityTypes.BIRD_RAHI.get(), EntityBirdRahi.createAttributes().build());
        event.put(NuiCraftEntityTypes.BAT_RAHI.get(), EntityBatRahi.createAttributes().build());
        event.put(NuiCraftEntityTypes.HOTO_BUG.get(), EntityHotoBug.createAttributes().build());
        
        // Batch 5
        event.put(NuiCraftEntityTypes.MATORAN.get(), EntityMatoran.createAttributes().build());
        event.put(NuiCraftEntityTypes.BOHROK.get(), EntityBohrok.createAttributes().build());
        event.put(NuiCraftEntityTypes.TURAGA.get(), EntityTuraga.createAttributes().build());
        event.put(NuiCraftEntityTypes.MUKAU.get(), EntityMukau.createAttributes().build());
        event.put(NuiCraftEntityTypes.HAPAKA.get(), EntityHapaka.createAttributes().build());
        event.put(NuiCraftEntityTypes.BOHROK_VA.get(), EntityBohrokVa.createAttributes().build());
        
        // Batch 5b
        event.put(NuiCraftEntityTypes.NUI_RAMA.get(), EntityNuiRama.createAttributes().build());
        event.put(NuiCraftEntityTypes.ASH_BEAR.get(), EntityAshBear.createAttributes().build());
        event.put(NuiCraftEntityTypes.RAHAGA.get(), EntityRahaga.createAttributes().build());
        event.put(NuiCraftEntityTypes.VAKO.get(), EntityVako.createAttributes().build());
        event.put(NuiCraftEntityTypes.KIKANALO.get(), EntityKikanalo.createAttributes().build());
        event.put(NuiCraftEntityTypes.RAHKSHI.get(), EntityRahkshi.createAttributes().build());
        
        // Batch 5c
        event.put(NuiCraftEntityTypes.KRAATA.get(), EntityKraata.createAttributes().build());
        event.put(NuiCraftEntityTypes.PIRAKA.get(), EntityPiraka.createAttributes().build());
        event.put(NuiCraftEntityTypes.DARK_HUNTER.get(), EntityDarkHunter.createAttributes().build());
        event.put(NuiCraftEntityTypes.FUSA.get(), EntityFusa.createAttributes().build());
        event.put(NuiCraftEntityTypes.SKRALL.get(), EntitySkrall.createAttributes().build());
        event.put(NuiCraftEntityTypes.ZESK.get(), EntityZesk.createAttributes().build());
        event.put(NuiCraftEntityTypes.AGORI.get(), EntityAgori.createAttributes().build());
        event.put(NuiCraftEntityTypes.FOX_RAHI.get(), EntityFoxRahi.createAttributes().build());
        event.put(NuiCraftEntityTypes.KAVINIKA.get(), EntityKavinika.createAttributes().build());
        event.put(NuiCraftEntityTypes.ARCHIVES_MOLE.get(), EntityArchivesMole.createAttributes().build());
        event.put(NuiCraftEntityTypes.TAKEA_SHARK.get(), EntityTakeaShark.createAttributes().build());
    }
}
