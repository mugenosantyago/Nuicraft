package eastonium.nuicraft.core;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.blockentity.PurifierBlockEntity;
import eastonium.nuicraft.menu.PurifierMenu;
import eastonium.nuicraft.morph.NuiCraftAttachments;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class NuiCraftRegistration {
    public static final DeferredRegister<Fluid>              FLUIDS             = DeferredRegister.create(Registries.FLUID, NuiCraft.MODID);
    public static final DeferredRegister<FluidType>          FLUID_TYPES        = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, NuiCraft.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, NuiCraft.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, NuiCraft.MODID);
    public static final DeferredRegister<MenuType<?>>        MENU_TYPES         = DeferredRegister.create(Registries.MENU, NuiCraft.MODID);

    /** Purifier block entity type — referenced by BlockPurifier and PurifierBlockEntity. */
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PurifierBlockEntity>> PURIFIER_BE =
            BLOCK_ENTITY_TYPES.register("purifier", () ->
                    new BlockEntityType<>(PurifierBlockEntity::new, NuiCraftBlocks.PURIFIER.get()));

    /** Purifier menu type — referenced by PurifierMenu and PurifierScreen. */
    public static final DeferredHolder<MenuType<?>, MenuType<PurifierMenu>> PURIFIER_MENU =
            MENU_TYPES.register("purifier", () ->
                    IMenuTypeExtension.create((windowId, inv, buf) -> new PurifierMenu(windowId, inv)));

    public static void register(IEventBus modEventBus) {
        NuiCraftAttachments.register(modEventBus);
        NuiCraftBlocks.BLOCKS.register(modEventBus);
        NuiCraftItems.ITEMS.register(modEventBus);
        NuiCraftEntityTypes.ENTITY_TYPES.register(modEventBus);
        FLUIDS.register(modEventBus);
        FLUID_TYPES.register(modEventBus);
        BLOCK_ENTITY_TYPES.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
        MENU_TYPES.register(modEventBus);
    }
}
