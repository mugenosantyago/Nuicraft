package eastonium.mnogii.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import eastonium.mnogii.menu.ElementSwiperMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ElementSwiperScreen extends AbstractContainerScreen<ElementSwiperMenu> {

    // Colours
    private static final int BG_OUTER   = 0xFF1A1A2E;
    private static final int BG_PANEL   = 0xFF16213E;
    private static final int BG_INV     = 0xFF0F3460;
    private static final int SLOT_DARK  = 0xFF080808;
    private static final int SLOT_MID   = 0xFF2E2E4A;
    private static final int SLOT_EMPTY = 0xFF1C1C30;
    private static final int SEP_COLOR  = 0xFF533483;
    private static final int LABEL_COL  = 0xFFAAAAAA;

    // Label names for the 6 element stones
    private static final String[] STONE_LABELS = {
            "Fire", "Water", "Air", "Earth", "Rock", "Ice"
    };

    public ElementSwiperScreen(ElementSwiperMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth  = 176;
        this.imageHeight = 158;
        this.inventoryLabelY = this.imageHeight - 82;
        this.titleLabelY = 4;
    }

    @Override
    protected void renderBg(GuiGraphics g, float partialTick, int mouseX, int mouseY) {
        int x = this.leftPos, y = this.topPos;

        // ── Overall background ──────────────────────────────────────────────
        g.fill(x, y, x + imageWidth, y + imageHeight, BG_OUTER);

        // ── Custom content panel (top half) ─────────────────────────────────
        g.fill(x + 6, y + 6, x + 170, y + 68, BG_PANEL);

        // ── Separator between custom area and player inv ─────────────────────
        g.fill(x + 6,  y + 70, x + 170, y + 71, SEP_COLOR);
        g.fill(x + 6,  y + 71, x + 170, y + 72, 0xFF2A184E);

        // ── Player inventory panel ───────────────────────────────────────────
        g.fill(x + 6, y + 74, x + 170, y + 152, BG_INV);

        // ── Input slot area + label ──────────────────────────────────────────
        g.drawString(this.font, "Toa Stone:", x + 8, y + 10, LABEL_COL, false);
        drawSlot(g, x + ElementSwiperMenu.INPUT_X, y + ElementSwiperMenu.INPUT_Y,
                !this.menu.inputContainer.getItem(0).isEmpty());

        // ── Arrow ────────────────────────────────────────────────────────────
        g.drawString(this.font, "\u2192", x + 54, y + 31, 0xFFFFFFFF, false);

        // ── Result slots + labels ────────────────────────────────────────────
        g.drawString(this.font, "Select:", x + 74, y + 10, LABEL_COL, false);
        boolean hasInput = !this.menu.inputContainer.getItem(0).isEmpty();
        for (int i = 0; i < 6; i++) {
            int rx = x + ElementSwiperMenu.RESULT_X0 + (i % 3) * ElementSwiperMenu.RESULT_STEP;
            int ry = y + ElementSwiperMenu.RESULT_Y0 + (i / 3) * ElementSwiperMenu.RESULT_STEP;
            drawSlot(g, rx, ry, hasInput);
        }

        // ── Player inventory slot backgrounds ────────────────────────────────
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                drawSlot(g, x + 7 + col * 18, y + 75 + row * 18, true);
            }
        }
        for (int col = 0; col < 9; col++) {
            drawSlot(g, x + 7 + col * 18, y + 129, true);
        }
    }

    /** Renders an 18×18 slot background. {@code active} controls brightness. */
    private static void drawSlot(GuiGraphics g, int x, int y, boolean active) {
        int inner = active ? SLOT_MID : SLOT_EMPTY;
        g.fill(x,     y,     x + 18, y + 18, SLOT_DARK);
        g.fill(x + 1, y + 1, x + 17, y + 17, inner);
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        super.render(g, mouseX, mouseY, partialTick);
        this.renderTooltip(g, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics g, int mouseX, int mouseY) {
        // Title
        g.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 0xFFE0C060, false);
        // Player inventory label
        g.drawString(this.font, this.playerInventoryTitle,
                8, this.inventoryLabelY, LABEL_COL, false);
    }
}
