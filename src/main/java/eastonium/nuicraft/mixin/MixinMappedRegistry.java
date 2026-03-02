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

import java.util.Map;

/**
 * Injects into MappedRegistry.freeze() to silently bind any unbound tag
 * HolderSets to empty before the vanilla "Unbound tags" exception is thrown.
 *
 * In Minecraft 1.21.5+, ClientItemInfoLoader creates a frozen registry
 * snapshot during resource-pack loading (before data-packs are applied).
 * Mods that declare TagKey<Item> repair/material tags but ship no corresponding
 * tag JSON are therefore "unbound" at that point, crashing the game.
 *
 * This mixin detects the pre-data-pack freeze (no tags are bound yet) and
 * calls bindAllTagsToEmpty() so the freeze check passes.
 * Data pack loading will later overwrite the empty bindings with real data.
 */
@Mixin(MappedRegistry.class)
public abstract class MixinMappedRegistry<T> {

    @Shadow private boolean frozen;
    @Shadow private Map<TagKey<T>, HolderSet.Named<T>> frozenTags;

    @Shadow public abstract void bindAllTagsToEmpty();

    @Inject(method = "freeze", at = @At("HEAD"))
    private void nuicraft_bindUnboundTagsBeforeFreeze(CallbackInfoReturnable<Registry<T>> cir) {
        if (!this.frozen && !this.frozenTags.isEmpty()) {
            boolean anyBound = this.frozenTags.values().stream().anyMatch(HolderSet.Named::isBound);
            if (!anyBound) {
                // We are in a pre-data-pack freeze (e.g. triggered by ModelManager/ClientItemInfoLoader).
                // Bind all unbound registered tags to empty so the freeze succeeds.
                // Proper data-pack loading will later overwrite these with actual tag members.
                bindAllTagsToEmpty();
            }
        }
    }
}
