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
                    .build(ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "mahi").toString()));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityFikou>> FIKOU =
            ENTITY_TYPES.register("fikou", () -> EntityType.Builder.of(EntityFikou::new, MobCategory.CREATURE)
                    .sized(0.7F, 0.5F)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "fikou").toString()));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityHoi>> HOI =
            ENTITY_TYPES.register("hoi", () -> EntityType.Builder.of(EntityHoi::new, MobCategory.CREATURE)
                    .sized(0.6F, 0.4F)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "hoi").toString()));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityKofoJaga>> KOFO_JAGA =
            ENTITY_TYPES.register("kofo_jaga", () -> EntityType.Builder.of(EntityKofoJaga::new, MobCategory.CREATURE)
                    .sized(1.0F, 0.8F)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "kofo_jaga").toString()));
    
    public static final DeferredHolder<EntityType<?>, EntityType<EntityNuiJaga>> NUI_JAGA =
            ENTITY_TYPES.register("nui_jaga", () -> EntityType.Builder.of(EntityNuiJaga::new, MobCategory.MONSTER)
                    .sized(1.4F, 1.2F)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "nui_jaga").toString()));
    
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
