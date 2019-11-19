package com.backdoored.mixin;

import net.minecraft.launchwrapper.IClassTransformer;

public class ClassTransformer implements IClassTransformer
{
    public ClassTransformer() {
        super();
    }
    
    public byte[] transform(final String s, final String s2, final byte[] array) {
        return array;
    }
}
