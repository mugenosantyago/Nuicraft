#!/usr/bin/env python3
"""
Convert mask OBJ/MTL voxel models to Bedrock geo.json + palette texture PNG.

Fixes UV mapping issues where masks appear gray/black due to incorrect
conversion from VoxiGen OBJ output.

Each voxel in the OBJ gets one cube in the geo.json, with per-face UV
mapping to a compact palette texture. All 6 faces of each cube point to
the single pixel of that voxel's color.

Usage:
    python3 scripts/convert_obj_to_bedrock_geo.py
    python3 scripts/convert_obj_to_bedrock_geo.py kaukau   # single mask
"""

import json
import math
import sys
from pathlib import Path

try:
    from PIL import Image
except ImportError:
    print("Pillow is required: pip install Pillow")
    sys.exit(1)

# Directories (relative to project root)
SCRIPT_DIR = Path(__file__).resolve().parent
PROJECT_ROOT = SCRIPT_DIR.parent
MODELS_DIR = PROJECT_ROOT / "models"
GEO_OUT_DIR = PROJECT_ROOT / "src" / "main" / "resources" / "assets" / "nuicraft" / "geo" / "armor"
TEX_OUT_DIR = PROJECT_ROOT / "src" / "main" / "resources" / "assets" / "nuicraft" / "textures" / "armor"

# Target dimensions for the mask on the player head (Bedrock model units)
TARGET_WIDTH = 13.0
TARGET_HEIGHT = 14.0
Y_BOTTOM = 19.0       # Bottom of the mask area (just below the chin)
Z_FRONT = -6.0        # Z position (front of the face)


def parse_mtl(filepath):
    """Parse MTL file -> {material_name: (r, g, b, a)} with values 0-255."""
    materials = {}
    current = None
    kd = (0.0, 0.0, 0.0)
    d = 1.0

    with open(filepath) as f:
        for line in f:
            line = line.strip()
            if line.startswith("newmtl "):
                # Save previous material
                if current is not None:
                    materials[current] = (
                        min(255, max(0, int(round(kd[0] * 255)))),
                        min(255, max(0, int(round(kd[1] * 255)))),
                        min(255, max(0, int(round(kd[2] * 255)))),
                        min(255, max(0, int(round(d * 255)))),
                    )
                current = line.split(None, 1)[1]
                kd = (0.0, 0.0, 0.0)
                d = 1.0
            elif line.startswith("Kd "):
                parts = line.split()
                kd = (float(parts[1]), float(parts[2]), float(parts[3]))
            elif line.startswith("d "):
                d = float(line.split()[1])

    # Save last material
    if current is not None:
        materials[current] = (
            min(255, max(0, int(round(kd[0] * 255)))),
            min(255, max(0, int(round(kd[1] * 255)))),
            min(255, max(0, int(round(kd[2] * 255)))),
            min(255, max(0, int(round(d * 255)))),
        )
    return materials


