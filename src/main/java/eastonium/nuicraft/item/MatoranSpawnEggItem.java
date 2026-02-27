package eastonium.nuicraft.item;

import eastonium.nuicraft.core.NuiCraftEntityTypes;
import eastonium.nuicraft.entity.EntityMatoran;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.core.component.DataComponents;

/**
 * Spawn egg that spawns a Matoran with a specific Koro and Mask baked in.
 * Uses the generic nuicraft:matoran entity type with ENTITY_DATA to set
 * Koro and Mask ordinals, which are read by EntityMatoran.readAdditionalSaveData.
 */
public class MatoranSpawnEggItem extends SpawnEggItem {

    public MatoranSpawnEggItem(EntityMatoran.Koro koro, EntityMatoran.Mask mask, Item.Properties props) {
        super(NuiCraftEntityTypes.MATORAN.get(), buildProps(koro, mask, props));
    }

    private static Item.Properties buildProps(EntityMatoran.Koro koro, EntityMatoran.Mask mask, Item.Properties props) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("Koro", koro.ordinal());
        tag.putInt("Mask", mask.ordinal());
        return props.component(DataComponents.ENTITY_DATA, CustomData.of(tag));
    }
}
