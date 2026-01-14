package cc.irori.patches.mixin;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.server.core.modules.entity.item.PickupItemComponent;
import com.hypixel.hytale.server.core.modules.entity.item.PickupItemSystem;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PickupItemSystem.class)
public abstract class PickupItemSystemMixin {

    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/hypixel/hytale/component/Ref;isValid()Z"
            )
    )
    private boolean patches$safeIsValid(Ref<EntityStore> ref) {
        return ref != null && ref.isValid();
    }
}
