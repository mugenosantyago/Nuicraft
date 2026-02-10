package eastonium.nuicraft.core;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.entity.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NuiCraftEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = 
            DeferredRegister.create(Registries.ENTITY_TYPE, NuiCraft.MODID);

    // Creature mobs
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMahi>> MAHI =
            ENTITY_TYPES.register("mahi", () -> EntityType.Builder.of(EntityMahi::new, MobCategory.CREATURE)
                    .sized(0.9F, 1.4F)
                    .clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "mahi"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityFikou>> FIKOU =
            ENTITY_TYPES.register("fikou", () -> EntityType.Builder.of(EntityFikou::new, MobCategory.CREATURE)
                    .sized(0.7F, 0.5F)
                    .clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "fikou"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityHoi>> HOI =
            ENTITY_TYPES.register("hoi", () -> EntityType.Builder.of(EntityHoi::new, MobCategory.CREATURE)
                    .sized(0.6F, 0.4F)
                    .clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "hoi"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityKofoJaga>> KOFO_JAGA =
            ENTITY_TYPES.register("kofo_jaga", () -> EntityType.Builder.of(EntityKofoJaga::new, MobCategory.CREATURE)
                    .sized(1.0F, 0.8F)
                    .clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "kofo_jaga"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityNuiJaga>> NUI_JAGA =
            ENTITY_TYPES.register("nui_jaga", () -> EntityType.Builder.of(EntityNuiJaga::new, MobCategory.MONSTER)
                    .sized(1.4F, 1.2F)
                    .clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "nui_jaga"))));
    
    // Batch 1: Crabs, Sheep, Rabbits
    public static final DeferredHolder<EntityType<?>, EntityType<EntityUssalCrab>> USSAL_CRAB =
            ENTITY_TYPES.register("ussal_crab", () -> EntityType.Builder.of(EntityUssalCrab::new, MobCategory.CREATURE)
                    .sized(0.8F, 0.7F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "ussal_crab"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntitySheepRahi>> SHEEP_RAHI =
            ENTITY_TYPES.register("sheep_rahi", () -> EntityType.Builder.of(EntitySheepRahi::new, MobCategory.CREATURE)
                    .sized(0.9F, 1.3F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "sheep_rahi"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityRabbitRahi>> RABBIT_RAHI =
            ENTITY_TYPES.register("rabbit_rahi", () -> EntityType.Builder.of(EntityRabbitRahi::new, MobCategory.CREATURE)
                    .sized(0.4F, 0.5F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "rabbit_rahi"))));
    
    // Batch 2: Large predators
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMuaka>> MUAKA =
            ENTITY_TYPES.register("muaka", () -> EntityType.Builder.of(EntityMuaka::new, MobCategory.CREATURE)
                    .sized(1.2F, 1.1F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "muaka"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityKaneRa>> KANE_RA =
            ENTITY_TYPES.register("kane_ra", () -> EntityType.Builder.of(EntityKaneRa::new, MobCategory.CREATURE)
                    .sized(1.5F, 1.3F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "kane_ra"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityPokawi>> POKAWI =
            ENTITY_TYPES.register("pokawi", () -> EntityType.Builder.of(EntityPokawi::new, MobCategory.CREATURE)
                    .sized(0.8F, 0.9F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "pokawi"))));
    
    // Batch 3: Aquatic
    public static final DeferredHolder<EntityType<?>, EntityType<EntityTurtleRahi>> TURTLE_RAHI =
            ENTITY_TYPES.register("turtle_rahi", () -> EntityType.Builder.of(EntityTurtleRahi::new, MobCategory.CREATURE)
                    .sized(0.9F, 0.7F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "turtle_rahi"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityHikaki>> HIKAKI =
            ENTITY_TYPES.register("hikaki", () -> EntityType.Builder.of(EntityHikaki::new, MobCategory.CREATURE)
                    .sized(0.8F, 0.8F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "hikaki"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityShoreTurtle>> SHORE_TURTLE =
            ENTITY_TYPES.register("shore_turtle", () -> EntityType.Builder.of(EntityShoreTurtle::new, MobCategory.CREATURE)
                    .sized(1.0F, 0.8F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "shore_turtle"))));
    
    // Batch 4: Flying
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBirdRahi>> BIRD_RAHI =
            ENTITY_TYPES.register("bird_rahi", () -> EntityType.Builder.of(EntityBirdRahi::new, MobCategory.CREATURE)
                    .sized(0.6F, 0.8F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "bird_rahi"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBatRahi>> BAT_RAHI =
            ENTITY_TYPES.register("bat_rahi", () -> EntityType.Builder.of(EntityBatRahi::new, MobCategory.AMBIENT)
                    .sized(0.5F, 0.9F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "bat_rahi"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityHotoBug>> HOTO_BUG =
            ENTITY_TYPES.register("hoto_bug", () -> EntityType.Builder.of(EntityHotoBug::new, MobCategory.AMBIENT)
                    .sized(0.3F, 0.4F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "hoto_bug"))));
    
    // Batch 5: Remaining creatures
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMatoran>> MATORAN =
            ENTITY_TYPES.register("matoran", () -> EntityType.Builder.of(EntityMatoran::new, MobCategory.CREATURE)
                    .sized(0.4F, 1.0F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "matoran"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBohrok>> BOHROK =
            ENTITY_TYPES.register("bohrok", () -> EntityType.Builder.of(EntityBohrok::new, MobCategory.MONSTER)
                    .sized(1.0F, 0.9F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "bohrok"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityTuraga>> TURAGA =
            ENTITY_TYPES.register("turaga", () -> EntityType.Builder.of(EntityTuraga::new, MobCategory.CREATURE)
                    .sized(0.45F, 1.1F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "turaga"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMukau>> MUKAU =
            ENTITY_TYPES.register("mukau", () -> EntityType.Builder.of(EntityMukau::new, MobCategory.CREATURE)
                    .sized(1.1F, 1.0F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "mukau"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityHapaka>> HAPAKA =
            ENTITY_TYPES.register("hapaka", () -> EntityType.Builder.of(EntityHapaka::new, MobCategory.CREATURE)
                    .sized(0.9F, 0.8F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "hapaka"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityBohrokVa>> BOHROK_VA =
            ENTITY_TYPES.register("bohrok_va", () -> EntityType.Builder.of(EntityBohrokVa::new, MobCategory.MONSTER)
                    .sized(0.8F, 0.7F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "bohrok_va"))));
    
    // Batch 5b: More creatures
    public static final DeferredHolder<EntityType<?>, EntityType<EntityNuiRama>> NUI_RAMA =
            ENTITY_TYPES.register("nui_rama", () -> EntityType.Builder.of(EntityNuiRama::new, MobCategory.CREATURE)
                    .sized(1.3F, 1.2F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "nui_rama"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityAshBear>> ASH_BEAR =
            ENTITY_TYPES.register("ash_bear", () -> EntityType.Builder.of(EntityAshBear::new, MobCategory.CREATURE)
                    .sized(1.4F, 1.3F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "ash_bear"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityRahaga>> RAHAGA =
            ENTITY_TYPES.register("rahaga", () -> EntityType.Builder.of(EntityRahaga::new, MobCategory.CREATURE)
                    .sized(0.5F, 1.0F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "rahaga"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityVako>> VAKO =
            ENTITY_TYPES.register("vako", () -> EntityType.Builder.of(EntityVako::new, MobCategory.CREATURE)
                    .sized(0.7F, 0.6F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "vako"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityKikanalo>> KIKANALO =
            ENTITY_TYPES.register("kikanalo", () -> EntityType.Builder.of(EntityKikanalo::new, MobCategory.CREATURE)
                    .sized(1.0F, 1.5F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "kikanalo"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityRahkshi>> RAHKSHI =
            ENTITY_TYPES.register("rahkshi", () -> EntityType.Builder.of(EntityRahkshi::new, MobCategory.MONSTER)
                    .sized(1.1F, 1.0F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "rahkshi"))));
    
    // Batch 5c: Final creatures
    public static final DeferredHolder<EntityType<?>, EntityType<EntityKraata>> KRAATA =
            ENTITY_TYPES.register("kraata", () -> EntityType.Builder.of(EntityKraata::new, MobCategory.CREATURE)
                    .sized(0.5F, 0.6F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "kraata"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityPiraka>> PIRAKA =
            ENTITY_TYPES.register("piraka", () -> EntityType.Builder.of(EntityPiraka::new, MobCategory.CREATURE)
                    .sized(0.8F, 1.0F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "piraka"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityDarkHunter>> DARK_HUNTER =
            ENTITY_TYPES.register("dark_hunter", () -> EntityType.Builder.of(EntityDarkHunter::new, MobCategory.CREATURE)
                    .sized(0.9F, 1.1F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "dark_hunter"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityFusa>> FUSA =
            ENTITY_TYPES.register("fusa", () -> EntityType.Builder.of(EntityFusa::new, MobCategory.CREATURE)
                    .sized(0.75F, 0.7F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "fusa"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntitySkrall>> SKRALL =
            ENTITY_TYPES.register("skrall", () -> EntityType.Builder.of(EntitySkrall::new, MobCategory.CREATURE)
                    .sized(0.85F, 1.0F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "skrall"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityZesk>> ZESK =
            ENTITY_TYPES.register("zesk", () -> EntityType.Builder.of(EntityZesk::new, MobCategory.CREATURE)
                    .sized(0.75F, 0.9F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "zesk"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityAgori>> AGORI =
            ENTITY_TYPES.register("agori", () -> EntityType.Builder.of(EntityAgori::new, MobCategory.CREATURE)
                    .sized(0.35F, 0.8F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "agori"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityFoxRahi>> FOX_RAHI =
            ENTITY_TYPES.register("fox_rahi", () -> EntityType.Builder.of(EntityFoxRahi::new, MobCategory.CREATURE)
                    .sized(0.6F, 0.7F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "fox_rahi"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityKavinika>> KAVINIKA =
            ENTITY_TYPES.register("kavinika", () -> EntityType.Builder.of(EntityKavinika::new, MobCategory.CREATURE)
                    .sized(0.4F, 0.5F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "kavinika"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityArchivesMole>> ARCHIVES_MOLE =
            ENTITY_TYPES.register("archives_mole", () -> EntityType.Builder.of(EntityArchivesMole::new, MobCategory.CREATURE)
                    .sized(0.3F, 0.4F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "archives_mole"))));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityTakeaShark>> TAKEA_SHARK =
            ENTITY_TYPES.register("takea_shark", () -> EntityType.Builder.of(EntityTakeaShark::new, MobCategory.CREATURE)
                    .sized(0.9F, 0.8F).clientTrackingRange(64)
                    .build(net.minecraft.resources.ResourceKey.create(Registries.ENTITY_TYPE, 
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "takea_shark"))));
    
    // Projectile entities
//    public static final DeferredHolder<EntityType<?>, EntityType<EntityKanoka>> KANOKA_DISC =
//            ENTITY_TYPES.register("kanoka_disc", () -> EntityType.Builder.<EntityKanoka>of(
//                    EntityKanoka::new,
//                    MobCategory.MISC
//            )
//            .sized(0.5F, 0.5F)
//            .clientTrackingRange(64)
//            .updateInterval(10)
//            .build(NuiCraft.MODID + ":kanoka_disc"));
    
    // Creature mobs
//    public static final DeferredHolder<EntityType<?>, EntityType<EntityMahi>> MAHI =
//            ENTITY_TYPES.register("mahi", () -> EntityType.Builder.of(
//                    EntityMahi::new,
//                    MobCategory.CREATURE
//            )
//            .sized(0.9F, 1.4F)
//            .clientTrackingRange(64)
//            .updateInterval(3)
//            .build(NuiCraft.MODID + ":mahi"));
//
//    public static final DeferredHolder<EntityType<?>, EntityType<EntityFikou>> FIKOU =
//            ENTITY_TYPES.register("fikou", () -> EntityType.Builder.of(
//                    EntityFikou::new,
//                    MobCategory.CREATURE
//            )
//            .sized(0.7F, 0.5F)
//            .clientTrackingRange(64)
//            .updateInterval(3)
//            .build(NuiCraft.MODID + ":fikou"));
//
//    public static final DeferredHolder<EntityType<?>, EntityType<EntityHoi>> HOI =
//            ENTITY_TYPES.register("hoi", () -> EntityType.Builder.of(
//                    EntityHoi::new,
//                    MobCategory.CREATURE
//            )
//            .sized(0.6F, 0.4F)
//            .clientTrackingRange(64)
//            .updateInterval(3)
//            .build(NuiCraft.MODID + ":hoi"));
//
//    public static final DeferredHolder<EntityType<?>, EntityType<EntityKofoJaga>> KOFO_JAGA =
//            ENTITY_TYPES.register("kofo_jaga", () -> EntityType.Builder.of(
//                    EntityKofoJaga::new,
//                    MobCategory.CREATURE
//            )
//            .sized(1.0F, 0.8F)
//            .clientTrackingRange(64)
//            .updateInterval(3)
//            .build(NuiCraft.MODID + ":kofo_jaga"));
//
//    public static final DeferredHolder<EntityType<?>, EntityType<EntityNuiJaga>> NUI_JAGA =
//            ENTITY_TYPES.register("nui_jaga", () -> EntityType.Builder.of(
//                    EntityNuiJaga::new,
//                    MobCategory.CREATURE
//            )
//            .sized(1.4F, 1.2F)
//            .clientTrackingRange(64)
//            .updateInterval(3)
//            .build(NuiCraft.MODID + ":nui_jaga"));
}
