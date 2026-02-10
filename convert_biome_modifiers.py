#!/usr/bin/env python3
import json
from pathlib import Path

source_dir = Path('/Users/otoyume/Documents/GitHub/Nuicraft/extracted_1203_biome_modifiers')
target_dir = Path('/Users/otoyume/Documents/GitHub/Nuicraft/src/main/resources/data/nuicraft/neoforge/biome_modifier')

target_dir.mkdir(parents=True, exist_ok=True)

# Map of old entity names to new ones
entity_map = {
    'bionicle_qfn:': 'nuicraft:',
}

count = 0
for json_file in source_dir.glob('*.json'):
    try:
        with open(json_file) as f:
            data = json.load(f)
        
        # Change type from forge:add_spawns to neoforge:add_spawns
        if data.get('type') == 'forge:add_spawns':
            data['type'] = 'neoforge:add_spawns'
        
        # Update entity type from bionicle_qfn to nuicraft
        if 'spawners' in data and isinstance(data['spawners'], dict):
            if 'type' in data['spawners']:
                data['spawners']['type'] = data['spawners']['type'].replace('bionicle_qfn:', 'nuicraft:')
        
        # Write to target
        with open(target_dir / json_file.name, 'w') as f:
            json.dump(data, f, indent=2)
        
        count += 1
        print(f"Converted {json_file.name}")
    except Exception as e:
        print(f"Error processing {json_file.name}: {e}")

print(f"\nTotal converted: {count}")
