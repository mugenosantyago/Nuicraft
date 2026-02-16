package eastonium.nuicraft.entity;

import eastonium.nuicraft.core.NuiCraftEntityTypes;
import eastonium.nuicraft.core.NuiCraftItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

/**
 * Projectile entity for throwable Kanoka/bamboo discs.
 * Renders as the item that was thrown.
 * On impact, the disc returns to the thrower (or drops at impact if no owner).
 */
public class EntityThrownDisc extends ThrowableItemProjectile {

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

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide) {
            ItemStack stack = this.getItem();
            if (!stack.isEmpty()) {
                LivingEntity owner = getOwner() instanceof LivingEntity living ? living : null;
                if (owner != null) {
                    if (owner instanceof Player player) {
                        if (!player.getInventory().add(stack)) {
                            player.drop(stack, false);
                        }
                    } else if (owner.getMainHandItem().isEmpty()) {
                        owner.setItemSlot(EquipmentSlot.MAINHAND, stack);
                    } else {
                        ItemEntity drop = new ItemEntity(level(), owner.getX(), owner.getY(), owner.getZ(), stack);
                        level().addFreshEntity(drop);
                    }
                } else {
                    ItemEntity drop = new ItemEntity(level(), getX(), getY(), getZ(), stack);
                    level().addFreshEntity(drop);
                }
            }
            this.discard();
        }
    }
}
