package committee.nova.consistentaim.mixin;

import committee.nova.consistentaim.api.IOptions;
import committee.nova.consistentaim.util.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Shadow
    @Final
    public Options options;

    @Inject(
            method = "handleKeybinds",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Options;setCameraType(Lnet/minecraft/client/CameraType;)V"
            ),
            cancellable = true
    )
    private void inject$handleKeyBinds(CallbackInfo ci) {
        if (!Utilities.isAiming()) return;
        ci.cancel();
        ((IOptions) options).consistentaim$cycleCameraTypeZoomed();
    }
}
