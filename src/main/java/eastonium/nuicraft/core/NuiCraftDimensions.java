package eastonium.nuicraft.core;

import eastonium.nuicraft.NuiCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.LevelStem;

/**
 * Resource keys for NuiCraft dimensions.
 * The Mata Nui dimension must be present in the world's dimension list (e.g. use the NuiCraft world preset when creating a world).
 */
public class NuiCraftDimensions {
    public static final ResourceKey<LevelStem> MATA_NUI_STEM = ResourceKey.create(
            Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "mata_nui"));
    public static final ResourceKey<Level> MATA_NUI = ResourceKey.create(
            Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "mata_nui"));
}
