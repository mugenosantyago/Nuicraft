# 1.20.3 Bionicle Mod Port Analysis

## Summary
Porting Bionicle QFN mod from Forge 1.20.3 to NeoForge 1.21.8 for NuiCraft.

## Entities to Port (47 total)

### Bohrok (6)
- bohrok_tahnok
- gahlok
- kohrak
- lehvak
- nuhvok
- pahrak

### Rahkshi (7)
- guurahk
- kurahk
- lerahk
- panrahk
- rahkshi_yellow
- turahk
- vohrak

### Matoran (22)
- agni_matoran
- ahkmou_matoran
- boreas_matoran
- hafu_matoran
- hewkii_matoran
- jaller_matoran
- kokkan_matoran
- kongu_matoran
- kotu_matoran
- macku_matoran
- matoro_matoran
- nuparu_matoran
- okoth_matoran
- onepu_matoran
- pakastaa_matoran
- tuuli_matoran
- vohon_matoran
- zemya_matoran

### Turaga (6)
- matau_turaga
- nokama_turaga
- nuju_turaga
- onewa_turaga
- vakama_turaga
- whenua_turaga

### Rahi (6)
- kane_ra
- muaka
- nui_jaga
- nui_rama_green
- nui_rama_orange
- spider_fikou
- tarakava_blue
- tarakava_green
- tarakava_yellow
- makuta

## Assets Extracted
- ✅ Entity textures: `extracted_1203_textures_entities/`
- ✅ Item textures: From original JAR
- ✅ Recipes (94 total): `extracted_1203_recipes/`
- ✅ Loot tables: `extracted_1203_loot_tables/`
- ✅ Biome modifiers: `extracted_1203_biome_modifiers/`

## Port Strategy

### Phase 1: Items & Blocks
1. Register all items from recipes (Kanoka disks, weapons, armor, etc.)
2. Register all blocks (ores, building blocks, etc.)
3. Create item models and textures

### Phase 2: Entities
1. Create generic entity class extending Animal/Monster
2. Create renderers using GenericNuiCraftRenderer pattern
3. Register entity types with attributes
4. Copy textures

### Phase 3: Spawning
1. Convert Forge biome_modifier to NeoForge biome_modifier
2. Set spawn placements
3. Define entity attributes (health, speed, etc.)

### Phase 4: Loot & Recipes
1. Copy/convert recipes to NeoForge format
2. Set up entity loot tables
3. Add to creative tab

## Key Differences: Forge 1.20.3 → NeoForge 1.21.8
- Biome modifier format stays same but under `neoforge:add_spawns`
- Item registration uses DeferredRegister (same)
- Entity attributes registration method is same
- Renderer registration uses `RegisterRenderers` event

## Files to Create
- Entity classes: ~47 files
- Entity renderers: ~10-15 files (can reuse generic renderer)
- Item classes/registration updates
- Recipes (can copy JSON directly)
- Loot tables (can copy JSON directly, update entity names)
- Biome modifiers (update format for NeoForge)
