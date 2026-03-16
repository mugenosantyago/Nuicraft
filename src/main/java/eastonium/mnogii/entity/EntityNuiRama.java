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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.EnumSet;

/**
 * Nui-Rama — flying insectoid Rahi. Always hostile to players.
 * Idles 3–6 blocks above terrain so it stays within player reach distance.
 */
public class EntityNuiRama extends PathfinderMob {

    private String lastAnimState = "";

    public EntityNuiRama(EntityType<? extends PathfinderMob> type, Level level) {
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
        this.goalSelector.addGoal(1, new FlyingMeleeGoal(this, 0.10));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        // Keep entity 3–6 blocks above terrain — low enough to be within player attack reach
        this.goalSelector.addGoal(2, new FlyLowGoal(this, 3, 6));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomFlyingGoal(this, 0.6));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
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
            boolean moving = this.getDeltaMovement().lengthSqr() > 1.0E-5;
            String animState = moving ? "walk" : "idle";
            if (!animState.equals(lastAnimState)) {
                lastAnimState = animState;
                NuiRamaAnimator.sendMovementCommand(this);
            }
        }
    }

    /**
     * Navigates to the target's eye height (an air block) rather than feet (ground block).
     * FlyingPathNavigation fails to build paths into solid blocks, so navigating to eye
     * height lets it descend to and hover near ground-level targets correctly.
     */
    static class FlyingMeleeGoal extends Goal {
        private static final double HOVER_DIST        = 3.0;
        private static final double ATTACK_DIST       = 2.5;
        private static final int    BASE_ATTACK_INTERVAL = 40;

        private final EntityNuiRama mob;
        private final double chaseSpeed;
        private int attackCooldown;

        FlyingMeleeGoal(EntityNuiRama mob, double chaseSpeed) {
            this.mob = mob;
            this.chaseSpeed = chaseSpeed;
            setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override public boolean canUse() {
            LivingEntity t = mob.getTarget();
            return t != null && t.isAlive();
        }

        @Override public boolean canContinueToUse() {
            LivingEntity t = mob.getTarget();
            return t != null && t.isAlive() && mob.distanceTo(t) < 24.0;
        }

        @Override public void start() { attackCooldown = BASE_ATTACK_INTERVAL; }
        @Override public void stop()  { mob.getNavigation().stop(); }

        @Override
        public void tick() {
            LivingEntity target = mob.getTarget();
            if (target == null) return;

            mob.getLookControl().setLookAt(target, 30f, 30f);

            double dist = mob.position().distanceTo(
                    target.position().add(0, target.getBbHeight() * 0.5, 0));

            if (dist > HOVER_DIST) {
                mob.getNavigation().moveTo(
                        target.getX(), target.getEyeY(), target.getZ(), chaseSpeed);
            } else {
                mob.getNavigation().stop();
            }

            if (--attackCooldown <= 0 && dist <= ATTACK_DIST) {
                attackCooldown = BASE_ATTACK_INTERVAL + mob.random.nextInt(10);
                if (mob.level() instanceof net.minecraft.server.level.ServerLevel sl) {
                    mob.doHurtTarget(sl, target);
                }
            }
        }
    }

    /**
     * Keeps the entity 3–6 blocks above terrain so it stays within player attack reach.
     * Does not run during combat (FlyingMeleeGoal at priority 1 preempts this at priority 2).
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
            // Trigger if too high OR too low relative to the target band
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
