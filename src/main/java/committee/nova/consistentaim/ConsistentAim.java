package committee.nova.consistentaim;

import com.mojang.logging.LogUtils;
import committee.nova.consistentaim.proxy.AimingProxyManager;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(ConsistentAim.MODID)
public class ConsistentAim {
    public static final String MODID = "consistentaim";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ConsistentAim() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class,
                () -> new IExtensionPoint.DisplayTest(() -> "", (a, b) -> true));
        AimingProxyManager.init();
    }
}
