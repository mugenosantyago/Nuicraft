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
                        
                        // MATA MASKS
                        if (helmet.is(NuiCraftItems.MASK_MATA_GOLD.get())) {
                            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 30, 1, false, false));
                            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 30, 0, false, false));
                            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 30, 1, false, false));
                            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 30, 0, false, false));
                            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30, 1, false, false));
                            
                        } else if (helmet.is(NuiCraftItems.MASK_MATA_PAKARI.get())) {
                            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 30, 0, false, false));
                            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 30, 0, false, false));
                            
                        } else if (helmet.is(NuiCraftItems.MASK_MATA_KAUKAU.get())) {
                            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 30, 0, false, false));
                            
                        } else if (helmet.is(NuiCraftItems.MASK_MATA_HAU.get())) {
                            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 30, 1, false, false));
                            
                        } else if (helmet.is(NuiCraftItems.MASK_MATA_KAKAMA.get())) {
                            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30, 1, false, false));
                        }
                        // NUVA MASKS
                        else if (helmet.is(NuiCraftItems.MASK_NUVA_PAKARI.get())) {
                            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 30, 1, false, false));
                            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 30, 1, false, false));
                            
                        } else if (helmet.is(NuiCraftItems.MASK_NUVA_KAUKAU.get())) {
                            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 30, 0, false, false));
                            
                        } else if (helmet.is(NuiCraftItems.MASK_NUVA_HAU.get())) {
                            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 30, 3, false, false));
                            
                        } else if (helmet.is(NuiCraftItems.MASK_NUVA_KAKAMA.get())) {
                            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30, 3, false, false));
                        }
                        // LEGENDARY MASKS
                        else if (helmet.is(NuiCraftItems.MASK_IGNIKA.get())) {
                            List<Entity> nearbyEntities = level.getEntities(player, player.getBoundingBox().inflate(5.0D));
                            for (Entity entity : nearbyEntities) {
                                if (player.tickCount % 30 == 0 && (entity instanceof Mob || entity instanceof AmbientCreature || entity instanceof Squid)) {
                                    entity.hurt(level.damageSources().wither(), 1.0F);
                                    player.heal(1.0F);
                                }
                            }
                        } else if (helmet.is(NuiCraftItems.MASK_VAHI.get())) {
                            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 1, false, false));
                            if (player.tickCount % 2 == 0) {
                                level.setDayTime(level.getDayTime() - 1); // Slow down time
                            }
                            List<Entity> nearbyEntities = level.getEntities(player, player.getBoundingBox().inflate(10.0D));
                            for (Entity entity : nearbyEntities) {
                                if (entity instanceof Mob mob) {
                                    mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 3, false, false));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean removeFallDistance(ItemStack helmet) {
        return helmet.is(NuiCraftItems.MASK_MATA_MIRU.get()) || 
               helmet.is(NuiCraftItems.MASK_NUVA_MIRU.get()) || 
               helmet.is(NuiCraftItems.MASK_MATA_GOLD.get());
    }
}