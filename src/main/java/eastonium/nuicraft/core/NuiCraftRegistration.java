package eastonium.nuicraft.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import eastonium.nuicraft.NuiCraft;

public class NuiCraftRegistration {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, NuiCraft.MODID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, NuiCraft.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, NuiCraft.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, NuiCraft.MODID);

    public static void register(IEventBus modEventBus) {
        // Register blocks and items from their respective classes
        NuiCraftBlocks.BLOCKS.register(modEventBus);
        NuiCraftItems.ITEMS.register(modEventBus);
        NuiCraftEntityTypes.ENTITY_TYPES.register(modEventBus);
        
        // Register other registries
        FLUIDS.register(modEventBus);
        FLUID_TYPES.register(modEventBus);
        BLOCK_ENTITY_TYPES.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
    }
}
