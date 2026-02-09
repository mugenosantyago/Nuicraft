package eastonium.nuicraft.item;

import eastonium.nuicraft.core.NuiCraftItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;

public class NuiCraftTiers {
    // Protodermis: Between iron and diamond (similar to old config: harvest level 2, durability 500)
    public static final ToolMaterial PROTODERMIS = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            500,      // Uses (durability)
            5.0F,     // Speed
            2.0F,     // Attack damage bonus
            7,        // Enchantability
            ItemTags.IRON_TOOL_MATERIALS // TODO: Create custom tag for protodermis
    );
    
    // Protosteel: Superior to diamond (similar to old config: harvest level 7, durability 4620)
    public static final ToolMaterial PROTOSTEEL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            4620,     // Uses (durability) - very high
            11.0F,    // Speed - faster than diamond
            5.0F,     // Attack damage bonus - high damage
            15,       // Enchantability - very high
            ItemTags.NETHERITE_TOOL_MATERIALS // TODO: Create custom tag for protosteel
    );
}
