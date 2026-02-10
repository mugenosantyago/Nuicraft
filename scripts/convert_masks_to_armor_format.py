#!/usr/bin/env python3
"""
Convert mask textures to 64x32 armor overlay format with correct UV layout.
Vanilla humanoid head is an 8x8 cube; texOffs(32,0). Each face uses an 8x8 region:
  SOUTH (front): (56,8)-(64,16)   NORTH (back): (40,8)-(48,16)
  EAST: (48,8)-(56,16)            WEST: (32,8)-(40,16)
  UP: (48,0)-(56,8)               DOWN: (40,0)-(48,8)
We paste the mask (scaled to 8x8) into all six regions so the front and sides show the mask.
"""
from pathlib import Path
from PIL import Image

MASKS_DIR = Path(__file__).resolve().parent.parent / "src/main/resources/assets/nuicraft/textures/entity/equipment/humanoid/masks"
SIZE_ARMOR = (64, 32)
# Head cube face regions (x, y) for 8x8 paste in 64x32 texture
HEAD_FACE_POSITIONS = [
    (56, 8),   # SOUTH (front - what you see)
    (40, 8),   # NORTH (back)
    (48, 8),   # EAST
    (32, 8),   # WEST
    (48, 0),   # UP
    (40, 0),   # DOWN
]

def main():
    if not MASKS_DIR.exists():
        print(f"Missing: {MASKS_DIR}")
        return
    for png in sorted(MASKS_DIR.glob("*.png")):
        try:
            img = Image.open(png).convert("RGBA")
        except Exception as e:
            print(f"Skip {png.name}: {e}")
            continue
        # Get mask as 8x8: from 16x16 source or from existing 64x32 right half
        if img.size == (16, 16):
            mask_8 = img.resize((8, 8), Image.Resampling.NEAREST)
        elif img.size == SIZE_ARMOR:
            # Reuse right half (32x32), scale to 8x8
            right_half = img.crop((32, 0, 64, 32))
            mask_8 = right_half.resize((8, 8), Image.Resampling.NEAREST)
        else:
            # Arbitrary size: scale to 8x8
            mask_8 = img.resize((8, 8), Image.Resampling.NEAREST)
        # Build 64x32: transparent then paste 8x8 into each face
        out = Image.new("RGBA", SIZE_ARMOR, (0, 0, 0, 0))
        for (x, y) in HEAD_FACE_POSITIONS:
            out.paste(mask_8, (x, y))
        out.save(png, "PNG")
        print(f"Fixed: {png.name} (mask in all 6 head faces, front at 56,8)")
    print("Done.")

if __name__ == "__main__":
    main()
