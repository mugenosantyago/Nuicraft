package eastonium.nuicraft;

import eastonium.nuicraft.core.NuiCraftItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.List;

/**
 * Applies all Kanohi mask powers: both attribute modifiers and potion effects.
 * Attribute modifiers are applied as transient modifiers (auto-removed when mask is unequipped).
 * This avoids the vanilla armor rendering issues caused by ItemAttributeModifiers components.
 */
public class ServerTickHandler {

    private static final int EFFECT_INTERVAL = 40;
    private static final int EFFECT_DURATION = 60;
    private static final double KOMAU_RANGE = 8.0;

    // Unique modifier IDs for each mask stat
    private static final ResourceLocation MOD_ARMOR = ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "mask_armor");
    private static final ResourceLocation MOD_TOUGHNESS = ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "mask_toughness");
    private static final ResourceLocation MOD_SPEED = ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "mask_speed");
    private static final ResourceLocation MOD_ATTACK = ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "mask_attack");
    private static final ResourceLocation MOD_KB_RESIST = ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "mask_kb_resist");
    private static final ResourceLocation MOD_REACH = ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "mask_reach");

    @SubscribeEvent
    public void onServerTick(ServerTickEvent.Post event) {
        for (ServerLevel level : event.getServer().getAllLevels()) {
            for (ServerPlayer player : level.players()) {
                ItemStack headSlot = player.getItemBySlot(EquipmentSlot.HEAD);
                Item mask = headSlot.isEmpty() ? null : headSlot.getItem();

                // Apply or remove attribute modifiers based on current mask
                updateMaskAttributes(player, mask);

                // Apply potion effects periodically
                if (mask != null && level.getGameTime() % EFFECT_INTERVAL == 0) {
                    applyMaskEffects(player, mask, level);
                }
            }
        }
    }

    /**
     * Apply transient attribute modifiers for the worn mask.
     * Removes old modifiers if the mask changed.
     */
    private void updateMaskAttributes(ServerPlayer player, Item mask) {
        // Determine desired stats
        double armor = 0, toughness = 0, speed = 0, attack = 0, kbResist = 0, reach = 0;

        if (mask == NuiCraftItems.MASK_MATA_HAU.get()) {
            armor = 4; toughness = 2;
        } else if (mask == NuiCraftItems.MASK_MATA_KAUKAU.get()) {
            armor = 2;
        } else if (mask == NuiCraftItems.MASK_MATA_MIRU.get()) {
            armor = 2;
        } else if (mask == NuiCraftItems.MASK_MATA_KAKAMA.get()) {
            armor = 2; speed = 0.4;
        } else if (mask == NuiCraftItems.MASK_MATA_PAKARI.get()) {
            armor = 3; toughness = 1; attack = 3;
        } else if (mask == NuiCraftItems.MASK_MATA_AKAKU.get()) {
            armor = 2;
        } else if (mask == NuiCraftItems.MASK_MATA_HUNA.get()) {
            armor = 1;
        } else if (mask == NuiCraftItems.MASK_MATA_MAHIKI.get()) {
            armor = 1;
        } else if (mask == NuiCraftItems.MASK_MATA_MATATU.get()) {
            armor = 2; kbResist = 0.5; reach = 3;
        } else if (mask == NuiCraftItems.MASK_MATA_KOMAU.get()) {
            armor = 2;
        } else if (mask == NuiCraftItems.MASK_MATA_RARU.get()) {
            armor = 2;
        } else if (mask == NuiCraftItems.MASK_MATA_RURU.get()) {
            armor = 2;
        }

        // Apply or remove each modifier
        setOrRemoveModifier(player.getAttribute(Attributes.ARMOR), MOD_ARMOR, armor, AttributeModifier.Operation.ADD_VALUE);
        setOrRemoveModifier(player.getAttribute(Attributes.ARMOR_TOUGHNESS), MOD_TOUGHNESS, toughness, AttributeModifier.Operation.ADD_VALUE);
        setOrRemoveModifier(player.getAttribute(Attributes.MOVEMENT_SPEED), MOD_SPEED, speed, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        setOrRemoveModifier(player.getAttribute(Attributes.ATTACK_DAMAGE), MOD_ATTACK, attack, AttributeModifier.Operation.ADD_VALUE);
        setOrRemoveModifier(player.getAttribute(Attributes.KNOCKBACK_RESISTANCE), MOD_KB_RESIST, kbResist, AttributeModifier.Operation.ADD_VALUE);
        setOrRemoveModifier(player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE), MOD_REACH, reach, AttributeModifier.Operation.ADD_VALUE);
    }

    private void setOrRemoveModifier(AttributeInstance attr, ResourceLocation id, double value, AttributeModifier.Operation op) {
        if (attr == null) return;

        if (value > 0) {
            // Add or update the modifier
            if (!attr.hasModifier(id)) {
                attr.addTransientModifier(new AttributeModifier(id, value, op));
            } else if (attr.getModifier(id).amount() != value) {
                attr.removeModifier(id);
                attr.addTransientModifier(new AttributeModifier(id, value, op));
            }
        } else {
            // Remove if no longer needed
            if (attr.hasModifier(id)) {
                attr.removeModifier(id);
            }
        }
    }

    /**
     * Apply potion effects for masks that grant them.
     */
    private void applyMaskEffects(ServerPlayer player, Item mask, ServerLevel level) {
        if (mask == NuiCraftItems.MASK_MATA_HAU.get()) {
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, EFFECT_DURATION, 0, true, false, true));
        } else if (mask == NuiCraftItems.MASK_MATA_KAUKAU.get()) {
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, EFFECT_DURATION, 0, true, false, true));
        } else if (mask == NuiCraftItems.MASK_MATA_MIRU.get()) {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, EFFECT_DURATION, 0, true, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, EFFECT_DURATION, 0, true, false, true));
        } else if (mask == NuiCraftItems.MASK_MATA_AKAKU.get()) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, EFFECT_DURATION, 0, true, false, true));
        } else if (mask == NuiCraftItems.MASK_MATA_HUNA.get()) {
            player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, EFFECT_DURATION, 0, true, false, true));
        } else if (mask == NuiCraftItems.MASK_MATA_MAHIKI.get()) {
            player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, EFFECT_DURATION, 0, true, false, true));
        } else if (mask == NuiCraftItems.MASK_MATA_RARU.get()) {
            player.addEffect(new MobEffectInstance(MobEffects.LUCK, EFFECT_DURATION, 0, true, false, true));
        } else if (mask == NuiCraftItems.MASK_MATA_RURU.get()) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, EFFECT_DURATION, 0, true, false, true));
        } else if (mask == NuiCraftItems.MASK_MATA_KOMAU.get()) {
            AABB aura = player.getBoundingBox().inflate(KOMAU_RANGE);
            List<Monster> nearbyMobs = level.getEntitiesOfClass(Monster.class, aura);
            for (Monster mob : nearbyMobs) {
                mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, EFFECT_DURATION, 0, true, true, true));
                mob.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, EFFECT_DURATION, 0, true, true, true));
            }
        }
    }
}
