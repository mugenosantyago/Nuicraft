package eastonium.nuicraft;

import com.mojang.logging.LogUtils;
import eastonium.nuicraft.core.NuiCraftBlocks;
import eastonium.nuicraft.core.NuiCraftEntityAttributes;
import eastonium.nuicraft.core.NuiCraftItems;
import eastonium.nuicraft.core.NuiCraftRegistration;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod(NuiCraft.MODID)
public class NuiCraft {
    public static final String MODID = "nuicraft";
    public static final Logger LOGGER = LogUtils.getLogger();
    
    // Creative tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NUICRAFT_TAB = CREATIVE_MODE_TABS.register("nuicraft_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.nuicraft_tab"))
            .icon(() -> new ItemStack(NuiCraftBlocks.NUVA_CUBE.get()))
            .displayItems((parameters, output) -> {
                // Add all items from the mod to the creative tab
                NuiCraftItems.ITEMS.getEntries().forEach(item -> output.accept(item.get()));
            })
            .build());
    
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NUICRAFT_MASK_TAB = CREATIVE_MODE_TABS.register("nuicraft_mask_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.nuicraft_mask_tab"))
            .icon(() -> new ItemStack(NuiCraftItems.MASK_MATA_HAU.get()))
            .displayItems((parameters, output) -> {
                // Add all Mata masks
                output.accept(NuiCraftItems.MASK_MATA_AKAKU.get());
                output.accept(NuiCraftItems.MASK_MATA_HAU.get());
                output.accept(NuiCraftItems.MASK_MATA_HUNA.get());
                output.accept(NuiCraftItems.MASK_MATA_KAKAMA.get());
                output.accept(NuiCraftItems.MASK_MATA_KAUKAU.get());
                output.accept(NuiCraftItems.MASK_MATA_KOMAU.get());
                output.accept(NuiCraftItems.MASK_MATA_MAHIKI.get());
                output.accept(NuiCraftItems.MASK_MATA_MATATU.get());
                output.accept(NuiCraftItems.MASK_MATA_MIRU.get());
                output.accept(NuiCraftItems.MASK_MATA_PAKARI.get());
                output.accept(NuiCraftItems.MASK_MATA_RARU.get());
                output.accept(NuiCraftItems.MASK_MATA_RURU.get());
            })
            .build());
    
    public NuiCraft(IEventBus modEventBus) {
        LOGGER.info("Initializing NuiCraft - The essence of the Bionicle Universe in Minecraft!");
        
        // Register deferred registers
        NuiCraftRegistration.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        
        // Register mod event listeners
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(NuiCraftEntityAttributes::registerEntityAttributes);
        
        // Register event handlers
        NeoForge.EVENT_BUS.register(new ServerTickHandler());
        
        // Client-side event handlers (only register on client)
        if (net.neoforged.fml.loading.FMLEnvironment.dist.isClient()) {
            NeoForge.EVENT_BUS.register(new ClientTickHandler());
            eastonium.nuicraft.client.NuiCraftClient.registerModBusEvents(modEventBus);
        }
    }
    
    private void commonSetup(net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent event) {
        LOGGER.info("NuiCraft common setup");
        
        event.enqueueWork(() -> {
            // Common setup tasks
        });
    }
}
