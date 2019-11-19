package org.spongepowered.asm.transformers;

import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.ClassWriter;

public class MixinClassWriter extends ClassWriter
{
    public MixinClassWriter(final int n) {
        super(n);
    }
    
    public MixinClassWriter(final ClassReader classReader, final int n) {
        super(classReader, n);
    }
    
    @Override
    protected String getCommonSuperClass(final String s, final String s2) {
        return ClassInfo.getCommonSuperClass(s, s2).getName();
    }
}
