package committee.nova.consistentaim.mixin;

import committee.nova.consistentaim.config.ClientConfig;
import committee.nova.consistentaim.util.Utilities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BowItem.class)
public abstract class MixinBowItem {
    @Inject(method = "use", at = @At("HEAD"))
    private void inject$use(World pLevel, PlayerEntity pPlayer, Hand pHand, CallbackInfoReturnable<ActionResult<ItemStack>> cir) {
        if (ClientConfig.vanillaBow.get() && !Utilities.is1stPerson())
            Utilities.onStartAiming();
    }

    @Inject(method = "releaseUsing", at = @At("HEAD"))
    private void inject$stopUsing(ItemStack pStack, World pLevel, LivingEntity pEntityLiving, int pTimeLeft, CallbackInfo ci) {
        if (ClientConfig.vanillaBow.get()) Utilities.onStopAiming();
    }
}
