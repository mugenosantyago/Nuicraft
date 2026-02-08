package eastonium.nuicraft.item;

import eastonium.nuicraft.core.NuiCraftItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class NuiCraftTiers {
    // Protodermis: Between iron and diamond (similar to old config: harvest level 2, durability 500)
    public static final Tier PROTODERMIS = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            500,      // Uses (durability)
            5.0F,     // Speed
            2.0F,     // Attack damage bonus
            7,        // Enchantability
            () -> Ingredient.of(NuiCraftItems.GENERIC_ITEM.get()) // TODO: Use actual protodermis ingot
    );
    
    // Protosteel: Superior to diamond (similar to old config: harvest level 7, durability 4620)
    public static final Tier PROTOSTEEL = new SimpleTier(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            4620,     // Uses (durability) - very high
            11.0F,    // Speed - faster than diamond
            5.0F,     // Attack damage bonus - high damage
            15,       // Enchantability - very high
            () -> Ingredient.of(NuiCraftItems.GENERIC_ITEM.get()) // TODO: Use actual protosteel ingot
    );
}
