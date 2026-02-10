#!/usr/bin/env python3
"""
Convert mask textures to 128x64 armor overlay format (like 1.20.1 full-head mask).
Mask is drawn on all six head faces so it wraps the whole head with full detail.
Uses 16x16 source from entity/equipment/masks/.
"""
from pathlib import Path
from PIL import Image

# Prefer 16x16 source from here (same filenames as humanoid/masks)
SOURCE_MASKS_DIR = Path(__file__).resolve().parent.parent / "src/main/resources/assets/nuicraft/textures/entity/equipment/masks"
OUT_MASKS_DIR = Path(__file__).resolve().parent.parent / "src/main/resources/assets/nuicraft/textures/entity/equipment/humanoid/masks"

SIZE_ARMOR_HIRES = (128, 64)
# All six head faces for 128x64 (full-head mask like 1.20.1)
HEAD_FACE_POSITIONS_128 = [
    (112, 16),  # SOUTH (front)
    (80, 16),   # NORTH (back)
    (96, 16),   # EAST
    (64, 16),   # WEST
    (96, 0),    # UP
    (80, 0),    # DOWN
]

def main():
    out_dir = OUT_MASKS_DIR
    if not out_dir.exists():
        out_dir.mkdir(parents=True, exist_ok=True)
    # List masks from output dir (or source dir if output empty)
    names = sorted({f.name for f in out_dir.glob("*.png")} or {f.name for f in SOURCE_MASKS_DIR.glob("*.png")})
    if not names:
        print("No mask PNGs found.")
        return
    for name in names:
        # Prefer 16x16 source
        src_path = SOURCE_MASKS_DIR / name
        fallback = out_dir / name
        if src_path.exists():
            img = Image.open(src_path).convert("RGBA")
        elif fallback.exists():
            img = Image.open(fallback).convert("RGBA")
        else:
            print(f"Skip {name}: no source")
            continue
        # Get 16x16 mask (use as-is or scale)
        if img.size == (16, 16):
            mask_16 = img
        elif img.size == (64, 32):
            # Reuse front face 8x8 from current texture, scale up to 16x16
            face = img.crop((56, 8, 64, 16))
            mask_16 = face.resize((16, 16), Image.Resampling.NEAREST)
        else:
            mask_16 = img.resize((16, 16), Image.Resampling.NEAREST)
        # Build 128x64: mask on all six head faces (full-head like 1.20.1)
        out = Image.new("RGBA", SIZE_ARMOR_HIRES, (0, 0, 0, 0))
        for (x, y) in HEAD_FACE_POSITIONS_128:
            out.paste(mask_16, (x, y))
        out_path = out_dir / name
        out.save(out_path, "PNG")
        print(f"OK: {name} (128x64, all 6 faces, from {'16x16' if img.size == (16, 16) else '64x32'})")
    print("Done.")

if __name__ == "__main__":
    main()
