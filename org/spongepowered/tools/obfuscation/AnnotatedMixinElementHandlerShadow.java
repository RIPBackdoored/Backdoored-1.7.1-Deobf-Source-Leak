package org.spongepowered.tools.obfuscation;

import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import javax.lang.model.element.ExecutableElement;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import javax.lang.model.element.VariableElement;
import org.spongepowered.tools.obfuscation.interfaces.IObfuscationDataProvider;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import javax.lang.model.element.Element;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;
import java.util.Iterator;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;

class AnnotatedMixinElementHandlerShadow extends AnnotatedMixinElementHandler
{
    AnnotatedMixinElementHandlerShadow(final IMixinAnnotationProcessor mixinAnnotationProcessor, final AnnotatedMixin annotatedMixin) {
        super(mixinAnnotationProcessor, annotatedMixin);
    }
    
    public void registerShadow(final AnnotatedElementShadow<?, ?> annotatedElementShadow) {
        this.validateTarget(annotatedElementShadow.getElement(), annotatedElementShadow.getAnnotation(), annotatedElementShadow.getName(), "@Shadow");
        if (!annotatedElementShadow.shouldRemap()) {
            return;
        }
        final Iterator<TypeHandle> iterator = this.mixin.getTargets().iterator();
        while (iterator.hasNext()) {
            this.registerShadowForTarget(annotatedElementShadow, iterator.next());
        }
    }
    
    private void registerShadowForTarget(final AnnotatedElementShadow<?, ?> annotatedElementShadow, final TypeHandle typeHandle) {
        final ObfuscationData<?> obfuscationData = annotatedElementShadow.getObfuscationData(this.obf.getDataProvider(), typeHandle);
        if (obfuscationData.isEmpty()) {
            final String s = this.mixin.isMultiTarget() ? (" in target " + typeHandle) : "";
            if (typeHandle.isSimulated()) {
                annotatedElementShadow.printMessage(this.ap, Diagnostic.Kind.WARNING, "Unable to locate obfuscation mapping" + s + " for @Shadow " + annotatedElementShadow);
            }
            else {
                annotatedElementShadow.printMessage(this.ap, Diagnostic.Kind.WARNING, "Unable to locate obfuscation mapping" + s + " for @Shadow " + annotatedElementShadow);
            }
            return;
        }
        for (final ObfuscationType obfuscationType : obfuscationData) {
            try {
                annotatedElementShadow.addMapping(obfuscationType, obfuscationData.get(obfuscationType));
            }
            catch (Mappings.MappingConflictException ex) {
                annotatedElementShadow.printMessage(this.ap, Diagnostic.Kind.ERROR, "Mapping conflict for @Shadow " + annotatedElementShadow + ": " + ex.getNew().getSimpleName() + " for target " + typeHandle + " conflicts with existing mapping " + ex.getOld().getSimpleName());
            }
        }
    }
}
