package eastonium.nuicraft.item;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;

/**
 * Heatstone Lighter - A durable flint and steel that recharges in the Nether
 */
public class ItemHeatstoneLighter extends FlintAndSteelItem {
    public ItemHeatstoneLighter(Properties properties) {
        super(properties);
    }
    
    public static Properties createProperties() {
        return new Properties()
                .stacksTo(1)
                .durability(128);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        ItemStack stack = context.getItemInHand();
        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        Level level = context.getLevel();
        
        if (!context.getPlayer().mayUseItemAt(pos, context.getClickedFace(), stack)) {
            return InteractionResult.FAIL;
        }
        
        if (stack.getDamageValue() < stack.getMaxDamage()) {
            if (level.isEmptyBlock(pos)) {
                level.playSound(context.getPlayer(), pos, SoundEvents.FLINTANDSTEEL_USE, 
                               SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 0.8F);
                level.setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
            }
            stack.hurtAndBreak(1, context.getPlayer(), context.getPlayer().getEquipmentSlotForItem(stack));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        // Auto-repair in the Nether
        if (entity instanceof LivingEntity living && 
            level.getBiome(entity.blockPosition()).is(Biomes.NETHER_WASTES)) {
            if (level.random.nextInt(60) == 0 && stack.getDamageValue() > 0) {
                stack.setDamageValue(stack.getDamageValue() - 1);
            }
        }
    }
}
