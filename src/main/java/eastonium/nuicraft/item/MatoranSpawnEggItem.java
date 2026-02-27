package eastonium.nuicraft.item;

import eastonium.nuicraft.core.NuiCraftEntityTypes;
import eastonium.nuicraft.entity.EntityMatoran;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Spawn egg that spawns a Matoran with a specific Koro + Mask locked in.
 * Overrides useOn to directly instantiate the entity rather than relying
 * on the ENTITY_DATA component merge path, which is unreliable for synced data.
 */
public class MatoranSpawnEggItem extends SpawnEggItem {

    private final EntityMatoran.Koro koro;
    private final EntityMatoran.Mask mask;

    public MatoranSpawnEggItem(EntityMatoran.Koro koro, EntityMatoran.Mask mask, Item.Properties props) {
        super(NuiCraftEntityTypes.MATORAN.get(), props);
        this.koro = koro;
        this.mask = mask;
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Level level = ctx.getLevel();
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        ServerLevel serverLevel = (ServerLevel) level;
        BlockPos clickedPos = ctx.getClickedPos();
        Direction face = ctx.getClickedFace();
        BlockState state = level.getBlockState(clickedPos);

        // Spawn position: on top of the clicked face, centred in the block
        BlockPos spawnPos = state.getCollisionShape(level, clickedPos).isEmpty()
                ? clickedPos
                : clickedPos.relative(face);

        // Build the entity with the fixed koro + mask directly in the constructor
        EntityMatoran matoran = new EntityMatoran(
                NuiCraftEntityTypes.MATORAN.get(), serverLevel, koro, mask);

        // Assign a random profession
        EntityMatoran.Profession[] profs = EntityMatoran.Profession.values();
        matoran.setProfession(profs[serverLevel.getRandom().nextInt(profs.length)]);

        matoran.moveTo(
                spawnPos.getX() + 0.5,
                (double) spawnPos.getY(),
                spawnPos.getZ() + 0.5,
                serverLevel.getRandom().nextFloat() * 360f,
                0f);

        serverLevel.addFreshEntityWithPassengers(matoran);

        var player = ctx.getPlayer();
        if (player == null || !player.isCreative()) {
            ctx.getItemInHand().shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}