def parse_obj(filepath):
    """
    Parse OBJ file -> {(vx, vy, vz): material_name}.

    Uses face normals to push each face's center inward to correctly identify
    which voxel the face belongs to (handles front/back/side faces properly).

    VoxiGen models may have non-integer grid alignment (e.g., Z from -0.5 to
    0.5). We detect the grid origin from minimum vertex coordinates and use
    floor(coord - grid_min) to snap correctly.
    """
    vertices = []
    face_lines = []
    current_material = None
    mtl_ref = None

    # First pass: collect vertices, faces, materials
    with open(filepath) as f:
        for line in f:
            line = line.strip()
            if line.startswith("mtllib "):
                mtl_ref = line.split(None, 1)[1]
            elif line.startswith("v "):
                parts = line.split()
                vertices.append((float(parts[1]), float(parts[2]), float(parts[3])))
            elif line.startswith("usemtl "):
                current_material = line.split()[1]
            elif line.startswith("f "):
                face_lines.append((line, current_material))

    if not vertices:
        return {}, mtl_ref

    # Detect grid origin from minimum vertex coordinates
    grid_min = (
        min(v[0] for v in vertices),
        min(v[1] for v in vertices),
        min(v[2] for v in vertices),
    )

    # Second pass: process faces
    voxel_materials = {}

    for line, material in face_lines:
        if material is None:
            continue
        parts = line.split()[1:]
        face_verts = []
        for p in parts:
            vi = int(p.split("//")[0]) - 1
            face_verts.append(vertices[vi])

        n = len(face_verts)
        if n < 3:
            continue

        # Face center
        cx = sum(v[0] for v in face_verts) / n
        cy = sum(v[1] for v in face_verts) / n
        cz = sum(v[2] for v in face_verts) / n

        # Cross product for face normal
        v0, v1, v2 = face_verts[0], face_verts[1], face_verts[2]
        e1 = (v1[0] - v0[0], v1[1] - v0[1], v1[2] - v0[2])
        e2 = (v2[0] - v0[0], v2[1] - v0[1], v2[2] - v0[2])
        fnx = e1[1] * e2[2] - e1[2] * e2[1]
        fny = e1[2] * e2[0] - e1[0] * e2[2]
        fnz = e1[0] * e2[1] - e1[1] * e2[0]
        length = math.sqrt(fnx * fnx + fny * fny + fnz * fnz)
        if length < 1e-9:
            continue
        fnx, fny, fnz = fnx / length, fny / length, fnz / length

        # Push center inward (opposite to normal) to land inside the cube
        ix = cx - 0.4 * fnx
        iy = cy - 0.4 * fny
        iz = cz - 0.4 * fnz

        # Snap to voxel grid using grid_min as the origin.
        # A voxel at grid index (i,j,k) spans from grid_min + (i,j,k)
        # to grid_min + (i+1, j+1, k+1).
        vx = int(math.floor(ix - grid_min[0]))
        vy = int(math.floor(iy - grid_min[1]))
        vz = int(math.floor(iz - grid_min[2]))

        if (vx, vy, vz) not in voxel_materials:
            voxel_materials[(vx, vy, vz)] = material

    return voxel_materials, mtl_ref, grid_min


def build_palette_texture(unique_colors):
    """
    Build a palette texture from unique RGBA color tuples.
    Returns (Image, {color_tuple: (px, py)}, texture_size).
    """
    n = len(unique_colors)
    tex_size = 16
    while tex_size * tex_size < n:
        tex_size *= 2

    img = Image.new("RGBA", (tex_size, tex_size), (0, 0, 0, 0))
    color_to_uv = {}

    for i, color in enumerate(unique_colors):
        px = i % tex_size
        py = i // tex_size
        img.putpixel((px, py), color)
        color_to_uv[color] = (px, py)

    return img, color_to_uv, tex_size


def generate_geo_json(voxels, material_colors, color_to_uv, tex_size, mask_name, grid_min):
    """Generate Bedrock geometry JSON with per-face UV mapping."""
    all_pos = list(voxels.keys())
    if not all_pos:
        return None

    # Voxel grid bounding box (grid indices; each voxel is 1x1x1)
    min_i = min(p[0] for p in all_pos)
    max_i = max(p[0] for p in all_pos) + 1
    min_j = min(p[1] for p in all_pos)
    max_j = max(p[1] for p in all_pos) + 1

    grid_width = max_i - min_i
    grid_height = max_j - min_j

    # Uniform scale to fit within target bounds
    scale = min(TARGET_WIDTH / grid_width, TARGET_HEIGHT / grid_height)

    # Centering: horizontal center of grid -> X=0 in Bedrock
    x_center_grid = (min_i + max_i) / 2.0

    # Fallback color (magenta, easy to spot errors)
    fallback_color = (255, 0, 255, 255)

    cubes = []
    for (vi, vj, vk), material in sorted(voxels.items()):
        color = material_colors.get(material, fallback_color)
        uv_pos = color_to_uv.get(color)
        if uv_pos is None:
            continue

        px, py = uv_pos

        # Transform grid indices -> Bedrock model coords
        geo_x = round((vi - x_center_grid) * scale, 4)
        geo_y = round((vj - min_j) * scale + Y_BOTTOM, 4)
        geo_z = round(Z_FRONT, 4)
        cube_size = round(scale, 4)

        face_uv = {"uv": [px, py], "uv_size": [1, 1]}
        cube = {
            "origin": [geo_x, geo_y, geo_z],
            "size": [cube_size, cube_size, cube_size],
            "uv": {
                "north": face_uv.copy(),
                "south": face_uv.copy(),
                "east": face_uv.copy(),
                "west": face_uv.copy(),
                "up": face_uv.copy(),
                "down": face_uv.copy(),
            },
        }
        cubes.append(cube)

    geo = {
        "format_version": "1.16.0",
        "minecraft:geometry": [
            {
                "description": {
                    "identifier": f"geometry.nuicraft.armor.{mask_name}",
                    "texture_width": tex_size,
                    "texture_height": tex_size,
                    "visible_bounds_width": 4,
                    "visible_bounds_height": 4.5,
                    "visible_bounds_offset": [0, 1.75, 0],
                },
                "bones": [
                    {
                        "name": "armorHead",
                        "pivot": [0, 24, 0],
                        "cubes": cubes,
                    }
                ],
            }
        ],
    }
    return geo


