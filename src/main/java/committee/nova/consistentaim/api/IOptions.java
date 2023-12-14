package committee.nova.consistentaim.api;

import net.minecraft.client.CameraType;

public interface IOptions {
    CameraType consistentaim$getCameraTypeZoomed();

    void consistentaim$setCameraTypeZoomed(CameraType cameraType);

    void consistentaim$cycleCameraTypeZoomed();
}
