#!/usr/bin/env python3
import json
from pathlib import Path

# Entity name mappings - removing underscores for consistency
entity_map = {
    'bionicle_qfn:agni_matoran': 'nuicraft:agni_matoran',
    'bionicle_qfn:ahkmou_matoran': 'nuicraft:ahkmou_matoran',
    'bionicle_qfn:boreas_matoran': 'nuicraft:boreas_matoran',
    'bionicle_qfn:bohrok_tahnok': 'nuicraft:bohrok_tahnok',
    'bionicle_qfn:gahlok': 'nuicraft:gahlok',
    'bionicle_qfn:guurahk': 'nuicraft:guurahk',
    'bionicle_qfn:hafu_matoran': 'nuicraft:hafu_matoran',
    'bionicle_qfn:hewkii_matoran': 'nuicraft:hewkii_matoran',
    'bionicle_qfn:jaller_matoran': 'nuicraft:jaller_matoran',
    'bionicle_qfn:kane_ra': 'nuicraft:kane_ra',
    'bionicle_qfn:kohrak': 'nuicraft:kohrak',
    'bionicle_qfn:kokkan_matoran': 'nuicraft:kokkan_matoran',
    'bionicle_qfn:kongu_matoran': 'nuicraft:kongu_matoran',
    'bionicle_qfn:kotu_matoran': 'nuicraft:kotu_matoran',
    'bionicle_qfn:kurahk': 'nuicraft:kurahk',
    'bionicle_qfn:lehvak': 'nuicraft:lehvak',
    'bionicle_qfn:lerahk': 'nuicraft:lerahk',
    'bionicle_qfn:macku_matoran': 'nuicraft:macku_matoran',
    'bionicle_qfn:makuta': 'nuicraft:makuta',
    'bionicle_qfn:matau_turaga': 'nuicraft:matau_turaga',
    'bionicle_qfn:matoro_matoran': 'nuicraft:matoro_matoran',
    'bionicle_qfn:muaka': 'nuicraft:muaka',
    'bionicle_qfn:nokama_turaga': 'nuicraft:nokama_turaga',
    'bionicle_qfn:nuhvok': 'nuicraft:nuhvok',
    'bionicle_qfn:nui_jaga': 'nuicraft:nui_jaga',
    'bionicle_qfn:nui_rama_green': 'nuicraft:nui_rama_green',
    'bionicle_qfn:nui_rama_orange': 'nuicraft:nui_rama_orange',
    'bionicle_qfn:nuju_turaga': 'nuicraft:nuju_turaga',
    'bionicle_qfn:nuparu_matoran': 'nuicraft:nuparu_matoran',
    'bionicle_qfn:okoth_matoran': 'nuicraft:okoth_matoran',
    'bionicle_qfn:onepu_matoran': 'nuicraft:onepu_matoran',
    'bionicle_qfn:onewa_turaga': 'nuicraft:onewa_turaga',
    'bionicle_qfn:pahrak': 'nuicraft:pahrak',
    'bionicle_qfn:pakastaa_matoran': 'nuicraft:pakastaa_matoran',
    'bionicle_qfn:panrahk': 'nuicraft:panrahk',
    'bionicle_qfn:rahkshi_yellow': 'nuicraft:rahkshi_yellow',
    'bionicle_qfn:spider_fikou': 'nuicraft:spider_fikou',
    'bionicle_qfn:tahnok': 'nuicraft:bohrok_tahnok',  # Alias for bohrok_tahnok
    'bionicle_qfn:tarakava_blue': 'nuicraft:tarakava_blue',
    'bionicle_qfn:tarakava_green': 'nuicraft:tarakava_green',
    'bionicle_qfn:tarakava_yellow': 'nuicraft:tarakava_yellow',
    'bionicle_qfn:turahk': 'nuicraft:turahk',
    'bionicle_qfn:tuuli_matoran': 'nuicraft:tuuli_matoran',
    'bionicle_qfn:vakama_turaga': 'nuicraft:vakama_turaga',
    'bionicle_qfn:vohon_matoran': 'nuicraft:vohon_matoran',
    'bionicle_qfn:vohrak': 'nuicraft:vohrak',
    'bionicle_qfn:whenua_turaga': 'nuicraft:whenua_turaga',
    'bionicle_qfn:zemya_matoran': 'nuicraft:zemya_matoran',
}

source_dir = Path('/Users/otoyume/Documents/GitHub/Nuicraft/src/main/resources/data/nuicraft/neoforge/biome_modifier')

count = 0
for json_file in sorted(source_dir.glob('*.json')):
    try:
        with open(json_file) as f:
            data = json.load(f)
        
        # Ensure type is neoforge:add_spawns
        if data.get('type') != 'neoforge:add_spawns':
            data['type'] = 'neoforge:add_spawns'
        
        # Fix biomes holder set - change forge:any to minecraft:any
        if 'biomes' in data and isinstance(data['biomes'], dict):
            if data['biomes'].get('type') == 'forge:any':
                data['biomes']['type'] = 'minecraft:any'
        
        # Update entity type using the mapping
        if 'spawners' in data and isinstance(data['spawners'], dict):
            if 'type' in data['spawners']:
                old_type = data['spawners']['type']
                if old_type in entity_map:
                    data['spawners']['type'] = entity_map[old_type]
                else:
                    # Try replacing just the namespace if not in explicit map
                    if old_type.startswith('bionicle_qfn:'):
                        data['spawners']['type'] = old_type.replace('bionicle_qfn:', 'nuicraft:')
        
        # Write back
        with open(json_file, 'w') as f:
            json.dump(data, f, indent=2)
        
        count += 1
        print(f"Fixed {json_file.name}")
    except Exception as e:
        print(f"Error processing {json_file.name}: {e}")

print(f"\nTotal fixed: {count}")
