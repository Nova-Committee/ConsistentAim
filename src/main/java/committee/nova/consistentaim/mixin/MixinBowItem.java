package committee.nova.consistentaim.mixin;

import committee.nova.consistentaim.config.ClientConfig;
import committee.nova.consistentaim.util.Utilities;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BowItem.class)
public abstract class MixinBowItem {
    @Inject(method = "use", at = @At("HEAD"))
    private void inject$use(Level l, Player p, InteractionHand h, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if (ClientConfig.vanillaBow.get() && !Utilities.is1stPerson())
            Utilities.onStartAiming();
    }

    @Inject(method = "releaseUsing", at = @At("HEAD"))
    private void inject$stopUsing(ItemStack s, Level l, LivingEntity e, int i, CallbackInfo ci) {
        if (ClientConfig.vanillaBow.get()) Utilities.onStopAiming();
    }
}
