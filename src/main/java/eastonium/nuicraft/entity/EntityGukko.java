package eastonium.nuicraft.entity;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * Gukko - large flying Rahi. Can be ridden; player controls flight (like a rideable flying mount).
 * Flies most of the time when not ridden. Animations can be added later.
 */
public class EntityGukko extends PathfinderMob {

    /** Movement input from the controlling player (set by server from GukkoInputPayload). */
    private boolean inputForward;
    private boolean inputBack;
    private boolean inputLeft;
    private boolean inputRight;
    private boolean inputUp;
    private boolean inputDown;

    private static final double FLY_SPEED = 0.35;
    private static final double VERTICAL_SPEED = 0.28;
    private static final float RIDEABLE_HEIGHT_OFFSET = 0.5f;

    public EntityGukko(EntityType<? extends PathfinderMob> type, Level level) {
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
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 0.8, 20) {
            @Override
            public boolean canUse() {
                return !EntityGukko.this.isVehicle() && super.canUse();
            }
        });
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 24.0)
                .add(Attributes.FLYING_SPEED, 0.4)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.FOLLOW_RANGE, 24.0);
    }

    /** Called from server payload handler to set movement input from the rider. */
    public void setMovementInput(boolean forward, boolean back, boolean left, boolean right, boolean up, boolean down) {
        this.inputForward = forward;
        this.inputBack = back;
        this.inputLeft = left;
        this.inputRight = right;
        this.inputUp = up;
        this.inputDown = down;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isVehicle()) {
            Entity passenger = getControllingPassenger();
            if (passenger instanceof Player) {
                this.setYRot(passenger.getYRot());
                this.yRotO = this.getYRot();
                this.setXRot(passenger.getXRot() * 0.5f);
                this.applyRiderMovement();
            }
        } else {
            this.inputForward = false;
            this.inputBack = false;
            this.inputLeft = false;
            this.inputRight = false;
            this.inputUp = false;
            this.inputDown = false;
        }
    }

    private void applyRiderMovement() {
        Vec3 motion = this.getDeltaMovement();
        double x = motion.x;
        double y = motion.y;
        double z = motion.z;

        float yaw = this.getYRot() * ((float) Math.PI / 180f);
        double forward = (inputForward ? 1 : 0) - (inputBack ? 1 : 0);
        double strafe = (inputRight ? 1 : 0) - (inputLeft ? 1 : 0);
        double vertical = (inputUp ? 1 : 0) - (inputDown ? 1 : 0);

        double sin = Mth.sin(yaw);
        double cos = Mth.cos(yaw);
        x += (strafe * cos - forward * sin) * FLY_SPEED;
        z += (forward * cos + strafe * sin) * FLY_SPEED;
        y += vertical * VERTICAL_SPEED;

        // Slight deceleration when no input
        if (!inputForward && !inputBack && !inputLeft && !inputRight) {
            x *= 0.9;
            z *= 0.9;
        }
        if (!inputUp && !inputDown) {
            y *= 0.9;
        }

        this.setDeltaMovement(Mth.clamp(x, -1.5, 1.5), Mth.clamp(y, -1.0, 1.0), Mth.clamp(z, -1.5, 1.5));
        this.move(net.minecraft.world.entity.MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.91));
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
        float yOffset = RIDEABLE_HEIGHT_OFFSET;
        Vec3 pos = new Vec3(0, yOffset, 0)
                .yRot(-this.getYRot() * ((float) Math.PI / 180f));
        Vec3 attach = this.getPassengerAttachmentPoint(passenger, this.getDimensions(net.minecraft.world.entity.Pose.STANDING), 1.0f);
        callback.accept(passenger, this.getX() + pos.x, this.getY() + pos.y + attach.y, this.getZ() + pos.z);
    }

    @Override
    public net.minecraft.world.InteractionResult mobInteract(Player player, net.minecraft.world.InteractionHand hand) {
        if (!this.level().isClientSide && this.getPassengers().isEmpty() && !player.isSecondaryUseActive()) {
            player.startRiding(this);
            return net.minecraft.world.InteractionResult.SUCCESS;
        }
        return this.level().isClientSide ? net.minecraft.world.InteractionResult.SUCCESS : net.minecraft.world.InteractionResult.CONSUME;
    }
}
