package committee.nova.consistentaim.api;

import net.minecraft.client.settings.PointOfView;

public interface IOptions {
    PointOfView consistentaim$getCameraTypeZoomed();

    void consistentaim$setCameraTypeZoomed(PointOfView cameraType);

    void consistentaim$cycleCameraTypeZoomed();
}
