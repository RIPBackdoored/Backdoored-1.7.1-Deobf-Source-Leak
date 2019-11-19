package com.backdoored.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import \u0000r.\u0000k.\u0000r.\u0000b;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.entity.item.EntityItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityItem.class })
public abstract class MixinEntityItem
{
    @Shadow
    private int field_145804_b;
    
    public MixinEntityItem() {
        super();
    }
    
    @Inject(method = { "setPickupDelay" }, at = { @At("RETURN") }, cancellable = true)
    public void setPickupDelayWrap(final int n, final CallbackInfo callbackInfo) {
        final \u0000b \u0000b = new \u0000b(this.field_145804_b);
        MinecraftForge.EVENT_BUS.post((Event)\u0000b);
        this.field_145804_b = \u0000b.rm;
    }
    
    @Inject(method = { "setDefaultPickupDelay" }, at = { @At("RETURN") }, cancellable = true)
    public void setDefaultPickupDelayWrap(final CallbackInfo callbackInfo) {
        final \u0000b \u0000b = new \u0000b(this.field_145804_b);
        MinecraftForge.EVENT_BUS.post((Event)\u0000b);
        this.field_145804_b = \u0000b.rm;
    }
    
    @Inject(method = { "setNoPickupDelay" }, at = { @At("RETURN") }, cancellable = true)
    public void setNoPickupDelayWrap(final CallbackInfo callbackInfo) {
        final \u0000b \u0000b = new \u0000b(this.field_145804_b);
        MinecraftForge.EVENT_BUS.post((Event)\u0000b);
        this.field_145804_b = \u0000b.rm;
    }
}
