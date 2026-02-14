package eastonium.nuicraft.core;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.entity.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NuiCraftEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, NuiCraft.MODID);

    private static ResourceKey<EntityType<?>> key(String name) {
        return ResourceKey.create(Registries.ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, name));
    }

    // ---- Passive Rahi ----

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMahi>> MAHI =
            ENTITY_TYPES.register("mahi", () -> EntityType.Builder.of(EntityMahi::new, MobCategory.CREATURE)
                    .sized(0.9F, 1.4F).clientTrackingRange(64).build(key("mahi")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityFikou>> FIKOU =
            ENTITY_TYPES.register("fikou", () -> EntityType.Builder.of(EntityFikou::new, MobCategory.CREATURE)
                    .sized(0.7F, 0.5F).clientTrackingRange(64).build(key("fikou")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityHoi>> HOI =
            ENTITY_TYPES.register("hoi", () -> EntityType.Builder.of(EntityHoi::new, MobCategory.CREATURE)
                    .sized(0.6F, 0.4F).clientTrackingRange(64).build(key("hoi")));

    // ---- Hostile Rahi ----

    public static final DeferredHolder<EntityType<?>, EntityType<EntityKofoJaga>> KOFO_JAGA =
            ENTITY_TYPES.register("kofo_jaga", () -> EntityType.Builder.of(EntityKofoJaga::new, MobCategory.MONSTER)
                    .sized(1.0F, 0.8F).clientTrackingRange(64).build(key("kofo_jaga")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityNuiJaga>> NUI_JAGA =
            ENTITY_TYPES.register("nui_jaga", () -> EntityType.Builder.of(EntityNuiJaga::new, MobCategory.MONSTER)
                    .sized(1.4F, 1.2F).clientTrackingRange(64).build(key("nui_jaga")));

    // ---- NPCs (dialogue) ----

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMatoran>> MATORAN =
            ENTITY_TYPES.register("matoran", () -> EntityType.Builder.of(EntityMatoran::new, MobCategory.CREATURE)
                    .sized(0.6F, 1.8F).clientTrackingRange(64).build(key("matoran")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityTuraga>> TURAGA =
            ENTITY_TYPES.register("turaga", () -> EntityType.Builder.of(EntityTuraga::new, MobCategory.CREATURE)
                    .sized(0.6F, 1.6F).clientTrackingRange(64).build(key("turaga")));

    // ---- Projectiles ----

    public static final DeferredHolder<EntityType<?>, EntityType<EntityThrownDisc>> THROWN_DISC =
            ENTITY_TYPES.register("thrown_disc", () -> EntityType.Builder.<EntityThrownDisc>of(EntityThrownDisc::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(key("thrown_disc")));
}
