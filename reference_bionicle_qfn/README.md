# Reference assets from bionicle_qfn (extracted from JAR)

These were extracted from `bionicle_qfn-0.2.0.jar` for use in NuiCraft.

## 3D mask model (Blockbench Java format)

- **`models/haumasktest.json`** – Hau mask, made in Blockbench (Java entity/model format).

To use it as the 3D mask in NuiCraft (AzureLib uses Bedrock-style geo):

1. Open **Blockbench**.
2. **File → Open** and choose `models/haumasktest.json`.
3. **File → Export → Export Bedrock Geometry**.
4. Save as e.g. `mask.geo.json`.
5. Put the file in `src/main/resources/assets/nuicraft/geo/armor/` (replace or merge with the existing flat mask).
6. Ensure the **root bone** used for the head is named **`armorHead`** (or update `MaskArmorRenderer` to use the bone name from the export). Pivot for the head should be around `[0, 24, 0]` in model space.

## Textures

- **`textures/item/`** – Mask item icons (red_hau, black_pakari, blue_kaukau, etc.). You can copy these into `src/main/resources/assets/nuicraft/textures/item/masks/` and point your item models at them if you want to reuse the QFN look.
- **`textures/armor/`** – Vanilla-style armor layer textures (layer_1, layer_2 per mask). NuiCraft currently uses AzureLib 3D geo with per-mask textures; these layer textures are for the old 2D-overlay style and are kept here for reference.

## Note

The JAR contained **no** `.geo.json` or `.bbmodel` files. Only this one Java-format Blockbench model (`haumasktest.json`) was found for masks. Other files in the mod’s `models/custom/` are tools/weapons (axes, shields, etc.), not masks.
