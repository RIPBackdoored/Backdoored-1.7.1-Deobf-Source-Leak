package com.backdoored.mixin;

import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import \u0000r.\u0000k.\u0000r.\u0000q;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.Shadow;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { EntityPlayer.class }, priority = 9900)
public abstract class MixinEntityPlayer
{
    public MixinEntityPlayer() {
        super();
    }
    
    @Shadow
    public abstract GameProfile func_146103_bH();
    
    @ModifyConstant(method = { "attackTargetEntityWithCurrentItem" }, constant = { @Constant(doubleValue = 0.6) })
    private double decelerate(final double n) {
        return 1.0;
    }
    
    @Redirect(method = { "attackTargetEntityWithCurrentItem" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;setSprinting(Z)V"))
    private void dontSprintPlsThx(final EntityPlayer entityPlayer, final boolean b) {
    }
    
    @ModifyConstant(method = { "getPortalCooldown" }, constant = { @Constant(intValue = 10) })
    private int getModifiedPortalCooldown(final int n) {
        final \u0000q \u0000q = new \u0000q(n, (EntityPlayer)this);
        MinecraftForge.EVENT_BUS.post((Event)\u0000q);
        return \u0000q.rq;
    }
}
