package eastonium.mnogii.entity;

import eastonium.mnogii.core.MnogiiBlocks;
import eastonium.mnogii.core.MnogiiItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import eastonium.mnogii.client.animator.MatoranAnimator;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import java.util.EnumSet;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

/**
 * Matoran - villager-like NPC that trades items relative to their Koro and Profession.
 * Ta-Koro Matoran trade fire/desert items, Ga-Koro trade water items, etc.
 * Each Matoran also has a profession (Maskmaker, Smith, Miner, etc.) that adds
 * additional specialised trades on top of their koro-specific base trades.
 */
public class EntityMatoran extends Animal implements Merchant {

    /** Kanohi Mata mask variant. Model/texture path uses getId() (e.g. pakari, hau). */
    public enum Mask {
        HAU("hau")      { @Override public Item getDropItem() { return MnogiiItems.MASK_MATA_HAU.get(); } },
        KAUKAU("kaukau"){ @Override public Item getDropItem() { return MnogiiItems.MASK_MATA_KAUKAU.get(); } },
        MIRU("miru")    { @Override public Item getDropItem() { return MnogiiItems.MASK_MATA_MIRU.get(); } },
        KAKAMA("kakama"){ @Override public Item getDropItem() { return MnogiiItems.MASK_MATA_KAKAMA.get(); } },
        PAKARI("pakari"){ @Override public Item getDropItem() { return MnogiiItems.MASK_MATA_PAKARI.get(); } },
        AKAKU("akaku")  { @Override public Item getDropItem() { return MnogiiItems.MASK_MATA_AKAKU.get(); } },
        HUNA("huna")    { @Override public Item getDropItem() { return MnogiiItems.MASK_MATA_HUNA.get(); } },
        MAHIKI("mahiki"){ @Override public Item getDropItem() { return MnogiiItems.MASK_MATA_MAHIKI.get(); } },
        MATATU("matatu"){ @Override public Item getDropItem() { return MnogiiItems.MASK_MATA_MATATU.get(); } },
        KOMAU("komau")  { @Override public Item getDropItem() { return MnogiiItems.MASK_MATA_KOMAU.get(); } },
        RARU("raru")    { @Override public Item getDropItem() { return MnogiiItems.MASK_MATA_RARU.get(); } },
        RURU("ruru")    { @Override public Item getDropItem() { return MnogiiItems.MASK_MATA_RURU.get(); } };

        private final String id;
        Mask(String id) { this.id = id; }
        public String getId() { return id; }
        /** The mask item dropped when this matoran is killed. */
        public abstract Item getDropItem();
    }

    public enum Koro {
        /** Ta-Koro (red) - fire/desert */
        TA("matoran_ta", MnogiiBlocks.TA_KORO_STONE.get(), MnogiiItems.INGOT_PROTODERMIS.get(), MnogiiItems.FIRE_TOA_STONE.get(), Items.BLAZE_POWDER),
        /** Ga-Koro (blue) - water */
        GA("matoran_ga", MnogiiBlocks.GA_KORO_STONE.get(), MnogiiItems.INGOT_PROTODERMIS.get(), MnogiiItems.WATER_TOA_STONE.get(), Items.PRISMARINE_SHARD),
        /** Po-Koro (brown) - rock/stone */
        PO("matoran_po", MnogiiBlocks.PO_KORO_STONE.get(), MnogiiItems.INGOT_PROTODERMIS.get(), MnogiiItems.ROCK_TOA_STONE.get(), Items.SMOOTH_STONE),
        /** Onu-Koro (black) - earth */
        ONU("matoran_onu", MnogiiBlocks.ONU_KORO_STONE.get(), MnogiiItems.INGOT_PROTODERMIS.get(), MnogiiItems.EARTH_TOA_STONE.get(), Items.COAL),
        /** Le-Koro (green) - air */
        LE("matoran_le", MnogiiBlocks.LE_KORO_STONE.get(), MnogiiItems.INGOT_PROTODERMIS.get(), MnogiiItems.AIR_TOA_STONE.get(), Items.FEATHER),
        /** Ko-Koro (white) - ice */
        KO("matoran_ko", MnogiiBlocks.KO_KORO_STONE.get(), MnogiiItems.INGOT_PROTODERMIS.get(), MnogiiItems.ICE_TOA_STONE.get(), Items.SNOWBALL),
        /** Purple visitor Matoran — not bound to a specific village. */
        PURPLE("matoran_purple", MnogiiBlocks.TA_KORO_STONE.get(), MnogiiItems.INGOT_PROTODERMIS.get(), MnogiiItems.FIRE_TOA_STONE.get(), Items.AMETHYST_SHARD),
        /** Yellow visitor Matoran — not bound to a specific village. */
        YELLOW("matoran_yellow", MnogiiBlocks.PO_KORO_STONE.get(), MnogiiItems.INGOT_PROTODERMIS.get(), MnogiiItems.ROCK_TOA_STONE.get(), Items.GOLD_NUGGET);

        private final String textureName;
        private final net.minecraft.world.level.ItemLike koroStone;
        private final net.minecraft.world.level.ItemLike protodermis;
        private final net.minecraft.world.level.ItemLike toaStone;
        private final net.minecraft.world.level.ItemLike extraItem;

        Koro(String textureName, net.minecraft.world.level.ItemLike koroStone, net.minecraft.world.level.ItemLike protodermis, net.minecraft.world.level.ItemLike toaStone, net.minecraft.world.level.ItemLike extraItem) {
            this.textureName = textureName;
            this.koroStone = koroStone;
            this.protodermis = protodermis;
            this.toaStone = toaStone;
            this.extraItem = extraItem;
        }

