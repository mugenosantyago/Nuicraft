package eastonium.nuicraft.entity;

import eastonium.nuicraft.core.NuiCraftBlocks;
import eastonium.nuicraft.core.NuiCraftItems;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import eastonium.nuicraft.client.animator.MatoranAnimator;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
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
import org.jetbrains.annotations.Nullable;

/**
 * Matoran - villager-like NPC that trades items relative to their Koro.
 * Ta-Koro Matoran trade fire/desert items, Ga-Koro trade water items, etc.
 */
public class EntityMatoran extends PathfinderMob implements Merchant {

    /** Kanohi Mata mask variant. Model/texture path uses getId() (e.g. pakari, hau). */
    public enum Mask {
        HAU("hau")      { @Override public Item getDropItem() { return NuiCraftItems.MASK_MATA_HAU.get(); } },
        KAUKAU("kaukau"){ @Override public Item getDropItem() { return NuiCraftItems.MASK_MATA_KAUKAU.get(); } },
        MIRU("miru")    { @Override public Item getDropItem() { return NuiCraftItems.MASK_MATA_MIRU.get(); } },
        KAKAMA("kakama"){ @Override public Item getDropItem() { return NuiCraftItems.MASK_MATA_KAKAMA.get(); } },
        PAKARI("pakari"){ @Override public Item getDropItem() { return NuiCraftItems.MASK_MATA_PAKARI.get(); } },
        AKAKU("akaku")  { @Override public Item getDropItem() { return NuiCraftItems.MASK_MATA_AKAKU.get(); } },
        HUNA("huna")    { @Override public Item getDropItem() { return NuiCraftItems.MASK_MATA_HUNA.get(); } },
        MAHIKI("mahiki"){ @Override public Item getDropItem() { return NuiCraftItems.MASK_MATA_MAHIKI.get(); } },
        MATATU("matatu"){ @Override public Item getDropItem() { return NuiCraftItems.MASK_MATA_MATATU.get(); } },
        KOMAU("komau")  { @Override public Item getDropItem() { return NuiCraftItems.MASK_MATA_KOMAU.get(); } },
        RARU("raru")    { @Override public Item getDropItem() { return NuiCraftItems.MASK_MATA_RARU.get(); } },
        RURU("ruru")    { @Override public Item getDropItem() { return NuiCraftItems.MASK_MATA_RURU.get(); } };

        private final String id;
        Mask(String id) { this.id = id; }
        public String getId() { return id; }
        /** The mask item dropped when this matoran is killed. */
        public abstract Item getDropItem();
    }

    public enum Koro {
        /** Ta-Koro (red) - fire/desert */
        TA("matoran_ta", NuiCraftBlocks.TA_KORO_STONE.get(), NuiCraftItems.INGOT_PROTODERMIS.get(), NuiCraftItems.FIRE_TOA_STONE.get(), Items.BLAZE_POWDER),
        /** Ga-Koro (blue) - water */
        GA("matoran_ga", NuiCraftBlocks.GA_KORO_STONE.get(), NuiCraftItems.INGOT_PROTODERMIS.get(), NuiCraftItems.WATER_TOA_STONE.get(), Items.PRISMARINE_SHARD),
        /** Po-Koro (brown) - rock/stone */
        PO("matoran_po", NuiCraftBlocks.PO_KORO_STONE.get(), NuiCraftItems.INGOT_PROTODERMIS.get(), NuiCraftItems.ROCK_TOA_STONE.get(), Items.SMOOTH_STONE),
        /** Onu-Koro (black) - earth */
        ONU("matoran_onu", NuiCraftBlocks.ONU_KORO_STONE.get(), NuiCraftItems.INGOT_PROTODERMIS.get(), NuiCraftItems.EARTH_TOA_STONE.get(), Items.COAL),
        /** Le-Koro (green) - air */
        LE("matoran_le", NuiCraftBlocks.LE_KORO_STONE.get(), NuiCraftItems.INGOT_PROTODERMIS.get(), NuiCraftItems.AIR_TOA_STONE.get(), Items.FEATHER),
        /** Ko-Koro (white) - ice */
        KO("matoran_ko", NuiCraftBlocks.KO_KORO_STONE.get(), NuiCraftItems.INGOT_PROTODERMIS.get(), NuiCraftItems.ICE_TOA_STONE.get(), Items.SNOWBALL);

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
    }

    private static final EntityDataAccessor<Integer> DATA_KORO = SynchedEntityData.defineId(EntityMatoran.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_MASK = SynchedEntityData.defineId(EntityMatoran.class, EntityDataSerializers.INT);

    @Nullable
    private Player tradingPlayer;
    @Nullable
    private MerchantOffers offers;

    public EntityMatoran(EntityType<? extends PathfinderMob> type, Level level) {
        this(type, level, Koro.TA);
    }

    public EntityMatoran(EntityType<? extends PathfinderMob> type, Level level, Koro koro) {
        this(type, level, koro, Mask.PAKARI);
    }

    public EntityMatoran(EntityType<? extends PathfinderMob> type, Level level, Koro koro, Mask mask) {
        super(type, level);
        this.entityData.set(DATA_KORO, koro.ordinal());
        this.entityData.set(DATA_MASK, mask.ordinal());
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

    /** Variant key for model/texture: matoran_{koro}_{mask} (e.g. matoran_ta_pakari). */
    public String getVariantKey() {
        return getKoro().getTextureName() + "_" + getMask().getId();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_KORO, Koro.TA.ordinal());
        builder.define(DATA_MASK, Mask.PAKARI.ordinal());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        // Fight back when hurt — peaceful until attacked
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);   // 1 heart — small but feisty
    }

