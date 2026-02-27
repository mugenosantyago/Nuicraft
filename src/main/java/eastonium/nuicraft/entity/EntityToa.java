package eastonium.nuicraft.entity;

import eastonium.nuicraft.client.animator.ToaAnimator;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * Toa - NPC that spawns once per corresponding Koro-themed biome.
 * Variant determines texture and spawn biome (Tahu=Ta-Koro/fire, Gali=Ga-Koro/water, etc.).
 */
public class EntityToa extends PathfinderMob {

    public enum Variant {
        TAHU("toa_tahu"),
        GALI("toa_gali"),
        LEWA("toa_lewa"),
        ONUA("toa_onua"),
        POHATU("toa_pohatu"),
        KOPAKA("toa_kopaka");

        private final String textureName;

        Variant(String textureName) {
            this.textureName = textureName;
        }

        public String getTextureName() {
            return textureName;
        }
    }

    private final Variant variant;
    private boolean lastMoving = false;

    public EntityToa(EntityType<? extends PathfinderMob> type, Level level, Variant variant) {
        super(type, level);
        this.variant = variant;
    }

    public Variant getVariant() {
        return variant;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.5D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 10.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.FOLLOW_RANGE, 20.0D);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            boolean moving = this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-5;
            if (moving != lastMoving) {
                lastMoving = moving;
                ToaAnimator.sendMovementCommand(this);
            }
        }
    }
}
