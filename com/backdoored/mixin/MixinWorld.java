package com.backdoored.mixin;

import \u0000r.\u0000k.\u0000r.\u0000d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import \u0000r.\u0000k.\u0000r.\u0000l;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ World.class })
public class MixinWorld
{
    public MixinWorld() {
        super();
    }
    
    @Inject(method = { "getSunBrightnessFactor" }, at = { @At("RETURN") }, cancellable = true, remap = false)
    private void getBrightnessOfSun(final float n, final CallbackInfoReturnable<Float> callbackInfoReturnable) {
        final \u0000l \u0000l = new \u0000l((float)callbackInfoReturnable.getReturnValue());
        MinecraftForge.EVENT_BUS.post((Event)\u0000l);
        callbackInfoReturnable.setReturnValue(\u0000l.mz);
    }
    
    @Inject(method = { "getSunBrightnessBody" }, at = { @At("RETURN") }, cancellable = true, remap = false)
    private void getBrightnessBodyOfSun(final float n, final CallbackInfoReturnable<Float> callbackInfoReturnable) {
        final \u0000l \u0000l = new \u0000l((float)callbackInfoReturnable.getReturnValue());
        MinecraftForge.EVENT_BUS.post((Event)\u0000l);
        callbackInfoReturnable.setReturnValue(\u0000l.mz);
    }
    
    @Inject(method = { "checkLightFor" }, at = { @At("HEAD") }, cancellable = true)
    private void checkLightForWrapper(final EnumSkyBlock enumSkyBlock, final BlockPos blockPos, final CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        final \u0000d \u0000d = new \u0000d((Boolean)null);
        MinecraftForge.EVENT_BUS.post((Event)\u0000d);
        if (\u0000d.sdo != null) {
            callbackInfoReturnable.setReturnValue(\u0000d.sdo);
        }
    }
}
