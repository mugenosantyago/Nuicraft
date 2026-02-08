package eastonium.nuicraft.item;

import net.minecraft.world.item.Item;

/**
 * Sluice tool used to extract protodermis from protodermis ore deposits
 * Used in interaction with BlockProtodermisDeposit
 */
public class ItemSluice extends Item {
    public ItemSluice(Properties properties) {
        super(properties);
    }
    
    public static Properties createProperties() {
        return new Properties()
                .stacksTo(1);
    }
}

