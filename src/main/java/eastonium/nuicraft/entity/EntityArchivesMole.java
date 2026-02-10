package eastonium.nuicraft.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class EntityArchivesMole extends Animal {
    public EntityArchivesMole(EntityType<? extends EntityArchivesMole> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.15D);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.CARROTS) || stack.is(Items.POTATOES);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return new EntityArchivesMole(eastonium.nuicraft.core.NuiCraftEntityTypes.ARCHIVES_MOLE.get(), level);
    }
}
