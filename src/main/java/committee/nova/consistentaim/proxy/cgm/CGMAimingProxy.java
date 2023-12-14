package committee.nova.consistentaim.proxy.cgm;

import com.mrcrayfish.guns.client.handler.AimingHandler;
import committee.nova.consistentaim.proxy.IAimingProxy;

public class CGMAimingProxy implements IAimingProxy {
    public CGMAimingProxy() throws ClassNotFoundException {
        Class.forName("com.mrcrayfish.guns.client.handler.AimingHandler");
    }

    @Override
    public boolean isAiming() {
        return AimingHandler.get().isZooming();
    }
}