        public String getTextureName() { return textureName; }
        public net.minecraft.world.level.ItemLike getKoroStone() { return koroStone; }
        public net.minecraft.world.level.ItemLike getProtodermis() { return protodermis; }
        public net.minecraft.world.level.ItemLike getToaStone() { return toaStone; }
        public net.minecraft.world.level.ItemLike getExtraItem() { return extraItem; }

        /** The Toa's signature Kanohi mask associated with this Koro. */
        public Item getToaMask() {
            return switch (this) {
                case TA     -> MnogiiItems.MASK_MATA_HAU.get();
                case GA     -> MnogiiItems.MASK_MATA_KAUKAU.get();
                case LE     -> MnogiiItems.MASK_MATA_MIRU.get();
                case ONU    -> MnogiiItems.MASK_MATA_PAKARI.get();
                case PO     -> MnogiiItems.MASK_MATA_KAKAMA.get();
                case KO     -> MnogiiItems.MASK_MATA_AKAKU.get();
                case PURPLE -> MnogiiItems.MASK_MATA_HUNA.get();
                case YELLOW -> MnogiiItems.MASK_MATA_KAKAMA.get();
            };
        }

        /**
         * The Matoran mask variant the dedicated Maskmaker NPC wears.
         * Uses only implemented masks (geo + textures exist).
         */
        public Mask getMaskmakerMask() {
            return switch (this) {
                case TA     -> Mask.HAU;
                case GA     -> Mask.KAUKAU;
                case LE     -> Mask.MIRU;
                case ONU    -> Mask.PAKARI;
                case PO     -> Mask.KAKAMA;
                case KO     -> Mask.HAU;
                case PURPLE -> Mask.HUNA;
                case YELLOW -> Mask.KAKAMA;
            };
        }

        /** A secondary Kanohi associated with this Koro's culture. */
        public Item getSecondaryMask() {
            return switch (this) {
                case TA     -> MnogiiItems.MASK_MATA_RURU.get();
                case GA     -> MnogiiItems.MASK_MATA_HUNA.get();
                case LE     -> MnogiiItems.MASK_MATA_MATATU.get();
                case ONU    -> MnogiiItems.MASK_MATA_KOMAU.get();
                case PO     -> MnogiiItems.MASK_MATA_MAHIKI.get();
                case KO     -> MnogiiItems.MASK_MATA_RARU.get();
                case PURPLE -> MnogiiItems.MASK_MATA_RARU.get();
                case YELLOW -> MnogiiItems.MASK_MATA_MAHIKI.get();
            };
        }

        /** Koro-specific Kanoka disk. */
        public Item getKoroDisk() {
            return switch (this) {
                case TA     -> MnogiiItems.KANOKA_DISK_TA.get();
                case GA     -> MnogiiItems.KANOKA_DISK_GA.get();
                case LE     -> MnogiiItems.KANOKA_DISK_LE.get();
                case ONU    -> MnogiiItems.KANOKA_DISK_ONU.get();
                case PO     -> MnogiiItems.KANOKA_DISK_PO.get();
                case KO     -> MnogiiItems.KANOKA_DISK_KO.get();
                case PURPLE -> MnogiiItems.KANOKA_DISK_TA.get();
                case YELLOW -> MnogiiItems.KANOKA_DISK_PO.get();
            };
        }
    }

    /**
     * Matoran profession — analogous to vanilla villager professions.
     * Determines additional trade offers on top of the standard koro-stone/toa-stone base trades.
     */
    public enum Profession {
        /** Crafts and sells Kanohi masks. */
        MASKMAKER("maskmaker"),
        /** Forges protodermis weapons and tools. */
        SMITH("smith"),
        /** Extracts and sells ores and ingots from the earth. */
        MINER("miner"),
        /** Carves and launches Kanoka disks. */
        DISC_CRAFTER("disc_crafter"),
        /** Studies elemental lore; trades toa stones and special items. */
        SCHOLAR("scholar"),
        /** General goods trader; deals in everyday mod materials. */
        MERCHANT("merchant");

        private final String id;
        Profession(String id) { this.id = id; }
        public String getId() { return id; }
    }

    /**
     * The eight accent colors a Matoran's mask and feet can be painted.
     * Body color is always the canonical koro color; mask and feet are randomized.
     */
    public enum MatoranColor {
        RED   (0xCC2200),   // Ta-Koro canonical
        BLUE  (0x0044AA),   // Ga-Koro canonical
        GREEN (0x006600),   // Le-Koro canonical
        BLACK (0x111111),   // Onu-Koro canonical
        BROWN (0x886633),   // Po-Koro canonical
        WHITE (0xCCDDEE),   // Ko-Koro canonical
        PURPLE(0x660099),   // extra accent
        YELLOW(0xEECC00);   // extra accent

        public final int rgb;
        MatoranColor(int rgb) { this.rgb = rgb; }
        public String getId()  { return name().toLowerCase(); }

        /** The canonical koro body color. */
        public static MatoranColor forKoro(Koro koro) {
            return switch (koro) {
                case TA     -> RED;
                case GA     -> BLUE;
                case LE     -> GREEN;
                case ONU    -> BLACK;
                case PO     -> BROWN;
                case KO     -> WHITE;
                case PURPLE -> PURPLE;
                case YELLOW -> YELLOW;
            };
        }

        /** Pick any of the 8 colors at random. */
        public static MatoranColor random(net.minecraft.util.RandomSource rand) {
            return values()[rand.nextInt(values().length)];
        }

