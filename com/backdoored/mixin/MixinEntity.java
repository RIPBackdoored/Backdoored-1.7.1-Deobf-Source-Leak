package com.backdoored.mixin;

import org.spongepowered.asm.mixin.injection.Redirect;
import \u0000r.\u0000k.\u0000r.\u0000t;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import \u0000r.\u0000k.\u0000r.\u0000y;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Entity.class })
public abstract class MixinEntity
{
    public MixinEntity() {
        super();
    }
    
    @Shadow
    public abstract int func_82145_z();
    
    @Inject(method = { "turn" }, at = { @At("HEAD") }, cancellable = true)
    private void turn(float bq, float bv, final CallbackInfo callbackInfo) {
        final \u0000y \u0000y = new \u0000y((Entity)this, bq, bv);
        MinecraftForge.EVENT_BUS.post((Event)\u0000y);
        if (\u0000y.isCanceled()) {
            callbackInfo.cancel();
        }
        bq = \u0000y.bq;
        bv = \u0000y.bv;
    }
    
    @Redirect(method = { "onEntityUpdate" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getMaxInPortalTime()I"))
    private int getModifiedMaxInPortalTime(final Entity entity) {
        final \u0000t \u0000t = new \u0000t(entity, this.func_82145_z());
        MinecraftForge.EVENT_BUS.post((Event)\u0000t);
        return \u0000t.fs;
    }
}
