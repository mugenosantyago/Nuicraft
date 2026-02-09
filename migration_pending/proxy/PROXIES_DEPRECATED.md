# Proxy System Deprecated

The old proxy system (ClientProxyBionicle and CommonProxyBionicle) is **deprecated** in modern Minecraft/NeoForge.

## What Replaced It?

### Client-Only Code
- **Old:** `@SidedProxy` with ClientProxyBionicle
- **New:** `@EventBusSubscriber(value = Dist.CLIENT)` or `DistExecutor`
- **Location:** `src/main/java/eastonium/nuicraft/client/ClientSetup.java`

### Common Code
- **Old:** CommonProxyBionicle with `@SubscribeEvent` for registration
- **New:** `DeferredRegister` in main mod class constructor
- **Location:** `src/main/java/eastonium/nuicraft/core/` classes

## Migration Status

- ✅ Registration migrated to DeferredRegister system
- ✅ Client setup moved to ClientSetup.java
- ⚠️ Entity renderers not yet implemented (TODO in ClientSetup)
- ⚠️ Item colors not yet implemented (TODO in ClientSetup)
- ⚠️ Particle providers not yet implemented (TODO in ClientSetup)

## TODO: Delete These Files

Once all functionality is confirmed migrated:
1. Delete `ClientProxyBionicle.java`
2. Delete `CommonProxyBionicle.java`
3. Delete this folder

**Do not delete yet** - keeping as reference during migration.
