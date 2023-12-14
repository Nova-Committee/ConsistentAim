package committee.nova.consistentaim.mixin.cgm;

import com.mrcrayfish.guns.client.handler.AimingHandler;
import committee.nova.consistentaim.util.Utilities;
import net.minecraftforge.event.TickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = AimingHandler.class, remap = false)
public abstract class MixinAimingHandler {
    @Inject(
            method = "onClientTick(Lnet/minecraftforge/event/TickEvent$ClientTickEvent;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mrcrayfish/framework/api/network/FrameworkNetwork;sendToServer(Lcom/mrcrayfish/framework/network/message/IMessage;)V",
                    ordinal = 0
            ),
            remap = false
    )
    private void inject$onClientTick$0(TickEvent.ClientTickEvent event, CallbackInfo ci) {
        Utilities.onStartAiming();
    }

    @Inject(
            method = "onClientTick(Lnet/minecraftforge/event/TickEvent$ClientTickEvent;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mrcrayfish/framework/api/network/FrameworkNetwork;sendToServer(Lcom/mrcrayfish/framework/network/message/IMessage;)V",
                    ordinal = 1
            ),
            remap = false
    )
    private void inject$onClientTick$1(TickEvent.ClientTickEvent event, CallbackInfo ci) {
        Utilities.onStopAiming();
    }
}
