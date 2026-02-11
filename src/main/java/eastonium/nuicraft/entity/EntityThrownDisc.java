package eastonium.nuicraft.entity;

import eastonium.nuicraft.core.NuiCraftEntityTypes;
import eastonium.nuicraft.core.NuiCraftItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Projectile entity for throwable Kanoka/bamboo discs.
 * Renders as the item that was thrown.
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
}
