package com.backdoored.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.renderer.chunk.RenderChunk;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ RenderChunk.class })
public class MixinRenderChunk
{
    public MixinRenderChunk() {
        super();
    }
    
    @Inject(method = { "setPosition" }, at = { @At("HEAD") })
    public void setPosition(final int n, final int n2, final int n3, final CallbackInfo callbackInfo) {
    }
}
