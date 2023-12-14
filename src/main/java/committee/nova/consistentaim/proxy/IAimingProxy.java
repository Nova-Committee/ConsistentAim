package committee.nova.consistentaim.proxy;

import committee.nova.consistentaim.config.ClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.Items;

public interface IAimingProxy {
    IAimingProxy VANILLA = () -> {
        final ClientPlayerEntity player = Minecraft.getInstance().player;
        if (player == null) return false;
        return ClientConfig.vanillaBow.get() && player.isUsingItem() && player.getUseItem().getItem().equals(Items.BOW);
    };

    boolean isAiming();
}
