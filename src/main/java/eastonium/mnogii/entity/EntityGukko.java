package eastonium.mnogii.entity;

import eastonium.mnogii.client.animator.GukkoAnimator;
import eastonium.mnogii.core.MnogiiEntityTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * Gukko — large flying Rahi, rideable without a saddle.
 * Right-click to mount. WASD steers horizontally; Space ascends; Shift descends.
 * Breed with feathers.
 */
public class EntityGukko extends Animal {

    private static final float FLY_SPEED     = 0.15f;
    private static final float FLY_DRAG      = 0.9f;
    /** How far above the entity's bounding-box top the rider sits. */
    private static final float HEIGHT_OFFSET = 2.0f;

    // Cache the protected LivingEntity.jumping field for reading rider space-bar input.
    private static final java.lang.reflect.Field JUMPING_FIELD;
    static {
        java.lang.reflect.Field f = null;
        try {
            f = LivingEntity.class.getDeclaredField("jumping");
            f.setAccessible(true);
        } catch (Exception ignored) {}
        JUMPING_FIELD = f;
    }

    private static boolean isJumping(LivingEntity entity) {
        if (JUMPING_FIELD == null) return false;
        try { return JUMPING_FIELD.getBoolean(entity); } catch (Exception e) { return false; }
    }

    private boolean lastMoving = false;

    public EntityGukko(EntityType<? extends EntityGukko> type, Level level) {
        super(type, level);
        this.moveControl = new FlyingMoveControl(this, 10, true);
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
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.0, stack -> stack.is(Items.FEATHER), false));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.0));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomFlyingGoal(this, 1.0));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.FLYING_SPEED, 0.12)
                .add(Attributes.MOVEMENT_SPEED, 0.12)
                .add(Attributes.FOLLOW_RANGE, 100.0)
                .add(Attributes.TEMPT_RANGE, 16.0);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.FEATHER);
    }

    @Override
    public AgeableMob getBreedOffspring(net.minecraft.server.level.ServerLevel level, AgeableMob otherParent) {
        return new EntityGukko(MnogiiEntityTypes.GUKKO.get(), level);
    }

    @Override
    public boolean doHurtTarget(net.minecraft.server.level.ServerLevel level, net.minecraft.world.entity.Entity target) {
        boolean hit = super.doHurtTarget(level, target);
        if (hit) GukkoAnimator.sendAttackCommand(this);
        return hit;
    }

    // ---- Riding ----

    @Override
    public void travel(Vec3 travelVector) {
        if (isVehicle() && getControllingPassenger() instanceof LivingEntity driver) {
            // Face where the rider faces; no pitch-tilt on the Gukko body.
            setYRot(driver.getYRot());
            yRotO  = getYRot();
            setXRot(0f);
            yBodyRot = getYRot();
            yHeadRot = getYRot();

            float yaw = getYRot() * (float) (Math.PI / 180.0);

            float fwd    = driver.zza;  // W = +1, S = −1
            float strafe = driver.xxa;  // A = +1, D = −1

            // WASD = purely horizontal movement
            double dx = (strafe * Mth.cos(yaw) - fwd * Mth.sin(yaw)) * FLY_SPEED;
            double dz = (fwd   * Mth.cos(yaw) + strafe * Mth.sin(yaw)) * FLY_SPEED;

            // Space = ascend, Shift = descend
            double dy = 0;
            if (isJumping(driver))          dy += FLY_SPEED;
            if (driver.isShiftKeyDown())    dy -= FLY_SPEED;

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
            if (moving != lastMoving || this.tickCount % 20 == 1) {
                lastMoving = moving;
                GukkoAnimator.sendMovementCommand(this);
            }
        }
    }

    @Override
    public LivingEntity getControllingPassenger() {
        Entity e = getFirstPassenger();
        return e instanceof LivingEntity living ? living : null;
    }

    @Override
    public boolean canAddPassenger(Entity passenger) {
        return this.getPassengers().isEmpty();
    }

    @Override
    public void positionRider(Entity passenger, Entity.MoveFunction callback) {
        if (!this.hasPassenger(passenger)) return;
        // Sit centered on top — no forward lean, matches Happy Ghast style
        callback.accept(passenger,
                getX(),
                getY() + getDimensions(Pose.STANDING).height() + HEIGHT_OFFSET,
                getZ());
    }

    @Override
    public net.minecraft.world.InteractionResult mobInteract(Player player, net.minecraft.world.InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (this.getPassengers().isEmpty() && this.isFood(stack)) {
            net.minecraft.world.InteractionResult result = super.mobInteract(player, hand);
            if (result.consumesAction()) return result;
        }
        if (this.getPassengers().isEmpty() && !player.isSecondaryUseActive()) {
            if (!this.level().isClientSide) player.startRiding(this);
            return net.minecraft.world.InteractionResult.SUCCESS;
        }
        return this.level().isClientSide
                ? net.minecraft.world.InteractionResult.SUCCESS
                : net.minecraft.world.InteractionResult.CONSUME;
    }
}
