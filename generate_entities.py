#!/usr/bin/env python3
import os
import json
from pathlib import Path

# List of all entities to create
ENTITIES = [
    # Bohrok
    ('bohrok_tahnok', 'BohrokTahnok', 'Monster'),
    ('gahlok', 'Gahlok', 'Monster'),
    ('kohrak', 'Kohrak', 'Monster'),
    ('lehvak', 'Lehvak', 'Monster'),
    ('nuhvok', 'Nuhvok', 'Monster'),
    ('pahrak', 'Pahrak', 'Monster'),
    # Rahkshi
    ('guurahk', 'Guurahk', 'Monster'),
    ('kurahk', 'Kurahk', 'Monster'),
    ('lerahk', 'Lerahk', 'Monster'),
    ('panrahk', 'Panrahk', 'Monster'),
    ('rahkshi_yellow', 'RahkshiYellow', 'Monster'),
    ('turahk', 'Turahk', 'Monster'),
    ('vohrak', 'Vohrak', 'Monster'),
    # Matoran
    ('agni_matoran', 'AgniMatoran', 'Animal'),
    ('ahkmou_matoran', 'AkhmouMatoran', 'Animal'),
    ('boreas_matoran', 'BoreasMatoran', 'Animal'),
    ('hafu_matoran', 'HafuMatoran', 'Animal'),
    ('hewkii_matoran', 'HewkiiMatoran', 'Animal'),
    ('jaller_matoran', 'JallerMatoran', 'Animal'),
    ('kokkan_matoran', 'KokkanMatoran', 'Animal'),
    ('kongu_matoran', 'KonguMatoran', 'Animal'),
    ('kotu_matoran', 'KotuMatoran', 'Animal'),
    ('macku_matoran', 'MackuMatoran', 'Animal'),
    ('matoro_matoran', 'MatoroMatoran', 'Animal'),
    ('nuparu_matoran', 'NuparuMatoran', 'Animal'),
    ('okoth_matoran', 'OkothMatoran', 'Animal'),
    ('onepu_matoran', 'OnepuMatoran', 'Animal'),
    ('pakastaa_matoran', 'PakastaaMatoran', 'Animal'),
    ('tuuli_matoran', 'TuuliMatoran', 'Animal'),
    ('vohon_matoran', 'VohonMatoran', 'Animal'),
    ('zemya_matoran', 'ZemyaMatoran', 'Animal'),
    # Turaga
    ('matau_turaga', 'MatauTuraga', 'Animal'),
    ('nokama_turaga', 'NokamaTuraga', 'Animal'),
    ('nuju_turaga', 'NujuTuraga', 'Animal'),
    ('onewa_turaga', 'OnewaaTuraga', 'Animal'),
    ('vakama_turaga', 'VakamaTuraga', 'Animal'),
    ('whenua_turaga', 'WhenauTuraga', 'Animal'),
    # Rahi
    ('kane_ra', 'KaneRa', 'Animal'),
    ('muaka', 'Muaka', 'Animal'),
    ('nui_jaga', 'NuiJaga', 'Monster'),
    ('nui_rama_green', 'NuiRamaGreen', 'Animal'),
    ('nui_rama_orange', 'NuiRamaOrange', 'Animal'),
    ('spider_fikou', 'SpiderFikou', 'Monster'),
    ('tarakava_blue', 'TarakavaBlue', 'Animal'),
    ('tarakava_green', 'TarakavaGreen', 'Animal'),
    ('tarakava_yellow', 'TarakavaYellow', 'Animal'),
    ('makuta', 'Makuta', 'Monster'),
]

def create_entity_class(entity_id, class_name, base_class):
    """Generate Java entity class"""
    base_extends = 'Monster' if base_class == 'Monster' else 'Animal'
    static_register = f'NuiCraftEntityTypes.{entity_id.upper().replace("_", "")}.get()' if base_class == 'Monster' else f'NuiCraftEntityTypes.{entity_id.upper().replace("_", "")}.get()'
    
    java_code = f'''package eastonium.nuicraft.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.{base_class.lower()}.{base_extends};
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Entity{class_name} extends {base_extends} {{
    
    public Entity{class_name}(EntityType<? extends Entity{class_name}> type, Level level) {{
        super(type, level);
    }}

    @Override
    protected void registerGoals() {{
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, false));
    }}

    public static AttributeSupplier.Builder createAttributes() {{
        return {base_extends}.create{base_extends}Attributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);
    }}
}}
'''
    return java_code

# Create output directory
entity_dir = Path('/Users/otoyume/Documents/GitHub/Nuicraft/src/main/java/eastonium/nuicraft/entity')
entity_dir.mkdir(parents=True, exist_ok=True)

# Generate entity files
for entity_id, class_name, base_class in ENTITIES:
    # Skip if already exists (Mahi, Fikou, etc.)
    filename = f'Entity{class_name}.java'
    filepath = entity_dir / filename
    
    if filepath.exists():
        print(f"Skipping {filename} (already exists)")
        continue
    
    code = create_entity_class(entity_id, class_name, base_class)
    filepath.write_text(code)
    print(f"Created {filename}")

print(f"\nGenerated {len([e for e in ENTITIES if not (entity_dir / f'Entity{e[1]}.java').exists()])} entity classes")
