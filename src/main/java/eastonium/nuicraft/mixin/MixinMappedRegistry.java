package eastonium.nuicraft.mixin;

import net.minecraft.core.HolderSet;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Fixes "Unbound tags in registry minecraft:item" crashes on NeoForge 1.21.5+.
 *
 * In this version, ClientItemInfoLoader freezes the item registry snapshot during
 * resource-pack loading to resolve item models — before data packs are applied.
 * Mods that declare TagKey<Item> for repair/material purposes without shipping
 * the corresponding tag JSON files therefore have "unbound" entries at freeze time.
 *
 * This mixin injects at the top of MappedRegistry.freeze() and, for every
 * unbound HolderSet.Named in frozenTags, calls bind(List.of()) via reflection
 * (the method is package-private). Tags that are already properly bound are
 * left untouched. When data packs load later, their tag data overwrites any
 * empty bindings we set here.
 */
@Mixin(MappedRegistry.class)
public abstract class MixinMappedRegistry<T> {

    /**
     * Cached reference to the package-private HolderSet.Named#bind(List) method.
     * Resolved once at class load time.
     */
    private static final Method HOLDER_SET_BIND;

    static {
        Method m = null;
        try {
            m = HolderSet.Named.class.getDeclaredMethod("bind", List.class);
            m.setAccessible(true);
        } catch (NoSuchMethodException e) {
            // Will be null — injection will be a no-op and the vanilla crash will surface.
        }
        HOLDER_SET_BIND = m;
    }

    @Shadow private boolean frozen;
    @Shadow private Map<TagKey<T>, HolderSet.Named<T>> frozenTags;

    @Inject(method = "freeze", at = @At("HEAD"))
    private void nuicraft_bindUnboundTagsBeforeFreeze(CallbackInfoReturnable<Registry<T>> cir) {
        if (this.frozen || HOLDER_SET_BIND == null) return;

        for (HolderSet.Named<T> holderSet : this.frozenTags.values()) {
            if (!holderSet.isBound()) {
                try {
                    HOLDER_SET_BIND.invoke(holderSet, List.of());
                } catch (ReflectiveOperationException ignored) {
                    // If invocation fails, leave the tag unbound; vanilla will crash as before.
                }
            }
        }
    }
}
