// DEPRECATED: Fluid system needs complete rewrite for NeoForge 1.21.3
// Old FluidRegistry system is completely replaced
// Modern fluids use FluidType and DeferredRegister
//
// TODO: Implement modern fluid system in core/NuiCraftFluids.java

package eastonium.nuicraft;

/**
 * @deprecated Fluid system needs migration to NeoForge FluidType API
 * 
 * This file is kept temporarily for reference during migration.
 * 
 * Modern fluid system requires:
 * 1. FluidType registration (physical properties)
 * 2. Fluid registration (still/flowing variants)
 * 3. Block registration (LiquidBlock)
 * 4. Item registration (BucketItem)
 * 
 * See MIGRATION_STATUS.md for implementation details.
 */
@Deprecated
public class NuiCraftFluids {
    // Fluid system needs complete rewrite
    // TODO: Implement in core package using NeoForge FluidType API
}
