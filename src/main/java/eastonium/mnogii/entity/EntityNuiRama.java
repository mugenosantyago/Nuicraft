package eastonium.mnogii.entity;

import eastonium.mnogii.client.animator.NuiRamaAnimator;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
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
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

/**
 * Nui-Rama — flying insectoid Rahi. Always hostile to players.
 *
 * Altitude is managed by AltitudeGoal (no flags, runs every tick) which directly
 * sets deltaMovement.y — navigation-based altitude goals are too slow/intermittent
 * to reliably keep the entity within the player's 3-block attack reach.
 */
public class EntityNuiRama extends Monster {

    private String lastAnimState = "";

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
        this.goalSelector.addGoal(1, new FlyingMeleeGoal(this, 0.10));
        // AltitudeGoal has NO flags — runs alongside any movement goal every single tick
        this.goalSelector.addGoal(2, new AltitudeGoal(this, 2.5));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomFlyingGoal(this, 0.6));
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
            boolean moving = this.getDeltaMovement().lengthSqr() > 1.0E-5;
            String animState = moving ? "walk" : "idle";
            if (!animState.equals(lastAnimState)) {
                lastAnimState = animState;
                NuiRamaAnimator.sendMovementCommand(this);
            }
        }
    }

    /**
     * Directly sets deltaMovement.y every tick to hold the entity at a target altitude.
     * Uses no Goal flags so it runs alongside MOVE goals without blocking them.
     * Runs in aiStep() before travel(), so the Y value is applied by vanilla move().
     *
     * When no combat target: hovers at terrain surface + targetAlt blocks.
     * When in combat: descends to target's body center so the player can reach it.
     */
    static class AltitudeGoal extends Goal {
        private final EntityNuiRama mob;
        private final double targetAlt;

        AltitudeGoal(EntityNuiRama mob, double targetAlt) {
            this.mob = mob;
            this.targetAlt = targetAlt;
            setFlags(EnumSet.noneOf(Flag.class)); // intentionally no flags
        }

        @Override public boolean canUse()            { return true; }
        @Override public boolean canContinueToUse()  { return true; }

        @Override
        public void tick() {
            LivingEntity target = mob.getTarget();
            double wantedY;
            if (target != null) {
                // In combat: hover with entity center at target's body center
                wantedY = target.getY() + target.getBbHeight() * 0.5 - mob.getBbHeight() * 0.5;
            } else {
                // Idle: stay targetAlt blocks above terrain surface
                BlockPos surface = mob.level().getHeightmapPos(
                        Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, mob.blockPosition());
                wantedY = surface.getY() + targetAlt;
            }
            // Proportional Y controller — sets deltaMovement.y, preserved through vanilla travel()
            double dy = Mth.clamp((wantedY - mob.getY()) * 0.3, -0.3, 0.3);
            Vec3 vel = mob.getDeltaMovement();
            mob.setDeltaMovement(vel.x, dy, vel.z);
        }
    }

    /**
     * Handles horizontal combat movement. AltitudeGoal (above) handles the Y axis
     * independently, so this goal only needs to steer X/Z toward the target.
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

            double dx = target.getX() - mob.getX();
            double dz = target.getZ() - mob.getZ();
            double horizDist = Math.sqrt(dx * dx + dz * dz);
            double fullDist  = mob.distanceTo(target);

            Vec3 vel = mob.getDeltaMovement();
            if (horizDist > HOVER_DIST) {
                // Steer X/Z directly — AltitudeGoal owns Y, so leave vel.y untouched
                double scale = chaseSpeed / Math.max(horizDist, 0.01);
                mob.setDeltaMovement(vel.x * 0.7 + dx * scale,
                                     vel.y,
                                     vel.z * 0.7 + dz * scale);
            } else {
                // Arrived — brake X/Z so the entity hovers in place (Y still by AltitudeGoal)
                mob.setDeltaMovement(vel.x * 0.2, vel.y, vel.z * 0.2);
            }

            if (--attackCooldown <= 0 && fullDist <= ATTACK_DIST) {
                attackCooldown = BASE_ATTACK_INTERVAL + mob.random.nextInt(10);
                if (mob.level() instanceof net.minecraft.server.level.ServerLevel sl) {
                    mob.doHurtTarget(sl, target);
                }
            }
        }
    }
}
