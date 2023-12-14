package committee.nova.consistentaim.mixin;

import committee.nova.consistentaim.api.IOptions;
import committee.nova.consistentaim.util.Utilities;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(GameSettings.class)
public abstract class MixinOptions implements IOptions {

    @Unique
    private PointOfView consistentaim$cameraTypeZoomed = PointOfView.FIRST_PERSON;

    @Override
    public PointOfView consistentaim$getCameraTypeZoomed() {
        return consistentaim$cameraTypeZoomed;
    }

    @Override
    public void consistentaim$setCameraTypeZoomed(PointOfView cameraType) {
        this.consistentaim$cameraTypeZoomed = cameraType;
    }

    @Override
    public void consistentaim$cycleCameraTypeZoomed() {
        consistentaim$setCameraTypeZoomed(consistentaim$cameraTypeZoomed.cycle());
        final PlayerEntity player = Minecraft.getInstance().player;
        if (player == null) return;
        player.displayClientMessage(
                new TranslationTextComponent(
                        "msg.consistentaim.cameratype",
                        Utilities.getCameraTypeName(consistentaim$cameraTypeZoomed)
                ),
                true
        );
    }
}
