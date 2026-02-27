package eastonium.nuicraft.entity;

import eastonium.nuicraft.client.animator.ToaAnimator;
import eastonium.nuicraft.core.NuiCraftItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
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
        TAHU("toa_tahu",   "Hau"),
        GALI("toa_gali",   "Kaukau"),
        LEWA("toa_lewa",   "Miru"),
        ONUA("toa_onua",   "Pakari"),
        POHATU("toa_pohatu", "Kakama"),
        KOPAKA("toa_kopaka", "Akaku");

        private final String textureName;
        private final String maskBone;

        Variant(String textureName, String maskBone) {
            this.textureName = textureName;
            this.maskBone = maskBone;
        }

        public String getTextureName() { return textureName; }
        public String getMaskBone()    { return maskBone; }
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
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.5D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 10.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    /**
     * Spawn check that prevents more than one Toa of any variant within 160 blocks.
     * Used by NuiCraftSpawnPlacements for all Toa entity types.
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

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.FOLLOW_RANGE, 20.0D);
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
