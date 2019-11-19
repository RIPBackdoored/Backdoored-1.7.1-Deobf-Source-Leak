package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import javax.lang.model.element.VariableElement;

class AnnotatedElementShadowField extends AnnotatedElementShadow<VariableElement, MappingField>
{
    final /* synthetic */ AnnotatedMixinElementHandlerShadow this$0;
    
    public AnnotatedElementShadowField(final AnnotatedMixinElementHandlerShadow this$0, final VariableElement variableElement, final AnnotationHandle annotationHandle, final boolean b) {
        this.this$0 = this$0;
        super(variableElement, annotationHandle, b, IMapping.Type.FIELD);
    }
    
    @Override
    public MappingField getMapping(final TypeHandle typeHandle, final String s, final String s2) {
        return new MappingField(typeHandle.getName(), s, s2);
    }
    
    @Override
    public void addMapping(final ObfuscationType obfuscationType, final IMapping<?> obfuscatedName) {
        this.this$0.addFieldMapping(obfuscationType, this.setObfuscatedName(obfuscatedName), this.getDesc(), obfuscatedName.getDesc());
    }
    
    @Override
    public /* bridge */ IMapping getMapping(final TypeHandle typeHandle, final String s, final String s2) {
        return this.getMapping(typeHandle, s, s2);
    }
}
