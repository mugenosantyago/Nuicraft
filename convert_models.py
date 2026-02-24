"""
Converts .bbmodel files (Blockbench free format) to:
  - Bedrock .geo.json (AzureLib compatible, format_version 1.12.0)
  - Extracted .png textures renamed by village (ta, le, onu, ga, ko, po)

Color → village mapping:
  RED   → ta   (Ta-Koro, fire)
  GREEN → le   (Le-Koro, air)
  BLACK → onu  (Onu-Koro, earth)
  BLUE  → ga   (Ga-Koro, water)
  WHITE → ko   (Ko-Koro, ice)
  BROWN → po   (Po-Koro, stone)

Output:
  Geo files  → src/main/resources/assets/nuicraft/geo/entity/<mask>.geo.json
  Textures   → src/main/resources/assets/nuicraft/textures/entity/<mask>/<village>.png
"""

import json
import base64
import os
import re

# ── Paths ──────────────────────────────────────────────────────────────────────

ROOT = os.path.dirname(os.path.abspath(__file__))
MODELS_DIR = os.path.join(ROOT, "models", "bionicle_job_part1")
GEO_OUT = os.path.join(ROOT, "src", "main", "resources", "assets", "nuicraft", "geo", "entity")
TEX_OUT = os.path.join(ROOT, "src", "main", "resources", "assets", "nuicraft", "textures", "entity")

# ── Color → village name ───────────────────────────────────────────────────────

COLOR_MAP = {
    "RED":   "ta",
    "GREEN": "le",
    "BLACK": "onu",
    "BLUE":  "ga",
    "WHITE": "ko",
    "BROWN": "po",
}

# ── Coordinate conversion helpers (Blockbench free → Bedrock) ─────────────────
# Bedrock entity models mirror the X axis compared to Java / Blockbench free.
# Rules:
#   position/pivot : new_x = -old_x
#   cube origin    : new_x = -max(x1, x2)  (because min of negated is neg of max)
#   rotation       : [rx, -ry, -rz]
#   face order     : east ↔ west are swapped (due to X mirror)

FACE_SWAP = {"east": "west", "west": "east",
             "north": "north", "south": "south",
             "up": "up", "down": "down"}


def convert_pivot(origin):
    """Negate X for pivot / bone origin."""
    if not origin or len(origin) < 3:
        return [0.0, 0.0, 0.0]
    return [-origin[0], origin[1], origin[2]]


def convert_rotation(rotation):
    """Mirror rotation across X axis: [rx, -ry, -rz]."""
    if not rotation or len(rotation) < 3:
        return [0.0, 0.0, 0.0]
    return [rotation[0], -rotation[1], -rotation[2]]


def convert_cube(elem):
    """
    Convert a Blockbench cube element to a Bedrock cube dict.
    Returns None if the element has zero size.
    """
    from_pos = elem.get("from", [0, 0, 0])
    to_pos   = elem.get("to",   [0, 0, 0])
    rotation = elem.get("rotation", [0, 0, 0])
    pivot    = elem.get("origin", [0, 0, 0])
    inflate  = elem.get("inflate", 0)
    faces    = elem.get("faces", {})

    # Size (always positive)
    size_x = abs(to_pos[0] - from_pos[0])
    size_y = abs(to_pos[1] - from_pos[1])
    size_z = abs(to_pos[2] - from_pos[2])

    if size_x == 0 and size_y == 0 and size_z == 0:
        return None

    # Bedrock origin = [-max_x, min_y, min_z]
    origin_x = -max(from_pos[0], to_pos[0])
    origin_y =  min(from_pos[1], to_pos[1])
    origin_z =  min(from_pos[2], to_pos[2])

    cube = {
        "origin": [origin_x, origin_y, origin_z],
        "size":   [size_x,   size_y,   size_z],
    }

    if inflate:
        cube["inflate"] = inflate

    # Individual cube rotation (if any)
    if any(r != 0 for r in rotation):
        cube["pivot"]    = convert_pivot(pivot)
        cube["rotation"] = convert_rotation(rotation)

    # Per-face UV (swap east/west due to X mirror)
    if faces:
        uv_dict = {}
        for bb_face, face_data in faces.items():
            bedrock_face = FACE_SWAP.get(bb_face, bb_face)
            uv = face_data.get("uv", [0, 0, 0, 0])
            u1, v1, u2, v2 = uv[0], uv[1], uv[2], uv[3]
            # uv_size can be negative (signals texture flip in Bedrock too)
            uv_dict[bedrock_face] = {
                "uv":      [min(u1, u2), min(v1, v2)],
                "uv_size": [u2 - u1, v2 - v1],
            }
        cube["uv"] = uv_dict

    return cube


# ── Bone / outliner conversion ─────────────────────────────────────────────────

def build_bones(outliner, elements_by_uuid, groups_by_uuid, parent_name=None):
    """
    Recursively walk the outliner tree and produce a flat list of Bedrock bones.
    """
    bones = []
    for node in outliner:
        if isinstance(node, str):
            # Bare element UUID at the top level — shouldn't normally happen
            # but handle gracefully by skipping (it belongs to a parent bone)
            continue

        uuid = node.get("uuid", "")
        group = groups_by_uuid.get(uuid, {})

        raw_name = group.get("name") or ""
        bone_name = raw_name.strip() if raw_name.strip() else f"bone_{uuid[:8]}"

        raw_origin   = group.get("origin",   [0, 0, 0])
        raw_rotation = group.get("rotation", [0, 0, 0])

        bone = {"name": bone_name, "pivot": convert_pivot(raw_origin)}
        if parent_name:
            bone["parent"] = parent_name

        rot = convert_rotation(raw_rotation)
        if any(r != 0 for r in rot):
            bone["rotation"] = rot

        # Collect cubes that are direct element-UUID children of this bone
        cubes = []
        child_groups = []
        for child in node.get("children", []):
            if isinstance(child, str):
                elem = elements_by_uuid.get(child)
                if elem:
                    c = convert_cube(elem)
                    if c:
                        cubes.append(c)
            else:
                child_groups.append(child)

        if cubes:
            bone["cubes"] = cubes

        bones.append(bone)

        # Recurse into child bones
        bones.extend(build_bones(child_groups, elements_by_uuid, groups_by_uuid, bone_name))

    return bones


