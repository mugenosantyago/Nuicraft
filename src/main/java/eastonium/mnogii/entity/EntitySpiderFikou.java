package eastonium.mnogii.entity;

import eastonium.mnogii.client.animator.SpiderFikouAnimator;
import eastonium.mnogii.core.MnogiiEntityTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class EntitySpiderFikou extends EntityFikou {

    public EntitySpiderFikou(EntityType<? extends EntitySpiderFikou> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return EntityFikou.createAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.28D);
    }

    @Override
    protected void dispatchMovementAnimation(boolean moving) {
        SpiderFikouAnimator.sendMovementCommand(this, moving);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return new EntitySpiderFikou(MnogiiEntityTypes.SPIDER_FIKOU.get(), level);
    }
}
