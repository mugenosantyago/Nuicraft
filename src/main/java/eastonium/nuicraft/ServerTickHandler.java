package eastonium.nuicraft;

import eastonium.nuicraft.core.NuiCraftItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.List;

/**
 * Applies Kanohi mask powers to players wearing masks.
 *
 * Attribute-based powers (speed, strength, knockback resist, reach) are
 * handled automatically via ItemAttributeModifiers on the items.
 *
 * Potion-effect powers are applied here every 2 seconds with a 3-second
 * duration so they persist while worn and fade quickly when removed.
 */
public class ServerTickHandler {

    // Apply effects every 40 ticks (2 seconds), duration 60 ticks (3 seconds)
    private static final int APPLY_INTERVAL = 40;
    private static final int EFFECT_DURATION = 60;

    // Komau aura range in blocks
    private static final double KOMAU_RANGE = 8.0;

    @SubscribeEvent
    public void onServerTick(ServerTickEvent.Post event) {
        for (ServerLevel level : event.getServer().getAllLevels()) {
            if (level.getGameTime() % APPLY_INTERVAL != 0) continue;

            for (ServerPlayer player : level.players()) {
                ItemStack headSlot = player.getItemBySlot(EquipmentSlot.HEAD);
                if (headSlot.isEmpty()) continue;

                Item mask = headSlot.getItem();
                applyMaskPower(player, mask, level);
            }
        }
    }

    private void applyMaskPower(ServerPlayer player, Item mask, ServerLevel level) {
        // Hau - Mask of Shielding: Resistance I
        if (mask == NuiCraftItems.MASK_MATA_HAU.get()) {
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, EFFECT_DURATION, 0, true, false, true));
        }
        // Kaukau - Mask of Water Breathing
        else if (mask == NuiCraftItems.MASK_MATA_KAUKAU.get()) {
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, EFFECT_DURATION, 0, true, false, true));
        }
        // Miru - Mask of Levitation: Slow Falling + Jump Boost I
        else if (mask == NuiCraftItems.MASK_MATA_MIRU.get()) {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, EFFECT_DURATION, 0, true, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, EFFECT_DURATION, 0, true, false, true));
        }
        // Akaku - Mask of X-Ray Vision: Night Vision
        else if (mask == NuiCraftItems.MASK_MATA_AKAKU.get()) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, EFFECT_DURATION, 0, true, false, true));
        }
        // Huna - Mask of Concealment: Invisibility
        else if (mask == NuiCraftItems.MASK_MATA_HUNA.get()) {
            player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, EFFECT_DURATION, 0, true, false, true));
        }
        // Mahiki - Mask of Illusion: Invisibility
        else if (mask == NuiCraftItems.MASK_MATA_MAHIKI.get()) {
            player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, EFFECT_DURATION, 0, true, false, true));
        }
        // Raru - Mask of Translation: Luck I
        else if (mask == NuiCraftItems.MASK_MATA_RARU.get()) {
            player.addEffect(new MobEffectInstance(MobEffects.LUCK, EFFECT_DURATION, 0, true, false, true));
        }
        // Ruru - Mask of Night Vision: Night Vision
        else if (mask == NuiCraftItems.MASK_MATA_RURU.get()) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, EFFECT_DURATION, 0, true, false, true));
        }
        // Komau - Mask of Mind Control: Weakness + Slowness to nearby hostile mobs
        else if (mask == NuiCraftItems.MASK_MATA_KOMAU.get()) {
            AABB aura = player.getBoundingBox().inflate(KOMAU_RANGE);
            List<Monster> nearbyMobs = level.getEntitiesOfClass(Monster.class, aura);
            for (Monster mob : nearbyMobs) {
                mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, EFFECT_DURATION, 0, true, true, true));
                mob.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, EFFECT_DURATION, 0, true, true, true));
            }
        }
        // Kakama, Pakari, Matatu - attribute-only masks, no potion effects needed
    }
}
