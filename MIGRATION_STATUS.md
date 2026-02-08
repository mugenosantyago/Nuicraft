# NuiCraft Migration Status: 1.12.1 Forge → 1.21.3 NeoForge

## ✅ Completed Tasks

### Build System & Configuration
- ✅ **Updated build.gradle** - Migrated from ForgeGradle 2.3 to NeoGradle 7.x
- ✅ **Updated gradle.properties** - Added NeoForge 1.21.3 dependencies and mod metadata
- ✅ **Created settings.gradle** - Added modern Gradle plugin management
- ✅ **Created gradle-wrapper.properties** - Using Gradle 8.10.2
- ✅ **Created META-INF/neoforge.mods.toml** - Replaced old mcmod.info

### Core Mod Structure
- ✅ **Updated NuiCraft.java** - Migrated to modern @Mod annotation and event system
  - Removed old @EventHandler methods
  - Removed SidedProxy system
  - Added modern IEventBus-based registration
  - Created CreativeTabs using new DeferredRegister system

### Registration System
- ✅ **Created NuiCraftRegistration.java** - Central registration hub for all deferred registers
- ✅ **Created core/NuiCraftBlocks.java** - DeferredRegister for all blocks (placeholder implementations)
- ✅ **Created core/NuiCraftItems.java** - DeferredRegister for all items (placeholder implementations)
- ✅ **Migrated language files** - Converted en_us.lang to en_us.json format

## 🚧 Tasks Requiring Completion

### Block Classes (HIGH PRIORITY)
Each block class needs to be updated for 1.21.3 API:

**Required Changes for ALL Block Classes:**
1. Change Material system → BlockBehaviour.Properties
2. Update setUnlocalizedName → No longer needed (uses lang files)
3. Update setRegistryName → Handled by DeferredRegister
4. Update setCreativeTab → Handled by CreativeTabs
5. Update Block method signatures (many changed in 1.13+)
6. Update IBlockState → BlockState
7. Update World → Level/ServerLevel
8. Update BlockPos usage (mostly the same)

**Blocks Needing Migration:**
- [ ] BlockBamboo.java
- [ ] BlockBionicleStone.java
- [ ] BlockKoro.java (complex - has metadata/variants)
- [ ] BlockLightstone.java (has special rendering)
- [ ] BlockMetal.java
- [ ] BlockNuvaCube.java ✅ (simple, started)
- [ ] BlockOre.java (needs loot table system update)
- [ ] BlockProtodermisDeposit.java
- [ ] fluid/BlockNuiCraftFluid.java → Need to use NeoForge fluid system

### Machine Blocks (COMPLEX)
- [ ] machine/maskForge/BlockMaskForge.java
  - Has TileEntity (needs BlockEntity migration)
  - Has GUI system (needs Menu/Screen migration)
  - Has custom rendering
- [ ] machine/purifier/BlockPurifier.java
  - Same complexity as BlockMaskForge

### Item Classes
**Required Changes for ALL Item Classes:**
1. Update ItemStack constructor usage
2. Remove setUnlocalizedName/setRegistryName
3. Update Item.Properties system
4. Update creative tab assignment
5. Update tooltip/display methods

**Items Needing Migration:**
- [ ] ItemBlockKoro.java
- [ ] ItemBlockGeneric.java
- [ ] ItemBlockPlacer.java
- [ ] ItemGeneric.java
- [ ] ItemGenericMeta.java (complex - has metadata system)
- [ ] ItemSluice.java
- [ ] ItemHeatstoneLighter.java

**Tool Items:**
- [ ] ItemBionicleAxe.java → Use modern AxeItem
- [ ] ItemBioniclePick.java → Use modern PickaxeItem
- [ ] ItemBionicleShovel.java → Use modern ShovelItem
- [ ] ItemBionicleSword.java → Use modern SwordItem
- [ ] ItemBionicleHoe.java → Use modern HoeItem

**Weapon Items:**
- [ ] kanoka/ItemDiscLauncher.java
- [ ] kanoka/ItemBambooDisc.java
- [ ] kanoka/ItemKanokaDisc.java

**Mask Items (Complex - has NBT data, coloring, armor functionality):**
- [ ] kanohi/ItemMask.java
- [ ] kanohi/ItemMaskMeta.java
- [ ] kanohi/ItemColoredMask.java
- [ ] kanohi/ItemGoldMataMask.java
- [ ] kanohi/DyeMaskRecipeFactory.java

### Entity System (CRITICAL)
All entities need complete rewrite for 1.21.3:

**Mobs:**
- [ ] mobs/mahi/EntityMahi.java
- [ ] mobs/fikou/EntityFikou.java
- [ ] mobs/hoi/EntityHoi.java
- [ ] mobs/kofo_jaga/EntityKofoJaga.java
- [ ] mobs/nui_jaga/EntityNuiJaga.java

**Projectiles:**
- [ ] kanoka/EntityKanoka.java

