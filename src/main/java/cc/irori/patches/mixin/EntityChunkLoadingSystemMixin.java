package cc.irori.patches.mixin;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.universe.world.chunk.EntityChunk;
import com.hypixel.hytale.server.core.universe.world.chunk.WorldChunk;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityChunk.EntityChunkLoadingSystem.class)
public class EntityChunkLoadingSystemMixin {

    @Unique
    private static final HytaleLogger patches$LOGGER = HytaleLogger.get("Irori-Patches");

    @Redirect(
            method = "onComponentRemoved(Lcom/hypixel/hytale/component/Ref;Lcom/hypixel/hytale/component/NonTicking;Lcom/hypixel/hytale/component/Store;Lcom/hypixel/hytale/component/CommandBuffer;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/hypixel/hytale/server/core/modules/entity/component/TransformComponent;setChunkLocation(Lcom/hypixel/hytale/component/Ref;Lcom/hypixel/hytale/server/core/universe/world/chunk/WorldChunk;)V"
            )
    )
    private void patches$fixSetChunkLocation(TransformComponent instance, Ref<ChunkStore> chunkRef, WorldChunk chunk) {
        if (instance == null) {
            patches$LOGGER.atWarning().log("Suppressed NPE in EntityChunkLoadingSystem!!");
            return;
        }
        instance.setChunkLocation(chunkRef, chunk);
    }
}
