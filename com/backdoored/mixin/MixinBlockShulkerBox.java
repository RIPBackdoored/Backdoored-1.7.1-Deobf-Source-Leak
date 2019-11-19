package com.backdoored.mixin;

import net.minecraft.block.BlockShulkerBox;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ BlockShulkerBox.class })
public class MixinBlockShulkerBox
{
    public MixinBlockShulkerBox() {
        super();
    }
}
