package eastonium.mnogii.entity;

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
 * Turaga - elder NPC representing one of the six village elders of Mata Nui.
 *
 * The six Turaga of the Mata series, each tied to a Koro:
 *   Ta-Koro → VAKAMA  (Huna mask)
 *   Ga-Koro → NOKAMA  (Rau mask)
 *   Le-Koro → MATAU   (Mahiki mask)
 *   Po-Koro → ONEWA   (Komau mask)
 *   Onu-Koro → WHENUA (Ruru mask)
 *   Ko-Koro → NUJU    (Matatu mask)
 *
 * geoId  - which geo/animation file to use (falls back to matatu_turaga if not implemented)
 * textureFile - flat texture filename under textures/entity/
 */
public class EntityTuraga extends PathfinderMob {

    public enum TuragaType {
        VAKAMA("huna_turaga",   "huna_turaga/ta"),
        NOKAMA("rau_turaga",    "rau_turaga/default"),
        MATAU("mahiki_turaga",  "mahiki_turaga/le"),
        ONEWA("komau_turaga",   "komau_turaga/po"),
        WHENUA("ruru_turaga",   "ruru_turaga/onu"),
        NUJU("matatu_turaga",   "matatu_turaga/ko");

        private final String geoId;
        private final String texturePath;

        TuragaType(String geoId, String texturePath) {
            this.geoId = geoId;
            this.texturePath = texturePath;
        }

        /** Geo/animation file prefix, e.g. "matatu_turaga" → matatu_turaga.geo.json */
        public String getGeoId()       { return geoId; }
        /** Texture path under textures/entity/, e.g. "matatu_turaga/ta" */
        public String getTexturePath() { return texturePath; }
    }

    private static final EntityDataAccessor<Integer> DATA_TYPE =
            SynchedEntityData.defineId(EntityTuraga.class, EntityDataSerializers.INT);

    /** Counts idle ticks before firing the next ambient animation. */
    private int ambientAnimTimer = 0;
    private boolean lastMoving = false;
    /** Tracks whether the initial pose command has been sent to the client. */
    private boolean poseSent = false;

    public EntityTuraga(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    /** Constructor used by per-character entity types to pin the type at spawn. */
    public EntityTuraga(EntityType<? extends PathfinderMob> type, Level level, TuragaType turagaType) {
        super(type, level);
        this.entityData.set(DATA_TYPE, turagaType.ordinal());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_TYPE, TuragaType.NUJU.ordinal());
    }

    public TuragaType getTuragaType() {
        int i = this.entityData.get(DATA_TYPE);
        TuragaType[] types = TuragaType.values();
        return i >= 0 && i < types.length ? types[i] : TuragaType.NUJU;
    }

    public void setTuragaType(TuragaType type) {
        this.entityData.set(DATA_TYPE, type.ordinal());
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty,
                                        EntitySpawnReason reason, @Nullable SpawnGroupData spawnData) {
        // Only infer type from biome for the generic turaga entity.
        // Character-specific types (TURAGA_NOKAMA, TURAGA_VAKAMA, etc.) have their
        // type fixed in the constructor and must not be overwritten here.
        if (this.getType() == eastonium.mnogii.core.MnogiiEntityTypes.TURAGA.get()) {
            var biome = level.getBiome(this.blockPosition());
            if (biome.is(Biomes.BADLANDS) || biome.is(Biomes.WOODED_BADLANDS) || biome.is(Biomes.DESERT)) {
                setTuragaType(TuragaType.VAKAMA);
            } else if (biome.is(Biomes.WARM_OCEAN) || biome.is(Biomes.OCEAN) || biome.is(Biomes.BEACH)) {
                setTuragaType(TuragaType.NOKAMA);
            } else if (biome.is(Biomes.JUNGLE) || biome.is(Biomes.SPARSE_JUNGLE) || biome.is(Biomes.BAMBOO_JUNGLE)) {
                setTuragaType(TuragaType.MATAU);
            } else if (biome.is(Biomes.SAVANNA) || biome.is(Biomes.SAVANNA_PLATEAU) || biome.is(Biomes.WINDSWEPT_SAVANNA)) {
                setTuragaType(TuragaType.ONEWA);
            } else if (biome.is(Biomes.LUSH_CAVES) || biome.is(Biomes.DRIPSTONE_CAVES) || biome.is(Biomes.DEEP_DARK)) {
                setTuragaType(TuragaType.WHENUA);
            } else {
                setTuragaType(TuragaType.NUJU);
            }
        }
        return super.finalizeSpawn(level, difficulty, reason, spawnData);
    }

    @Override
    public Component getDisplayName() {
        return switch (getTuragaType()) {
            case VAKAMA -> Component.literal("Turaga Vakama");
            case NOKAMA -> Component.literal("Turaga Nokama");
            case MATAU  -> Component.literal("Turaga Matau");
            case ONEWA  -> Component.literal("Turaga Onewa");
            case WHENUA -> Component.literal("Turaga Whenua");
            case NUJU   -> Component.literal("Turaga Nuju");
        };
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean requiresCustomPersistence() {
        return true;
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
        this.entityData.set(DATA_TYPE, input.getIntOr("TuragaType", TuragaType.NUJU.ordinal()));
        poseSent = false; // re-send pose after loading so the controller re-initialises
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            if (!poseSent || this.tickCount % 40 == 1) {
                poseSent = true;
                eastonium.mnogii.client.animator.TuragaAnimator.sendPoseCommand(this);
            }
            boolean moving = this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-5;
            if (moving != lastMoving || this.tickCount % 40 == 1) {
                lastMoving = moving;
                eastonium.mnogii.client.animator.TuragaAnimator.sendMovementCommand(this);
            }
            if (!moving) {
                ambientAnimTimer++;
                if (ambientAnimTimer >= 80 + this.random.nextInt(120)) {
                    ambientAnimTimer = 0;
                    eastonium.mnogii.client.animator.TuragaAnimator.sendWaveCommand(this);
                }
            } else {
                ambientAnimTimer = 0;
            }
        }
    }
}
