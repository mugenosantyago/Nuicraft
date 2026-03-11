package eastonium.mnogii.item;

import eastonium.mnogii.entity.EntityThrownDisc;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Disc item that can be thrown (Kanoka, bamboo disc, etc.).
 * Flies flat (low gravity), deals 2 damage on hit, and returns exactly 1 disc to the thrower.
 */
public class ItemThrowableDisc extends Item {

    private static final float THROW_POWER = 2.5F;

    public ItemThrowableDisc(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.isEmpty()) {
            return InteractionResult.FAIL;
        }

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 0.5F,
                0.9F + level.getRandom().nextFloat() * 0.2F);

        if (level instanceof ServerLevel serverLevel) {
            // Spawn projectile carrying exactly 1 disc — prevents returning extra items.
            ItemStack throwStack = stack.copyWithCount(1);
            EntityThrownDisc disc = new EntityThrownDisc(level, player, throwStack);
            disc.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, THROW_POWER, 1.0F);
            serverLevel.addFreshEntity(disc);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        stack.consume(1, player);
        return InteractionResult.SUCCESS;
    }
}
