package com.backdoored.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import \u0000r.\u0000k.\u0000r.\u0000m;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.ChunkRenderContainer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ ChunkRenderContainer.class })
public class MixinChunkRenderContainer
{
    public MixinChunkRenderContainer() {
        super();
    }
    
    @Inject(method = { "preRenderChunk" }, at = { @At("HEAD") })
    public void preRenderChunk(final RenderChunk renderChunk, final CallbackInfo callbackInfo) {
        MinecraftForge.EVENT_BUS.post((Event)new \u0000m.\u0000h(renderChunk));
    }
}
