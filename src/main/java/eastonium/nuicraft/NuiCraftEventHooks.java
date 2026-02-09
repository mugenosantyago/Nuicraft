package eastonium.nuicraft;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.fluids.FluidUtil;

/**
 * Event hooks for NuiCraft custom fluid bucket handling
 * 
 * NOTE: FillBucketEvent may have been removed or moved in 1.21.8
 * TODO: Verify event still exists or find replacement
 */
public class NuiCraftEventHooks {
    
    // Temporarily commented out - FillBucketEvent needs verification
    /*@SubscribeEvent(priority = EventPriority.NORMAL)
    public void onFillBucket(FillBucketEvent event) {
        // Check if it's a bucket
        ItemStack emptyBucket = event.getEmptyBucket();
        if (emptyBucket.isEmpty() || !emptyBucket.is(Items.BUCKET)) {
            return;
        }

        // Check if targeting a block
        BlockHitResult target = event.getTarget();
        if (target == null || target.getType() != HitResult.Type.BLOCK) {
            return;
        }
        
        Level level = event.getLevel();
        BlockPos pos = target.getBlockPos();

        // Try to pick up fluid
        ItemStack singleBucket = emptyBucket.copy();
        singleBucket.setCount(1);

        var filledBucket = FluidUtil.tryPickUpFluid(singleBucket, event.getEntity(), level, pos, target.getDirection());
        if (filledBucket.isSuccess()) {
            event.setFilledBucket(filledBucket.getResult());
        }
    }*/
}