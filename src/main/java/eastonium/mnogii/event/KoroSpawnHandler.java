package eastonium.mnogii.event;

import eastonium.mnogii.core.MnogiiEntityTypes;
import eastonium.mnogii.entity.EntityMatoran;
import eastonium.mnogii.entity.EntityToa;
import eastonium.mnogii.entity.EntityTuraga;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Directly spawns Matoran, Toa, and Turaga inside koro structures whenever
 * the expected population falls below target — bypassing the CREATURE mob cap
 * that prevents spawn_overrides from reliably placing custom NPCs.
 *
 * Runs every CHECK_INTERVAL ticks (20 s) on the server, scanning structure
 * starts in a small radius around each online player.
 */
public class KoroSpawnHandler {

    /** How often (server ticks) to scan for under-populated koros. */
    private static final int CHECK_INTERVAL = 400;
    /** Chunk radius around each player to scan. */
    private static final int SCAN_RADIUS = 4;
    /** Extra blocks beyond the structure bounding box when counting existing entities. */
    private static final int ENTITY_BUFFER = 24;
    /** Target matoran count range [min, max]. */
    private static final int MATORAN_MIN = 5;
    private static final int MATORAN_MAX = 10;

    /**
     * Controls how the spawn position Y is determined for a koro.
     * <ul>
     *   <li>{@code SURFACE}     – heightmap surface (open-air villages)</li>
     *   <li>{@code TOWER_FLOOR} – one block above the structure bounding-box floor
     *                             (tower structures like Ko-Koro)</li>
     *   <li>{@code UNDERGROUND} – scan downward from the bounding-box centre Y to
     *                             find the first walkable floor inside the underground
     *                             chamber (Onu-Koro)</li>
     *   <li>{@code TREEHOUSE}   – scan upward from the terrain surface to find the
     *                             first elevated platform above the ground
     *                             (Le-Koro treehouse)</li>
     * </ul>
     */
    private enum SpawnMode { SURFACE, TOWER_FLOOR, TOWER_TOP, UNDERGROUND, TREEHOUSE }

    private record KoroInfo(
            EntityToa.Variant toaVariant,
            EntityType<EntityToa> toaType,
            EntityTuraga.TuragaType turagaType,
            EntityType<EntityTuraga> turagaEntityType,
            EntityMatoran.Koro koro,
            /** Koro-specific Matoran entity type — its factory hard-codes the correct koro so
             *  the entity is created with the right tribe even before readAdditionalSaveData runs. */
            EntityType<EntityMatoran> matoranEntityType,
            SpawnMode spawnMode,
            /** Override spawn mode used only for the Toa. Defaults to spawnMode when not overridden. */
            SpawnMode toaSpawnMode) {

        KoroInfo(EntityToa.Variant toaVariant, EntityType<EntityToa> toaType,
                 EntityTuraga.TuragaType turagaType, EntityType<EntityTuraga> turagaEntityType,
                 EntityMatoran.Koro koro, EntityType<EntityMatoran> matoranEntityType,
                 SpawnMode spawnMode) {
            this(toaVariant, toaType, turagaType, turagaEntityType, koro, matoranEntityType, spawnMode, spawnMode);
        }
    }


    /**
     * Fraction of regular Matoran that are "visitors" from another koro,
     * adding colour variety without overwhelming the home koro's presence.
     */
    private static final float VISITOR_CHANCE = 0.25f;

    private static Map<String, KoroInfo> KORO_INFO = null;

