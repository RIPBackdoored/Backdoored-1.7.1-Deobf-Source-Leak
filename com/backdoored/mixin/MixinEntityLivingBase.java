package com.backdoored.mixin;

import \u0000r.\u0000k.\u0000r.\u0000cc;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import \u0000r.\u0000k.\u0000r.\u0000ci;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityLivingBase.class })
public class MixinEntityLivingBase
{
    public MixinEntityLivingBase() {
        super();
    }
    
    @Shadow
    public void func_70664_aZ() {
    }
    
    @ModifyConstant(method = { "getWaterSlowDown" }, constant = { @Constant(floatValue = 0.8f) })
    public float getWaterSlowDownWrapper(final float n) {
        final \u0000ci \u0000ci = new \u0000ci(n);
        MinecraftForge.EVENT_BUS.post((Event)\u0000ci);
        return \u0000ci.sgb;
    }
    
    @ModifyConstant(method = { "handleJumpWater" }, constant = { @Constant(doubleValue = 0.03999999910593033) })
    public double handleJumpWaterWrap(final double n) {
        final \u0000cc \u0000cc = new \u0000cc(n);
        MinecraftForge.EVENT_BUS.post((Event)\u0000cc);
        return \u0000cc.spx;
    }
    
    @ModifyConstant(method = { "handleJumpLava" }, constant = { @Constant(doubleValue = 0.03999999910593033) })
    public double handleJumpLavaWrap(final double n) {
        final \u0000cc \u0000cc = new \u0000cc(n);
        MinecraftForge.EVENT_BUS.post((Event)\u0000cc);
        return \u0000cc.spx;
    }
}
