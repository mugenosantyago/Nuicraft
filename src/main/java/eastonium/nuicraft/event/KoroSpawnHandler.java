package eastonium.nuicraft.event;

import eastonium.nuicraft.core.NuiCraftEntityTypes;
import eastonium.nuicraft.entity.EntityMatoran;
import eastonium.nuicraft.entity.EntityToa;
import eastonium.nuicraft.entity.EntityTuraga;
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
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.HashMap;
import java.util.HashSet;
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
    private static final int MATORAN_MIN = 2;
    private static final int MATORAN_MAX = 5;

    private record KoroInfo(
            EntityToa.Variant toaVariant,
            EntityType<EntityToa> toaType,
            EntityTuraga.TuragaType turagaType,
            EntityType<EntityTuraga> turagaEntityType,
            EntityMatoran.Koro koro) {}

    private static Map<String, KoroInfo> KORO_INFO = null;

    private static Map<String, KoroInfo> koroInfo() {
        if (KORO_INFO == null) {
            KORO_INFO = new HashMap<>();
            KORO_INFO.put("gakoro",  new KoroInfo(
                    EntityToa.Variant.GALI,   NuiCraftEntityTypes.TOA_GALI.get(),
                    EntityTuraga.TuragaType.NOKAMA, NuiCraftEntityTypes.TURAGA_NOKAMA.get(),
                    EntityMatoran.Koro.GA));
            KORO_INFO.put("takoro",  new KoroInfo(
                    EntityToa.Variant.TAHU,   NuiCraftEntityTypes.TOA_TAHU.get(),
                    EntityTuraga.TuragaType.VAKAMA, NuiCraftEntityTypes.TURAGA_VAKAMA.get(),
                    EntityMatoran.Koro.TA));
            KORO_INFO.put("lekoro",  new KoroInfo(
                    EntityToa.Variant.LEWA,   NuiCraftEntityTypes.TOA_LEWA.get(),
                    EntityTuraga.TuragaType.MATAU,  NuiCraftEntityTypes.TURAGA_MATAU.get(),
                    EntityMatoran.Koro.LE));
            KORO_INFO.put("onukoro", new KoroInfo(
                    EntityToa.Variant.ONUA,   NuiCraftEntityTypes.TOA_ONUA.get(),
                    EntityTuraga.TuragaType.WHENUA, NuiCraftEntityTypes.TURAGA_WHENUA.get(),
                    EntityMatoran.Koro.ONU));
            KORO_INFO.put("pokoro",  new KoroInfo(
                    EntityToa.Variant.POHATU, NuiCraftEntityTypes.TOA_POHATU.get(),
                    EntityTuraga.TuragaType.ONEWA,  NuiCraftEntityTypes.TURAGA_ONEWA.get(),
                    EntityMatoran.Koro.PO));
            KORO_INFO.put("kokoro",  new KoroInfo(
                    EntityToa.Variant.KOPAKA, NuiCraftEntityTypes.TOA_KOPAKA.get(),
                    EntityTuraga.TuragaType.NUJU,   NuiCraftEntityTypes.TURAGA_NUJU.get(),
                    EntityMatoran.Koro.KO));
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
                        if (id == null || !"nuicraft".equals(id.getNamespace())) continue;

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
        long matoranCount = level.getEntitiesOfClass(EntityMatoran.class, bounds,
                e -> e.getKoro() == info.koro).size();

        if (toaCount == 0) {
            EntityToa toa = new EntityToa(info.toaType, level, info.toaVariant);
            place(level, toa, safePos(level, center));
        }
        if (turagaCount == 0) {
            EntityTuraga turaga = new EntityTuraga(info.turagaEntityType, level, info.turagaType);
            place(level, turaga, safePos(level, center.offset(3, 0, 0)));
        }

        int target = MATORAN_MIN + level.getRandom().nextInt(MATORAN_MAX - MATORAN_MIN + 1);
        EntityMatoran.Mask[] masks = EntityMatoran.IMPLEMENTED_MASKS;
        EntityMatoran.Profession[] professions = EntityMatoran.Profession.values();
        for (long i = matoranCount; i < target; i++) {
            int ox = level.getRandom().nextIntBetweenInclusive(-8, 8);
            int oz = level.getRandom().nextIntBetweenInclusive(-8, 8);
            EntityMatoran mat = new EntityMatoran(
                    NuiCraftEntityTypes.MATORAN.get(), level,
                    info.koro,
                    masks[level.getRandom().nextInt(masks.length)],
                    professions[level.getRandom().nextInt(professions.length)]);
            BlockPos spawnPos = safePos(level, center.offset(ox, 0, oz));
            place(level, mat, spawnPos);
            // Pin them to the structure so they wander back inside instead of scattering.
            mat.setHomePos(safePos(level, center));
        }
    }

    private static BlockPos safePos(ServerLevel level, BlockPos near) {
        return level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, near);
    }

    private static void place(ServerLevel level, PathfinderMob mob, BlockPos pos) {
        mob.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        mob.finalizeSpawn(level, level.getCurrentDifficultyAt(pos),
                EntitySpawnReason.STRUCTURE, null);
        level.addFreshEntity(mob);
    }
}
