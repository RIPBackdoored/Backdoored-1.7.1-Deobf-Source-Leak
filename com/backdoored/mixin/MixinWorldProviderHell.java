package com.backdoored.mixin;

import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import \u0000r.\u0000k.\u0000r.\u0000l;
import net.minecraft.world.WorldProviderHell;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ WorldProviderHell.class })
public class MixinWorldProviderHell
{
    public MixinWorldProviderHell() {
        super();
    }
    
    @ModifyConstant(method = { "generateLightBrightnessTable" }, constant = { @Constant(floatValue = 0.9f) })
    private float getBrightness(final float n) {
        final \u0000l \u0000l = new \u0000l(n);
        MinecraftForge.EVENT_BUS.post((Event)\u0000l);
        return \u0000l.mz;
    }
}
