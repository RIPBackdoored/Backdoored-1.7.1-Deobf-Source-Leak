package com.backdoored.mixin;

import net.minecraft.block.BlockSlime;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ BlockSlime.class })
public class MixinBlockSlime
{
    public MixinBlockSlime() {
        super();
    }
}
