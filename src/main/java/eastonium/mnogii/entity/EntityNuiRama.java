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
        this.goalSelector.addGoal(1, new FlyingMeleeGoal(this, 1.2));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new FlyHighGoal(this, 12, 28));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomFlyingGoal(this, 0.8));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.ATTACK_DAMAGE, 6.0)
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
     * Flies directly at the target using MoveControl rather than pathfinding.
     * MeleeAttackGoal uses FlyingPathNavigation which silently fails for ground targets,
     * freezing the entity and causing server/client hitbox desyncs (appears invincible).
     * MoveControl steers smoothly every tick with no path-building failure mode.
     */
    static class FlyingMeleeGoal extends Goal {
        private static final double ATTACK_RANGE_SQ = 3.5 * 3.5;
        private static final int BASE_ATTACK_INTERVAL = 20;

        private final EntityNuiRama mob;
        private final double speed;
        private int attackCooldown;

        FlyingMeleeGoal(EntityNuiRama mob, double speed) {
            this.mob = mob;
            this.speed = speed;
            setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = mob.getTarget();
            return target != null && target.isAlive();
        }

        @Override
        public boolean canContinueToUse() {
            LivingEntity target = mob.getTarget();
            return target != null && target.isAlive() && mob.distanceTo(target) < 24.0;
        }

        @Override
        public void start() {
            attackCooldown = BASE_ATTACK_INTERVAL;
        }

        @Override
        public void stop() {
            mob.getNavigation().stop();
        }

        @Override
        public void tick() {
            LivingEntity target = mob.getTarget();
            if (target == null) return;

            mob.getLookControl().setLookAt(target, 30f, 30f);
            mob.getMoveControl().setWantedPosition(target.getX(), target.getEyeY(), target.getZ(), speed);

            if (--attackCooldown <= 0 && mob.distanceToSqr(target) <= ATTACK_RANGE_SQ) {
                attackCooldown = BASE_ATTACK_INTERVAL + mob.random.nextInt(10);
                if (mob.level() instanceof net.minecraft.server.level.ServerLevel sl) {
                    mob.doHurtTarget(sl, target);
                }
            }
        }
    }

    /**
     * Keeps the Nui-Rama flying above the terrain. Activates when the entity
     * dips below the minimum altitude and navigates it back up.
     */
    static class FlyHighGoal extends Goal {
        private final PathfinderMob mob;
        private final int minAlt;
        private final int maxAlt;

        FlyHighGoal(PathfinderMob mob, int minAlt, int maxAlt) {
            this.mob = mob;
            this.minAlt = minAlt;
            this.maxAlt = maxAlt;
            setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (mob.getTarget() != null) return false;
            if (mob.getRandom().nextInt(15) != 0) return false;
            BlockPos surface = mob.level().getHeightmapPos(
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, mob.blockPosition());
            return mob.getY() < surface.getY() + minAlt;
        }

        @Override
        public boolean canContinueToUse() {
            return mob.getTarget() == null && mob.getNavigation().isInProgress();
        }

        @Override
        public void start() {
            BlockPos surface = mob.level().getHeightmapPos(
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, mob.blockPosition());
            double targetY = surface.getY() + minAlt + mob.getRandom().nextInt(maxAlt - minAlt);
            double dx = (mob.getRandom().nextDouble() - 0.5) * 20;
            double dz = (mob.getRandom().nextDouble() - 0.5) * 20;
            mob.getNavigation().moveTo(mob.getX() + dx, targetY, mob.getZ() + dz, 1.0);
        }
    }
}
