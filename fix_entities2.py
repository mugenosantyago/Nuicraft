#!/usr/bin/env python3
from pathlib import Path

def create_animal_entity(entity_id, class_name):
    """Generate Java entity class for Animal-based entities"""
    
    java_code = f'''package eastonium.nuicraft.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Entity{class_name} extends Animal {{
    
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
    }}

    public static AttributeSupplier.Builder createAttributes() {{
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }}

    @Override
    public boolean isFood(ItemStack stack) {{
        return false;
    }}

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {{
        return new Entity{class_name}(eastonium.nuicraft.core.NuiCraftEntityTypes.{entity_id.upper().replace('_', '')}.get(), level);
    }}
}}
'''
    return java_code

# All animal entities
ANIMALS = [
    ('agni_matoran', 'AgniMatoran'),
    ('ahkmou_matoran', 'AkhmouMatoran'),
    ('boreas_matoran', 'BoreasMatoran'),
    ('hafu_matoran', 'HafuMatoran'),
    ('hewkii_matoran', 'HewkiiMatoran'),
    ('jaller_matoran', 'JallerMatoran'),
    ('kokkan_matoran', 'KokkanMatoran'),
    ('kongu_matoran', 'KonguMatoran'),
    ('kotu_matoran', 'KotuMatoran'),
    ('macku_matoran', 'MackuMatoran'),
    ('matoro_matoran', 'MatoroMatoran'),
    ('nuparu_matoran', 'NuparuMatoran'),
    ('okoth_matoran', 'OkothMatoran'),
    ('onepu_matoran', 'OnepuMatoran'),
    ('pakastaa_matoran', 'PakastaaMatoran'),
    ('tuuli_matoran', 'TuuliMatoran'),
    ('vohon_matoran', 'VohonMatoran'),
    ('zemya_matoran', 'ZemyaMatoran'),
    ('matau_turaga', 'MatauTuraga'),
    ('nokama_turaga', 'NokamaTuraga'),
    ('nuju_turaga', 'NujuTuraga'),
    ('onewa_turaga', 'OnewaaTuraga'),
    ('vakama_turaga', 'VakamaTuraga'),
    ('whenua_turaga', 'WhenauTuraga'),
    ('kane_ra', 'KaneRa'),
    ('muaka', 'Muaka'),
    ('nui_rama_green', 'NuiRamaGreen'),
    ('nui_rama_orange', 'NuiRamaOrange'),
    ('tarakava_blue', 'TarakavaBlue'),
    ('tarakava_green', 'TarakavaGreen'),
    ('tarakava_yellow', 'TarakavaYellow'),
]

entity_dir = Path('/Users/otoyume/Documents/GitHub/Nuicraft/src/main/java/eastonium/nuicraft/entity')

# Generate animal entities
for entity_id, class_name in ANIMALS:
    filepath = entity_dir / f'Entity{class_name}.java'
    code = create_animal_entity(entity_id, class_name)
    filepath.write_text(code)
    print(f"Fixed {class_name}")

print(f"\nFixed {len(ANIMALS)} animal entities")