        /**
         * Blend two parent colors by averaging their RGB, then snapping to the
         * nearest enum value. Gives natural-looking offspring accent colors.
         * If both parents share the same color the result equals that color,
         * preserving strong family traits through many generations.
         */
        public static MatoranColor blend(MatoranColor a, MatoranColor b) {
            if (a == b) return a;
            int r  = (((a.rgb >> 16) & 0xFF) + ((b.rgb >> 16) & 0xFF)) / 2;
            int g  = (((a.rgb >>  8) & 0xFF) + ((b.rgb >>  8) & 0xFF)) / 2;
            int bl = ((a.rgb         & 0xFF) + (b.rgb         & 0xFF)) / 2;
            int minDist = Integer.MAX_VALUE;
            MatoranColor nearest = a;
            for (MatoranColor c : values()) {
                int dr = ((c.rgb >> 16) & 0xFF) - r;
                int dg = ((c.rgb >>  8) & 0xFF) - g;
                int db = ( c.rgb        & 0xFF) - bl;
                int dist = dr * dr + dg * dg + db * db;
                if (dist < minDist) { minDist = dist; nearest = c; }
            }
            return nearest;
        }

        /**
         * Pick a random accent color that is guaranteed to be different from the
         * given koro's canonical body color.  This ensures newly-spawned Matoran
         * always have at least one visually distinct accent.
         */
        public static MatoranColor randomAccent(Koro koro, net.minecraft.util.RandomSource rand) {
            MatoranColor body = forKoro(koro);
            MatoranColor[] others = java.util.Arrays.stream(values())
                    .filter(c -> c != body)
                    .toArray(MatoranColor[]::new);
            return others[rand.nextInt(others.length)];
        }
    }

