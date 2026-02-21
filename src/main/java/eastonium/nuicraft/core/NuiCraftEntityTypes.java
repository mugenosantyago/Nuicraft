package eastonium.nuicraft.core;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.entity.*;
import net.minecraft.core.registries.Registries;
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

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMuaka>> MUAKA =
            ENTITY_TYPES.register("muaka", () -> EntityType.Builder.of(EntityMuaka::new, MobCategory.MONSTER)
                    .sized(1.4F, 1.2F).clientTrackingRange(64).build(key("muaka")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityTarakava>> TARAKAVA =
            ENTITY_TYPES.register("tarakava", () -> EntityType.Builder.of(EntityTarakava::new, MobCategory.MONSTER)
                    .sized(1.2F, 1.0F).clientTrackingRange(64).build(key("tarakava")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityKofoJaga>> KOFO_JAGA =
            ENTITY_TYPES.register("kofo_jaga", () -> EntityType.Builder.of(EntityKofoJaga::new, MobCategory.MONSTER)
                    .sized(1.0F, 0.8F).clientTrackingRange(64).build(key("kofo_jaga")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityNuiJaga>> NUI_JAGA =
            ENTITY_TYPES.register("nui_jaga", () -> EntityType.Builder.of(EntityNuiJaga::new, MobCategory.MONSTER)
                    .sized(1.4F, 1.2F).clientTrackingRange(64).build(key("nui_jaga")));

    // ---- Flying Rahi ----

    public static final DeferredHolder<EntityType<?>, EntityType<EntityGukko>> GUKKO =
            ENTITY_TYPES.register("gukko", () -> EntityType.Builder.of(EntityGukko::new, MobCategory.CREATURE)
                    .sized(1.2F, 0.9F).clientTrackingRange(64).build(key("gukko")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityNuiRama>> NUI_RAMA =
            ENTITY_TYPES.register("nui_rama", () -> EntityType.Builder.of(EntityNuiRama::new, MobCategory.CREATURE)
                    .sized(0.9F, 0.6F).clientTrackingRange(64).build(key("nui_rama")));

    // ---- NPCs (dialogue) ----

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMatoran>> MATORAN =
            ENTITY_TYPES.register("matoran", () -> EntityType.Builder.of(EntityMatoran::new, MobCategory.CREATURE)
                    .sized(0.6F, 1.8F).clientTrackingRange(64).build(key("matoran")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityTuraga>> TURAGA =
            ENTITY_TYPES.register("turaga", () -> EntityType.Builder.of(EntityTuraga::new, MobCategory.CREATURE)
                    .sized(0.6F, 1.6F).clientTrackingRange(64).build(key("turaga")));

    // ---- Toa (one per Koro-themed biome) ----

    public static final DeferredHolder<EntityType<?>, EntityType<EntityToa>> TOA_TAHU =
            ENTITY_TYPES.register("toa_tahu", () -> EntityType.Builder.<EntityToa>of((t, l) -> new EntityToa(t, l, EntityToa.Variant.TAHU), MobCategory.CREATURE)
                    .sized(0.6F, 1.8F).clientTrackingRange(64).build(key("toa_tahu")));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityToa>> TOA_GALI =
            ENTITY_TYPES.register("toa_gali", () -> EntityType.Builder.<EntityToa>of((t, l) -> new EntityToa(t, l, EntityToa.Variant.GALI), MobCategory.CREATURE)
                    .sized(0.6F, 1.8F).clientTrackingRange(64).build(key("toa_gali")));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityToa>> TOA_LEWA =
            ENTITY_TYPES.register("toa_lewa", () -> EntityType.Builder.<EntityToa>of((t, l) -> new EntityToa(t, l, EntityToa.Variant.LEWA), MobCategory.CREATURE)
                    .sized(0.6F, 1.8F).clientTrackingRange(64).build(key("toa_lewa")));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityToa>> TOA_ONUA =
            ENTITY_TYPES.register("toa_onua", () -> EntityType.Builder.<EntityToa>of((t, l) -> new EntityToa(t, l, EntityToa.Variant.ONUA), MobCategory.CREATURE)
                    .sized(0.6F, 1.8F).clientTrackingRange(64).build(key("toa_onua")));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityToa>> TOA_POHATU =
            ENTITY_TYPES.register("toa_pohatu", () -> EntityType.Builder.<EntityToa>of((t, l) -> new EntityToa(t, l, EntityToa.Variant.POHATU), MobCategory.CREATURE)
                    .sized(0.6F, 1.8F).clientTrackingRange(64).build(key("toa_pohatu")));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityToa>> TOA_KOPAKA =
            ENTITY_TYPES.register("toa_kopaka", () -> EntityType.Builder.<EntityToa>of((t, l) -> new EntityToa(t, l, EntityToa.Variant.KOPAKA), MobCategory.CREATURE)
                    .sized(0.6F, 1.8F).clientTrackingRange(64).build(key("toa_kopaka")));

    // ---- Projectiles ----

    public static final DeferredHolder<EntityType<?>, EntityType<EntityThrownDisc>> THROWN_DISC =
            ENTITY_TYPES.register("thrown_disc", () -> EntityType.Builder.<EntityThrownDisc>of(EntityThrownDisc::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(key("thrown_disc")));
}
