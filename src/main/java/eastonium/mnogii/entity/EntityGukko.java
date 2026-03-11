package eastonium.mnogii.entity;

import eastonium.mnogii.client.animator.GukkoAnimator;
import eastonium.mnogii.core.MnogiiEntityTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Ghast;
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
 * Gukko — large flying Rahi, rideable without a saddle (Happy Ghast style).
 * Right-click to mount. WASD steers horizontally; look up/down to ascend/descend.
 * Space provides an extra upward boost. Breed with feathers.
 */
public class EntityGukko extends Animal {

    private static final float FLY_SPEED    = 0.15f;
    private static final float FLY_DRAG     = 0.9f;
    private static final float HEIGHT_OFFSET = 0.6f;

    private boolean lastMoving = false;

    public EntityGukko(EntityType<? extends EntityGukko> type, Level level) {
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
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.0, stack -> stack.is(Items.FEATHER), false));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.0));
        this.goalSelector.addGoal(4, new Ghast.RandomFloatAroundGoal(this, 16));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 0.5, 20) {
            @Override public boolean canUse() {
                return !EntityGukko.this.isVehicle() && super.canUse();
            }
        });
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.FLYING_SPEED, 0.17)
                .add(Attributes.MOVEMENT_SPEED, 0.17)
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
            // Sync orientation from the rider
            setYRot(driver.getYRot());
            yRotO = getYRot();
            setXRot(driver.getXRot() * 0.5f);
            yBodyRot = getYRot();
            yHeadRot = getYRot();

            float yaw   = getYRot()      * (float) (Math.PI / 180.0);
            float pitch = driver.getXRot() * (float) (Math.PI / 180.0);

            // Native player input (synced client→server by Minecraft automatically)
            float fwd    = driver.zza;  // W = +1, S = −1
            float strafe = driver.xxa;  // D = +1, A = −1

            // Horizontal movement rotated to face yaw
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
            if (moving != lastMoving) {
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