    private static Map<String, KoroInfo> koroInfo() {
        if (KORO_INFO == null) {
            KORO_INFO = new HashMap<>();
            KORO_INFO.put("gakoro",  new KoroInfo(
                    EntityToa.Variant.GALI,   MnogiiEntityTypes.TOA_GALI.get(),
                    EntityTuraga.TuragaType.NOKAMA, MnogiiEntityTypes.TURAGA_NOKAMA.get(),
                    EntityMatoran.Koro.GA,  MnogiiEntityTypes.MATORAN_GA.get(),  SpawnMode.TREEHOUSE));
            KORO_INFO.put("takoro",  new KoroInfo(
                    EntityToa.Variant.TAHU,   MnogiiEntityTypes.TOA_TAHU.get(),
                    EntityTuraga.TuragaType.VAKAMA, MnogiiEntityTypes.TURAGA_VAKAMA.get(),
                    EntityMatoran.Koro.TA,  MnogiiEntityTypes.MATORAN_TA.get(),  SpawnMode.SURFACE));
            KORO_INFO.put("lekoro",  new KoroInfo(
                    EntityToa.Variant.LEWA,   MnogiiEntityTypes.TOA_LEWA.get(),
                    EntityTuraga.TuragaType.MATAU,  MnogiiEntityTypes.TURAGA_MATAU.get(),
                    EntityMatoran.Koro.LE,  MnogiiEntityTypes.MATORAN_LE.get(),  SpawnMode.TREEHOUSE));
            KORO_INFO.put("onukoro", new KoroInfo(
                    EntityToa.Variant.ONUA,   MnogiiEntityTypes.TOA_ONUA.get(),
                    EntityTuraga.TuragaType.WHENUA, MnogiiEntityTypes.TURAGA_WHENUA.get(),
                    EntityMatoran.Koro.ONU, MnogiiEntityTypes.MATORAN_ONU.get(), SpawnMode.UNDERGROUND));
            KORO_INFO.put("pokoro",  new KoroInfo(
                    EntityToa.Variant.POHATU, MnogiiEntityTypes.TOA_POHATU.get(),
                    EntityTuraga.TuragaType.ONEWA,  MnogiiEntityTypes.TURAGA_ONEWA.get(),
                    EntityMatoran.Koro.PO,  MnogiiEntityTypes.MATORAN_PO.get(),  SpawnMode.SURFACE));
            KORO_INFO.put("kokoro",  new KoroInfo(
                    EntityToa.Variant.KOPAKA, MnogiiEntityTypes.TOA_KOPAKA.get(),
                    EntityTuraga.TuragaType.NUJU,   MnogiiEntityTypes.TURAGA_NUJU.get(),
                    EntityMatoran.Koro.KO,  MnogiiEntityTypes.MATORAN_KO.get(),  SpawnMode.TOWER_FLOOR, SpawnMode.TOWER_TOP));
        }
        return KORO_INFO;
    }

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) return;
        if (serverLevel.dimension() != Level.OVERWORLD) return;

        long gameTime = serverLevel.getGameTime();
        if (gameTime % CHECK_INTERVAL != 0) return;

        var structureRegistry = serverLevel.registryAccess().lookupOrThrow(Registries.STRUCTURE);
        Set<Long> visited = new HashSet<>();

        for (ServerPlayer player : serverLevel.players()) {
            ChunkPos origin = player.chunkPosition();
            for (int dx = -SCAN_RADIUS; dx <= SCAN_RADIUS; dx++) {
                for (int dz = -SCAN_RADIUS; dz <= SCAN_RADIUS; dz++) {
                    int cx = origin.x + dx;
                    int cz = origin.z + dz;
                    long key = ChunkPos.asLong(cx, cz);
                    if (!visited.add(key)) continue;

                    BlockPos probe = new BlockPos(cx << 4, 64, cz << 4);
                    if (!serverLevel.isLoaded(probe)) continue;

                    LevelChunk chunk = serverLevel.getChunk(cx, cz);
                    for (Map.Entry<Structure, StructureStart> entry : chunk.getAllStarts().entrySet()) {
                        StructureStart start = entry.getValue();
                        if (!start.isValid()) continue;

                        ResourceLocation id = structureRegistry.getKey(entry.getKey());
                        if (id == null || !"mnogii".equals(id.getNamespace())) continue;

                        KoroInfo info = koroInfo().get(id.getPath());
                        if (info == null) continue;

                        ensurePopulated(serverLevel, start, info);
                    }
                }
            }
        }
    }

    private static void ensurePopulated(ServerLevel level, StructureStart start, KoroInfo info) {
        AABB bounds = AABB.of(start.getBoundingBox()).inflate(ENTITY_BUFFER);
        BlockPos center = start.getBoundingBox().getCenter();

        long toaCount = level.getEntitiesOfClass(EntityToa.class, bounds,
                e -> e.getVariant() == info.toaVariant).size();
        long turagaCount = level.getEntitiesOfClass(EntityTuraga.class, bounds,
                e -> e.getTuragaType() == info.turagaType).size();
        // Count maskmakers and regular Matoran separately so the maskmaker doesn't
        // consume a regular-villager slot.
        long maskmakerCount = level.getEntitiesOfClass(EntityMatoran.class, bounds,
                e -> e.getKoro() == info.koro
                  && e.getProfession() == EntityMatoran.Profession.MASKMAKER).size();
        long matoranCount = level.getEntitiesOfClass(EntityMatoran.class, bounds,
                e -> e.getKoro() == info.koro
                  && e.getProfession() != EntityMatoran.Profession.MASKMAKER).size();

        if (toaCount == 0) {
            EntityToa toa = new EntityToa(info.toaType, level, info.toaVariant);
            int toaOx = level.getRandom().nextIntBetweenInclusive(5, 8) * (level.getRandom().nextBoolean() ? 1 : -1);
            int toaOz = level.getRandom().nextIntBetweenInclusive(5, 8) * (level.getRandom().nextBoolean() ? 1 : -1);
            place(level, toa, spawnPosWithMode(level, start, info.toaSpawnMode(), center.offset(toaOx, 0, toaOz)));
        }
        if (turagaCount == 0) {
            EntityTuraga turaga = new EntityTuraga(info.turagaEntityType, level, info.turagaType);
            place(level, turaga, spawnPos(level, start, info, center.offset(3, 0, 0)));
        }

        // Exactly one dedicated Maskmaker per koro — wears and sells the koro's signature mask.
        if (maskmakerCount == 0) {
            EntityMatoran maskmaker = new EntityMatoran(
                    info.matoranEntityType(), level,
                    info.koro(),
                    info.koro().getMaskmakerMask(),
                    EntityMatoran.Profession.MASKMAKER);
            BlockPos pos = spawnPos(level, start, info, center.offset(2, 0, -2));
            place(level, maskmaker, pos);
            maskmaker.setHomePos(spawnPos(level, start, info, center));
        }

        int target = MATORAN_MIN + level.getRandom().nextInt(MATORAN_MAX - MATORAN_MIN + 1);

        // Shuffle the mask pool so each Matoran in this batch gets a distinct mask.
        // We cycle through the shuffled list rather than picking independently,
        // which prevents two Matoran from wearing the same mask in the same koro.
        java.util.List<EntityMatoran.Mask> maskPool =
                new java.util.ArrayList<>(java.util.Arrays.asList(EntityMatoran.IMPLEMENTED_MASKS));
        java.util.Collections.shuffle(maskPool, new java.util.Random(level.getRandom().nextLong()));

        // Treehouse koros (Ga-Koro, Le-Koro) use a tighter random offset so that
        // the position finder lands on the platform rather than in open water/air.
        boolean treehouse = info.spawnMode() == SpawnMode.TREEHOUSE;
        int minOff = treehouse ? 1 : 3;
        int maxOff = treehouse ? 3 : 7;

        for (long i = matoranCount; i < target; i++) {
            int ox = level.getRandom().nextIntBetweenInclusive(minOff, maxOff) * (level.getRandom().nextBoolean() ? 1 : -1);
            int oz = level.getRandom().nextIntBetweenInclusive(minOff, maxOff) * (level.getRandom().nextBoolean() ? 1 : -1);
            EntityMatoran.Mask mask = maskPool.get((int)(i % maskPool.size()));

            // Visitor matoran: spawn with a random other-koro colour for visual variety.
            // Majority are still home-koro, but ~25% come from elsewhere.
            boolean isVisitor = level.getRandom().nextFloat() < VISITOR_CHANCE;
            EntityMatoran.Koro spawnKoro = info.koro();
            if (isVisitor) {
                EntityMatoran.Koro[] allKoros = EntityMatoran.Koro.values();
                EntityMatoran.Koro pick;
                do { pick = allKoros[level.getRandom().nextInt(allKoros.length)]; }
                while (pick == info.koro());
                spawnKoro = pick;
            }

            EntityMatoran mat = new EntityMatoran(
                    info.matoranEntityType(), level,
                    spawnKoro,
                    mask,
                    EntityMatoran.RANDOM_PROFESSIONS[level.getRandom().nextInt(EntityMatoran.RANDOM_PROFESSIONS.length)]);
            BlockPos pos = spawnPos(level, start, info, center.offset(ox, 0, oz));
            place(level, mat, pos);
            // Pin them to the structure so they wander back inside instead of scattering.
            mat.setHomePos(spawnPos(level, start, info, center));
        }
    }

    /**
     * Returns a safe spawn position based on the koro's {@link SpawnMode}:
     * <ul>
     *   <li>{@code SURFACE}     – heightmap surface (open-air koros)</li>
     *   <li>{@code TOWER_FLOOR} – one block above the structure bounding-box floor</li>
     *   <li>{@code UNDERGROUND} – scans downward from the bounding-box centre Y to
     *       find the first position where the block below is solid and there are
     *       two air blocks above — i.e. the floor of the underground chamber.</li>
     * </ul>
     */
    private static BlockPos spawnPos(ServerLevel level, StructureStart start, KoroInfo info, BlockPos near) {
        return spawnPosWithMode(level, start, info.spawnMode(), near);
    }

    private static BlockPos spawnPosWithMode(ServerLevel level, StructureStart start, SpawnMode mode, BlockPos near) {
        return switch (mode) {
            case TOWER_FLOOR -> findRandomTowerFloor(level, start, near);
            case TOWER_TOP   -> new BlockPos(near.getX(), start.getBoundingBox().maxY() + 1, near.getZ());
            case UNDERGROUND -> findUndergroundFloor(level, start, near);
            case TREEHOUSE   -> findTreehouseFloor(level, start, near);
            default          -> level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, near);
        };
    }

    /**
     * Scans all Y levels inside the tower (minY+1 to maxY-1) to collect every
     * walkable floor position, then picks one at random so Matoran spread across
     * all levels of the Ko-Koro tower rather than clustering at the ground floor.
     */
    private static BlockPos findRandomTowerFloor(ServerLevel level, StructureStart start, BlockPos near) {
        int minY = start.getBoundingBox().minY() + 1;
        int maxY = start.getBoundingBox().maxY() - 1;
        int x = near.getX(), z = near.getZ();

        List<BlockPos> floors = new ArrayList<>();
        for (int y = minY; y <= maxY; y++) {
            BlockPos candidate = new BlockPos(x, y, z);
            if (!level.isLoaded(candidate)) break;

            BlockState floor = level.getBlockState(candidate.below());
            BlockState feet  = level.getBlockState(candidate);
            BlockState head  = level.getBlockState(candidate.above());

            if (floor.isSolid() && !feet.isSolid() && !head.isSolid()) {
                floors.add(candidate.immutable());
            }
        }
        if (!floors.isEmpty()) {
            return floors.get(level.getRandom().nextInt(floors.size()));
        }
        // Fallback: bottom floor
        return new BlockPos(x, minY, z);
    }

    /**
     * Scans downward from the structure's bounding-box centre Y to find the
     * first walkable position (solid floor, two air blocks above) inside the
     * underground chamber. Falls back to the centre Y if nothing is found.
     */
    private static BlockPos findUndergroundFloor(ServerLevel level, StructureStart start, BlockPos near) {
        int startY  = start.getBoundingBox().getCenter().getY();
        int minY    = start.getBoundingBox().minY() + 1;
        int x = near.getX(), z = near.getZ();

        for (int y = startY; y > minY; y--) {
            BlockPos candidate = new BlockPos(x, y, z);
            if (!level.isLoaded(candidate)) break;

            BlockState floor = level.getBlockState(candidate.below());
            BlockState feet  = level.getBlockState(candidate);
            BlockState head  = level.getBlockState(candidate.above());

            if (floor.isSolid() && !feet.isSolid() && !head.isSolid()) {
                return candidate;
            }
        }
        // Fallback: centre Y (entity will drop to the floor on spawn)
        return new BlockPos(x, startY, z);
    }

    /**
     * Scans upward from the structure's minimum Y (not the terrain surface) to
     * find the first elevated platform inside the bounding box.
     *
     * Using the structure minY rather than the heightmap surface avoids the Ga-Koro
     * water-spawn bug: MOTION_BLOCKING in a warm-ocean biome returns the water
     * surface, and random X,Z offsets often land in open water where no platform
     * is directly overhead.  Starting from minY, we walk up through the entire
     * structure height and find the first solid-floor + 2-air-above position.
     *
     * If nothing is found at the exact X,Z, spiral inward toward the structure
     * centre (up to 5 steps) to catch cases where the offset lands just outside
     * the platform edge.
     */
    private static BlockPos findTreehouseFloor(ServerLevel level, StructureStart start, BlockPos near) {
        BlockPos result = scanUpForFloor(level, start, near.getX(), near.getZ());
        if (result != null) return result;

        // Walk step-by-step toward the BB centre until we hit a platform.
        int cx = start.getBoundingBox().getCenter().getX();
        int cz = start.getBoundingBox().getCenter().getZ();
        int x = near.getX(), z = near.getZ();
        for (int step = 1; step <= 5; step++) {
            int nx = x + (int) Math.signum(cx - x) * step;
            int nz = z + (int) Math.signum(cz - z) * step;
            result = scanUpForFloor(level, start, nx, nz);
            if (result != null) return result;
        }

        // Fallback: bounding-box centre Y so the entity at least lands inside
        return new BlockPos(near.getX(), start.getBoundingBox().getCenter().getY(), near.getZ());
    }

    private static BlockPos scanUpForFloor(ServerLevel level, StructureStart start, int x, int z) {
        int startY = start.getBoundingBox().minY();
        int maxY   = start.getBoundingBox().maxY() - 1;
        for (int y = startY; y <= maxY; y++) {
            BlockPos candidate = new BlockPos(x, y, z);
            if (!level.isLoaded(candidate)) return null;

            BlockState floor = level.getBlockState(candidate.below());
            BlockState feet  = level.getBlockState(candidate);
            BlockState head  = level.getBlockState(candidate.above());

            if (floor.isSolid()
                    && !feet.isSolid() && feet.getFluidState().isEmpty()
                    && !head.isSolid() && head.getFluidState().isEmpty()) {
                return candidate;
            }
        }
        return null;
    }

    private static void place(ServerLevel level, PathfinderMob mob, BlockPos pos) {
        mob.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        mob.finalizeSpawn(level, level.getCurrentDifficultyAt(pos),
                EntitySpawnReason.STRUCTURE, null);
        level.addFreshEntity(mob);
    }
}
