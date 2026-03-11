package eastonium.mnogii.entity;

import eastonium.mnogii.client.animator.NuiRamaAnimator;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

/**
 * Nui-Rama — flying insectoid Rahi. Wild: hostile. Tamed with spider eyes (1-in-3 chance).
 * Once tamed, becomes a rideable flying mount (Happy Ghast style — no saddle needed).
 * WASD steers; look up/down to ascend/descend.
 */
public class EntityNuiRama extends TamableAnimal {

    private static final float FLY_SPEED    = 0.15f;
    private static final float FLY_DRAG     = 0.9f;
    private static final float HEIGHT_OFFSET = 0.4f;

    private boolean lastMoving = false;

    public EntityNuiRama(EntityType<? extends TamableAnimal> type, Level level) {
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

        // Wild-only: attack players on sight and fight back when hurt
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true) {
            @Override public boolean canUse() { return !EntityNuiRama.this.isTame() && super.canUse(); }
        });
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true) {
            @Override public boolean canUse() { return !EntityNuiRama.this.isTame() && super.canUse(); }
        });
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this) {
            @Override public boolean canUse() { return !EntityNuiRama.this.isTame() && super.canUse(); }
        });

        // Idle wandering (both states, suppressed while carrying a rider)
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 0.6, 20) {
            @Override public boolean canUse() { return !EntityNuiRama.this.isVehicle() && super.canUse(); }
        });
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.ATTACK_DAMAGE, 6.0)
                .add(Attributes.FLYING_SPEED, 0.17)
                .add(Attributes.MOVEMENT_SPEED, 0.17)
                .add(Attributes.FOLLOW_RANGE, 100.0);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.SPIDER_EYE);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(net.minecraft.server.level.ServerLevel level, AgeableMob otherParent) {
        return null;
    }

    @Override
    public boolean doHurtTarget(net.minecraft.server.level.ServerLevel level, net.minecraft.world.entity.Entity target) {
        boolean hit = super.doHurtTarget(level, target);
        if (hit) NuiRamaAnimator.sendAttackCommand(this);
        return hit;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // Attempt taming while wild and holding a spider eye
        if (!isTame() && isFood(stack)) {
            if (!level().isClientSide) {
                usePlayerItem(player, hand, stack);
                if (random.nextInt(3) == 0) {
                    tame(player);
                    level().broadcastEntityEvent(this, (byte) 7);
                } else {
                    level().broadcastEntityEvent(this, (byte) 6);
                }
            }
            return level().isClientSide ? InteractionResult.CONSUME : InteractionResult.SUCCESS;
        }

        // Tamed + no rider: mount on right-click (not sneaking)
        if (isTame() && getPassengers().isEmpty() && !player.isSecondaryUseActive()) {
            if (!level().isClientSide) player.startRiding(this);
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }

    // ---- Riding (Happy Ghast style) ----

    @Override
    public void travel(Vec3 travelVector) {
        if (isVehicle() && getControllingPassenger() instanceof LivingEntity driver) {
            // Sync orientation from the rider
            setYRot(driver.getYRot());
            yRotO = getYRot();
            setXRot(driver.getXRot() * 0.5f);
            yBodyRot = getYRot();
            yHeadRot = getYRot();

            float yaw   = getYRot()       * (float) (Math.PI / 180.0);
            float pitch = driver.getXRot() * (float) (Math.PI / 180.0);

            float fwd    = driver.zza;  // W = +1, S = −1
            float strafe = driver.xxa;  // D = +1, A = −1

            double dx = (strafe * Mth.cos(yaw) - fwd * Mth.sin(yaw)) * FLY_SPEED;
            double dz = (fwd * Mth.cos(yaw) + strafe * Mth.sin(yaw)) * FLY_SPEED;

            // Vertical: look up while pressing W to rise (pitch-based, Happy Ghast style)
            double dy = fwd * -Mth.sin(pitch) * FLY_SPEED;

            Vec3 motion = getDeltaMovement();
            setDeltaMovement(
                Mth.clamp(motion.x + dx, -0.5, 0.5),
                Mth.clamp(motion.y + dy, -0.5, 0.5),
                Mth.clamp(motion.z + dz, -0.5, 0.5)
            );
            move(MoverType.SELF, getDeltaMovement());
            setDeltaMovement(getDeltaMovement().scale(FLY_DRAG));
        } else {
            super.travel(travelVector);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            boolean moving = this.getDeltaMovement().lengthSqr() > 1.0E-5;
            if (moving != lastMoving || this.tickCount == 1) {
                lastMoving = moving;
                NuiRamaAnimator.sendMovementCommand(this);
            }
        }
    }

    @Override
    public LivingEntity getControllingPassenger() {
        Entity e = getFirstPassenger();
        return e instanceof LivingEntity living ? living : null;
    }

    /** Only a tamed Nui-Rama can carry a passenger. */
    @Override
    public boolean canAddPassenger(Entity passenger) {
        return isTame() && getPassengers().isEmpty();
    }

    @Override
    public void positionRider(Entity passenger, Entity.MoveFunction callback) {
        if (!hasPassenger(passenger)) return;
        callback.accept(passenger,
                getX(),
                getY() + getDimensions(Pose.STANDING).height() + HEIGHT_OFFSET,
                getZ());
    }
}
