#!/usr/bin/env python3
"""
Convert 16x16 mask textures to 64x32 armor overlay format.
Vanilla humanoid armor uses 64x32; the head overlay uses the right half (32,0)-(64,32).
We place each mask (scaled 16->32) in that region.
"""
from pathlib import Path
from PIL import Image

MASKS_DIR = Path(__file__).resolve().parent.parent / "src/main/resources/assets/nuicraft/textures/entity/equipment/humanoid/masks"
SIZE_ARMOR = (64, 32)
HEAD_REGION = (32, 0, 64, 32)  # right half for head overlay

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
        if img.size == SIZE_ARMOR:
            print(f"Already 64x32: {png.name}")
            continue
        # Create 64x32 transparent canvas
        out = Image.new("RGBA", SIZE_ARMOR, (0, 0, 0, 0))
        # Scale mask to 32x32 and paste into head region (32,0)
        head_size = (32, 32)
        scaled = img.resize(head_size, Image.Resampling.NEAREST)
        out.paste(scaled, (32, 0))
        out.save(png, "PNG")
        print(f"Converted: {png.name} -> 64x32 (head region)")
    print("Done.")

if __name__ == "__main__":
    main()