    /** Prevent natural despawn — matoran are persistent NPCs. */
    @Override
    public boolean requiresCustomPersistence() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        // Dispatch idle animation loop from server every second so AzureLib keeps it running.
        if (!this.level().isClientSide && this.tickCount % 20 == 0) {
            MatoranAnimator.sendIdleCommand(this);
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

    /**
     * Masks that have converted geo + texture sets ready. Only these are assigned
     * during natural/structure spawning so every matoran shows a valid model.
     * Expand this list as new bbmodel batches are converted.
     */
    private static final Mask[] IMPLEMENTED_MASKS = {
        Mask.HAU, Mask.HUNA, Mask.KAKAMA, Mask.KAUKAU, Mask.MIRU, Mask.PAKARI
    };

    @Override
    public net.minecraft.world.entity.SpawnGroupData finalizeSpawn(ServerLevelAccessor level, net.minecraft.world.DifficultyInstance difficulty, net.minecraft.world.entity.EntitySpawnReason reason, @Nullable net.minecraft.world.entity.SpawnGroupData spawnData) {
        if (spawnData == null && reason == net.minecraft.world.entity.EntitySpawnReason.NATURAL) {
            // Infer Koro from biome so all matoran in a structure share the same color.
            var biome = level.getBiome(this.blockPosition());
            if (biome.is(Biomes.BADLANDS) || biome.is(Biomes.WOODED_BADLANDS) || biome.is(Biomes.DESERT)) setKoro(Koro.TA);
            else if (biome.is(Biomes.WARM_OCEAN) || biome.is(Biomes.OCEAN) || biome.is(Biomes.BEACH)) setKoro(Koro.GA);
            else if (biome.is(Biomes.SAVANNA) || biome.is(Biomes.SAVANNA_PLATEAU) || biome.is(Biomes.WINDSWEPT_SAVANNA)) setKoro(Koro.PO);
            else if (biome.is(Biomes.LUSH_CAVES) || biome.is(Biomes.DRIPSTONE_CAVES) || biome.is(Biomes.DEEP_DARK) || biome.is(Biomes.WINDSWEPT_GRAVELLY_HILLS)) setKoro(Koro.ONU);
            else if (biome.is(Biomes.JUNGLE) || biome.is(Biomes.SPARSE_JUNGLE) || biome.is(Biomes.BAMBOO_JUNGLE)) setKoro(Koro.LE);
            else if (biome.is(Biomes.SNOWY_PLAINS) || biome.is(Biomes.ICE_SPIKES) || biome.is(Biomes.FROZEN_PEAKS) || biome.is(Biomes.SNOWY_TAIGA)) setKoro(Koro.KO);
            // Pick a random mask from only the implemented set (geo + textures exist).
            setMask(IMPLEMENTED_MASKS[level.getRandom().nextInt(IMPLEMENTED_MASKS.length)]);
        }
        return super.finalizeSpawn(level, difficulty, reason, spawnData);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("entity.nuicraft.matoran." + getKoro().name().toLowerCase());
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.level().isClientSide && !this.getOffers().isEmpty()) {
            player.awardStat(Stats.TALKED_TO_VILLAGER);
            this.setTradingPlayer(player);
            this.openTradingScreen(player, this.getDisplayName(), this.getVillagerXp());
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
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
        return this.getTradingPlayer() == player && this.isAlive() && player.canInteractWithEntity(this, 4.0);
    }

    private void updateTrades() {
        Koro koro = getKoro();
        MerchantOffers offers = this.getOffers();
        offers.clear();

        // Koro stone: emeralds -> koro stone
        offers.add(new MerchantOffer(new ItemCost(Items.EMERALD, 2), new ItemStack(koro.getKoroStone(), 4), 12, 1, 0.1F));
        // Protodermis: koro stone -> protodermis ingot
        offers.add(new MerchantOffer(new ItemCost(koro.getKoroStone(), 8), new ItemStack(koro.getProtodermis(), 1), 8, 1, 0.1F));
        // Toa stone: protodermis + emerald -> toa stone
        offers.add(new MerchantOffer(new ItemCost(koro.getProtodermis(), 2), java.util.Optional.of(new ItemCost(Items.EMERALD, 1)), new ItemStack(koro.getToaStone(), 1), 4, 2, 0.1F));
        // Extra item: emeralds -> koro-themed item
        offers.add(new MerchantOffer(new ItemCost(Items.EMERALD, 1), new ItemStack(koro.getExtraItem(), 4), 16, 1, 0.05F));
        // Reverse: koro stone -> emeralds
        offers.add(new MerchantOffer(new ItemCost(koro.getKoroStone(), 4), new ItemStack(Items.EMERALD, 1), 12, 1, 0.1F));
    }

    @Override
    public void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("Koro", this.entityData.get(DATA_KORO));
        output.putInt("Mask", this.entityData.get(DATA_MASK));
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
        this.entityData.set(DATA_KORO, input.getIntOr("Koro", Koro.TA.ordinal()));
        this.entityData.set(DATA_MASK, input.getIntOr("Mask", Mask.PAKARI.ordinal()));
        this.offers = input.read("Offers", MerchantOffers.CODEC).orElse(null);
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
