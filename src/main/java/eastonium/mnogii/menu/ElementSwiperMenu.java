package eastonium.mnogii.menu;

import eastonium.mnogii.core.MnogiiItems;
import eastonium.mnogii.core.MnogiiRegistration;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ElementSwiperMenu extends AbstractContainerMenu {

    // The 6 toa stones in display order (fire, water, air, earth, rock, ice)
    static final Item[] TOA_STONES = {
            MnogiiItems.FIRE_TOA_STONE.get(),
            MnogiiItems.WATER_TOA_STONE.get(),
            MnogiiItems.AIR_TOA_STONE.get(),
            MnogiiItems.EARTH_TOA_STONE.get(),
            MnogiiItems.ROCK_TOA_STONE.get(),
            MnogiiItems.ICE_TOA_STONE.get()
    };

    public final SimpleContainer inputContainer;
    private final SimpleContainer resultContainer = new SimpleContainer(6);

    // ── Slot index constants ──────────────────────────────────────────────────
    public static final int SLOT_INPUT   = 0;
    public static final int SLOT_RESULT_START = 1;   // slots 1-6
    public static final int SLOT_INV_START    = 7;   // slots 7-33 (main inv)
    public static final int SLOT_HOT_START    = 34;  // slots 34-42 (hotbar)

    // Layout constants (relative to menu origin, not screen origin)
    // Input slot sits on the left; 3×2 result grid on the right
    public static final int INPUT_X = 22, INPUT_Y = 28;
    // Result grid: 3 columns, 2 rows, 24px spacing
    public static final int RESULT_X0 = 76, RESULT_Y0 = 10, RESULT_STEP = 24;

    public ElementSwiperMenu(int containerId, Inventory playerInventory) {
        super(MnogiiRegistration.ELEMENT_SWIPER_MENU.get(), containerId);

        final Player player = playerInventory.player;

        // Input container — notifies slotsChanged() when modified
        this.inputContainer = new SimpleContainer(1) {
            @Override
            public void setChanged() {
                super.setChanged();
                ElementSwiperMenu.this.slotsChanged(this);
            }
        };

        // Slot 0 — input (accepts toa stones only)
        this.addSlot(new Slot(inputContainer, 0, INPUT_X, INPUT_Y) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return isToaStone(stack);
            }
        });

        // Slots 1-6 — result stones (read-only, triggers conversion on take)
        for (int i = 0; i < 6; i++) {
            final int stoneIdx = i;
            int rx = RESULT_X0 + (i % 3) * RESULT_STEP;
            int ry = RESULT_Y0 + (i / 3) * RESULT_STEP;
            this.addSlot(new Slot(resultContainer, i, rx, ry) {
                @Override
                public boolean mayPlace(ItemStack s) { return false; }

                @Override
                public boolean mayPickup(Player p) {
                    return !inputContainer.getItem(0).isEmpty();
                }

                @Override
                public void onTake(Player p, ItemStack taken) {
                    inputContainer.removeItem(0, 1);
                    consumeSwiper(p);
                    slotsChanged(inputContainer);
                }
            });
        }

        // Player main inventory (slots 7-33)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory,
                        col + row * 9 + 9,
                        8 + col * 18, 76 + row * 18));
            }
        }
        // Player hotbar (slots 34-42)
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 130));
        }
    }

    // ── Logic ─────────────────────────────────────────────────────────────────

    @Override
    public void slotsChanged(Container container) {
        ItemStack input = inputContainer.getItem(0);
        if (isToaStone(input)) {
            for (int i = 0; i < 6; i++) {
                resultContainer.setItem(i, new ItemStack(TOA_STONES[i]));
            }
        } else {
            for (int i = 0; i < 6; i++) {
                resultContainer.setItem(i, ItemStack.EMPTY);
            }
        }
        super.slotsChanged(container);
    }

    public static boolean isToaStone(ItemStack stack) {
        for (Item s : TOA_STONES) {
            if (stack.is(s)) return true;
        }
        return false;
    }

    /** Removes one Element Swiper from the player's inventory. */
    private static void consumeSwiper(Player player) {
        var inv = player.getInventory();
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack s = inv.getItem(i);
            if (s.is(MnogiiItems.ELEMENT_SWIPER.get())) {
                s.shrink(1);
                return;
            }
        }
    }

    @Override
    public boolean stillValid(Player player) { return true; }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (!slot.hasItem()) return result;

        ItemStack stack = slot.getItem();
        result = stack.copy();

        if (index == SLOT_INPUT) {
            // Input → player inventory
            if (!this.moveItemStackTo(stack, SLOT_INV_START, SLOT_HOT_START + 9, true))
                return ItemStack.EMPTY;
        } else if (index >= SLOT_INV_START) {
            // Player inventory → input (if it's a toa stone)
            if (isToaStone(stack)) {
                if (!this.moveItemStackTo(stack, SLOT_INPUT, SLOT_INPUT + 1, false))
                    return ItemStack.EMPTY;
            } else {
                return ItemStack.EMPTY;
            }
        } else {
            return ItemStack.EMPTY; // result slots not shift-clickable
        }

        if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
        else slot.setChanged();
        if (stack.getCount() == result.getCount()) return ItemStack.EMPTY;
        slot.onTake(player, stack);
        return result;
    }
}
