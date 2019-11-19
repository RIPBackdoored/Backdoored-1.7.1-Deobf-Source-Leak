package com.backdoored.mixin;

import net.minecraft.client.renderer.entity.RenderPig;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ RenderPig.class })
public class MixinRenderPig
{
    public MixinRenderPig() {
        super();
    }
}
