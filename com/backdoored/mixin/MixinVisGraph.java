package com.backdoored.mixin;

import net.minecraft.client.renderer.chunk.VisGraph;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ VisGraph.class })
public class MixinVisGraph
{
    public MixinVisGraph() {
        super();
    }
}
