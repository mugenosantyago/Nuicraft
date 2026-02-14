package eastonium.nuicraft.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * Simple dialogue screen showing an NPC name and message with a Close button.
 */
public class DialogueScreen extends Screen {

    private static final int PANEL_PADDING = 16;
    private static final int PANEL_WIDTH = 280;
    private static final int LINE_HEIGHT = 12;
    private static final int MAX_MESSAGE_WIDTH = PANEL_WIDTH - PANEL_PADDING * 2;

    private final Component title;
    private final Component message;

    public DialogueScreen(Component title, Component message) {
        super(Component.empty());
        this.title = title;
        this.message = message;
    }

    @Override
    protected void init() {
        super.init();
        int buttonWidth = 120;
        int buttonHeight = 20;
        int buttonX = (this.width - buttonWidth) / 2;
        int buttonY = this.height / 2 + 60;
        this.addRenderableWidget(
                Button.builder(Component.translatable("gui.nuicraft.dialogue.close"), btn -> this.onClose())
                        .bounds(buttonX, buttonY, buttonWidth, buttonHeight)
                        .build()
        );
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        int centerX = this.width / 2;
        int panelLeft = centerX - PANEL_WIDTH / 2;
        int panelTop = this.height / 2 - 80;
        int panelRight = panelLeft + PANEL_WIDTH;
        int panelBottom = panelTop + 160;

        // Semi-transparent panel background
        guiGraphics.fill(panelLeft, panelTop, panelRight, panelBottom, 0xC0_20_20_20);
        guiGraphics.fill(panelLeft, panelTop, panelRight, panelTop + 1, 0xFF_40_40_40);
        guiGraphics.fill(panelLeft, panelBottom - 1, panelRight, panelBottom, 0xFF_20_20_20);

        // Title (NPC name)
        int titleY = panelTop + PANEL_PADDING;
        guiGraphics.drawCenteredString(this.font, this.title, centerX, titleY, 0xFF_FF_CC_00);

        // Message (wrapped)
        int messageY = titleY + LINE_HEIGHT + 8;
        for (var line : this.font.split(this.message, MAX_MESSAGE_WIDTH)) {
            guiGraphics.drawCenteredString(this.font, line, centerX, messageY, 0xFF_EE_EE_EE);
            messageY += LINE_HEIGHT;
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