def convert_mask(mask_name):
    """Convert a single mask from OBJ/MTL to geo.json + texture PNG."""
    obj_path = MODELS_DIR / f"{mask_name}.obj"
    if not obj_path.exists():
        print(f"  SKIP {mask_name}: {obj_path} not found")
        return False

    print(f"\n--- Converting: {mask_name} ---")

    # Parse OBJ (also gets the mtllib reference and grid origin)
    voxels, mtl_ref, grid_min = parse_obj(obj_path)
    print(f"  Voxels: {len(voxels)}")

    # Find MTL file (use mtllib reference from OBJ, fallback to mask_name.mtl)
    mtl_path = MODELS_DIR / (mtl_ref or f"{mask_name}.mtl")
    if not mtl_path.exists():
        mtl_path = MODELS_DIR / f"{mask_name}.mtl"
    if not mtl_path.exists():
        print(f"  ERROR: No MTL file found for {mask_name}")
        return False

    print(f"  MTL: {mtl_path.name}")
    material_colors = parse_mtl(mtl_path)
    print(f"  Materials defined: {len(material_colors)}")

    # Collect unique colors used by voxels
    used_colors = set()
    for mat in voxels.values():
        if mat in material_colors:
            used_colors.add(material_colors[mat])
    unique_colors = sorted(used_colors)
    print(f"  Unique colors used: {len(unique_colors)}")

    if not unique_colors:
        print(f"  ERROR: No colors found")
        return False

    # Build palette texture
    tex_img, color_to_uv, tex_size = build_palette_texture(unique_colors)

    # Generate geo.json
    geo = generate_geo_json(voxels, material_colors, color_to_uv, tex_size, mask_name, grid_min)
    if geo is None:
        print(f"  ERROR: No cubes generated")
        return False

    n_cubes = len(geo["minecraft:geometry"][0]["bones"][0]["cubes"])
    print(f"  Output cubes: {n_cubes}")
    print(f"  Texture: {tex_size}x{tex_size}")

    # Write geo.json
    geo_path = GEO_OUT_DIR / f"{mask_name}.geo.json"
    with open(geo_path, "w") as f:
        json.dump(geo, f)
    print(f"  Wrote: {geo_path.relative_to(PROJECT_ROOT)}")

    # Write texture PNG
    tex_path = TEX_OUT_DIR / f"mask_mata_{mask_name}.png"
    tex_img.save(tex_path, "PNG")
    print(f"  Wrote: {tex_path.relative_to(PROJECT_ROOT)}")

    return True


def main():
    # Determine which masks to convert
    if len(sys.argv) > 1:
        mask_names = sys.argv[1:]
    else:
        # All masks that have a corresponding geo.json (i.e., are used in the mod)
        # Strip .geo from stem since files are named like "akaku.geo.json"
        mask_names = sorted(
            f.name.replace(".geo.json", "")
            for f in GEO_OUT_DIR.glob("*.geo.json")
        )

    if not mask_names:
        # Fallback: all OBJ files (exclude .geo suffix)
        mask_names = sorted(f.stem for f in MODELS_DIR.glob("*.obj"))

    print(f"Masks to convert: {', '.join(mask_names)}")

    success = 0
    for name in mask_names:
        if convert_mask(name):
            success += 1

    print(f"\n=== Done: {success}/{len(mask_names)} masks converted ===")


if __name__ == "__main__":
    main()
