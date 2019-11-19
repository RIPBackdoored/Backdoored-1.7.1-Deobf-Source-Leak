package com.backdoored.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import \u0000r.\u0000k.\u0000r.\u0000x;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Overwrite;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import \u0000r.\u0000k.\u0000r.\u0000ck;
import net.minecraft.util.ResourceLocation;
import javax.annotation.Nullable;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.entity.AbstractClientPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { AbstractClientPlayer.class }, priority = 999999999)
public abstract class MixinAbstractClientPlayer extends MixinEntityPlayer
{
    public MixinAbstractClientPlayer() {
        super();
    }
    
    @Shadow
    @Nullable
    protected abstract NetworkPlayerInfo func_175155_b();
    
    @Overwrite
    @Nullable
    public ResourceLocation func_110303_q() {
        final NetworkPlayerInfo func_175155_b = this.func_175155_b();
        final \u0000ck \u0000ck = new \u0000ck(func_175155_b);
        MinecraftForge.EVENT_BUS.post((Event)\u0000ck);
        if (\u0000ck.sbp != null) {
            return \u0000ck.sbp;
        }
        return (func_175155_b == null) ? null : func_175155_b.func_178861_h();
    }
    
    @Inject(method = { "hasSkin" }, at = { @At("RETURN") }, cancellable = true)
    public void hasSkin(final CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        final \u0000x.\u0000y \u0000y = new \u0000x.\u0000y(this.func_175155_b(), (boolean)callbackInfoReturnable.getReturnValue());
        MinecraftForge.EVENT_BUS.post((Event)\u0000y);
        callbackInfoReturnable.setReturnValue(\u0000y.sov);
    }
    
    @Inject(method = { "getLocationSkin()Lnet/minecraft/util/ResourceLocation;" }, at = { @At("RETURN") }, cancellable = true)
    public void getSkin(final CallbackInfoReturnable<ResourceLocation> callbackInfoReturnable) {
        final \u0000x.\u0000h \u0000h = new \u0000x.\u0000h(this.func_175155_b(), (ResourceLocation)callbackInfoReturnable.getReturnValue());
        MinecraftForge.EVENT_BUS.post((Event)\u0000h);
        callbackInfoReturnable.setReturnValue(\u0000h.sia);
    }
}
