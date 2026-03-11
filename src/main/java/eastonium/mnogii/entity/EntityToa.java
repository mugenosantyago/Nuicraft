package eastonium.mnogii.entity;

import eastonium.mnogii.client.animator.ToaAnimator;
import eastonium.mnogii.core.MnogiiItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;

/**
 * Toa - NPC that spawns once per corresponding Koro-themed biome.
 * Variant determines texture and spawn biome (Tahu=Ta-Koro/fire, Gali=Ga-Koro/water, etc.).
 */
public class EntityToa extends PathfinderMob {

    public enum Variant {
        //                texture          maskBone   toaStone                         signatureMask
        TAHU  ("toa_tahu",   "Hau",    () -> MnogiiItems.FIRE_TOA_STONE.get(),  () -> MnogiiItems.MASK_MATA_HAU.get()),
        GALI  ("toa_gali",   "Kaukau", () -> MnogiiItems.WATER_TOA_STONE.get(), () -> MnogiiItems.MASK_MATA_KAUKAU.get()),
        LEWA  ("toa_lewa",   "Miru",   () -> MnogiiItems.AIR_TOA_STONE.get(),   () -> MnogiiItems.MASK_MATA_MIRU.get()),
        ONUA  ("toa_onua",   "Pakari", () -> MnogiiItems.EARTH_TOA_STONE.get(), () -> MnogiiItems.MASK_MATA_PAKARI.get()),
        POHATU("toa_pohatu", "Kakama", () -> MnogiiItems.ROCK_TOA_STONE.get(),  () -> MnogiiItems.MASK_MATA_KAKAMA.get()),
        KOPAKA("toa_kopaka", "Akaku",  () -> MnogiiItems.ICE_TOA_STONE.get(),   () -> MnogiiItems.MASK_MATA_AKAKU.get());

        private final String textureName;
        private final String maskBone;
        private final java.util.function.Supplier<Item> toaStoneSupplier;
        private final java.util.function.Supplier<Item> signatureMaskSupplier;

        Variant(String textureName, String maskBone,
                java.util.function.Supplier<Item> toaStoneSupplier,
                java.util.function.Supplier<Item> signatureMaskSupplier) {
            this.textureName           = textureName;
            this.maskBone              = maskBone;
            this.toaStoneSupplier      = toaStoneSupplier;
            this.signatureMaskSupplier = signatureMaskSupplier;
        }

        public String getTextureName()  { return textureName; }
        public String getMaskBone()     { return maskBone; }
        public Item   getToaStone()     { return toaStoneSupplier.get(); }
        public Item   getSignatureMask(){ return signatureMaskSupplier.get(); }
    }

    private final Variant variant;
    private boolean lastMoving = false;

    public EntityToa(EntityType<? extends PathfinderMob> type, Level level, Variant variant) {
        super(type, level);
        this.variant = variant;
    }

    public Variant getVariant() {
        return variant;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.5D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 10.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource source, float amount) {
        boolean hurt = super.hurtServer(serverLevel, source, amount);
        if (hurt && source.getEntity() instanceof LivingEntity attacker
                && serverLevel.getDifficulty() != Difficulty.PEACEFUL) {
            this.setTarget(attacker);
        }
        return hurt;
    }

    @Override
    public boolean doHurtTarget(ServerLevel serverLevel, Entity target) {
        boolean hit = super.doHurtTarget(serverLevel, target);
        ToaAnimator.sendAttackCommand(this);
        return hit;
    }

    /**
     * Spawn check that prevents more than one Toa of any variant within 160 blocks.
     * Used by MnogiiSpawnPlacements for all Toa entity types.
     */
    public static boolean checkToaSpawnRules(EntityType<? extends Mob> type,
                                             ServerLevelAccessor level,
                                             EntitySpawnReason reason,
                                             BlockPos pos,
                                             RandomSource random) {
        if (!PathfinderMob.checkMobSpawnRules(type, level, reason, pos, random)) return false;
        if (level instanceof net.minecraft.server.level.ServerLevel serverLevel) {
            return serverLevel.getEntitiesOfClass(EntityToa.class,
                    new AABB(pos).inflate(160)).isEmpty();
        }
        return true;
    }

    @Override
    public boolean requiresCustomPersistence() {
        return true;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 300.0D)      // Wither-tier health
                .add(Attributes.MOVEMENT_SPEED, 0.6D)    // Fast when provoked
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.ATTACK_DAMAGE, 15.0D)    // Wither-level — 7.5 hearts per hit
                .add(Attributes.ARMOR, 4.0D);            // Wither natural armour
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.level().isClientSide) return InteractionResult.SUCCESS;

        ItemStack held = player.getItemInHand(hand);
        if (!held.is(this.variant.getToaStone())) {
            return super.mobInteract(player, hand);
        }

        // Consume one toa stone
        held.shrink(1);

        // Build the enchanted signature mask
        ItemStack mask = new ItemStack(this.variant.getSignatureMask());
        try {
            Holder<Enchantment> mending = this.level().registryAccess()
                    .lookupOrThrow(Registries.ENCHANTMENT)
                    .getOrThrow(Enchantments.MENDING);
            mask.enchant(mending, 1);
        } catch (Exception ignored) { /* mending not available — give unenchanted */ }

        giveOrDrop(player, mask);

        // Full protodermis tool + weapon set
        for (Item tool : new Item[]{
                MnogiiItems.PROTODERMIS_SWORD.get(),
                MnogiiItems.PROTODERMIS_PICK.get(),
                MnogiiItems.PROTODERMIS_AXE.get(),
                MnogiiItems.PROTODERMIS_SHOVEL.get(),
                MnogiiItems.PROTODERMIS_SCYTHE.get()
        }) {
            giveOrDrop(player, new ItemStack(tool));
        }

        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.VILLAGER_YES, SoundSource.NEUTRAL, 1.0F, 1.0F);

        return InteractionResult.CONSUME;
    }

    private void giveOrDrop(Player player, ItemStack stack) {
        if (!player.getInventory().add(stack)) {
            player.drop(stack, false);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            boolean moving = this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-5;
            if (moving != lastMoving) {
                lastMoving = moving;
                ToaAnimator.sendMovementCommand(this);
            }
        }
    }
}
