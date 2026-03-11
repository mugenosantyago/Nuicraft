# Mata Nui Island – export from your save

Your full Mata Nui island map generates as **one structure** in ocean biomes. This file explains how to export it from your save and add it to the mod.

## What’s already set up

- **Structure:** `mata_nui_island` – single piece, no jigsaw expansion.
- **Where it generates:** Ocean biomes (all ocean and deep ocean types).
- **How often:** About every **400 chunks** (spacing 400, separation 380), so it’s rare.
- **Height:** The structure’s origin is placed at **Y = 62** (sea level). Build your island so the base is at Y 62 and the land rises above that.

## Exporting the island from your save

### Option 1: One structure block (if the island fits in 48×48×48)

1. Open your Mata Nui island save in Creative.
2. Place a **Structure block** (Save mode).
3. Set the bounds so they contain the **entire island** (and a bit of water if you want).
4. Put the **structure name** as: `mata_nui_island`.
5. Save the structure.
6. Copy the saved `.nbt` file into:
   - **Mod:** `src/main/resources/data/nuicraft/worldgen/structure/mata_nui_island.nbt`
   - Or in a datapack: `data/nuicraft/worldgen/structure/mata_nui_island.nbt`

Structure blocks are limited to **48×48×48**. If your island is bigger, use Option 2.

### Option 2: Island bigger than 48×48×48

Use one of these to export a larger region as a single structure:

- **Litematica** – save the island as a schematic, then use a tool to convert schematic → structure `.nbt`, and name it `mata_nui_island.nbt`.
- **WorldEdit** – `//copy`, then export the selection to a structure file (e.g. via a mod or script that writes `.nbt`), and name it `mata_nui_island.nbt`.
- Or build the island in **multiple 48×48×48 structure blocks** and then merge them in a 3D tool into one `mata_nui_island.nbt` (advanced).

Put the final file in:

`data/nuicraft/worldgen/structure/mata_nui_island.nbt`

## Origin and height (Y = 62)

- The mod places the structure with its **origin (0,0,0)** at world **Y = 62**.
- So in your save, when you set the structure block (or export region), the **corner that becomes (0,0,0)** will end up at sea level.
- Build the island so the **base of the land** is at Y 62 (or the bottom of your export box is at 62). Then it will sit correctly in the ocean in generated worlds.

## Tweaking how often it generates

Edit:

`data/nuicraft/worldgen/structure_set/mata_nui_island.json`

- **spacing:** average chunks between attempts (e.g. `400` = rare).
- **separation:** minimum chunks between two islands (e.g. `380`).
- Increase both to make it rarer; decrease to make it more frequent (e.g. 200/180).

## Biomes

The island is set to generate only in the biomes listed in the tag:

`data/nuicraft/tags/worldgen/biome/ocean_island_spawn.json`

Right now that’s all vanilla ocean and deep ocean variants. You can remove or add biomes there if you want it only in warm ocean, etc.
