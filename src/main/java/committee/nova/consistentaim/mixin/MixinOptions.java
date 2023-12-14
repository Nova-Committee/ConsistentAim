package committee.nova.consistentaim.mixin;

import committee.nova.consistentaim.api.IOptions;
import committee.nova.consistentaim.util.Utilities;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Options.class)
public abstract class MixinOptions implements IOptions {

    @Unique
    private CameraType consistentaim$cameraTypeZoomed = CameraType.FIRST_PERSON;

    @Override
    public CameraType consistentaim$getCameraTypeZoomed() {
        return consistentaim$cameraTypeZoomed;
    }

    @Override
    public void consistentaim$setCameraTypeZoomed(CameraType cameraType) {
        this.consistentaim$cameraTypeZoomed = cameraType;
    }

    @Override
    public void consistentaim$cycleCameraTypeZoomed() {
        consistentaim$cameraTypeZoomed = consistentaim$cameraTypeZoomed.cycle();
        final Player player = Minecraft.getInstance().player;
        if (player == null) return;
        player.displayClientMessage(
                new TranslatableComponent(
                        "msg.consistentaim.cameratype",
                        Utilities.getCameraTypeName(consistentaim$cameraTypeZoomed)
                ),
                true
        );
    }
}
