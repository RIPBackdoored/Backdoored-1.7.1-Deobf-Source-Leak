package com.backdoored.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import \u0000r.\u0000k.\u0000b.\u0000k;
import \u0000r.\u0000k.\u0000d.\u0000m.\u0000j.\u0000i;
import net.minecraft.client.renderer.RenderItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { RenderItem.class }, priority = 999999999)
public class MixinRenderItem
{
    public MixinRenderItem() {
        super();
    }
    
    @ModifyArg(method = { "renderEffect" }, at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/RenderItem.renderModel(Lnet/minecraft/client/renderer/block/model/IBakedModel;I)V"), index = 1)
    private int renderModel(final int n) {
        if (\u0000i.xk != null && \u0000i.xk.lo()) {
            return \u0000k.lz().getRGB();
        }
        return n;
    }
}
