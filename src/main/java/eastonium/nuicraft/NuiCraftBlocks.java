// DEPRECATED: This file has been replaced by core/NuiCraftBlocks.java
// The old @ObjectHolder system is no longer used
// Modern registration uses DeferredRegister
//
// See: src/main/java/eastonium/nuicraft/core/NuiCraftBlocks.java

package eastonium.nuicraft;

/**
 * @deprecated Use {@link eastonium.nuicraft.core.NuiCraftBlocks} instead
 * 
 * This file is kept temporarily for compatibility during migration.
 * All block registrations have been moved to the core package.
 * 
 * Old @ObjectHolder pattern is replaced by DeferredRegister.
 * Model registration is now handled automatically by assets or in ClientSetup.
 */
@Deprecated
public class NuiCraftBlocks {
    // All blocks moved to core.NuiCraftBlocks
    // This class exists only to prevent compilation errors in unmigrated files
    // TODO: Delete after all references updated to core.NuiCraftBlocks
}