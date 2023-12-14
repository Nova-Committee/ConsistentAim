package committee.nova.consistentaim.proxy;

import com.google.common.collect.Sets;
import committee.nova.consistentaim.ConsistentAim;
import committee.nova.consistentaim.proxy.cgm.CGMAimingProxy;
import committee.nova.consistentaim.proxy.tac.TACAimingProxy;
import net.minecraftforge.fml.ModList;

import java.util.Set;

public class AimingProxyManager {
    public static void init() {
        validateAimingProxy("cgm", CGMAimingProxy::new);
        validateAimingProxy("tac", TACAimingProxy::new);
    }

    private static final Set<IAimingProxy> aimingProxies = Sets.newHashSet(IAimingProxy.DUMMY);

    private static void validateAimingProxy(String id, SupplierEx<IAimingProxy> proxySupplier) {
        if (!ModList.get().isLoaded(id)) return;
        try {
            final IAimingProxy proxy = proxySupplier.get();
            aimingProxies.add(proxy);
        } catch (Exception e) {
            ConsistentAim.LOGGER.warn("Failed to validate aiming proxy for {}", id);
        }
    }

    public static boolean testAiming() {
        for (final IAimingProxy proxy : aimingProxies) if (proxy.isAiming()) return true;
        return false;
    }

    @FunctionalInterface
    interface SupplierEx<T> {
        T get() throws Exception;
    }
}
