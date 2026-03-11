// DEPRECATED: This file has been replaced by core/MnogiiItems.java
// The old @ObjectHolder system is no longer used
// Modern registration uses DeferredRegister
//
// See: src/main/java/eastonium/nuicraft/core/MnogiiItems.java

package eastonium.mnogii;

/**
 * @deprecated Use {@link eastonium.mnogii.core.MnogiiItems} instead
 * 
 * This file is kept temporarily for compatibility during migration.
 * All item registrations have been moved to the core package.
 * 
 * Old @ObjectHolder pattern is replaced by DeferredRegister.
 * Model registration is now handled automatically by assets or in ClientSetup.
 */
@Deprecated
public class MnogiiItems {
    // All items moved to core.MnogiiItems
    // This class exists only to prevent compilation errors in unmigrated files
    // TODO: Delete after all references updated to core.MnogiiItems
}