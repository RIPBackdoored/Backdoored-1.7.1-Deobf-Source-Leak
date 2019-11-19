package com.backdoored.mixin;

import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityPlayerMP.class })
public class MixinEntityPlayerMP
{
    public MixinEntityPlayerMP() {
        super();
    }
    
    @Inject(method = { "onDeath" }, at = { @At("HEAD") })
    public void onDeath(final DamageSource damageSource, final CallbackInfo callbackInfo) {
        System.out.println("Mixin Death");
    }
    
    @Redirect(method = { "isEntityInvulnerable" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;isEntityInvulnerable(Lnet/minecraft/util/DamageSource;)Z"))
    private boolean isEntityInvulnerable(final EntityPlayer entityPlayer, final DamageSource damageSource) {
        return false;
    }
    
    @Redirect(method = { "isEntityInvulnerable" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayerMP;isInvulnerableDimensionChange()Z"))
    private boolean isEntityInvulnerable(final EntityPlayerMP entityPlayerMP) {
        return false;
    }
}