    private static final EntityDataAccessor<Integer> DATA_KORO        = SynchedEntityData.defineId(EntityMatoran.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_MASK        = SynchedEntityData.defineId(EntityMatoran.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_PROFESSION  = SynchedEntityData.defineId(EntityMatoran.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_MASK_COLOR  = SynchedEntityData.defineId(EntityMatoran.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_FEET_COLOR  = SynchedEntityData.defineId(EntityMatoran.class, EntityDataSerializers.INT);

    /** Tracks the last animation dispatched so we only send on state transitions. */
    private boolean lastMoving = false;
    /** Counts idle ticks before firing the next ambient animation (wave / work). */
    private int ambientAnimTimer = 0;

    /** Distance (blocks) from home at which ReturnToHomeGoal activates. */
    private static final int HOME_RETURN_THRESHOLD = 22;
    /** Distance at which ReturnToHomeGoal considers arrival. */
    private static final int HOME_ARRIVE_THRESHOLD = 3;

    /** Persisted home position set by KoroSpawnHandler; null until first assignment. */
    @Nullable
    private BlockPos homePos = null;

    public void setHomePos(BlockPos pos) {
        this.homePos = pos.immutable();
    }

    @Nullable
    public BlockPos getHomePos() {
        return homePos;
    }

    @Nullable
    private Player tradingPlayer;
    @Nullable
    private MerchantOffers offers;

    public EntityMatoran(EntityType<? extends Animal> type, Level level) {
        this(type, level, Koro.TA);
    }

    public EntityMatoran(EntityType<? extends Animal> type, Level level, Koro koro) {
        this(type, level, koro, Mask.PAKARI);
    }

    public EntityMatoran(EntityType<? extends Animal> type, Level level, Koro koro, Mask mask) {
        this(type, level, koro, mask, Profession.MERCHANT);
    }

    public EntityMatoran(EntityType<? extends Animal> type, Level level, Koro koro, Mask mask, Profession profession) {
        super(type, level);
        this.entityData.set(DATA_KORO,       koro.ordinal());
        this.entityData.set(DATA_MASK,       mask.ordinal());
        this.entityData.set(DATA_PROFESSION, profession.ordinal());
    }

    public Koro getKoro() {
        int i = this.entityData.get(DATA_KORO);
        Koro[] koros = Koro.values();
        return i >= 0 && i < koros.length ? koros[i] : Koro.TA;
    }

    public void setKoro(Koro koro) {
        this.entityData.set(DATA_KORO, koro.ordinal());
    }

    public Mask getMask() {
        int i = this.entityData.get(DATA_MASK);
        Mask[] masks = Mask.values();
        return i >= 0 && i < masks.length ? masks[i] : Mask.PAKARI;
    }

    public void setMask(Mask mask) {
        this.entityData.set(DATA_MASK, mask.ordinal());
    }

    public Profession getProfession() {
        int i = this.entityData.get(DATA_PROFESSION);
        Profession[] profs = Profession.values();
        return i >= 0 && i < profs.length ? profs[i] : Profession.MERCHANT;
    }

    public void setProfession(Profession profession) {
        this.entityData.set(DATA_PROFESSION, profession.ordinal());
        // Invalidate cached offers so new profession trades are generated.
        this.offers = null;
    }

    public MatoranColor getMaskColor() {
        int i = this.entityData.get(DATA_MASK_COLOR);
        MatoranColor[] colors = MatoranColor.values();
        return i >= 0 && i < colors.length ? colors[i] : MatoranColor.RED;
    }

    public void setMaskColor(MatoranColor color) {
        this.entityData.set(DATA_MASK_COLOR, color.ordinal());
    }

    public MatoranColor getFeetColor() {
        int i = this.entityData.get(DATA_FEET_COLOR);
        MatoranColor[] colors = MatoranColor.values();
        return i >= 0 && i < colors.length ? colors[i] : MatoranColor.RED;
    }

    public void setFeetColor(MatoranColor color) {
        this.entityData.set(DATA_FEET_COLOR, color.ordinal());
    }

    /** Variant key for model/texture: matoran_{koro}_{mask} (e.g. matoran_ta_pakari). */
    public String getVariantKey() {
        return getKoro().getTextureName() + "_" + getMask().getId();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_KORO,       Koro.TA.ordinal());
        builder.define(DATA_MASK,       Mask.PAKARI.ordinal());
        builder.define(DATA_PROFESSION, Profession.MERCHANT.ordinal());
        builder.define(DATA_MASK_COLOR, MatoranColor.RED.ordinal());
        builder.define(DATA_FEET_COLOR, MatoranColor.RED.ordinal());
    }

    /**
     * Freezes the matoran in place while it has an active trading player.
     * Placed at priority 1 so it overrides strolling and attack.
     */
    private class TradingFreezeGoal extends Goal {
        TradingFreezeGoal() {
            setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return EntityMatoran.this.getTradingPlayer() != null
                    && EntityMatoran.this.getTradingPlayer().isAlive();
        }

        @Override
        public void start() {
            EntityMatoran.this.getNavigation().stop();
        }

        @Override
        public void tick() {
            Player trader = EntityMatoran.this.getTradingPlayer();
            if (trader != null) {
                EntityMatoran.this.getNavigation().stop();
                EntityMatoran.this.getLookControl().setLookAt(trader, 30.0F, 30.0F);
            }
        }

        @Override
        public void stop() {
            EntityMatoran.this.setTradingPlayer(null);
        }
    }

    /**
     * Makes the Matoran seek a nearby unoccupied bed at night, sleep in it,
     * and wake at dawn. Trading and combat take priority.
     */
    private class BedSleepGoal extends Goal {
        private static final int SEARCH_RADIUS = 10;
        /** Ticks to wait between bed searches. Prevents excessive blockstate reads per tick. */
        private static final int SEARCH_INTERVAL = 120;
        private BlockPos targetBed = null;
        private int searchCooldown = 0;

        BedSleepGoal() {
            setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
        }

        private boolean isNight() {
            long t = EntityMatoran.this.level().getDayTime() % 24000L;
            return t >= 13000L && t < 23000L;
        }

        @Override
        public boolean canUse() {
            if (EntityMatoran.this.isSleeping()) return false;
            if (EntityMatoran.this.getTradingPlayer() != null) return false;
            if (!isNight()) {
                searchCooldown = 0; // reset so the first nighttime check searches immediately
                return false;
            }
            if (--searchCooldown > 0) return false;
            searchCooldown = SEARCH_INTERVAL;
            targetBed = findFreeBed();
            return targetBed != null;
        }

        @Override
        public boolean canContinueToUse() {
            return isNight()
                    && !EntityMatoran.this.isSleeping()
                    && targetBed != null
                    && EntityMatoran.this.getTradingPlayer() == null;
        }

        @Override
        public void start() {
            if (targetBed != null) {
                EntityMatoran.this.getNavigation().moveTo(
                        targetBed.getX() + 0.5, targetBed.getY(), targetBed.getZ() + 0.5, 1.0);
            }
        }

        @Override
        public void tick() {
            if (targetBed == null) return;
            double dx = EntityMatoran.this.getX() - (targetBed.getX() + 0.5);
            double dz = EntityMatoran.this.getZ() - (targetBed.getZ() + 0.5);
            if (dx * dx + dz * dz < 1.5 * 1.5) {
                EntityMatoran.this.startSleeping(targetBed);
            }
        }

        @Override
        public void stop() {
            if (EntityMatoran.this.isSleeping()) {
                EntityMatoran.this.stopSleeping();
            }
            targetBed = null;
        }

        /** Wake up when daytime arrives. */
        @Override
        public boolean requiresUpdateEveryTick() { return false; }

        private BlockPos findFreeBed() {
            BlockPos origin = EntityMatoran.this.blockPosition();
            Level lvl = EntityMatoran.this.level();
            for (BlockPos pos : BlockPos.betweenClosed(
                    origin.offset(-SEARCH_RADIUS, -3, -SEARCH_RADIUS),
                    origin.offset( SEARCH_RADIUS,  3,  SEARCH_RADIUS))) {
                var state = lvl.getBlockState(pos);
                if (state.getBlock() instanceof BedBlock
                        && state.getValue(BedBlock.PART) == BedPart.HEAD
                        && !state.getValue(BedBlock.OCCUPIED)) {
                    return pos.immutable();
                }
            }
            return null;
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TradingFreezeGoal());
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(4, new BedSleepGoal());
        this.goalSelector.addGoal(5, new ReturnToHomeGoal());
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D, 60));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        // Fight back when hurt — peaceful until attacked
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    /**
     * Navigates the Matoran back toward their home position when they wander too
     * far (beyond HOME_RETURN_THRESHOLD blocks). Takes lower priority than
     * trading and combat so it doesn't interrupt interactions.
     */
    private class ReturnToHomeGoal extends Goal {

        ReturnToHomeGoal() {
            setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (homePos == null) return false;
            if (tradingPlayer != null) return false;
            double distSq = distanceToSqr(Vec3.atBottomCenterOf(homePos));
            return distSq > HOME_RETURN_THRESHOLD * HOME_RETURN_THRESHOLD;
        }

        @Override
        public boolean canContinueToUse() {
            if (homePos == null || tradingPlayer != null) return false;
            double distSq = distanceToSqr(Vec3.atBottomCenterOf(homePos));
            return distSq > HOME_ARRIVE_THRESHOLD * HOME_ARRIVE_THRESHOLD
                    && getNavigation().isInProgress();
        }

        @Override
        public void start() {
            getNavigation().moveTo(
                    homePos.getX() + 0.5,
                    homePos.getY(),
                    homePos.getZ() + 0.5,
                    0.85);
        }

        @Override
        public void stop() {
            getNavigation().stop();
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D);   // Piglin-level — 2.5 hearts
    }

    /** Prevent natural despawn — matoran are persistent NPCs. */
    @Override
    public boolean requiresCustomPersistence() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            boolean moving = this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-5;
            if (moving != lastMoving || this.tickCount % 40 == 1) {
                lastMoving = moving;
                MatoranAnimator.sendMovementCommand(this);
            }
            if (!moving) {
                ambientAnimTimer++;
                // Fire an ambient animation every 80–200 ticks (~4–10 s) while idle
                if (ambientAnimTimer >= 80 + this.random.nextInt(120)) {
                    ambientAnimTimer = 0;
                    MatoranAnimator.sendAmbientCommand(this);
                }
            } else {
                ambientAnimTimer = 0;
            }
        }
    }

