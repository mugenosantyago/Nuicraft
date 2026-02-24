package eastonium.nuicraft.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

/**
 * Turaga - elder NPC that can be spoken to and traded with (dialogue first).
 *
 * Each Turaga wears the mask associated with their Koro:
 *   Ko-Koro → MATATU (Turaga Nuju)
 *   Ga-Koro → RAU    (Turaga Nokama)
 *   Other Koros → MATATU as fallback until remaining models are delivered.
 */
public class EntityTuraga extends PathfinderMob {

    /** Which Turaga mask / model to use. */
    public enum TuragaType {
        MATATU("matatu_turaga"),
        RAU("rau_turaga");

        private final String id;
        TuragaType(String id) { this.id = id; }
        public String getId() { return id; }
    }

    private static final EntityDataAccessor<Integer> DATA_TYPE =
            SynchedEntityData.defineId(EntityTuraga.class, EntityDataSerializers.INT);

    public EntityTuraga(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_TYPE, TuragaType.MATATU.ordinal());
    }

    public TuragaType getTuragaType() {
        int i = this.entityData.get(DATA_TYPE);
        TuragaType[] types = TuragaType.values();
        return i >= 0 && i < types.length ? types[i] : TuragaType.MATATU;
    }

    public void setTuragaType(TuragaType type) {
        this.entityData.set(DATA_TYPE, type.ordinal());
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty,
                                        EntitySpawnReason reason, @Nullable SpawnGroupData spawnData) {
        var biome = level.getBiome(this.blockPosition());
        if (biome.is(Biomes.WARM_OCEAN) || biome.is(Biomes.OCEAN) || biome.is(Biomes.BEACH)) {
            setTuragaType(TuragaType.RAU);
        } else {
            // Ko-Koro and all other koros default to MATATU until more models are added
            setTuragaType(TuragaType.MATATU);
        }
        return super.finalizeSpawn(level, difficulty, reason, spawnData);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("entity.nuicraft.turaga." + getTuragaType().name().toLowerCase());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 24.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.FOLLOW_RANGE, 16.0D);
    }

    @Override
    public void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("TuragaType", this.entityData.get(DATA_TYPE));
    }

    @Override
    public void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.entityData.set(DATA_TYPE, input.getIntOr("TuragaType", TuragaType.MATATU.ordinal()));
    }
}