**Changes Needed:**
1. Create EntityType entries in NuiCraftRegistration
2. Update entity AI system (old AI → Goals system)
3. Update spawn egg system
4. Update entity attributes
5. Update entity data serialization (NBT)
6. Update entity networking

### Rendering System (CRITICAL)
- [ ] **Remove proxy system completely**
  - Delete ClientProxyBionicle.java
  - Delete CommonProxyBionicle.java
- [ ] **Create client event handler class**
  - Entity renderers
  - Block/Item colors
  - Model registration (now automatic in most cases)
- [ ] **Update entity renderers:**
  - mobs/*/Render*.java files
  - mobs/*/Model*.java files
  - Need to update to modern rendering pipeline
- [ ] **Update mask models:**
  - kanohi/ModelMaskIgnika.java
  - kanohi/ModelLongMask.java
  - kanohi/ModelAkakuMataMask.java

### TileEntity → BlockEntity Migration
- [ ] machine/maskForge/TileInventoryMaskForge.java
- [ ] machine/purifier/TileInventoryPurifier.java

**Changes Needed:**
1. TileEntity → BlockEntity
2. Update tick methods
3. Update NBT serialization
4. Update inventory handling (IItemHandler)
5. Register with BlockEntityType

### GUI/Container System → Menu/Screen
- [ ] GuiHandler.java → Delete, use MenuType system
- [ ] machine/maskForge/ContainerMaskForge.java → AbstractContainerMenu
- [ ] machine/maskForge/GuiMaskForgeInventory.java → Screen
- [ ] machine/maskForge/GuiHandlerMaskForge.java → Delete
- [ ] machine/purifier/ContainerPurifier.java → AbstractContainerMenu
- [ ] machine/purifier/GuiPurifierInventory.java → Screen
- [ ] machine/purifier/GuiHandlerPurifier.java → Delete

### Recipe System
- [ ] **Delete JSON recipes** (keep for reference, recreate with DataGen)
- [ ] **Create DataGen classes:**
  - data/RecipeProvider
  - data/LootTableProvider
  - data/BlockStateProvider
  - data/ItemModelProvider
- [ ] **Update custom recipes:**
  - machine/maskForge/recipe/* classes
  - CountedIngredient.java

### Fluid System
- [ ] **Migrate to NeoForge Fluid API:**
  - Create FluidType for each fluid
  - Create Fluid (still/flowing) for each
  - Create Block for fluids
  - Update NuiCraftFluids.java completely
  - Remove old FluidRegistry calls

### World Generation
- [ ] **NuiCraftWorldGenerator.java** - Complete rewrite
  - Old IWorldGenerator → BiomeModifier system
  - Update ore generation to use Configured Features
  - Update Placed Features
  - May need to create JSON files for features

### Particles
- [ ] particle/LighstoneFX.java → Update to ParticleProvider
- [ ] particle/TextureStitcherLightstoneFX.java

### Utilities & Misc
- [ ] ServerTickHandler.java → Update event handlers
- [ ] ClientTickHandler.java → Update event handlers  
- [ ] NuiCraftEventHooks.java → Update all events
- [ ] util/* classes - Various updates needed

### Data Files
- [ ] Update all blockstate JSON files
- [ ] Update all model JSON files
- [ ] Update texture references
- [ ] Create/update loot tables

## 📋 Migration Priority Order

1. **Phase 1: Core Compilation** (Get it to compile)
   - Fix all block classes
   - Fix all item classes
   - Create stub entity types
   
2. **Phase 2: Basic Functionality**
   - Complete entity migration
   - Update rendering system
   - Fix BlockEntities
   
3. **Phase 3: Advanced Features**
   - GUI/Menu system
   - Recipe system with DataGen
   - World generation
   - Fluids
   
4. **Phase 4: Polish**
   - Particles
   - Custom recipes
   - Loot tables
   - Testing and bug fixes

## 🔧 Key API Changes to Know

### 1.12 → 1.13 (The Flattening)
- Metadata removed completely
- IBlockState → BlockState
- All string IDs changed
- Recipes moved to JSON

### 1.13 → 1.16
- Forge config system overhauled
- New chunk generation system
- Dimensions overhauled

### 1.16 → 1.17
- Registry system updates
- World height changes

### 1.17 → 1.18
- Complete world generation rewrite
- New cave generation

### 1.18 → 1.20
- Various rendering updates
- Entity system refinements

### 1.20 → 1.21 (Forge → NeoForge)
- Forge split into NeoForge
- Many API relocations
- DeferredRegister improvements

## 📝 Notes

- Java 8 → Java 21 (significant language improvements available)
- Gradle 2.x → Gradle 8.x
- Many deprecated APIs need replacement
- Some features may need complete redesign

## 🎯 Next Steps

1. Start with simple block classes (BlockMetal, BlockBionicleStone)
2. Move to simple item classes
3. Test compilation frequently
4. One system at a time (don't try to fix everything at once)
