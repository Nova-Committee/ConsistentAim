package committee.nova.consistentaim.mixin;

import committee.nova.consistentaim.api.IOptions;
import committee.nova.consistentaim.proxy.AimingProxyManager;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
    @Shadow
    @Final
    public GameSettings options;

    @Inject(
            method = "handleKeybinds",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/GameSettings;setCameraType(Lnet/minecraft/client/settings/PointOfView;)V"
            ),
            cancellable = true
    )
    private void inject$handleKeyBinds(CallbackInfo ci) {
        if (!AimingProxyManager.testAiming()) return;
        ci.cancel();
        ((IOptions) options).consistentaim$cycleCameraTypeZoomed();
    }
}
