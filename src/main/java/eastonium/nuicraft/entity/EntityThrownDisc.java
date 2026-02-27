package eastonium.nuicraft.entity;

import eastonium.nuicraft.core.NuiCraftEntityTypes;
import eastonium.nuicraft.core.NuiCraftItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

/**
 * Projectile entity for throwable Kanoka/bamboo discs.
 * - Flat trajectory (minimal gravity) — not a snowball arc.
 * - Deals 2 damage on entity hit.
 * - Returns exactly 1 disc to the thrower on impact; no extra items.
 */
public class EntityThrownDisc extends ThrowableItemProjectile {

    private static final float DAMAGE = 2.0F;

    public EntityThrownDisc(EntityType<? extends EntityThrownDisc> type, Level level) {
        super(type, level);
    }

    public EntityThrownDisc(Level level, LivingEntity owner, ItemStack stack) {
        super(NuiCraftEntityTypes.THROWN_DISC.get(), owner, level, stack);
    }

    @Override
    protected Item getDefaultItem() {
        return NuiCraftItems.KANOKA_DISC.get();
    }

    /** Very low gravity so the disc flies mostly flat rather than arcing like a snowball. */
    @Override
    protected double getDefaultGravity() {
        return 0.005;
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        if (hitResult.getEntity() instanceof LivingEntity target && this.level() instanceof ServerLevel) {
            DamageSource source = this.level().damageSources().thrown(this,
                    getOwner() != null ? getOwner() : this);
            target.hurt(source, DAMAGE);
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
     * Returns exactly 1 disc to the owner.
     * Uses count=1 to prevent returning the entire thrown stack on multi-disc throws.
     */
    private void returnDisc() {
        ItemStack returnStack = this.getItem().copyWithCount(1);
        if (returnStack.isEmpty()) return;

        var owner = getOwner();
        if (owner instanceof Player player) {
            // Silently add — no pickup sound to avoid confusion, just quietly return.
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
            // No owner — drop at impact point.
            level().addFreshEntity(new net.minecraft.world.entity.item.ItemEntity(
                    level(), getX(), getY(), getZ(), returnStack));
        }
    }
}