# ── Main conversion function ───────────────────────────────────────────────────

def convert_bbmodel(bbmodel_path, entity_name):
    """
    Read a .bbmodel file and return:
      (geo_dict, [(village_name, png_bytes), ...])
    """
    with open(bbmodel_path, encoding="utf-8") as f:
        data = json.load(f)

    resolution = data.get("resolution", {"width": 64, "height": 64})
    tex_w = resolution["width"]
    tex_h = resolution["height"]

    elements_by_uuid = {e["uuid"]: e for e in data.get("elements", [])}
    groups_by_uuid   = {g["uuid"]: g for g in data.get("groups",   [])}

    bones = build_bones(data.get("outliner", []), elements_by_uuid, groups_by_uuid)

    # Ensure there is always at least a root bone
    if not bones:
        bones = [{"name": "root", "pivot": [0.0, 0.0, 0.0]}]

    geo = {
        "format_version": "1.12.0",
        "minecraft:geometry": [{
            "description": {
                "identifier":             f"geometry.{entity_name}",
                "texture_width":           tex_w,
                "texture_height":          tex_h,
                "visible_bounds_width":    2,
                "visible_bounds_height":   2.5,
                "visible_bounds_offset":  [0, 1.25, 0],
            },
            "bones": bones,
        }],
    }

    # Extract textures
    textures = []
    used_villages = set()
    all_villages = list(COLOR_MAP.values())  # ordered: ta, le, onu, ga, ko, po

    for tex in data.get("textures", []):
        name = tex.get("name", "")          # e.g. "RED_KAUKAU_matoran.png"
        source = tex.get("source", "")      # "data:image/png;base64,..."

        # Determine village from colour prefix
        color_match = re.match(r"^(RED|GREEN|BLACK|BLUE|WHITE|BROWN)_", name, re.IGNORECASE)
        if color_match:
            color   = color_match.group(1).upper()
            village = COLOR_MAP.get(color, color.lower())

            # If this village slot is already taken, the file is mislabeled —
            # assign it to the next missing village in the standard order.
            if village in used_villages:
                missing = [v for v in all_villages if v not in used_villages]
                if missing:
                    correct = missing[0]
                    print(f"  NOTE: '{name}' is mislabeled ('{village}' already used) — treating as '{correct}'")
                    village = correct
                else:
                    print(f"  WARNING: '{name}' is a true duplicate with no missing slot, skipping.")
                    continue
        else:
            # Single texture (e.g. RAU_turaga.png) — no colour prefix, turaga variant
            village = "default"

        # Decode base64 PNG
        if "base64," in source:
            b64 = source.split("base64,", 1)[1]
            try:
                png_bytes = base64.b64decode(b64)
            except Exception as e:
                print(f"  WARNING: could not decode texture '{name}': {e}")
                continue
        else:
            print(f"  WARNING: texture '{name}' has no base64 source, skipping.")
            continue

        used_villages.add(village)
        textures.append((village, png_bytes, name))

    return geo, textures


# ── Run ────────────────────────────────────────────────────────────────────────

def main():
    os.makedirs(GEO_OUT, exist_ok=True)

    issues = []

    for folder_name in sorted(os.listdir(MODELS_DIR)):
        folder_path = os.path.join(MODELS_DIR, folder_name)
        if not os.path.isdir(folder_path):
            continue

        bbmodel_file = None
        for f in os.listdir(folder_path):
            if f.endswith(".bbmodel"):
                bbmodel_file = os.path.join(folder_path, f)
                break

        if not bbmodel_file:
            continue

        # Entity name = lowercase folder name  (e.g. KAUKAU_matoran → kaukau_matoran)
        entity_name = folder_name.lower()

        print(f"\n{'─'*60}")
        print(f"Processing: {folder_name}  →  {entity_name}")

        geo, textures = convert_bbmodel(bbmodel_file, entity_name)

        # Write geo.json
        geo_path = os.path.join(GEO_OUT, f"{entity_name}.geo.json")
        with open(geo_path, "w", encoding="utf-8") as f:
            json.dump(geo, f, indent="\t")
        print(f"  ✓ geo.json  → geo/entity/{entity_name}.geo.json")

        # Write textures
        tex_dir = os.path.join(TEX_OUT, entity_name)
        os.makedirs(tex_dir, exist_ok=True)

        villages_written = []
        for village, png_bytes, original_name in textures:
            out_path = os.path.join(tex_dir, f"{village}.png")
            with open(out_path, "wb") as f:
                f.write(png_bytes)
            villages_written.append(village)
            print(f"  ✓ texture   → textures/entity/{entity_name}/{village}.png  (was: {original_name})")

        # Turaga have a single texture per model (no village colour variants)
        is_turaga = "turaga" in entity_name
        if not is_turaga:
            missing = set(COLOR_MAP.values()) - set(villages_written)
            if missing:
                msg = f"  ⚠ {entity_name}: missing village textures: {', '.join(sorted(missing))}"
                print(msg)
                issues.append(msg)

    print(f"\n{'═'*60}")
    print("Done!")
    if issues:
        print("\nIssues found (need attention from the artists):")
        for issue in issues:
            print(issue)
    else:
        print("No issues found.")


if __name__ == "__main__":
    main()
