package eastonium.mnogii.entity;

import eastonium.mnogii.client.animator.NuiRamaAnimator;
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

import java.util.EnumSet;

/**
 * Nui-Rama — flying insectoid Rahi. Always hostile to players.
 *
 * Movement split:
 *  - Combat  → FlyingMeleeGoal sets deltaMovement directly in 3D each tick.
 *              travel() just applies it and drags. FlyingPathNavigation is NOT
 *              used for combat because it cannot build paths from altitude to
 *              ground targets, which freezes the entity (apparent invincibility).
 *  - Wandering → super.travel() / FlyingPathNavigation / FlyingMoveControl as normal.
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
        this.goalSelector.addGoal(3, new WaterAvoidingRandomFlyingGoal(this, 0.8));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.FLYING_SPEED, 0.17)
                .add(Attributes.MOVEMENT_SPEED, 0.17)
                .add(Attributes.FOLLOW_RANGE, 12.0);
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
     * Chases the target by computing a 3D unit vector toward its center and adding
     * it to deltaMovement each tick. This lets the entity descend from altitude to
     * ground-level players — something FlyingMoveControl cannot do because vanilla
     * travel() only converts zza into horizontal movement and ignores pitch entirely.
     */
    static class FlyingMeleeGoal extends Goal {
        private static final double HOVER_DIST    = 3.0;
        private static final double ATTACK_DIST   = 2.5;
        private static final int BASE_ATTACK_INTERVAL = 40;

        private final EntityNuiRama mob;
        private final double chaseSpeed;
        private int attackCooldown;

        FlyingMeleeGoal(EntityNuiRama mob, double chaseSpeed) {
            this.mob = mob;
            this.chaseSpeed = chaseSpeed;
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

            // Measure distance to target's center
            double dist = mob.position().distanceTo(
                    target.position().add(0, target.getBbHeight() * 0.5, 0));

            if (dist > HOVER_DIST) {
                // Navigate to the target's eye height — this is an air block, so
                // FlyingPathNavigation can actually build the path and descend to it.
                // Navigating to the feet (ground) breaks path-building for flying mobs.
                mob.getNavigation().moveTo(
                        target.getX(), target.getEyeY(), target.getZ(), chaseSpeed);
            } else {
                // Close enough — stop navigating so the entity holds position
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
}
