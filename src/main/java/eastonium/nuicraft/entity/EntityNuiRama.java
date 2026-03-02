package eastonium.nuicraft.entity;

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
 * Nui-Rama - flying insectoid Rahi. Wild: hostile, as dangerous as a Wither Skeleton.
 * Tame by feeding spider eyes (1-in-3 chance per feeding). Once tamed, becomes a
 * rideable flying mount with the same controls as the Gukko.
 */
public class EntityNuiRama extends TamableAnimal {

    /** Movement input from the controlling player (set by server from GukkoInputPayload). */
    private boolean inputForward, inputBack, inputLeft, inputRight, inputUp, inputDown;

    private static final double FLY_SPEED       = 0.12;
    private static final double VERTICAL_SPEED  = 0.12;
    private static final float  HEIGHT_OFFSET   = 0.3f;

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
                .add(Attributes.MAX_HEALTH, 35.0)       // wither skeleton HP
                .add(Attributes.ATTACK_DAMAGE, 7.0)     // wither skeleton attack
                .add(Attributes.FLYING_SPEED, 0.35)
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.FOLLOW_RANGE, 24.0);
    }

    /** Spider eyes are the taming item (thematic for an insectoid predator). */
    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.SPIDER_EYE);
    }

    /** Nui-Ramas do not breed. */
    @Override
    public @Nullable AgeableMob getBreedOffspring(net.minecraft.server.level.ServerLevel level, AgeableMob otherParent) {
        return null;
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
                    level().broadcastEntityEvent(this, (byte) 7); // heart particles
                } else {
                    level().broadcastEntityEvent(this, (byte) 6); // smoke particles
                }
            }
            return level().isClientSide ? InteractionResult.CONSUME : InteractionResult.SUCCESS;
        }

        // Tamed + no rider: mount on right-click (not sneaking)
        if (isTame() && getPassengers().isEmpty() && !player.isSecondaryUseActive()) {
            if (!level().isClientSide) {
                player.startRiding(this);
            }
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }

    // ---- Flying mount (mirrors EntityGukko) ----

    /** Called from the server payload handler to relay rider input from the client. */
    public void setMovementInput(boolean forward, boolean back, boolean left, boolean right, boolean up, boolean down) {
        this.inputForward = forward;
        this.inputBack    = back;
        this.inputLeft    = left;
        this.inputRight   = right;
        this.inputUp      = up;
        this.inputDown    = down;
    }

    @Override
    public void tick() {
        super.tick();
        if (isVehicle()) {
            Entity passenger = getControllingPassenger();
            if (passenger instanceof Player) {
                setYRot(passenger.getYRot());
                yRotO = getYRot();
                setXRot(passenger.getXRot() * 0.5f);
                applyRiderMovement();
            }
        } else {
            inputForward = inputBack = inputLeft = inputRight = inputUp = inputDown = false;
        }
    }

    private void applyRiderMovement() {
        Vec3 motion = getDeltaMovement();
        double x = motion.x, y = motion.y, z = motion.z;

        float yaw      = getYRot() * ((float) Math.PI / 180f);
        double forward  = (inputForward ? 1 : 0) - (inputBack  ? 1 : 0);
        double strafe   = (inputRight   ? 1 : 0) - (inputLeft  ? 1 : 0);
        double vertical = (inputUp      ? 1 : 0) - (inputDown  ? 1 : 0);

        double sin = Mth.sin(yaw), cos = Mth.cos(yaw);
        x += (strafe * cos - forward * sin) * FLY_SPEED;
        z += (forward * cos + strafe * sin) * FLY_SPEED;
        y += vertical * VERTICAL_SPEED;

        if (!inputForward && !inputBack && !inputLeft && !inputRight) { x *= 0.9; z *= 0.9; }
        if (!inputUp && !inputDown) y *= 0.9;

        setDeltaMovement(Mth.clamp(x, -2.5, 2.5), Mth.clamp(y, -1.5, 1.5), Mth.clamp(z, -2.5, 2.5));
        move(MoverType.SELF, getDeltaMovement());
        setDeltaMovement(getDeltaMovement().scale(0.91));
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
        Vec3 pos    = new Vec3(0, HEIGHT_OFFSET, 0).yRot(-getYRot() * ((float) Math.PI / 180f));
        Vec3 attach = getPassengerAttachmentPoint(passenger, getDimensions(Pose.STANDING), 1.0f);
        callback.accept(passenger, getX() + pos.x, getY() + pos.y + attach.y, getZ() + pos.z);
    }
}