    /** Stop trading the moment this matoran acquires a combat target. */
    @Override
    public void setTarget(@Nullable LivingEntity target) {
        super.setTarget(target);
        if (target != null) {
            this.setTradingPlayer(null);
        }
    }

    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource source, float amount) {
        boolean hurt = super.hurtServer(serverLevel, source, amount);
        if (hurt) {
            // Resolve the attacker: prefer the indirect owner (e.g. shooter of an arrow)
            // but fall back to the direct entity (melee weapon, explosion, etc.).
            Entity attacker = source.getEntity() != null ? source.getEntity() : source.getDirectEntity();
            if (attacker instanceof LivingEntity living) {
                this.setTarget(living);
            }
        }
        return hurt;
    }

    @Override
    public boolean doHurtTarget(ServerLevel serverLevel, Entity target) {
        boolean hit = super.doHurtTarget(serverLevel, target);
        MatoranAnimator.sendAttackCommand(this);
        return hit;
    }

    /**
     * Masks that have converted geo + texture sets ready. Only these are assigned
     * during natural/structure spawning so every matoran shows a valid model.
     * Expand this list as new bbmodel batches are converted.
     */
    public static final Mask[] IMPLEMENTED_MASKS = {
        Mask.HAU, Mask.HUNA, Mask.KAKAMA, Mask.KAUKAU, Mask.MIRU, Mask.PAKARI
    };

    /**
     * Professions available to naturally-spawned / bred Matoran.
     * MASKMAKER is intentionally excluded — it is reserved for the one
     * dedicated mask-seller spawned by KoroSpawnHandler in each koro.
     */
    public static final Profession[] RANDOM_PROFESSIONS = {
        Profession.SMITH, Profession.MINER, Profession.DISC_CRAFTER,
        Profession.SCHOLAR, Profession.MERCHANT
    };

    /** Food items that trigger breeding (and speed up baby growth when fed to a child). */
    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.BREAD)
            || stack.is(Items.WHEAT)
            || stack.is(Items.CARROT)
            || stack.is(Items.POTATO)
            || stack.is(Items.APPLE);
    }

    /**
     * Produces a baby Matoran when two adults breed.
     *
     * Koro: randomly inherited from one parent.
     * Mask: blended from both parents' mask colors — cross-koro breeding produces
     *       unique intermediate hues.  If the blended result matches the child's
     *       body color, one of the parents' original accent colors is used instead
     *       so the mask is always visually distinct.
     * Feet: same logic applied independently to feet colors.
     * Profession/Mask: random from the implemented pool.
     *
     * NOTE: finalizeSpawn is called after this by vanilla, but it is told NOT to
     * overwrite these accent colors when the spawn reason is BREEDING.
     */
    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        var rand = level.getRandom();
        Koro childKoro = (otherParent instanceof EntityMatoran other && rand.nextBoolean())
                ? other.getKoro()
                : this.getKoro();
        MatoranColor bodyColor = MatoranColor.forKoro(childKoro);

        EntityMatoran child = new EntityMatoran(
                eastonium.mnogii.core.MnogiiEntityTypes.MATORAN.get(), level, childKoro);
        child.setMask(IMPLEMENTED_MASKS[rand.nextInt(IMPLEMENTED_MASKS.length)]);
        child.setProfession(RANDOM_PROFESSIONS[rand.nextInt(RANDOM_PROFESSIONS.length)]);

        if (otherParent instanceof EntityMatoran other) {
            // Blend accent colors — if blend collapses to body color, fall back to
            // the other parent's distinct accent to keep visuals interesting.
            MatoranColor maskBlend = MatoranColor.blend(this.getMaskColor(), other.getMaskColor());
            if (maskBlend == bodyColor) maskBlend = other.getMaskColor() != bodyColor
                    ? other.getMaskColor() : MatoranColor.randomAccent(childKoro, rand);
            child.setMaskColor(maskBlend);

            MatoranColor feetBlend = MatoranColor.blend(this.getFeetColor(), other.getFeetColor());
            if (feetBlend == bodyColor) feetBlend = other.getFeetColor() != bodyColor
                    ? other.getFeetColor() : MatoranColor.randomAccent(childKoro, rand);
            child.setFeetColor(feetBlend);
        } else {
            child.setMaskColor(MatoranColor.randomAccent(childKoro, rand));
            child.setFeetColor(MatoranColor.randomAccent(childKoro, rand));
        }
        return child;
    }

    @Override
    public net.minecraft.world.entity.SpawnGroupData finalizeSpawn(ServerLevelAccessor level, net.minecraft.world.DifficultyInstance difficulty, net.minecraft.world.entity.EntitySpawnReason reason, @Nullable net.minecraft.world.entity.SpawnGroupData spawnData) {
        if (reason == net.minecraft.world.entity.EntitySpawnReason.NATURAL) {
            // Infer Koro from biome for natural/ambient spawning (not used for KoroSpawnHandler
            // which sets koro/mask/profession directly via constructor).
            var biome = level.getBiome(this.blockPosition());
            if (biome.is(Biomes.BADLANDS) || biome.is(Biomes.WOODED_BADLANDS) || biome.is(Biomes.DESERT)) setKoro(Koro.TA);
            else if (biome.is(Biomes.WARM_OCEAN) || biome.is(Biomes.OCEAN) || biome.is(Biomes.BEACH)
                    || biome.is(Biomes.LUKEWARM_OCEAN) || biome.is(Biomes.DEEP_LUKEWARM_OCEAN)) setKoro(Koro.GA);
            else if (biome.is(Biomes.SAVANNA) || biome.is(Biomes.SAVANNA_PLATEAU) || biome.is(Biomes.WINDSWEPT_SAVANNA)) setKoro(Koro.PO);
            else if (biome.is(Biomes.LUSH_CAVES) || biome.is(Biomes.DRIPSTONE_CAVES) || biome.is(Biomes.DEEP_DARK) || biome.is(Biomes.WINDSWEPT_GRAVELLY_HILLS)) setKoro(Koro.ONU);
            else if (biome.is(Biomes.JUNGLE) || biome.is(Biomes.SPARSE_JUNGLE) || biome.is(Biomes.BAMBOO_JUNGLE)) setKoro(Koro.LE);
            else if (biome.is(Biomes.SNOWY_PLAINS) || biome.is(Biomes.ICE_SPIKES) || biome.is(Biomes.FROZEN_PEAKS) || biome.is(Biomes.SNOWY_TAIGA)) setKoro(Koro.KO);

            // Pick a random mask from only the implemented set (geo + textures exist).
            setMask(IMPLEMENTED_MASKS[level.getRandom().nextInt(IMPLEMENTED_MASKS.length)]);

            // Assign a random profession from the non-maskmaker pool.
            setProfession(RANDOM_PROFESSIONS[level.getRandom().nextInt(RANDOM_PROFESSIONS.length)]);
        }
        // BREEDING: getBreedOffspring() already set blended accent colors — preserve them.
        // All other spawn reasons get distinct accent colors guaranteed different from body.
        if (reason != net.minecraft.world.entity.EntitySpawnReason.BREEDING) {
            var rand = level.getRandom();
            setMaskColor(MatoranColor.randomAccent(getKoro(), rand));
            setFeetColor(MatoranColor.randomAccent(getKoro(), rand));
        }
        return super.finalizeSpawn(level, difficulty, reason, spawnData);
    }

    @Override
    public Component getDisplayName() {
        Component koroName       = Component.translatable("entity.mnogii.matoran." + getKoro().name().toLowerCase());
        Component professionName = Component.translatable("entity.mnogii.matoran.profession." + getProfession().getId());
        return Component.translatable("entity.mnogii.matoran.name_format", koroName, professionName);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // Adult fed with food → enter love mode for breeding.
        if (!this.isBaby() && this.isFood(stack) && this.canFallInLove()) {
            if (!this.level().isClientSide) {
                this.usePlayerItem(player, hand, stack);
                this.setInLove(player);
            }
            return this.level().isClientSide ? InteractionResult.CONSUME : InteractionResult.SUCCESS;
        }

        // Baby fed with food → accelerate growth (same mechanic as vanilla animals).
        if (this.isBaby() && this.isFood(stack)) {
            if (!this.level().isClientSide) {
                this.usePlayerItem(player, hand, stack);
                this.ageUp(AgeableMob.getSpeedUpSecondsWhenFeeding(-this.getAge()), true);
            }
            return this.level().isClientSide ? InteractionResult.CONSUME : InteractionResult.SUCCESS;
        }

        // Otherwise open the trading screen as normal.
        if (!this.level().isClientSide && !this.isBaby() && !this.getOffers().isEmpty()) {
            player.awardStat(Stats.TALKED_TO_VILLAGER);
            this.setTradingPlayer(player);
            this.openTradingScreen(player, this.getDisplayName(), this.getVillagerXp());
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    // ---- Merchant implementation ----

    @Override
    public void setTradingPlayer(@Nullable Player player) {
        this.tradingPlayer = player;
    }

    @Nullable
    @Override
    public Player getTradingPlayer() {
        return this.tradingPlayer;
    }

    @Override
    public MerchantOffers getOffers() {
        if (this.level().isClientSide) {
            throw new IllegalStateException("Cannot load Matoran offers on the client");
        }
        if (this.offers == null) {
            this.offers = new MerchantOffers();
            this.updateTrades();
        }
        return this.offers;
    }

    @Override
    public void overrideOffers(MerchantOffers offers) {
        this.offers = offers;
    }

    @Override
    public void notifyTrade(MerchantOffer offer) {
        offer.increaseUses();
        this.ambientSoundTime = -this.getAmbientSoundInterval();
        this.rewardTradeXp(offer);
    }

    protected void rewardTradeXp(MerchantOffer offer) {
        if (offer.shouldRewardExp() && this.level() != null) {
            int xp = 2 + this.random.nextInt(4);
            this.level().addFreshEntity(new ExperienceOrb(this.level(), this.getX(), this.getY() + 0.5, this.getZ(), xp));
        }
    }

    @Override
    public void notifyTradeUpdated(ItemStack stack) {
        if (!this.level().isClientSide && this.ambientSoundTime > -this.getAmbientSoundInterval() + 20) {
            this.ambientSoundTime = -this.getAmbientSoundInterval();
            this.playSound(stack.isEmpty() ? SoundEvents.VILLAGER_NO : SoundEvents.VILLAGER_YES, 1.0F, 1.0F);
        }
    }

    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int xp) {}

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    public SoundEvent getNotifyTradeSound() {
        return SoundEvents.VILLAGER_YES;
    }

    @Override
    public boolean isClientSide() {
        return this.level().isClientSide;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.isAlive() && player.canInteractWithEntity(this, 8.0);
    }

    private void updateTrades() {
        Koro koro = getKoro();
        MerchantOffers offers = this.getOffers();
        offers.clear();

        // ---- Base koro trades (every matoran regardless of profession) ----
        // Buy koro stone with emeralds
        offers.add(new MerchantOffer(new ItemCost(Items.EMERALD, 2), new ItemStack(koro.getKoroStone(), 4), 12, 1, 0.1F));
        // Smelt koro stone into protodermis ingot
        offers.add(new MerchantOffer(new ItemCost(koro.getKoroStone(), 8), new ItemStack(koro.getProtodermis(), 1), 8, 1, 0.1F));
        // Trade up to toa stone: protodermis + emerald
        offers.add(new MerchantOffer(new ItemCost(koro.getProtodermis(), 2), java.util.Optional.of(new ItemCost(Items.EMERALD, 1)), new ItemStack(koro.getToaStone(), 1), 4, 2, 0.1F));
        // Sell koro stone back for emeralds
        offers.add(new MerchantOffer(new ItemCost(koro.getKoroStone(), 4), new ItemStack(Items.EMERALD, 1), 12, 1, 0.1F));

        // ---- Profession-specific trades ----
        switch (getProfession()) {
            case MASKMAKER -> addMaskmakerTrades(offers, koro);
            case SMITH     -> addSmithTrades(offers, koro);
            case MINER     -> addMinerTrades(offers, koro);
            case DISC_CRAFTER -> addDiscCrafterTrades(offers, koro);
            case SCHOLAR   -> addScholarTrades(offers, koro);
            case MERCHANT  -> addMerchantTrades(offers, koro);
        }
    }

    /**
     * Maskmaker: sells only the koro's signature Toa mask at a premium price.
     * Exactly one Maskmaker is guaranteed per koro structure.
     */
    private void addMaskmakerTrades(MerchantOffers offers, Koro koro) {
        offers.add(new MerchantOffer(
                new ItemCost(Items.EMERALD, 12),
                new ItemStack(koro.getToaMask(), 1),
                4,    // limited stock
                2,    // villager XP per sale
                0.02F // minimal price inflation
        ));
    }

    /**
     * Smith: sells protodermis tools and weapons.
     */
    private void addSmithTrades(MerchantOffers offers, Koro koro) {
        offers.add(new MerchantOffer(new ItemCost(Items.EMERALD, 8),  new ItemStack(MnogiiItems.PROTODERMIS_SWORD.get(), 1),  4, 2, 0.05F));
        offers.add(new MerchantOffer(new ItemCost(Items.EMERALD, 6),  new ItemStack(MnogiiItems.PROTODERMIS_PICK.get(), 1),   4, 2, 0.05F));
        offers.add(new MerchantOffer(new ItemCost(Items.EMERALD, 6),  new ItemStack(MnogiiItems.PROTODERMIS_AXE.get(), 1),    4, 2, 0.05F));
        // Buy protodermis ingots
        offers.add(new MerchantOffer(new ItemCost(MnogiiItems.INGOT_PROTODERMIS.get(), 5), new ItemStack(Items.EMERALD, 1), 12, 1, 0.1F));
    }

    /**
     * Miner: sells raw materials — protodermis and protosteel ingots.
     */
    private void addMinerTrades(MerchantOffers offers, Koro koro) {
        // Buy ingots with emeralds
        offers.add(new MerchantOffer(new ItemCost(Items.EMERALD, 2), new ItemStack(MnogiiItems.INGOT_PROTODERMIS.get(), 2), 16, 1, 0.1F));
        offers.add(new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(MnogiiItems.INGOT_PROTOSTEEL.get(), 1),  8, 2, 0.1F));
        // Sell koro's extra item in bulk
        offers.add(new MerchantOffer(new ItemCost(Items.EMERALD, 1), new ItemStack(koro.getExtraItem(), 6), 16, 1, 0.05F));
        // Sell ingots back for emeralds
        offers.add(new MerchantOffer(new ItemCost(MnogiiItems.INGOT_PROTODERMIS.get(), 6), new ItemStack(Items.EMERALD, 1), 16, 1, 0.1F));
    }

    /**
     * Disc Crafter: sells koro-specific Kanoka disks and general discs.
     */
    private void addDiscCrafterTrades(MerchantOffers offers, Koro koro) {
        // Koro-specific disk — thematic
        offers.add(new MerchantOffer(new ItemCost(Items.EMERALD, 2), new ItemStack(koro.getKoroDisk(), 2), 12, 1, 0.1F));
        // General bamboo kanoka discs cheap
        offers.add(new MerchantOffer(new ItemCost(Items.EMERALD, 1), new ItemStack(MnogiiItems.KANOKA_BAMBOO.get(), 3), 16, 1, 0.05F));
        // Craft up: bamboo discs → proper kanoka disc
        offers.add(new MerchantOffer(new ItemCost(MnogiiItems.KANOKA_BAMBOO.get(), 4), new ItemStack(koro.getKoroDisk(), 1), 8, 2, 0.1F));
        // Sell extra item for emeralds
        offers.add(new MerchantOffer(new ItemCost(koro.getExtraItem(), 4), new ItemStack(Items.EMERALD, 1), 12, 1, 0.1F));
    }

    /**
     * Scholar: studies elemental lore; trades toa stones, Onu-Wahi stones, and special tools.
     */
    private void addScholarTrades(MerchantOffers offers, Koro koro) {
        // Element Swiper — exotic elemental tool
        offers.add(new MerchantOffer(new ItemCost(Items.EMERALD, 4), new ItemStack(MnogiiItems.ELEMENT_SWIPER.get(), 1), 6, 2, 0.05F));
        // Onu-Wahi stones — useful for mask forging
        offers.add(new MerchantOffer(new ItemCost(Items.EMERALD, 2), new ItemStack(MnogiiItems.ONU_WAHI_STONE.get(), 2), 12, 1, 0.1F));
        // Sell back Onu-Wahi stones for emeralds
        offers.add(new MerchantOffer(new ItemCost(MnogiiItems.ONU_WAHI_STONE.get(), 2), new ItemStack(Items.EMERALD, 1), 12, 1, 0.1F));
        // Extra toa stone trade: koro stone + protodermis → toa stone (alternate route)
        offers.add(new MerchantOffer(new ItemCost(koro.getKoroStone(), 6), java.util.Optional.of(new ItemCost(MnogiiItems.INGOT_PROTODERMIS.get(), 1)), new ItemStack(koro.getToaStone(), 1), 4, 2, 0.1F));
    }

    /**
     * Merchant: general goods dealer; trades crafting components and handy tools.
     */
    private void addMerchantTrades(MerchantOffers offers, Koro koro) {
        // Heatstone Lighter — handy tool
        offers.add(new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(MnogiiItems.HEATSTONE_LIGHTER.get(), 1), 4, 2, 0.05F));
        // Gears — crafting component
        offers.add(new MerchantOffer(new ItemCost(Items.EMERALD, 2), new ItemStack(MnogiiItems.GEAR.get(), 4), 16, 1, 0.05F));
        // Hammer
        offers.add(new MerchantOffer(new ItemCost(Items.EMERALD, 1), new ItemStack(MnogiiItems.HAMMER.get(), 1), 12, 1, 0.05F));
        // Sell gears back for emeralds
        offers.add(new MerchantOffer(new ItemCost(MnogiiItems.GEAR.get(), 3), new ItemStack(Items.EMERALD, 1), 16, 1, 0.1F));
        // Koro's extra item — themed goods
        offers.add(new MerchantOffer(new ItemCost(Items.EMERALD, 1), new ItemStack(koro.getExtraItem(), 4), 16, 1, 0.05F));
    }

    @Override
    public void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("Koro",       this.entityData.get(DATA_KORO));
        output.putInt("Mask",       this.entityData.get(DATA_MASK));
        output.putInt("Profession", this.entityData.get(DATA_PROFESSION));
        output.putInt("MaskColor",  this.entityData.get(DATA_MASK_COLOR));
        output.putInt("FeetColor",  this.entityData.get(DATA_FEET_COLOR));
        if (homePos != null) {
            output.putInt("HomePosX", homePos.getX());
            output.putInt("HomePosY", homePos.getY());
            output.putInt("HomePosZ", homePos.getZ());
        }
        if (!this.level().isClientSide) {
            MerchantOffers offers = this.getOffers();
            if (!offers.isEmpty()) {
                output.store("Offers", MerchantOffers.CODEC, offers);
            }
        }
    }

    @Override
    public void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.entityData.set(DATA_KORO,       input.getIntOr("Koro",       Koro.TA.ordinal()));
        this.entityData.set(DATA_MASK,       input.getIntOr("Mask",       Mask.PAKARI.ordinal()));
        this.entityData.set(DATA_PROFESSION, input.getIntOr("Profession", Profession.MERCHANT.ordinal()));
        this.entityData.set(DATA_MASK_COLOR, input.getIntOr("MaskColor",  MatoranColor.RED.ordinal()));
        this.entityData.set(DATA_FEET_COLOR, input.getIntOr("FeetColor",  MatoranColor.RED.ordinal()));
        this.offers = input.read("Offers", MerchantOffers.CODEC).orElse(null);
        // Restore home pos after load (Y uses MIN_VALUE as "not saved" sentinel).
        int savedY = input.getIntOr("HomePosY", Integer.MIN_VALUE);
        if (savedY != Integer.MIN_VALUE) {
            setHomePos(new BlockPos(
                    input.getIntOr("HomePosX", 0),
                    savedY,
                    input.getIntOr("HomePosZ", 0)));
        }
    }

    @Override
    public void die(DamageSource source) {
        this.setTradingPlayer(null);
        // Drop the mask the matoran was wearing
        if (!this.level().isClientSide) {
            this.spawnAtLocation((ServerLevel) this.level(), new ItemStack(getMask().getDropItem()));
        }
        super.die(source);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.getTradingPlayer() != null ? SoundEvents.VILLAGER_TRADE : SoundEvents.VILLAGER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.VILLAGER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.VILLAGER_DEATH;
    }
}
