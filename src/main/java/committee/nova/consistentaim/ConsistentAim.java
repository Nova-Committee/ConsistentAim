package committee.nova.consistentaim;

import committee.nova.consistentaim.config.ClientConfig;
import committee.nova.consistentaim.proxy.AimingProxyManager;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ConsistentAim.MODID)
public class ConsistentAim {
    public static final String MODID = "consistentaim";
    public static final Logger LOGGER = LogManager.getLogger();

    public ConsistentAim() {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST,
                () -> Pair.of(() -> "", (a, b) -> true));
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.CFG);
        AimingProxyManager.init();
    }
}
