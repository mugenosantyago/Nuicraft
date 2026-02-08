# Quick Reference: 1.12.1 → 1.21.3 Migration Patterns

## Import Changes

### Blocks & Items
```java
// OLD (1.12)
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

// NEW (1.21)
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
```

### World & Level
```java
// OLD (1.12)
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

// NEW (1.21)
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevel;
import net.minecraft.core.BlockPos;
```

### Block State
```java
// OLD (1.12)
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.properties.PropertyDirection;

// NEW (1.21)
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
```

## Block Creation

### Simple Block
```java
// OLD (1.12)
public class MyBlock extends Block {
    public MyBlock() {
        super(Material.ROCK);
        setHardness(3.0F);
        setResistance(5.0F);
        setSoundType(SoundType.STONE);
        setCreativeTab(MyMod.TAB);
        setRegistryName("my_block");
        setUnlocalizedName(MODID + ".my_block");
    }
}

// NEW (1.21)
public class MyBlock extends Block {
    public MyBlock(BlockBehaviour.Properties props) {
        super(props);
    }
    
    public static BlockBehaviour.Properties createProperties() {
        return BlockBehaviour.Properties.of()
            .strength(3.0F, 5.0F)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops();
    }
}

// In registration class:
public static final DeferredBlock<MyBlock> MY_BLOCK = 
    BLOCKS.register("my_block", () -> new MyBlock(MyBlock.createProperties()));
```

### Block with BlockState Properties
```java
// OLD (1.12)
public class DirectionalBlock extends Block {
    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    
    public DirectionalBlock() {
        super(Material.ROCK);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }
    
    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getHorizontalIndex();
    }
    
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }
}

// NEW (1.21)
public class DirectionalBlock extends HorizontalDirectionalBlock {
    public DirectionalBlock(BlockBehaviour.Properties props) {
        super(props);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
    }
    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, 
            context.getHorizontalDirection().getOpposite());
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    
    // No metadata methods - they don't exist anymore!
}
```

## Item Creation

### Simple Item
```java
// OLD (1.12)
public class MyItem extends Item {
    public MyItem() {
        setCreativeTab(MyMod.TAB);
        setRegistryName("my_item");
        setUnlocalizedName(MODID + ".my_item");
    }
}

// NEW (1.21)
// Usually just use Item directly via DeferredRegister:
public static final DeferredItem<Item> MY_ITEM = 
    ITEMS.registerSimpleItem("my_item");

// Or if you need custom behavior:
public class MyItem extends Item {
    public MyItem(Properties props) {
        super(props);
    }
}

public static final DeferredItem<MyItem> MY_ITEM = 
    ITEMS.register("my_item", () -> new MyItem(new Item.Properties()));
```

### Tool Items
```java
// OLD (1.12)
public class MySword extends ItemSword {
    public MySword(ToolMaterial material) {
        super(material);
        setCreativeTab(MyMod.TAB);
        setRegistryName("my_sword");
    }
}

// NEW (1.21)
public static final DeferredItem<SwordItem> MY_SWORD = 
    ITEMS.register("my_sword", () -> new SwordItem(
        MY_TIER,  // Custom Tier
        new Item.Properties().attributes(
            SwordItem.createAttributes(MY_TIER, 3, -2.4F)
        )
    ));
```

### Custom Tool Tier
```java
// OLD (1.12)
public static ToolMaterial MY_MATERIAL = EnumHelper.addToolMaterial(
    "MY_MATERIAL", 2, 500, 5.0F, 2.0F, 7);

// NEW (1.21)
public enum MyTier implements Tier {
    MY_TIER(
        BlockTags.INCORRECT_FOR_IRON_TOOL,  // incorrect blocks tag
        500,      // uses (durability)
        5.0F,     // speed
        2.0F,     // attack damage bonus
        7,        // enchantment value
        () -> Ingredient.of(MyItems.MY_INGOT.get())  // repair ingredient
    );
    
    private final TagKey<Block> incorrectBlocksForDrops;
    private final int uses;
    private final float speed;
    private final float attackDamageBonus;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;
    
    MyTier(TagKey<Block> incorrectBlocksForDrops, int uses, float speed, 
           float attackDamageBonus, int enchantmentValue, 
           Supplier<Ingredient> repairIngredient) {
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
        this.uses = uses;
        this.speed = speed;
        this.attackDamageBonus = attackDamageBonus;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }
    
    @Override public int getUses() { return uses; }
    @Override public float getSpeed() { return speed; }
    @Override public float getAttackDamageBonus() { return attackDamageBonus; }
    @Override public TagKey<Block> getIncorrectBlocksForDrops() { return incorrectBlocksForDrops; }
    @Override public int getEnchantmentValue() { return enchantmentValue; }
    @Override public Ingredient getRepairIngredient() { return repairIngredient.get(); }
}
```

## Registration

