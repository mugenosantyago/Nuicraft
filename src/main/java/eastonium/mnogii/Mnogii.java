package eastonium.mnogii;

import com.mojang.logging.LogUtils;
import eastonium.mnogii.core.MnogiiBlocks;
import eastonium.mnogii.core.MnogiiEntityAttributes;
import eastonium.mnogii.core.MnogiiItems;
import eastonium.mnogii.core.MnogiiRegistration;
import eastonium.mnogii.core.MnogiiSpawnPlacements;
import eastonium.mnogii.config.MnogiiConfig;
import eastonium.mnogii.event.DialogueEventHandler;
import eastonium.mnogii.event.KoroSpawnHandler;
import eastonium.mnogii.event.MnogiiAnimationSyncHandler;
import eastonium.mnogii.network.MnogiiPayloads;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod(Mnogii.MODID)
public class Mnogii {
    public static final String MODID = "mnogii";
    public static final Logger LOGGER = LogUtils.getLogger();
    
    // Creative tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MNOGII_TAB = CREATIVE_MODE_TABS.register("mnogii_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.mnogii_tab"))
            .icon(() -> new ItemStack(MnogiiBlocks.NUVA_CUBE.get()))
            .displayItems((parameters, output) -> {
                MnogiiItems.ITEMS.getEntries().stream()
                    .filter(item -> {
                        String id = item.getId().getPath();
                        return !id.contains("akaku") && !id.contains("komau");
                    })
                    .forEach(item -> output.accept(item.get()));
            })
            .build());
    
    public Mnogii(IEventBus modEventBus) {
        LOGGER.info("Initializing Mnogii - The essence of the Bionicle Universe in Minecraft!");
        
        // Register deferred registers
        MnogiiRegistration.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        
        // Register mod event listeners
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(MnogiiEntityAttributes::registerEntityAttributes);
        modEventBus.addListener(MnogiiPayloads::register);
        modEventBus.addListener(MnogiiSpawnPlacements::register);
        ModList.get().getModContainerById(MODID).ifPresent(c -> c.registerConfig(ModConfig.Type.COMMON, MnogiiConfig.SPEC));

        // Game events
        NeoForge.EVENT_BUS.register(DialogueEventHandler.class);
        NeoForge.EVENT_BUS.register(KoroSpawnHandler.class);
        NeoForge.EVENT_BUS.register(MnogiiAnimationSyncHandler.class);
        
        // Client-side (renderers, Gukko input when riding)
        if (net.neoforged.fml.loading.FMLEnvironment.dist.isClient()) {
            eastonium.mnogii.client.MnogiiClient.registerModBusEvents(modEventBus);
        }
    }
    
    private void commonSetup(net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent event) {
        LOGGER.info("Mnogii common setup");
        
        event.enqueueWork(() -> {
            // Common setup tasks
        });
    }
}
