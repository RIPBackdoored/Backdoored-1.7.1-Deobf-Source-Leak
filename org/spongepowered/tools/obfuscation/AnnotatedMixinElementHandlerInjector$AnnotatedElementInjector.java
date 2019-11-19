package org.spongepowered.tools.obfuscation;

import javax.tools.Diagnostic;
import java.util.Iterator;
import java.lang.annotation.Annotation;
import javax.lang.model.element.Element;
import org.spongepowered.asm.mixin.injection.Coerce;
import javax.lang.model.element.VariableElement;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.struct.InjectorRemap;
import javax.lang.model.element.ExecutableElement;

static class AnnotatedElementInjector extends AnnotatedElement<ExecutableElement>
{
    private final InjectorRemap state;
    
    public AnnotatedElementInjector(final ExecutableElement executableElement, final AnnotationHandle annotationHandle, final InjectorRemap state) {
        super(executableElement, annotationHandle);
        this.state = state;
    }
    
    public boolean shouldRemap() {
        return this.state.shouldRemap();
    }
    
    public boolean hasCoerceArgument() {
        if (!this.annotation.toString().equals("@Inject")) {
            return false;
        }
        final Iterator<? extends VariableElement> iterator = ((ExecutableElement)this.element).getParameters().iterator();
        return iterator.hasNext() && AnnotationHandle.of((Element)iterator.next(), Coerce.class).exists();
    }
    
    public void addMessage(final Diagnostic.Kind kind, final CharSequence charSequence, final Element element, final AnnotationHandle annotationHandle) {
        this.state.addMessage(kind, charSequence, element, annotationHandle);
    }
    
    @Override
    public String toString() {
        return this.getAnnotation().toString();
    }
}
