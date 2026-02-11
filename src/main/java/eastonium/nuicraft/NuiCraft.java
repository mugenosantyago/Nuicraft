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
            NeoForge.EVENT_BUS.register(new eastonium.nuicraft.client.MaskTooltipHandler());
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
