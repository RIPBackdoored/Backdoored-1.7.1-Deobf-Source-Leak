package org.spongepowered.tools.obfuscation;

import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.mirror.MethodHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.ElementKind;
import java.util.Iterator;
import java.util.List;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import javax.lang.model.type.DeclaredType;
import org.spongepowered.asm.mixin.Interface;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;

public class AnnotatedMixinElementHandlerSoftImplements extends AnnotatedMixinElementHandler
{
    AnnotatedMixinElementHandlerSoftImplements(final IMixinAnnotationProcessor mixinAnnotationProcessor, final AnnotatedMixin annotatedMixin) {
        super(mixinAnnotationProcessor, annotatedMixin);
    }
    
    public void process(final AnnotationHandle annotationHandle) {
        if (!this.mixin.remap()) {
            return;
        }
        final List<AnnotationHandle> annotationList = annotationHandle.getAnnotationList("value");
        if (annotationList.size() < 1) {
            this.ap.printMessage(Diagnostic.Kind.WARNING, "Empty @Implements annotation", this.mixin.getMixin(), annotationHandle.asMirror());
            return;
        }
        for (final AnnotationHandle annotationHandle2 : annotationList) {
            final Interface.Remap remap = annotationHandle2.<Interface.Remap>getValue("remap", Interface.Remap.ALL);
            if (remap == Interface.Remap.NONE) {
                continue;
            }
            try {
                this.processSoftImplements(remap, new TypeHandle(annotationHandle2.<DeclaredType>getValue("iface")), annotationHandle2.<String>getValue("prefix"));
            }
            catch (Exception ex) {
                this.ap.printMessage(Diagnostic.Kind.ERROR, "Unexpected error: " + ex.getClass().getName() + ": " + ex.getMessage(), this.mixin.getMixin(), annotationHandle2.asMirror());
            }
        }
    }
    
    private void processSoftImplements(final Interface.Remap remap, final TypeHandle typeHandle, final String s) {
        final Iterator<ExecutableElement> iterator = typeHandle.<ExecutableElement>getEnclosedElements(ElementKind.METHOD).iterator();
        while (iterator.hasNext()) {
            this.processMethod(remap, typeHandle, s, iterator.next());
        }
        final Iterator<TypeHandle> iterator2 = typeHandle.getInterfaces().iterator();
        while (iterator2.hasNext()) {
            this.processSoftImplements(remap, iterator2.next(), s);
        }
    }
    
    private void processMethod(final Interface.Remap remap, final TypeHandle typeHandle, final String s, final ExecutableElement executableElement) {
        final String string = executableElement.getSimpleName().toString();
        final String javaSignature = TypeUtils.getJavaSignature(executableElement);
        final String descriptor = TypeUtils.getDescriptor(executableElement);
        if (remap != Interface.Remap.ONLY_PREFIXED) {
            final MethodHandle method = this.mixin.getHandle().findMethod(string, javaSignature);
            if (method != null) {
                this.addInterfaceMethodMapping(remap, typeHandle, null, method, string, descriptor);
            }
        }
        if (s != null) {
            final MethodHandle method2 = this.mixin.getHandle().findMethod(s + string, javaSignature);
            if (method2 != null) {
                this.addInterfaceMethodMapping(remap, typeHandle, s, method2, string, descriptor);
            }
        }
    }
    
    private void addInterfaceMethodMapping(final Interface.Remap remap, final TypeHandle typeHandle, final String s, final MethodHandle methodHandle, final String s2, final String s3) {
        final ObfuscationData<MappingMethod> obfMethod = this.obf.getDataProvider().getObfMethod(new MappingMethod(typeHandle.getName(), s2, s3));
        if (obfMethod.isEmpty()) {
            if (remap.forceRemap()) {
                this.ap.printMessage(Diagnostic.Kind.ERROR, "No obfuscation mapping for soft-implementing method", methodHandle.getElement());
            }
            return;
        }
        this.addMethodMappings(methodHandle.getName(), s3, this.applyPrefix(obfMethod, s));
    }
    
    private ObfuscationData<MappingMethod> applyPrefix(final ObfuscationData<MappingMethod> obfuscationData, final String s) {
        if (s == null) {
            return obfuscationData;
        }
        final ObfuscationData<MappingMethod> obfuscationData2 = new ObfuscationData<MappingMethod>();
        for (final ObfuscationType obfuscationType : obfuscationData) {
            obfuscationData2.put(obfuscationType, obfuscationData.get(obfuscationType).addPrefix(s));
        }
        return obfuscationData2;
    }
}
