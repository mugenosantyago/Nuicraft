package eastonium.nuicraft.event;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import eastonium.nuicraft.config.NuiCraftConfig;
import eastonium.nuicraft.core.NuiCraftDimensions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import org.slf4j.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.*;
import java.util.stream.Stream;

/**
 * When the Mata Nui dimension is first loaded, optionally copy region data from the
 * configured "Matanui world" save (its overworld) so the dimension uses that terrain.
 * Only runs once per world; requires the source world to have been opened in 1.21 to upgrade chunks.
 */
public class MataNuiWorldImport {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final String IMPORT_DONE_KEY = "matanui_import_done";

    @SubscribeEvent
    public static void onLevelLoad(LevelEvent.Load event) {
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) return;
        if (!serverLevel.dimension().equals(NuiCraftDimensions.MATA_NUI)) return;

        String pathConfig = NuiCraftConfig.getMatanuiWorldPath();
        if (pathConfig == null || pathConfig.isBlank()) return;

        try {
            DimensionDataStorage storage = serverLevel.getDataStorage();
            MataNuiImportMarker marker = storage.computeIfAbsent(MataNuiImportMarker.TYPE);
            if (marker.done) return;

            var server = serverLevel.getServer();
            LevelStorageSource.LevelStorageAccess levelStorage = getLevelStorage(server);
            if (levelStorage == null) {
                LOGGER.warn("NuiCraft: Could not get level storage for Mata Nui import");
                return;
            }

            Path serverDir = server.getServerDirectory();
            Path sourceRegionDir = serverDir.resolve(pathConfig).resolve("region");
            Path targetDimensionDir = levelStorage.getDimensionPath(NuiCraftDimensions.MATA_NUI);
            Path targetRegionDir = targetDimensionDir.resolve("region");

            if (!Files.isDirectory(sourceRegionDir)) {
                LOGGER.info("NuiCraft: Matanui world path not found or has no region folder: {}", sourceRegionDir);
                return;
            }

            Files.createDirectories(targetRegionDir);
            int count = 0;
            try (Stream<Path> stream = Files.list(sourceRegionDir)) {
                for (Path p : stream.toList()) {
                    if (p.getFileName().toString().endsWith(".mca")) {
                        Path dest = targetRegionDir.resolve(p.getFileName());
                        Files.copy(p, dest, StandardCopyOption.REPLACE_EXISTING);
                        count++;
                    }
                }
            }
            if (count > 0) {
                marker.setDone();
                LOGGER.info("NuiCraft: Imported {} region file(s) from Matanui world into Mata Nui dimension.", count);
            }
        } catch (IOException e) {
            LOGGER.error("NuiCraft: Failed to import Matanui world data", e);
        } catch (Exception e) {
            LOGGER.error("NuiCraft: Error during Mata Nui import", e);
        }
    }

    @SuppressWarnings("deprecation")
    private static LevelStorageSource.LevelStorageAccess getLevelStorage(net.minecraft.server.MinecraftServer server) {
        try {
            Field f = net.minecraft.server.MinecraftServer.class.getDeclaredField("storageSource");
            f.setAccessible(true);
            return (LevelStorageSource.LevelStorageAccess) f.get(server);
        } catch (Exception e) {
            LOGGER.debug("NuiCraft: Reflection for storageSource failed", e);
            return null;
        }
    }

    /** SavedData that marks whether we already ran the import for this dimension. */
    public static class MataNuiImportMarker extends SavedData {
        private static final Codec<MataNuiImportMarker> CODEC = Codec.BOOL.xmap(
                done -> {
                    MataNuiImportMarker m = new MataNuiImportMarker();
                    m.done = done;
                    return m;
                },
                m -> m.done
        );
        public static final SavedDataType<MataNuiImportMarker> TYPE = new SavedDataType<>(IMPORT_DONE_KEY, MataNuiImportMarker::new, CODEC);

        private boolean done;

        public MataNuiImportMarker() {
            super();
        }

        public void setDone() {
            this.done = true;
            setDirty();
        }
    }
}
