package cc.irori.patches.mixin;

import com.hypixel.hytale.server.core.entity.reference.InvalidatablePersistentRef;
import com.hypixel.hytale.server.npc.systems.SpawnReferenceSystems;
import com.hypixel.hytale.server.spawning.spawnmarkers.SpawnMarkerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SpawnReferenceSystems.MarkerAddRemoveSystem.class)
public class MarkerAddRemoveSystemMixin {

    @Redirect(
            method = "onEntityRemove",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/hypixel/hytale/server/spawning/spawnmarkers/SpawnMarkerEntity;getNpcReferences()[Lcom/hypixel/hytale/server/core/entity/reference/InvalidatablePersistentRef;"
            )
    )
    public InvalidatablePersistentRef[] patches$fixOnEntityRemoveRedirect(SpawnMarkerEntity instance) {
        InvalidatablePersistentRef[] refs = instance.getNpcReferences();
        return refs == null ? new InvalidatablePersistentRef[0] : refs;
    }
}
