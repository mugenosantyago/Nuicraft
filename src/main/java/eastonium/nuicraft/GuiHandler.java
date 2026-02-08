// DEPRECATED: Old GUI handler system (1.12)
// Modern Minecraft uses MenuType registration and IMenuProvider
// See modern pattern in machine blocks (BlockMaskForge, BlockPurifier)
// TODO: Delete this file after migrating all GUIs to MenuType system
//
// Migration needed:
// 1. Register MenuTypes in NuiCraftRegistration
// 2. Implement IMenuProvider on blocks
// 3. Create AbstractContainerMenu classes
// 4. Create Screen classes for client

package eastonium.nuicraft;

/**
 * Stub class for compilation
 * 
 * GUI system completely changed in modern Minecraft:
 * - Old: IGuiHandler with integer IDs
 * - New: MenuType registration + IMenuProvider
 * 
 * See MIGRATION_STATUS.md for implementation details
 */
public class GuiHandler {
    // Old GUI handler system deprecated
    // Modern GUI opening uses player.openMenu(menuProvider)
}