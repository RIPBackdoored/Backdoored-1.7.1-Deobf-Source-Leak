package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import javax.lang.model.element.ExecutableElement;

class AnnotatedElementShadowMethod extends AnnotatedElementShadow<ExecutableElement, MappingMethod>
{
    final /* synthetic */ AnnotatedMixinElementHandlerShadow this$0;
    
    public AnnotatedElementShadowMethod(final AnnotatedMixinElementHandlerShadow this$0, final ExecutableElement executableElement, final AnnotationHandle annotationHandle, final boolean b) {
        this.this$0 = this$0;
        super(executableElement, annotationHandle, b, IMapping.Type.METHOD);
    }
    
    @Override
    public MappingMethod getMapping(final TypeHandle typeHandle, final String s, final String s2) {
        return typeHandle.getMappingMethod(s, s2);
    }
    
    @Override
    public void addMapping(final ObfuscationType obfuscationType, final IMapping<?> obfuscatedName) {
        this.this$0.addMethodMapping(obfuscationType, this.setObfuscatedName(obfuscatedName), this.getDesc(), obfuscatedName.getDesc());
    }
    
    @Override
    public /* bridge */ IMapping getMapping(final TypeHandle typeHandle, final String s, final String s2) {
        return this.getMapping(typeHandle, s, s2);
    }
}
