package eastonium.nuicraft.core;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.blockentity.PurifierBlockEntity;
import eastonium.nuicraft.menu.ElementSwiperMenu;
import eastonium.nuicraft.menu.PurifierMenu;
import eastonium.nuicraft.morph.NuiCraftAttachments;
import eastonium.nuicraft.recipe.PurifyingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
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
    public static final DeferredRegister<RecipeType<?>>      RECIPE_TYPES       = DeferredRegister.create(Registries.RECIPE_TYPE, NuiCraft.MODID);
    public static final DeferredRegister<MenuType<?>>        MENU_TYPES         = DeferredRegister.create(Registries.MENU, NuiCraft.MODID);

    /** Recipe type for the Purifier — processed only by the Purifier, not vanilla furnaces. */
    public static final DeferredHolder<RecipeType<?>, RecipeType<PurifyingRecipe>> PURIFYING_TYPE =
            RECIPE_TYPES.register("purifying",
                    () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "purifying")));

    /** Serializer for purifying recipes — mirrors the smelting serializer pattern, default 200 ticks. */
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<PurifyingRecipe>> PURIFYING_SERIALIZER =
            RECIPE_SERIALIZERS.register("purifying",
                    () -> new AbstractCookingRecipe.Serializer<>(PurifyingRecipe::new, 200));

    /** Purifier block entity type — referenced by BlockPurifier and PurifierBlockEntity. */
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PurifierBlockEntity>> PURIFIER_BE =
            BLOCK_ENTITY_TYPES.register("purifier", () ->
                    new BlockEntityType<>(PurifierBlockEntity::new, NuiCraftBlocks.PURIFIER.get()));

    /** Purifier menu type — referenced by PurifierMenu and PurifierScreen. */
    public static final DeferredHolder<MenuType<?>, MenuType<PurifierMenu>> PURIFIER_MENU =
            MENU_TYPES.register("purifier", () ->
                    IMenuTypeExtension.create((windowId, inv, buf) -> new PurifierMenu(windowId, inv)));

    /** Element Swiper menu type. */
    public static final DeferredHolder<MenuType<?>, MenuType<ElementSwiperMenu>> ELEMENT_SWIPER_MENU =
            MENU_TYPES.register("element_swiper", () ->
                    IMenuTypeExtension.create((windowId, inv, buf) -> new ElementSwiperMenu(windowId, inv)));

    public static void register(IEventBus modEventBus) {
        NuiCraftAttachments.register(modEventBus);
        NuiCraftBlocks.BLOCKS.register(modEventBus);
        NuiCraftItems.ITEMS.register(modEventBus);
        NuiCraftEntityTypes.ENTITY_TYPES.register(modEventBus);
        FLUIDS.register(modEventBus);
        FLUID_TYPES.register(modEventBus);
        BLOCK_ENTITY_TYPES.register(modEventBus);
        RECIPE_TYPES.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
        MENU_TYPES.register(modEventBus);
    }
}
