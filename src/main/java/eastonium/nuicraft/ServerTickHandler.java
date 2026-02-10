package eastonium.nuicraft;

import java.util.List;

import eastonium.nuicraft.core.NuiCraftItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

public class ServerTickHandler {
    @SubscribeEvent
    public void onServerTick(ServerTickEvent.Post event) {
        // Process all players on the server
        for (ServerLevel level : event.getServer().getAllLevels()) {
            for (ServerPlayer player : level.players()) {
                if (player.tickCount % 20 == 0) {
                    ItemStack helmet = player.getInventory().getItem(39); // Helmet slot (36+3)
                    if (!helmet.isEmpty()) {
                        if (removeFallDistance(helmet)) {
                            player.resetFallDistance();
                        }
                        
                        // Mata Masks
                        if (helmet.is(NuiCraftItems.MASK_MATA_AKAKU.get())) {
                            // Akaku - Vision
                            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 260, 0, false, false));
                        } else if (helmet.is(NuiCraftItems.MASK_MATA_HAU.get())) {
                            // Hau - Protection
                            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 30, 1, false, false));
                        } else if (helmet.is(NuiCraftItems.MASK_MATA_HUNA.get())) {
                            // Huna - Concealment
                            player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 30, 0, false, false));
                        } else if (helmet.is(NuiCraftItems.MASK_MATA_KAKAMA.get())) {
                            // Kakama - Speed
                            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 30, 1, false, false));
                        } else if (helmet.is(NuiCraftItems.MASK_MATA_KAUKAU.get())) {
                            // Kaukau - Water Breathing
                            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 30, 0, false, false));
                        } else if (helmet.is(NuiCraftItems.MASK_MATA_KOMAU.get())) {
                            // Komau - Mind Control
                            player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 30, 0, false, false));
                        } else if (helmet.is(NuiCraftItems.MASK_MATA_MAHIKI.get())) {
                            // Mahiki - Illusion
                            player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 15, 0, false, false));
                        } else if (helmet.is(NuiCraftItems.MASK_MATA_MATATU.get())) {
                            // Matatu - Telekinesis
                            player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 30, 0, false, false));
                        } else if (helmet.is(NuiCraftItems.MASK_MATA_MIRU.get())) {
                            // Miru - Levitation
                            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 30, 0, false, false));
                        } else if (helmet.is(NuiCraftItems.MASK_MATA_PAKARI.get())) {
                            // Pakari - Strength
                            player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 30, 1, false, false));
                        } else if (helmet.is(NuiCraftItems.MASK_MATA_RARU.get())) {
                            // Raru - Chameleon/Concealment
                            player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 30, 0, false, false));
                        } else if (helmet.is(NuiCraftItems.MASK_MATA_RURU.get())) {
                            // Ruru - Sleep/Sensing
                            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 260, 0, false, false));
                        }
                    }
                }
            }
        }
    }

    private boolean removeFallDistance(ItemStack helmet) {
        return helmet.is(NuiCraftItems.MASK_MATA_MIRU.get());
    }
}