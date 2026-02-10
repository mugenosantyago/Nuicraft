package eastonium.nuicraft.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.level.Level;

public class EntityUssalCrab extends CaveSpider {
    public EntityUssalCrab(EntityType<? extends EntityUssalCrab> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return CaveSpider.createAttributes()
                .add(Attributes.MAX_HEALTH, 16.0D);
    }
}
