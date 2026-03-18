package eastonium.mnogii.entity;

import eastonium.mnogii.core.MnogiiEntityTypes;
import eastonium.mnogii.core.MnogiiItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

/**
 * Projectile entity for throwable Kanoka/bamboo discs.
 * - Flat trajectory (minimal gravity) — not a snowball arc.
 * - Deals 2 damage on entity hit.
 * - Koro-variant discs apply an elemental effect on hit.
 * - Returns the disc to the thrower on impact with 1 durability consumed.
 *   When durability is depleted the disc breaks and nothing is returned.
 */
public class EntityThrownDisc extends ThrowableItemProjectile {

    private static final float DAMAGE = 2.0F;

    public EntityThrownDisc(EntityType<? extends EntityThrownDisc> type, Level level) {
        super(type, level);
    }

    public EntityThrownDisc(Level level, LivingEntity owner, ItemStack stack) {
        super(MnogiiEntityTypes.THROWN_DISC.get(), owner, level, stack);
    }

    @Override
    protected Item getDefaultItem() {
        return MnogiiItems.KANOKA_BAMBOO.get();
    }

    /** Very low gravity so the disc flies mostly flat rather than arcing like a snowball. */
    @Override
    protected double getDefaultGravity() {
        return 0.005;
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        if (hitResult.getEntity() instanceof LivingEntity target && this.level() instanceof ServerLevel serverLevel) {
            DamageSource source = this.level().damageSources().thrown(this,
                    getOwner() != null ? getOwner() : this);
            target.hurt(source, DAMAGE);
            applyKoroEffect(target, serverLevel);
        }
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide) {
            returnDisc();
            this.discard();
        }
    }

    /**
     * Applies an elemental effect to the target based on which koro disc was thrown.
     * Each koro has a thematic effect matching its elemental affinity.
     */
    private void applyKoroEffect(LivingEntity target, ServerLevel level) {
        Item disc = this.getItem().getItem();
        Vec3 pos = target.position();

        if (disc == MnogiiItems.KANOKA_DISK_TA.get()) {
            // Ta-Koro (Fire): ignite and scorch
            target.igniteForSeconds(8.0F);
            level.sendParticles(ParticleTypes.FLAME,
                    pos.x, pos.y + 1.0, pos.z, 25, 0.3, 0.5, 0.3, 0.06);

        } else if (disc == MnogiiItems.KANOKA_DISK_GA.get()) {
            // Ga-Koro (Water): slow and soak
            target.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 120, 1)); // Slowness II, 6s
            level.sendParticles(ParticleTypes.SPLASH,
                    pos.x, pos.y + 1.2, pos.z, 35, 0.3, 0.3, 0.3, 0.4);

        } else if (disc == MnogiiItems.KANOKA_DISK_LE.get()) {
            // Le-Koro (Air/Jungle): launch upward with levitation
            target.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, 0)); // Levitation I, 3s
            level.sendParticles(ParticleTypes.CLOUD,
                    pos.x, pos.y + 1.0, pos.z, 20, 0.4, 0.3, 0.4, 0.05);

        } else if (disc == MnogiiItems.KANOKA_DISK_KO.get()) {
            // Ko-Koro (Ice): freeze and slow
            target.setTicksFrozen(target.getTicksRequiredToFreeze() + 80);
            target.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 100, 2)); // Slowness III, 5s
            level.sendParticles(ParticleTypes.SNOWFLAKE,
                    pos.x, pos.y + 1.0, pos.z, 25, 0.3, 0.5, 0.3, 0.05);

        } else if (disc == MnogiiItems.KANOKA_DISK_ONU.get()) {
            // Onu-Koro (Earth): blind and exhaust
            target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0));     // Blindness, 5s
            target.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 100, 1)); // Mining Fatigue II, 5s
            level.sendParticles(ParticleTypes.SMOKE,
                    pos.x, pos.y + 1.0, pos.z, 20, 0.3, 0.5, 0.3, 0.02);

        } else if (disc == MnogiiItems.KANOKA_DISK_PO.get()) {
            // Po-Koro (Stone): knockback and weaken
            Vec3 knockDir = pos.subtract(this.position()).normalize();
            target.knockback(2.5, -knockDir.x, -knockDir.z);
            target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0)); // Weakness, 5s
            level.sendParticles(ParticleTypes.CRIT,
                    pos.x, pos.y + 1.0, pos.z, 20, 0.3, 0.4, 0.3, 0.1);
        }
    }

    /**
     * Returns the disc to the owner after applying 1 point of damage.
     * If the disc's durability is fully depleted, it breaks and nothing is returned.
     */
    private void returnDisc() {
        ItemStack returnStack = this.getItem().copyWithCount(1);
        if (returnStack.isEmpty()) return;

        if (returnStack.isDamageableItem()) {
            int newDamage = returnStack.getDamageValue() + 1;
            if (newDamage >= returnStack.getMaxDamage()) {
                return;
            }
            returnStack.setDamageValue(newDamage);
        }

        var owner = getOwner();
        if (owner instanceof Player player) {
            if (player.isCreative()) return;
            if (!player.getInventory().add(returnStack)) {
                player.drop(returnStack, false);
            }
        } else if (owner instanceof LivingEntity living) {
            if (living.getMainHandItem().isEmpty()) {
                living.setItemSlot(net.minecraft.world.entity.EquipmentSlot.MAINHAND, returnStack);
            } else {
                level().addFreshEntity(new net.minecraft.world.entity.item.ItemEntity(
                        level(), owner.getX(), owner.getY(), owner.getZ(), returnStack));
            }
        } else {
            level().addFreshEntity(new net.minecraft.world.entity.item.ItemEntity(
                    level(), getX(), getY(), getZ(), returnStack));
        }
    }
}
