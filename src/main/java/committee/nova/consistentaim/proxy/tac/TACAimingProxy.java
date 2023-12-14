package committee.nova.consistentaim.proxy.tac;

import com.tac.guns.client.handler.AimingHandler;
import committee.nova.consistentaim.api.IAimingHandler;
import committee.nova.consistentaim.config.ClientConfig;
import committee.nova.consistentaim.proxy.IAimingProxy;

public class TACAimingProxy implements IAimingProxy {
    public TACAimingProxy() throws ClassNotFoundException {
        Class.forName("com.tac.guns.client.handler.AimingHandler");
    }

    @Override
    public boolean isAiming() {
        return ClientConfig.tac.get() && ((IAimingHandler) AimingHandler.get()).consistentaim$isAiming();
    }
}
