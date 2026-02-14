# Village structure placeholders (Koro)

The six Koro (Ta-Koro, Ga-Koro, Po-Koro, Onu-Koro, Le-Koro, Ko-Koro) are jigsaw structures. The JSON config is in place; the **structure piece files (`.nbt`) are not**—add them when you build the villages in-game.

## Where to put your structure files

Save structure block exports here (as part of the mod or in a datapack):

```
data/nuicraft/worldgen/structure/<name>.nbt
```

So for example: `data/nuicraft/worldgen/structure/takoro_center.nbt`

## Required structure IDs (file names)

Use these **exact** IDs as the file name (without `.nbt`). Jigsaw connectors in each piece must match the pool names in `template_pool/*.json`.

### Ta-Koro
| Structure ID | Filename |
|--------------|----------|
| `takoro_center` | `takoro_center.nbt` |
| `takoro_entrancepaths` | `takoro_entrancepaths.nbt` |
| `takoro_villagerhouses1` | `takoro_villagerhouses1.nbt` |
| `takoro_villagerhouses2` | `takoro_villagerhouses2.nbt` |
| `takoro_villagerhouses3` | `takoro_villagerhouses3.nbt` |

### Ga-Koro
| Structure ID | Filename |
|--------------|----------|
| `gakoro_center` | `gakoro_center.nbt` |
| `gakoro_transition` | `gakoro_transition.nbt` |
| `gakoro_leftlilypad` | `gakoro_leftlilypad.nbt` |
| `gakoro_leftsecondlilypad` | `gakoro_leftsecondlilypad.nbt` |
| `gakoro_rightlilypad` | `gakoro_rightlilypad.nbt` |
| `gakoro_rightsecondlilypad` | `gakoro_rightsecondlilypad.nbt` |
| `gakoro_nixiehut` | `gakoro_nixiehut.nbt` |

### Po-Koro
| Structure ID | Filename |
|--------------|----------|
| `pokoro_center` | `pokoro_center.nbt` |
| `pokoro_center2` | `pokoro_center2.nbt` |
| `pokoro_entrance` | `pokoro_entrance.nbt` |
| `pokoro_entrance2` | `pokoro_entrance2.nbt` |
| `pokoro_leftcorner` | `pokoro_leftcorner.nbt` |
| `pokoro_leftsecondcorner` | `pokoro_leftsecondcorner.nbt` |
| `pokoro_rightcorner` | `pokoro_rightcorner.nbt` |
| `pokoro_rightsecondcorner` | `pokoro_rightsecondcorner.nbt` |

### Onu-Koro
| Structure ID | Filename |
|--------------|----------|
| `onukoro_entrance` | `onukoro_entrance.nbt` |
| `onukoro_entrance2` | `onukoro_entrance2.nbt` |
| `onukoro_center` | `onukoro_center.nbt` |
| `onukoro_ravin` | `onukoro_ravin.nbt` |

### Le-Koro
| Structure ID | Filename |
|--------------|----------|
| `lekoro_center` | `lekoro_center.nbt` |
| `lekoro_lefttrees` | `lekoro_lefttrees.nbt` |
| `lekoro_righttrees` | `lekoro_righttrees.nbt` |
| `lekoro_rightsecondtrees` | `lekoro_rightsecondtrees.nbt` |

### Ko-Koro
| Structure ID | Filename |
|--------------|----------|
| `ko-koro` | `ko-koro.nbt` |

---

## How to create the `.nbt` files

1. In a creative world, build a village piece (e.g. Ta-Koro center).
2. Place a **Structure Block** in **Save** mode, set the size to include your build, and type the structure name (e.g. `takoro_center`).
3. Save the structure.
4. Export it:
   - **Option A:** Use “Export” in the structure block (if your setup supports it), or copy the structure file from your world save: `saves/<World>/generated/<namespace>/structures/<name>.nbt` (or from the structure block export folder).
   - **Option B:** Use a datapack that exports structures, or copy from `.minecraft/saves/<World>/generated/` after saving.
5. Put the `.nbt` file into `data/nuicraft/worldgen/structure/` in the mod’s `src/main/resources` (or in a datapack with the same path).

For jigsaw villages, each piece needs **jigsaw block** connectors that match the pool and target names in the corresponding `template_pool/*.json` (see the pool `name` and any `target` / joint names in the JSON).

## If you get “missing structure” or loading errors

The game will try to load these structures when generating chunks in the Koro biomes. Until the `.nbt` files exist, you may see errors in the log. To avoid that until your builds are ready, you can temporarily remove or rename the six files in `structure_set/` (e.g. `takoro.json`, `gakoro.json`, …) so the Koro are not registered for placement. Restore those files once you’ve added the structure `.nbt` files.
