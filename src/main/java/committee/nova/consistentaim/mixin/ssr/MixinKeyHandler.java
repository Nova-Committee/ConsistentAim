package committee.nova.consistentaim.mixin.ssr;

import com.teamderpy.shouldersurfing.client.KeyHandler;
import committee.nova.consistentaim.proxy.AimingProxyManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = KeyHandler.class, remap = false)
public abstract class MixinKeyHandler {
    @Redirect(
            method = "onInput",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/KeyMapping;consumeClick()Z"
            ),
            remap = false
    )
    private static boolean inject$onInput(KeyMapping instance) {
        if (!instance.equals(Minecraft.getInstance().options.keyTogglePerspective)) return instance.consumeClick();
        return !AimingProxyManager.testAiming() && instance.consumeClick();
    }
}
