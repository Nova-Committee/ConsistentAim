package committee.nova.consistentaim;

import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(ConsistentAim.MODID)
public class ConsistentAim {
    public static final String MODID = "consistentaim";

    public ConsistentAim() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class,
                () -> new IExtensionPoint.DisplayTest(() -> "", (a, b) -> true));
    }
}
