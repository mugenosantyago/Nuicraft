# NuiCraft - Minecraft 1.12.1 Forge → 1.21.3 NeoForge Migration

## 🎯 Project Status

This Bionicle-themed Minecraft mod is currently undergoing a **major version migration** from:
- **Source:** Minecraft 1.12.1 with Forge
- **Target:** Minecraft 1.21.3 with NeoForge

This represents **9 major Minecraft versions** worth of changes, including:
- The "Flattening" (1.13) - Complete metadata system overhaul
- Multiple rendering pipeline changes
- Forge → NeoForge split (1.20.2)
- Java 8 → Java 21

## ⚠️ Current State: **NOT COMPILABLE**

The mod is currently **in progress** and will not compile. This is expected at this stage of migration. Many files are stubs or partially updated.

## ✅ What's Been Completed

### Build System (100% Complete)
- ✅ **build.gradle** - Fully migrated to NeoGradle 7.0.163
- ✅ **gradle.properties** - Updated for NeoForge 1.21.3
- ✅ **settings.gradle** - Created with modern plugin management
- ✅ **gradle-wrapper.properties** - Gradle 8.10.2

### Mod Configuration (100% Complete)
- ✅ **META-INF/neoforge.mods.toml** - Replaces old mcmod.info
- ✅ **en_us.json** - Language file converted from .lang format

### Core Registration (75% Complete)
- ✅ **NuiCraft.java** - Main mod class updated
  - Modern @Mod annotation
  - IEventBus-based registration
  - Creative tabs via DeferredRegister
- ✅ **NuiCraftRegistration.java** - Central deferred register hub
- ✅ **core/NuiCraftBlocks.java** - Block registration (stubs created)
- ✅ **core/NuiCraftItems.java** - Item registration (stubs created)

### Updated Block Classes (20% Complete)
- ✅ **BlockMetal.java** - Fully updated
- ✅ **BlockBionicleStone.java** - Fully updated (with directional facing)
- ✅ **BlockNuvaCube.java** - Fully updated
- ✅ **BlockLightstone.java** - Fully updated (particle effects stubbed)

## 🚧 What Still Needs Work

### High Priority - Required for Compilation

#### 1. Block Classes (Many remaining)
The following blocks need to be updated to modern API:

**Simple Blocks (Lower Complexity):**
- [ ] `BlockBamboo.java` - Plant block
- [ ] `BlockOre.java` - Ore drops need loot table system
- [ ] `BlockProtodermisDeposit.java` - Special ore behavior
- [ ] `fluid/BlockNuiCraftFluid.java` - Needs NeoForge fluid API

**Complex Blocks (Higher Complexity):**
- [ ] `BlockKoro.java` - Has many variants (was using metadata)
- [ ] `machine/maskForge/BlockMaskForge.java` - Has BlockEntity, GUI, custom rendering
- [ ] `machine/purifier/BlockPurifier.java` - Has BlockEntity, GUI

#### 2. Item Classes (All need updates)
**Tool Items:**
- [ ] Update tool tier system (currently using placeholder Tiers.IRON)
- [ ] Create custom `ProtodermisToolTier` and `ProtosteelToolTier`

**Special Items:**
- [ ] `ItemGenericMeta.java` - Complex multi-variant item system
- [ ] `ItemHeatstoneLighter.java`
- [ ] `ItemSluice.java`
- [ ] `ItemDiscLauncher.java`
- [ ] `kanoka/ItemBambooDisc.java`
- [ ] `kanoka/ItemKanokaDisc.java`

**Mask Items (Complex - Armor functionality):**
- [ ] `kanohi/ItemMask.java`
- [ ] `kanohi/ItemMaskMeta.java`
- [ ] `kanohi/ItemColoredMask.java` - Has NBT color data
- [ ] `kanohi/ItemGoldMataMask.java`
- [ ] Mask 3D model classes

#### 3. Entity System (Critical - Complete Rewrite Needed)
**Mobs:**
- [ ] `mobs/mahi/EntityMahi.java`
- [ ] `mobs/fikou/EntityFikou.java`
- [ ] `mobs/hoi/EntityHoi.java`
- [ ] `mobs/kofo_jaga/EntityKofoJaga.java`
- [ ] `mobs/nui_jaga/EntityNuiJaga.java`

**Projectiles:**
- [ ] `kanoka/EntityKanoka.java`

**Required Changes:**
1. Create `EntityType<?>` entries in registration
2. Migrate AI system (old `EntityAI*` → modern `Goal` system)
3. Update attributes system
4. Update NBT serialization
5. Entity spawn eggs
6. Entity networking

#### 4. Rendering System
**Delete These (Proxy System is Gone):**
- ❌ `proxy/CommonProxyBionicle.java`
- ❌ `proxy/ClientProxyBionicle.java`

**Create New Client-Side Handler:**
- [ ] Create `client/ClientSetup.java` for:
  - Entity renderer registration
  - Block/Item colors
  - Particle registration

**Update Renderers:**
- [ ] All `mobs/*/Render*.java` files
- [ ] All `mobs/*/Model*.java` files
- [ ] `kanoka/RenderKanoka.java`

#### 5. BlockEntity (was TileEntity)
- [ ] `machine/maskForge/TileInventoryMaskForge.java` → BlockEntity
- [ ] `machine/purifier/TileInventoryPurifier.java` → BlockEntity
- [ ] Update tick methods
- [ ] Update NBT handling
- [ ] Update inventory (use `IItemHandler`)

