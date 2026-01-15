package cc.irori.patches.mixin;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.HytaleServer;
import com.hypixel.hytale.server.core.ShutdownReason;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.util.thread.TickingThread;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TickingThread.class)
public class TickingThreadMixin {

    // HytaleServer doesn't shutdown after a fatal exception in the default world.
    @Inject(
            method = "run",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/hypixel/hytale/logger/HytaleLogger$Api;log(Ljava/lang/String;Ljava/lang/Object;)V",
                    shift = At.Shift.AFTER
            )
    )
    private void patches$shutdownAfterFatalException(CallbackInfo ci) {
        TickingThread self = (TickingThread)(Object) this;
        if (self instanceof World world) {
            if (world.getName().equals(HytaleServer.get().getConfig().getDefaults().getWorld())) {
                HytaleServer.get().shutdownServer(ShutdownReason.CRASH.withMessage("Fatal exception in default world!"));
            } else {
                HytaleLogger.getLogger().atSevere().log("Fatal exception in non-default world! This has been recognized by IroriPowered:Patches.");
            }
        }
    }
}
