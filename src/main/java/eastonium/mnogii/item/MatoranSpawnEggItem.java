package eastonium.mnogii.item;

import eastonium.mnogii.core.MnogiiEntityTypes;
import eastonium.mnogii.entity.EntityMatoran;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Spawn egg that spawns a Matoran with a specific Koro + Mask locked in.
 * Uses the koro-specific entity type so the client-side factory creates the
 * entity with the correct koro defaults, avoiding visual desync on first frame.
 */
public class MatoranSpawnEggItem extends SpawnEggItem {

    private final EntityMatoran.Koro koro;
    private final EntityMatoran.Mask mask;

    public MatoranSpawnEggItem(EntityMatoran.Koro koro, EntityMatoran.Mask mask, Item.Properties props) {
        super(entityTypeForKoro(koro), props);
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
        var stack = ctx.getItemInHand();
        BlockPos clickedPos = ctx.getClickedPos();
        Direction face = ctx.getClickedFace();
        BlockState state = level.getBlockState(clickedPos);

        BlockEntity be = level.getBlockEntity(clickedPos);
        if (be instanceof net.minecraft.world.level.Spawner spawner) {
            spawner.setEntityId(entityTypeForKoro(koro), level.getRandom());
            level.sendBlockUpdated(clickedPos, state, state, 3);
            if (ctx.getPlayer() == null || !ctx.getPlayer().isCreative()) {
                stack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }

        BlockPos spawnPos = state.getCollisionShape(level, clickedPos).isEmpty()
                ? clickedPos
                : clickedPos.relative(face);

        EntityType<EntityMatoran> type = entityTypeForKoro(koro);
        EntityMatoran matoran = new EntityMatoran(type, serverLevel, koro, mask);

        matoran.setProfession(
                EntityMatoran.RANDOM_PROFESSIONS[serverLevel.getRandom().nextInt(EntityMatoran.RANDOM_PROFESSIONS.length)]);

        matoran.setPos(spawnPos.getX() + 0.5, (double) spawnPos.getY(), spawnPos.getZ() + 0.5);
        matoran.setYRot(serverLevel.getRandom().nextFloat() * 360f);
        matoran.yRotO = matoran.getYRot();

        matoran.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(spawnPos),
                EntitySpawnReason.SPAWN_ITEM_USE, null);

        serverLevel.addFreshEntityWithPassengers(matoran);

        var player = ctx.getPlayer();
        if (player == null || !player.isCreative()) {
            stack.shrink(1);
        }

        return InteractionResult.SUCCESS;
    }

    private static EntityType<EntityMatoran> entityTypeForKoro(EntityMatoran.Koro koro) {
        return switch (koro) {
            case TA     -> MnogiiEntityTypes.MATORAN_TA.get();
            case GA     -> MnogiiEntityTypes.MATORAN_GA.get();
            case LE     -> MnogiiEntityTypes.MATORAN_LE.get();
            case ONU    -> MnogiiEntityTypes.MATORAN_ONU.get();
            case KO     -> MnogiiEntityTypes.MATORAN_KO.get();
            case PO     -> MnogiiEntityTypes.MATORAN_PO.get();
            case PURPLE -> MnogiiEntityTypes.MATORAN_PURPLE.get();
            case YELLOW -> MnogiiEntityTypes.MATORAN_YELLOW.get();
        };
    }
}