### Block & Item Registration
```java
// OLD (1.12)
@Mod.EventBusSubscriber
public class Registration {
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new MyBlock());
    }
    
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(MyBlocks.MY_BLOCK));
        event.getRegistry().register(new MyItem());
    }
}

// NEW (1.21)
public class MyBlocks {
    public static final DeferredRegister.Blocks BLOCKS = 
        DeferredRegister.createBlocks(MODID);
    
    public static final DeferredBlock<MyBlock> MY_BLOCK = 
        BLOCKS.register("my_block", () -> new MyBlock(MyBlock.createProperties()));
}

public class MyItems {
    public static final DeferredRegister.Items ITEMS = 
        DeferredRegister.createItems(MODID);
    
    // BlockItem automatically created:
    public static final DeferredItem<BlockItem> MY_BLOCK = 
        ITEMS.registerSimpleBlockItem(MyBlocks.MY_BLOCK);
    
    public static final DeferredItem<Item> MY_ITEM = 
        ITEMS.registerSimpleItem("my_item");
}

// In main mod class constructor:
MyBlocks.BLOCKS.register(modEventBus);
MyItems.ITEMS.register(modEventBus);
```

## Entity Registration

### Entity Type
```java
// OLD (1.12)
EntityRegistry.registerModEntity(
    new ResourceLocation(MODID, "my_entity"),
    MyEntity.class,
    "my_entity",
    1,  // id
    this,  // mod instance
    64,  // tracking range
    3,  // update frequency
    true  // send velocity updates
);

// NEW (1.21)
public class MyEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = 
        DeferredRegister.create(Registries.ENTITY_TYPE, MODID);
    
    public static final DeferredHolder<EntityType<?>, EntityType<MyEntity>> MY_ENTITY =
        ENTITIES.register("my_entity", () -> EntityType.Builder.of(
            MyEntity::new,
            MobCategory.CREATURE
        )
        .sized(0.6F, 1.8F)  // hitbox size
        .clientTrackingRange(64)
        .updateInterval(3)
        .build(ResourceLocation.fromNamespaceAndPath(MODID, "my_entity").toString()));
}
```

## Creative Tabs

```java
// OLD (1.12)
public static CreativeTabs MY_TAB = new CreativeTabs("my_tab") {
    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(MyItems.MY_ITEM);
    }
};

// NEW (1.21)
public static final DeferredRegister<CreativeModeTab> TABS = 
    DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MY_TAB = 
    TABS.register("my_tab", () -> CreativeModeTab.builder()
        .title(Component.translatable("itemGroup.my_tab"))
        .icon(() -> new ItemStack(MyItems.MY_ITEM.get()))
        .displayItems((parameters, output) -> {
            output.accept(MyItems.MY_ITEM.get());
            output.accept(MyBlocks.MY_BLOCK.get());
        })
        .build());
```

## Events

```java
// OLD (1.12)
@SubscribeEvent
public void onPlayerTick(TickEvent.PlayerTickEvent event) {
    if (event.phase == TickEvent.Phase.START) {
        // Do stuff
    }
}

// NEW (1.21)
@SubscribeEvent
public void onPlayerTick(PlayerTickEvent.Pre event) {  // or .Post
    Player player = event.getEntity();
    // Do stuff
}
```

## Client-Side Operations

```java
// OLD (1.12)
@SidedProxy(clientSide = "...", serverSide = "...")
public static CommonProxy proxy;

proxy.registerRenderers();

// NEW (1.21)
// In main mod class:
modEventBus.addListener(this::clientSetup);

private void clientSetup(FMLClientSetupEvent event) {
    event.enqueueWork(() -> {
        // Client-only registration
        EntityRenderers.register(MyEntities.MY_ENTITY.get(), MyEntityRenderer::new);
    });
}

// Or use DistExecutor:
DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
    // Client code
});
```

## Common Pitfalls

### ❌ Don't Do This:
```java
// Using metadata (doesn't exist anymore)
itemStack.getMetadata()
block.getMetaFromState(state)

// Setting registry name in constructor
setRegistryName("name")
setUnlocalizedName("name")

// Using proxies
@SidedProxy

// Using Item.REGISTRY or Block.REGISTRY
Item.REGISTRY.getObject(...)
```

### ✅ Do This Instead:
```java
// Use NBT for item variants
itemStack.getOrCreateTag().putInt("variant", 1)

// Registry names handled by DeferredRegister
ITEMS.register("name", () -> new MyItem(...))

// Use DistExecutor or level.isClientSide()
DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {...})

// Use DeferredRegister
DeferredRegister<Item> ITEMS = DeferredRegister.create(...)
```

## Testing Compilation

```bash
# Clean build
./gradlew clean

# Try to compile
./gradlew build

# Run client (once it compiles)
./gradlew runClient

# Run server
./gradlew runServer

# Generate run configurations for IDE
./gradlew genIntellijRuns  # or genEclipseRuns, genVSCodeRuns
```

## Common Error Messages

### "Cannot find symbol: Material"
**Fix:** Use `BlockBehaviour.Properties.of()` instead of `Material.ROCK`, etc.

### "Cannot resolve method setUnlocalizedName"
**Fix:** Remove it - localization uses lang JSON files with registry names

### "Cannot resolve method getMetaFromState"
**Fix:** Remove metadata system - use separate items or NBT

### "Package net.minecraftforge does not exist"
**Fix:** Change imports to `net.neoforged` for NeoForge-specific APIs

### "Cannot find FMLPreInitializationEvent"
**Fix:** Use `FMLCommonSetupEvent`, `FMLClientSetupEvent`, etc. in mod constructor via event bus

---

Keep this reference handy while migrating! It covers the most common patterns you'll encounter.
