package eastonium.mnogii.core;

import eastonium.mnogii.entity.*;
import eastonium.mnogii.entity.EntityToa;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

/**
 * Registers spawn placement rules for Mnogii mobs so they can spawn correctly in world gen.
 */
public class MnogiiSpawnPlacements {

    public static void register(RegisterSpawnPlacementsEvent event) {
        // Core Bionicle mobs
        event.register(MnogiiEntityTypes.MAHI.get(),
                SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(MnogiiEntityTypes.FIKOU.get(),
                SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(MnogiiEntityTypes.SPIDER_FIKOU.get(),
                SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(MnogiiEntityTypes.HOI.get(),
                SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        // Hostile Rahi use checkMobSpawnRules (not checkMonsterSpawnRules) so they can spawn
        // on the surface in daylight biomes like a predatory animal, not like an underground monster.
        event.register(MnogiiEntityTypes.KOFO_JAGA.get(),
                SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                PathfinderMob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(MnogiiEntityTypes.NUI_JAGA.get(),
                SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                PathfinderMob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(MnogiiEntityTypes.MUAKA.get(),
                SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                EntityMuaka::checkKoroSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(MnogiiEntityTypes.TARAKAVA.get(),
                SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                EntityTarakava::checkKoroSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        // Flying Rahi (no heightmap - they fly)
        event.register(MnogiiEntityTypes.GUKKO.get(),
                SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                PathfinderMob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(MnogiiEntityTypes.NUI_RAMA.get(),
                SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                PathfinderMob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        // NPCs — generic types plus all koro/variant-specific types used in structure spawn_overrides
        var npcRule = RegisterSpawnPlacementsEvent.Operation.REPLACE;

        // Matoran (generic + all 6 koro variants)
        event.register(MnogiiEntityTypes.MATORAN.get(),     SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PathfinderMob::checkMobSpawnRules, npcRule);
        event.register(MnogiiEntityTypes.MATORAN_TA.get(),  SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PathfinderMob::checkMobSpawnRules, npcRule);
        event.register(MnogiiEntityTypes.MATORAN_GA.get(),  SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PathfinderMob::checkMobSpawnRules, npcRule);
        event.register(MnogiiEntityTypes.MATORAN_LE.get(),  SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PathfinderMob::checkMobSpawnRules, npcRule);
        event.register(MnogiiEntityTypes.MATORAN_ONU.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PathfinderMob::checkMobSpawnRules, npcRule);
        event.register(MnogiiEntityTypes.MATORAN_KO.get(),  SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PathfinderMob::checkMobSpawnRules, npcRule);
        event.register(MnogiiEntityTypes.MATORAN_PO.get(),  SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PathfinderMob::checkMobSpawnRules, npcRule);

        // Toa (all 6 variants) — limited to one per 160-block radius via custom spawn check
        event.register(MnogiiEntityTypes.TOA_TAHU.get(),   SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityToa::checkToaSpawnRules, npcRule);
        event.register(MnogiiEntityTypes.TOA_GALI.get(),   SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityToa::checkToaSpawnRules, npcRule);
        event.register(MnogiiEntityTypes.TOA_LEWA.get(),   SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityToa::checkToaSpawnRules, npcRule);
        event.register(MnogiiEntityTypes.TOA_ONUA.get(),   SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityToa::checkToaSpawnRules, npcRule);
        event.register(MnogiiEntityTypes.TOA_POHATU.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityToa::checkToaSpawnRules, npcRule);
        event.register(MnogiiEntityTypes.TOA_KOPAKA.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityToa::checkToaSpawnRules, npcRule);

        // Turaga (generic + all 6 named characters)
        event.register(MnogiiEntityTypes.TURAGA.get(),        SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PathfinderMob::checkMobSpawnRules, npcRule);
        event.register(MnogiiEntityTypes.TURAGA_VAKAMA.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PathfinderMob::checkMobSpawnRules, npcRule);
        event.register(MnogiiEntityTypes.TURAGA_NOKAMA.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PathfinderMob::checkMobSpawnRules, npcRule);
        event.register(MnogiiEntityTypes.TURAGA_MATAU.get(),  SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PathfinderMob::checkMobSpawnRules, npcRule);
        event.register(MnogiiEntityTypes.TURAGA_ONEWA.get(),  SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PathfinderMob::checkMobSpawnRules, npcRule);
        event.register(MnogiiEntityTypes.TURAGA_WHENUA.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PathfinderMob::checkMobSpawnRules, npcRule);
        event.register(MnogiiEntityTypes.TURAGA_NUJU.get(),   SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PathfinderMob::checkMobSpawnRules, npcRule);
    }
}
