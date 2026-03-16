package eastonium.mnogii.entity;

import eastonium.mnogii.client.animator.NuiRamaAnimator;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.EnumSet;

/**
 * Nui-Rama — flying insectoid Rahi. Always hostile to players.
 * All movement goes through navigation / FlyingMoveControl — never direct
 * setDeltaMovement() — so client-server positions stay in sync.
 */
public class EntityNuiRama extends Monster {

    private boolean lastMoving = false;

    public EntityNuiRama(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.setNoGravity(true);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation nav = new FlyingPathNavigation(this, level);
        nav.setCanOpenDoors(false);
        nav.setCanFloat(true);
        return nav;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new FlyLowGoal(this, 2, 5));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomFlyingGoal(this, 0.6));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.FLYING_SPEED, 0.17)
                .add(Attributes.MOVEMENT_SPEED, 0.17)
                .add(Attributes.FOLLOW_RANGE, 16.0);
    }

    @Override
    public boolean doHurtTarget(net.minecraft.server.level.ServerLevel level, Entity target) {
        boolean hit = super.doHurtTarget(level, target);
        if (hit) NuiRamaAnimator.sendAttackCommand(this);
        return hit;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            boolean moving = this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-5;
            if (moving != lastMoving || this.tickCount % 40 == 1) {
                lastMoving = moving;
                NuiRamaAnimator.sendMovementCommand(this);
            }
        }
    }

    /**
     * Keeps the entity 2–5 blocks above terrain so it stays within player reach.
     * Uses navigation.moveTo() (not setDeltaMovement) so positions sync correctly.
     * Same structure as Gukko's FlyHighGoal, but with lower altitude band.
     */
    static class FlyLowGoal extends Goal {
        private final PathfinderMob mob;
        private final int minAlt;
        private final int maxAlt;

        FlyLowGoal(PathfinderMob mob, int minAlt, int maxAlt) {
            this.mob = mob;
            this.minAlt = minAlt;
            this.maxAlt = maxAlt;
            setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (mob.getTarget() != null) return false;
            if (mob.getRandom().nextInt(10) != 0) return false;
            BlockPos surface = mob.level().getHeightmapPos(
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, mob.blockPosition());
            int surfaceY = surface.getY();
            return mob.getY() > surfaceY + maxAlt || mob.getY() < surfaceY + minAlt;
        }

        @Override
        public boolean canContinueToUse() {
            return mob.getTarget() == null && mob.getNavigation().isInProgress();
        }

        @Override
        public void start() {
            BlockPos surface = mob.level().getHeightmapPos(
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, mob.blockPosition());
            double targetY = surface.getY() + minAlt
                    + mob.getRandom().nextInt(Math.max(1, maxAlt - minAlt));
            double dx = (mob.getRandom().nextDouble() - 0.5) * 8;
            double dz = (mob.getRandom().nextDouble() - 0.5) * 8;
            mob.getNavigation().moveTo(mob.getX() + dx, targetY, mob.getZ() + dz, 0.8);
        }
    }
}
