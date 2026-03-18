package eastonium.mnogii.core;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.entity.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MnogiiEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, Mnogii.MODID);

    private static ResourceKey<EntityType<?>> key(String name) {
        return ResourceKey.create(Registries.ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, name));
    }

    // ---- Passive Rahi ----

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMahi>> MAHI =
            ENTITY_TYPES.register("mahi", () -> EntityType.Builder.of(EntityMahi::new, MobCategory.CREATURE)
                    .sized(0.9F, 1.4F).clientTrackingRange(64).build(key("mahi")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityFikou>> FIKOU =
            ENTITY_TYPES.register("fikou", () -> EntityType.Builder.of(EntityFikou::new, MobCategory.CREATURE)
                    .sized(0.8F, 0.5F).clientTrackingRange(64).build(key("fikou")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntitySpiderFikou>> SPIDER_FIKOU =
            ENTITY_TYPES.register("spider_fikou", () -> EntityType.Builder.of(EntitySpiderFikou::new, MobCategory.CREATURE)
                    .sized(0.8F, 0.5F).clientTrackingRange(64).build(key("spider_fikou")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityHoi>> HOI =
            ENTITY_TYPES.register("hoi", () -> EntityType.Builder.of(EntityHoi::new, MobCategory.CREATURE)
                    .sized(0.6F, 0.4F).clientTrackingRange(64).build(key("hoi")));

    // ---- Hostile Rahi ----

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMuaka>> MUAKA =
            ENTITY_TYPES.register("muaka", () -> EntityType.Builder.of(EntityMuaka::new, MobCategory.MONSTER)
                    .sized(2.0F, 2.5F).clientTrackingRange(64).build(key("muaka")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityTarakava>> TARAKAVA =
            ENTITY_TYPES.register("tarakava", () -> EntityType.Builder.of(EntityTarakava::new, MobCategory.MONSTER)
                    .sized(1.5F, 2.5F).clientTrackingRange(64).build(key("tarakava")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityKofoJaga>> KOFO_JAGA =
            ENTITY_TYPES.register("kofo_jaga", () -> EntityType.Builder.of(EntityKofoJaga::new, MobCategory.MONSTER)
                    .sized(1.0F, 0.8F).clientTrackingRange(64).build(key("kofo_jaga")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityNuiJaga>> NUI_JAGA =
            ENTITY_TYPES.register("nui_jaga", () -> EntityType.Builder.of(EntityNuiJaga::new, MobCategory.MONSTER)
                    .sized(1.4F, 1.2F).clientTrackingRange(64).build(key("nui_jaga")));

    // ---- Flying Rahi ----

    public static final DeferredHolder<EntityType<?>, EntityType<EntityGukko>> GUKKO =
            ENTITY_TYPES.register("gukko", () -> EntityType.Builder.of(EntityGukko::new, MobCategory.CREATURE)
                    .sized(2.5F, 1.8F).clientTrackingRange(64).build(key("gukko")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityNuiRama>> NUI_RAMA =
            ENTITY_TYPES.register("nui_rama", () -> EntityType.Builder.of(EntityNuiRama::new, MobCategory.CREATURE)
                    .sized(2.0F, 2.0F).clientTrackingRange(64).build(key("nui_rama")));

    // ---- NPCs (dialogue) ----

    /** Generic Matoran - koro determined by biome at spawn, used by structure spawn_overrides. */
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMatoran>> MATORAN =
            ENTITY_TYPES.register("matoran", () -> EntityType.Builder.<EntityMatoran>of((type, level) -> new EntityMatoran(type, level), MobCategory.CREATURE)
                    .sized(0.6F, 1.5F).clientTrackingRange(64).build(key("matoran")));

    // Koro-specific Matoran - koro + canonical mask locked in; used for spawn eggs.
    public static final DeferredHolder<EntityType<?>, EntityType<EntityMatoran>> MATORAN_TA =
            ENTITY_TYPES.register("matoran_ta", () -> EntityType.Builder.<EntityMatoran>of(
                    (type, level) -> new EntityMatoran(type, level, EntityMatoran.Koro.TA, EntityMatoran.Mask.HAU), MobCategory.CREATURE)
                    .sized(0.6F, 1.5F).clientTrackingRange(64).build(key("matoran_ta")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMatoran>> MATORAN_GA =
            ENTITY_TYPES.register("matoran_ga", () -> EntityType.Builder.<EntityMatoran>of(
                    (type, level) -> new EntityMatoran(type, level, EntityMatoran.Koro.GA, EntityMatoran.Mask.HUNA), MobCategory.CREATURE)
                    .sized(0.6F, 1.5F).clientTrackingRange(64).build(key("matoran_ga")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMatoran>> MATORAN_LE =
            ENTITY_TYPES.register("matoran_le", () -> EntityType.Builder.<EntityMatoran>of(
                    (type, level) -> new EntityMatoran(type, level, EntityMatoran.Koro.LE, EntityMatoran.Mask.MIRU), MobCategory.CREATURE)
                    .sized(0.6F, 1.5F).clientTrackingRange(64).build(key("matoran_le")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMatoran>> MATORAN_ONU =
            ENTITY_TYPES.register("matoran_onu", () -> EntityType.Builder.<EntityMatoran>of(
                    (type, level) -> new EntityMatoran(type, level, EntityMatoran.Koro.ONU, EntityMatoran.Mask.PAKARI), MobCategory.CREATURE)
                    .sized(0.6F, 1.5F).clientTrackingRange(64).build(key("matoran_onu")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMatoran>> MATORAN_KO =
            ENTITY_TYPES.register("matoran_ko", () -> EntityType.Builder.<EntityMatoran>of(
                    (type, level) -> new EntityMatoran(type, level, EntityMatoran.Koro.KO, EntityMatoran.Mask.KAUKAU), MobCategory.CREATURE)
                    .sized(0.6F, 1.5F).clientTrackingRange(64).build(key("matoran_ko")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMatoran>> MATORAN_PO =
            ENTITY_TYPES.register("matoran_po", () -> EntityType.Builder.<EntityMatoran>of(
                    (type, level) -> new EntityMatoran(type, level, EntityMatoran.Koro.PO, EntityMatoran.Mask.KAKAMA), MobCategory.CREATURE)
                    .sized(0.6F, 1.5F).clientTrackingRange(64).build(key("matoran_po")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMatoran>> MATORAN_PURPLE =
            ENTITY_TYPES.register("matoran_purple", () -> EntityType.Builder.<EntityMatoran>of(
                    (type, level) -> new EntityMatoran(type, level, EntityMatoran.Koro.PURPLE, EntityMatoran.Mask.HUNA), MobCategory.CREATURE)
                    .sized(0.6F, 1.5F).clientTrackingRange(64).build(key("matoran_purple")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityMatoran>> MATORAN_YELLOW =
            ENTITY_TYPES.register("matoran_yellow", () -> EntityType.Builder.<EntityMatoran>of(
                    (type, level) -> new EntityMatoran(type, level, EntityMatoran.Koro.YELLOW, EntityMatoran.Mask.KAKAMA), MobCategory.CREATURE)
                    .sized(0.6F, 1.5F).clientTrackingRange(64).build(key("matoran_yellow")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityTuraga>> TURAGA =
            ENTITY_TYPES.register("turaga", () -> EntityType.Builder.<EntityTuraga>of(EntityTuraga::new, MobCategory.CREATURE)
                    .sized(0.55F, 1.5F).clientTrackingRange(64).build(key("turaga")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityTuraga>> TURAGA_VAKAMA =
            ENTITY_TYPES.register("turaga_vakama", () -> EntityType.Builder.<EntityTuraga>of(
                    (t, l) -> new EntityTuraga(t, l, EntityTuraga.TuragaType.VAKAMA), MobCategory.CREATURE)
                    .sized(0.55F, 1.5F).clientTrackingRange(64).build(key("turaga_vakama")));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityTuraga>> TURAGA_NOKAMA =
            ENTITY_TYPES.register("turaga_nokama", () -> EntityType.Builder.<EntityTuraga>of(
                    (t, l) -> new EntityTuraga(t, l, EntityTuraga.TuragaType.NOKAMA), MobCategory.CREATURE)
                    .sized(0.55F, 1.5F).clientTrackingRange(64).build(key("turaga_nokama")));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityTuraga>> TURAGA_MATAU =
            ENTITY_TYPES.register("turaga_matau", () -> EntityType.Builder.<EntityTuraga>of(
                    (t, l) -> new EntityTuraga(t, l, EntityTuraga.TuragaType.MATAU), MobCategory.CREATURE)
                    .sized(0.55F, 1.5F).clientTrackingRange(64).build(key("turaga_matau")));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityTuraga>> TURAGA_ONEWA =
            ENTITY_TYPES.register("turaga_onewa", () -> EntityType.Builder.<EntityTuraga>of(
                    (t, l) -> new EntityTuraga(t, l, EntityTuraga.TuragaType.ONEWA), MobCategory.CREATURE)
                    .sized(0.55F, 1.5F).clientTrackingRange(64).build(key("turaga_onewa")));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityTuraga>> TURAGA_WHENUA =
            ENTITY_TYPES.register("turaga_whenua", () -> EntityType.Builder.<EntityTuraga>of(
                    (t, l) -> new EntityTuraga(t, l, EntityTuraga.TuragaType.WHENUA), MobCategory.CREATURE)
                    .sized(0.55F, 1.5F).clientTrackingRange(64).build(key("turaga_whenua")));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityTuraga>> TURAGA_NUJU =
            ENTITY_TYPES.register("turaga_nuju", () -> EntityType.Builder.<EntityTuraga>of(
                    (t, l) -> new EntityTuraga(t, l, EntityTuraga.TuragaType.NUJU), MobCategory.CREATURE)
                    .sized(0.55F, 1.5F).clientTrackingRange(64).build(key("turaga_nuju")));

    // ---- Toa (one per Koro-themed biome) ----

    public static final DeferredHolder<EntityType<?>, EntityType<EntityToa>> TOA_TAHU =
            ENTITY_TYPES.register("toa_tahu", () -> EntityType.Builder.<EntityToa>of((t, l) -> new EntityToa(t, l, EntityToa.Variant.TAHU), MobCategory.CREATURE)
                    .sized(1.0F, 3.8F).clientTrackingRange(64).build(key("toa_tahu")));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityToa>> TOA_GALI =
            ENTITY_TYPES.register("toa_gali", () -> EntityType.Builder.<EntityToa>of((t, l) -> new EntityToa(t, l, EntityToa.Variant.GALI), MobCategory.CREATURE)
                    .sized(1.0F, 3.8F).clientTrackingRange(64).build(key("toa_gali")));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityToa>> TOA_LEWA =
            ENTITY_TYPES.register("toa_lewa", () -> EntityType.Builder.<EntityToa>of((t, l) -> new EntityToa(t, l, EntityToa.Variant.LEWA), MobCategory.CREATURE)
                    .sized(1.0F, 3.8F).clientTrackingRange(64).build(key("toa_lewa")));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityToa>> TOA_ONUA =
            ENTITY_TYPES.register("toa_onua", () -> EntityType.Builder.<EntityToa>of((t, l) -> new EntityToa(t, l, EntityToa.Variant.ONUA), MobCategory.CREATURE)
                    .sized(1.0F, 3.8F).clientTrackingRange(64).build(key("toa_onua")));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityToa>> TOA_POHATU =
            ENTITY_TYPES.register("toa_pohatu", () -> EntityType.Builder.<EntityToa>of((t, l) -> new EntityToa(t, l, EntityToa.Variant.POHATU), MobCategory.CREATURE)
                    .sized(1.0F, 3.8F).clientTrackingRange(64).build(key("toa_pohatu")));
    public static final DeferredHolder<EntityType<?>, EntityType<EntityToa>> TOA_KOPAKA =
            ENTITY_TYPES.register("toa_kopaka", () -> EntityType.Builder.<EntityToa>of((t, l) -> new EntityToa(t, l, EntityToa.Variant.KOPAKA), MobCategory.CREATURE)
                    .sized(1.0F, 3.8F).clientTrackingRange(64).build(key("toa_kopaka")));

    // ---- Projectiles ----

    public static final DeferredHolder<EntityType<?>, EntityType<EntityThrownDisc>> THROWN_DISC =
            ENTITY_TYPES.register("thrown_disc", () -> EntityType.Builder.<EntityThrownDisc>of(EntityThrownDisc::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(key("thrown_disc")));
}
