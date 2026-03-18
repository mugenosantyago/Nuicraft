package eastonium.mnogii.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

/**
 * Toa weapon that applies an elemental on-hit effect matching its koro affinity.
 * The effect fires in {@link #postHurtEnemy} (server-side only).
 */
public class ItemElementalWeapon extends Item {

    public enum Element { FIRE, WATER, AIR, ICE, EARTH, STONE }

    private final Element element;

    public ItemElementalWeapon(Element element, Properties properties) {
        super(properties);
        this.element = element;
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postHurtEnemy(stack, target, attacker);
        if (!(attacker.level() instanceof ServerLevel serverLevel)) return;

        Vec3 pos = target.position();

        switch (element) {
            case FIRE -> {
                target.igniteForSeconds(8.0F);
                serverLevel.sendParticles(ParticleTypes.FLAME,
                        pos.x, pos.y + 1.0, pos.z, 25, 0.3, 0.5, 0.3, 0.06);
            }
            case WATER -> {
                target.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 120, 1)); // Slowness II, 6s
                serverLevel.sendParticles(ParticleTypes.SPLASH,
                        pos.x, pos.y + 1.2, pos.z, 35, 0.3, 0.3, 0.3, 0.4);
            }
            case AIR -> {
                target.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, 0)); // Levitation I, 3s
                serverLevel.sendParticles(ParticleTypes.CLOUD,
                        pos.x, pos.y + 1.0, pos.z, 20, 0.4, 0.3, 0.4, 0.05);
            }
            case ICE -> {
                target.setTicksFrozen(target.getTicksRequiredToFreeze() + 80);
                target.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 100, 2)); // Slowness III, 5s
                serverLevel.sendParticles(ParticleTypes.SNOWFLAKE,
                        pos.x, pos.y + 1.0, pos.z, 25, 0.3, 0.5, 0.3, 0.05);
            }
            case EARTH -> {
                target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0));      // Blindness, 5s
                target.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 100, 1)); // Mining Fatigue II, 5s
                serverLevel.sendParticles(ParticleTypes.SMOKE,
                        pos.x, pos.y + 1.0, pos.z, 20, 0.3, 0.5, 0.3, 0.02);
            }
            case STONE -> {
                target.knockback(2.0, target.getX() - attacker.getX(), target.getZ() - attacker.getZ());
                target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0)); // Weakness, 5s
                serverLevel.sendParticles(ParticleTypes.CRIT,
                        pos.x, pos.y + 1.0, pos.z, 20, 0.3, 0.4, 0.3, 0.1);
            }
        }
    }
}
