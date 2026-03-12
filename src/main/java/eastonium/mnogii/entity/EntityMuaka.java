package eastonium.mnogii.entity;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.client.animator.MuakaAnimator;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.Structure;

/**
 * Muaka: large tiger Rahi. Strength comparable to a wither skeleton.
 */
public class EntityMuaka extends Monster {

    private boolean lastMoving = false;

    public EntityMuaka(EntityType<? extends EntityMuaka> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            boolean moving = this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-5;
            if (moving != lastMoving || this.tickCount % 40 == 1) {
                lastMoving = moving;
                MuakaAnimator.sendMovementCommand(this);
            }
        }
    }

    @Override
    public boolean doHurtTarget(net.minecraft.server.level.ServerLevel level, net.minecraft.world.entity.Entity target) {
        boolean hit = super.doHurtTarget(level, target);
        if (hit) MuakaAnimator.sendAttackCommand(this);
        return hit;
    }

    private static final TagKey<Structure> KORO_STRUCTURES =
        TagKey.create(net.minecraft.core.registries.Registries.STRUCTURE,
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "koro"));

    public static boolean checkKoroSpawnRules(EntityType<? extends Monster> type,
            ServerLevelAccessor level, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        if (!Monster.checkMonsterSpawnRules(type, level, reason, pos, random)) return false;
        if (level instanceof ServerLevel serverLevel) {
            return serverLevel.structureManager().getStructureWithPieceAt(pos, KORO_STRUCTURES).isValid() == false;
        }
        return true;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D);
    }
}