#### 6. GUI/Menu/Screen System
**Delete Old System:**
- ❌ `GuiHandler.java`
- ❌ `machine/*/GuiHandler*.java`

**Migrate to Modern System:**
- [ ] `machine/maskForge/ContainerMaskForge.java` → `MenuType`
- [ ] `machine/maskForge/GuiMaskForgeInventory.java` → `Screen`
- [ ] `machine/purifier/ContainerPurifier.java` → `MenuType`
- [ ] `machine/purifier/GuiPurifierInventory.java` → `Screen`

#### 7. Recipe System
- [ ] Delete JSON recipes (or keep for reference)
- [ ] Create data generation classes:
  - [ ] `data/RecipeProvider`
  - [ ] `data/LootTableProvider`
  - [ ] `data/BlockStateProvider`
  - [ ] `data/ItemModelProvider`
- [ ] Migrate custom recipes:
  - [ ] `machine/maskForge/recipe/*` classes

#### 8. Fluid System (Major Changes)
- [ ] Complete rewrite of `NuiCraftFluids.java`
- [ ] Create `FluidType` for each fluid
- [ ] Create flowing/still `Fluid` variants
- [ ] Register fluid blocks
- [ ] Remove old `FluidRegistry` calls

#### 9. World Generation (Complete Rewrite)
- [ ] `NuiCraftWorldGenerator.java` - Replace with:
  - Configured Features (JSON or code)
  - Placed Features (JSON or code)
  - Biome Modifiers (JSON or code)
- [ ] Ore generation
- [ ] Structure generation (if any)

#### 10. Particles
- [ ] `particle/LighstoneFX.java` → ParticleProvider
- [ ] `particle/TextureStitcherLightstoneFX.java` → Update

#### 11. Event Handlers
- [ ] `ServerTickHandler.java` - Update events
- [ ] `ClientTickHandler.java` - Update events
- [ ] `NuiCraftEventHooks.java` - Update all event subscriptions

#### 12. Assets & Data
- [ ] Update all blockstate JSONs
- [ ] Update all model JSONs
- [ ] Update texture references
- [ ] Create loot tables for blocks
- [ ] Create data pack structure

## 📚 Key API Changes to Understand

### Material System → BlockBehaviour.Properties
```java
// Old (1.12)
new Block(Material.ROCK)
    .setHardness(3.0F)
    .setResistance(5.0F)
    .setSoundType(SoundType.STONE);

// New (1.21)
Block.Properties.of()
    .strength(3.0F, 5.0F)
    .sound(SoundType.STONE)
    .requiresCorrectToolForDrops();
```

### Metadata → BlockState Properties
```java
// Old (1.12) - Using metadata
int meta = stack.getMetadata();
state.getValue(PROPERTY);

// New (1.21) - Properties only
state.getValue(BlockStateProperties.FACING);
// No metadata - use separate items or NBT
```

### Registration
```java
// Old (1.12)
@GameRegistry.ObjectHolder
GameRegistry.register(block);

// New (1.21)
DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(MODID);
DeferredBlock<Block> BLOCK = BLOCKS.register("name", () -> new Block(...));
```

### Sided Operations
```java
// Old (1.12)
@SidedProxy
proxy.doClientThing();

// New (1.21)
if (level.isClientSide()) {
    // Client only code
}
// OR use DistExecutor for registration
```

## 🔨 How to Continue Migration

### Recommended Approach:

1. **Phase 1: Get Basic Compilation**
   - Finish all block classes
   - Finish all item classes
   - Create stub entities (even if non-functional)
   - Aim: `gradle build` succeeds

2. **Phase 2: Make It Loadable**
   - Implement entity types properly
   - Fix any registration issues
   - Aim: Mod loads in game

3. **Phase 3: Restore Functionality**
   - Entity AI and behavior
   - Block functionality
   - Item functionality
   - Aim: Basic gameplay works

4. **Phase 4: Advanced Features**
   - GUIs/Menus
   - Custom recipes
   - World generation
   - Fluids
   - Aim: Feature complete

5. **Phase 5: Polish**
   - Rendering (particles, models)
   - Data generation
   - Loot tables
   - Testing
   - Aim: Release ready

### Development Tips:

1. **Test Frequently** - Try to compile after each major change
2. **One System at a Time** - Don't try to fix everything at once
3. **Use Modern Examples** - Look at other 1.21 mods for reference
4. **Incremental Progress** - Get things working, then optimize
5. **Keep Old Code** - Comment out rather than delete for reference

## 📖 Useful Resources

- [NeoForge Documentation](https://docs.neoforged.net/)
- [Minecraft Forge Community Wiki](https://forge.gemwire.uk/wiki/Main_Page)
- [Modding Discord Servers](https://discord.gg/neoforged)
- [Parchment Mappings](https://github.com/ParchmentMC/Parchment)

## 🤝 Need Help?

For detailed migration status and what's been changed, see `MIGRATION_STATUS.md`.

The migration from 1.12 → 1.21 is one of the most complex Minecraft mod migrations possible. Take it step by step, and don't hesitate to ask for help in modding communities!

---

**Note:** This is a work-in-progress migration. The mod represents significant work and has great potential. The Bionicle theme is well-executed with custom mobs, items, blocks, and mechanics. With patience and systematic work, it will be fantastic on modern Minecraft!
