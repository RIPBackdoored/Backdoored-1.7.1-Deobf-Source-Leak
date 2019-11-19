package com.backdoored.mixin;

import \u0000r.\u0000k.\u0000d.\u0000m.\u0000o;
import \u0000r.\u0000k.\u0000d.\u0000m.\u0000s.\u0000g;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import \u0000r.\u0000k.\u0000r.\u0000cr;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityPlayerSP.class })
public class MixinEntityPlayerSP extends MixinEntityLivingBase
{
    private Minecraft mc;
    
    public MixinEntityPlayerSP() {
        super();
        this.mc = Minecraft.func_71410_x();
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("HEAD") })
    private void preMotion(final CallbackInfo callbackInfo) {
        final \u0000cr.\u0000y \u0000y = new \u0000cr.\u0000y(this.mc.field_71439_g.field_70177_z, this.mc.field_71439_g.field_70125_A, this.mc.field_71439_g.field_70122_E);
        MinecraftForge.EVENT_BUS.post((Event)\u0000y);
        this.mc.field_71439_g.field_70177_z = \u0000y.lss;
        this.mc.field_71439_g.field_70125_A = \u0000y.lsl;
        this.mc.field_71439_g.field_70122_E = \u0000y.lsy;
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("RETURN") })
    private void postMotion(final CallbackInfo callbackInfo) {
        final \u0000cr.\u0000h \u0000h = new \u0000cr.\u0000h(this.mc.field_71439_g.field_70177_z, this.mc.field_71439_g.field_70125_A, this.mc.field_71439_g.field_70122_E);
        MinecraftForge.EVENT_BUS.post((Event)\u0000h);
        this.mc.field_71439_g.field_70177_z = \u0000h.lss;
        this.mc.field_71439_g.field_70125_A = \u0000h.lsl;
        this.mc.field_71439_g.field_70122_E = \u0000h.lsy;
    }
    
    @Override
    public void func_70664_aZ() {
        try {
            final double field_70159_w = ((EntityPlayerSP)this).field_70159_w;
            final double field_70179_y = ((EntityPlayerSP)this).field_70179_y;
            super.func_70664_aZ();
            ((\u0000g)\u0000o.d((Class)\u0000g.class)).d(field_70159_w, field_70179_y, (EntityPlayerSP)this);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
