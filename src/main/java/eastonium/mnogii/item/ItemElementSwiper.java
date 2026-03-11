package eastonium.mnogii.item;

import eastonium.mnogii.menu.ElementSwiperMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * The Element Swiper opens a GUI that lets you exchange any Toa Stone for
 * a different elemental Toa Stone. Each conversion consumes one swiper from
 * the player's inventory.
 */
public class ItemElementSwiper extends Item {

    private static final Component TITLE =
            Component.translatable("container.mnogii.element_swiper");

    public ItemElementSwiper(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            player.openMenu(new SimpleMenuProvider(
                    (id, inv, p) -> new ElementSwiperMenu(id, inv),
                    TITLE));
        }
        return InteractionResult.SUCCESS;
    }
}
