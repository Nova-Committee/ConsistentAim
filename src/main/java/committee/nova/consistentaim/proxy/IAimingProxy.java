package committee.nova.consistentaim.proxy;

import committee.nova.consistentaim.config.ClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.Items;

public interface IAimingProxy {
    IAimingProxy VANILLA = () -> {
        final LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return false;
        if (ClientConfig.vanillaSpyglass.get() && player.isScoping()) return true;
        return ClientConfig.vanillaBow.get() && player.isUsingItem() && player.getUseItem().is(Items.BOW);
    };

    boolean isAiming();
}
