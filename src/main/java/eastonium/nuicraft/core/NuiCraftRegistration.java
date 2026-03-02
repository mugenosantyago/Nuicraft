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
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Consumer;

public class NuiCraftRegistration {
    public static final DeferredRegister<Fluid>              FLUIDS             = DeferredRegister.create(Registries.FLUID, NuiCraft.MODID);
    public static final DeferredRegister<FluidType>          FLUID_TYPES        = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, NuiCraft.MODID);

    // =========================================================================
    // Protodermis fluids — 4 variants, each has a FluidType, source, and flowing
    // =========================================================================

    // ---- Regular liquid protodermis (silver) ----
    public static final DeferredHolder<FluidType, FluidType> TYPE_PROTODERMIS =
        FLUID_TYPES.register("protodermis", () -> new FluidType(
            FluidType.Properties.create().density(1000).viscosity(1000)) {
            @Override public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(new IClientFluidTypeExtensions() {
                    private static final ResourceLocation STILL = ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still");
                    private static final ResourceLocation FLOW  = ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow");
                    @Override public ResourceLocation getStillTexture() { return STILL; }
                    @Override public ResourceLocation getFlowingTexture() { return FLOW; }
                    @Override public int getTintColor() { return 0xFFC4D8E0; } // silver-blue
                });
            }
        });

    public static final DeferredHolder<Fluid, FlowingFluid> SOURCE_PROTODERMIS =
        FLUIDS.register("protodermis", () -> new BaseFlowingFluid.Source(protoProps()));
    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_PROTODERMIS =
        FLUIDS.register("flowing_protodermis", () -> new BaseFlowingFluid.Flowing(protoProps()));

    private static BaseFlowingFluid.Properties protoProps() {
        return new BaseFlowingFluid.Properties(TYPE_PROTODERMIS, SOURCE_PROTODERMIS, FLOWING_PROTODERMIS)
            .block(() -> (LiquidBlock) NuiCraftBlocks.FLUID_PROTODERMIS.get())
            .bucket(() -> NuiCraftItems.PROTODERMIS_BUCKET.get());
    }

    // ---- Pure liquid protodermis (gold shimmer) ----
    public static final DeferredHolder<FluidType, FluidType> TYPE_PROTODERMIS_PURE =
        FLUID_TYPES.register("protodermis_pure", () -> new FluidType(
            FluidType.Properties.create().density(1100).viscosity(1100)) {
            @Override public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(new IClientFluidTypeExtensions() {
                    private static final ResourceLocation STILL = ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still");
                    private static final ResourceLocation FLOW  = ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow");
                    @Override public ResourceLocation getStillTexture() { return STILL; }
                    @Override public ResourceLocation getFlowingTexture() { return FLOW; }
                    @Override public int getTintColor() { return 0xFFE8D880; } // golden
                });
            }
        });

    public static final DeferredHolder<Fluid, FlowingFluid> SOURCE_PROTODERMIS_PURE =
        FLUIDS.register("protodermis_pure", () -> new BaseFlowingFluid.Source(protoPureProps()));
    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_PROTODERMIS_PURE =
        FLUIDS.register("flowing_protodermis_pure", () -> new BaseFlowingFluid.Flowing(protoPureProps()));

    private static BaseFlowingFluid.Properties protoPureProps() {
        return new BaseFlowingFluid.Properties(TYPE_PROTODERMIS_PURE, SOURCE_PROTODERMIS_PURE, FLOWING_PROTODERMIS_PURE)
            .block(() -> (LiquidBlock) NuiCraftBlocks.FLUID_PROTODERMIS_PURE.get())
            .bucket(() -> NuiCraftItems.PURE_PROTODERMIS_BUCKET.get());
    }

    // ---- Molten protodermis (orange glow) ----
    public static final DeferredHolder<FluidType, FluidType> TYPE_PROTODERMIS_MOLTEN =
        FLUID_TYPES.register("protodermis_molten", () -> new FluidType(
            FluidType.Properties.create().density(3000).viscosity(6000).temperature(1200)) {
            @Override public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(new IClientFluidTypeExtensions() {
                    private static final ResourceLocation STILL = ResourceLocation.fromNamespaceAndPath("minecraft", "block/lava_still");
                    private static final ResourceLocation FLOW  = ResourceLocation.fromNamespaceAndPath("minecraft", "block/lava_flow");
                    @Override public ResourceLocation getStillTexture() { return STILL; }
                    @Override public ResourceLocation getFlowingTexture() { return FLOW; }
                    @Override public int getTintColor() { return 0xFFE05020; } // deep orange-red
                });
            }
        });

    public static final DeferredHolder<Fluid, FlowingFluid> SOURCE_PROTODERMIS_MOLTEN =
        FLUIDS.register("protodermis_molten", () -> new BaseFlowingFluid.Source(protoMoltenProps()));
    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_PROTODERMIS_MOLTEN =
        FLUIDS.register("flowing_protodermis_molten", () -> new BaseFlowingFluid.Flowing(protoMoltenProps()));

    private static BaseFlowingFluid.Properties protoMoltenProps() {
        return new BaseFlowingFluid.Properties(TYPE_PROTODERMIS_MOLTEN, SOURCE_PROTODERMIS_MOLTEN, FLOWING_PROTODERMIS_MOLTEN)
            .tickRate(30)   // slower flow than water, like lava
            .block(() -> (LiquidBlock) NuiCraftBlocks.FLUID_PROTODERMIS_MOLTEN.get())
            .bucket(() -> NuiCraftItems.MOLTEN_PROTODERMIS_BUCKET.get());
    }

    // ---- Pure molten protodermis (bright gold) ----
    public static final DeferredHolder<FluidType, FluidType> TYPE_PROTODERMIS_PURE_MOLTEN =
        FLUID_TYPES.register("protodermis_pure_molten", () -> new FluidType(
            FluidType.Properties.create().density(3500).viscosity(7000).temperature(1400)) {
            @Override public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(new IClientFluidTypeExtensions() {
                    private static final ResourceLocation STILL = ResourceLocation.fromNamespaceAndPath("minecraft", "block/lava_still");
                    private static final ResourceLocation FLOW  = ResourceLocation.fromNamespaceAndPath("minecraft", "block/lava_flow");
                    @Override public ResourceLocation getStillTexture() { return STILL; }
                    @Override public ResourceLocation getFlowingTexture() { return FLOW; }
                    @Override public int getTintColor() { return 0xFFFFCC00; } // bright gold
                });
            }
        });

    public static final DeferredHolder<Fluid, FlowingFluid> SOURCE_PROTODERMIS_PURE_MOLTEN =
        FLUIDS.register("protodermis_pure_molten", () -> new BaseFlowingFluid.Source(protoPureMoltenProps()));
    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_PROTODERMIS_PURE_MOLTEN =
        FLUIDS.register("flowing_protodermis_pure_molten", () -> new BaseFlowingFluid.Flowing(protoPureMoltenProps()));

    private static BaseFlowingFluid.Properties protoPureMoltenProps() {
        return new BaseFlowingFluid.Properties(TYPE_PROTODERMIS_PURE_MOLTEN, SOURCE_PROTODERMIS_PURE_MOLTEN, FLOWING_PROTODERMIS_PURE_MOLTEN)
            .tickRate(40)
            .block(() -> (LiquidBlock) NuiCraftBlocks.FLUID_PROTODERMIS_PURE_MOLTEN.get())
            .bucket(() -> NuiCraftItems.PURE_MOLTEN_PROTODERMIS_BUCKET.get());
    }
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
