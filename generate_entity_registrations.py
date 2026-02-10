#!/usr/bin/env python3

ENTITIES = [
    # Bohrok (Monster)
    ('bohrok_tahnok', 'BohrokTahnok', 'MONSTER', 0.8, 0.8),
    ('gahlok', 'Gahlok', 'MONSTER', 0.8, 0.8),
    ('kohrak', 'Kohrak', 'MONSTER', 0.8, 0.8),
    ('lehvak', 'Lehvak', 'MONSTER', 0.8, 0.8),
    ('nuhvok', 'Nuhvok', 'MONSTER', 0.8, 0.8),
    ('pahrak', 'Pahrak', 'MONSTER', 0.8, 0.8),
    # Rahkshi (Monster)
    ('guurahk', 'Guurahk', 'MONSTER', 0.6, 2.5),
    ('kurahk', 'Kurahk', 'MONSTER', 0.6, 2.5),
    ('lerahk', 'Lerahk', 'MONSTER', 0.6, 2.5),
    ('panrahk', 'Panrahk', 'MONSTER', 0.6, 2.5),
    ('rahkshi_yellow', 'RahkshiYellow', 'MONSTER', 0.6, 2.5),
    ('turahk', 'Turahk', 'MONSTER', 0.6, 2.5),
    ('vohrak', 'Vohrak', 'MONSTER', 0.6, 2.5),
    # Matoran (Animal)
    ('agni_matoran', 'AgniMatoran', 'CREATURE', 0.4, 1.3),
    ('ahkmou_matoran', 'AkhmouMatoran', 'CREATURE', 0.4, 1.3),
    ('boreas_matoran', 'BoreasMatoran', 'CREATURE', 0.4, 1.3),
    ('hafu_matoran', 'HafuMatoran', 'CREATURE', 0.4, 1.3),
    ('hewkii_matoran', 'HewkiiMatoran', 'CREATURE', 0.4, 1.3),
    ('jaller_matoran', 'JallerMatoran', 'CREATURE', 0.4, 1.3),
    ('kokkan_matoran', 'KokkanMatoran', 'CREATURE', 0.4, 1.3),
    ('kongu_matoran', 'KonguMatoran', 'CREATURE', 0.4, 1.3),
    ('kotu_matoran', 'KotuMatoran', 'CREATURE', 0.4, 1.3),
    ('macku_matoran', 'MackuMatoran', 'CREATURE', 0.4, 1.3),
    ('matoro_matoran', 'MatoroMatoran', 'CREATURE', 0.4, 1.3),
    ('nuparu_matoran', 'NuparuMatoran', 'CREATURE', 0.4, 1.3),
    ('okoth_matoran', 'OkothMatoran', 'CREATURE', 0.4, 1.3),
    ('onepu_matoran', 'OnepuMatoran', 'CREATURE', 0.4, 1.3),
    ('pakastaa_matoran', 'PakastaaMatoran', 'CREATURE', 0.4, 1.3),
    ('tuuli_matoran', 'TuuliMatoran', 'CREATURE', 0.4, 1.3),
    ('vohon_matoran', 'VohonMatoran', 'CREATURE', 0.4, 1.3),
    ('zemya_matoran', 'ZemyaMatoran', 'CREATURE', 0.4, 1.3),
    # Turaga (Animal)
    ('matau_turaga', 'MatauTuraga', 'CREATURE', 0.5, 1.8),
    ('nokama_turaga', 'NokamaTuraga', 'CREATURE', 0.5, 1.8),
    ('nuju_turaga', 'NujuTuraga', 'CREATURE', 0.5, 1.8),
    ('onewa_turaga', 'OnewaaTuraga', 'CREATURE', 0.5, 1.8),
    ('vakama_turaga', 'VakamaTuraga', 'CREATURE', 0.5, 1.8),
    ('whenua_turaga', 'WhenauTuraga', 'CREATURE', 0.5, 1.8),
    # Rahi
    ('kane_ra', 'KaneRa', 'CREATURE', 1.2, 1.0),
    ('muaka', 'Muaka', 'CREATURE', 1.2, 1.0),
    ('nui_rama_green', 'NuiRamaGreen', 'CREATURE', 1.0, 0.9),
    ('nui_rama_orange', 'NuiRamaOrange', 'CREATURE', 1.0, 0.9),
    ('spider_fikou', 'SpiderFikou', 'MONSTER', 0.7, 0.5),
    ('tarakava_blue', 'TarakavaBlue', 'CREATURE', 1.0, 1.2),
    ('tarakava_green', 'TarakavaGreen', 'CREATURE', 1.0, 1.2),
    ('tarakava_yellow', 'TarakavaYellow', 'CREATURE', 1.0, 1.2),
    ('makuta', 'Makuta', 'MONSTER', 1.0, 2.5),
]

# Existing entities we should skip
SKIP = ['mahi', 'fikou', 'hoi', 'kofo_jaga', 'nui_jaga']

print("// Add these to NuiCraftEntityTypes.java:\n")

for entity_id, class_name, category, width, height in ENTITIES:
    if entity_id in SKIP:
        continue
    
    constant_name = entity_id.upper().replace("_", "")
    
    print(f'''    public static final DeferredHolder<EntityType<?>, EntityType<Entity{class_name}>> {constant_name} =
            ENTITY_TYPES.register("{entity_id}", () -> EntityType.Builder.of(Entity{class_name}::new, MobCategory.{category})
                    .sized({width}F, {height}F)
                    .clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "{entity_id}"))));
    ''')

print(f"\nTotal entities to add: {len([e for e in ENTITIES if e[0] not in SKIP])}")
